package com.example.pass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path.Op;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Switch;


public class CharView extends SurfaceView implements SurfaceHolder.Callback  {

	SurfaceHolder holder;
	public boolean isRunning = true;
	public SoundManager mSoundManager;// = new SoundManager();	
	public int state;
	private int ttick = 100;
	public final static int LOGO = 0;
	public final static int PREGAME = 1;
	public final static int INGAME = 2;
	public final static int PAUSE = 3;
	public final static int GAMEOVER = 4;
	public final static int WINGAME = 5;
	public final static int MENU = 6;
	public final static int SETTING = 7;
	public final static int INIT = 8;
	public final static int CHOOSELEVEL = 9;
	public final static int HELP = 10;
	public final static int WELCOME = 11;
	public final static int SOUND = 12;

	Context context = getContext();
	private Bitmap pitimg;
	Pit pit;
	boolean isfall, iswin, isCollide, HasSolu, isStart, isStand;
	 boolean soundebtstate,easybtstate,normalbtstate, hardbtstate;
	boolean continuebtstate,mainmenubtstate,musicbtstate,crlbtstate,modebtstate,welcomeclick;
	Canvas c;
	AssetManager am;
	Paint mPaint;
	Bitmap remiw, remib, door,hand, remi2, number, menubg, winbg, losebg,loadingbg,pausebg,choosestagebg,welcombg,optionbg;
	Bitmap winwd,losewd,replaywd,startwd,startwd2,choosestagewd;
	Bitmap pausebt,resumebt,continuebt,backbt,yesbt,nobt,okbt,returnbt,mainmenubt,settingbt,continuegamebt,newgamebt,scoreboard,easybt,normbt,hardbt
	,modebt,musicbt,crlbt,soundebt,mode2bt,music2bt,crl2bt,sounde2bt,helpbt;
	private final int W = 32;
	private final int H = 48;
	int posx, posy, pitposx, pitposy, doorx, doory;
	int pitW, pitH, doorW, doorH;
	int pointX, pointY;
	private int heap;
	int speed = 0;
	int speedver = 0;
	int i = 1;
	boolean isLeft = true;
	boolean isUpper = true;
	private int frame = 0;
	private int frame2 = 0;
	private int frames = 0;
	private int framelogo = 0;
	
	int pitnum;
	int wintimes,losetimes;
	
	int survivetime, showtime;//画布上的显示时间
	String  str;//游戏倒计时时间
	ArrayList<Pit> pitlist;
	MediaPlayer bgmPlayer = new MediaPlayer();
	public CharView(final GameUI context) {
		super(context);

		mPaint = new Paint();
		holder = getHolder();// 获取holder
		holder.addCallback(this);
		am = getResources().getAssets();
		
	
		mSoundManager = new SoundManager();	
		mSoundManager.initSounds(GameUI.gu.getBaseContext());
		
		
		try {
//			mSoundManager.addSound(1,am.openFd("sample.ogg"));
//			mSoundManager.addSound(1,am.openFd("bgm/sample.mp3"));
			mSoundManager.addBgm(1,am.openFd("bgm/welcomebgm.mp3"));
			mSoundManager.addBgm(2,am.openFd("bgm/menubgm.mp3"));
			mSoundManager.addBgm(3,am.openFd("bgm/choosestagebgm.mp3"));
			mSoundManager.addBgm(4,am.openFd("bgm/settingbgm.mp3"));
			mSoundManager.addBgm(5,am.openFd("bgm/easybgm.mp3"));
			mSoundManager.addBgm(6,am.openFd("bgm/normalbgm.mp3"));
			mSoundManager.addBgm(7,am.openFd("bgm/hardbgm.mp3"));
			
			mSoundManager.addSound(1,am.openFd("sound/ok.wav"));
			mSoundManager.addSound(2,am.openFd("sound/pause.wav"));
//			mSoundManager.addSound(1,am.openFd("sound/select.wav"));
			Thread.sleep(1000);// 给予初始化音乐文件足够时间
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		setState(SOUND);
//		
		
		welcombg = ImageUtils.getImageFromAssetsFile("welcome.png", am);
		menubg = ImageUtils.getImageFromAssetsFile("menubg.png", am);
		optionbg = ImageUtils.getImageFromAssetsFile("optionbg.png", am);
		choosestagebg=  ImageUtils.getImageFromAssetsFile("choosestagebg.png", am);
		continuebt = ImageUtils.getImageFromAssetsFile("continue.png", am);
//		backbt = ImageUtils.getImageFromAssetsFile("back.png", am);
		winbg = ImageUtils.getImageFromAssetsFile("winbg.png", am);
		
		
		
		
		mainmenubt = ImageUtils.getImageFromAssetsFile("play.png", am);
		settingbt =  ImageUtils.getImageFromAssetsFile("option.png", am);
		helpbt = ImageUtils.getImageFromAssetsFile("help.png", am);
		continuegamebt =  ImageUtils.getImageFromAssetsFile("continuegame.png", am);
		newgamebt = ImageUtils.getImageFromAssetsFile("newgame.png", am);
		
		choosestagewd = ImageUtils.getImageFromAssetsFile("choosestage.png", am);
		easybt = ImageUtils.getImageFromAssetsFile("easy.png", am);
		normbt =  ImageUtils.getImageFromAssetsFile("normal.png", am);
		hardbt =  ImageUtils.getImageFromAssetsFile("hard.png", am);
		pausebt = ImageUtils.getImageFromAssetsFile("pauseBtn.png", am);
		returnbt = ImageUtils.getImageFromAssetsFile("return.png", am);
//		startwd = ImageUtils.getImageFromAssetsFile("start.png", am);
//		startwd2 =  ImageUtils.getImageFromAssetsFile("start2.png", am);
		
		
		setState(WELCOME);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		isRunning = true;
		new Thread(new MyThread()).start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		isRunning = false;
	}

	public void setState(int s) {
		state = s;
		switch (s) {
		case SOUND:
			
			
			break;
		case INIT:
			if (easybtstate) {
				pitnum = 7;
				 survivetime = 300;	
			}else if (normalbtstate) {
				pitnum = 10;
				 survivetime = 200;
			}else if (hardbtstate) {
				pitnum = 14;
				 survivetime = 100;
			}
			break;
		 case WELCOME:

			 welcomeclick = false;
			 mSoundManager.playBgm(1);
			 break;
		 case MENU:
			 
			 mSoundManager.playBgm(2);
			 mainmenubtstate = false;
			 break;
		 case LOGO :
			 ttick = 20;
		 break;
		 case CHOOSELEVEL:
			 
			 break;
		 case PREGAME:
			 ttick = 50;

			 break;
//			 
//		 case INGAME:
//			 survivetime = 300;
//			 break;
		 case PAUSE:
			 mSoundManager.playSound(2);
			 break;
		 case WINGAME:
			 continuebtstate= false;
			
				wintimes++;
			 break;
		 case GAMEOVER:
			 losetimes++;
			 continuebtstate= false;
//			
			break;
		 case SETTING:
			 modebt=  ImageUtils.getImageFromAssetsFile("mode.png", am);
				mode2bt = ImageUtils.getImageFromAssetsFile("mode2.png", am);
				musicbt=  ImageUtils.getImageFromAssetsFile("music.png", am);
				music2bt=  ImageUtils.getImageFromAssetsFile("music2.png", am);
				
				crlbt=  ImageUtils.getImageFromAssetsFile("ct.png", am);
				crl2bt=  ImageUtils.getImageFromAssetsFile("ct2.png", am);
				soundebt=  ImageUtils.getImageFromAssetsFile("soundeffect.png", am);
				sounde2bt=  ImageUtils.getImageFromAssetsFile("soundeffect2.png", am);
			 crlbtstate = false;
			 modebtstate = false;
			 musicbtstate = true;
			 soundebtstate = false;
		
			 break;
			 
		//
		 }
	
	}

	class MyThread implements Runnable {
		public void run() {
			while (isRunning) {

				c = holder.lockCanvas(null);// 获取画布
				mPaint.setAntiAlias(true);// 抗锯齿
				int count;// 倒计时
				

				try {

					switch (state) {
//				case SOUND:
//				
//					
//					break;
					case INIT:
						mSoundManager.stopBgm(2);
						posx = GameUI.screenWidth / 2 - remiw.getWidth() / 4;
						posy = GameUI.screenHeight - remiw.getHeight() / 4;

						doorW = door.getWidth();
						doorH = door.getHeight();
						doorx = GameUI.screenWidth / 2 - doorW / 2;
						doory = 0;
						pitlist = new ArrayList<Pit>();
					//	pitlist = randompit(pitnum);// 随机坑
						randompit(pitnum);
						
						setState(PREGAME);
						break;
					case PREGAME:
					
						count = ttick-- / 10;
						mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						drawpit(c);
						c.drawBitmap(door, doorx, doory, mPaint);

						c.save();
						c.clipRect(posx, posy, posx + W, posy + H);
						c.drawBitmap(remi2, posx - W * frame2, posy, mPaint);
						c.restore();
						if (frame2 < 3) {
							frame2++;
						} else {
							frame2 = 0;
						}
						
						if(count>0){
							c.save();
							c.clipRect(GameUI.screenWidth / 2 - 20,
									GameUI.screenHeight / 2 - 20,
									GameUI.screenWidth / 2 + 20,
									GameUI.screenHeight / 2 + 20);
							c.drawBitmap(number, GameUI.screenWidth / 2 - 20
									- 40 * count, GameUI.screenHeight / 2 - 20,
									mPaint);
							c.restore();
						}	
						else{
								setState(INGAME);
						}
							
						
	
						break;

					case INGAME:
						
						mPaint.setColor(Color.BLACK);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						c.drawBitmap(door, doorx, doory, mPaint);
						isfall = FallJudge(pitlist, posx, posy, W, H);
						c.drawBitmap(pausebt, GameUI.screenWidth - 88,0, mPaint);

						showtime  = survivetime--/10;
						
						mPaint.setColor(Color.BLUE);
						 str = "timeleft:"+showtime;
						mPaint.setTextSize(40);//设置字体大小
						c.drawText(str,20,30, mPaint);
						System.out.println(showtime+"showtime");
						if (showtime ==0) {
							setState(GAMEOVER);							
						} else {

							if (isfall) {
								setState(GAMEOVER);
								
							} else {
								iswin = WinJudge(doorx, doory, doorW, doorH,
										posx, posy, W, H);
								if (iswin) {
								
									setState(WINGAME);
									
								} else {// keep playing
									
//									if (drawhand) {
//										c.save();
//										c.clipRect(pointX, pointY, hand.getWidth()/5+pointX, hand.getHeight()/2 +pointY);
//										c.drawBitmap(hand, pointX-hand.getWidth()/5*framehand, pointY-hand.getHeight()/2*handheap, mPaint);
//										c.restore();
//										if (framehand<4) {
//											framehand++;
//											handheap = 0;
//										} else {
//											
//											framehand = 0;
//											handheap= 1;
//										}
//									}
									
									
									
									posx = posx + speed;
									posy = posy + speedver;
									if (posx <= 0) {
										posx = 0;
									}
									if (posx >= (GameUI.screenWidth - W)) {
										posx = GameUI.screenWidth - W;
									}
									if (posy <= 0) {
										posy = 0;
									}
									if (posy >= GameUI.screenHeight - H) {
										posy = GameUI.screenHeight - H;
									}
								
										
//										int dx = Math.abs(pointX-posx);
//										int dy = Math.abs(pointY-posy);
//										if (dx<W&&dy<H) {
//											if( isLeft&&posx<pointX||isLeft!=false&&posx>pointX)
//												posx = posx +0;
//											if (isUpper&&posy<pointY||isUpper!=false&posy>pointY) {
//												posy = posy+0;
//											}	
//										}
//										
									if (isStand) {
										c.save();
										c.clipRect(posx, posy, posx + W, posy
												+ H);
										c.drawBitmap(remi2, posx - W * frames,
												posy, mPaint);
										c.restore();
									} else  {
										c.save();
										c.clipRect(posx, posy, posx + W, posy
												+ H);
										c.drawBitmap(remiw, posx - W * frame,
												posy - heap, mPaint);
										c.restore();

									}
									if (frame < 3) {
										frame++;
									} else {
										frame = 0;
									}
									if (frames < 3) {
										frames++;
									} else {
										frames = 0;
									}
								}
							}
						}//pause
						break;
					case GAMEOVER:
						DataProvider.saveInt(context, "losetimes", losetimes);
						mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						c.drawBitmap(continuebt, GameUI.screenWidth - 129,
								GameUI.screenHeight - 74, mPaint);
						if(!continuebtstate){
							c.drawBitmap(door, doorx, doory, mPaint);
							 str = "timeleft:"+showtime;
								mPaint.setTextSize(40);//设置字体大小
								mPaint.setColor(Color.BLUE);
								c.drawText(str,20,30, mPaint);
								drawpit(c);
								c.drawBitmap(losebg, GameUI.screenWidth/2 - losebg.getWidth()/2,
										GameUI.screenHeight/2 - losebg.getHeight()/2, mPaint);
							
						}

						else{
							mPaint.setColor(Color.BLACK);
							c.drawRect(0, 0, GameUI.screenWidth,
									GameUI.screenHeight, mPaint);		
							c.drawBitmap(scoreboard, GameUI.screenWidth/2 - scoreboard.getWidth()/2,
									GameUI.screenHeight/2 - scoreboard.getHeight()/2, mPaint);
							c.drawBitmap(replaywd,   scoreboard.getWidth()/2 - replaywd.getWidth()/2,400, mPaint);
							c.drawBitmap(yesbt, 300,550, mPaint);
							c.drawBitmap(nobt, 50,550, mPaint);
							c.drawBitmap(winwd, 80, 200, mPaint);
							c.drawBitmap(losewd, 80, 300, mPaint);
							str = losetimes+"";
							
								mPaint.setTextSize(60);//设置字体大小
								mPaint.setColor(Color.YELLOW);
								c.drawText(str,GameUI.screenWidth/2+30 ,360, mPaint);
							str = wintimes+"";
								mPaint.setTextSize(60);//设置字体大小
								mPaint.setColor(Color.YELLOW);
								c.drawText(str,GameUI.screenWidth/2+30 ,260, mPaint);
							
						}

						break;
					case WINGAME:

						DataProvider.saveInt(context, "wintimes", wintimes);
						if(!continuebtstate){
							mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						c.drawBitmap(door, doorx, doory, mPaint);
						drawpit(c);
						c.drawBitmap(winbg, GameUI.screenWidth/2 - winbg.getWidth()/2,
									GameUI.screenHeight/2 - winbg.getHeight()/2, mPaint);
						c.drawBitmap(continuebt, GameUI.screenWidth - 129,
								GameUI.screenHeight - 74, mPaint);
						}
						else {
							mPaint.setColor(Color.WHITE);
							c.drawRect(0, 0, GameUI.screenWidth,
									GameUI.screenHeight, mPaint);
							c.drawBitmap(winbg, GameUI.screenWidth/2 - winbg.getWidth()/2,
									GameUI.screenHeight/2 - winbg.getHeight()/2, mPaint);
							c.drawBitmap(scoreboard, GameUI.screenWidth/2 - scoreboard.getWidth()/2,
									GameUI.screenHeight/2 - scoreboard.getHeight()/2, mPaint);
							c.drawBitmap(winwd, 80, 200, mPaint);
							c.drawBitmap(losewd, 80, 300, mPaint);
							c.drawBitmap(replaywd,scoreboard.getWidth()/2 - replaywd.getWidth()/2,400, mPaint);
								c.drawBitmap(yesbt, 300,550, mPaint);
								c.drawBitmap(nobt, 50,550, mPaint);
								str = losetimes+"";
								
								mPaint.setTextSize(60);//设置字体大小
								mPaint.setColor(Color.YELLOW);
								c.drawText(str,GameUI.screenWidth/2+30 ,360, mPaint);
							str = wintimes+"";
								mPaint.setTextSize(60);//设置字体大小
								mPaint.setColor(Color.YELLOW);
								c.drawText(str,GameUI.screenWidth/2+30 ,260, mPaint);
							
						}
						System.out.println("win");
						break;
					case PAUSE:
						mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						c.drawBitmap(pausebg,GameUI.screenWidth/2 - pausebg.getWidth()/2,
								GameUI.screenHeight/2 - pausebg.getHeight()/2, mPaint);
						c.drawBitmap(resumebt, GameUI.screenWidth - 88,0, mPaint);
						
					
						break;
					case CHOOSELEVEL:
						
						mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
//						c.drawBitmap(choosestagebg,  GameUI.screenWidth/2 - choosestagebg.getWidth()/2,
//								GameUI.screenHeight/2 - choosestagebg.getHeight()/2, mPaint);
						c.drawBitmap(menubg, GameUI.screenWidth/2 - menubg.getWidth()/2,
								GameUI.screenHeight/2 - menubg.getHeight()/2, mPaint);
						c.drawBitmap(choosestagewd,  GameUI.screenWidth/2 - choosestagewd.getWidth()/2,
								100, mPaint);
						c.drawBitmap(easybt, GameUI.screenWidth/2 -easybt.getWidth()/2 , 300, mPaint);
						c.drawBitmap(normbt, GameUI.screenWidth/2 -normbt.getWidth()/2, 450, mPaint);
						c.drawBitmap(hardbt, GameUI.screenWidth/2 -hardbt.getWidth()/2, 600, mPaint);
						
						break;
					case LOGO:
						losebg = ImageUtils.getImageFromAssetsFile("losebg.png", am);
						loadingbg = ImageUtils.getImageFromAssetsFile("loading.png", am);
						pausebg =  ImageUtils.getImageFromAssetsFile("pause.png", am);
						
						scoreboard = ImageUtils.getImageFromAssetsFile("scoreboard.png", am);
						yesbt= ImageUtils.getImageFromAssetsFile("yes.png", am);
						nobt= ImageUtils.getImageFromAssetsFile("no.png", am);
						
						resumebt = ImageUtils.getImageFromAssetsFile("resumebt.png", am);
						winwd = ImageUtils.getImageFromAssetsFile("win.png", am);
						losewd = ImageUtils.getImageFromAssetsFile("lose.png", am);
						replaywd = ImageUtils.getImageFromAssetsFile("replay.png", am);	
						remiw = ImageUtils.getImageFromAssetsFile("remi.png", am);
						door = ImageUtils.getImageFromAssetsFile("doortemp.jpg", am);
//						hand = ImageUtils.getImageFromAssetsFile("hand.png", am);
						pitimg = ImageUtils.getImageFromAssetsFile("pittemp.jpg", am);
						remi2 = ImageUtils.getImageFromAssetsFile("remi2.png", am);
						number = ImageUtils.getImageFromAssetsFile("number.png", am);
						int loadingcount = ttick--/10;
						
						if (loadingcount <0)
							setState(INIT);
						mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						c.save();
						c.clipRect( GameUI.screenWidth/2 - loadingbg.getWidth()/20,
								GameUI.screenHeight/2 - loadingbg.getHeight()/2,
								GameUI.screenHeight/2 - loadingbg.getHeight()/2 + loadingbg.getWidth()/10, 
								GameUI.screenHeight/2 - loadingbg.getHeight()/2 + loadingbg.getHeight());
						c.drawBitmap(loadingbg, GameUI.screenWidth/2 - loadingbg.getWidth()/20 - 384*framelogo,
								GameUI.screenHeight/2 - loadingbg.getHeight()/2, mPaint);
						c.restore();
						if (framelogo < 9) {
							framelogo++;
						} else {
							framelogo = 0;
						}
					
						break;

					case MENU:
					
						if(!mainmenubtstate){
								mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,
								GameUI.screenHeight, mPaint);
						c.drawBitmap(menubg, GameUI.screenWidth/2 - menubg.getWidth()/2,
								GameUI.screenHeight/2 - menubg.getHeight()/2, mPaint);
						c.drawBitmap(mainmenubt, 0,0, mPaint);
						c.drawBitmap(helpbt, 0,65, mPaint);
						
						}else {
							
							c.drawBitmap(newgamebt,0,65,mPaint);
							c.drawBitmap(continuegamebt,0,130,mPaint);
							c.drawBitmap(settingbt,0,195,mPaint);
							c.drawBitmap(helpbt, 0,740, mPaint);
						}

						break;
					case HELP:
						c.drawBitmap(helpbt, 400, 400, mPaint);
						break;
					case WELCOME:
					
						c.save();
						c.clipRect(0,0, 480,800);
						c.drawBitmap(welcombg, 0-480*frame,0, mPaint);
						c.restore();
						if (frame < 2) {
							frame++;
						} else {
							frame = 0;
						}
						if(welcomeclick){
							 mSoundManager.stopBgm(1);
							setState(MENU);
						}
							
						break;
					case SETTING:
						mPaint.setColor(Color.WHITE);
						c.drawRect(0, 0, GameUI.screenWidth,GameUI.screenHeight, mPaint);
						c.drawBitmap(optionbg, 0,GameUI.screenHeight/2 - optionbg.getHeight()/2, mPaint);
						c.drawBitmap(soundebt, GameUI.screenWidth/2 - soundebt.getWidth()/2, 200, mPaint);
						c.drawBitmap(musicbt, GameUI.screenWidth/2 - musicbt.getWidth()/2, 300, mPaint);
						c.drawBitmap(modebt, GameUI.screenWidth/2 - modebt.getWidth()/2, 400, mPaint);
						c.drawBitmap(crlbt, GameUI.screenWidth/2 - crlbt.getWidth()/2, 500, mPaint);
						c.drawBitmap(returnbt, 0, GameUI.screenHeight - returnbt.getHeight(), mPaint);
						
						if(soundebtstate){
							c.drawBitmap(sounde2bt, GameUI.screenWidth/2 - sounde2bt.getWidth()/2, 200, mPaint);
							}
						c.drawBitmap(music2bt, GameUI.screenWidth/2 - music2bt.getWidth()/2, 300, mPaint);
						c.drawBitmap(mode2bt, GameUI.screenWidth/2 - mode2bt.getWidth()/2, 400, mPaint);
						c.drawBitmap(crl2bt, GameUI.screenWidth/2 - crl2bt.getWidth()/2, 500, mPaint);
						
						break;

					}
					Thread.sleep(100);
				} catch (Exception e) {
					//
				} finally {
					if (c != null)
						holder.unlockCanvasAndPost(c);
				}
			}

		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointX = (int) event.getX();
		pointY = (int) event.getY();
		isStand = false;
		switch (event.getAction()) {
//		case MotionEvent.ACTION_MOVE:
//			break;
		case MotionEvent.ACTION_DOWN:

			switch (state) {
//			case SOUND:
////				mSoundManager.playSound(1);
//				
//				break;
			case INGAME:
				
				if (pointX < posx) {
					isLeft = true;
					heap = remiw.getHeight() / 4;
					speed = -20;
				}
				
				if (pointX > posx + remiw.getWidth()) {
					isLeft = false;
					heap = remiw.getHeight() / 4 * 2;
					speed = 20;
				}
				if (pointY < posy) {
					isUpper = true;
					heap = remiw.getHeight() / 4 * 3;
					speedver = -20;
				}
				if (pointY > posy + remiw.getHeight()) {
					isUpper = false;
					heap = 0;
					speedver = 20;
				}
				if (pointY > 0 && pointY < 60 && pointX > GameUI.screenWidth - 88) {//pause
					
					setState(PAUSE);
					
				}
				break;
			case PAUSE:
				if (pointY > 0 && pointY < 60 && pointX > GameUI.screenWidth - 88) {
					setState(INGAME);
					
				}
					
				break;
			case WINGAME:
				
				if( pointY > GameUI.screenHeight -  continuebt.getHeight()&& pointX > GameUI.screenWidth - continuebt.getWidth())
					continuebtstate = true;
				
				if(continuebtstate&&pointX>300&&pointX<yesbt.getWidth()+300&&pointY>550&&pointY<yesbt.getHeight()+550)
					setState(INIT);
				
				if(continuebtstate&&pointX>50&&pointX<yesbt.getWidth()+50&&pointY>550&&pointY<yesbt.getHeight()+550){
					setState(MENU);
				 mSoundManager.playSound(1);
				 }
				break;
			case GAMEOVER:

				
				if(pointY > GameUI.screenHeight -continuebt.getHeight()&& pointX > GameUI.screenWidth - continuebt.getWidth())
					continuebtstate = true;

					
				if(continuebtstate&&pointX>300&&pointX<yesbt.getWidth()+300&&pointY>550&&pointY<yesbt.getHeight()+550)
					setState(INIT);
				
				if(continuebtstate&&pointX>50&&pointX<yesbt.getWidth()+50&&pointY>550&&pointY<yesbt.getHeight()+550)
					setState(MENU);
				break;
			case MENU:
				if (mainmenubtstate) {

					if(pointX<mainmenubt.getWidth()&&pointY<mainmenubt.getHeight()){
						mainmenubtstate = false;	
						mSoundManager.playSound(1);
					}
						
						
				}else {
					if(pointX<mainmenubt.getWidth()&&pointY<mainmenubt.getHeight())
						mainmenubtstate = true;
				}
				
				if(mainmenubtstate&&pointX<newgamebt.getWidth()&&pointY>65&&pointY<125){//newgame
					wintimes = 0;
					losetimes = 0;
					setState(CHOOSELEVEL);	
					mSoundManager.playSound(1);
				
				}
				if(mainmenubtstate&&pointX<continuegamebt.getWidth()&&pointY>130&&pointY<190){//continuegame
					wintimes = DataProvider.getInt(context, "wintimes");
					losetimes = DataProvider.getInt(context, "losetimes");
					setState(CHOOSELEVEL);	
				}
					
				if(mainmenubtstate&&pointX<settingbt.getWidth()&&pointY>195&&pointY<255){
					setState(SETTING);
				mainmenubtstate = false;	
				mSoundManager.playSound(1);
				}
				break;
			case CHOOSELEVEL:
				if(pointX>167&&pointX<227&&pointY>300&&pointY<360){
					easybtstate = true;
					
					 setState(LOGO);
				}
					
				if(pointX>167&&pointX<227&&pointY>450&&pointY<510){
					normalbtstate = true;
					
					 setState(LOGO);
					}
				if(pointX>167&&pointX<227&&pointY>600&&pointY<660){
					hardbtstate = true;
					
					 setState(LOGO);
				}
				
					break;
			case WELCOME:
				welcomeclick = true;
				break;
		
			case SETTING:
				if(pointY> GameUI.screenHeight - returnbt.getHeight()&&pointX<returnbt.getWidth())
					setState(MENU);
//			
				if(soundebtstate){
					if(pointX>GameUI.screenWidth/2 - soundebt.getWidth()/2&&pointX<GameUI.screenWidth/2 + soundebt.getWidth()/2&&
							pointY>200&&pointY<200+soundebt.getHeight())
						soundebtstate = false;
				}else {
					if(pointX>GameUI.screenWidth/2 - soundebt.getWidth()/2&&pointX<GameUI.screenWidth/2 + soundebt.getWidth()/2&&
							pointY>200&&pointY<200+soundebt.getHeight())
						soundebtstate = true;
				}
				if(pointX>GameUI.screenWidth/2 - musicbt.getWidth()/2&&pointX<GameUI.screenWidth/2 + musicbt.getWidth()/2&&
						pointY>300&&pointY<300+musicbt.getHeight())
					musicbtstate = false;
				if(pointX>GameUI.screenWidth/2 - modebt.getWidth()/2&&pointX<GameUI.screenWidth/2 + modebt.getWidth()/2&&
						pointY>400&&pointY<400+modebt.getHeight())
					modebtstate = true;
				
				if(pointX>GameUI.screenWidth/2 - crlbt.getWidth()/2&&pointX<GameUI.screenWidth/2 + crlbt.getWidth()/2&&
						pointY>500&&pointY<500+crlbt.getHeight())
					crlbtstate = true;

	
				break;
			default:
				break;
			}

			return true;
		case MotionEvent.ACTION_UP:
			
			speed = 0;
			speedver = 0;
			if (state == INGAME) {
				if (pointY > posy + remiw.getHeight())
					isStand = true;
			} else {
				isStand = false;
			}
		}

		return true;
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
	}

	public boolean CollideJudge(int rectx, int recty, int rectW, int rectH,
			int charx, int chary, int charW, int charH) {
		if (rectx >= (charx - rectW) && rectx <= (charx + charW)
				&& recty >= (chary - rectH) && recty <= (chary + charH)) {
			isCollide = true;
		} else {
			isCollide = false;
		}
		return isCollide;
	}

	public boolean FallJudge(ArrayList<Pit> pitlist, int charx, int chary,
			int charW, int charH) {
		int i;
		boolean judgeflag = false;
		int posxt, posyt, pitwt, pitht;

		for (i = 0; i < pitlist.size(); i++) {

			posxt = pitlist.get(i).getPosx();
			posyt = pitlist.get(i).getPosy();
			pitwt = pitlist.get(i).getpitW();
			pitht = pitlist.get(i).getpitH();
			judgeflag = CollideJudge(posxt, posyt, pitwt, pitht, charx, chary,
					charW, charH);
			if (judgeflag) {
				pitlist.get(i).setShowpit(false);
				break;
			}
		}
		return judgeflag;
	}

	public boolean WinJudge(int doorxt, int dooryt, int doorWt, int doorHt,
			int charx, int chary, int charW, int charH) {
		boolean judgeflag;
		judgeflag = CollideJudge(doorxt, dooryt, doorWt, doorHt, charx, chary,
				charW, charH);
		return judgeflag;
	}

//	public ArrayList<Pit> randompit(int pitnum) {
//		int i;
//
//		do {
//			for (i = 0; i < pitnum; i++) {
//				Random r = new Random();
//				int posxseed = Math.abs(r.nextInt()% ((GameUI.screenWidth - W) / 3));
//				int posyseed = Math.abs(r.nextInt() % ((GameUI.screenHeight - H) / 3));
//				 int sizewseed = Math.abs(r.nextInt() % (GameUI.screenWidth/3-doorW));
//				 int sizehseed = Math.abs(r.nextInt() % (GameUI.screenHeight/3-doorH));
//	
//				pitposx = posxseed * (i + 1);
//				if (i < pitnum - 1) {
//					pitposy = doorH + posyseed * (i+1);
//					pitW = sizewseed + GameUI.screenWidth / 5;
//					pitH = sizehseed + GameUI.screenHeight / 5;
//				} else {
//					pitposy = posyseed * i - H;
//					pitW = sizewseed;
//					pitH = sizehseed;
//				}
//
//				pit = new Pit(pitposx, pitposy, pitW, pitH, true);
//				JudgePit(pit);
//				if (pit.isHassolu() ) {
//					pitlist.add(pit);
//				} else if (pit.isHassolu() == false) {
//					i--;
//				}
//				System.out.println(sizewseed + "seedsize");
//				System.out.println(sizehseed + "seedsizea");
//				System.out.println(posxseed + "seedx");
//				System.out.println(posyseed + "seedy");
//				System.out.println(pitposx + "pitposx");
//				System.out.println(pitposy + "pitposy");
//				System.out.println(pitW + "pitW");
//				System.out.println(pitH + "pitH");
//				System.out.println(pitH + pitposy + "pitok");
//			}
//		} while (JudgeSolution(pitlist) == false|| pitlist.size() < pitnum);
//		System.out.println(pitlist.size() + "size");
//		return pitlist;
//
//	}
//	public  void randompit(int pitnum) {
//		
//		for(int i = 0; i < pitnum; i++){
//			Random r = new Random();
////			int sizewseed  = GameUI.screenWidth/pitnum;
////			int sizehseed = GameUI.screenHeight/pitnum;
////			pitW = sizewseed ;
////			pitH = sizehseed ;
//			int sizewseed = Math.abs(r.nextInt()% (GameUI.screenWidth/pitnum*3/2));
//			int sizehseed = Math.abs(r.nextInt()% (GameUI.screenHeight/pitnum*3/2));
//			pitW = sizewseed + GameUI.screenWidth/pitnum;
//			pitH = sizehseed + GameUI.screenHeight/pitnum;
//			int posxseed = Math.abs(r.nextInt()% (GameUI.screenWidth  ));
//			int posyseed = Math.abs(r.nextInt() % (GameUI.screenHeight - H- doorH - pitH ));
//			pitposx = posxseed;
//			pitposy = doorH + posyseed;
//			pit = new Pit(pitposx, pitposy, pitW, pitH, true);
//			pitlist.add(pit);
//		}
//
//	}
public  void randompit(int pitnum) {
		
		for(int i = 0; i < pitnum; i++){
			Random r = new Random();
//			int sizewseed  = GameUI.screenWidth/pitnum;
//			int sizehseed = GameUI.screenHeight/pitnum;

			
			pitW =  GameUI.screenWidth/pitnum + GameUI.screenWidth/pitnum * Math.abs(r.nextInt()% 2);
			pitH =  GameUI.screenHeight/pitnum+ GameUI.screenHeight/pitnum * Math.abs(r.nextInt()% 2);
			
			int posxseed = Math.abs(r.nextInt()% (GameUI.screenWidth  ));
			int posyseed = Math.abs(r.nextInt() % (GameUI.screenHeight - H- doorH ));
//			
			
		
			pitposx =(int) Math.floor(posxseed) ;
			pitposy = doorH +  (int) (Math.floor(posyseed));
			if (pitposy>  GameUI.screenHeight/pitnum *(pitnum - 3) ) {
				pitH = - ( GameUI.screenHeight/pitnum+ GameUI.screenHeight/pitnum * Math.abs(r.nextInt()% 2 ) );
			} 
			if(pitposx>  GameUI.screenWidth/pitnum *(pitnum - 3))
			{
				pitW = - ( GameUI.screenWidth/pitnum+ GameUI.screenWidth/pitnum * Math.abs(r.nextInt()% 2 ) );
			} 
			System.out.println(pitposx+"pitposx");
			System.out.println(pitposy+"pitposy");
			pit = new Pit(pitposx, pitposy, pitW, pitH, true);
			pitlist.add(pit);
			
		}

	}

	public void drawpit(Canvas c) {
		int i;
		pit.setPitimg(pitimg);
		for (i = 0; i < pitlist.size(); i++) {
			int posxt, posyt, pitwt, pitht;
			posxt = pitlist.get(i).getPosx();
			posyt = pitlist.get(i).getPosy();
			pitwt = pitlist.get(i).getpitW();
			pitht = pitlist.get(i).getpitH();

			if (pitlist.get(i).isShowpit())
				c.save();
			c.clipRect(posxt, posyt, posxt + pitwt, posyt + pitht);
			c.drawBitmap(pitimg, posxt, posyt, mPaint);
			c.restore();

		}

	}
//
//	public boolean JudgeSolution(ArrayList<Pit> pitlist) {
//		int posxt, posyt, pitwt, pitht, sumx = 0, sumy = 0;
//		for (i = 0; i < pitlist.size(); i++) {
//			posxt = pitlist.get(i).getPosx();
//			posyt = pitlist.get(i).getPosy();
//			pitwt = pitlist.get(i).getpitW();
//			pitht = pitlist.get(i).getpitH();
//			sumx += pitwt;
//			sumy += pitht;
//			if (GameUI.screenWidth - sumx < 2 * W
//					&& GameUI.screenHeight - sumy < 2 * H) {
//				HasSolu = false;
//				pitlist.remove(i-1);
//
//			} else {
//				HasSolu = true;
//
//			}
//		}
//
//		return HasSolu;
//
//	}
	public boolean JudgeSolution(ArrayList<Pit> pitlist) {
		int posxt, posyt, pitwt, pitht, sumx = 0, sumy = 0;
		for (i = 0; i < pitlist.size(); i++) {
			posxt = pitlist.get(i).getPosx();
			posyt = pitlist.get(i).getPosy();
			pitwt = pitlist.get(i).getpitW();
			pitht = pitlist.get(i).getpitH();
			sumx += pitwt;
			sumy += pitht;
			if (GameUI.screenWidth - sumx < 2 * W){
				
			}
			if( GameUI.screenHeight - sumy < 2 * H) {
			

			} else {
				HasSolu = true;

			}
		}

		return HasSolu;

	}
	public void JudgePit(Pit pit) {
		int posxt, posyt, pitwt, pitht;
		posxt = pit.getPosx();
		posyt = pit.getPosy();
		pitwt = pit.getpitW();
		pitht = pit.getpitH();
		if ((posyt + pitht) > GameUI.screenHeight - H*2 || posyt < doorH
				|| pitwt < W|| pitht < H
		// &&doorx<posxt&&posxt<doorx+doorW&&doory<posyt&&posyt<doory+doorH
		// &&posx<posxt&&posxt<posx+W&&posy<posyt&&posyt<posy+H
		) {
			pit.setHassolu(false);
		} else {
			pit.setHassolu(true);
		}

	}
}
