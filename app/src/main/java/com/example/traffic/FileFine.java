package com.example.traffic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;













import com.example.traffic.Searchdetail.getproduct;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class FileFine extends ActionBarActivity {
	
	EditText e1,e2,e3,e4,e5,e6;
	 String OID,ONAME,OCONTACT,OMAIL, OADD, VVENO, VVTYPE,VCHENO, VPURCHASE, VID;
	 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	Button b1,b2,b3;
	String uid;
	String name;
	PreferenceHelper pf;
	Spinner spnfine;
	JSONParser js;
	private int mYear, mMonth, mDay;
	String ss,fdate ;
	
	TextView t1,t2,t3;
	private ArrayList<Category> categoriesList;
	ArrayList<HashMap<String, String>> data;
	ProgressDialog pd, pDialog;
	
	private String URL_CATEGORIES = "http://192.168.1.222/jsonfile/get_fine.php";
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_fine);
		spnfine = (Spinner)findViewById(R.id.spinner1);
		new GetCategories().execute();
		e1= (EditText)findViewById(R.id.editText1);
		e2= (EditText)findViewById(R.id.etxtpname);
		e3 = (EditText)findViewById(R.id.etxtplicence);
		t3= (TextView)findViewById(R.id.txtdate);
		
		t1 = (TextView)findViewById(R.id.textView1);
		t2 = (TextView)findViewById(R.id.textView2);
		b2 = (Button)findViewById(R.id.btnpfine);
		b3 = (Button)findViewById(R.id.btndate);
		
		
		categoriesList = new ArrayList<Category>();
		//categoriesstatelist =new ArrayList<Categorystate>();
		
//		spnfine.setOnItemSelectedListener(this);
		
		uid = pf.getPreferences(FileFine.this, "pm_id");
		b1 = (Button)findViewById(R.id.button1);
		
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				
				DatePickerDialog dpd = new DatePickerDialog(FileFine.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						t3.setText((year + "-"+ (monthOfYear + 1) + "-" + dayOfMonth));
						fdate = t3.getText().toString();
					}
				}, mYear, mMonth, mDay);
				
				dpd.show();
				
				
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String result = null;
        	   	InputStream is = null; 
        	   	
        	   	String name = e2.getText().toString();
        	   	String licence = e3.getText().toString();
        	   //	String vno = e4.getText().toString();
        	   	
        	   	List<NameValuePair> params = new ArrayList<NameValuePair>();
        	   	params.add(new BasicNameValuePair("uid", uid));
        	   	params.add(new BasicNameValuePair("VID", VID));
        	   	params.add(new BasicNameValuePair("name", name));
        	   	params.add(new BasicNameValuePair("licence", licence));
        	   	params.add(new BasicNameValuePair("ss", ss));
        	   	params.add(new BasicNameValuePair("fdate", fdate));
        	   	
        	   //	params.add(new BasicNameValuePair("uid", uid));
        	   	
        	   	StrictMode.setThreadPolicy(policy); 
        	   	try{
        	        HttpClient httpclient = new DefaultHttpClient();
        	        HttpPost httppost = new HttpPost("http://192.168.1.222/jsonfile/mycart.php?uid="+uid+"&VID="+VID+"&name="+name+"&licence="+licence+"&ss="+ss+"&fdate="+fdate);
        	        //httppost.setEntity(new UrlEncodedFormEntity(params));
        	        HttpResponse response = httpclient.execute(httppost); 
        	        HttpEntity entity = response.getEntity();
        	        is = entity.getContent();

        	        Log.e("log_tag", "connection success ");
        	        Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
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
        	                Toast.makeText(getApplicationContext(), "sb:::::"+sb, Toast.LENGTH_LONG).show();
        	                
        	                
        	                result=sb.toString();
        	                
        	                is.close();
								String subject="Break Down GOV Rules";

							Intent i =new Intent(FileFine.this,MailActivity.class);
							i.putExtra("email",t2.getText().toString());
							i.putExtra("subject",subject);
							i.putExtra("ss",ss);

							startActivity(i);
        	                
        	               
        	               // startActivity(i1);
        	                
        	        	}
        	        		
        	    
    	    		}
    	    	
    	    	catch(Exception e)
    	    	{
    	    	       Log.e("log_tag", "Error converting result "+e.toString());
    	       	}
    			
    	    	//startActivity(i1);	
    			
    		
    		 
        	
    		
    		
    	
        	   	
        	   	
				
			}
		});
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				name = e1.getText().toString();
				new getproduct().execute();
			}
		});
	}
	
	class getproduct extends AsyncTask<Void, Void, Void>{
		//PreferenceHelper pf;
		
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd = new ProgressDialog(FileFine.this);
			pd.setMessage("fetching data...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			pd.show();
		} 

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Intent i = getIntent();

			//s = i.getStringExtra("id");
			BasicNameValuePair nm = new BasicNameValuePair("my", name);
			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(nm);
			js = new JSONParser();
			data = new ArrayList<HashMap<String,String>>();
			
			
			String strresponce = js.getJSONFromUrl("http://192.168.1.222/jsonfile/grev.php", param);
			Log.d("database", "product comes"+ strresponce);
			JSONObject jobjResponse;  
			try {
				jobjResponse = new JSONObject(strresponce);
				JSONArray jArrayProdList = new JSONArray();
				jArrayProdList = jobjResponse.getJSONArray("rag");
				for (int i1 = 0; i1 < jArrayProdList.length(); i1++) {
					HashMap<String,String> map = new HashMap<String, String>();
					JSONObject jobj = jArrayProdList.getJSONObject(i1);
					
				 OID= jobj.getString("om_id");
				 ONAME = jobj.getString("om_name");
			     OCONTACT = jobj.getString("om_cnct");
				 OMAIL = jobj.getString("om_mail");
				 OADD= jobj.getString("om_add");
				 VID= jobj.getString("vm_id");
			     VVENO= jobj.getString("vm_vehical_no");
				 VVTYPE= jobj.getString("vm_type");
				 VCHENO= jobj.getString("vm_chessis_no");
				 VPURCHASE= jobj.getString("vm_purchase");
													
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
			if(pd.isShowing()){
				pd.dismiss();			
			}
			//id.setText(OID);
			t1.setText(ONAME);
			//owcontact.setText(OCONTACT);
			t2.setText(OMAIL);
//			oadd.setText(OADD);
//			vvcheno.setText(VCHENO);
//			vpurchase.setText(VPURCHASE);
//			vveno.setText(VVENO);
//			vvtype.setText(VVTYPE);
			
			//imageLoader.displayImage(img,img1 , options);
			
		}
		
	}
	private class GetCategories extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FileFine.this);
			pDialog.setMessage("Fetching Fine categories..");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			ServiceHandler jsonParser = new ServiceHandler();
			String json = jsonParser.makeServiceCall(URL_CATEGORIES, ServiceHandler.GET);

			Log.e("Response: ", "> " + json);

			if (json != null) {
				try {
					JSONObject jsonObj = new JSONObject(json);
					if (jsonObj != null) {
						JSONArray state = jsonObj.getJSONArray("year_list");

						for (int i = 0; i < state.length(); i++) {
							JSONObject catObj = (JSONObject) state.get(i);
							Category cat = new Category(catObj.getInt("fdm_id"),
									catObj.getString("fdm_desc"));
									//catObj.getString("city_state");
							categoriesList.add(cat);
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Log.e("JSON Data", "Didn't receive any data from server!");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			populateSpinner();
		}

	}
	private void populateSpinner() {
		List<String> lables = new ArrayList<String>();
		
		//txtCategory.setText("");

		for (int i = 0; i < categoriesList.size(); i++) {
			lables.add(categoriesList.get(i).getName());
		}

		// Creating adapter for spinner
		final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spnfine.setAdapter(spinnerAdapter);
		//spnstate.setAdapter(spinnerAdapter);
		
		spnfine.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//final long spinID= ((Spinner)findViewById(R.id.spinner1)).getSelectedItemId();
//			String	 spnfine = parent.getItemAtPosition(position).toString();
//	           int     count = position;
			 ss = spinnerAdapter.getItem(position);
//				String id = spinnerAdapter.getItem(position).get
				Toast.makeText(getApplicationContext(), "you selected" +ss, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_fine, menu);
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


//	@Override
//	public void onItemSelected(AdapterView<?> parent, View view, int position,
//			long id) {
//		// TODO Auto-generated method stub
//		
//		ss = spinnerAdapter.getItem(position);
//		
//	}
//
//
//	@Override
//	public void onNothingSelected(AdapterView<?> parent) {
//		// TODO Auto-generated method stub
//		
//	}
}
