package br.unic.ra.gerador.controller;

import java.sql.Connection;

import br.unic.ra.gerador.bean.UsuarioBean;
import br.unic.ra.gerador.dao.UsuarioDAO;
import br.unic.ra.gerador.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;

public class UsuarioController {
	
	public static void fazerLogin(String login, String senha) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			
			if (login == null || login.trim().equals("")) {
				throw new Exception("Login inv�lido.");
			}
			
			if (login == null || login.trim().equals("")) {
				throw new Exception("Senha inv�lida.");
			}
			
			UsuarioDAO.fazerLogin(login, senha, conn);
		}
		catch (Exception exc) {
			throw exc;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
		
	}

	public static UsuarioBean carregarUsuarioPorId(int id) throws Exception {
		
		Connection conn = null;
		
		try {
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);			
			return UsuarioDAO.carregarUsuarioPorId(id, conn);
		}
		catch (Exception exc) {
			throw exc;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public static UsuarioBean carregarUsuarioPorLogin(String login) throws Exception {

		Connection conn = null;
		
		if (login == null || login.trim().equals("")) {
			throw new Exception("Par�metro login inv�lido.");
		}
		
		try {
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);			
			return UsuarioDAO.carregarUsuarioPorLogin(login, conn);
		}
		catch (Exception exc) {
			throw exc;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}		
	}
}
