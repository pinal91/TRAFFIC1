package com.example.traffic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.traffic.Searchdetail.getproduct;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchQrCode extends ActionBarActivity {
	 String OID,ONAME,OCONTACT,OMAIL, OADD, VVENO, VVTYPE,VCHENO, VPURCHASE,VID;
		String s;
		JSONParser js;
		String name;
		ProgressDialog pd;
		ArrayList<HashMap<String, String>> data;
		TextView id1,name1,gender1,address1,city1,state1,country1,pincode1,mail1;
		TextView id ,owname, owcontact, omail, oadd, vveno, vvtype, vvcheno, vpurchase ;

		ImageView img1;
		EditText e1;
		ImageLoader imageLoader;
		DisplayImageOptions options;
		
		Button btnHistory,btnCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_qr_code);
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheOnDisc(true).cacheInMemory(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.displayer(new FadeInBitmapDisplayer(300)).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
		SearchQrCode.this).defaultDisplayImageOptions(defaultOptions)
		.memoryCache(new WeakMemoryCache())
		.discCacheSize(100 * 1024 * 1024).build();

		ImageLoader.getInstance().init(config);
		// END - UNIVERSAL IMAGE LOADER SETUPuto-generated method stub
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.build();
		
	
		id = (TextView)findViewById(R.id.id);
		owname = (TextView)findViewById(R.id.name);
		owcontact = (TextView)findViewById(R.id.gender);
		omail = (TextView)findViewById(R.id.address);
		oadd = (TextView)findViewById(R.id.city);
		vveno = (TextView)findViewById(R.id.state);
		vvtype = (TextView)findViewById(R.id.country);
		vvcheno = (TextView)findViewById(R.id.pincode);
		vpurchase = (TextView)findViewById(R.id.mail);
		
		btnHistory=(Button)findViewById(R.id.btnHistory);
		//btnCreate=(Button)findViewById(R.id.btnCreate);
		img1 = (ImageView)findViewById(R.id.dimg);
		
		
		
		new getproduct().execute();
		
		
		btnHistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),History.class);
				i.putExtra("s", VID);
				startActivity(i);
				
				Toast.makeText(getApplicationContext(),VID, Toast.LENGTH_LONG).show();;
				
			}
		});
		

		
	}
	
	class getproduct extends AsyncTask<Void, Void, Void>{
		//PreferenceHelper pf;
		
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd = new ProgressDialog(SearchQrCode.this);
			pd.setMessage("fetching data...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			pd.show();
		} 

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Intent i = getIntent();
			//name = e1.getText().toString();
			s = i.getStringExtra("id");
			BasicNameValuePair nm = new BasicNameValuePair("my", s);
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
			id.setText(OID);
			owname.setText(ONAME);
			owcontact.setText(OCONTACT);
			omail.setText(OMAIL);
			oadd.setText(OADD);
			vvcheno.setText(VCHENO);
			vpurchase.setText(VPURCHASE);
			vveno.setText(VVENO);
			vvtype.setText(VVTYPE);
			
			//imageLoader.displayImage(img,img1 , options);
			
		}
		
	}
}
