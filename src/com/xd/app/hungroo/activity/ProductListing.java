/**
 * 
 */
package com.xd.app.hungroo.activity;

import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.adapter.CustomProductAdapter;
import com.xd.app.hungroo.dto.VendorProductInventory;
import com.xd.app.hungroo.util.ServiceCore;
import com.xd.app.hungroo.wrapper.AppCache;
import com.xd.app.hungroo.wrapper.ProductWrapper;

/**
 * @author : Akshay
 *
 */
public class ProductListing extends Activity {
	public ProductWrapper productWrapper;
	private ImageButton cartButton;
	private ImageButton backButton;
	private String categoryId;
	private SharedPreferences sp;
	private TextView heading;
	private TextView pageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list);
		pageName = (TextView) findViewById(R.id.pageName);
		pageName.setText("Products");
		// getActionBar().hide();
		Intent intent = this.getIntent();

		/* Obtain String from Intent */
		if (intent != null) {
			categoryId = intent.getExtras().getString("categoryId");
			heading = (TextView) findViewById(R.id.headingProductListing);

			heading.setText(intent.getExtras().getString("categoryName"));

		} else {
			// DO SOMETHING HERE
		}
		sp = getSharedPreferences("foodwaysSP", Context.MODE_PRIVATE);
		new DownloadServiceData().execute(new String[] { categoryId });

		cartButton = (ImageButton) findViewById(R.id.cartButton);
		backButton = (ImageButton) findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		cartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProductListing.this, Cart.class);
				startActivity(intent);

			}
		});
	}

	private class DownloadServiceData extends AsyncTask<String, Void, String> {
		ProgressDialog progress = new ProgressDialog(ProductListing.this,
				ProgressDialog.BUTTON_NEUTRAL);
		private ListView lv;

		@Override
		protected void onPreExecute() {

			// progress.setTitle("Loading");
			progress.setMessage("loading data...");
			progress.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			if (AppCache.getCacheMap().get(
					"productList_" + categoryId + "_"
							+ sp.getLong("locationId", 0)) == null) {
				ServiceCore sc = new ServiceCore();
				try {

					String responseString = sc
							.getRequest("http://52.76.37.190:8080/Gobazaar_Webshop/services/getProductByCategoryId/"
									+ categoryId
									+ "/"
									+ sp.getLong("locationId", 1));
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = mapper.readTree(responseString);
					productWrapper = mapper.readValue(
							node.path("entitiesResponse").get(0)
									.path("baseDTO"), ProductWrapper.class);
					AppCache.getCacheMap().put(
							"productList_" + categoryId + "_"
									+ sp.getLong("locationId", 1),
							productWrapper.getVendorProductWrapper());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				productWrapper = new ProductWrapper();
				productWrapper
						.setVendorProductWrapper((List<VendorProductInventory>) AppCache
								.getCacheMap().get(
										"productList_" + categoryId + "_"
												+ sp.getLong("locationId", 1)));
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (progress != null && progress.isShowing()) {
				progress.dismiss();
			}
			lv = (ListView) findViewById(R.id.productList);
			CustomProductAdapter adapter = new CustomProductAdapter(
					ProductListing.this,
					productWrapper.getVendorProductWrapper(), categoryId,
					sp.getLong("locationId", 1), sp);
			lv.setAdapter(adapter);
			adapter.notifyDataSetChanged();

		}
	}

}
