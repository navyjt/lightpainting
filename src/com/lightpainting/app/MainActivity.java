package com.lightpainting.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;


public class MainActivity extends FragmentActivity {

	private View currentButton;
	public MainActivity() {
		// TODO 自动生成的构造函数存根
	}

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        requestWindowFeature(Window.FEATURE_NO_TITLE);//使应用程序无标题栏
        setContentView(R.layout.activity_main);
        initComponents();

    }

	private void initComponents() {
		// TODO 自动生成的方法存根
		 ImageButton btn_one = (ImageButton) findViewById(R.id.buttom_one);
	     ImageButton btn_two = (ImageButton) findViewById(R.id.buttom_two);
	

	     btn_one.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            FragmentNew fragment_one = new FragmentNew();
            ft.replace(R.id.fl_content, fragment_one);
            ft.commit();
            setButton(v);

        }

		
    });

    btn_two.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
           // Fragment_Two fragment_two = new Fragment_Two();
            //ft.replace(R.id.fl_content, fragment_two);
            //ft.commit();
           // setButton(v);

        }
    });

    btn_one.performClick();
}    
    private void setButton(View v) {
		 if (currentButton != null && currentButton.getId() != v.getId()) {
	            currentButton.setEnabled(true);
	        }
	        v.setEnabled(false);
	        currentButton = v;
	        // TODO 自动生成的方法存根
		
	}
}