package br.com.wargen.activity;

import br.com.wargen.R;
import br.com.wargen.R.layout;
import br.com.wargen.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
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
		
		campoEndereco.setText(LoginActivity.SERVER_ADDRESS);
		campoUsuario.setText(LoginActivity.SERVER_USER);
		campoSenha.setText(LoginActivity.SERVER_PASSWORD);
	}
}
