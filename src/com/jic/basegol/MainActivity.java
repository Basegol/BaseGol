package com.jic.basegol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jic.basegol.adapter.CategoriasPagerAdapter;
import com.jic.basegol.asynctask.UsuarioLoginTask;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Fuentes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.util.Utiles;

public class MainActivity extends ActionBarActivity  implements
ActionBar.TabListener {

	public static Context appContext;
	public static final String tag = "MainActivity";
    private ViewPager viewPager;
    private CategoriasPagerAdapter mAdapter;
    private ActionBar actionBar;
    private static MainActivity instance;
    
    private String[] tabs = { Constantes.TAB_FAVORITOS, Constantes.TAB_FUTBOL};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		boolean primeraEjecucion = false;
		
        super.onCreate(savedInstanceState);
        // Carga el layout
        setContentView(R.layout.activity_main);
        // Carga las preferencias
        Preferencias.cargaPreferencias(this);
        Fuentes.cargaFuentes(this);
        // Carga el spinner
        Utiles.initSpinnerComunidades(this);

        String codUsuario = Preferencias.getUsuarioCodigo();
        
        if (Constantes.COD_USUARIO_GENERICO.equals(codUsuario))
        {
        	// Se indica al usuario que seleccione una comunidad si es la primera vez que accede
        	primeraEjecucion = true;
        }
        
        new UsuarioLoginTask(this).execute("");
        
        // Inicialización de las Vistas
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));
        mAdapter = new CategoriasPagerAdapter(getSupportFragmentManager());
        // Pone las tabs en modo inicio
        CategoriasPagerAdapter.fgFavoritos.setInitMode(Constantes.INIT_MODE_1);
        CategoriasPagerAdapter.fgFutbol.setInitMode(Constantes.INIT_MODE_1);
        
        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);   
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);
        
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        
        // Pone las tabs en modo ejecucion
        CategoriasPagerAdapter.fgFavoritos.setInitMode(Constantes.INIT_MODE_0);
        CategoriasPagerAdapter.fgFutbol.setInitMode(Constantes.INIT_MODE_0);
        
        
        // Recoge los favoritos
        CategoriasPagerAdapter.fgFavoritos.actualizaFavoritos();
        
        if(primeraEjecucion)
        {
        	// Llama a la pantalla de eleccion de comunidad
            Intent primera = new Intent(this, PrimeraEjecucionActivity.class);
            this.startActivityForResult(primera,0);
        }

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        //default:
	            //return super.onOptionsItemSelected(item);
	        default:
	        	//onBackPressed();
	        	//finish();
	        	//Activity activity = this;
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setMessage("¿Quiere salir de BaseGol?")
	        	   .setCancelable(false)
	        	   .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
	        	       public void onClick(DialogInterface dialog, int id) {
	        	   
	        	    	   dialog.dismiss();
	        	    	   dialog.cancel();
	        	    	   finish();
	        	       }
	        	   })
	        	   .setNegativeButton("No", new DialogInterface.OnClickListener() {
	        	       public void onClick(DialogInterface dialog, int id) {
	        	            dialog.cancel();
	        	       }
	        	   });
	        	AlertDialog alert = builder.create();
	        	alert.show();
	        	
	      	   	return true;
	    }
	}
	

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		int position = tab.getPosition();
        viewPager.setCurrentItem(position);	
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
		int position = tab.getPosition();
        viewPager.setCurrentItem(position);
        
        // FAVORITOS
        if(position == 0 )
        {
        	CategoriasPagerAdapter.fgFavoritos.actualizaFavoritos();
        }
        
        // FUTBOL
        if (position == 1)
        {
        	CategoriasPagerAdapter.fgFutbol.actualizaCategorias();
        }
                
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	}
	
	
	public static MainActivity getInstance()
	{
		return instance;
	}
	
}
