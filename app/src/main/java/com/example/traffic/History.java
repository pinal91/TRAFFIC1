package com.example.traffic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;







import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class History extends ActionBarActivity {
	
	
	
	
	String pid;
	String pdepid;
	String psmid;
	String phmid;
	String  pname;
	String  pfname;
	String  pmname;
	String  pcast;
	String  pgender;
	String  pheight;
	String  pweight;
	String  pjob;
	Button b1;
	String  ptime;
	String  pnationality;
	String  pdiseases;
	String  poccuoation;
	String  page;
	String  pcontact;
	String  pdesc;
	String  pbgroup;
	String  pemail;
	String  padd;
	String  ppincode;
	String pcity;
	String  pdate;
	String  pqrcode,pm_id;
	 String sm_name,sm_contact, sm_email,hm_name, hm_adress;
	 String hm_url,hm_contact,prm_medicine_name,tm_description,tm_date,tm_time,rm_description,rm_image;	
	 
	
	String s;
	JSONParser js;
	ProgressDialog pd;
	ArrayList<HashMap<String, String>> data;
	TextView id1,name1,gender1,address1,city1,state1,country1,pincode1,mail1;
	ImageView img1;
	
	ImageLoader imageLoader;
	DisplayImageOptions options;
	
	ListView lvHistory;
	//String s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		//b1 = (Button)findViewById(R.id.btnadd);
		
		Intent i = getIntent();
		 s = i.getStringExtra("s");
		
		 Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
		lvHistory=(ListView)findViewById(R.id.lvHistory);
		getproduct g=new getproduct();
		g.execute();
		
//		b1.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}

	
	
	
	
	class getproduct extends AsyncTask<Void, Void, Void>{
		//PreferenceHelper pf;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			
			pd = new ProgressDialog(History.this);
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
			
			
			String strresponce = js.getJSONFromUrl("http://192.168.1.222/jsonfile/history.php?pid="+s);
			Log.d("database", "product comes"+ strresponce);
			JSONObject jobjResponse;  
			try {
				jobjResponse = new JSONObject(strresponce);
				JSONArray jArrayProdList = new JSONArray();
				jArrayProdList = jobjResponse.getJSONArray("nikki");
				for (int i1 = 0; i1 < jArrayProdList.length(); i1++) {
					HashMap<String,String> map = new HashMap<String, String>();
					JSONObject jobj = jArrayProdList.getJSONObject(i1);
					
					 pm_id= jobj.getString("vm_id");

					 sm_name= jobj.getString("pm_name");
					 sm_contact = jobj.getString("fm_name");
					 sm_email = jobj.getString("fdm_id");
					 hm_name = jobj.getString("fm_date");
//					 hm_adress= jobj.getString("hm_address");
//					 hm_url= jobj.getString("hm_url");
//					hm_contact= jobj.getString("hm_contact");
//					prm_medicine_name= jobj.getString("prm_medicine_name");
//					tm_description= jobj.getString("tm_description");
//					tm_date= jobj.getString("tm_date");
//					tm_time= jobj.getString("tm_time");
//					rm_description= jobj.getString("rm_description");
//					rm_image= jobj.getString("rm_image");
					
					
					map.put("sm_name", sm_name);
					map.put( "sm_contact", sm_contact);
					map.put( "sm_email", sm_email );
					map.put( "hm_name", hm_name );
//					map.put( "hm_adress", hm_adress );
//					map.put( "hm_url", hm_url );
//					map.put("hm_contact", hm_contact);
//					map.put( "prm_medicine_name", prm_medicine_name);
//					map.put( "tm_description", tm_description );
//					map.put( "tm_date", tm_date );
//					map.put( "tm_time ", tm_time );
//					map.put( "rm_description",rm_description );
//					map.put( "rm_image",rm_image );
					
					map.put("pm_id", pm_id);
					
					
					
					
					
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
			
			Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
			
			faculty_list_adapter adapter = new faculty_list_adapter(History.this,data );
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


	
}
