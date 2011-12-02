package com.nuitinfo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.TreeSet;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.nuitinfo.object.Product;

public class ListGiftsAdapter extends BaseAdapter{

	private static final int TYPE_ITEM = 0;
	private static final int TYPE_SEPARATOR = 1;
	
	private final ArrayList<Product> giftsList = new ArrayList<Product>();
	private ArrayList<Product> giftsListSort = new ArrayList<Product>();
	private final LayoutInflater inflater;
	private EventActivity activity;
	
	@SuppressWarnings("rawtypes")
	private final TreeSet separatorsSet = new TreeSet();

	public ListGiftsAdapter(EventActivity activity,
			ArrayList<Product> giftsList) {
		this.activity = activity;
		this.inflater = LayoutInflater.from(activity);
		this.sortListGifts(giftsList);
	}
	
	public void addItem(Product item) {
		giftsList.add(item);
	}
	
	@SuppressWarnings("unchecked")
	public void addSeparator(Product item) {
		giftsList.add(item);
		separatorsSet.add(giftsList.size() - 1);
	}
	
	@Override
	public int getItemViewType(int position) {
		return separatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
	}
	
	@Override
	public int getCount() {
		return giftsList.size();
	}

	@Override
	public Object getItem(int position) {
		return giftsList.get(position);
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
		super.notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		TextView text_view_title;
		TextView text_view_price;
		TextView text_view_compteur;

		int type = getItemViewType(position);
		switch (type) {
		case TYPE_ITEM:
			convertView = inflater.inflate(R.layout.gifts_list_item, null);
			text_view_title = (TextView) convertView
					.findViewById(R.id.gift_list_item_title);
			text_view_price = (TextView) convertView
					.findViewById(R.id.gift_list_item_price);
			text_view_compteur = (TextView) convertView.findViewById(R.id.gift_list_item_vote);
			text_view_title.setText(giftsList.get(position).getName());
			text_view_price.setText(giftsList.get(position).getPrice()+"€");
			text_view_compteur.setText(giftsList.get(position).getCompteur()+"");
			if(giftsList.get(position).getCompteur() < 0){
				text_view_compteur.setTextColor(Color.RED);
			} else if(giftsList.get(position).getCompteur() > 0){
				text_view_compteur.setTextColor(Color.GREEN);
			}
			break;
		case TYPE_SEPARATOR:
			break;
		}

		return convertView;
	}
	
	public ArrayList<Product> getSortListGifts(){
		return this.giftsListSort;
	}
	
	public void updateView(ArrayList<Product> productsList) {
		this.separatorsSet.clear();
		this.giftsList.clear();
		this.sortListGifts(productsList);
		this.notifyDataSetChanged();
	}
	
	private void sortListGifts(ArrayList<Product> giftsList){
		giftsListSort.clear();
		
		for(int i =0; i < giftsList.size(); i++){
			boolean tri = false;
			for(int j = 0; j < giftsListSort.size(); j++){
				if(giftsList.get(i).getCompteur() >= giftsListSort.get(j).getCompteur()){
					giftsListSort.add(j, giftsList.get(i));
					tri = true;
					break;
				}
			}
			if(!tri){
				giftsListSort.add(giftsList.get(i));
			}
		}
		for(int i = 0; i < giftsListSort.size(); i++){
			this.addItem(giftsListSort.get(i));
		}
	}

}
