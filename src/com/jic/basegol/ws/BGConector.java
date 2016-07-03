package com.jic.basegol.ws;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jic.basegol.estadistica.Estadistica;
import com.jic.basegol.estadistica.EstadisticaManager;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.ws.beans.BGCategoria;
import com.jic.basegol.ws.beans.BGClasificacion;
import com.jic.basegol.ws.beans.BGDetallePartido;
import com.jic.basegol.ws.beans.BGResultadoReducido;
import com.jic.basegol.ws.beans.BGRetorno;


public class BGConector 
{

	private static final String tag = "CRZConector";
	private static final String tagGetCategorias = tag+"_getCategorias";
	private static final String tagGetCategoriasFav = tag+"_getCategoriasFav";
	private static final String tagGetResultadoPartidosReducido = tag+"_getResultadoPartidosReducido";
	private static final String tagSetFavorito = tag+"_tagSetFavorito";
	private static final String tagGetClasificacion = tag+"_tagGetClasificacion";
	private static final String tagUpdUsuario = tag+"_tagUpdUsuario";
	private static final String tagDetallePartido = tag+"_tagDetallePartido";
	private static final String tagUpdPartido = tag+"_tagUpdPartido";
	public static String BG_WS_CLAVE = "filezilla";
	
	private static int TIME_OUT_CONNECTION = 30000;
	private static int TIME_OUT_SOCKET = 30000;
	
	/**
	 * Devuelve las categorías para un deporte dado y una región
	 * @param clave
	 * @param deporte
	 * @param pais
	 * @param zona
	 * @return
	 */
	public static ArrayList<BGCategoria> getCategorias(String deporte, String pais, String zona)
	{
		long ti = System.currentTimeMillis();
		
		ArrayList<BGCategoria> result = null;
		
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetCategorias,Preferencias.getUsuarioCodigo());}

			HttpPost post = new HttpPost(BGServicios.WS_CATEGORIA_NIVEL);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO, Preferencias.getUsuarioCodigo()));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_DEPORTE, deporte));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_PAIS, pais));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_ZONA, zona));				


			post.setEntity(new UrlEncodedFormEntity(urlParameters,"UTF-8"));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetCategorias, jsonResponse);}
			
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGCategoria>>(){}.getType();
			Collection<BGCategoria> lista = gson.fromJson(jsonResponse, collectionType);
			
			result = (ArrayList<BGCategoria>)lista;
			long timeCost =  (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetCategorias, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagGetCategorias, timeCost,"[DEPORTE="+deporte+"];[PAIS="+pais+"];[ZONA="+zona+"]"));
			
		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagGetCategorias, Log.getStackTraceString(e));}
		}
		
		return result;	
	}
	
	/**
	 * Devuelve las categor??as favoritas de un deporte dado
	 * @param clave
	 * @param deporte
	 * @return
	 */
	public static ArrayList<BGCategoria> getCategoriasFavoritos()
	{
		
		long ti = System.currentTimeMillis();
		
		ArrayList<BGCategoria> result = null;
		
		try
		{
			
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetCategoriasFav,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_CATEGORIA_FAVORITOS);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO,Preferencias.getUsuarioCodigo()));
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters,"UTF-8"));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetCategoriasFav, jsonResponse);}
			
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGCategoria>>(){}.getType();
			Collection<BGCategoria> lista = gson.fromJson(jsonResponse, collectionType);
			
			result = (ArrayList<BGCategoria>)lista;
			long timeCost = (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetCategoriasFav, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagGetCategoriasFav, timeCost,""));
			
		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagGetCategoriasFav, Log.getStackTraceString(e));}
		}
		
		return result;	
	}
		
	/**
	 * Devuelve los resultados de una categor??a en formato reducido
	 * @param clave
	 * @param codCategoria
	 * @param jornada
	 * @return
	 */
	public static ArrayList<BGResultadoReducido> getResultadoPartidosReducido(String codCategoria, String jornada, String deporte)
	{
		long ti = System.currentTimeMillis();
		
		ArrayList<BGResultadoReducido> result = null;
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetResultadoPartidosReducido,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_RESULTADO_PARTIDOS_APP);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_CATEGORIA, codCategoria));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_JORNADA, jornada));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_DEPORTE_DESC, deporte));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO,Preferencias.getUsuarioCodigo()));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION,"1"));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetResultadoPartidosReducido, jsonResponse);}
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGResultadoReducido>>(){}.getType();
			Collection<BGResultadoReducido> lista = gson.fromJson(jsonResponse, collectionType);
			
			result = (ArrayList<BGResultadoReducido>)lista;
			
			long timeCost = (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetResultadoPartidosReducido, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagGetResultadoPartidosReducido, timeCost,"[CODCATEGORIA="+codCategoria+"];[JORNADA="+jornada+"];[DEPORTE="+deporte+"]"));
			
		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagGetResultadoPartidosReducido, Log.getStackTraceString(e));}
		}
		
		return result;
	}
	
	
	/**
	 * Devuelve los resultados de una categor??a en formato reducido
	 * @param codCategoria
	 * @param jornada
	 * @return
	 */
	public static BGRetorno setFavorito(String codCategoria, String isFavorito)
	{
		long ti = System.currentTimeMillis();
		
		ArrayList<BGRetorno> resultArray = null;
		BGRetorno result = null;
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdUsuario,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_CATEGORIA_UPD_FAVORITOS);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO, Preferencias.getUsuarioCodigo()));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_CATEGORIA, codCategoria));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_FAVORITO, isFavorito));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagSetFavorito, jsonResponse);}
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGRetorno>>(){}.getType();
			Collection<BGRetorno> lista = gson.fromJson(jsonResponse, collectionType);
			
			resultArray = (ArrayList<BGRetorno>)lista;
			
			if(resultArray != null && resultArray.size()>0){result = resultArray.get(0);}
			
			long timeCost = (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagSetFavorito, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagSetFavorito, timeCost,"[CODCATEGORIA="+codCategoria+"];[ISFAVORTIO="+isFavorito+"]"));
		
		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagSetFavorito, Log.getStackTraceString(e));}
		}
		
		return result;
		
	}

	/**
	 * Devuelve la clasificacion de una categor??a
	 * @param codAplicacion
	 * @param codCategoria
	 * @return
	 */
	public static ArrayList<BGClasificacion> getClasificacion(String codCategoria)
	{
		long ti = System.currentTimeMillis();
		
		ArrayList<BGClasificacion> result = null;
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetClasificacion,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_CLASIFICACION);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_CATEGORIA, codCategoria));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetClasificacion, jsonResponse);}
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGClasificacion>>(){}.getType();
			Collection<BGClasificacion> lista = gson.fromJson(jsonResponse, collectionType);
			
			result = (ArrayList<BGClasificacion>)lista;
			
			long timeCost = (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagGetClasificacion, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagGetClasificacion, timeCost,"[CODCATEGORIA="+codCategoria+"]"));

		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagGetClasificacion, Log.getStackTraceString(e));}
		}
		
		return result;
	}
	
	
	/**
	 * Actualiza datos del usuario
	 * @param codAplicacion
	 * @param codCategoria
	 * @return
	 */
	public static BGRetorno updUsuario(String infoAdicional)
	{
		long ti = System.currentTimeMillis();
		
		ArrayList<BGRetorno> resultArray = null;
		BGRetorno result = null;
		
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdUsuario,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_USUARIO_UPD);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO, Preferencias.getUsuarioCodigo()));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_USUARIO_INFO, infoAdicional));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdUsuario, jsonResponse);}
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGRetorno>>(){}.getType();
			Collection<BGRetorno> lista = gson.fromJson(jsonResponse, collectionType);
			
			resultArray = (ArrayList<BGRetorno>)lista;
			
			if(resultArray != null && resultArray.size()>0){result = resultArray.get(0);}
			
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdUsuario, "Time Cost: " + (System.currentTimeMillis()-ti) + "ms");}

		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagUpdUsuario, Log.getStackTraceString(e));}
		}
		
		return result;
		
	}
	
	/**
	 * Recupera el detalle de un partido
	 * @param clave
	 * @param codCategoria
	 * @param jornada
	 * @return
	 */
	public static BGDetallePartido getDetallePartido(String codPartido, String deporte)
	{
		long ti = System.currentTimeMillis();
		
		BGDetallePartido result = null;
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagDetallePartido,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_RESULTADO_PARTIDOS_DETALLE);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO, Preferencias.getUsuarioCodigo()));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_PARTIDO, codPartido));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_DEPORTE_DESC, deporte));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagDetallePartido, jsonResponse);}
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGDetallePartido>>(){}.getType();
			Collection<BGDetallePartido> lista = gson.fromJson(jsonResponse, collectionType);
			
			ArrayList<BGDetallePartido>resultAux = (ArrayList<BGDetallePartido>)lista;
			
			// Es posible que devuelva con tipoFile 0 y 1 
			if(resultAux!= null && resultAux.size()>1)
			{
				result = resultAux.get(1);
			}else
			{
				result = resultAux.get(0);
			}
			
			long timeCost = (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagDetallePartido, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagDetallePartido, timeCost,"[CODPARTIDO="+codPartido+"];[DEPORTE="+deporte+"]"));
		
		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagDetallePartido, Log.getStackTraceString(e));}
		}
		
		return result;
	}
	
	
	/**
	 * Actualiza el resultado de un partido y sus datos
	 * @param codPartido
	 * @param descDeporte
	 * @param tantoLocal
	 * @param tantoVisitante
	 * @param tipoPartido
	 * @return
	 */
	public static BGRetorno updResultadoPartido(String codPartido, String descDeporte, String tantoLocal, String tantoVisitante, String tipoPartido)
	{
		
		long ti = System.currentTimeMillis();
		
		ArrayList<BGRetorno> resultArray = null;
		BGRetorno result = null;
		
		try
		{
			HttpClient client =  new DefaultHttpClient(getHttpParams());
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdPartido,Preferencias.getUsuarioCodigo());}
			
			HttpPost post = new HttpPost(BGServicios.WS_PARTIDO_UPD);
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_CLAVE, BG_WS_CLAVE));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_APLICACION, "1"));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_USUARIO, Preferencias.getUsuarioCodigo()));
			
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_DEPORTE_DESC, descDeporte));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_COD_PARTIDO, codPartido));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_TANTO_LOCAL, tantoLocal));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_TANTO_VISITANTE, tantoVisitante));
			urlParameters.add(new BasicNameValuePair(BGConstantes.PARAM_NAME_PARTIDO_COD_TIPO, tipoPartido));
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse resp = client.execute(post);
			
			String respuesta = EntityUtils.toString(resp.getEntity());
			String jsonResponse = BGUtil.getJSONStringfromNET(respuesta);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdPartido, jsonResponse);}
			
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<BGRetorno>>(){}.getType();
			Collection<BGRetorno> lista = gson.fromJson(jsonResponse, collectionType);
			
			resultArray = (ArrayList<BGRetorno>)lista;
			
			if(resultArray != null && resultArray.size()>0){result = resultArray.get(0);}
			
			long timeCost = (System.currentTimeMillis()-ti);
			if(Preferencias.BG_DEBUG_MODE){Log.i(tagUpdPartido, "Time Cost: " + timeCost + "ms");}
			EstadisticaManager.add(new Estadistica(Estadistica.TIPO_RED, tagUpdPartido, timeCost,"[CODPARTIDO="+codPartido+"];[DESCDEPORTE="+descDeporte+"]"));

		}catch(Exception e){
			if(Preferencias.BG_DEBUG_MODE){Log.e(tagUpdPartido, Log.getStackTraceString(e));}
		}
		
		return result;
		
	}
	
	
	private static HttpParams getHttpParams()
	{
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = TIME_OUT_CONNECTION;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		int timeoutSocket = TIME_OUT_SOCKET;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		return httpParameters;
	}
	
}
