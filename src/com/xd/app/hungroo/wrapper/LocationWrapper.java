package com.xd.app.hungroo.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xd.app.hungroo.dto.LocationDTO;

public class LocationWrapper implements Serializable {

	private List<LocationDTO> locationWrapper = new ArrayList<LocationDTO>();

	public List<LocationDTO> getLocationWrapper() {
		return locationWrapper;
	}

	public void setLocationWrapper(List<LocationDTO> locationWrapper) {
		this.locationWrapper = locationWrapper;
	}

}
