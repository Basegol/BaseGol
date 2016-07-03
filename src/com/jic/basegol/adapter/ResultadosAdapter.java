package com.jic.basegol.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
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
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Fuentes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.ws.beans.BGResultadoReducido;

public class ResultadosAdapter extends ArrayAdapter<BGResultadoReducido>{

	 private ArrayList<BGResultadoReducido> items;
	 private LayoutInflater vi;
	 
     public ResultadosAdapter(Context context, int textViewResourceId, ArrayList<BGResultadoReducido> items) {
             super(context, textViewResourceId, items);
             this.vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             this.items = items;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
    	 
             View v = convertView;
             
             BGResultadoReducido o = items.get(position);     
             
             String tipoFila = (o!=null)?o.getTipoFila():"";
             
             if (Constantes.TIPO_FILA_0.equals(tipoFila))
             {
            	 // Tipo resultado
            	 v = vi.inflate(R.layout.row_resultado, null);
             }
             else if (Constantes.TIPO_FILA_1.equals(tipoFila))
             {
            	 // Tipo separador menu
            	 v = vi.inflate(R.layout.row_resultado_separador, null);
             }
             else
             {
            	 v = vi.inflate(R.layout.row_categoria, null);
            	 v.setVisibility(View.GONE);
             }

             // Row tipo resultado
             if(Constantes.TIPO_FILA_0.equals(tipoFila))
             {
            	 TextView litLoc = (TextView) v.findViewById(R.id.litLocal);
                 TextView litVis = (TextView) v.findViewById(R.id.litVis);
                 TextView estado = (TextView) v.findViewById(R.id.litEstadoPartido);
                 //TextView separador = (TextView) v.findViewById(R.id.cteSeparador);
                 //TextView golLoc = (TextView) v.findViewById(R.id.golLoc);
                 //TextView golVis = (TextView) v.findViewById(R.id.golVis);
                 TextView litMarca = (TextView) v.findViewById(R.id.litMarca);
                 //ImageView weather = (ImageView)v.findViewById(R.id.imgWeather);
                 //ImageView gmaps = (ImageView)v.findViewById(R.id.imgGmaps);
                 //gmaps.setOnClickListener(new GmapsListener(v));
                 //gmaps.setOnClickListener(new OSMapsListener(v));

                 litLoc.setText(o.getLitLoc());
                 litVis.setText(o.getLitVis());
                 estado.setText(o.getLitEstado());
                 //separador.setText(" - ");
                 String colorLoc = o.getColorLoc();
                 String colorVis = o.getColorVis();
                 String golesLocal = o.getTanLoc();
                 String golesVisitante = o.getTanVis();
                 try
                 {
                     int colorLocal = Color.parseColor(colorLoc);
                	 int colorVisitante = Color.parseColor(colorVis);
                     litLoc.setTextColor(colorLocal);
                     litVis.setTextColor(colorVisitante);
                 }catch(Exception e){}
                
                 //golLoc.setText(Utiles.hasValue(golesLocal)?golesLocal:"0");
                 //golVis.setText(Utiles.hasValue(golesVisitante)?golesVisitante:"0");
                 //golLoc.setTypeface(Fuentes.digitalScore);
                 //separador.setTypeface(Fuentes.digitalScore);
                 //golVis.setTypeface(Fuentes.digitalScore);
                 litMarca.setText(o.getLitMarca());
                 litMarca.setTypeface(Fuentes.digitalScore);
                 //weather.setImageResource(R.drawable.ic_weather_sun);
                 //gmaps.setImageResource(R.drawable.ic_gmaps);
                 //gmaps.setImageResource(R.drawable.ic_osm);

             }
             
             // Row tipo Separador
             if(Constantes.TIPO_FILA_1.equals(tipoFila))
             { 
            	 TextView tvSeparador = (TextView)v.findViewById(R.id.ClasLitSeparador);
            	 tvSeparador.setText(o.getLitFila());
             }
             

             return v;
     }
     
}
