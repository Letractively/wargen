package br.com.wargen;

import android.content.Context;
import android.net.ConnectivityManager;

public class Configuracoes {
	public static String ENDERECO_SERVIDOR = "189.31.1.39:8080";
	public static String NAMESPACE_WEBSERVICE = "http://webservice.gerador.wargen.com.br";
	public static String URL_WEBSERVICE = "http://" + ENDERECO_SERVIDOR + "/wargen/services/WsMetodosMobile?wsdl";
	public static String NOME_USUARIO = "<Usuario>";
	
	public static boolean verificarConexaoInternetAtiva(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getNetworkInfo(cm.TYPE_MOBILE).isConnected()) || (cm.getNetworkInfo(cm.TYPE_WIFI).isConnected());
	}
	
	public static void setEnderecoServidor(String endereco) {
		ENDERECO_SERVIDOR = endereco;
		URL_WEBSERVICE = "http://" + ENDERECO_SERVIDOR + "/wargen/services/WsMetodosMobile?wsdl";
	}
}
