package com.monkey.monkey.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@SQLDelete(sql = "UPDATE friend_request SET deleted = true WHERE request_Id=?")
@FilterDef(name = "deletedFriendRequestFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFriendRequestFilter", condition = "deleted = :isDeleted")
public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long friendId;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "appUserId", scope = AppUser.class)
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser user;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "appUserId", scope = AppUser.class)
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "friend_id", nullable = false)
	private AppUser friend;
	
	
	private String createdDate;
	
	@JsonIgnore
	private boolean deleted = Boolean.FALSE;

	
	
	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public AppUser getFriend() {
		return friend;
	}

	public void setFriend(AppUser friend) {
		this.friend = friend;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		if (createdDate == null || createdDate.isEmpty()) {
			ZoneId timeZone = ZoneId.of("Asia/Kolkata");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			ZonedDateTime currentDateTime = ZonedDateTime.now(timeZone);
			String formattedDateTime = currentDateTime.format(formatter);
			this.createdDate = formattedDateTime;

		} else {
			this.createdDate = createdDate;

		}
	}

	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
