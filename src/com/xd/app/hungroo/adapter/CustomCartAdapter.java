package com.xd.app.hungroo.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.dto.CartDTO;
import com.xd.app.hungroo.dto.CartProductDTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCartAdapter extends BaseAdapter {
	List<CartProductDTO> cartProducts = new ArrayList<CartProductDTO>();
	Context context;
	private static LayoutInflater inflater = null;

	public CustomCartAdapter(Context context, CartDTO cart) {
		if (cart.getCartProductList() != null) {
			cartProducts = cart.getCartProductList();
		}
		this.context = context;

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cartProducts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public class Holder {

		public TextView procutName;
		public ImageView productImage;
		public TextView priceCartProduct;
		public TextView quantityCartProduct;
		public Button deleteButtonCartProduct;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.cart_custom_listing, null);
		holder.procutName = (TextView) rowView.findViewById(R.id.productNameCartProduct);
		holder.productImage = (ImageView) rowView.findViewById(R.id.productImageCartProduct);
		holder.priceCartProduct = (TextView) rowView.findViewById(R.id.priceCartProduct);
		holder.quantityCartProduct = (TextView) rowView.findViewById(R.id.quantityCartProduct);
		holder.deleteButtonCartProduct = (Button) rowView.findViewById(R.id.deleteButtonCartProduct);
		holder.productImage.setImageResource(R.drawable.food_guidelines);
		holder.procutName.setText(cartProducts.get(position).getProductname());

		// holder.procutName.setText("asdfghjklkjhgfdsadfghjkl");
		holder.priceCartProduct.setText("subtotal:" + cartProducts.get(position).getSalePrice()
				.multiply(new BigDecimal(cartProducts.get(position).getQuantity())));
		// holder.priceCartProduct.setText("sdfghj------------");
		holder.quantityCartProduct.setText("qty:" + cartProducts.get(position).getQuantity());
		holder.deleteButtonCartProduct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// delete product from cart
			}
		});
		return rowView;
	}

}
