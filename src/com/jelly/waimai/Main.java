package com.jelly.waimai;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jelly.waimai.util.DBHelper;
import com.jelly.waimai.util.GetPic;

public class Main extends FragmentActivity {
	private Fragment[] mFragments;
	private RadioGroup bottomRg;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private RadioButton rb1,rb2,rb3,rb4;
	private DBHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//in front of setContentView;
		setContentView(R.layout.main);
		
//		ImageView im = (ImageView)findViewById(R.id.pic);
//		im.setBackgroundResource(R.drawable.baji);
		int result = GetPic.getPic("baji");
		Log.i("result", result+"");
		
		helper = new DBHelper(this,"food.db",null,1);
		helper.getReadableDatabase();
		try {
			createDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		mFragments = new Fragment[4];
		fragmentManager = getSupportFragmentManager();
		mFragments[0]=fragmentManager.findFragmentById(R.id.fragment_main);
		mFragments[1]=fragmentManager.findFragmentById(R.id.fragment_sort);
		mFragments[2]=fragmentManager.findFragmentById(R.id.fragment_rank);
		mFragments[3]=fragmentManager.findFragmentById(R.id.fragment_order);
		
		fragmentTransaction = fragmentManager.beginTransaction()
				.hide(mFragments[0]).hide(mFragments[1])
				.hide(mFragments[2]).hide(mFragments[3]);
		fragmentTransaction.show(mFragments[0]).commit();
		setFragmentIndicator();
	}
	private void setFragmentIndicator(){
		
		bottomRg = (RadioGroup)findViewById(R.id.bottomRg);
		rb1 = (RadioButton)findViewById(R.id.rb1);
		rb2 = (RadioButton)findViewById(R.id.rb2);
		rb3 = (RadioButton)findViewById(R.id.rb3);
		rb3 = (RadioButton)findViewById(R.id.rb4);
		
		bottomRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				fragmentTransaction = fragmentManager.beginTransaction()
						.hide(mFragments[0]).hide(mFragments[1])
						.hide(mFragments[2]).hide(mFragments[3]);
				switch(checkedId){
				case R.id.rb1:
					fragmentTransaction.show(mFragments[0]).commit();
					break;
				case R.id.rb2:
					fragmentTransaction.show(mFragments[1]).commit();
					break;
				case R.id.rb3:
					fragmentTransaction.show(mFragments[2]).commit();
					break;
				case R.id.rb4:
					fragmentTransaction.show(mFragments[3]).commit();
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void createDatabase() throws IOException {
		 
	    InputStream assetsDB = this.getAssets().open("food.db");
	    OutputStream dbOut = new FileOutputStream("/data/data/com.jelly.waimai/databases/food.db");
	 
	    byte[] buffer = new byte[1024];
	    int length;
	    while ((length = assetsDB.read(buffer))>0){
	      dbOut.write(buffer, 0, length);
	    }
	 
	    dbOut.flush();
	    dbOut.close();
	    assetsDB.close();
	}
}
