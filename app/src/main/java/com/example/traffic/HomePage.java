package com.example.traffic;



import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomePage extends ActionBarActivity {
	ActionBar actionbar;
	Button btnfine,btnqrcode,btnbreak, btntraffic,btnlocatio,btnsettin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		actionbar = getSupportActionBar();
        actionbar.setIcon(R.drawable.ic_launcher);
        actionbar.setTitle(Html.fromHtml("<font color='#ffffff'>Home Page</font>"));
        
        btnfine = (Button)findViewById(R.id.btnFine);
        btnbreak = (Button)findViewById(R.id.btnbreakdwn);
        btnqrcode = (Button)findViewById(R.id.btnqrcode);
        btntraffic = (Button)findViewById(R.id.btntrafficadd);
        btnlocatio = (Button)findViewById(R.id.btnlocation);
        btnsettin = (Button)findViewById(R.id.btnsetting);
        
        btnsettin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomePage.this, SettingPage.class);
				startActivity(i1);
			}
		});
        
        
        
        btntraffic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomePage.this, Add_Traffic.class);
				startActivity(i1);
			}
		});
        
        btnbreak.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomePage.this, FileFine.class);
				startActivity(i1);
			}
		});
        
        btnqrcode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomePage.this, Searchdetail.class);
				startActivity(i1);
			}
		});
        
        
        
        btnfine.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomePage.this, FileFine.class);
				startActivity(i1);
			}
		});
        
        btnlocatio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1 = new Intent(HomePage.this, MyLocation.class);
				startActivity(i1);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
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
