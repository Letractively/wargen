package br.unic.ra.gerador.core.controller;

import br.unic.ra.gerador.core.bean.UsuarioBean;
import br.unic.ra.gerador.core.dao.UsuarioDAO;

public class UsuarioController {
	
	public void doLogin(String login, String senha) throws Exception {
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Login inv�lido.");
		}
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Senha inv�lida.");
		}
		
		if (!new UsuarioDAO().doLogin(login, senha)) {
			throw new Exception("Usu�rio ou senha inv�lidos");
		}
		
	}
	
	public UsuarioBean loadByLogin(String login) throws Exception {
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Par�metro login inv�lido.");
		}
		
		return new UsuarioDAO().loadByLogin(login);
		
	}

}
