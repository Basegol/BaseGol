package com.jic.basegol.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.jic.basegol.R;
import com.jic.basegol.adapter.KeyValueAdapter;
import com.jic.basegol.listener.RegionSpinnerListener;
import com.jic.basegol.ws.BGUtil;

public class Utiles {

	
	
	public static String POD = "";
	
	public static int getImgComunidad(long id)
	{	
		int result = 0;
		
		if(id == 1){return R.drawable.ic_flag_of_andalucia;}
		if(id == 2){return R.drawable.ic_flag_of_aragon;}
		if(id == 3){return R.drawable.ic_flag_of_cantabria;}
		if(id == 4){return R.drawable.ic_flag_of_castile_and_leon;}
		if(id == 5){return R.drawable.ic_flag_of_castile_la_mancha;}
		if(id == 6){return R.drawable.ic_flag_of_catalonia;}
		if(id == 7){return R.drawable.ic_flag_of_ceuta;}
		if(id == 8){return R.drawable.ic_flag_of_madrid;}
		if(id == 9){return R.drawable.ic_flag_of_the_valencian;}
		if(id == 10){return R.drawable.ic_flag_of_extremadura_with_coa;}
		if(id == 11){return R.drawable.ic_flag_of_galicia;}
		if(id == 12){return R.drawable.ic_flag_of_the_balearic_islands;}
		if(id == 13){return R.drawable.ic_flag_of_the_canary_islands;}
		if(id == 14){return R.drawable.ic_flag_of_la_rioja;}
		if(id == 15){return R.drawable.ic_flag_of_melilla;}
		if(id == 16){return R.drawable.ic_flag_of_navarra;}
		if(id == 17){return R.drawable.ic_flag_of_the_basque_country;}
		if(id == 18){return R.drawable.ic_flag_of_asturias;}
		if(id == 19){return R.drawable.ic_flag_of_murcia;}
		
		return result;
	}
	
	
	public static boolean hasValue(String s)
	{
		if (s==null || "".equalsIgnoreCase(s.trim())){return false;}else{return true;}
	}	
	
	/**
	 * Devuelve un color de Android seg??n el color obtenido mediante el WS
	 * @param colorWS
	 * @return
	 */
	public static int getColor(String colorWS)
	{
		if("1".equals(colorWS))
		{
			return Color.BLUE;
		}
		

		return Color.GRAY;
	}
	
	public static void initSpinnerComunidades(Activity activity) {
		// SE CARGA EL SPINNER
        try
        {
        	Spinner spinner = (Spinner) activity.findViewById(R.id.spinner_region);
        	spinner.setOnItemSelectedListener(new RegionSpinnerListener(activity));

          
            final LinkedHashMap<String, String> spinnerHash = new LinkedHashMap<String, String>();
            String[] entries = activity.getResources().getStringArray(R.array.setting_entryvalues_list_region);
            String[] values = activity.getResources().getStringArray(R.array.setting_entries_list_region);
           
           for(int i = 0; i < entries.length; i ++)
           {
        	   spinnerHash.put(entries[i], values[i]);
           }

            final KeyValueAdapter adapter = new KeyValueAdapter(activity, android.R.layout.simple_spinner_item, spinnerHash);
            spinner.setAdapter(adapter);
            spinner.setSelection(Integer.parseInt(Preferencias.getSpinnerComunidadId()));
        }
        catch (final Exception e)
        {
        	if(Preferencias.BG_DEBUG_MODE){Log.e("initSpinnerComunidades", "onCreate() Exception: " + e.toString());}
        }
	}
	
	public static void initSpinnerComunidades(Activity activity, float textSize, boolean isBold) {
		// SE CARGA EL SPINNER
        try
        {
        	Spinner spinner = (Spinner) activity.findViewById(R.id.spinner_region);
        	spinner.setOnItemSelectedListener(new RegionSpinnerListener(activity));

          
            final LinkedHashMap<String, String> spinnerHash = new LinkedHashMap<String, String>();
            String[] entries = activity.getResources().getStringArray(R.array.setting_entryvalues_list_region);
            String[] values = activity.getResources().getStringArray(R.array.setting_entries_list_region);
           
           for(int i = 0; i < entries.length; i ++)
           {
        	   spinnerHash.put(entries[i], values[i]);
           }

            final KeyValueAdapter adapter = new KeyValueAdapter(activity, android.R.layout.simple_spinner_item, spinnerHash,null,textSize, isBold);
            spinner.setAdapter(adapter);
            spinner.setSelection(Integer.parseInt(Preferencias.getSpinnerComunidadId()));
        }
        catch (final Exception e)
        {
        	if(Preferencias.BG_DEBUG_MODE){Log.e("initSpinnerComunidades", "onCreate() Exception: " + e.toString());}
        }
	}
	
	public static void initSpinnerTipoPartido(Activity activity) {
		// SE CARGA EL SPINNER
        try
        {
        	Spinner spinner = (Spinner) activity.findViewById(R.id.spinner_tipoPartido);
        	//spinner.setOnItemSelectedListener(new RegionSpinnerListener(activity));

          
            final LinkedHashMap<String, String> spinnerHash = new LinkedHashMap<String, String>();
            String[] entries = activity.getResources().getStringArray(R.array.setting_entryvalues_tipo_partido);
            String[] values = activity.getResources().getStringArray(R.array.setting_entries_tipo_partido);
           
           for(int i = 0; i < entries.length; i ++)
           {
        	   spinnerHash.put(entries[i], values[i]);
           }

            final KeyValueAdapter adapter = new KeyValueAdapter(activity, android.R.layout.simple_spinner_item, spinnerHash);
            spinner.setAdapter(adapter);
            spinner.setSelection(Integer.parseInt("0"));
        }
        catch (final Exception e)
        {
        	if(Preferencias.BG_DEBUG_MODE){ Log.e("initSpinnerComunidades", "onCreate() Exception: " + e.toString());}
        }
	}
	
	public static void initSpinnerMarcadores(Activity activity) {
        try
        {
        	// Genera el adaptador
            final LinkedHashMap<String, String> spinnerHash = new LinkedHashMap<String, String>();
            String[] entries = activity.getResources().getStringArray(R.array.marcador_keys);
            String[] values = activity.getResources().getStringArray(R.array.marcador_keys);
           
           for(int i = 0; i < entries.length; i ++)
           {
        	   spinnerHash.put(entries[i], values[i]);
           }

            final KeyValueAdapter adapter = new KeyValueAdapter(activity, android.R.layout.simple_spinner_item, spinnerHash, Fuentes.digitalScore, 120f, false);
            
            // Inicializa los spinners
            //Spinner spinnerLoc = (Spinner) activity.findViewById(R.id.spinner_loc_marcador);
        	//Spinner spinnerVis = (Spinner) activity.findViewById(R.id.spinner_vis_marcador);
        	//spinner.setOnItemSelectedListener(new RegionSpinnerListener(activity));
            //spinnerLoc.setAdapter(adapter);
            //spinnerVis.setAdapter(adapter);
 
        }
        catch (final Exception e)
        {
        	if(Preferencias.BG_DEBUG_MODE){Log.e("initSpinnerMarcadores", "onCreate() Exception: " + e.toString());}
        }
	}
	
	 
	 /**
	  * Devuelve la posicion del GPS
	  * @param activity
	  * @return
	  */
	 public static Location getLocation(Activity activity){
	 
		LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		Location location = null;
		
    	if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  
	    	// Get the location manager
	        
	        // Define the criteria how to select the locatioin provider -> use
	        // default
	        Criteria criteria = new Criteria();
	        String provider = locationManager.getBestProvider(criteria, false);
	        location = locationManager.getLastKnownLocation(provider);
	        if(location == null)
	        {
	        	Toast toast = Toast.makeText(activity, "No se pudo conseguir las coordenadas del GPS", Toast.LENGTH_SHORT);
	        	toast.show();	        	
	        }

		 }else{
			 // check if enabled and if not send user to the GSP settings
			 // Better solution would be to display a dialog and suggesting to 
			 // go to the settings
			 Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			 activity.startActivity(intent);
	
		 }
    	
    	return location;

  }  
	 
	 public static int getColorFromRetorno(String tipoRetorno)
	 {
		 
		 if ("1".equalsIgnoreCase(tipoRetorno.trim()))
		 {
			 return  R.color.LightGreen;
		 }
		 
		 if ("2".equalsIgnoreCase(tipoRetorno.trim()))
		 {
			 return  R.color.LightYellow;
		 }
		 
		 if ("3".equalsIgnoreCase(tipoRetorno.trim()))
		 {
			 return  R.color.red;
		 }
		
		 return R.color.White;
	 }
	 
	 public static String getCodZonaComunidad()
	 {
		 // Devuelve +1 por la posici??n del array
		 return String.valueOf(Integer.valueOf(Preferencias.getSpinnerComunidadId())+1);
	 }
	 
	 
	 public static boolean isTemporal(String alias)
	 {
		 return (alias==null || "".equals(alias.trim()) || alias.startsWith("Temp") || alias.startsWith("temp"));
		 
	 }
	
}
