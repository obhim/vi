package com.monkey.monkey.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monkey.monkey.Service.UploadImageService;
import com.monkey.monkey.aws.AmazonClient;
import com.monkey.monkey.models.UploadImage;

@CrossOrigin
@RestController
public class UploadImageController {

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private UploadImageService uploadImageService;
    
    
   

}

