package com.lightpainting.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MarqueeText extends TextView implements Runnable {
	
	private int currentScrollX;// ��ǰ������λ�� 
	private boolean isStop = false; 
	private int textWidth; 
	private boolean isMeasure = false; 
	private int delaytime = 0;
	

	public MarqueeText(Context context) {
		super(context);
		// TODO �Զ����ɵĹ��캯�����
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO �Զ����ɵĹ��캯�����
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO �Զ����ɵĹ��캯�����
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO �Զ����ɵĹ��캯�����
	}


	
	protected void onDraw(Canvas canvas) { 
		
		// TODO Auto-generated method stub 
		super.onDraw(canvas); 
	
		if (!isMeasure) 
		{// ���ֿ��ֻ���ȡһ�ξͿ����� 
		getTextWidth(); 
		isMeasure = true; 
		} 
	} 
		/** 
		* ��ȡ���ֿ�� 
		*/ 
	private void getTextWidth() { 
		
		Paint paint = this.getPaint(); 
		String str = this.getText().toString(); 
		textWidth = (int) paint.measureText(str); 
	} 
		@Override 
	public void run() { 
			

			
		currentScrollX += 5;// �����ٶ� 
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
		//Log.d("laomaizi","���ֿ�ʼ����");
		
	} 
		// ��ʼ���� 
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
		// ֹͣ���� 
	public void stopScroll() { 
		
		isStop = true; 
		
	} 
		// ��ͷ��ʼ���� 
	public void startFor0() { 
		
		currentScrollX = 0; 
		startScroll(delaytime); 
	} 

}
