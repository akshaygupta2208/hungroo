package com.xd.app.hungroo.adapter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.activity.ProductListing;
import com.xd.app.hungroo.dto.CartDTO;
import com.xd.app.hungroo.dto.CartProductDTO;
import com.xd.app.hungroo.dto.VendorProductInventory;
import com.xd.app.hungroo.util.ServiceCore;
import com.xd.app.hungroo.wrapper.AppCache;

public class CustomProductAdapter extends BaseAdapter implements ListAdapter {
	Context context;
	boolean productAddedToCart = false;
	String cartProductId;
	List<VendorProductInventory> productList;
	private String categoryId;
	private Long location;
	private SharedPreferences sp;
	private static LayoutInflater inflater = null;

	public CustomProductAdapter(ProductListing productListing,
			List<VendorProductInventory> list, String categoryId, long l,
			SharedPreferences sp) {
		this.categoryId = categoryId;
		this.location = l;
		context = productListing;
		this.productList = list;
		this.sp = sp;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return productList.size();
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
		public TextView productName;
		public ImageView productImage;
		public TextView description;
		public ImageView vegNonVegIcon;
		public TextView price;
		public Button plusButton;
		public Button minusButton;
		public TextView quantity;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.product_custom_list, null);
		holder.productImage = (ImageView) rowView
				.findViewById(R.id.productImage);
		holder.productName = (TextView) rowView.findViewById(R.id.productName);
		holder.description = (TextView) rowView.findViewById(R.id.description);
		holder.vegNonVegIcon = (ImageView) rowView
				.findViewById(R.id.vegNonVegIcon);
		holder.price = (TextView) rowView.findViewById(R.id.price);
		holder.plusButton = (Button) rowView.findViewById(R.id.plusButton);
		holder.minusButton = (Button) rowView.findViewById(R.id.minusButton);
		holder.quantity = (TextView) rowView.findViewById(R.id.quantity);

		holder.productImage.setImageResource(R.drawable.food_guidelines);
		holder.productName.setText(productList.get(position).getProduct()
				.getName());
		holder.description.setText(productList.get(position).getProduct()
				.getDescription());
		holder.vegNonVegIcon.setImageResource(R.drawable.veg);

		/*
		 * if (productList.get(position).getIsFeatured()) {
		 * holder.vegNonVegIcon.setImageResource(R.drawable.veg); } else {
		 * holder.vegNonVegIcon.setImageResource(R.drawable.non_veg); }
		 */

		holder.price.setText("price: Rs."
				+ productList.get(position).getSalePricePerUnit());
		holder.quantity.setText("" + productList.get(position).getTempQty());
		holder.plusButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int qty = productList.get(position).getTempQty() + 1;
				if (addProductToCart(productList.get(position), context)) {
					productList.get(position).setTempQty(qty);
					productList.get(position).setCartProductId(cartProductId);
					holder.quantity.setText(""
							+ productList.get(position).getTempQty());
				}
			}
		});
		holder.minusButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int qty = productList.get(position).getTempQty() - 1;
				if (qty > 0) {
					// add product to cart
					if (addProductToCart(productList.get(position), context)) {
						productList.get(position).setTempQty(qty);
						productList.get(position).setCartProductId(
								cartProductId);
						holder.quantity.setText(""
								+ productList.get(position).getTempQty());
					}
				}
				if (qty == 0) {
					// delete product from cart
					if (deleteProductFromCart(productList.get(position),
							context)) {
						productList.get(position).setTempQty(qty);
						holder.quantity.setText(""
								+ productList.get(position).getTempQty());
						updateProductInCache();
					}
				}
			}
		});
		/*
		 * rowView.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Toast.makeText(context, "You Clicked " +
		 * productList.get(position), Toast.LENGTH_SHORT).show(); } });
		 */
		return rowView;
	}

	protected void updateProductInCache() {
		AppCache.getCacheMap().put(
				"productList_" + categoryId + "_" + location, productList);

	}

	protected boolean deleteProductFromCart(
			VendorProductInventory vendorProductInventory, Context context2) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean addProductToCart(
			VendorProductInventory vendorProductInventory, Context context2) {

		CartProductDTO cartProductDTO = new CartProductDTO();
		cartProductDTO.setCategoryid(vendorProductInventory.getCategoryId());
		cartProductDTO.setDiscountApplied(new BigDecimal(0));
		cartProductDTO.setGbuCode(vendorProductInventory.getGbuCode());
		cartProductDTO.setMrp(vendorProductInventory.getPricePerUnit());
		cartProductDTO.setProductId(vendorProductInventory.getProduct()
				.getProductId());
		cartProductDTO.setProductname(vendorProductInventory.getProduct()
				.getName());
		cartProductDTO.setProductStatus(vendorProductInventory.getProduct()
				.getStatus());
		cartProductDTO.setQuantity(vendorProductInventory.getTempQty());
		cartProductDTO.setSalePrice(vendorProductInventory
				.getSalePricePerUnit());
		cartProductDTO.setShippingCharge(vendorProductInventory.getProduct()
				.getShippingCost());
		cartProductDTO.setSkuId(vendorProductInventory.getVendorSku());
		cartProductDTO
				.setSubTotal(vendorProductInventory.getSalePricePerUnit());
		cartProductDTO.setVendorId(vendorProductInventory.getVendorId());
		CartDTO cart = new CartDTO();
		if (sp.getLong("customerId", 0) != 0) {
			cart.setCustomerId(sp.getLong("customerId", 0));
		}
		if (sp.getString("sessionId", null) == null) {
			cart.setSessionId(Long.valueOf(sp.getString("sessionId", null)));
		}
		List<CartProductDTO> prodList = new ArrayList<CartProductDTO>();
		prodList.add(cartProductDTO);
		cart.setCartProductList(prodList);
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(cart);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// JsonNode jsonNode = mapper.readTree(json);
		productAddedToCart = false;
		new DownloadServiceData().execute(new String[] { json });
		return productAddedToCart;
	}

	private class DownloadServiceData extends AsyncTask<String, Void, String> {
		ProgressDialog progress = new ProgressDialog(context,
				ProgressDialog.BUTTON_NEUTRAL);

		// private List prgmNameList;

		// private List<Integer> prgmImages = new ArrayList<Integer>();

		@Override
		protected void onPreExecute() {
			// progress.setTitle("Loading");
			progress.setMessage("loading data...");
			progress.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			ServiceCore sc = new ServiceCore();
			try {
				String responseString = sc
						.postRequest(
								"http://52.76.37.190:8080/Gobazaar_Webshop/services/addproducttocart",
								urls[0]);
				if (responseString.contains("successfully")) {
					ObjectMapper mapper = new ObjectMapper();
					JsonNode addToCartResp = mapper.readTree(responseString);
					productAddedToCart = true;
					cartProductId = addToCartResp.path("entitiesResponse")
							.get(0).path("baseDTO").path("cartProductList")
							.get(0).path("cartProductId").getTextValue();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (progress != null && progress.isShowing()) {
				progress.dismiss();
			}
			if (productAddedToCart) {
				Toast.makeText(context, "product Added to cart Successfully",
						1000).show();
			}
		}
	}
}
