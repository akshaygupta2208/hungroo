package com.xd.app.hungroo.dto;

import java.math.BigDecimal;


public class CartProductDTO {
	private Long cartId;
	private Long cartProductId;
	private Integer categoryid;
	private BigDecimal discountApplied;
	private String gbuCode;
	private BigDecimal subTotal;
	private BigDecimal mrp;
	private Long productId;
	private String title;
	private String productStatus;
	private Integer quantity;
	private BigDecimal salePrice;
	private BigDecimal shippingCharge;
	private Long vendorId;
	private String productname;
	private String skuId;
	private BigDecimal vat;
	private BigDecimal tax;
private Integer inStock;
	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(Long cartProductId) {
		this.cartProductId = cartProductId;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public BigDecimal getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(BigDecimal discountApplied) {
		this.discountApplied = discountApplied;
	}

	public String getGbuCode() {
		return gbuCode;
	}

	public void setGbuCode(String gbuCode) {
		this.gbuCode = gbuCode;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getMrp() {
		return mrp;
	}

	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(BigDecimal shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Integer getInStock() {
		return inStock;
	}

	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

}
