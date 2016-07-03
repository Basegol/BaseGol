package com.jic.basegol.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jic.basegol.R;
import com.jic.basegol.R.color;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.util.Utiles;
import com.jic.basegol.ws.beans.BGClasificacion;

public class ClasificacionAdapter extends ArrayAdapter<BGClasificacion>{

	private ArrayList<BGClasificacion> items;
	LayoutInflater vi;

	public ClasificacionAdapter(Context context, int textViewResourceId, ArrayList<BGClasificacion> items) {
		super(context, textViewResourceId, items);
		this.vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// parent.setScrollContainer(true);
		View v = convertView;

		BGClasificacion o = items.get(position);
		String tipoFila = (o!=null)?o.getTipoFila():"";


		if(Constantes.TIPO_FILA_0.equals(tipoFila))
		{
			v = vi.inflate(R.layout.row_clasificacion, null);              	                	 
			TextView tvGrupoPos = (TextView)v.findViewById(R.id.ClasGrupo);
			TextView tvPosicion = (TextView)v.findViewById(R.id.ClasPos);
			TextView tvEquipo = (TextView)v.findViewById(R.id.ClasLitEquipo);
			TextView tvPuntos = (TextView)v.findViewById(R.id.ClasPuntos);
			TextView tvJugados = (TextView)v.findViewById(R.id.ClasJugados);
			TextView tvGanados = (TextView)v.findViewById(R.id.ClasGanados);
			TextView tvEmpatados = (TextView)v.findViewById(R.id.ClasEmpatados);
			TextView tvPerdidos = (TextView)v.findViewById(R.id.ClasPerdidos);
			TextView tvGolesFavor = (TextView)v.findViewById(R.id.ClasGolesFavor);
			TextView tvGolesContra = (TextView)v.findViewById(R.id.ClasGolesContra);
			tvGrupoPos.setText(" ");
			tvGrupoPos.setBackgroundColor(Utiles.getColor(o.getColor()));
			tvPosicion.setText(o.getOrdenClas());
			tvEquipo.setText(o.getLitEquipo());
			tvPuntos.setText(o.getPuntos());
			tvJugados.setText(o.getJugados());
			tvGanados.setText(o.getGanados());
			tvEmpatados.setText(o.getEmpate());
			tvPerdidos.setText(o.getPerdidos());
			tvGolesFavor.setText(o.getGolesFavor());
			tvGolesContra.setText(o.getGolesContra());
		}

		if(Constantes.TIPO_FILA_1.equals(tipoFila))
		{
			v = vi.inflate(R.layout.row_clasificacion, null);     
			TextView tvGrupoPos = (TextView)v.findViewById(R.id.ClasGrupo);
			TextView tvPosicion = (TextView)v.findViewById(R.id.ClasPos);
			TextView tvEquipo = (TextView)v.findViewById(R.id.ClasLitEquipo);
			TextView tvPuntos = (TextView)v.findViewById(R.id.ClasPuntos);
			TextView tvJugados = (TextView)v.findViewById(R.id.ClasJugados);
			TextView tvGanados = (TextView)v.findViewById(R.id.ClasGanados);
			TextView tvEmpatados = (TextView)v.findViewById(R.id.ClasEmpatados);
			TextView tvPerdidos = (TextView)v.findViewById(R.id.ClasPerdidos);
			TextView tvGolesFavor = (TextView)v.findViewById(R.id.ClasGolesFavor);
			TextView tvGolesContra = (TextView)v.findViewById(R.id.ClasGolesContra);
			v.setBackgroundColor(getContext().getResources().getColor(R.color.DarkGray));
			tvEquipo.setText(o.getLitEquipo());
			tvPuntos.setText(o.getPuntos());
			tvJugados.setText(o.getJugados());
			tvGanados.setText(o.getGanados());
			tvEmpatados.setText(o.getEmpate());
			tvPerdidos.setText(o.getPerdidos());
			tvGolesFavor.setText(o.getGolesFavor());
			tvGolesContra.setText(o.getGolesContra());
		}

		if("".equalsIgnoreCase(tipoFila))
		{
			v = vi.inflate(R.layout.row_clasificacion, null); 
			v.setVisibility(View.GONE);
		}

		return v;
	}

}
