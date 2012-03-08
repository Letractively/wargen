package br.unic.wargen.gerador.controller;

import java.sql.Connection;

import br.unic.wargen.gerador.bean.UsuarioBean;
import br.unic.wargen.gerador.dao.UsuarioDAO;
import br.unic.wargen.gerador.enums.TipoBanco;
import br.unic.wargen.gerador.manager.DBManager;

public class UsuarioController {
	
	public static void fazerLogin(String login, String senha) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			
			if (login == null || login.trim().equals("")) {
				throw new Exception("Login inválido.");
			}
			
			if (login == null || login.trim().equals("")) {
				throw new Exception("Senha inválida.");
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
			throw new Exception("Parãmetro login inválido.");
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
