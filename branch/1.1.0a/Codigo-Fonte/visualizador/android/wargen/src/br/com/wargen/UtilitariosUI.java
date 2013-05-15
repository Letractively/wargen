package br.com.wargen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UtilitariosUI {
	public static void MensagemAlerta(Context context, String mensagem) {

		AlertDialog alerta = null;
		
		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(context);
		alertaBuilder.setMessage(mensagem);
		alertaBuilder.setCancelable(true);
		alertaBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(final DialogInterface dialog, final int which) {  
            	             
            }  
        }); 
		
		alerta = alertaBuilder.create();
		alerta.show();
	}
}
