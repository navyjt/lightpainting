package com.lightpainting.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class startShowActivity extends Activity {

	private int ifont ;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//使应用程序无标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_startshow);
		Intent intent = getIntent();
		String data = intent.getStringExtra("showtext");
		String fontsize = intent.getStringExtra("fontsize");
		ifont = Integer.parseInt(fontsize);
		TextView showtext = (TextView) findViewById(R.id.showtext);
		showtext.setText(data);
		showtext.setTextSize(ifont);
		showtext.setTextColor(Color.WHITE);
		
		
	
	}
}

//android:theme="@style/AppTheme" >