package com.lightpainting.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.DESKeySpec;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import util.DebugLog;
import android.R.integer;
import android.R.raw;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

/**
 * @author 涛 Favlist 用于在程序首页点击底部按钮跳转的listfragment
 *
 */
public class FavList extends Fragment {

	private List<FavItem> favlists = new ArrayList<FavItem>();
	private FavItemAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View contentView = inflater.inflate(R.layout.favlist, container, false);

		initFavLists();

		adapter = new FavItemAdapter(getActivity(), R.layout.favitem, favlists);
		ListView plistview = (ListView) contentView.findViewById(R.id.listview);
		plistview.setAdapter(adapter);
		registerForContextMenu(plistview);
		plistview.setOnItemClickListener(new OnItemClickListener() {
		//点击某一项条目发生的行为。
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FavItem f = adapter.getItem(position);
				DebugLog.log(f.getText() + "was Clicked");
				int speed =50;
				
				int iedit = f.getText().length();
				if (iedit > 0) {
					speed = Integer.parseInt(f.getTime()) * 30 / iedit;
				} 
				
				Intent intent = new Intent(getActivity().getApplicationContext(), startShowActivity.class);
				String text = f.getText();
				intent.putExtra("showtext", text);
				String color = f.getColor();
				intent.putExtra("color", color);
				String fontsize = f.getFontsize();
				intent.putExtra("fontsize", fontsize);
				intent.putExtra("speed", speed);

				int iWaittoBlank = iedit *  Integer.parseInt(f.getDelay())/ Integer.parseInt(f.getTime());
				intent.putExtra("stop", iWaittoBlank);
				
				startActivity(intent);
				Log.d("laomaizi", "启动第二个activity");

			}
		});

		return contentView;

	}

	private void initFavLists() {
		//开始解析XML文档
		try {
			XmlPullParserTest();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
/*		FavItem pItem1 = new FavItem("First", "#0000FF", "120", "5", "5", "1");
		favlists.add(pItem1);
*/

	}
	
	public List<FavItem> XmlPullParserTest() throws Exception {  
        //创建一个InputStream通过反射得到当前累的对象然后getClassLoader().getResourceAsStream();  
        
        String inputFileName = getActivity().getApplicationContext().getFilesDir().getAbsolutePath()+ "/fav/"+ "fav.xml";
        DebugLog.log(inputFileName);
        File inputfile = new File(inputFileName);
        InputStream inStream = new FileInputStream(inputfile);
        
        //Xml.newPullParser()得到一个XmlPullParser  
        XmlPullParser parser = Xml.newPullParser();  
        
        try {  
            parser.setInput(inStream, "UTF-8");  
            int eventType = parser.getEventType();  

            FavItem currentFav = null;  
           // List<FavItem> favlists = null;  

            while (eventType != XmlPullParser.END_DOCUMENT) {  
                        switch (eventType) {  
                        case XmlPullParser.START_DOCUMENT://文档开始事件,可以进行数据初始化处理  
                        	favlists = new ArrayList<FavItem>();  
                                    break;  

                         case XmlPullParser.START_TAG://开始元素事件  
                                    String name = parser.getName();  
                                    if (name.equalsIgnoreCase("lightpainting")) {  
                                    	currentFav = new FavItem();  
                                    	//currentFav.setId(new Integer(parser.getAttributeValue(null, "id")));  
                                    } else if (currentFav != null) {  
                                                if (name.equalsIgnoreCase("text")) {  
                                                	currentFav.setText(parser.nextText());// 如果后面是Text元素,即返回它的值  
                                                } else if (name.equalsIgnoreCase("fontsize")) {  
                                                	currentFav.setFontsize(parser.nextText());  
                                                }  
                                                else if (name.equalsIgnoreCase("time")) {  
                                                	currentFav.setTime(parser.nextText());  
                                                }  
                                                else if (name.equalsIgnoreCase("delay")) {  
                                                	currentFav.setDelay(parser.nextText());  
                                                }  
                                                else if (name.equalsIgnoreCase("color")) {  
                                                	currentFav.setColor(parser.nextText());  
                                                }  
                                    }  
                                    break;  

                        case XmlPullParser.END_TAG://结束元素事件  
                                    if (parser.getName().equalsIgnoreCase("lightpainting") && currentFav != null) { 
                                    	currentFav = new FavItem(currentFav.getText(),currentFav.getColor(),currentFav.getFontsize(),
                                    			currentFav.getTime(),currentFav.getDelay(),currentFav.getId())		;
                                    	favlists.add(currentFav);  
                                    	currentFav = null;  
                                    }  

                                    break;  
                        }  

                         eventType = parser.next();  
            }  


				
				} catch (Exception e) { 
					DebugLog.log(e.toString());
				            e.printStackTrace();  
				           // return null;
				}  

		inStream.close();  
		return favlists; 
                
            
    }  

	public void onClick(View v) {
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		//使用 adaptercontextmenuinfo来获取菜单信息
		
		String local_file = getActivity().getApplicationContext()
				.getFilesDir().getAbsolutePath()
				+ "/fav/"+ "fav.xml";
		File newXmlFile = new File(local_file);
		
		XmlSerializer serializer = Xml.newSerializer();
		FileOutputStream fileos = null;
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		int position = info.position;
		FavItem favItem = favlists.get(position);

		
		switch (item.getItemId()) {
		case R.id.delete_item:
			favlists.remove(position);
			adapter.notifyDataSetChanged();
				
			//这里需要将favlists全部封装进去
			
			try {
				fileos = new FileOutputStream(newXmlFile);
				serializer.setOutput(fileos, "UTF-8");
				serializer.startDocument(null, Boolean.valueOf(true));
				
				for(FavItem f:favlists){
					serializer.startTag(null, "lightpainting");
					serializer.attribute(null, "xmlns",
							"http://www.laomaizi.com");
					serializer.startTag(null, "text");
					serializer.text(f.getText());
					serializer.endTag(null, "text");
					serializer.startTag(null, "fontsize");
					serializer.text(f.getFontsize());
					serializer.endTag(null, "fontsize");
					serializer.startTag(null, "time");
					serializer.text(f.getTime());
					serializer.endTag(null, "time");
					serializer.startTag(null, "delay");
					serializer.text(f.getDelay());
					serializer.endTag(null, "delay");
					serializer.startTag(null, "color");
					serializer.text(f.getColor());
					serializer.endTag(null, "color");
					serializer.endTag(null, "lightpainting");
			}
				
				
				serializer.endDocument();
				serializer.flush();
				DebugLog.log("删除列表内容，写入空文件完成");
				fileos.close();
				
			} catch (FileNotFoundException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			
			
			break;

		case R.id.delete_item_all:
		
			try {
				fileos = new FileOutputStream(newXmlFile);
				serializer.setOutput(fileos, "UTF-8");
				serializer.startDocument(null, Boolean.valueOf(true));
				serializer.endDocument();
				serializer.flush();
				DebugLog.log("删除列表内容，写入空文件完成");
				fileos.close();
					
				favlists.removeAll(favlists);
				adapter.notifyDataSetChanged();
				
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	
			
			
			break;
		}
		
		
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.fav_list_item_context, menu);
		
		//super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	

}
