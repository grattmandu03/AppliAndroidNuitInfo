package com.nuitinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nuitinfo.object.User;

import android.app.Activity;
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
				Toast.makeText(FriendActivity.this, "Not yet Implemented", 3000).show();
//				setResult(20);
//				finish();
			}
		});
        
        listView_people.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peopleListDisplay));
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
