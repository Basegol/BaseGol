package com.jic.basegol;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jic.basegol.asynctask.DetallePartidoTask;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Fuentes;
import com.jic.basegol.util.Utiles;
import com.jic.basegol.ws.beans.BGDetallePartido;

public class DetalleActivity extends ActionBarActivity{
	
	ActionBar actionBar;
	private String codPartido;
	private BGDetallePartido partido;
	private String deporte;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.detail_resultado);
    	Utiles.initSpinnerTipoPartido(this);
        
        actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);
        
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {

        	String codPart = (String)bundle.get(Constantes.PARAM_COD_PARTIDO);
        	String isMod = (String)bundle.get(Constantes.PARAM_IS_MOD);
        	String litDeporte = (String)bundle.get(Constantes.PARAM_DESC_DEPORTE);
        	String litMarca = (String)bundle.get(Constantes.PARAM_LIT_ESTADO_PARTIDO);
        	String codLoc = (String)bundle.get(Constantes.PARAM_COD_LOCAL);
        	String codVis = (String)bundle.get(Constantes.PARAM_COD_VISITANTE);
        	String litLoc = (String)bundle.get(Constantes.PARAM_LIT_LOCAL);
        	String litVis = (String)bundle.get(Constantes.PARAM_LIT_VISITANTE);
        	String marcLoc = (String)bundle.get(Constantes.PARAM_MARCADOR_LOCAL);
        	String marcVis = (String)bundle.get(Constantes.PARAM_MARCADOR_VISITANTE);
        	String codParTip = (String)bundle.get(Constantes.PARAM_TIPO_PARTIDO_CODIGO);
        	
        	TextView tvEstadoPartido = (TextView)findViewById(R.id.detailLitEstadoPartido);
        	tvEstadoPartido.setText(litMarca);
        	TextView tvCodLoc = (TextView)findViewById(R.id.detailCodLocal);
        	tvCodLoc.setText(codLoc);
        	tvCodLoc.setVisibility(View.GONE);
        	TextView tvCodVis = (TextView)findViewById(R.id.detailCodVis);
        	tvCodVis.setText(codVis);
        	tvCodVis.setVisibility(View.GONE);
        	TextView tvCodPart = (TextView)findViewById(R.id.detailCodPar);
        	tvCodPart.setText(codPart);
        	tvCodPart.setVisibility(View.GONE);
        	TextView tvLitLoc = (TextView)findViewById(R.id.detailLitLocal);
        	tvLitLoc.setText(litLoc);
        	TextView tvLitVis = (TextView)findViewById(R.id.detailLitVis);
        	tvLitVis.setText(litVis);
        	
        	EditText tvLocMarcador = (EditText)findViewById(R.id.detailLocMarcador);
        	tvLocMarcador.setText(marcLoc);
        	tvLocMarcador.setTypeface(Fuentes.digitalScore);
        	EditText tvVisMarcador = (EditText)findViewById(R.id.detailVisMarcador);
        	tvVisMarcador.setText(marcVis);
        	tvVisMarcador.setTypeface(Fuentes.digitalScore);
        	TextView tvSeparador = (TextView)findViewById(R.id.cteSeparador);
        	tvSeparador.setText(Constantes.SEPARADOR_GUION);
        	tvSeparador.setTypeface(Fuentes.digitalScore);
        	
        	TextView codRetorno = ((TextView)findViewById(R.id.detailMsgRetorno));
        	codRetorno.setVisibility(View.GONE);
        	
        	Spinner spinner = ((Spinner)findViewById(R.id.spinner_tipoPartido));
        	// Se le resta uno para la posici??n del array
        	spinner.setSelection(Integer.valueOf(codParTip));
        	
        	partido = new BGDetallePartido();
        	this.deporte = litDeporte;
        	this.codPartido = codPart;
        	new DetallePartidoTask(this).execute("");

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
	        default:
	        	this.finish();
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public String getCodPartido()
	{
		return codPartido;
	}

	public BGDetallePartido getPartido() {
		return partido;
	}

	public void setPartido(BGDetallePartido partido) {
		this.partido = partido;
	}
	
	public String getDeporte() {
		return deporte;
	}
}
