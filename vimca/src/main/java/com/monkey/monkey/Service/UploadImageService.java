package com.monkey.monkey.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monkey.monkey.Repository.UploadImageRepository;
import com.monkey.monkey.aws.AmazonClient;
import com.monkey.monkey.models.UploadImage;

@Service
public class UploadImageService {
	@Value("${my.global.path}")
	private String path;
	@Autowired
	private AmazonClient amazonClient;

	@Autowired
	private UploadImageRepository uploadImageRepository;
	
	public UploadImage save(UploadImage image) {
		return uploadImageRepository.save(image);
	}

	public UploadImage saveUploadImage(MultipartFile uploadImageFile, UploadImage uploadImage) throws Exception {
	    UploadImage imageEntity = new UploadImage();
	    final String path = "https://amsaccounting.s3.ap-south-1.amazonaws.com/";

	    if (uploadImageFile != null && !uploadImageFile.isEmpty()) {
	        try {
	            String fileDownloadUri = amazonClient.uploadFile(uploadImageFile);
	            imageEntity.setImageNamePath(path + fileDownloadUri);
	            imageEntity.setImageName(uploadImageFile.getOriginalFilename());
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("Error uploading file: " + e.getMessage());
	        }
	    }

	    if (uploadImage.getAppUser() != null) {
	        imageEntity.setAppUser(uploadImage.getAppUser());
	    }

	    UploadImage entity = save(imageEntity); // Assuming 'save' is a method that persists the UploadImage object
	    return entity;
	}
	
	 public List<UploadImage> getAllImagesByAppUserId(Long appUserId) {
	        try {
	            return uploadImageRepository.findByAppUserId(appUserId,false);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error fetching images for AppUser with ID: " + appUserId, e);
	        }
	    }
}
