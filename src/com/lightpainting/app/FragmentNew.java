package com.lightpainting.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class FragmentNew extends Fragment {
	
	private EditText edittextstring;
	private TextView selcolor;
	private TextView camerasetuptext;
	private Button startShowActivity;
	private Button selColorDialog;
	private Button cameraSetup;
	private SeekBar seekbarsize;
	private SeekBar seekbarspeed;
	private SeekBar seekbarstop;
	private String fontsize = "200";
	private int time = 15;
	private int speed;
	private int iWaitToStop = 5;
	private String strcolor ="#FFFFFF";
	private ColorPickerDialog dialog;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View  contentView=  inflater.inflate(R.layout.activity_fragment_new,null);
	        startShowActivity = (Button) contentView.findViewById(R.id.start_button);
			edittextstring = (EditText) contentView.findViewById(R.id.edit_text);
			seekbarsize = (SeekBar) contentView.findViewById(R.id.seekBarFont);
			seekbarspeed = (SeekBar) contentView.findViewById(R.id.seekBarspeed);
			selColorDialog = (Button) contentView.findViewById(R.id.selcolor_button);
			selcolor = (TextView)contentView.findViewById(R.id.selcolor);
			cameraSetup = (Button) contentView.findViewById(R.id.camera_setup);
			camerasetuptext = (TextView)contentView.findViewById(R.id.camera_setuptext);
			seekbarstop = (SeekBar)contentView.findViewById(R.id.seekBarStop);

	  

		seekbarspeed.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				time = progress;
			// TODO 自动生成的方法存根
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
				//Toast.makeText(MainActivity.this, "越往左速度越快！", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getActivity().getApplicationContext(), "移动速度设置为"+String.valueOf(time)+"秒", Toast.LENGTH_SHORT).show();
			
			}
		
		});
		
		cameraSetup.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				
				int iedit = edittextstring.length();
				
				speed = time*23/iedit;
				
				// TODO 自动生成的方法存根
				camerasetuptext.setText("相机设置如下：ISO100-200，快门优先，快门速度为"+String.valueOf(time+iWaitToStop)+"秒");
			}
		
			});
	
		seekbarsize.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				//此行注释掉，程序里不需要调节文字大小的，使用默认文字大小即可
				//fontsize = String.valueOf(progress);
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
		seekbarstop.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				iWaitToStop = progress;
			
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
				Toast.makeText(getActivity().getApplicationContext(), "延迟时间为"+String.valueOf(iWaitToStop)+"秒", Toast.LENGTH_SHORT).show();
				
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
				
				MainActivity parentActivity = (MainActivity ) getActivity();//调用父activity
				
				//View  contentView=  inflater.inflate(R.layout.activity_fragment_new,null);
				LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());  
				layout.setOrientation(LinearLayout.VERTICAL);  
				  
				final TextView colorText = new TextView(getActivity().getApplicationContext());  
				ColorPickerView colorPick = new ColorPickerView(getActivity().getApplicationContext(),Color.parseColor("#FFFFFF"), 1.5,colorText);  
				  
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);  
				lp.gravity = Gravity.CENTER_HORIZONTAL;           
				layout.addView(colorPick, lp);  
				layout.addView(colorText,lp);  
				Log.d("Laomaizi","准备打开对话框");
				
				AlertDialog mAlertDialog =new AlertDialog.Builder(parentActivity).setTitle("选择一个颜色").setView(layout)  
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
				
				int iedit = edittextstring.length();
				if (iedit >0)
				{
					speed = time*30/iedit;
				}
				else 
				{
					Toast.makeText(getActivity().getApplicationContext(), "请输入需要光绘的文字！", Toast.LENGTH_SHORT).show(); 
					return;
				}
				
				Intent intent = new Intent(getActivity().getApplicationContext(),startShowActivity.class);
				String data = edittextstring.getText().toString();
				intent.putExtra("showtext",data);
				intent.putExtra("fontsize", fontsize);
				intent.putExtra("speed", speed);
				intent.putExtra("color", strcolor);
				
				int iWaittoBlank = iedit*iWaitToStop/time;
				intent.putExtra("stop", iWaittoBlank);
				Log.d("Laomaizi","滚动时间为"+String.valueOf(time)+"字符长度为"+String.valueOf(iedit)+"启动延时"+
				String.valueOf(iWaitToStop)+"预留空格"+String.valueOf(iWaittoBlank));
				

				//此处开始显示跑马灯字体的启动画面，设置黑屏图像显示5秒，
				/*MainActivity.this.setContentView(R.layout.activity_blank);
				Toast.makeText(MainActivity.this, "页面全黑一下！", Toast.LENGTH_SHORT).show(); 
				try {
					Thread.sleep(3000);
					
					Toast.makeText(MainActivity.this, "我在睡觉！", Toast.LENGTH_SHORT).show();
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				*/
				startActivity(intent);
				Log.d("laomaizi", "启动第二个activity"+String.valueOf(speed));
				
				//============以下为讲文字保存到历史记录中的代码===
				 
				 try {
					SaveToFavlist(data,fontsize,speed,strcolor);
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				 
			}

			private void SaveToFavlist(String data, String fontsize, int speed,
					String strcolor) throws IOException {
/*				File newXmlFile = new File(getActivity().getApplicationContext()+"/fav.xml");
				Log.d("Laomaizi",getActivity().getApplicationContext().toString());
				if(!newXmlFile.exists()){
					try {
						newXmlFile.createNewFile();
						Log.d("Laomaizi","目录不存在，创建之");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}*/
				
				String local_file = Environment.getExternalStorageDirectory().getAbsolutePath()+"/fav/";
				File newXmlFile = new File(local_file);
				if(!newXmlFile.exists()){
					newXmlFile.mkdirs();
					Log.d("Laomaizi","不存在目录，创建"+Environment.getExternalStorageDirectory().getAbsolutePath().toString());
				}

				local_file = newXmlFile.getAbsolutePath()+"/"+"fav.xml";
				newXmlFile = new File(local_file);
				try {
				   if(!newXmlFile.exists()) {
					   newXmlFile.createNewFile();
					   Log.d("Laomaizi","已经存在"+Environment.getExternalStorageDirectory().getAbsolutePath().toString());
				   } 
				} catch (IOException ex) {
					ex.printStackTrace();
				
					}
				FileOutputStream fileos = null;
				
				try {
					fileos = new FileOutputStream(newXmlFile);
				} catch (FileNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
				XmlSerializer serializer = Xml.newSerializer();
				
				try {
					
					////<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
					serializer.setOutput(fileos,"UTF-8");
					serializer.startDocument(null, Boolean.valueOf(true));
					serializer.setFeature("http://xmlpull.org/v1/doc/feature.html#indent-output", true);
					//<lightpainting xmlns="http://www.laomaizi.com"
					serializer.startTag(null, "lightpainting");
					serializer.attribute(null, "xmlns", "http://www.laomaizi.com");
					serializer.startTag(null, "text");
					serializer.text(data);
					serializer.endTag(null, "text");
					serializer.startTag(null, "fontsize");
					serializer.text(fontsize);
					serializer.endTag(null, "fontsize");
					serializer.startTag(null, "speed");
					serializer.text(String.valueOf(speed));
					serializer.endTag(null, "speed");
					serializer.startTag(null, "color");
					serializer.text(strcolor);
					serializer.endTag(null, "color");
					serializer.endTag(null, "lightpainting");
					serializer.endDocument();
					serializer.flush();
					Log.d("Laomaizi","写入文件完成");
					fileos.close();
					
				} catch (IllegalArgumentException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
			
		});
	  return contentView;	
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.main, menu);
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
