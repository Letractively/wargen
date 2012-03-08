package br.unic.ra.gerador.core.controller;

import br.unic.ra.gerador.core.bean.UsuarioBean;
import br.unic.ra.gerador.core.dao.UsuarioDAO;

public class UsuarioController {
	
	public void doLogin(String login, String senha) throws Exception {
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Login inválido.");
		}
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Senha inválida.");
		}
		
		if (!new UsuarioDAO().doLogin(login, senha)) {
			throw new Exception("Usuário ou senha inválidos");
		}
		
	}
	
	public UsuarioBean loadByLogin(String login) throws Exception {
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Parãmetro login inválido.");
		}
		
		return new UsuarioDAO().loadByLogin(login);
		
	}

}
