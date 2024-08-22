package com.monkey.monkey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE product_id=?")
@FilterDef(name = "deletedProductEntityFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductEntityFilter", condition = "deleted = :isDeleted")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	private String productName;
	private String baseGemAmount;
	private String bonousGemAmount;
	private String offer;
	private String price;
	private String createdDate;
	private String updatedDate;
	private String discription;

	@JsonIgnore
	private boolean deleted = Boolean.FALSE;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBaseGemAmount() {
		return baseGemAmount;
	}

	public void setBaseGemAmount(String baseGemAmount) {
		this.baseGemAmount = baseGemAmount;
	}

	public String getBonousGemAmount() {
		return bonousGemAmount;
	}

	public void setBonousGemAmount(String bonousGemAmount) {
		this.bonousGemAmount = bonousGemAmount;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

}
