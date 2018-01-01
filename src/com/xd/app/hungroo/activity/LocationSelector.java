/**
 * 
 */
package com.xd.app.hungroo.activity;

import java.util.ArrayList;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.xd.app.hungroo.R;
import com.xd.app.hungroo.dto.LocationDTO;
import com.xd.app.hungroo.util.ServiceCore;
import com.xd.app.hungroo.wrapper.AppCache;
import com.xd.app.hungroo.wrapper.LocationWrapper;

/**
 * @author : Akshay
 *
 */
public class LocationSelector extends Activity {
	private LocationWrapper locationWrapper;
	Spinner spinner;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_picker);
		new DownloadServiceData().execute(new String[] { "1" });
		spinner = (Spinner) findViewById(R.id.locationSpinner);
		Button submitButton = (Button) findViewById(R.id.locationSubmitButton);
		sp = getSharedPreferences("foodwaysSP", Context.MODE_PRIVATE);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(LocationSelector.this,
						spinner.getSelectedItem().toString(),
						Toast.LENGTH_SHORT).show();
				SharedPreferences.Editor editor = sp.edit();
				editor.putLong(
						"locationId",
						locationWrapper.getLocationWrapper()
								.get(spinner.getSelectedItemPosition())
								.getLocationId());
				editor.putString("locationName",
						(String) spinner.getSelectedItem());
				editor.commit();
				Intent intent = new Intent(LocationSelector.this,
						CategoryListing.class);
				startActivity(intent);
			}
		});
	}

	private class DownloadServiceData extends AsyncTask<String, Void, String> {
		ProgressDialog progress = new ProgressDialog(LocationSelector.this);

		@Override
		protected void onPreExecute() {
			// progress.setTitle("Loading");
			progress.setMessage("loading data...");
			progress.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			if (AppCache.getCacheMap().get("locationWrapper") == null) {
				ServiceCore sc = new ServiceCore();
				try {
					String responseString = sc
							.getRequest("http://52.76.37.190:8080/Gobazaar_Webshop/services/getLocations");
					ObjectMapper mapper = new ObjectMapper();

					JsonNode node = mapper.readTree(responseString);
					locationWrapper = mapper.readValue(
							node.path("entitiesResponse").get(0)
									.path("baseDTO"), LocationWrapper.class);
					AppCache.getCacheMap().put("locationWrapper",
							locationWrapper);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				locationWrapper = (LocationWrapper) AppCache.getCacheMap().get(
						"locationWrapper");
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (progress != null && progress.isShowing()) {
				progress.dismiss();
			}
			List<String> locations = new ArrayList<String>();
			for (LocationDTO locationDTO : locationWrapper.getLocationWrapper()) {
				locations.add(locationDTO.getLocation());
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					LocationSelector.this,
					android.R.layout.simple_spinner_item, locations);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
		}
	}
}