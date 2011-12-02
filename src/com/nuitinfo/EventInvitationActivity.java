package com.nuitinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EventInvitationActivity extends Activity{
	

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_invitation_activity);
        
        Button button_add_contact = (Button) findViewById(R.id.button_add_contact);
        Button button_invite = (Button) findViewById(R.id.button_invite);
        Button button_create_event = (Button) findViewById(R.id.button_create_event);

        
        button_add_contact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(EventInvitationActivity.this, "Add contact : Not yet implemented 1", 3000).show();
			}
		});
        
        button_invite.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(EventInvitationActivity.this, "Invite : Not yet implemented 2", 3000).show();
			}
		});
        
        button_create_event.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(EventInvitationActivity.this, "Create event : Not yet implemented 3", 3000).show();
			}
		});

        
    }
    

}
