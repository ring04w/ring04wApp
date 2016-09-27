package com.example.airqualitylistener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class WelcomeActivity extends Activity {
	private boolean isFirstIn = true;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initViews();
	}

	private void initViews() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
		Editor editor = sp.edit();
		
		if (isFirstIn) {
			goGuide();
			editor.putBoolean("isFirstIn", false);
			editor.commit();
		}else {
			goHome();
		}
		
	}

	private void goHome() {
		startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
	}

	private void goGuide() {
			startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
	}
		
}
