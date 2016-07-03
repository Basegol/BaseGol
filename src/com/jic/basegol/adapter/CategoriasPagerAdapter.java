package com.jic.basegol.adapter;

import com.jic.basegol.fragment.CategoriasFragment;
import com.jic.basegol.fragment.FavoritosFragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
 
public class CategoriasPagerAdapter extends FragmentPagerAdapter {
 
	public static FavoritosFragment  fgFavoritos;
	public static CategoriasFragment fgFutbol;
	
	
	
    public CategoriasPagerAdapter(FragmentManager fm) {
    	super(fm);
    	
    	fgFavoritos = new FavoritosFragment();
    	
    	fgFutbol = new CategoriasFragment();
    	fgFutbol.setDeporte("FUTBOL");
    	fgFutbol.setPais("ESPAÑA");
    	fgFutbol.setZona("ARAGÓN");
    	
    }
 
    @Override
    public ListFragment getItem(int index) {
    	
        switch (index) {
        case 0:
        	return fgFavoritos;
        case 1:
        	return fgFutbol;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
    
 
}