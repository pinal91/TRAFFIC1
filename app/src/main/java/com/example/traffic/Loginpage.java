package com.example.traffic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loginpage extends ActionBarActivity {
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	TextView t1 ;
	ActionBar actionbar;
	EditText e1,e2;
	JSONParser js;
	String strresponce;
	ProgressDialog	pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpage);
		
		 e1 = (EditText)findViewById(R.id.etxtuname);
		 e2 = (EditText)findViewById(R.id.etxtpass);
		
		Button b1 = (Button)findViewById(R.id.btnlogin);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StrictMode.setThreadPolicy(policy); 
	         	new getdata().execute();
			}
		});
		
		actionbar = getSupportActionBar();
        actionbar.setIcon(R.drawable.ic_launcher);
        actionbar.setTitle(Html.fromHtml("<font color='#ffffff'>Login</font>"));
		
		t1 = (TextView)findViewById(R.id.txtregister);
		
		t1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(getApplicationContext(), Registrationpage.class);
				startActivity(i1);
			}
		});
		
		
		
	}
	class getdata extends AsyncTask<Void, Void, Void>{
		PreferenceHelper pf;
		String sname;
		String spass;
		String pm_mail;
		String pm_id;
		String cat ;
		JSONObject jobj;
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd = new ProgressDialog(Loginpage.this);
			pd.setMessage("Login Processing...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			pd.show();
		} 

		@SuppressWarnings("static-access")
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			sname = e1.getText().toString();
			spass = e2.getText().toString();
			
			BasicNameValuePair nm = new BasicNameValuePair("pm_mail", sname);
			BasicNameValuePair nm1 = new BasicNameValuePair("pm_pass", spass);
			
			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(nm);
			param.add(nm1);
			
			js = new JSONParser();
			ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
			
			
			 strresponce = js.getJSONFromUrl("http://192.168.1.222/jsonfile/login.php", param);
			Log.d("database", "Data Comes"+ strresponce);
			JSONObject jobjResponse;  
			try {
				jobjResponse = new JSONObject(strresponce);
				JSONArray jArrayProdList = new JSONArray();
				jArrayProdList = jobjResponse.getJSONArray("user");
				for (int i = 0; i < jArrayProdList.length(); i++) {
					HashMap<String,String> map = new HashMap<String, String>();
					jobj = jArrayProdList.getJSONObject(i);
					pm_mail = jobj.getString("pm_mail");
					pm_id = jobj.getString("pm_id");
					data.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			

			 if (pd.isShowing()) {
	                pd.dismiss();
	              
	                if (strresponce.contains("[]"))
	                {
	                	Toast.makeText(getApplicationContext(), "Username Password Doesn't Match", Toast.LENGTH_LONG).show();
	                }else {
	                    PreferenceHelper.SavePreferences(Loginpage.this,"pm_mail",pm_mail);
	                   PreferenceHelper.SavePreferences(Loginpage.this,"pm_id",pm_id);
	                   // PreferenceHelper.SavePreferences(Loginpage.this,"rm_emailid",rm_emailid);
	                   // PreferenceHelper.SavePreferences(Loginpage.this,"rm_id",rm_id);
	                    Intent i = new Intent(Loginpage.this, HomePage.class);
	                    startActivity(i);
	                }
		
			 }
		}
	
	

	}
	
}
