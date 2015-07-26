package com.lightpainting.app;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

/**
 * @author �� Favlist �����ڳ�����ҳ����ײ���ť��ת��listfragment
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
		//���ĳһ����Ŀ��������Ϊ��
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FavItem f = adapter.getItem(position);
				Log.d("Laomaizi", f.getText() + "was Clicked");
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
				Log.d("laomaizi", "�����ڶ���activity");

			}
		});

		// ArrayList<FavItem> favlists = null;

		// ������Ҫ��xml�ļ�����ȡ�����favlists��
		/*
		 * XmlPullParser parser =
		 * XmlPullParserFactory.newInstance().newPullParser();
		 * parser.setInput(new StringReader("fav.xml"));
		 * parseItems(favlists,parser);
		 */

		return contentView;

	}

	private void initFavLists() {
		// TODO �Զ����ɵķ������

		FavItem pItem1 = new FavItem("First", "#0000FF", "120", "5", "5", "1");
		favlists.add(pItem1);
		FavItem pItem2 = new FavItem("Second", "#565900", "80", "5", "5", "2");
		favlists.add(pItem2);
		FavItem pItem3 = new FavItem("Third", "#FF5500", "100", "5", "5", "3");
		favlists.add(pItem3);

	}

	/*
	 * static void parseItems(ArrayList<FavItem> favlists,XmlPullParser parser)
	 * throws XmlPullParserException, IOException { final String XML_PHOTO =
	 * "LightPainting"; int eventType = parser.next();
	 * 
	 * while(eventType != XmlPullParser.END_DOCUMENT){ if(eventType ==
	 * XmlPullParser.START_TAG&&XML_PHOTO.equals(parser.getName())) { String id
	 * = parser.getAttributeValue(null,"id"); String text =
	 * parser.getAttributeValue(null,"text"); String fontsize =
	 * parser.getAttributeValue(null,"fontsize"); String color =
	 * parser.getAttributeValue(null,"color"); String continuetime =
	 * parser.getAttributeValue(null,"time"); String delay =
	 * parser.getAttributeValue(null,"delay"); FavItem item = new FavItem();
	 * item.setId(id); item.setText(text); item.setFontsize(fontsize);
	 * item.setColor(color); item.setTime(continuetime); item.setDelay(delay);
	 * //favlists.getFavs().add(item); favlists.add(item);
	 * 
	 * /*FavLab favlab = null; favlab.getFavs().add(item); } eventType=
	 * parser.next(); } }
	 */
	public void onClick(View v) {
	}

}
