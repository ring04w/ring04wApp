package com.example.airqualitylistener;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class GuideActivity extends Activity {

	private List<View> views;
	private ViewPaperAdapter vpAdapter;
	private ViewPager vp;
	private Button btnStart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initViews();
	}

	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		
		views.add(inflater.inflate(R.layout.guide_one, null));
		views.add(inflater.inflate(R.layout.guide_two, null));
		views.add(inflater.inflate(R.layout.guide_three, null));
		views.add(inflater.inflate(R.layout.guide_four, null));
		vpAdapter = new ViewPaperAdapter(views, this);
		
		vp = (ViewPager)findViewById(R.id.vp_guide);
		vp.setAdapter(vpAdapter);
		btnStart = (Button)views.get(3).findViewById(R.id.btn_start);
		
		btnStart.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
			}
		});
		
		
	}
	

}
