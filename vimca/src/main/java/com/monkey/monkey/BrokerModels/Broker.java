package com.monkey.monkey.BrokerModels;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.monkey.models.AppUser;
@Entity
@SQLDelete(sql = "UPDATE broker SET deleted = true WHERE broker_Id=?")
@FilterDef(name = "deletedBrokerFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedBrokerFilter", condition = "deleted = :isDeleted")
public class Broker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brokerId;
	private String email;
	private String password;
	private String mobile;
	private String name;
	private String gender;
	private String birthday;
    
    private String referralCode;
	
	private String country;
	private String state;
	private String city;
	private String zipCode;
	private String timezone;
	private String profileImage;
	private String profileImageUrl;
	
    private double lifeTimeCommission = 0.0;
    private double balanceCommission=0.0;
    
    

	// Bank account details for international payments
    private String bankAccountNumber;
    private String bankName;
    private String bankBranch;
    private String swiftCode;  // Required for international payments

   //payapal 
    private String paypalEmailId;
    
    
    private String referralLink;
    private String bannerLink;
    private String appletLink;
    @JsonIgnore
	private boolean deleted = Boolean.FALSE;
	public Long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
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
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
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
	public double getLifeTimeCommission() {
		return lifeTimeCommission;
	}
	public void setLifeTimeCommission(double lifeTimeCommission) {
		this.lifeTimeCommission = lifeTimeCommission;
	}
	public double getBalanceCommission() {
		return balanceCommission;
	}
	public void setBalanceCommission(double balanceCommission) {
		this.balanceCommission = balanceCommission;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getPaypalEmailId() {
		return paypalEmailId;
	}
	public void setPaypalEmailId(String paypalEmailId) {
		this.paypalEmailId = paypalEmailId;
	}
	public String getReferralLink() {
		return referralLink;
	}
	public void setReferralLink(String referralLink) {
		this.referralLink = referralLink;
	}
	public String getBannerLink() {
		return bannerLink;
	}
	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
	}
	public String getAppletLink() {
		return appletLink;
	}
	public void setAppletLink(String appletLink) {
		this.appletLink = appletLink;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Broker() {
		super();
		// TODO Auto-generated constructor stub
	}


}
