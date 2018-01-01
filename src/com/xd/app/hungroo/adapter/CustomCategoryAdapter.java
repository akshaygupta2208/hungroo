package com.xd.app.hungroo.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.activity.CategoryListing;
import com.xd.app.hungroo.activity.ProductListing;
import com.xd.app.hungroo.dto.CategoryDTO;

public class CustomCategoryAdapter extends BaseAdapter implements ListAdapter {
	Context context;
	List<CategoryDTO> categoryList;
	private static LayoutInflater inflater = null;

	public CustomCategoryAdapter(CategoryListing categoryListing,
			List<CategoryDTO> categoryList) {
		context = categoryListing;
		this.categoryList = categoryList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return categoryList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class Holder {
		private TextView tv;
		private ImageView img;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.category_custom_list, null);
		holder.tv = (TextView) rowView.findViewById(R.id.categoryName);
		holder.img = (ImageView) rowView.findViewById(R.id.categoryImage);
		holder.tv.setText(categoryList.get(position).getCategoryName());
		holder.img.setImageResource(R.drawable.food_guidelines);
		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ProductListing.class);
				intent.putExtra("categoryId", categoryList.get(position)
						.getCategoryId().toString());
				intent.putExtra("categoryName", categoryList.get(position)
						.getCategoryName());
				context.startActivity(intent);
			}
		});
		return rowView;
	}
}
