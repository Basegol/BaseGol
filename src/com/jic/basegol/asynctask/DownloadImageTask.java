package com.jic.basegol.asynctask;

import java.io.InputStream;
import java.util.HashMap;

import com.jic.basegol.util.CacheImage;
import com.jic.basegol.util.Preferencias;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

	ImageView bmImage;

	public DownloadImageTask(ImageView bmImage) {
		this.bmImage = bmImage;
	}

	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
		Bitmap mIcon11 = null;

		if(CacheImage.cache.get(urldisplay)!=null)
		{
			mIcon11 = CacheImage.cache.get(urldisplay);
		}else{
			try 
			{
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				if(Preferencias.BG_DEBUG_MODE){Log.e("Error", e.getMessage());}
			}
			CacheImage.cache.put(urldisplay, mIcon11);
		}
		return mIcon11;
	}

	protected void onPostExecute(Bitmap result) {
		bmImage.setImageBitmap(result);
	}

}
