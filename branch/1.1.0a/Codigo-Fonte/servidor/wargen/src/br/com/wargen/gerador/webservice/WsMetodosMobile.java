package br.com.wargen.gerador.webservice;

import br.com.wargen.gerador.bean.UsuarioBean;

public class WsMetodosMobile {

	public boolean testarConexao() {
		return true;
	}
	
	public UsuarioBean fazerLogin(String login, String senha) {
		UsuarioBean usuario = new UsuarioBean();
		usuario.setNome("Levrangeles");
		
		return usuario;
	}
	
}
