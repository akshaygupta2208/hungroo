package com.xd.app.hungroo.activity;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.adapter.CustomCategoryAdapter;
import com.xd.app.hungroo.util.ServiceCore;
import com.xd.app.hungroo.wrapper.AppCache;
import com.xd.app.hungroo.wrapper.CategoryWrapper;

/**
 * @author : Akshay
 *
 */
public class CategoryListing extends Activity {
	public CategoryWrapper categoryWrapper;
	private ImageButton cartButton;
	private ImageButton backButton;
	private TextView pageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list);

		new DownloadServiceData().execute(new String[] { "1" });
		pageName = (TextView) findViewById(R.id.pageName);
		pageName.setText("Category");

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
				Intent intent = new Intent(CategoryListing.this, Cart.class);
				startActivity(intent);

			}
		});

	}

	private class DownloadServiceData extends AsyncTask<String, Void, String> {
		ProgressDialog progress = new ProgressDialog(CategoryListing.this,
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
			if (AppCache.getCacheMap().get("categoryWrapper") == null) {
				ServiceCore sc = new ServiceCore();
				try {
					String responseString = sc
							.getRequest("http://52.76.37.190:8080/Gobazaar_Webshop/services/getLiveCategory");
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = mapper.readTree(responseString);
					categoryWrapper = mapper.readValue(
							node.path("entitiesResponse").get(0)
									.path("baseDTO"), CategoryWrapper.class);
					AppCache.getCacheMap().put("categoryWrapper",
							categoryWrapper);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				categoryWrapper = (CategoryWrapper) AppCache.getCacheMap().get(
						"categoryWrapper");
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (progress != null && progress.isShowing()) {
				progress.dismiss();
			}
			lv = (ListView) findViewById(R.id.categoryList);
			lv.setAdapter(new CustomCategoryAdapter(CategoryListing.this,
					categoryWrapper.getCategoryWrapper()));
		}
	}
}
