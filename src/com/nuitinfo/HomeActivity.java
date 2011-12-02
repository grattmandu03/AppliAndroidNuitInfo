package com.nuitinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {
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
				Toast.makeText(HomeActivity.this, "Not yet Implemented", 3000).show();
			}
		});
        
        profil_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(HomeActivity.this, "Not yet Implemented", 3000).show();
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
