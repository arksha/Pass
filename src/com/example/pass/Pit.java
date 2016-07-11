package com.example.pass;




import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.Image;

public class Pit {
	private Bitmap pitimg ;
	private int posx,posy,pitW,pitH,recCentrPointx,recCentrPointy ;//¿í¸ß
	boolean showpit,hassolu;
//	private OvalShape oval;

	public boolean isHassolu() {
		return hassolu;
	}
	public void setHassolu(boolean hassolu) {
		this.hassolu = hassolu;
	}
	int recArea;
	public Pit() {
	super();
	this.showpit = true;
}
	public Pit( int posx,int posy,int pitW, int pitH,boolean showpit) {
		super();
		this.showpit = showpit;
		this.posx = posx;
		this.posy = posy;
		this.pitW = pitW;
		this.pitH = pitH;
		
	}
	
	public boolean isShowpit() {
		return showpit;
	}
	public void setShowpit(boolean showpit) {
		this.showpit = showpit;
	}
	public int getpitW() {
		return pitW;
	}
	public void setpitW(int pitW) {
		this.pitW = pitW;
	}
	public int getpitH() {
		return pitH;
	}
	public void setpitH(int pitH) {
		this.pitH = pitH;
	}
	
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	public Bitmap getPitimg() {
		return pitimg;
	}
	public void setPitimg(Bitmap pitimg) {
		this.pitimg = pitimg;
	}
	public int getRecArea() {
		recArea= this.pitH*this.pitW;
		return recArea ;
	}
	
	public int getpitcentrx(){
		recCentrPointx =this.posx + this.pitW/2; 
		return recCentrPointx;
	}
	public int getpitcentry(){
		recCentrPointy =this.posy + this.pitH/2;
		return recCentrPointy;
	}
}
