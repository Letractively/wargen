package br.unic.ra.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.unic.ra.gerador.bean.UsuarioBean;
import br.unic.ra.gerador.manager.DBManager;

public class UsuarioDAO {

	private static PreparedStatement stmt;
	private static ResultSet rs;
	
	public static void fazerLogin(String login, String senha, Connection conn) throws Exception{
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("fazerLogin"));
			stmt.setString(1, login);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			if ((!rs.next())) {
				throw new Exception("Usuário ou senha inválidos");
			}
			
		}
		catch (Exception exc){
			throw exc;
		}
	}

	public static UsuarioBean carregarUsuarioPorId(int id, Connection conn) throws Exception {
		
		UsuarioBean usuario = null;
		
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarUsuarioPorId"));
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {			
				usuario = new UsuarioBean();
				usuario.setId(id);
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setStatus(rs.getBoolean("status"));
			}
			
			return usuario;			
		}
		catch (Exception exc){
			throw exc;
		}
	}
	
	public static UsuarioBean carregarUsuarioPorLogin(String login, Connection conn) throws Exception{
		
		UsuarioBean usuario = null;
		
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarUsuarioPorLogin"));
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				usuario = new UsuarioBean();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(login);
				usuario.setStatus(rs.getBoolean(("status")));				
			}
			
			return usuario;			
		}
		catch (Exception exc){
			throw exc;
		}
	}

}
