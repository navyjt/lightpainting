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
	private TextView camerasetuptext;
	private Button startShowActivity;
	private Button selColorDialog;
	private Button cameraSetup;
	private SeekBar seekbarsize;
	private SeekBar seekbarspeed;
	private String fontsize = "200";
	private int time = 15;
	private int speed;
	private String strcolor ="#FFFFFF";
	private ColorPickerDialog dialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ʹӦ�ó����ޱ�����
		setContentView(R.layout.activity_main);
		
		startShowActivity = (Button) findViewById(R.id.start_button);
		edittextstring = (EditText) findViewById(R.id.edit_text);
		seekbarsize = (SeekBar) findViewById(R.id.seekBarFont);
		seekbarspeed = (SeekBar) findViewById(R.id.seekBarspeed);
		selColorDialog = (Button) findViewById(R.id.selcolor_button);
		selcolor = (TextView)findViewById(R.id.selcolor);
		cameraSetup = (Button) findViewById(R.id.camera_setup);
		camerasetuptext = (TextView)findViewById(R.id.camera_setuptext);
		
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
				Toast.makeText(MainActivity.this, "�ƶ��ٶ�����Ϊ"+String.valueOf(time)+"��", Toast.LENGTH_SHORT).show();
			
			}
		
		});
		
		cameraSetup.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				
				int iedit = edittextstring.length();
				
				speed = time*23/iedit;
				
				// TODO �Զ����ɵķ������
				camerasetuptext.setText("����������£�ISO100-200���������ȣ������ٶ�Ϊ"+String.valueOf(time)+"��");
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
				
				Log.d("Laomaizi","�����ť");
				LinearLayout layout = new LinearLayout(MainActivity.this);  
				layout.setOrientation(LinearLayout.VERTICAL);  
				  
				final TextView colorText = new TextView(MainActivity.this);  
				ColorPickerView colorPick = new ColorPickerView(MainActivity.this,Color.parseColor("#FFFFFF"), 1.5,colorText);  
				  
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);  
				lp.gravity = Gravity.CENTER_HORIZONTAL;           
				layout.addView(colorPick, lp);  
				layout.addView(colorText,lp);  
				Log.d("Laomaizi","׼���򿪶Ի���");
				
				AlertDialog mAlertDialog =new AlertDialog.Builder(MainActivity.this).setTitle("ѡ��һ����ɫ").setView(layout)  
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
					speed = time*23/iedit;
				}
				else 
				{
					Toast.makeText(MainActivity.this, "��������Ҫ�������֣�", Toast.LENGTH_SHORT).show(); 
					return;
				}
				
				Intent intent = new Intent(MainActivity.this,startShowActivity.class);
				String data = edittextstring.getText().toString();
				intent.putExtra("showtext",data);
				intent.putExtra("fontsize", fontsize);
				intent.putExtra("speed", speed);
				intent.putExtra("color", strcolor);
				startActivity(intent);
				Log.d("laomaizi", "�����ڶ���activity"+String.valueOf(speed));
				//System.out.println("�����ڶ���activity");
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
