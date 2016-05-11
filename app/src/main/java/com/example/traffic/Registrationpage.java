package com.example.traffic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;






import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrationpage extends ActionBarActivity {
	EditText e1,e2,e3,e4,e5;
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	Button b1;
	ActionBar actionbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrationpage);
		
		actionbar = getSupportActionBar();
        actionbar.setIcon(R.drawable.ic_launcher);
        actionbar.setTitle(Html.fromHtml("<font color='#ffffff'>Registration</font>"));
		
		e1= (EditText)findViewById(R.id.etxtname);
		e2= (EditText)findViewById(R.id.etxtphone);
		e3= (EditText)findViewById(R.id.etxtemailid);
		e4= (EditText)findViewById(R.id.etxtpass);
		e5= (EditText)findViewById(R.id.etxtcpass);
		
		b1 =(Button)findViewById(R.id.bb);
		b1.setOnClickListener(new View.OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String result = null;
        	   	InputStream is = null; 

				String name = e1.getText().toString();
				String phone = e2.getText().toString();
				String emailid = e3.getText().toString();
				String pass = e4.getText().toString();
				String cpass = e5.getText().toString();
				
				if (name.length()==0 || phone.length()==0 ||emailid.length()==0 || pass.length()==0 || cpass.length()==0 || cpass.length()==0) {
					
					Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_LONG).show();
					
					
				}
				else if (!(pass.equals(cpass))) {
					
					Toast.makeText(getApplicationContext(), "Password Does not match", Toast.LENGTH_LONG).show();
					e4.setError("Password does not match");
					e5.setError("Password does not match");
				}
				
				else if (!(emailid.contains("@")) || !(emailid.contains("."))) {
					
					Toast.makeText(getApplicationContext(), "Please enter valid email address", Toast.LENGTH_LONG).show();
					e3.setError("Email id not valid");
					
				}
				else if (! (e2.length()==10)){
					
					Toast.makeText(getApplicationContext(), "Please enter 10 digit mobile number", Toast.LENGTH_LONG).show();
					e2.setError("Mobile NO not valid");
				}
				
				else if (! (pass.length()==6)){
					
					Toast.makeText(getApplicationContext(), "Minimum six digit password", Toast.LENGTH_LONG).show();
					e4.setError("Mobile NO not valid");
				}
				else {
					
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("pm_name", name));
					params.add(new BasicNameValuePair("pm_cnct",phone));
					params.add(new BasicNameValuePair("pm_mail", emailid));
					params.add(new BasicNameValuePair("pm_pass", pass));
					
					
					StrictMode.setThreadPolicy(policy); 
					
					
					try{
		    	        HttpClient httpclient = new DefaultHttpClient();
		    	        HttpPost httppost = new HttpPost("http://192.168.1.222/jsonfile/insert.php");
		    	        httppost.setEntity(new UrlEncodedFormEntity(params));
		    	        HttpResponse response = httpclient.execute(httppost); 
		    	        HttpEntity entity = response.getEntity();
		    	        is = entity.getContent();

		    	        Log.e("log_tag", "connection success ");
		    	        Toast.makeText(getApplicationContext(), "Storing Data", Toast.LENGTH_SHORT).show();
						}
					catch(Exception e)
			    		{
			    	        Log.e("log_tag", "Error in http connection "+e.toString());
			    	        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

			    		}
					
			    	try{
		    	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		    	        StringBuilder sb = new StringBuilder();
		    	        String line = null;
		    	        while ((line = reader.readLine()) != null) 
		    	        	{
		    	                sb.append(line + "\n");
		    	                Toast.makeText(getApplicationContext(), "HI::"+sb, Toast.LENGTH_LONG).show();
		    	                
		    	                
		    	                result=sb.toString();
		    	                
		    	                is.close();
		    	                
		    	               
		    	                Intent i = new Intent(Registrationpage.this, Loginpage.class);
		    	                startActivity(i);
		    	               
		    	              
		    	                
		    	        	}
		    	        		
		    	    
			    		}
			    	
			    	catch(Exception e)
			    	{
			    	       Log.e("log_tag", "Error converting result "+e.toString());
			       	}
					
			    	
					
				}
				 
		    	
				
				
			}
		});





		}

}
