package com.jic.basegol.util;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.preference.PreferenceManager;

public class Preferencias {
	
	public static boolean BG_DEBUG_MODE = false;
	
	private static final String KEY_USUARIO_CODIGO = "usuario.codigo";
	private static final String KEY_USUARIO_EMAIL = "usuario.email";
	private static final String KEY_USUARIO_ALIAS = "usuario.alias";
	private static final String KEY_SERIAL_NUMBER = "dispositivo.serial";
	private static final String KEY_SPINNER_COMUNIDAD_ID = "spinner.comunidad.value";
	private static final String KEY_INTERVALO_NOTIFICACION = "notificacion.intervalo";
	private static final String KEY_DEVICE_ID = "dispositivo.device.id";
	private static final String KEY_MAC_ADDRESS = "dispositivo.red.mac";
	private static final String KEY_PHONE_NUMBER = "dispositivo.phone.number";
	private static final String KEY_USUARIO_REGISTRADO = "usuario.registrado";
	
	public static final String COD_USUARIO_GENERICO = "0";
	public static final String SPINNER_COMUNIDAD_ANDALUCIA_ID = "1";
	public static final String INTERVALO_NOTIFICACION_DEFAULT = "30";
	public static final String INTERVALO_NOTIFICACION_FINDE = "10";
	public static final String COD_USUARIO_REGISTRADO_Y = "Y";
	public static final String COD_USUARIO_REGISTRADO_N = "N";

	private static String usuarioCodigo;
	private static String usuarioEmail;
	private static String usuarioAlias;
	private static String serialNumber;
	private static String spinnerComunidadId;
	private static String intervaloNotificacion;
	private static String deviceId;
	private static String macAddress;
	private static String phoneNumber;
	private static String usuarioRegistrado;
	

	private static Context mContext;
	
	public static void cargaPreferencias(Context context)
	{
		String serial = "";
		if(Build.VERSION.SDK_INT >= 9){
			serial = android.os.Build.SERIAL;
		}else{
			
		}
		
		mContext = context;
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		
		usuarioCodigo = sharedPreferences.getString(KEY_USUARIO_CODIGO, COD_USUARIO_GENERICO);
		usuarioEmail = sharedPreferences.getString(KEY_USUARIO_EMAIL, null);
		usuarioAlias = sharedPreferences.getString(KEY_USUARIO_ALIAS, null);
		serialNumber = sharedPreferences.getString(KEY_SERIAL_NUMBER, serial);
		spinnerComunidadId = sharedPreferences.getString(KEY_SPINNER_COMUNIDAD_ID, SPINNER_COMUNIDAD_ANDALUCIA_ID);
		//intervaloNotificacion = sharedPreferences.getString(KEY_INTERVALO_NOTIFICACION, INTERVALO_NOTIFICACION_DEFAULT);
		intervaloNotificacion = INTERVALO_NOTIFICACION_DEFAULT;
		deviceId = sharedPreferences.getString(KEY_DEVICE_ID, null);
		macAddress = sharedPreferences.getString(KEY_MAC_ADDRESS, null);
		phoneNumber = sharedPreferences.getString(KEY_PHONE_NUMBER, null);
		usuarioRegistrado = sharedPreferences.getString(KEY_USUARIO_REGISTRADO, COD_USUARIO_REGISTRADO_N);
		
	}
	
    private static void savePreferences(String key, String value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }
	
	public static String getUsuarioCodigo() {
		return usuarioCodigo;
	}


	public static void setUsuarioCodigo(String usuarioCodigo) {
		Preferencias.usuarioCodigo = usuarioCodigo;
		savePreferences(KEY_USUARIO_CODIGO, usuarioCodigo);
	}

	public static String getUsuarioEmail() {
		return usuarioEmail;
	}

	public static void setUsuarioEmail(String usuarioEmail) {
		Preferencias.usuarioEmail = usuarioEmail;
		savePreferences(KEY_USUARIO_EMAIL, usuarioEmail);
	}

	public static String getSerialNumber() {
		return serialNumber;
	}

	public static void setSerialNumber(String serialNumber) {
		Preferencias.serialNumber = serialNumber;
		savePreferences(KEY_SERIAL_NUMBER, serialNumber);
	}

	public static String getSpinnerComunidadId() {
		return spinnerComunidadId;
	}

	public static void setSpinnerComunidadId(String spinnerComunidadId) {
		Preferencias.spinnerComunidadId = spinnerComunidadId;
		savePreferences(KEY_SPINNER_COMUNIDAD_ID, spinnerComunidadId);
	}
	
	public static String getIntervaloNotificacion() {
		
		int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		if(day == Calendar.SATURDAY || day == Calendar.SUNDAY)
		{
			return INTERVALO_NOTIFICACION_FINDE;
		}	
		
		return intervaloNotificacion;
	}

	public static void setIntervaloNotificacion(String intervaloNotificacion) {
		Preferencias.intervaloNotificacion = intervaloNotificacion;
		savePreferences(KEY_INTERVALO_NOTIFICACION, intervaloNotificacion);
	}

	public static String getDeviceId() {
		return deviceId;
	}

	public static void setDeviceId(String deviceId) {
		Preferencias.deviceId = deviceId;
		savePreferences(KEY_DEVICE_ID, deviceId);
	}

	public static String getMacAddress() {
		return macAddress;
	}

	public static void setMacAddress(String macAddress) {
		Preferencias.macAddress = macAddress;
		savePreferences(KEY_MAC_ADDRESS, macAddress);
	}

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(String phoneNumber) {
		Preferencias.phoneNumber = phoneNumber;
		savePreferences(KEY_PHONE_NUMBER, phoneNumber);
	}

	public static String getUsuarioRegistrado() {
		return usuarioRegistrado;
	}

	public static void setUsuarioRegistrado(String pusuarioRegistrado) {
		Preferencias.usuarioRegistrado = pusuarioRegistrado;
		savePreferences(KEY_USUARIO_REGISTRADO, pusuarioRegistrado);
	}

	public static String getUsuarioAlias() {
		return usuarioAlias;
	}

	public static void setUsuarioAlias(String PusuarioAlias) {
		Preferencias.usuarioAlias = PusuarioAlias;
		savePreferences(KEY_USUARIO_ALIAS, PusuarioAlias);
	}

	
	
}
