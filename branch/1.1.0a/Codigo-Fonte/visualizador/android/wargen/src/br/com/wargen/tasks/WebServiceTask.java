package br.com.wargen.tasks;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.wargen.Configuracoes;
import br.com.wargen.UtilitariosUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class WebServiceTask extends AsyncTask <String, Object, Integer>{
	
  private ProgressDialog progressDialog;
  private AlertDialog alertDialog;
  private Context context;
  private SoapObject retornoWebserviceObjeto;
  private SoapPrimitive retornoWebservicePrimitivo;

  private String URL;
  private String SOAP_ACTION;
  private String METHOD_NAME;
  private String NAMESPACE = "http://webservice.gerador.wargen.com.br";
	
  	SoapObject request = null;
  	HttpTransportSE androidHttpTransport = null;
  	SoapSerializationEnvelope envelope = null;
  
	public WebServiceTask(Context context, String endereco){
		this.context = context;
		this.URL  = "http://" + endereco + "/wargen/services/WsMetodosMobile?wsdl";
	}
	 
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();
        
        alertDialog = UtilitariosUI.AlertDialogInstance(context, "");
    }

	@Override
	protected Integer doInBackground(String... parametros) {
		try {
			this.SOAP_ACTION = parametros[0];
			this.METHOD_NAME = parametros[0];
			
			if (this.METHOD_NAME.toLowerCase().equals("testarconexao")) {
				if (!Configuracoes.verificarConexaoInternetAtiva(this.context)) {
					throw new Exception("Sem conexão com a internet.");
				}
				
				request = new SoapObject(NAMESPACE, METHOD_NAME);
			 
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				
				androidHttpTransport = new HttpTransportSE(URL);
				androidHttpTransport.call(SOAP_ACTION, envelope);
	 
				retornoWebservicePrimitivo = (SoapPrimitive)envelope.getResponse();
				
				if (retornoWebservicePrimitivo == null || !Boolean.parseBoolean(retornoWebservicePrimitivo.toString())) {
					throw new Exception("Não foi possível conectar no banco de dados.");
				}
				
				alertDialog.setMessage("Conexão realizada com sucesso!\n");
			}
			else if (this.METHOD_NAME.toLowerCase().equals("fazerlogin")) {				
				request = new SoapObject(NAMESPACE, METHOD_NAME);
				request.addAttribute("login", parametros[1].toString());
				request.addAttribute("senha", parametros[2].toString());
				
				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				
				androidHttpTransport = new HttpTransportSE(URL);
				androidHttpTransport.call(SOAP_ACTION, envelope);
	 
				retornoWebserviceObjeto = (SoapObject)envelope.getResponse();
				
				Configuracoes.NOME_USUARIO = retornoWebserviceObjeto.getPropertyAsString("nome").toString();
				//context.startActivity(new Intent(context, PrincipalActivity.class));
			}
		 
	    } catch (Exception e) {
	        e.printStackTrace();
	        alertDialog.setMessage(e.getMessage());
	    }
		return 1;
	}
	 
    @Override
    protected void onPostExecute(Integer result) {
        progressDialog.dismiss();
        alertDialog.show();
    }
    
    @Override
    protected void onProgressUpdate(Object... values) {
        progressDialog.setMessage(values[0].toString());
    }
}
