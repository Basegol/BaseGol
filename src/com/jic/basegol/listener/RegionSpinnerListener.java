package com.jic.basegol.listener;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jic.basegol.R;
import com.jic.basegol.adapter.CategoriasPagerAdapter;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.util.Utiles;

public class RegionSpinnerListener
    implements OnItemSelectedListener
{
	
	String tag = "RegionSpinnerListener";
	private Activity mActivity;
	
	public RegionSpinnerListener(Activity activity)
	{
		this.mActivity = activity;
		
	}
	

    public void onItemSelected(AdapterView<?> parent, View view, int position, long idRegion)
    {
        try
        {
        	Spinner spinner = (Spinner) parent.findViewById(R.id.spinner_region);

            //camKey is the long returned from getItemId()
            if (idRegion < 0)
            {
            	if(Preferencias.BG_DEBUG_MODE){Log.w(tag, "onItemSelected() "+idRegion+" is invalid");}
                return;
            }

            //get object for the position
            final String region = (String) spinner.getAdapter().getItem(position);

            if (region == null)
            {
                //Log.w(tag, "onItemSelected() camera source is null");
                return;
            }
            
            ImageView imgRegion = (ImageView)mActivity.findViewById(R.id.imgRegion);
            imgRegion.setImageResource(Utiles.getImgComunidad(idRegion));
            
            // Actualiza las preferencias con position ya que es la posicion del spinner, no el idRegion
            Preferencias.setSpinnerComunidadId(String.valueOf(position));
            
            CategoriasPagerAdapter.fgFutbol.setZona(region);
            CategoriasPagerAdapter.fgFutbol.actualizaCategorias();
   
        }
        catch (final Exception e)
        {
            Log.e(tag, "onItemSelected() Exception: " + e.toString());
        }
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
}