package com.xd.app.hungroo.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.xd.app.hungroo.dto.VendorProductInventory;

public class ProductWrapper {

	private List<VendorProductInventory> vendorProductWrapper = new ArrayList<VendorProductInventory>();

	public List<VendorProductInventory> getVendorProductWrapper() {
		return vendorProductWrapper;
	}

	public void setVendorProductWrapper(List<VendorProductInventory> vendorProductWrapper) {
		this.vendorProductWrapper = vendorProductWrapper;
	}
}