package com.xd.app.hungroo.activity;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.webkit.WebView;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.util.ServiceCore;
import com.xd.app.hungroo.wrapper.AppCache;
import com.xd.app.hungroo.wrapper.CategoryWrapper;
import com.xd.app.hungroo.wrapper.LocationWrapper;

/**
 * @author : Akshay
 *
 */
public class Launcher extends Activity {
	private SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		WebView wv = (WebView) findViewById(R.id.webView);
		wv.loadUrl("file:///android_asset/launcher.html");
		sp = getSharedPreferences("foodwaysSP", Context.MODE_PRIVATE);
		new DownloadServiceData().execute(new String[] { "1" });
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	private class DownloadServiceData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			ServiceCore sc = new ServiceCore();
			try {
				if (sp.getString("sessionId", null) == null) {
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString(
							"sessionId",
							telephonyManager.getDeviceId()
									+ (int) (Math.random() * 99999));
					editor.commit();
				}
				// get all locations
				String responseString = sc
						.getRequest("http://52.76.37.190:8080/Gobazaar_Webshop/services/getLocations");
				ObjectMapper mapper = new ObjectMapper();

				JsonNode node = mapper.readTree(responseString);
				LocationWrapper locationWrapper = mapper.readValue(
						node.path("entitiesResponse").get(0).path("baseDTO"),
						LocationWrapper.class);
				AppCache.getCacheMap().put("locationWrapper", locationWrapper);
				// get all categories
				String responseStringCategory = sc
						.getRequest("http://52.76.37.190:8080/Gobazaar_Webshop/services/getLiveCategory");
				JsonNode categoryNode = mapper.readTree(responseStringCategory);
				CategoryWrapper categoryWrapper = mapper.readValue(categoryNode
						.path("entitiesResponse").get(0).path("baseDTO"),
						CategoryWrapper.class);
				AppCache.getCacheMap().put("categoryWrapper", categoryWrapper);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Intent intent = new Intent(Launcher.this, LocationSelector.class);
			startActivity(intent);
		}
	}
}
