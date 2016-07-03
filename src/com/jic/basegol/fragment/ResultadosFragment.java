package com.jic.basegol.fragment;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jic.basegol.R;
import com.jic.basegol.DetalleActivity;
import com.jic.basegol.adapter.ResultadosAdapter;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.beans.BGResultadoReducido;

public class ResultadosFragment extends ListFragment implements OnItemClickListener{

	private String deporte;
	private String pais;
	private String zona;
	private String codCategoria;
	private String litCategoria;
	private String numJornada;


	private ResultadosAdapter m_adapter;
	ProgressDialog mProgressDialog = null;
	private String tag = "CategoriasFragment";


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		// Evita que al girar la pantalla se pierdan todos los datos
		this.setRetainInstance(true);
		new ResultadosTask().execute("");

	}

	@Override
	public void onSaveInstanceState(Bundle icicle) {
		  super.onSaveInstanceState(icicle);
	}
	
	
	private ArrayList<BGResultadoReducido> getResultados() {

		ArrayList<BGResultadoReducido> resultados = BGConector.getResultadoPartidosReducido(getCodCategoria(), getNumJornada(), getDeporte());
		return resultados;	
	}

	public void actualizaResultados()
	{
		if (getActivity()!=null)
		{
			new ResultadosTask().execute("");
		}

	}

	private class ResultadosTask extends AsyncTask<String, Void, ArrayList<BGResultadoReducido>> {
		ArrayList<BGResultadoReducido> temparrlist = new ArrayList<BGResultadoReducido>();

		ProgressDialog mProgressDialog = null;

		protected void onPreExecute(){
			//mProgressDialog = ProgressDialog.show(getActivity(),"Por favor espere", "Cargando resultados ...", true);
		}

		protected ArrayList<BGResultadoReducido> doInBackground(String... connection) {
			temparrlist= getResultados();
			return temparrlist;
		}

		protected void onPostExecute(ArrayList<BGResultadoReducido> result) {

			try{
				if(result!=null){
					m_adapter = new ResultadosAdapter(getActivity(), R.layout.row_resultado, result);
					//TextView tvCategoria = (TextView)getActivity().findViewById(R.id.headerCategoria);
					//tvCategoria.setText(getLitCategoria());
					//getListView().addHeaderView(tvCategoria);
					setListAdapter(m_adapter);
					//mProgressDialog.dismiss();
				}else{
					if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo resultados con codCategoria=" + getCodCategoria() + ";jornada="+getNumJornada()+";deporte="+getDeporte());}	
				}
			}catch(Exception e){if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo resultados con codCategoria=" + getCodCategoria() + ";jornada="+getNumJornada()+";deporte="+getDeporte());	}}

		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		BGResultadoReducido r = (BGResultadoReducido) getListAdapter().getItem(position);

		String tipoFila = r.getTipoFila();

		if(Constantes.TIPO_FILA_0.equalsIgnoreCase(tipoFila))
		{
			Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
			intent.putExtra(Constantes.PARAM_COD_LOCAL,r.getCodLoc());
			intent.putExtra(Constantes.PARAM_COD_VISITANTE,r.getCodVis());
			intent.putExtra(Constantes.PARAM_COD_PARTIDO,r.getCodPart());
			intent.putExtra(Constantes.PARAM_LIT_LOCAL,r.getLitLoc());
			intent.putExtra(Constantes.PARAM_LIT_VISITANTE,r.getLitVis());
			intent.putExtra(Constantes.PARAM_MARCADOR_LOCAL,r.getTanLoc());
			intent.putExtra(Constantes.PARAM_MARCADOR_VISITANTE,r.getTanVis());
			intent.putExtra(Constantes.PARAM_LIT_ESTADO_PARTIDO, r.getLitEstado());
			intent.putExtra(Constantes.PARAM_IS_MOD, r.getIsMod());
			intent.putExtra(Constantes.PARAM_DESC_DEPORTE, r.getLitDeporte());
			intent.putExtra(Constantes.PARAM_TIPO_PARTIDO_CODIGO, r.getCodParTip());
			
			startActivity(intent);
		}

	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}


	public String getDeporte() {
		return deporte;
	}


	public void setDeporte(String deporte) {
		
		this.deporte = deporte;
	}


	public String getZona() {
		return zona;
	}


	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodCategoria() {
		return codCategoria;
	}


	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}


	public String getLitCategoria() {
		return litCategoria;
	}


	public void setLitCategoria(String litCategoria) {
		this.litCategoria = litCategoria;
	}


	public String getNumJornada() {
		return numJornada;
	}


	public void setNumJornada(String numJornada) {
		this.numJornada = numJornada;
	}

}
