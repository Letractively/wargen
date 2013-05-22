package br.com.wargen.controller;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.wargen.Configuracoes;

public class UsuarioController {
  
	public String fazerLogin(String login, String senha) throws Exception {
		SoapObject request = new SoapObject(Configuracoes.NAMESPACE_WEBSERVICE, "fazerLogin");
		request.addAttribute("login", login);
		request.addAttribute("senha", senha);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE androidHttpTransport = new HttpTransportSE(Configuracoes.URL_WEBSERVICE);
		androidHttpTransport.call("fazerLogin", envelope);

		SoapObject retornoWebserviceObjeto = (SoapObject)envelope.getResponse();
		
		return retornoWebserviceObjeto.getPropertyAsString("nome").toString();
	}
}
