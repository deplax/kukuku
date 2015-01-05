package com.plannet.listener;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DrawerListItemOnClickListener implements ListView.OnItemClickListener {
	private static final int My_Plan = 0;
	private static final int Following = 1;
	private static final int Find = 2;

	private DrawerLayout drawerLayout;

	public DrawerListItemOnClickListener(DrawerLayout drawerLayout) {
		this.drawerLayout = drawerLayout;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

		switch (position) {
		case My_Plan:
			break;
		case Following:
			break;
		case Find:
			break;
		}
		drawerLayout.closeDrawers();
	}
}