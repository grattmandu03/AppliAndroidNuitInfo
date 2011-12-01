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
import android.widget.Button;
import android.widget.Toast;

public class FriendActivity extends Activity{
	
	private final ArrayList<User> deliveryList = new ArrayList<User>();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);
        
        Button add_friend_button = (Button) findViewById(R.id.add_friend_button);
        
        this.createFriendList();
        
        add_friend_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(FriendActivity.this, "Not yet Implemented", 3000).show();
//				setResult(20);
//				finish();
			}
		});
    }
	
	/*
	 * Methode remplissant la liste des bons de livraison en attente C'est ici
	 * que sera parser le resultat de la DataMatrix
	 */
	private void createFriendList() {
		deliveryList.clear();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	    long date_value = 0;
		try {
			date_value = format.parse("1989/05/04").getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		User user1 = new User(0,"monadresse@gmail.com","mdp","FIFRE","Sebastien",new Date(date_value));
		deliveryList.add(user1);
		
	}

}
