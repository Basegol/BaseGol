package com.jic.basegol.ws.beans;

public class BGCategoria {

	private String CodCategoria;
	private String DescripcionCategoria;
	private String IsFavorito;
	private String TipoFila;
	private String LitFila;
	private String Bandera;

	public String getTipoFila() {
		return TipoFila;
	}
	public void setTipoFila(String tipoFila) {
		TipoFila = tipoFila;
	}
	public String getLitFila() {
		return LitFila;
	}
	public void setLitFila(String litFila) {
		LitFila = litFila;
	}
	public String getBandera() {
		return Bandera;
	}
	public void setBandera(String bandera) {
		Bandera = bandera;
	}
	public String getCodCategoria() {
		return CodCategoria;
	}
	public void setCodCategoria(String codCategoria) {
		CodCategoria = codCategoria;
	}
	public String getDescripcionCategoria() {
		return DescripcionCategoria;
	}
	public void setDescripcionCategoria(String descripcionCategoria) {
		DescripcionCategoria = descripcionCategoria;
	}
	public String getIsFavorito() {
		return IsFavorito;
	}
	public void setIsFavorito(String isFavorito) {
		this.IsFavorito = isFavorito;
	}

}
