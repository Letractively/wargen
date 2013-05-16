package br.com.wargen.activity;

import br.com.wargen.Configuracoes;
import br.com.wargen.R;
import br.com.wargen.UtilitariosDB;
import br.com.wargen.UtilitariosUI;
import br.com.wargen.R.layout;
import br.com.wargen.R.menu;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_configuracao_servidor);
		
		EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
		EditText campoUsuario = (EditText) this.findViewById(R.id.txtUsuarioServidor);
		EditText campoSenha = (EditText) this.findViewById(R.id.txtSenhaServidor);
		
		campoEndereco.setText(Configuracoes.SERVER_ADDRESS);
		campoUsuario.setText(Configuracoes.SERVER_USER);
		campoSenha.setText(Configuracoes.SERVER_PASSWORD);
	}
	
	public void salvar(View view) {
		try {
			EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
			EditText campoUsuario = (EditText) this.findViewById(R.id.txtUsuarioServidor);
			EditText campoSenha = (EditText) this.findViewById(R.id.txtSenhaServidor);
			
			Configuracoes.SERVER_ADDRESS = campoEndereco.getText().toString();
			Configuracoes.SERVER_USER = campoUsuario.getText().toString();
			Configuracoes.SERVER_PASSWORD = campoSenha.getText().toString();
			
			this.finish();
		} catch (Exception e) {
			UtilitariosUI.MensagemAlerta(this, e.getMessage());
		}
	}
	
	public void testarConexao(View view) {
		try {
			EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
			EditText campoUsuario = (EditText) this.findViewById(R.id.txtUsuarioServidor);
			EditText campoSenha = (EditText) this.findViewById(R.id.txtSenhaServidor);
			
			UtilitariosDB.getConexao(campoEndereco.getText().toString(), campoUsuario.getText().toString(), campoSenha.getText().toString());

			UtilitariosUI.MensagemAlerta(this, "Sucesso!");
		} catch (Exception e) {
			UtilitariosUI.MensagemAlerta(this, e.getMessage() + "\n" + e.getLocalizedMessage());
		}
	}
}
