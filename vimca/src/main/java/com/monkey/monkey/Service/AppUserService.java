package com.monkey.monkey.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.monkey.GlobelException.MyException;
import com.monkey.monkey.Repository.AppUserRepository;
import com.monkey.monkey.models.AppUser;

@Service
public class AppUserService {
	
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private EntityManager entityManager;

	public AppUser addAppUser(AppUser appUser) {
	    AppUser savedAppUser = appUserRepository.save(appUser);
	    return savedAppUser;
	}

	public AppUser getAppUserById(Long id, boolean isDeleted) {
	    try {
	        AppUser appUserEntity = appUserRepository.getUserById(id, false);
	        return appUserEntity;
	    } catch (Exception e) {
	        return null;
	    }
	}

	public AppUser getAppUserByMobileNumber(String phoneNumber, boolean isDeleted) {
	    try {
	        AppUser appUserEntity = appUserRepository.getUserByMobileNumber(phoneNumber, false);
	        return appUserEntity;
	    } catch (Exception e) {
	        return null;
	    }
	}

	public Iterable<AppUser> findAllAppUser(boolean isDeleted) {
	    Session session = entityManager.unwrap(Session.class);
	    Filter filter = session.enableFilter("deletedAppUserFilter");
	    filter.setParameter("isDeleted", isDeleted);
	    Iterable<AppUser> appUserEntities = appUserRepository.findAll();
	    session.disableFilter("deletedAppUserFilter");
	    return appUserEntities;
	}

	public AppUser deleteAppUserById(Long id) {
	    try {
	        AppUser appUser = appUserRepository.getUserById(id, false);
	        appUserRepository.deleteById(id);
	        return appUser;
	    } catch (Exception e) {
	        return null;
	    }
	}

	public AppUser getAppUserByEmailId(String email, boolean b) {
	    return appUserRepository.getByEmailId(email, false);
	}

	public AppUser updateAppUser(AppUser existingAppUser, AppUser updatedAppUser) {
	    if (existingAppUser != null && updatedAppUser != null) {
	        // Ensure the updatedAppUser has the ID of the existingAppUser
	        updatedAppUser.setAppUserId(existingAppUser.getAppUserId());

	        // Check if phone number needs to be updated and is not already taken
	        if (updatedAppUser.getMobile() != null 
	            && !updatedAppUser.getMobile().equals(existingAppUser.getMobile())) {
	            AppUser userMobile = appUserRepository.getUserByMobileNumber(updatedAppUser.getMobile(), false);
	            if (userMobile != null) {
	                throw new MyException("Phone number is already present, please try again with a new number");
	            }
	            existingAppUser.setMobile(updatedAppUser.getMobile());
	        }

	        // Check if email needs to be updated and is not already taken
	        if (updatedAppUser.getEmail() != null) {
	            AppUser userByEmail = appUserRepository.getByEmailId(updatedAppUser.getEmail(), false);
	            if (userByEmail == null || userByEmail.getEmail().equals(existingAppUser.getEmail())) {
	                existingAppUser.setEmail(updatedAppUser.getEmail());
	            } else {
	                throw new MyException("Email is already present, please try again with a new email id");
	            }
	        }

	        // Update other fields if they are provided
	        if (updatedAppUser.getName() != null) {
	            existingAppUser.setName(updatedAppUser.getName());
	        }
	        if (updatedAppUser.getGender() != null) {
	            existingAppUser.setGender(updatedAppUser.getGender());
	        }

	        // Update birthday if provided
	        if (updatedAppUser.getBirthday() != null) {
	            existingAppUser.setBirthday(updatedAppUser.getBirthday());
	        }

	        // Update language if provided
	        if (updatedAppUser.getLanguage() != null) {
	            existingAppUser.setLanguage(updatedAppUser.getLanguage());
	        }

	        // Update the date
	        if (updatedAppUser.getUpdatedDate() == null) {
	            ZoneId timeZone = ZoneId.of("Asia/Kolkata");
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	            ZonedDateTime currentDateTime = ZonedDateTime.now(timeZone);
	            String formattedDateTime = currentDateTime.format(formatter);
	            existingAppUser.setUpdatedDate(formattedDateTime);
	        } else {
	            existingAppUser.setUpdatedDate(updatedAppUser.getUpdatedDate());
	        }

	        // Save the updated user
	        AppUser updatedAppUserEntity = appUserRepository.save(existingAppUser);
	        return updatedAppUserEntity;
	    } else {
	        throw new MyException("User or updated information is null");
	    }
	}

	public AppUser update(AppUser appUser) {
	    return appUserRepository.save(appUser);
	}
}
