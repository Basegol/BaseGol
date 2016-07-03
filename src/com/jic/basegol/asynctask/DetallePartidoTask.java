package com.jic.basegol.asynctask;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jic.basegol.R;
import com.jic.basegol.DetalleActivity;
import com.jic.basegol.listener.ActualizarResultadoListener;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Fuentes;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.BGConstantes;
import com.jic.basegol.ws.beans.BGDetallePartido;

public class DetallePartidoTask extends AsyncTask<String, Void, BGDetallePartido> {


	DetalleActivity mActivity;

	public DetallePartidoTask(DetalleActivity activity)
	{
		mActivity = activity;
	}

	ProgressDialog mProgressDialog = null;

	protected void onPreExecute(){
		mProgressDialog = ProgressDialog.show(mActivity,"Por favor espere", "Cargando datos del partido...", true);
	}


	protected BGDetallePartido doInBackground(String... connection) {
		BGDetallePartido result = BGConector.getDetallePartido(mActivity.getCodPartido(), mActivity.getDeporte());

		return result;

	}

	protected void onPostExecute(BGDetallePartido partido) {

		mActivity.setPartido(partido);
		TextView tvCatPartJornada = (TextView)mActivity.findViewById(R.id.detailCatPartJornada);
		tvCatPartJornada.setText(partido.getLitCat()+" - JORNADA " + partido.getNumJornada());

		TextView tvEstadoPartido = (TextView)mActivity.findViewById(R.id.detailLitEstadoPartido);
		tvEstadoPartido.setText(partido.getLitEstado());
		TextView tvCodLoc = (TextView)mActivity.findViewById(R.id.detailCodLocal);
		tvCodLoc.setText(partido.getCodLoc());
		tvCodLoc.setVisibility(View.GONE);
		TextView tvCodVis = (TextView)mActivity.findViewById(R.id.detailCodVis);
		tvCodVis.setText(partido.getCodVis());
		tvCodVis.setVisibility(View.GONE);
		TextView tvCodPart = (TextView)mActivity.findViewById(R.id.detailCodPar);
		tvCodPart.setText(partido.getCodPart());
		tvCodPart.setVisibility(View.GONE);
		TextView tvLitLoc = (TextView)mActivity.findViewById(R.id.detailLitLocal);
		tvLitLoc.setText(partido.getLitLoc());
		TextView tvLitVis = (TextView)mActivity.findViewById(R.id.detailLitVis);
		tvLitVis.setText(partido.getLitVis());

		EditText tvLocMarcador = (EditText)mActivity.findViewById(R.id.detailLocMarcador);
		tvLocMarcador.setText(partido.getTanLoc());
		tvLocMarcador.setTypeface(Fuentes.digitalScore);
		EditText tvVisMarcador = (EditText)mActivity.findViewById(R.id.detailVisMarcador);
		tvVisMarcador.setText(partido.getTanVis());
		tvVisMarcador.setTypeface(Fuentes.digitalScore);
		TextView tvSeparador = (TextView)mActivity.findViewById(R.id.cteSeparador);
		tvSeparador.setText(Constantes.SEPARADOR_GUION);
		tvSeparador.setTypeface(Fuentes.digitalScore);
		TextView infoAdicional = (TextView) mActivity.findViewById(R.id.detailInfoPartido);
		TextView infoAdicionalLong = (TextView) mActivity.findViewById(R.id.detailInfoPartidoLong);

		infoAdicional.setText("Informaci??n adicional");
		String info = partido.getInfoDetalle();
		infoAdicionalLong.setText(info);

		TextView tipoPartido = (TextView) mActivity.findViewById(R.id.detailDescDeporte);
		tipoPartido.setText(partido.getLitDep());
		tipoPartido.setVisibility(View.GONE);

		Button button = (Button)mActivity.findViewById(R.id.btnActualizaResultado);
		button.setOnClickListener(new ActualizarResultadoListener(mActivity));

		try
		{
			int colorLocal = Color.parseColor(partido.getColorLoc());
			int colorVisitante = Color.parseColor(partido.getColorVis());
			tvLitLoc.setTextColor(colorLocal);
			tvLitVis.setTextColor(colorVisitante);
		}catch(Exception e){}

		if(partido.getIsMod().equals(BGConstantes.COD_IS_NO_MOD))
		{
			tvLocMarcador.setEnabled(false);
			tvVisMarcador.setEnabled(false);
			tvSeparador.setEnabled(false);
			button.setEnabled(false);
			Spinner spinner = (Spinner)mActivity.findViewById(R.id.spinner_tipoPartido);
			spinner.setEnabled(false);
		}


		mProgressDialog.dismiss();
	}

}
