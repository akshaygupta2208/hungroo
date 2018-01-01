package com.xd.app.hungroo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDTO {
	private Long cartId;
	private Long customerId;
	private Long sessionId;
	private List<CartProductDTO> cartProductList = new ArrayList<CartProductDTO>();
	private Date createdTime;
	private String status;
	private Long totalCartValue;
	private Integer totalQuantity;
	private Long totalSavings;
	private Long shippingcharge;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTotalCartValue() {
		return totalCartValue;
	}

	public void setTotalCartValue(Long totalCartValue) {
		this.totalCartValue = totalCartValue;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Long getTotalSavings() {
		return totalSavings;
	}

	public void setTotalSavings(Long totalSavings) {
		this.totalSavings = totalSavings;
	}

	public Long getShippingcharge() {
		return shippingcharge;
	}

	public void setShippingcharge(Long shippingcharge) {
		this.shippingcharge = shippingcharge;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public List<CartProductDTO> getCartProductList() {
		return cartProductList;
	}

	public void setCartProductList(List<CartProductDTO> cartProductList) {
		this.cartProductList = cartProductList;
	}

}
