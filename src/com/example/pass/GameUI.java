package com.example.pass;

import com.example.pass.R;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class GameUI extends Activity {
	
	public static GameUI gu;
	CharView cv;
	
	public static int screenWidth,screenHeight;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		
		GameUI.gu = this;
		
		cv = new CharView(this);
		
		//setContentView(R.layout.activity_mainpass_ui);
		 DisplayMetrics dm = new DisplayMetrics();
			
		 	getWindowManager().getDefaultDisplay().getMetrics(dm);
			screenWidth = dm.widthPixels;
			screenHeight = dm.heightPixels;
			
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏 
	        SharedPreferences share = getSharedPreferences("playdata", MODE_PRIVATE);
	   	

	        setContentView(cv);
	}
	
	
}
