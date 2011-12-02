package com.nuitinfo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nuitinfo.object.Event;

public class ListEventsAdapter extends BaseAdapter{

	private static final int TYPE_ITEM = 0;
	private static final int TYPE_SEPARATOR = 1;
	
	private final ArrayList<Event> eventsList = new ArrayList<Event>();
	private ArrayList<Event> eventsListSort;
	private final LayoutInflater inflater;
	private ListEventsActivity activity;
	private int separator_number = 0;
	
	@SuppressWarnings("rawtypes")
	private final TreeSet separatorsSet = new TreeSet();

	public ListEventsAdapter(ListEventsActivity activity,
			ArrayList<Event> eventsList) {
		this.activity = activity;
		this.inflater = LayoutInflater.from(activity);
		this.sortListEvents(eventsList);
	}
	
	public void addItem(Event item) {
		eventsList.add(item);
	}
	
	@SuppressWarnings("unchecked")
	public void addSeparator(Event item) {
		eventsList.add(item);
		separatorsSet.add(eventsList.size() - 1);
	}
	
	@Override
	public int getItemViewType(int position) {
		return separatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
	}
	
	@Override
	public int getCount() {
		return eventsList.size();
	}

	@Override
	public Object getItem(int position) {
		return eventsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public boolean isEnabled(int position) {
		int type = getItemViewType(position);
		return (type == TYPE_ITEM);
	}

	@Override
	public void notifyDataSetChanged() {
		separator_number = 0;
		super.notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		TextView text_view_title;
		TextView text_view_date;
		TextView text_view_nbUsers;
		TextView text_view_events;

		int type = getItemViewType(position);
		switch (type) {
		case TYPE_ITEM:
			convertView = inflater.inflate(R.layout.event_list_item, null);
			text_view_title = (TextView) convertView
					.findViewById(R.id.event_list_item_title);
			text_view_nbUsers = (TextView) convertView
					.findViewById(R.id.event_list_item_nb_user);
			text_view_date = (TextView) convertView.findViewById(R.id.event_list_item_date);
			text_view_title.setText(eventsList.get(position).getVictimName());

			// Affichage du pictogramme permettant de savoir si la livraison
			// s'effectue le matin ou l'apres-midi
			DateFormat hourFormat = DateFormat
					.getTimeInstance(DateFormat.SHORT);
			String hourString = hourFormat.format(
							eventsList.get(position).getDueDate());
			text_view_date.setText(hourString);
			
			break;
		case TYPE_SEPARATOR:
			separator_number++;
			convertView = inflater.inflate(R.layout.event_list_separator,
					null);
			text_view_events = (TextView) convertView
					.findViewById(R.id.event_list_separator);
			if(separator_number == 1){
				text_view_events
				.setText(R.string.my_events);
			} else {
				text_view_events
				.setText(R.string.waiting_events);
			}
			break;
		}

		return convertView;
	}
	
	private void sortListEvents(ArrayList<Event> eventsList){
		// TODO Ajouter un autre séparateur
		this.addSeparator(eventsList.get(0));
		for(int i = 0; i < eventsList.size(); i++){
			this.addItem(eventsList.get(i));
		}
	}

}
