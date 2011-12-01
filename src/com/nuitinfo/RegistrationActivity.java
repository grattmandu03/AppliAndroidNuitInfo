package com.nuitinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RegistrationActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        
        Button sign_up_button = (Button) findViewById(R.id.sign_up_button);
        
        System.out.println(sign_up_button);
        
        sign_up_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Toast.makeText(RegistrationActivity.this, "Not yet Implemented", 3000).show();
				Intent intent = new Intent(RegistrationActivity.this,
						FriendActivity.class);
				startActivity(intent);
				
//				setResult(20);
//				finish();
			}
		});
    }
}
