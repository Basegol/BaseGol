package com.jic.basegol.util;

import java.util.HashMap;

import android.graphics.Bitmap;

public class CacheImage 
{

	public static HashMap<String, Bitmap> cache;
	
	static
	{
		if (cache == null){cache = new HashMap<String,Bitmap>();}
	}
	
}
