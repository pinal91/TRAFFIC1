package com.example.traffic;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.traffic.History.getproduct;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Add_Traffic extends ActionBarActivity {
	Button btntraffic;
	ListView lvHistory;
	ProgressDialog pd;
	JSONParser js;
	String nm_desc, nm_date, nm_time,pm_name;
	ArrayList<HashMap<String, String>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__traffic);
		
		btntraffic =(Button)findViewById(R.id.btnaddtraffic);
		lvHistory=(ListView)findViewById(R.id.listView1);
		
		getproduct g=new getproduct();
		g.execute();
		
		
		
		btntraffic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(Add_Traffic.this, Traffic_page.class);
				startActivity(i1);
			}
		});
	}
	class getproduct extends AsyncTask<Void, Void, Void>{
		//PreferenceHelper pf;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			
			pd = new ProgressDialog(Add_Traffic.this);
			pd.setMessage("fetching data...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			pd.show();
		} 

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			/*BasicNameValuePair nm = new BasicNameValuePair("my", s);
			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(nm);*/
			
			js = new JSONParser();
			data = new ArrayList<HashMap<String,String>>();
			
			
			String strresponce = js.getJSONFromUrl("http://192.168.1.222/jsonfile/notification.php");
			Log.d("database", "product comes"+ strresponce);
			JSONObject jobjResponse;  
			try {
				jobjResponse = new JSONObject(strresponce);
				JSONArray jArrayProdList = new JSONArray();
				jArrayProdList = jobjResponse.getJSONArray("nikki");
				for (int i1 = 0; i1 < jArrayProdList.length(); i1++) {
					HashMap<String,String> map = new HashMap<String, String>();
					JSONObject jobj = jArrayProdList.getJSONObject(i1);
					
					 pm_name= jobj.getString("pm_name");

					 nm_date= jobj.getString("nm_date");
					 nm_time = jobj.getString("nm_time");
					 nm_desc = jobj.getString("nm_desc");
					// hm_name = jobj.getString("fm_date");
//					 hm_adress= jobj.getString("hm_address");
//					 hm_url= jobj.getString("hm_url");
//					hm_contact= jobj.getString("hm_contact");
//					prm_medicine_name= jobj.getString("prm_medicine_name");
//					tm_description= jobj.getString("tm_description");
//					tm_date= jobj.getString("tm_date");
//					tm_time= jobj.getString("tm_time");
//					rm_description= jobj.getString("rm_description");
//					rm_image= jobj.getString("rm_image");
					
					
					map.put("pm_name", pm_name);
					map.put( "nm_date", nm_date);
					map.put( "nm_time", nm_time );
					map.put( "nm_desc", nm_desc );
//					map.put( "hm_adress", hm_adress );
//					map.put( "hm_url", hm_url );
//					map.put("hm_contact", hm_contact);
//					map.put( "prm_medicine_name", prm_medicine_name);
//					map.put( "tm_description", tm_description );
//					map.put( "tm_date", tm_date );
//					map.put( "tm_time ", tm_time );
//					map.put( "rm_description",rm_description );
//					map.put( "rm_image",rm_image );
					
			//		map.put("pm_id", pm_id);
					
					
					
					
					
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
			if(pd.isShowing()){
				pd.dismiss();			
			}
			
		//	Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
			
			notification_list_adpater adapter = new notification_list_adpater(Add_Traffic.this,data );
			lvHistory.setAdapter(adapter);
			
		/*	id1.setText(pid);
			name1.setText(pname+" "+pfname);
			gender1.setText(pgender);
			address1.setText(padd);
			city1.setText(pcity);
		//	state1.setText(state);
		//	country1.setText(country);
			pincode1.setText(ppincode);
			mail1.setText(pemail);*/
			
			//imageLoader.displayImage(img,img1 , options);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__traffic, menu);
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
