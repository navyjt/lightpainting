package com.lightpainting.app;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	
	private EditText edittextstring;
	private TextView selcolor;
	private Button startShowActivity;
	private Button selColorDialog;
	private SeekBar seekbarsize;
	private SeekBar seekbarspeed;
	private String fontsize = "200";
	private String speed = "150";
	private String strcolor ="#FFFFFF";
	 private ColorPickerDialog dialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//使应用程序无标题栏
		setContentView(R.layout.activity_main);
		
		startShowActivity = (Button) findViewById(R.id.start_button);
		edittextstring = (EditText) findViewById(R.id.edit_text);
		seekbarsize = (SeekBar) findViewById(R.id.seekBarFont);
		seekbarspeed = (SeekBar) findViewById(R.id.seekBarspeed);
		selColorDialog = (Button) findViewById(R.id.selcolor_button);
		selcolor = (TextView)findViewById(R.id.selcolor);
		
		seekbarspeed.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				speed = String.valueOf(progress);
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
				Toast.makeText(MainActivity.this, "越往左速度越快！", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
			
			}
		
		});
		
		
		
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

		/*selColorDialog.setOnClickListener(new OnClickListener()   
		{	//使用ColorPickerDialog类，不够完美

			@Override
			public void onClick(View v) {
				dialog = new ColorPickerDialog(MainActivity.this, selcolor.getTextColors().getDefaultColor(),getResources().getString(R.string.app_name), 
						
						
                        new ColorPickerDialog.OnColorChangedListener() {   

                        

                    public void colorChanged(int color) {   

                        selcolor.setTextColor(color);   

                    }   

                });   

                dialog.show();

				// TODO 自动生成的方法存根
				
			}
			
		});*/
		
		selColorDialog.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				
				Log.d("Laomaizi","点击按钮");
				LinearLayout layout = new LinearLayout(MainActivity.this);  
				layout.setOrientation(LinearLayout.VERTICAL);  
				  
				final TextView colorText = new TextView(MainActivity.this);  
				ColorPickerView colorPick = new ColorPickerView(MainActivity.this,Color.parseColor("#FFFFFF"), 0.8,colorText);  
				  
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);  
				lp.gravity = Gravity.CENTER_HORIZONTAL;           
				layout.addView(colorPick, lp);  
				layout.addView(colorText,lp);  
				Log.d("Laomaizi","准备打开对话框");
				
				AlertDialog mAlertDialog =new AlertDialog.Builder(MainActivity.this).setTitle("选择一个颜色").setView(layout)  
				.setPositiveButton(getString(R.string.dialog_color_OK), new DialogInterface.OnClickListener() 
				{  
				    public void onClick(DialogInterface dialog, int id) {     
				       Log.d("laomaizi","the color choose is "+colorText.getText());  
				   	   strcolor = (String) colorText.getText();
					Log.d("Laomaizi","对话框返回"+strcolor);
					selcolor.setTextColor(Color.parseColor(strcolor));
				 }  
				}).setNegativeButton(getString(R.string.dialog_color_Cancel), new DialogInterface.OnClickListener() 
				{  
				    public void onClick(DialogInterface dialog, int id) {  
				         dialog.cancel();  
				    }  
				 }
				).create(); 
				mAlertDialog.show();
				Log.d("Laomaizi","对话框建立"+((String) colorText.getText()));
				//selcolor.setTextColor(Color.parseColor((String) colorText.getText()));
			
				
				
			}
			
		});
		
		startShowActivity.setOnClickListener(new OnClickListener()
		{
				//按钮点击事件
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,startShowActivity.class);
				String data = edittextstring.getText().toString();
				intent.putExtra("showtext",data);
				intent.putExtra("fontsize", fontsize);
				intent.putExtra("speed", speed);
				intent.putExtra("color", strcolor);
				startActivity(intent);
				Log.d("laomaizi", "启动第二个activity");
				//System.out.println("启动第二个activity");
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onTouchEvent(MotionEvent e)  {
		  // TODO Auto-generated method stub
		Log.d("laomaizi", "第一个activity点击触发");
		  // Toast.makeText(this, "DOWN " + e.getAction(), Toast.LENGTH_SHORT).show(); 
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
