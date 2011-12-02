package com.nuitinfo;

import android.app.TabActivity;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class EventActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_tabs);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("Gifts")
				.setIndicator(getResources().getString(R.string.gifts))
				.setContent(R.id.tab_gifts));
		tabHost.addTab(tabHost.newTabSpec("Contacts")
				.setIndicator(getResources().getString(R.string.contacts))
				.setContent(R.id.tab_contacts));
		tabHost.addTab(tabHost.newTabSpec("Options")
				.setIndicator(getResources().getString(R.string.my_options))
				.setContent(R.id.tab_options));

		tabHost.setCurrentTab(0);

		tabHost.getTabWidget().getChildTabViewAt(0).getLayoutParams().height = 60;
		tabHost.getTabWidget().getChildTabViewAt(1).getLayoutParams().height = 60;
		tabHost.getTabWidget()
				.getChildTabViewAt(0)
				.getBackground()
				.setColorFilter(new LightingColorFilter(0x1a50d800, 0x00000000));
		tabHost.getTabWidget()
				.getChildTabViewAt(1)
				.getBackground()
				.setColorFilter(new LightingColorFilter(0x1a50d800, 0x00000000));

	}

}
