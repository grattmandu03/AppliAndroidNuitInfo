package com.nuitinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends Activity{
	
	protected ProgressDialog progressDialog; // Pour informer l'utilisateur d'un traitement en cours
	protected String progressDialogText = "";
	private static SharedPreferences globalPref;
	
	private EditText editText_password;
	private EditText editText_confirmpassword;
	private EditText editText_email;
	private TextView textView_birth;
	private TextView textView_name;
	
	private SimpleDateFormat dateFormatOut;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil_activity);

		Button button_submit = (Button) findViewById(R.id.button_submit);
		editText_password = (EditText)findViewById(R.id.editText_password);
		editText_confirmpassword = (EditText)findViewById(R.id.editText_confirmpassword);
		editText_email = (EditText)findViewById(R.id.editText_email);
		textView_birth = (TextView)findViewById(R.id.textView_birth);
		textView_name = (TextView)findViewById(R.id.textView_name);

		createProgressDialog();
		
		globalPref = getSharedPreferences(HomeActivity.PREF_FILENAME, MODE_PRIVATE);
		
    	dateFormatOut = new SimpleDateFormat("dd/MM/yy 'at' HH:mm");
		
		loadFromServer();

		button_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ProfilActivity.this, "Not yet Implemented", 3000).show();
			}
		});
	}
	
	
	public void setValues(final String fullName, final String birthdate, final String email) {
		runOnUiThread(new Runnable() {
            public void run() {	
	            textView_name.setText(fullName);
	            editText_email.setText(email);
            }
		});
	}
	
	private void loadFromServer() {
		// Lancement de l'appel réseau dans un thread
		new Thread(new Runnable() {

			public void run() {
				BufferedReader br = null;
				StringBuilder builder = new StringBuilder();
				
        		try {
        			progressDialogText = getResources().getString(R.string.progress_load_friends);
        			
        			// Affichage boite de dialogue de progression
        			showProgressDialog("");
        			
        			// Appel d'une URL distante en GET
					HttpParams httpParameters = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParameters, HomeActivity.URL_TIMEOUT);
					HttpConnectionParams.setSoTimeout(httpParameters, HomeActivity.URL_TIMEOUT);
					
					HttpClient httpClient = new DefaultHttpClient(httpParameters);
					HttpGet httpGet = new HttpGet(HomeActivity.WS_URL + "getuserbyid/" + globalPref.getString("user_id", "-1"));
					
					
					// Execution de la requete (appel serveur)
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					HttpEntity res = httpResponse.getEntity();
					
					// Traitement de la réponse du serveur
					if (res != null) {
						br = new BufferedReader(new InputStreamReader(res.getContent()));
						String serverText = br.readLine();
						
						while (serverText != null) {
							builder.append(serverText);
							serverText = br.readLine();
						}
						
						// On parse le résultat JSON
						JSONObject jsonObject = new JSONObject(builder.toString());
						
						if (!jsonObject.has("error")) { // OK
							showProgressDialog(null); // Supprimer la boite de dialogue
							
							
							Date d = new Date(Long.parseLong(jsonObject.getString("birthdate")));
							
							setValues(jsonObject.getString("firstname")+ " " + jsonObject.getString("lastname"),
									  d.getMonth()+"/"+d.getDay()+"/"+d.getYear(),
									  jsonObject.getString("email"));
							
						} else {
							showProgressDialog(jsonObject.getString("error"));
						}
					}
					
				} catch (Exception e) {
					showProgressDialog(getResources().getString(R.string.error_server).toString()+e.getMessage());
					e.printStackTrace();
					
				} finally {
					if (br != null){
						try { br.close(); } // On ferme le flux de lecture de la réponse
						catch (IOException e) {}
					}
				}
			}
		}).start();
	}
	
	
	
	/**
     * Créer la boite de dialogue de progression de l'opération réseau, SANS l'afficher
     */
    private void createProgressDialog() {
    	progressDialog = new ProgressDialog(this);
    	progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	progressDialog.setMessage(progressDialogText);
    	progressDialog.setCancelable(false);
    }
    
    
    /**
     * Afficher / cacher la boite de dialogue de progression
     * @param message
     * 		- chaine vide pour afficher la boite de dialogue,
     * 		- String pour faire disparaitre la boite de dialogue et afficher un message dans un Toast,
     * 		- null pour désactiver
     */
    protected void showProgressDialog(final String message) {
    	runOnUiThread(new Runnable() {
            public void run() {
            	if (message == null) { // Désactivation
            		progressDialog.dismiss();
            		
            	} else if ("".equals(message)) { // Affichage
            		progressDialog.setMessage(progressDialogText);
            		progressDialog.show();
            		
            	} else { // Affichage erreur
            		progressDialog.dismiss();
            		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            	}
            }
        });
    }
    
}

