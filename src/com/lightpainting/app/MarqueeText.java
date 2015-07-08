package com.lightpainting.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MarqueeText extends TextView implements Runnable {
	
	private int currentScrollX;// 当前滚动的位置 
	private boolean isStop = false; 
	private int textWidth; 
	private boolean isMeasure = false; 
	private int delaytime = 0;
	

	public MarqueeText(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO 自动生成的构造函数存根
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO 自动生成的构造函数存根
	}


	
	protected void onDraw(Canvas canvas) { 
		
		// TODO Auto-generated method stub 
		super.onDraw(canvas); 
	
		if (!isMeasure) 
		{// 文字宽度只需获取一次就可以了 
		getTextWidth(); 
		isMeasure = true; 
		} 
	} 
		/** 
		* 获取文字宽度 
		*/ 
	private void getTextWidth() { 
		
		Paint paint = this.getPaint(); 
		String str = this.getText().toString(); 
		textWidth = (int) paint.measureText(str); 
	} 
		@Override 
	public void run() { 
			

			
		currentScrollX += 5;// 滚动速度 
		scrollTo(currentScrollX, 0);
		
		if (isStop) { 
		return; 
		} 
		
		if (getScrollX() <= -(this.getWidth())) { 
		scrollTo(textWidth, 0); 
		currentScrollX = textWidth; 
		 //return; 
		} 
		
		postDelayed(this, delaytime); 
		String timestr = Integer.toString(delaytime);
		//Log.d("laomaizi","文字开始滚动");
		
	} 
		// 开始滚动 
/*	public void startScroll() { 
		
		isStop = false; 
		this.removeCallbacks(this); 
		post(this); 
		Log.d("laomaizi","startScroll");
		
	} */
	public void startScroll(int i) { 
		
		isStop = false; 
		this.removeCallbacks(this); 
		post(this); 
		delaytime = i;

		
	} 
		// 停止滚动 
	public void stopScroll() { 
		
		isStop = true; 
		
	} 
		// 从头开始滚动 
	public void startFor0() { 
		
		currentScrollX = 0; 
		startScroll(delaytime); 
	} 

}
