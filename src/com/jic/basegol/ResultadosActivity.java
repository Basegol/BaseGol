package com.jic.basegol;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jic.basegol.adapter.ResultadosPagerAdapter;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Utiles;


public class ResultadosActivity extends ActionBarActivity implements
ActionBar.TabListener {

	public static Context appContext;
	public static final String tag = "ResultadosActivity";
    private ViewPager viewPager;
    private ResultadosPagerAdapter mAdapter;
    private ActionBar actionBar;
    private String codCategoria;
    private String litCategoria;
    private String tipoPantalla;
    private boolean isNotificacion;
    
    
    private String[] tabs = { Constantes.TAB_RESULTADOS, Constantes.TAB_CLASIFICACION, Constantes.TAB_CALENDARIO };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carga el layout
        setContentView(R.layout.activity_resultados);
        // Carga las preferencias (ya se han cargado en el MainActivity)
        //Preferencias.cargaPreferencias(this);
        // Carga el spinner
        //Utiles.initSpinnerComunidades(this);
 
        // Inicializaci??n de las Vistas
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new ResultadosPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);
        
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        
        isNotificacion = false;
        
        // Parametros extra
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
        	
            codCategoria = bundle.getString(Constantes.PARAM_ID_CATEGORIA);
            litCategoria = bundle.getString(Constantes.PARAM_LIT_CATEGORIA);
            tipoPantalla = bundle.getString(Constantes.PARAM_TIPO_PANTALLA);
            
            if(Constantes.PARAM_IS_NOTIFICACION_Y.equals(bundle.getString(Constantes.PARAM_IS_NOTIFICACION))){
            	isNotificacion = true;
            }
        }
        
        TextView tvCategoria = (TextView)this.findViewById(R.id.headerResultado);
        tvCategoria.setText(litCategoria);
        
        
        ResultadosPagerAdapter.fgResultados.setCodCategoria(codCategoria);
    	ResultadosPagerAdapter.fgResultados.setLitCategoria(litCategoria);
    	ResultadosPagerAdapter.fgClasificacion.setCodCategoria(codCategoria);
        ResultadosPagerAdapter.fgCalendario.setCodCategoria(codCategoria);
    	ResultadosPagerAdapter.fgCalendario.setLitCategoria(litCategoria);
    	ResultadosPagerAdapter.fgCalendario.setNumJornada(Constantes.NUM_JORNADA_CERO);
    	
        
        
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
                
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        default:
	        	onBackPressed();
	      	   	return true;
	            //return super.onOptionsItemSelected(item);
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
        
        // RESULTADOS
        if(position == 0 )
        {
        	ResultadosPagerAdapter.fgResultados.actualizaResultados();
        	
        }
        
        // CLASIFICACION
        if (position == 1)
        {
        	ResultadosPagerAdapter.fgClasificacion.actualizaClasificacion();
        }
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBackPressed() {
	
		if(isNotificacion)
		{
			   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			   //setIntent.addCategory(Intent.CATEGORY_HOME);
			   ActivityCompat.finishAffinity(this);
			   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
			   startActivity(intent);
			   finish();
		}else{
			super.onBackPressed();
		}

	}
	

}
