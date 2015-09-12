package com.lightpainting.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import util.DebugLog;
import android.R.integer;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
		FavItem pItem2 = new FavItem("Second", "#565900", "80", "5", "5", "2");
		favlists.add(pItem2);
		FavItem pItem3 = new FavItem("Third", "#FF5500", "100", "5", "5", "3");
		favlists.add(pItem3);*/

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
                
        //设置要解析的目标和读取的编码  
    /*    xpp.setInput(is, "UTF-8");  
        
        List<FavItem> favlists = new ArrayList<FavItem>();  
        
        FavItem p = null;  
        for (int i = xpp.getEventType(); i != XmlPullParser.END_DOCUMENT; i = xpp.next()) {  
            switch (i) {  
            case XmlPullParser.START_TAG:  
                if (xpp.getName() == "text") {  
                    String str = xpp.getAttributeValue(0);  
                    p = new FavItem();  
                    p.setId(Integer.parseInt(str));  
                } else if (xpp.getName().equals("name")) {  
                    p.setName(xpp.nextText());  
                } else if (xpp.getName().equals("age")) {  
                    p.setAge(Integer.parseInt(xpp.nextText()));  
                }  
                break;  
            case XmlPullParser.END_TAG:  
                //遍历Xml文件中一个对象，直到解析到这个对象的  
                if (xpp.getName().equals("person")) {  
                	favlists.add(p);  
                }  
                break;  
            }  
        }  
        return favlists;  */
         
    }  

	public void onClick(View v) {
	}

}
