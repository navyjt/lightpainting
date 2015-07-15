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
			// TODO �Զ����ɵķ������
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO �Զ����ɵķ������
				//Toast.makeText(MainActivity.this, "Խ�����ٶ�Խ�죡", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(getActivity().getApplicationContext(), "�ƶ��ٶ�����Ϊ"+String.valueOf(time)+"��", Toast.LENGTH_SHORT).show();
			
			}
		
		});
		
		cameraSetup.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				
				int iedit = edittextstring.length();
				
				speed = time*23/iedit;
				
				// TODO �Զ����ɵķ������
				camerasetuptext.setText("����������£�ISO100-200���������ȣ������ٶ�Ϊ"+String.valueOf(time+iWaitToStop)+"��");
			}
		
			});
	
		seekbarsize.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				//����ע�͵��������ﲻ��Ҫ�������ִ�С�ģ�ʹ��Ĭ�����ִ�С����
				//fontsize = String.valueOf(progress);
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO �Զ����ɵķ������
				
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
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
				Toast.makeText(getActivity().getApplicationContext(), "�ӳ�ʱ��Ϊ"+String.valueOf(iWaitToStop)+"��", Toast.LENGTH_SHORT).show();
				
			}
		
		}
		);


		/*selColorDialog.setOnClickListener(new OnClickListener()   
		{	//ʹ��ColorPickerDialog�࣬��������

			@Override
			public void onClick(View v) {
				dialog = new ColorPickerDialog(MainActivity.this, selcolor.getTextColors().getDefaultColor(),getResources().getString(R.string.app_name), 
						
						
                        new ColorPickerDialog.OnColorChangedListener() {   

                        

                    public void colorChanged(int color) {   

                        selcolor.setTextColor(color);   

                    }   

                });   

                dialog.show();

				// TODO �Զ����ɵķ������
				
			}
			
		});*/
		
		selColorDialog.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				
				MainActivity parentActivity = (MainActivity ) getActivity();//���ø�activity
				
				//View  contentView=  inflater.inflate(R.layout.activity_fragment_new,null);
				LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());  
				layout.setOrientation(LinearLayout.VERTICAL);  
				  
				final TextView colorText = new TextView(getActivity().getApplicationContext());  
				ColorPickerView colorPick = new ColorPickerView(getActivity().getApplicationContext(),Color.parseColor("#FFFFFF"), 1.5,colorText);  
				  
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);  
				lp.gravity = Gravity.CENTER_HORIZONTAL;           
				layout.addView(colorPick, lp);  
				layout.addView(colorText,lp);  
				Log.d("Laomaizi","׼���򿪶Ի���");
				
				AlertDialog mAlertDialog =new AlertDialog.Builder(parentActivity).setTitle("ѡ��һ����ɫ").setView(layout)  
				.setPositiveButton(getString(R.string.dialog_color_OK), new DialogInterface.OnClickListener() 
				{  
				    public void onClick(DialogInterface dialog, int id) {     
				       Log.d("laomaizi","the color choose is "+colorText.getText());  
				   	   strcolor = (String) colorText.getText();
					Log.d("Laomaizi","�Ի��򷵻�"+strcolor);
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
				Log.d("Laomaizi","�Ի�����"+((String) colorText.getText()));
				//selcolor.setTextColor(Color.parseColor((String) colorText.getText()));
			
				
				
			}
			
		});
		
		
		
		startShowActivity.setOnClickListener(new OnClickListener()
		{
				//��ť����¼�
			@Override
			public void onClick(View v) {
				
				int iedit = edittextstring.length();
				if (iedit >0)
				{
					speed = time*30/iedit;
				}
				else 
				{
					Toast.makeText(getActivity().getApplicationContext(), "��������Ҫ�������֣�", Toast.LENGTH_SHORT).show(); 
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
				Log.d("Laomaizi","����ʱ��Ϊ"+String.valueOf(time)+"�ַ�����Ϊ"+String.valueOf(iedit)+"������ʱ"+
				String.valueOf(iWaitToStop)+"Ԥ���ո�"+String.valueOf(iWaittoBlank));
				

				//�˴���ʼ��ʾ�����������������棬���ú���ͼ����ʾ5�룬
				/*MainActivity.this.setContentView(R.layout.activity_blank);
				Toast.makeText(MainActivity.this, "ҳ��ȫ��һ�£�", Toast.LENGTH_SHORT).show(); 
				try {
					Thread.sleep(3000);
					
					Toast.makeText(MainActivity.this, "����˯����", Toast.LENGTH_SHORT).show();
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				*/
				startActivity(intent);
				Log.d("laomaizi", "�����ڶ���activity"+String.valueOf(speed));
				
				//============����Ϊ�����ֱ��浽��ʷ��¼�еĴ���===
				 
				 try {
					SaveToFavlist(data,fontsize,speed,strcolor);
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
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
						Log.d("Laomaizi","Ŀ¼�����ڣ�����֮");
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}*/
				
				String local_file = Environment.getExternalStorageDirectory().getAbsolutePath()+"/fav/";
				File newXmlFile = new File(local_file);
				if(!newXmlFile.exists()){
					newXmlFile.mkdirs();
					Log.d("Laomaizi","������Ŀ¼������"+Environment.getExternalStorageDirectory().getAbsolutePath().toString());
				}

				local_file = newXmlFile.getAbsolutePath()+"/"+"fav.xml";
				newXmlFile = new File(local_file);
				try {
				   if(!newXmlFile.exists()) {
					   newXmlFile.createNewFile();
					   Log.d("Laomaizi","�Ѿ�����"+Environment.getExternalStorageDirectory().getAbsolutePath().toString());
				   } 
				} catch (IOException ex) {
					ex.printStackTrace();
				
					}
				FileOutputStream fileos = null;
				
				try {
					fileos = new FileOutputStream(newXmlFile);
				} catch (FileNotFoundException e) {
					// TODO �Զ����ɵ� catch ��
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
					Log.d("Laomaizi","д���ļ����");
					fileos.close();
					
				} catch (IllegalArgumentException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO �Զ����ɵ� catch ��
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
		Log.d("laomaizi", "��һ��activity�������");
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
