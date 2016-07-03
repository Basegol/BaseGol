package com.jic.basegol.ws;

public class BGServicios 
{
	public static String URL_SERVIDOR_BASE = "http://www.basegol.com/";
	public static String WS_CATEGORIA_NIVEL = URL_SERVIDOR_BASE+"WS/Categorias.asmx/GetCategoriaNivel";
	public static String WS_CATEGORIA_FAVORITOS = URL_SERVIDOR_BASE+"WS/Categorias.asmx/GetCategoriasFavorito";
	public static String WS_CATEGORIA_UPD_FAVORITOS = URL_SERVIDOR_BASE+"WS/Usuario.asmx/UpdFavorito";
	public static String WS_RESULTADO_PARTIDOS_APP = URL_SERVIDOR_BASE+"WS/Partidos.asmx/GetPartidosApp";
	public static String WS_RESULTADO_PARTIDOS_DETALLE = URL_SERVIDOR_BASE+"WS/Partidos.asmx/GetPartidosDetalleApp";
	public static String WS_CLASIFICACION = URL_SERVIDOR_BASE+"WS/Clasificacion.asmx/GetClasificacion";
	public static String WS_USUARIO_UPD = URL_SERVIDOR_BASE+"WS/Usuario.asmx/UpdAltaUsuarioApp";
	public static String WS_PARTIDO_UPD = URL_SERVIDOR_BASE+"ws/usuarioresultado.asmx/InsertarResultado";
	public static String WS_TRAZABILIDAD_USUARIO_APP = URL_SERVIDOR_BASE + "/WS/Usuario.asmx/TrazabilidadUsuarioApp";
	
}
