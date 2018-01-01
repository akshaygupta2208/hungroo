package com.xd.app.hungroo.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xd.app.hungroo.dto.CategoryDTO;

public class CategoryWrapper implements Serializable {

	private List<CategoryDTO> categoryWrapper = new ArrayList<CategoryDTO>();

	public List<CategoryDTO> getCategoryWrapper() {
		return categoryWrapper;
	}

	public void setCategoryWrapper(List<CategoryDTO> categoryWrapper) {
		this.categoryWrapper = categoryWrapper;
	}

}
