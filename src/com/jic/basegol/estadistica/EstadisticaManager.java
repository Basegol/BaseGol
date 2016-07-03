package com.jic.basegol.estadistica;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

import com.jic.basegol.util.Preferencias;

public class EstadisticaManager extends Activity
{

	public static ArrayList<Estadistica> estadisticas;
	public static int numEstadisticas;
	
	public static void add (Estadistica estadi)
	{
		if(estadisticas == null)
		{
			estadisticas = new  ArrayList<Estadistica>();
		}
		
		if("1".equalsIgnoreCase(Preferencias.getUsuarioCodigo()) || "3".equalsIgnoreCase(Preferencias.getUsuarioCodigo()))
		{
			estadisticas.add(estadi);
		}
	}

	
	public static void clear()
	{
		estadisticas = new ArrayList<Estadistica>();
	}
	
	public static String print()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("*****************************************************************\n");
		sb.append("N??mero de estadisiticas recopiladas: " + getNumEstadisticas() + "\n");
		sb.append("Usuario Codigo: " + Preferencias.getUsuarioCodigo()+ "\n");
		sb.append("Usuario Alias: " + Preferencias.getUsuarioAlias()+ "\n");
		sb.append("*****************************************************************\n");
		
		for (int i = 0; i < estadisticas.size(); i ++)
		{
			Estadistica estadistica = estadisticas.get(i);
			sb.append("-------------------------------"+ i + "----------------------------------\n");
			sb.append("Tipo: " + estadistica.getTipo() + "\n");
			sb.append("Nombre: " + estadistica.getNombre() + "\n");
			sb.append("Fecha: " + estadistica.getFecha() + "\n");
			sb.append("CosteTiempo: " + estadistica.getCosteTiempo() + "ms\n");
			sb.append("InfoAdicional: " + estadistica.getInfoAdicional() + "\n");
			sb.append("-------------------------------"+ i + "----------------------------------\n");
			
		}
		
		return sb.toString();
	}
	
	
	public static ArrayList<Estadistica> getEstadisticas() {
		return estadisticas;
	}

	public static void setEstadisticas(ArrayList<Estadistica> estadisticas) {
		EstadisticaManager.estadisticas = estadisticas;
	}

	public static int getNumEstadisticas() {
		return estadisticas.size();
	}

	
	
}
