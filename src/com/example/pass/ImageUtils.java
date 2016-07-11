package com.example.pass;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

public class ImageUtils {

	public static Bitmap getImageFromAssetsFile(String fileName, AssetManager am){  
	      Bitmap image = null;  
	      try  
	      {  
	          InputStream is = am.open(fileName);  
	          BitmapFactory.Options opt = new BitmapFactory.Options();
	          opt.inPreferredConfig = Bitmap.Config.RGB_565;   
	          opt.inPurgeable = true;  
	          opt.inInputShareable = true;  
	          image = BitmapFactory.decodeStream(is, null, opt); 
//	          image = BitmapFactory.decodeStream(is);
	          is.close();  
	      }  
	      catch (IOException e)  
	      {  
	          e.printStackTrace();  
	      }  
	  
	      return image;  	  
	} 
}
