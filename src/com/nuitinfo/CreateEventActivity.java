package com.nuitinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CreateEventActivity extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);
        
        Button button_next = (Button) findViewById(R.id.button_next);

        
        button_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(CreateEventActivity.this, "Not yet implemented", 3000).show();
			}
		});
                
    }
    

}

