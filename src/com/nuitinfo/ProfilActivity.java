package com.nuitinfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import com.nuitinfo.object.User;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil_activity);

		Button button_submit = (Button) findViewById(R.id.button_submit);
		EditText editText_password = (EditText)findViewById(R.id.editText_password);
		EditText editText_confirmpassword = (EditText)findViewById(R.id.editText_confirmpassword);
		EditText editText_email = (EditText)findViewById(R.id.editText_email);
		TextView textView_birth = (TextView)findViewById(R.id.textView_birth);
		TextView textView_name = (TextView)findViewById(R.id.textView_name);

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		long date_value = 0;
		try {
			date_value = format.parse("1989/05/04").getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		User user1 = new User(0,"monadresse@gmail.com","mdp","FIFRE","Sebastien",new Date(date_value));
		DateFormat date_format = DateFormat.getDateInstance(DateFormat.SHORT);
		String date = date_format.format(user1.getBirthDate());

		textView_name.setText(user1.getFirstname() + " " + user1.getLastname());
		textView_birth.setText(date);
		editText_email.setText(user1.getEmail());

		button_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ProfilActivity.this, "Not yet Implemented", 3000).show();


			}
		});
	}
}

