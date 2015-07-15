package com.lightpainting.app;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FavList extends ListFragment {
	
	public  void oncreate(Bundle savedInstanceState) throws XmlPullParserException, IOException{
		
		super.onCreate(savedInstanceState);
		ArrayList<FavItem> favlists = null;
		
				
		//这里需要打开xml文件，读取后存入favlists中
		XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(new StringReader("fav.xml"));
		parseItems(favlists,parser);
		
		ArrayAdapter<FavItem> adapter = new ArrayAdapter<FavItem>(getActivity(),R.layout.favlist,favlists);
	}
		
		static void parseItems(ArrayList<FavItem> favlists,XmlPullParser parser) throws XmlPullParserException, IOException
		{
			final String XML_PHOTO = "LightPainting";
			int eventType = parser.next();
			
			while(eventType != XmlPullParser.END_DOCUMENT){
				if(eventType == XmlPullParser.START_TAG&&XML_PHOTO.equals(parser.getName()))
				{
					String id = parser.getAttributeValue(null,"id");
					String text = parser.getAttributeValue(null,"text");
					String fontsize = parser.getAttributeValue(null,"fontsize");
					String color = parser.getAttributeValue(null,"color");
					String continuetime = parser.getAttributeValue(null,"time");
					String delay = parser.getAttributeValue(null,"delay");
					FavItem item = new FavItem();
					item.setId(id);
					item.setText(text);
					item.setFontsize(fontsize);
					item.setColor(color);
					item.setTime(continuetime);
					item.setDelay(delay);
					//favlists.getFavs().add(item);
					favlists.add(item);
					
					/*FavLab favlab = null;
					favlab.getFavs().add(item);*/
				}
				eventType= parser.next();
			}
		}
		
		public void onClick(View v){
		}
		
		public void onListItemClick(ListView l,View v,int position,long id){
			
			FavItem f = (FavItem)(getListAdapter()).getItem(position);
			Log.d("Laomaizi",f.getText()+"was Clicked");
			
		}


}
