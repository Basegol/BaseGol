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

public class FavoritosFragment extends ListFragment implements OnItemClickListener{

	private CategoriasAdapter m_adapter;
	ProgressDialog mProgressDialog = null;
	private String tag = "FavoritosFragment";
	private Integer initMode;

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Evita que al girar la pantalla se pierdan todos los datos
		this.setRetainInstance(true);
		getActivity().getLayoutInflater().inflate(R.layout.list_categorias, null);
		new FavoritosTask().execute("");
	}


	private ArrayList<BGCategoria> getCategorias() {

		ArrayList<BGCategoria> categorias = BGConector.getCategoriasFavoritos();
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

	public void actualizaFavoritos()
	{
		// Si hay actividad creada y no es el modo inicio entonces actualiza
		if (getActivity()!=null && getInitMode()!=1)
		{
			new FavoritosTask().execute("");
		}

	}

	public void busqueda()
	{
		if (getActivity()!=null)
		{
			new FavoritosTask().execute("");
		}

	}

	private class FavoritosTask extends AsyncTask<String, Void, ArrayList<BGCategoria>> {
		ArrayList<BGCategoria> temparrlist = new ArrayList<BGCategoria>();

		ProgressDialog mProgressDialog = null;

		protected void onPreExecute(){
			//mProgressDialog = ProgressDialog.show(getActivity(),"Por favor espere", "Cargando favoritos ...", true);
		}

		protected ArrayList<BGCategoria> doInBackground(String... connection) {
			temparrlist= getCategorias();
			return temparrlist;
		}

		protected void onPostExecute(ArrayList<BGCategoria> result) {
			try{
				if (result != null)
				{
					m_adapter = new CategoriasAdapter(getActivity(), R.layout.row_categoria, result);
					setListAdapter(m_adapter);
				}else{
					if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo favoritos");}
				}
			}catch(Exception e){

				result = new ArrayList<BGCategoria>();
				BGCategoria mto = new BGCategoria();
				mto.setTipoFila(Constantes.TIPO_FILA_1);
				mto.setLitFila(Constantes.MENSAJE_MANTENIMIENTO);
				result.add(mto);
				try{m_adapter = new CategoriasAdapter(getActivity(), R.layout.row_categoria, result);}catch(Exception ex){}
				
				setListAdapter(m_adapter);
				if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error obteniendo favoritos");}	
			}

			//mProgressDialog.dismiss();
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
		String tipoFila = categoria.getTipoFila();
		if(Constantes.TIPO_FILA_0.equalsIgnoreCase(tipoFila))
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
		String codCategoria = categoria.getCodCategoria();
		String litCategoria = categoria.getDescripcionCategoria();
		Intent intent = new Intent(getActivity().getApplicationContext(), ResultadosActivity.class);
		intent.putExtra(Constantes.PARAM_ID_CATEGORIA, codCategoria);
		intent.putExtra(Constantes.PARAM_LIT_CATEGORIA, litCategoria);
		startActivity(intent);


	}
	
	public Integer getInitMode() {
		return initMode;
	}

	public void setInitMode(Integer initMode) {
		this.initMode = initMode;
	}

	public FavoritosFragment() {
		super();
	}

}
