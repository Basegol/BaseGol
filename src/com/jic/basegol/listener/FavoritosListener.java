package com.jic.basegol.listener;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.jic.basegol.R;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.BGConstantes;
import com.jic.basegol.ws.beans.BGRetorno;

public class FavoritosListener implements OnClickListener {

	private View mView;
	private String isFavorito;
	private String codCategoria;
	private BGRetorno retorno;
	
	private String tag = "FavoritosListener";

	public FavoritosListener(View v, String isFavorito, String codCategoria) {
		this.mView = v;
		this.isFavorito = isFavorito;
		this.codCategoria = codCategoria;
		this.retorno = new BGRetorno();
		this.tag = tag+isFavorito+codCategoria;
	}
	
	@Override
	public void onClick(View arg0) {
		new UpdateFavoritoTask().execute();
	}
	
	private class UpdateFavoritoTask extends AsyncTask<Void, Void, BGRetorno> {

		@Override
		protected BGRetorno doInBackground(Void... arg0) {
			//Cambia el signo ya que se pretende la accion contraria
			if(BGConstantes.COD_IS_FAVORITO.equals(isFavorito)){isFavorito = BGConstantes.COD_IS_NO_FAVORITO;}else{isFavorito = BGConstantes.COD_IS_FAVORITO;}
			retorno = BGConector.setFavorito(codCategoria, isFavorito);
			return retorno;
		}
		
	    protected void onPostExecute(BGRetorno retorno) {
	    	String codRetorno = retorno.getRetorno(); 
	    	if (BGConstantes.COD_RETORNO_OK.equals(codRetorno))
	    	{
	    		ImageView fav = (ImageView)mView.findViewById(R.id.imgFavCategoria);
	    		TextView is = (TextView)mView.findViewById(R.id.isFavorito);		
	    		
	    		if(BGConstantes.COD_IS_NO_FAVORITO.equals(isFavorito))
	    		{
	    			fav.setImageResource(R.drawable.ic_nfav2);
	    			is.setText(BGConstantes.COD_IS_NO_FAVORITO);
	    		}else{
	    			fav.setImageResource(R.drawable.ic_fav2);
	    			is.setText(BGConstantes.COD_IS_FAVORITO);
	    		}
	    		
	    	}else{
	    		//No se ha podido actualizar, deja el favorito como estaba
	    		if(BGConstantes.COD_IS_FAVORITO.equals(isFavorito))
	    		{
	    			isFavorito = BGConstantes.COD_IS_NO_FAVORITO;
	    		}else{
	    			isFavorito = BGConstantes.COD_IS_FAVORITO;
	    		}
	
	    		if(Preferencias.BG_DEBUG_MODE){Log.e(tag, "Error actualizar favorito");}
	    	}
	    }
	}
}
