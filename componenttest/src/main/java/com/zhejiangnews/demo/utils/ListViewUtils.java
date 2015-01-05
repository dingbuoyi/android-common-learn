package com.zhejiangnews.demo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zhejiangnews.demo.R;

public final class ListViewUtils {

	public static void initWithSimpleAdapter(Context context, ListView listView) {
		SimpleAdapter adapter = new SimpleAdapter(context, getTestDate(), R.layout.ultra_pull_to_refresh_list_item, new String[] {
				"PIC", "TITLE", "CONTENT" },
				new int[] { R.id.listitem_pic, R.id.listitem_title, R.id.listitem_content });
		listView.setAdapter(adapter);
	}

	private ListViewUtils() {

	}

	private static List<Map<String, Object>> getTestDate() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PIC", R.drawable.ic_launcher);
			map.put("TITLE", "Test Title");
			map.put("CONTENT", "Test Content");
			dataList.add(map);
		}
		return dataList;
	}
}
