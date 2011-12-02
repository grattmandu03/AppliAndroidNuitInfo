package com.nuitinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	public static int URL_TIMEOUT = 10000;
	public static String WS_URL = "http://ogc-json-ws.herokuapp.com/jsonrenderer/";
	public static String PREF_FILENAME = "main";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        
        Button friend_button = (Button) findViewById(R.id.friend_button);
        Button event_button = (Button) findViewById(R.id.event_button);
        Button profil_button = (Button) findViewById(R.id.profil_button);
        Button param_button = (Button) findViewById(R.id.param_button);

        
        friend_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(HomeActivity.this, "Not yet implemented", 3000).show();
			}
		});
        
        event_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						ListEventsActivity.class);
				startActivity(intent);
			}
		});
        
        profil_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						ProfilActivity.class);
				startActivity(intent);
			}
		});
        
        param_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(HomeActivity.this, "Not yet Implemented", 3000).show();
			}
		});
        
    }
    

}
