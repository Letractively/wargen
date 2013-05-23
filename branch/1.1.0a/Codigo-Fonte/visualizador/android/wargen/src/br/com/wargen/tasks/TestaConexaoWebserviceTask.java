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

public class TestaConexaoWebserviceTask extends AsyncTask <String, Object, Boolean>{
	
	private ActivityPersonalizada activity;
	private ProgressDialog progressDialog;
  	private SoapPrimitive retornoWebservicePrimitivo;

  	private String URL;

	private SoapObject request = null;
	private HttpTransportSE androidHttpTransport = null;
	private SoapSerializationEnvelope envelope = null;
	
	private Exception exception;
  
	public TestaConexaoWebserviceTask(ActivityPersonalizada activity, String endereco){
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
	protected Boolean doInBackground(String... parametros) {
		try {
			request = new SoapObject(Configuracoes.NAMESPACE_WEBSERVICE, "testarConexao");
		 
			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call("testarConexao", envelope);
 
			retornoWebservicePrimitivo = (SoapPrimitive)envelope.getResponse();
				
			if (retornoWebservicePrimitivo == null || !Boolean.parseBoolean(retornoWebservicePrimitivo.toString())) {
				throw new Exception("Não foi possível conectar no banco de dados.");
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	        this.exception = e;
	        return false;
	    }		
		return true;
	}
	 
    @Override
    protected void onPostExecute(Boolean conectou) {
        progressDialog.dismiss();
        
        if (!conectou) {
        	this.activity.onTaskFalhou(this.exception);
        }
        else {
        	this.activity.onTaskExecutou(null);
        }
        	
    }
}
