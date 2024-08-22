package com.monkey.monkey.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.monkey.monkey.BrokerModels.Broker;

@Entity
@SQLDelete(sql = "UPDATE app_user SET deleted = true WHERE app_user_Id=?")
@FilterDef(name = "deletedAppUserFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedAppUserFilter", condition = "deleted = :isDeleted")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appUserId;
	private String email;
	private String password;
	private String mobile;
	private String name;
	private String gender;
	private String birthday;
	private String language;
	private String createdDate;
	private String updatedDate;
	// private int age;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	private String timezone;

	private String profileImage;
	private String profileImageUrl;

	// App Information
	private String deviceType;
	private Boolean subscriptionStatus=Boolean.FALSE;
	private Double availableGems = 0.0;
	private Double usedGems = 0.0;

	private Boolean isBanned = false; // Indicates if the user is banned
	private String banType;
	private Integer banTimes = 0;
	private Long banStartTime;
	private Long banEndTime;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "brokerId", scope = Broker.class)
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "broker_id", nullable = false)
	private Broker broker;

	private String referralType;

	// friend Request
	@OneToMany(mappedBy = "sender")
	private Set<FriendRequest> sentRequests;

	@OneToMany(mappedBy = "receiver")
	private Set<FriendRequest> receivedRequests;

	@JsonIgnore
	private boolean deleted = Boolean.FALSE;

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Boolean getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(Boolean subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public Double getAvailableGems() {
		return availableGems;
	}

	public void setAvailableGems(Double availableGems) {
		this.availableGems = availableGems;
	}

	public Double getUsedGems() {
		return usedGems;
	}

	public void setUsedGems(Double usedGems) {
		this.usedGems = usedGems;
	}

	public Boolean getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}

	public String getBanType() {
		return banType;
	}

	public void setBanType(String banType) {
		this.banType = banType;
	}

	public Integer getBanTimes() {
		return banTimes;
	}

	public void setBanTimes(Integer banTimes) {
		this.banTimes = banTimes;
	}

	public Long getBanStartTime() {
		return banStartTime;
	}

	public void setBanStartTime(Long banStartTime) {
		this.banStartTime = banStartTime;
	}

	public Long getBanEndTime() {
		return banEndTime;
	}

	public void setBanEndTime(Long banEndTime) {
		this.banEndTime = banEndTime;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public String getReferralType() {
		return referralType;
	}

	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}

	public Set<FriendRequest> getSentRequests() {
		return sentRequests;
	}

	public void setSentRequests(Set<FriendRequest> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public Set<FriendRequest> getReceivedRequests() {
		return receivedRequests;
	}

	public void setReceivedRequests(Set<FriendRequest> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}

}