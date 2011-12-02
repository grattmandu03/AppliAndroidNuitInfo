package com.nuitinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nuitinfo.object.User;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FriendActivity extends Activity{
	
	private final ArrayList<User> peopleList = new ArrayList<User>();
	private final ArrayList<String> peopleListDisplay = new ArrayList<String>();
	private Dialog dialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);
        
        Button add_people_button = (Button) findViewById(R.id.add_friend_button);
        ListView listView_people = (ListView) findViewById(R.id.listView_people);
        
        this.createFriendList();
        
        add_people_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				displayAddFriendDialog();
			}
		});
        
        listView_people.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peopleListDisplay));
    }
	
	
	private void displayAddFriendDialog() {
		dialog = new Dialog(this);
		
		dialog.setContentView(R.layout.add_friend_dialog);
		dialog.setTitle("Add someone");
		
		Button btnAdd = (Button) findViewById(R.id.send_request_btn);
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Un email d'invitation a été envoyé", Toast.LENGTH_SHORT).show();
			}
		});
		
		Button btnCancel = (Button) findViewById(R.id.cancel_btn);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
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

}
