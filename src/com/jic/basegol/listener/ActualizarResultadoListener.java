package com.jic.basegol.listener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jic.basegol.R;
import com.jic.basegol.util.Utiles;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.BGConstantes;
import com.jic.basegol.ws.beans.BGRetorno;

public class ActualizarResultadoListener implements OnClickListener {

	private Activity mActivity;
	private BGRetorno retorno;
	
	private String tag = "FavoritosListener";

	public ActualizarResultadoListener(Activity m) {
		this.mActivity = m;
		this.retorno = new BGRetorno();
	}
	
	@Override
	public void onClick(View arg0) {
		new ActualizarResultadoTask().execute();
		InputMethodManager imm = (InputMethodManager)mActivity.getSystemService(
			      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(((TextView)mActivity.findViewById(R.id.detailMsgRetorno)).getWindowToken(), 0);
	}
	
	private class ActualizarResultadoTask extends AsyncTask<Void, Void, BGRetorno> {
    	ProgressDialog mProgressDialog = null;
    	
	    protected void onPreExecute(){
	    	mProgressDialog = ProgressDialog.show(mActivity,"Por favor espere", "Actualizando datos del partido...", true);
	    	TextView codRetorno = ((TextView)mActivity.findViewById(R.id.detailMsgRetorno));
	    	codRetorno.setVisibility(View.GONE);
	    }
	    
		@Override
		protected BGRetorno doInBackground(Void... arg0) {
			//Cambia el signo ya que se pretende la accion contraria
			String tantoLocal = ((EditText)mActivity.findViewById(R.id.detailLocMarcador)).getText().toString();
			String tantoVisitante = ((EditText)mActivity.findViewById(R.id.detailVisMarcador)).getText().toString();
			String codPartido = ((TextView)mActivity.findViewById(R.id.detailCodPar)).getText().toString();
			String descDeporte = ((TextView)mActivity.findViewById(R.id.detailDescDeporte)).getText().toString();
			
			Spinner spinner = (Spinner)mActivity.findViewById(R.id.spinner_tipoPartido);
			String tipoPartido = String.valueOf(spinner.getSelectedItemId());
			//String tipoPartido = 

			
			retorno = BGConector.updResultadoPartido(codPartido, descDeporte, tantoLocal, tantoVisitante, tipoPartido);
			return retorno;
		}
		
	    protected void onPostExecute(BGRetorno retorno) {
	  
	    	//retorno.setRetorno("Tu resultado se ha insertado, gracias por colaborar");
	    	String tipoRetorno = retorno.getTipoRetorno();
	    	
	    	
	    	TextView codRetorno = ((TextView)mActivity.findViewById(R.id.detailMsgRetorno));
	    	codRetorno.setVisibility(View.VISIBLE);
	    	codRetorno.setText(retorno.getRetorno());
	    	codRetorno.setBackgroundColor(mActivity.getResources().getColor(Utiles.getColorFromRetorno(tipoRetorno)));

	    	mProgressDialog.dismiss();
	    	
	    }
	}
}
