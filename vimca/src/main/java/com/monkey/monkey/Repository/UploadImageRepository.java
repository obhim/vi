package com.monkey.monkey.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monkey.monkey.models.UploadImage;

public interface UploadImageRepository  extends JpaRepository<UploadImage, Long>{

	@Query("select u from UploadImage u where  d.appUser.appUserId=:appUserId  AND d.deleted=:isDeleted")
	List<UploadImage> findByAppUserId(Long appUserId, boolean isDeleted);

}
