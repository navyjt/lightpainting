package com.lightpainting.app;

import util.DebugLog;
import util.MarqueeText;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class startShowActivity extends Activity {

	private int ifont;
	private MarqueeText showtext;
	private Vibrator vibrator;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 使应用程序无标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_startshow);
		Intent intent = getIntent();
		String data = intent.getStringExtra("showtext");
		String fontsize = intent.getStringExtra("fontsize");
		int ispeed = intent.getIntExtra("speed", 150);
		int istop = intent.getIntExtra("stop", 1);
		DebugLog.log("第二个activity获取延迟时间" + String.valueOf(istop));

		String allData = "";
		for (int i = 0; i < istop + 1; i++) {
			allData += " ";
			DebugLog.log("第二个activity增加空格" + String.valueOf(i));
		}
		allData += data;
		String color = intent.getStringExtra("color");
		ifont = Integer.parseInt(fontsize);
		showtext = (MarqueeText) findViewById(R.id.showtext);
		showtext.setText(allData);

		showtext.setTextSize(ifont);
		showtext.setTextColor(Color.parseColor(color));
		DebugLog.log("第二个activity启动成功" + allData + "speed is "
						+ String.valueOf(ispeed));
		start(showtext, ispeed);
	}

	public boolean onTouchEvent(MotionEvent e) {
		((Activity) this).finish();
		return true;
	}

	public void start(View v, int i) {

		DebugLog.log("第二个activity 延时启动开始");

		// 开始移动字符前振动一次
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 100, 400, 100, 400 }; // 停止 开启 停止 开启
		vibrator.vibrate(pattern, -1); // 重复两次上面的pattern 如果只想震动一次，index设为-1
		showtext.startScroll(i);
	}

	public void stop(View v) {
		showtext.stopScroll();
	}

	public void startFor0(View v) {
		showtext.startFor0();
	}
}
