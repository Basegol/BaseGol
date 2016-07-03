package com.jic.basegol.util;

import android.content.Context;
import android.graphics.Typeface;

public class Fuentes 
{
	
	public static Typeface digitalScore;
	
	public static void cargaFuentes(Context context)
	{
		digitalScore = Typeface.createFromAsset(context.getAssets(), "fonts/digital_score.ttf");
	}

	
}
