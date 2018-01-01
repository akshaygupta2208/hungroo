package com.xd.app.hungroo.dto;

import java.math.BigDecimal;

public class VendorProductInventory {
	private String cartProductId;
	private Long vendorProductInventoryId;
	private String gbuCode;
	private Long vendorId;
	private Long availableProduct;
	private Long consumedProduct;
	private String vendorName;
	private Integer ranking;
	private BigDecimal pricePerUnit;
	private Integer categoryId;
	private String vendorSku;
	private String vendorStatus;
	private BigDecimal salePricePerUnit;
	private ProductDTO product;
	private int tempQty;

	public String getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(String cartProductId) {
		this.cartProductId = cartProductId;
	}

	public int getTempQty() {
		return tempQty;
	}

	public void setTempQty(int tempQty) {
		this.tempQty = tempQty;
	}

	public Long getVendorProductInventoryId() {
		return vendorProductInventoryId;
	}

	public void setVendorProductInventoryId(Long vendorProductInventoryId) {
		this.vendorProductInventoryId = vendorProductInventoryId;
	}

	public String getGbuCode() {
		return gbuCode;
	}

	public void setGbuCode(String gbuCode) {
		this.gbuCode = gbuCode;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getAvailableProduct() {
		return availableProduct;
	}

	public void setAvailableProduct(Long availableProduct) {
		this.availableProduct = availableProduct;
	}

	public Long getConsumedProduct() {
		return consumedProduct;
	}

	public void setConsumedProduct(Long consumedProduct) {
		this.consumedProduct = consumedProduct;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getVendorSku() {
		return vendorSku;
	}

	public void setVendorSku(String vendorSku) {
		this.vendorSku = vendorSku;
	}

	public String getVendorStatus() {
		return vendorStatus;
	}

	public void setVendorStatus(String vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	public BigDecimal getSalePricePerUnit() {
		return salePricePerUnit;
	}

	public void setSalePricePerUnit(BigDecimal salePricePerUnit) {
		this.salePricePerUnit = salePricePerUnit;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

}
