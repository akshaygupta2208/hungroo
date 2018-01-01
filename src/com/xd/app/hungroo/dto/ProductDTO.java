package com.xd.app.hungroo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDTO {
	private String cartProductId;
	private Long productId;
	private String name;
	private String description;
	private String familyCode;
	private Integer brandId;
	private String brandMessage;
	private String marketingMessage;
	private String warrantyApplicable;
	private String guaranteeApplicable;
	private String packagingInstruction;
	private Integer createdBy;
	private Date createdDate;
	private Integer modifiedBy;
	private Date modifiedDate;
	private String status;
	private String guaranty;
	private String otherInfo;
	private String gbuCode;
	private Integer categoryId;
	private BigDecimal shippingCost;

	public String getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(String cartProductId) {
		this.cartProductId = cartProductId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFamilyCode() {
		return familyCode;
	}

	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandMessage() {
		return brandMessage;
	}

	public void setBrandMessage(String brandMessage) {
		this.brandMessage = brandMessage;
	}

	public String getMarketingMessage() {
		return marketingMessage;
	}

	public void setMarketingMessage(String marketingMessage) {
		this.marketingMessage = marketingMessage;
	}

	public String getWarrantyApplicable() {
		return warrantyApplicable;
	}

	public void setWarrantyApplicable(String warrantyApplicable) {
		this.warrantyApplicable = warrantyApplicable;
	}

	public String getGuaranteeApplicable() {
		return guaranteeApplicable;
	}

	public void setGuaranteeApplicable(String guaranteeApplicable) {
		this.guaranteeApplicable = guaranteeApplicable;
	}

	public String getPackagingInstruction() {
		return packagingInstruction;
	}

	public void setPackagingInstruction(String packagingInstruction) {
		this.packagingInstruction = packagingInstruction;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGuaranty() {
		return guaranty;
	}

	public void setGuaranty(String guaranty) {
		this.guaranty = guaranty;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getGbuCode() {
		return gbuCode;
	}

	public void setGbuCode(String gbuCode) {
		this.gbuCode = gbuCode;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

}
