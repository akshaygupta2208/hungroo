/**
 * 
 */
package com.xd.app.hungroo.activity;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.adapter.CustomCartAdapter;
import com.xd.app.hungroo.dto.CartDTO;
import com.xd.app.hungroo.util.ServiceCore;

/**
 * @author : Akshay
 *
 */
public class Cart extends Activity {

	ListView lv;
	Context context;
	private SharedPreferences sp;
	private Button placeOrederBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_listing);
		context = this;
		sp = getSharedPreferences("foodwaysSP", Context.MODE_PRIVATE);
		new DownloadServiceData().execute(new String[] { "1" });

		placeOrederBtn = (Button) findViewById(R.id.placeOrderButton);
		placeOrederBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (sp.getLong("customerId", 0) == 0) {
					Intent intent = new Intent(Cart.this, Login.class);
					startActivity(intent);
				} else {
					new CreateOrderService().execute(new String[] { "1" });
				}
			}
		});
	}

	private class CreateOrderService extends AsyncTask<String, Void, String> {
		ProgressDialog progress = new ProgressDialog(context,
				ProgressDialog.BUTTON_NEUTRAL);

		@Override
		protected void onPreExecute() {
			// progress.setTitle("Loading");
			progress.setMessage("creating order...");
			progress.show();
		}

		@Override
		protected String doInBackground(String... params) {
			ServiceCore sc = new ServiceCore();
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode json = mapper.readTree("{}");
				if (sp.getString("sessionId", null) != null) {
					((ObjectNode) json).put("sessionId",
							sp.getString("sessionId", null));
				}
				if (sp.getLong("customerId", 0) != 0) {
					((ObjectNode) json).put("customerId",
							sp.getLong("customerId", 0));
				}
				String responseString = sc
						.postRequest(
								"http://52.76.37.190:8080/Gobazaar_Webshop/services/createOrder",
								json.toString());
				JsonNode node = mapper.readTree(responseString);
				// = mapper.readValue(node.path("entitiesResponse").get(0)
				// .path("baseDTO"), CartDTO.class);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;

		}

	}

	private class DownloadServiceData extends AsyncTask<String, Void, String> {
		ProgressDialog progress = new ProgressDialog(context,
				ProgressDialog.BUTTON_NEUTRAL);
		private ListView lv;
		private CartDTO cart;

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
				ObjectMapper mapper = new ObjectMapper();
				JsonNode json = mapper.readTree("{}");
				if (sp.getString("sessionId", null) != null) {
					((ObjectNode) json).put("sessionId",
							sp.getString("sessionId", null));
				}
				if (sp.getLong("customerId", 0) != 0) {
					((ObjectNode) json).put("customerId",
							sp.getLong("customerId", 0));
				}

				String responseString = sc
						.postRequest(
								"http://52.76.37.190:8080/Gobazaar_Webshop/services/getcart/0",
								json.toString());
				JsonNode node = mapper.readTree(responseString);
				if (node.path("entitiesResponse") != null) {
					cart = mapper.readValue(node.path("entitiesResponse")
							.get(0).path("baseDTO"), CartDTO.class);
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
			lv = (ListView) findViewById(R.id.cartList);
			TextView totalCartValue = (TextView) findViewById(R.id.totalCartValue);
			if (cart == null) {
				lv.setVisibility(View.GONE);
				totalCartValue.setVisibility(View.GONE);
				placeOrederBtn.setVisibility(View.GONE);
				findViewById(R.id.totalCartValueLabel).setVisibility(View.GONE);
				
				TextView noCartFoundLabel = (TextView) findViewById(R.id.noCartFoundLablel);
				noCartFoundLabel.setText("No cart Found");
				noCartFoundLabel.setVisibility(View.VISIBLE);
			} else {
				CustomCartAdapter adapter = new CustomCartAdapter(context, cart);
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();

				totalCartValue.setText("" + cart.getTotalCartValue());
			}
		}
	}

}
