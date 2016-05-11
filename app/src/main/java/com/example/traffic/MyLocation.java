package com.example.traffic;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocation extends Activity implements LocationListener{
	 

	 LocationManager locationManager ;
	    String provider;
	    Button navigation;
	    TextView tvLongitude,tvLatitude;
	    String s1,s2;
	    double lng;
		double lat;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_location);
		
		 tvLongitude = (TextView)findViewById(R.id.tv_longitude);
		 
        // Getting reference to TextView tv_latitude
        tvLatitude = (TextView)findViewById(R.id.tv_latitude);
 
        navigation=(Button)findViewById(R.id.navigation);
        
        navigation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				 Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+lat+","+lng+""));
	                startActivity(i);

				
//				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
//					    Uri.parse("http://maps.google.com/maps?saddr="+lat+"&daddr="+lng+""));
//					startActivity(intent);
			}
		});
		
		// Getting LocationManager object
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
 
        // Creating an empty criteria object
        Criteria criteria = new Criteria();
 
        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);
 
        if(provider!=null && !provider.equals("")){
 
            // Get the location from the given provider
            Location location = locationManager.getLastKnownLocation(provider);
 
            locationManager.requestLocationUpdates(provider, 20000, 1,  this);
 
            if(location!=null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
 
        }else{
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.nearestloc, menu);
		return true;
	}
	
	
	 @Override
	    public void onLocationChanged(Location location) {
	        // Getting reference to TextView tv_longitude
	         tvLongitude = (TextView)findViewById(R.id.tv_longitude);
	 
	        // Getting reference to TextView tv_latitude
	         tvLatitude = (TextView)findViewById(R.id.tv_latitude);
	 
	        // Setting Current Longitude
	        tvLongitude.setText("Longitude:" + location.getLongitude());
	        lng = location.getLongitude();
	 
	        // Setting Current Latitude
	        tvLatitude.setText("Latitude:" + location.getLatitude() );
	        lat = location.getLatitude();
	    }
	 
	    @Override
	    public void onProviderDisabled(String provider) {
	        // TODO Auto-generated method stub
	    }
	 
	    @Override
	    public void onProviderEnabled(String provider) {
	        // TODO Auto-generated method stub
	    	
	    }
	 
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	        // TODO Auto-generated method stub
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
