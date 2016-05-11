package com.example.traffic;

import java.util.ArrayList;
import java.util.HashMap;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class faculty_list_adapter extends BaseAdapter{
	
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> result = new HashMap<String, String>();
	DisplayImageOptions options;
	
	faculty_list_adapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		this.data = arraylist;
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheOnDisc(true).cacheInMemory(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.displayer(new FadeInBitmapDisplayer(300)).build();

ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
		context).defaultDisplayImageOptions(defaultOptions)
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
		
		
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		TextView fname,fdepart,fqualifi,fdesignation,fexperience,femail;
		ImageView fac_img;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.singlefaculty, parent, false);
		result = data.get(position);
		
		fname = (TextView)itemView.findViewById(R.id.txtfacname);
		fdepart = (TextView)itemView.findViewById(R.id.txtfacdepartment);
		fqualifi = (TextView)itemView.findViewById(R.id.txtfacqualify);
		fdesignation = (TextView)itemView.findViewById(R.id.txtfacdesign);
		fexperience = (TextView)itemView.findViewById(R.id.txtfacexperience);
		
		fac_img = (ImageView) itemView.findViewById(R.id.imgfac);
		
		
		fname.setText(result.get("sm_contact"));
		fdepart.setText(result.get("sm_name"));
		fqualifi.setText(result.get("sm_email"));
		
		fdesignation.setText(result.get("hm_name"));
		//fexperience.setText(result.get("hm_adress"));*/
		
		imageLoader.displayImage(result.get("rm_image"), fac_img, options);
		
		
		 itemView.setOnClickListener(new View.OnClickListener() {

	            public void onClick(View v) {
	                // TODO Auto-generated method stub
//	                HashMap<String, String> detailmap = new HashMap<String, String>();
//	                
//	                
//					
//					
//
//	                detailmap = data.get(position);
//	                Intent idetl = new Intent(context, Detailrestuser.class);
//	                idetl.putExtra("sm_email", detailmap.get("sm_email"));
//	                idetl.putExtra("hm_name", detailmap.get("hm_name"));
//	                idetl.putExtra("hm_adress", detailmap.get("hm_adress"));
//	                idetl.putExtra("hm_url", detailmap.get("hm_url"));
//	                idetl.putExtra("hm_contact", detailmap.get("hm_contact"));
//	                idetl.putExtra("prm_medicine_name", detailmap.get("prm_medicine_name"));
//	                idetl.putExtra("tm_description", detailmap.get("tm_description"));
//	                idetl.putExtra("tm_date", detailmap.get("tm_date"));
//	                idetl.putExtra("rm_description", detailmap.get("rm_description"));
//	                idetl.putExtra("rm_image", detailmap.get("rm_image"));
//	                idetl.putExtra("pm_id", detailmap.get("pm_id"));
//
//
//
//	                context.startActivity(idetl);

	            }
	        });
		
		return itemView;
	}

}
