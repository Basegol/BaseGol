package com.jic.basegol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.jic.basegol.util.Preferencias;
import com.jic.basegol.util.Utiles;

public class PrimeraEjecucionActivity extends Activity implements OnClickListener{
	
	Activity mMainActivity;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 

		setContentView(R.layout.activity_primera_ejecucion);
		
		Utiles.initSpinnerComunidades(this,18, true);
		
		Button button = (Button)findViewById(R.id.btnOK);
		button.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Spinner spinner = (Spinner) MainActivity.getInstance().findViewById(R.id.spinner_region);
        spinner.setSelection(Integer.parseInt(Preferencias.getSpinnerComunidadId()));
		this.finish();
		
	}


}
