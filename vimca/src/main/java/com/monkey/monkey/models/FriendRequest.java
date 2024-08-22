package com.monkey.monkey.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.monkey.monkey.enums.FriendRequestStatus;

@Entity
@SQLDelete(sql = "UPDATE friend_request SET deleted = true WHERE request_Id=?")
@FilterDef(name = "deletedFriendRequestFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFriendRequestFilter", condition = "deleted = :isDeleted")
public class FriendRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "appUserId", scope = AppUser.class)
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id", nullable = false)
	private AppUser sender;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "appUserId", scope = AppUser.class)
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_id", nullable = false)
	private AppUser receiver;

	@Enumerated(EnumType.STRING)
    private FriendRequestStatus status = FriendRequestStatus.PENDING;

	private String createdDate;
	private String updatedDate;

	@JsonIgnore
	private boolean deleted = Boolean.FALSE;

	
	
	@PrePersist
    public void validateFriendRequest(AppUser sender,AppUser receiver) {
        if (sender.equals(receiver)) {
            throw new IllegalArgumentException("You cannot send a friend request to yourself.");
        }
    }

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public AppUser getSender() {
		return sender;
	}

	public void setSender(AppUser sender) {
		this.sender = sender;
	}

	public AppUser getReceiver() {
		return receiver;
	}

	public void setReceiver(AppUser receiver) {
		this.receiver = receiver;
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
	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
	public FriendRequestStatus getStatus() {
		return status;
	}

	public void setStatus(FriendRequestStatus status) {
		this.status = status;
	}

	public FriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
