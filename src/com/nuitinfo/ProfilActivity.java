package com.nuitinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.nuitinfo.object.User;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends Activity{
	
	protected ProgressDialog progressDialog; // Pour informer l'utilisateur d'un traitement en cours
	protected String progressDialogText = "";

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

		createProgressDialog();
		
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
	
	
	private void loadFromServer() {
		// Lancement de l'appel réseau dans un thread
		new Thread(new Runnable() {

			public void run() {
				/*BufferedReader br = null;
				StringBuilder builder = new StringBuilder();
				
        		try {
        			progressDialogText = getResources().getString(R.string.progress_load_friends);
        			
        			// Affichage boite de dialogue de progression
        			showProgressDialog("");
        			
        			// Appel d'une URL distante en POST
        			HttpParams httpParameters = new BasicHttpParams();
        			HttpConnectionParams.setConnectionTimeout(httpParameters, HomeActivity.URL_TIMEOUT);
        			HttpConnectionParams.setSoTimeout(httpParameters, HomeActivity.URL_TIMEOUT);
        			
        			HttpClient httpClient = new DefaultHttpClient(httpParameters);
        			HttpPost httpPost = new HttpPost(HomeActivity.WS_URL + "getuserbyid/");
        		
        			// On créé la liste des paramètres à transmettre
        			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        			parameters.add(new BasicNameValuePair("action", "listEvents"));
        			parameters.add(new BasicNameValuePair("sessId", globalPref.getString("AUTH_SESSID", "")));
        			
        			// On encode les paramètres en UTF-8
        			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
        			httpPost.setEntity(formEntity);
        			
        			// Execution de la requete (appel serveur)
					HttpResponse httpResponse = httpClient.execute(httpPost);
					
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
						int errorStatus = jsonObject.getInt("error");
						
						if (errorStatus == 0) { // OK
							showProgressDialog(null); // Supprimer la boite de dialogue
							
							resetList(); // Réinitialiser les données de la liste
							
							// Generation de la liste de contacts
							JSONArray ownEvents = jsonObject.getJSONArray("own_events");
							JSONArray invitedEvents = jsonObject.getJSONArray("invited_events");
							
							// Events de l'utilisateur
							if (ownEvents != null) {
								int nbOwn = ownEvents.length();
								if (nbOwn > 0) addListSeparator("YOUR EVENTS");
								
								for (int i=0; i<nbOwn; i++) {
    								JSONObject event = ownEvents.getJSONObject(i);
    								
    								if (event != null) { // Event extrait correctement
    									addListItem(new Event(event.getInt("event_id"),
    														  event.getInt("owner_id"),
    														  "-1",
    														  event.getString("title"),
    														  event.getString("description"),
    														  event.getString("date"),
    														  event.getInt("guests")
    														  ));
    								}
    							}
							}
							
							// Events auxquels l'utilisateur est invité
							if (invitedEvents != null) {
								int nbInvited = invitedEvents.length();
								if (nbInvited > 0) addListSeparator("INVITED TO JOIN");
								
								for (int i=0; i<nbInvited; i++) {
									JSONObject event = invitedEvents.getJSONObject(i);
    								
    								if (event != null) { // Event extrait correctement
    									addListItem(new Event(event.getInt("event_id"),
    														  event.getInt("owner_id"),
    														  event.getString("owner_name"),
    														  event.getString("title"),
    														  event.getString("description"),
    														  event.getString("date"),
    														  event.getInt("guests")
    														  ));
    								}
    							}
							}
							
							notifyRefresh(); // Mettre à jour la liste

						} else {
							showProgressDialog(jsonObject.getString("message"));
						}
					}
					
				} catch (Exception e) {
					showProgressDialog(getResources().getText(R.string.error_server).toString()+e.getMessage());
					
				} finally {
					if (br != null){
						try { br.close(); } // On ferme le flux de lecture de la réponse
						catch (IOException e) {}
					}
				}*/
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

