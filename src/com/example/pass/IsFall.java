package com.example.pass;

import java.lang.Math;

import android.R.integer;
import android.graphics.Point;

public class IsFall {
//private Boolean isfall ;
	
	public boolean FallJudge(int rectcentrx,int rectcentry,int charcentrx,int charcentry) {
		boolean isfall;
		int distentce = getdistence(rectcentrx,rectcentry,charcentrx,charcentry);
		if(distentce < (rectcentrx + charcentrx) ){
			isfall = true;
		}else{
			isfall = false;
		}
		return isfall;
	}
	public int getdistence(int rectcentrx,int rectcentry,int charcentrx,int charcentry) {
		int distence ;
		int ws= Math.abs(rectcentrx-charcentrx);
		int hs = Math.abs(rectcentry - charcentry);
		distence  = (int)Math.pow(ws*ws, hs*hs);
		return distence ; 
	}
//	public Boolean getIsfall() {
//		return isfall;
//	}
//	public void setIsfall(Boolean isfall) {
//		this.isfall = isfall;
//	}
//	public IsFall(Boolean isfall) {
//		super();
//		this.isfall = isfall;
//	}
//	public IsFall() {
//		super();
//	}
//	
	
}
