package br.com.wargen.activity;

import br.com.wargen.ActivityPersonalizada;
import br.com.wargen.Configuracoes;
import br.com.wargen.R;
import br.com.wargen.UtilitariosUI;
import br.com.wargen.tasks.WebServiceTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfiguracaoServidorActivity extends ActivityPersonalizada {
	
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
			
			Configuracoes.setEnderecoServidor(campoEndereco.getText().toString());
			
			this.finish();
		} catch (Exception e) {
			UtilitariosUI.MensagemAlerta(this, e.getMessage());
		}
	}
	
	public void testarConexao(View view) {
		try {
			EditText campoEndereco = (EditText) this.findViewById(R.id.txtEnderecoServidor);
			
			new WebServiceTask(this, campoEndereco.getText().toString()).execute("testarConexao", "");
		} catch (Exception e) {
			UtilitariosUI.MensagemAlerta(this, e.getMessage() + "\n" + e.getStackTrace());
		}
	}
}
