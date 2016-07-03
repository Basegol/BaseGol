package com.jic.basegol.estadistica;

import java.util.Date;

import com.jic.basegol.util.Preferencias;

public class Estadistica 
{
	public static final String TIPO_RED = "RED";
	public static final String TIPO_UI = "UI";
	
	
	private String tipo;
	private String nombre;
	private Long costeTiempo;
	private Date fecha;
	private String infoAdicional;
	
	public Estadistica(String tipo, String nombre, Long coste, String infoAdicional)
	{
		this.tipo = tipo;
		this.nombre = nombre;
		this.costeTiempo = coste;
		this.fecha = new Date();
		this.infoAdicional = infoAdicional;
		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCosteTiempo() {
		return costeTiempo;
	}

	public void setCosteTiempo(Long costeTiempo) {
		this.costeTiempo = costeTiempo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
	
	

}
