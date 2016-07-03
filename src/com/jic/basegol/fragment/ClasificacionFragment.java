package com.jic.basegol.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jic.basegol.R;
import com.jic.basegol.ResultadosActivity;
import com.jic.basegol.adapter.ClasificacionAdapter;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.beans.BGCategoria;
import com.jic.basegol.ws.beans.BGClasificacion;


public class ClasificacionFragment extends ListFragment implements OnItemClickListener{

	private String codCategoria;
	private ClasificacionAdapter m_adapter;
	private String tag = "ClasificacionFragment";


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Evita que al girar la pantalla se pierdan todos los datos
		this.setRetainInstance(true);
		new ClasificacionTask().execute("");
	}


	private ArrayList<BGClasificacion> getClasificacion() {

		ArrayList<BGClasificacion> resultados = BGConector.getClasificacion(getCodCategoria());
		return resultados;	
	}

	public void actualizaClasificacion()
	{
		if (getActivity()!=null)
		{
			new ClasificacionTask().execute("");
		}

	}


	private class ClasificacionTask extends AsyncTask<String, Void, ArrayList<BGClasificacion>> {

		ArrayList<BGClasificacion> temparrlist = new ArrayList<BGClasificacion>();

		protected void onPreExecute(){

		}

		protected ArrayList<BGClasificacion> doInBackground(String... connection) {
			temparrlist= getClasificacion();
			return temparrlist;
		}

		protected void onPostExecute(ArrayList<BGClasificacion> result) {

			try{
				if (result!=null)
				{
					m_adapter = new ClasificacionAdapter(getActivity(), R.layout.row_clasificacion, result);
					setListAdapter(m_adapter);
				}
				else
				{
					if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo clasificacion con codCategoria=" + getCodCategoria());}
				}

			}catch(Exception e){if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo clasificacion con codCategoria=" + getCodCategoria());	}}

		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		/*BGClasificacion r = (BGClasificacion) getListAdapter().getItem(position);

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
        startActivity(intent);
		 */
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}    

	public String getCodCategoria() {
		return codCategoria;
	}


	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}


}
