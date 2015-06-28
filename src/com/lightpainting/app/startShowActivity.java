package com.lightpainting.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class startShowActivity extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//使应用程序无标题栏
		setContentView(R.layout.activity_startshow);
		Intent intent = getIntent();
		String data = intent.getStringExtra("showtext");
		TextView showtext = (TextView) findViewById(R.id.showtext);
		showtext.setText(data);
		
		
	
	}
}

//android:theme="@style/AppTheme" >