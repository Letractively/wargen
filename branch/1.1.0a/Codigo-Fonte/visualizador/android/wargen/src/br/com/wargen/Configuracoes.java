package br.com.wargen;

import android.content.Context;
import android.net.ConnectivityManager;

public class Configuracoes {
	public static String ENDERECO_SERVIDOR = "201.40.26.206:8080";
	public static String NOME_USUARIO = "Usuário";
	
	public static boolean verificarConexaoInternetAtiva(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getNetworkInfo(cm.TYPE_MOBILE).isConnected()) || (cm.getNetworkInfo(cm.TYPE_WIFI).isConnected());
	}
}
