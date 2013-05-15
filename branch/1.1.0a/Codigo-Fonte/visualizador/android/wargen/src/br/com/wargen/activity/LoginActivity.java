package br.com.wargen.activity;

import br.com.wargen.R;
import br.com.wargen.UtilitariosUI;
import br.com.wargen.R.id;
import br.com.wargen.R.layout;
import br.com.wargen.R.menu;
import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	public static String SERVER_ADDRESS = "wargen:8080";
	public static String SERVER_USER = "wargen";
	public static String SERVER_PASSWORD = "123123";
	
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_sair:
                this.finish();
                return true;
            case R.id.menu_item_configurar_servidor:
            	this.startActivity(new Intent(this, ConfiguracaoServidorActivity.class));
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void fazerLogin(View view) {
    	//Intent intent = new Intent(this, MensagemFazendoLogin.class);
    	//EditText campoLogin = (EditText) this.findViewById(R.id.txtLogin);
    	
    	//this.startActivity(intent);
    	
    	
    	EditText campoLogin = (EditText) this.findViewById(R.id.txtLogin);
    	EditText campoSenha = (EditText) this.findViewById(R.id.txtSenha);
    	
    	if (campoLogin.getText().toString().trim() == null || campoLogin.getText().toString().trim().equals("")) {
    		UtilitariosUI.MensagemAlerta(this, "Login inválido");
    	}
    	else if (campoSenha.getText().toString().trim() == null || campoSenha.getText().toString().trim().equals("")) {
    		UtilitariosUI.MensagemAlerta(this, "Senha inválida");
    	}
    	
    	Intent intent = new Intent(this, PrincipalActivity.class);
    	this.startActivity(intent);
    }
    
}
