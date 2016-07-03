package com.jic.basegol.fragment;

import java.util.ArrayList;


import android.app.ProgressDialog;
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
import com.jic.basegol.adapter.CategoriasAdapter;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.beans.BGCategoria;

public class CategoriasFragment extends ListFragment implements OnItemClickListener{

	private String deporte;
	private String pais;
	private String zona;
	// parametro que sirve para que cuando se inicia la aplicaci??n no carge la categor??a a trav??s
	// del webservice de cara a mejorar el rendimiento
	private Integer initMode;

	private CategoriasAdapter m_adapter;
	ProgressDialog mProgressDialog = null;
	private String tag = "CategoriasFragment";


	public CategoriasFragment() {
		super();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Evita que al girar la pantalla se pierdan todos los datos
		this.setRetainInstance(true);
		getActivity().getLayoutInflater().inflate(R.layout.list_categorias, null);

		//new CateogoriasTask().execute("");
	}


	private ArrayList<BGCategoria> getCategorias() {

		ArrayList<BGCategoria> categorias = BGConector.getCategorias(this.getDeporte(),this.getPais(),this.getZona());

		/*BGCategoria banner1 = new BGCategoria();
		banner1.setTipoFila("11");
		categorias.add(3, banner1);

		BGCategoria banner2 = new BGCategoria();
		banner2.setTipoFila("11");
		categorias.add(5, banner2);

		BGCategoria banner3 = new BGCategoria();
		banner3.setTipoFila("11");
		categorias.add(8, banner3);
		 */
		if (categorias == null || categorias.size() == 0)
		{
			categorias = new ArrayList<BGCategoria>();
			BGCategoria mto = new BGCategoria();
			mto.setTipoFila(Constantes.TIPO_FILA_1);
			mto.setLitFila(Constantes.MENSAJE_MANTENIMIENTO);
			categorias.add(mto);
		}

		return categorias;	
	}



	public void actualizaCategorias()
	{
		// Si hay actividad creada y no es el modo inicio entonces actualiza
		if (getActivity()!=null && getInitMode()!=1)
		{
			new CateogoriasTask().execute("");
		}

	}

	private class CateogoriasTask extends AsyncTask<String, Void, ArrayList<BGCategoria>> {
		ArrayList<BGCategoria> temparrlist = new ArrayList<BGCategoria>();

		ProgressDialog mProgressDialog = null;

		protected void onPreExecute(){
			//mProgressDialog = ProgressDialog.show(getActivity(),"Por favor espere", "Cargando categor??as ...", true);
		}

		protected ArrayList<BGCategoria> doInBackground(String... connection) {
			temparrlist = getCategorias();
			return temparrlist;
		}

		protected void onPostExecute(ArrayList<BGCategoria> result) {
			try{
				m_adapter = new CategoriasAdapter(getActivity(), R.layout.row_categoria, result);
				setListAdapter(m_adapter);	
			}catch(Exception e){
				if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo categorias con deporte=" + getDeporte() + ";pais=" + getPais() + ";zona="+getZona());}
				result = new ArrayList<BGCategoria>();
				BGCategoria mto = new BGCategoria();
				mto.setTipoFila(Constantes.TIPO_FILA_1);
				mto.setLitFila(Constantes.MENSAJE_MANTENIMIENTO);
				result.add(mto);
				try{
				m_adapter = new CategoriasAdapter(getActivity(), R.layout.row_categoria, result);
				setListAdapter(m_adapter);
				}catch(Exception ex){}
			}

			//mProgressDialog.dismiss();categorias
		}
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		String description = (String)v.getContentDescription();

		if(v.getId() == R.id.imgFavCategoria)
		{
			if(Preferencias.BG_DEBUG_MODE){Log.i(tag,"Imagen Pulsada "+ description);}
		}

		BGCategoria categoria = (BGCategoria) getListAdapter().getItem(position);
		if(Constantes.TIPO_FILA_0.equalsIgnoreCase(categoria.getTipoFila()))
		{
			String codCategoria = categoria.getCodCategoria();
			String litCategoria = categoria.getDescripcionCategoria();
			Intent intent = new Intent(getActivity().getApplicationContext(), ResultadosActivity.class);
			intent.putExtra(Constantes.PARAM_ID_CATEGORIA, codCategoria);
			intent.putExtra(Constantes.PARAM_LIT_CATEGORIA, litCategoria);

			startActivity(intent);

		}

	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		BGCategoria categoria = (BGCategoria) arg0.getAdapter().getItem(arg2);
		Intent intent = new Intent(getActivity().getApplicationContext(), ResultadosActivity.class);
		intent.putExtra(Constantes.PARAM_ID_CATEGORIA, categoria.getCodCategoria());
		startActivity(intent);


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

	public Integer getInitMode() {
		return initMode;
	}

	public void setInitMode(Integer initMode) {
		this.initMode = initMode;
	}



}
