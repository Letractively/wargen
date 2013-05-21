package br.com.wargen.activity;

import java.net.URL;

import br.com.wargen.Configuracoes;
import br.com.wargen.R;
import br.com.wargen.UtilitariosUI;
import br.com.wargen.R.layout;
import br.com.wargen.R.menu;
import br.com.wargen.tasks.WebServiceTask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class ConfiguracaoServidorActivity extends Activity {
	
	AsyncTask task = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_configuracao_servidor);
		
		EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
		
		campoEndereco.setText(Configuracoes.ENDERECO_SERVIDOR);
	}
	
	public void salvar(View view) {
		try {
			EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
			
			Configuracoes.ENDERECO_SERVIDOR = campoEndereco.getText().toString();
			
			this.finish();
		} catch (Exception e) {
			UtilitariosUI.MensagemAlerta(this, e.getMessage());
		}
	}
	
	public void testarConexao(View view) {
		try {
			EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
			
			task = new WebServiceTask(this, campoEndereco.getText().toString()).execute("testarConexao", "");
		} catch (Exception e) {
			UtilitariosUI.MensagemAlerta(this, e.getMessage() + "\n" + e.getStackTrace());
		}
	}
}
