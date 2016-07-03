package com.jic.basegol.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import com.jic.basegol.fragment.ClasificacionFragment;
import com.jic.basegol.fragment.ResultadosFragment;
 
public class ResultadosPagerAdapter extends FragmentPagerAdapter {
 
	public static ResultadosFragment fgResultados;
	public static ClasificacionFragment fgClasificacion;
	public static ResultadosFragment fgCalendario;
	
    public ResultadosPagerAdapter(FragmentManager fm) {
    	super(fm);
    	
    	fgResultados = new ResultadosFragment();
    	fgClasificacion = new ClasificacionFragment();
    	fgCalendario = new ResultadosFragment();
    }
 
    @Override
    public ListFragment getItem(int index) {
    	
        switch (index) {
        case 0:
            // Favoritos
            return fgResultados;
        case 1:
            // Futbol
        	return fgClasificacion;
        case 2:
            // Favoritos
            return fgCalendario;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}