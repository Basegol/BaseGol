package com.jic.basegol.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jic.basegol.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.jic.basegol.asynctask.DownloadImageTask;
import com.jic.basegol.listener.FavoritosListener;
import com.jic.basegol.util.CacheImage;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.util.Utiles;
import com.jic.basegol.ws.BGConstantes;
import com.jic.basegol.ws.BGServicios;
import com.jic.basegol.ws.beans.BGCategoria;

public class CategoriasAdapter extends ArrayAdapter<BGCategoria>{

	 private ArrayList<BGCategoria> items;
	 LayoutInflater vi = null;

     public CategoriasAdapter(Context context, int textViewResourceId, ArrayList<BGCategoria> items) {
             super(context, textViewResourceId, items);
             this.vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             this.items = items;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
             View v = convertView;
             
             BGCategoria o = items.get(position);
             
             String tipoFila = (o!=null)?o.getTipoFila():"";
             if (Constantes.TIPO_FILA_0.equals(tipoFila))
             {
            	 // Tipo resultado
                 v = vi.inflate(R.layout.row_categoria, null);
             }
             else if (Constantes.TIPO_FILA_1.equals(tipoFila))
             {
            	 // Tipo separador menu
            	 v = vi.inflate(R.layout.row_categoria_separador, null); 
             }
             else
             {
            	 v = vi.inflate(R.layout.row_categoria, null);
            	 v.setVisibility(View.GONE);
             }
             
             if (o != null) {
	            	 
            	 if (Constantes.TIPO_FILA_0.equals(tipoFila) || Constantes.TIPO_FILA_1.equals(tipoFila))
            	 {
            		 ImageView pais = (ImageView)v.findViewById(R.id.imgPaisCategoria);
                	 //pais.setImageResource(R.drawable.ic_esp);
                	 String bandera = o.getBandera();
                	 String urlImage = BGServicios.URL_SERVIDOR_BASE+bandera;
                	 if (Utiles.hasValue(bandera))
                	 {
                		 if (CacheImage.cache.get(urlImage) != null)
                		 {
                			pais.setImageBitmap(CacheImage.cache.get(urlImage)); 
                		 }else{
                			 new DownloadImageTask(pais).execute(urlImage);
                		 }
                	 }
            	 }
            	 
                 // Solo para la fila tipo resultado
                 if (Constantes.TIPO_FILA_0.equals(tipoFila))
                 {
                	 
                	 TextView tt = (TextView) v.findViewById(R.id.descCategoria);
                     tt.setText(o.getDescripcionCategoria());
                     
                     TextView isFavorito = (TextView) v.findViewById(R.id.isFavorito);
                     ImageView fav = (ImageView)v.findViewById(R.id.imgFavCategoria);
                     String favorito = o.getIsFavorito();
                     
                     if (BGConstantes.COD_IS_FAVORITO.equals(favorito))
                     {
                    	 fav.setImageResource(R.drawable.ic_fav2);
                     }else{
                         fav.setImageResource(R.drawable.ic_nfav2);
                     }
                     isFavorito.setText(favorito);
                     isFavorito.setVisibility(View.GONE);
                     fav.setOnClickListener(new FavoritosListener(v, o.getIsFavorito(),o.getCodCategoria()));
                 }
                 
                 // Solo para la fila tipo resultado
                 if (Constantes.TIPO_FILA_1.equals(tipoFila))
                 {
                	 TextView tt = (TextView) v.findViewById(R.id.descCategoria);
                     tt.setText(o.getLitFila());
                 }
             }
             return v;
     }
     
}
