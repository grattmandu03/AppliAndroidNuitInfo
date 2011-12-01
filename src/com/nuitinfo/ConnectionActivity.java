package com.nuitinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ConnectionActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_activity);
        
        Button connection_button = (Button) findViewById(R.id.connection_button);
        Button registration_button = (Button) findViewById(R.id.registration_button);
        
        connection_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(ConnectionActivity.this, "Not yet implemented", 3000).show();
				Intent intent = new Intent(ConnectionActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
        
        registration_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConnectionActivity.this,
						RegistrationActivity.class);
				startActivityForResult(intent, 0);
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == 0 && resultCode == 20){
    		finish();
    	}
    }
}