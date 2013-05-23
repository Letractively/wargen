package br.com.wargen.tasks;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.wargen.ActivityPersonalizada;
import br.com.wargen.Configuracoes;
import br.com.wargen.UtilitariosUI;
import br.com.wargen.controller.UsuarioController;
import br.com.wargen.interfaces.IAsyncTaskListener;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class WebServiceTask extends AsyncTask <String, Object, Integer>{
	
	private ActivityPersonalizada activity;
	private ProgressDialog progressDialog;
  	private SoapObject retornoWebserviceObjeto;
  	private SoapPrimitive retornoWebservicePrimitivo;

  	private String URL;
  	private String SOAP_ACTION;
   	private String METHOD_NAME;

	private SoapObject request = null;
	private HttpTransportSE androidHttpTransport = null;
	private SoapSerializationEnvelope envelope = null;
	
	private Exception exception;
  
	public WebServiceTask(ActivityPersonalizada activity, String endereco){
		this.activity = activity;
		this.URL  = "http://" + endereco + "/wargen/services/WsMetodosMobile?wsdl";
	}
	 
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(this.activity);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();
    }

	@Override
	protected Integer doInBackground(String... parametros) {
		try {
			this.SOAP_ACTION = parametros[0];
			this.METHOD_NAME = parametros[0];
			
			if (this.METHOD_NAME.toLowerCase().equals("testarconexao")) {
				if (!Configuracoes.verificarConexaoInternetAtiva(this.activity)) {
					throw new Exception("Sem conexão com a internet.");
				}
				
				request = new SoapObject(Configuracoes.NAMESPACE_WEBSERVICE, METHOD_NAME);
			 
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				
				androidHttpTransport = new HttpTransportSE(URL);
				androidHttpTransport.call(SOAP_ACTION, envelope);
	 
				retornoWebservicePrimitivo = (SoapPrimitive)envelope.getResponse();
				
				if (retornoWebservicePrimitivo == null || !Boolean.parseBoolean(retornoWebservicePrimitivo.toString())) {
					throw new Exception("Não foi possível conectar no banco de dados.");
				}
			}
			else if (this.METHOD_NAME.toLowerCase().equals("fazerlogin")) {				
				Configuracoes.NOME_USUARIO = new UsuarioController().fazerLogin(parametros[1], parametros[2]);
				
				this.activity.onTaskExecutou(retornoWebserviceObjeto);
			}
		 
	    } catch (Exception e) {
	        e.printStackTrace();
	        this.exception = e;
	        //delegado.notificarTarefaConcluida("fazerLogin", false, alertDialog.setMessage(e.getMessage()));
	    }
		return 1;
	}
	 
    @Override
    protected void onPostExecute(Integer result) {
        progressDialog.dismiss();
        
        if (this.exception != null) {
        	this.activity.onTaskFalhou(this.exception);
        }
        else {
        	this.activity.onTaskExecutou(null);
        }
        	
    }
    
    @Override
    protected void onProgressUpdate(Object... values) {
        progressDialog.setMessage(values[0].toString());
    }
}
