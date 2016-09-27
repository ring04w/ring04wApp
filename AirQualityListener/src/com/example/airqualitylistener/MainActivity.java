package com.example.airqualitylistener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btnReloadData;
	private TextView tvPmData;
	private ImageView imgSettings;
	private TextView releaseTime;
	private TextView pm25;
	private TextView mCityName;
	String response;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnReloadData = (Button) findViewById(R.id.btn_reload_data);
		tvPmData = (TextView) findViewById(R.id.tv_pm_data);
		imgSettings = (ImageView) findViewById(R.id.img_settings);
		releaseTime = (TextView) findViewById(R.id.relase_time);
		pm25 = (TextView) findViewById(R.id.pm25);
		mCityName = (TextView) findViewById(R.id.city_name);
		

		btnReloadData
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View v) {
						reload();
					}
				});

		imgSettings.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SettingsActivity.class);
				startActivity(intent);
			}
		});

	}

	private void reload() {

		new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... params) {
                try {
                   URL url = new URL("http://aqicn.org/publishingdata/json/");
                    // URL url = new URL("http://www.weather.com.cn/data/cityinfo/101190404.html");
                    InputStream in = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer content = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                    reader.close();
                    return content.toString();
                }catch(IOException e) {
                    e.printStackTrace();
                }

                return null;
            
	}
		

// 	private void parseJsonWithHttpGet(String jsonData) {
// 		try {
// 			JSONArray jsonArray = new JSONArray(jsonData);
// 			JSONObject jsonObject = jsonArray.getJSONObject(0);
// 			String cityName = jsonObject.getString("area");
// 			Double pm25Value = jsonObject.getDouble("pm2_5");
			
// 			pm25.setText(pm25Value.toString());
// 			mCityName.setText(cityName.toString());
			
// 		}catch(Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
// }
	protected void onPostExecute(String result) {
	if (result != null) {
	try {
	JSONArray jsonArray = new JSONArray(result);
	JSONObject jsonObject = jsonArray.getJSONObject(0);
	
	String cityName = jsonObject.getString("cityName");
	String localName = jsonObject.getString("localName");

	JSONArray pollutantsArray = jsonObject.getJSONArray("pollutants");
	JSONObject pollutantsObject = pollutantsArray.getJSONObject(0);
	String date = pollutantsObject.getString("time");
	
	Double pm25Value = pollutantsObject.getDouble("value");
//	tvPmData.setText("Date:" + date.toString() + " cityName: " +
//	cityName.toString()
//	+ "\n localName: " + localName.toString()
//	+ "\n PM2.5: " + pm25Value );
	releaseTime.setText(date.toString());
	pm25.setText(pm25Value.toString());

	} catch (JSONException e) {
	e.printStackTrace();
	}
	
	}
	}
	
	}.execute();

	}
}