package com.nuitinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.nuitinfo.object.Product;
import com.nuitinfo.object.User;

public class EventActivity extends TabActivity {
	
	private ListView listView_gifts;
	private ListView listView_contacts;
	private final ArrayList<User> peopleList = new ArrayList<User>();
	private final ArrayList<String> peopleListDisplay = new ArrayList<String>();
	private ArrayList<Product> listProducts = new ArrayList<Product>();
	private ListGiftsAdapter adapter;

	private AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_tabs);
		
		listView_gifts = (ListView) findViewById(R.id.listView_gifts);
		listView_contacts = (ListView) findViewById(R.id.listView_contacts);
		
		this.createListGift();
		this.createFriendList();
		adapter = new ListGiftsAdapter(this, listProducts);
		listView_gifts.setAdapter(adapter);
		
		listView_contacts.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peopleListDisplay));

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
		
		listView_gifts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				
				if (adapter != null) {

					alert = initAlertDialog(position).create();
					alert.show();
				}
				
			}
		});

	}
	
	/*
	 * Methode remplissant la liste des bons de livraison en attente C'est ici
	 * que sera parser le resultat de la DataMatrix
	 */
	private void createFriendList() {
		peopleList.clear();
		peopleListDisplay.clear();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	    long date_value = 0;
		try {
			date_value = format.parse("1989/05/04").getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		User user1 = new User(0,"monadresse@gmail.com","mdp","FIFRE","Sebastien",new Date(date_value));
		peopleList.add(user1);
		peopleListDisplay.add(user1.getLastname() + " " + user1.getFirstname());
	}
	
	private void createListGift(){
		listProducts.clear();
		
		Product product = new Product(0, "ean", "bonnet", "blanc et noir", 7.00, 0);
		Product product2 = new Product(0, "ean", "short", "blanc et noir", 15.00, 3);
		Product product3 = new Product(0, "ean", "pantalon", "blanc et noir", 8.00, -4);
		
		listProducts.add(product);
		listProducts.add(product2);
		listProducts.add(product3);
	}
	
	private AlertDialog.Builder initAlertDialog(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final CharSequence[] items = { getString(R.string.vote_plus),
				getString(R.string.vote_moins) };
		builder.setTitle(getString(R.string.vote));
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals(getString(R.string.vote_plus))) {
					adapter.getSortListGifts().get(position).setCompteur(adapter.getSortListGifts().get(position).getCompteur() + 1);
				} else if (items[item]
						.equals(getString(R.string.vote_moins))) {
					adapter.getSortListGifts().get(position).setCompteur(adapter.getSortListGifts().get(position).getCompteur() - 1);
				}
				listProducts = (ArrayList<Product>) adapter.getSortListGifts().clone();
				adapter.updateView(listProducts);
			}
		});
		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		return builder;
	}

}
