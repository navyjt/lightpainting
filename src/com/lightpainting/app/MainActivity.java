package com.lightpainting.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	
	private EditText edittextstring;
	private Button startShowActivity;
	private SeekBar seekbarsize;
	private String fontsize = "200";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//使应用程序无标题栏
		setContentView(R.layout.activity_main);
		
		startShowActivity = (Button) findViewById(R.id.start_button);
		edittextstring = (EditText) findViewById(R.id.edit_text);
		seekbarsize = (SeekBar) findViewById(R.id.seekBarFont);
		
		seekbarsize.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				fontsize = String.valueOf(progress);
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
				
			}
		
		}
		);

		startShowActivity.setOnClickListener(new OnClickListener()
		{
				//按钮点击事件
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,startShowActivity.class);
				String data = edittextstring.getText().toString();
				intent.putExtra("showtext",data);
				intent.putExtra("fontsize", fontsize);
				startActivity(intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
