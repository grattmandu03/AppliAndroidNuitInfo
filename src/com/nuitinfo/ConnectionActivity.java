package com.nuitinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ConnectionActivity extends Activity {
	
	protected ProgressDialog progressDialog; // Pour informer l'utilisateur d'un traitement en cours
	protected String progressDialogText = "";
	private static SharedPreferences globalPref;
	
	private EditText username;
	private EditText password;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_activity);
        
        Button connection_button = (Button) findViewById(R.id.connection_button);
        Button registration_button = (Button) findViewById(R.id.registration_button);
        username = (EditText) findViewById(R.id.connexion_email);
        password = (EditText) findViewById(R.id.connexion_pwd);
        
        createProgressDialog();
        progressDialogText = getString(R.string.server_connexion);
        
        globalPref = getSharedPreferences(HomeActivity.PREF_FILENAME, MODE_PRIVATE);
        
        
        // Listener sur le bouton de connexion
        connection_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!"".equals(username.getText().toString()) && !"".equals(password.getText().toString())) connectToServer();
			}
		});
        
     // Listener sur le bouton de création de compte
        registration_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConnectionActivity.this, RegistrationActivity.class);
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
    
    
    /**
     * Authentification auprès du serveur
     * Récupération de l'ID de l'utilisateur pour les prochaines requetes
     */
    private void connectToServer() {
    	// Lancement de l'appel réseau dans un thread
		new Thread(new Runnable() {

			public void run() {
		    	BufferedReader br = null;
				StringBuilder builder = new StringBuilder();
				
				try {
					// Affichage boite de dialogue de progression
					showProgressDialog("");
					
					// Appel d'une URL distante en POST
					HttpParams httpParameters = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParameters, HomeActivity.URL_TIMEOUT);
					HttpConnectionParams.setSoTimeout(httpParameters, HomeActivity.URL_TIMEOUT);
					
					HttpClient httpClient = new DefaultHttpClient(httpParameters);
					HttpGet httpGet = new HttpGet(HomeActivity.WS_URL + "getuserbyemailpassword/" + username.getText().toString() + "/" + password.getText().toString());
					
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
							
							if (jsonObject.getInt("id")!= -1) {
								// Sauvegarde de l'ID de l'utilisateur
						        globalPref.edit().putString("user_id", String.valueOf(jsonObject.getInt("id"))).commit();
						        
						        // Lancement du dashboard
								startActivity(new Intent(getApplicationContext(), HomeActivity.class));
							
								((Activity)ConnectionActivity.this).finish();
							
							} else {
								showProgressDialog(getResources().getString(R.string.connexion_nok).toString());
							}
		
						} else {
							showProgressDialog(jsonObject.getString("error"));
						}
					}
					
				} catch (Exception e) {
					showProgressDialog(getResources().getString(R.string.error_server).toString());
					
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