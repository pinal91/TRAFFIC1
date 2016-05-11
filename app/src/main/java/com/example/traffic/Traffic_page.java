package com.example.traffic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Traffic_page extends ActionBarActivity {
	EditText e1;
	Button b1,btndate, btntime;
	private int mYear, mMonth, mDay,mHour,mMinute;
	String uid;
	TextView t1,t2;
	PreferenceHelper pf;
	String ss,fdate,ftime ;
	JSONParser js;
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traffic_page);
		t1= (TextView)findViewById(R.id.txtdate);
		t2= (TextView)findViewById(R.id.txttime);
		e1 = (EditText)findViewById(R.id.etxtdesc);
		uid = pf.getPreferences(Traffic_page.this, "pm_id");
		b1 = (Button)findViewById(R.id.btnsubmit);
		btndate= (Button)findViewById(R.id.btndate);
		btntime = (Button)findViewById(R.id.btntime);
		
btndate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				
				DatePickerDialog dpd = new DatePickerDialog(Traffic_page.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						t1.setText((year + "-"+ (monthOfYear + 1) + "-" + dayOfMonth));
						fdate = t1.getText().toString();
					}
				}, mYear, mMonth, mDay);
				
				dpd.show();
				
				
			}
		});

btntime.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        
        TimePickerDialog tpd = new TimePickerDialog(Traffic_page.this, new TimePickerDialog.OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				 t2.setText(hourOfDay + ":" + minute);
				 ftime = t2.getText().toString();
			}
		},mHour, mMinute, false);
        tpd.show();
	

        // Launch Time Picker Dialog
//        TimePickerDialog tpd = new TimePickerDialog(this,
//                new TimePickerDialog.OnTimeSetListener() {
//
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay,
//                            int minute) {
//                        // Display Selected time in textbox
//                        txtTime.setText(hourOfDay + ":" + minute);
//                    }
//                }, mHour, mMinute, false);
//        tpd.show();
	}

		
	
});
		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String result = null;
        	   	InputStream is = null; 
        	   	
        	   	String desc = e1.getText().toString();
        	   	
        	   	
        	   	List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("nm_desc", desc));
				params.add(new BasicNameValuePair("nm_date",fdate));
				params.add(new BasicNameValuePair("nm_time", ftime));
				params.add(new BasicNameValuePair("pm_id", uid));
				
				StrictMode.setThreadPolicy(policy); 
				
				try{
	    	        HttpClient httpclient = new DefaultHttpClient();
	    	        HttpPost httppost = new HttpPost("http://192.168.0.101/jsonfile/insert_notification.php");
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
	    	                
	    	               
	    	                Intent i = new Intent(Traffic_page.this, Add_Traffic.class);
	    	                startActivity(i);
	    	               
	    	              
	    	                
	    	        	}
	    	        		
	    	    
		    		}
		    	
		    	catch(Exception e)
		    	{
		    	       Log.e("log_tag", "Error converting result "+e.toString());
		       	}
				
		    	
				
			}
			 
				
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.traffic_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
