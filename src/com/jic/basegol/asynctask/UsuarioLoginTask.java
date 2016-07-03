package com.jic.basegol.asynctask;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.jic.basegol.util.Constantes;
import com.jic.basegol.util.Preferencias;
import com.jic.basegol.util.Utiles;
import com.jic.basegol.ws.BGConector;
import com.jic.basegol.ws.beans.BGRetorno;
public class UsuarioLoginTask extends AsyncTask<String, Void, String> {


	Activity mActivity;

	public UsuarioLoginTask(Activity activity)
	{
		mActivity = activity;
	}

	protected void onPreExecute(){

	}

	protected String doInBackground(String... connection) {

		// Recupera datos referentes al telefono
		String deviceId = "";//((TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		String phoneNumber = "";//((TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
	    String serial =  "";
	    if(Build.VERSION.SDK_INT >= 9){
	    	serial =  android.os.Build.SERIAL;
	    }else
	    {
	    	serial = "api<9";
	    }
	    String macAddress = "";//((WifiManager) mActivity.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();

	    boolean actualizarUsuario = false;
	    
	    // Solo actualiza los datos del usuario si se obtiene informaci??n nueva
	    String prefDeviceId= Preferencias.getDeviceId();
	    String prefPhoneNumber = Preferencias.getPhoneNumber();
	    String prefSerial = Preferencias.getSerialNumber();
	    String prefMacAddress = Preferencias.getMacAddress();
	    
	    String cadenaInfo = "";
	    
	    // Si es nueva informaci??n se actualiza si no se deja la que hab??a
	    
	    if (Utiles.hasValue(deviceId) && !deviceId.equals(prefDeviceId))
	    {
	    	cadenaInfo = cadenaInfo + deviceId + ";";
	    	actualizarUsuario = true;
	    }else{
	    	cadenaInfo = cadenaInfo + prefDeviceId + ";";
	    }
	    

	    
	    if (Utiles.hasValue(macAddress) && !macAddress.equals(prefMacAddress))
	    {
	    	cadenaInfo = cadenaInfo + macAddress + ";";
	    	actualizarUsuario = true;
	    }else{
	    	cadenaInfo = cadenaInfo + prefMacAddress + ";";
	    }
	    
	    if (Utiles.hasValue(phoneNumber) && !phoneNumber.equals(prefPhoneNumber))
	    {
	    	cadenaInfo = cadenaInfo + phoneNumber + ";";
	    	actualizarUsuario = true;
	    }else{
	    	cadenaInfo = cadenaInfo + prefPhoneNumber + ";";
	    }
	    
	    if (Utiles.hasValue(serial) && !serial.equals(prefSerial))
	    {
	    	cadenaInfo = cadenaInfo + serial + ";";
	    	actualizarUsuario = true;
	    }else{
	    	cadenaInfo = cadenaInfo + prefSerial + ";";
	    }
	    

	    BGRetorno result = null;
	    if(actualizarUsuario || Utiles.isTemporal(Preferencias.getUsuarioAlias()))
	    {
	    	result = BGConector.updUsuario(cadenaInfo);
	    	String usuario = result.getAlias();
	    	String email = result.getEmailUsuario();
	    	
	    	if(Utiles.hasValue(usuario) && !Utiles.isTemporal(usuario))
	    	{
	    		Preferencias.setUsuarioAlias(usuario);
	    		Preferencias.setUsuarioRegistrado(Preferencias.COD_USUARIO_REGISTRADO_Y);
	    	}
	    	
	    	if(Utiles.hasValue(email))
	    	{
	    		Preferencias.setUsuarioEmail(email);
	    	}
	    }
	    
		return (result!=null)?result.getRetorno():Constantes.COD_USUARIO_GENERICO;		
	}
	
	protected void onPostExecute(String result) {
		
		// Valora el resultado y actualiza el codigo de usuario
		
		if (Utiles.hasValue(result))
		{
			Preferencias.setUsuarioCodigo(result);
		}

	}



}
