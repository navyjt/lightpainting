package com.lightpainting.app;

import android.app.Activity;
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
import android.widget.Toast;

public class startShowActivity extends Activity  {

	private int ifont ;
	private int ispeed;
	private MarqueeText showtext;
	private Vibrator vibrator;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ʹӦ�ó����ޱ�����
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_startshow);
		Intent intent = getIntent();
		String data = intent.getStringExtra("showtext");
		String fontsize = intent.getStringExtra("fontsize");
		int ispeed = intent.getIntExtra("speed",150);
		String color = intent.getStringExtra("color");
		ifont = Integer.parseInt(fontsize);
		//ispeed = Integer.parseInt(speed);
		showtext = (MarqueeText) findViewById(R.id.showtext);
		showtext.setText(data);
		showtext.setTextSize(ifont);
		showtext.setTextColor(Color.parseColor(color));
		Log.d("laomaizi", "�ڶ���activity�����ɹ�");
		start(showtext,ispeed);
	}
	
	public boolean onTouchEvent(MotionEvent e)  {
		  // TODO Auto-generated method stub
		Log.d("laomaizi", "�ڶ���activity�������");
		((Activity) this).finish();
		  // Toast.makeText(this, "DOWN " + e.getAction(), Toast.LENGTH_SHORT).show(); 
		 return true; 
	}
	
	
	public void start(View v,int i) {
		
		Log.d("laomaizi", "�ڶ���activity ��ʱ������ʼ");
/*		��δ�������ʹ�ƶ��ַ���ʱ2����ʾ
		try {
            Thread.currentThread();
			Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		*/
		
		Log.d("laomaizi", "�ڶ���activity ��ʱ��������");
		
		//��ʼ�ƶ��ַ�ǰ��һ��
		vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
	    long [] pattern = {100,400,100,400};   // ֹͣ ���� ֹͣ ����   
	    vibrator.vibrate(pattern,-1);           //�ظ����������pattern ���ֻ����һ�Σ�index��Ϊ-1   
		showtext.startScroll(i); 
	} 
	
	public void stop(View v) { 
		showtext.stopScroll(); 
	} 
	
	public void startFor0(View v){ 
		showtext.startFor0(); 
	} 
}

//android:theme="@style/AppTheme" >
//android:ellipsize="marquee"
//android:focusable="true"
//android:focusableInTouchMode="true"