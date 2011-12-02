package com.nuitinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuitinfo.object.Event;
import com.nuitinfo.object.User;

public class ListEventsActivity extends Activity{

	private final ArrayList<Event> listEvents = new ArrayList<Event>();
	private ListEventsAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list_activity);

		ListView listView_events = (ListView) findViewById(R.id.listView_events);
		TextView textview_no_event = (TextView) findViewById(R.id.event_list_no_event);
		Button create_event_button = (Button) findViewById(R.id.create_event_button);

		this.createListEvent();
		adapter = new ListEventsAdapter(this, listEvents);
		listView_events.setAdapter(adapter);

		if (listEvents.isEmpty()) {
			listView_events.setVisibility(View.GONE);
			textview_no_event.setVisibility(View.VISIBLE);
			adapter.notifyDataSetChanged();
		} else {
			listView_events.setVisibility(View.VISIBLE);
			textview_no_event.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();
		}

		create_event_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListEventsActivity.this,
						CreateEventActivity.class);
				startActivity(intent);
			}
		});

		listView_events.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {

				if (adapter != null) {
					int separator_number = 0;
					for (int i = 0; i < position; i++) {
						if (adapter.getItemViewType(i) == 1) {
							separator_number++;
						}
					}
					//long delivery_id = adapter.getDeliveryListSort()
					//		.get(position - separator_number).getDelivery_id();

					//alert = initAlertDialog(delivery_id).create();
					//alert.show();
				}
			}
		});

	}

	private void createListEvent(){
		listEvents.clear();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		long date_value = 0;
		try {
			date_value = format.parse("1989/05/04").getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Event event1 = new Event(0,0,"Cyril Chandelier", "Description",new Date(date_value),true);
		listEvents.add(event1);


	}
}
