package br.com.wargen.tasks.usuario;

import br.com.wargen.ActivityPersonalizada;
import br.com.wargen.Configuracoes;
import br.com.wargen.controller.UsuarioController;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class FazLoginTask  extends AsyncTask <String, Object, Boolean> {

	private ProgressDialog progressDialog;
	private ActivityPersonalizada activity;
	private Exception exception;
	
	public FazLoginTask(ActivityPersonalizada activity) {
		this.activity = activity;
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
			if (!Configuracoes.verificarConexaoInternetAtiva(this.activity)) {
				throw new Exception("A conexão com a internet não esta ativa.");
			}
			
			Configuracoes.NOME_USUARIO = new UsuarioController().fazerLogin(parametros[1], parametros[2]);
	    } catch (Exception e) {
	        e.printStackTrace();
	        this.exception = e;
	        return false;
	    }
		
		return true;
	}
	 
    @Override
    protected void onPostExecute(Boolean fezLogin) {
        progressDialog.dismiss();
        
        if (!fezLogin) {
        	activity.onTaskFalhou(this.exception);
        }
        else {
            activity.onTaskExecutou(null);
        }
    }
}
