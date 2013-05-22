package br.com.wargen.gerador.webservice;

import java.util.ArrayList;

import br.com.wargen.gerador.bean.ArquivoBean;
import br.com.wargen.gerador.bean.UsuarioBean;
import br.com.wargen.gerador.controller.ArquivoController;
import br.com.wargen.gerador.controller.UsuarioController;

public class WsMetodosMobile {

	public boolean testarConexao() {
		return true;
	}
	
	public UsuarioBean fazerLogin(String login, String senha) {
		UsuarioBean usuario = new UsuarioBean();
		usuario.setNome("Levrangeles");
		
		return usuario;
	}
	
	public ArrayList<ArquivoBean> listarArquivos(String login, String senha) throws Exception {
		return ArquivoController.listarArquivosPorUsuario(UsuarioController.carregarUsuarioPorLogin(login).getId());
	}
}
