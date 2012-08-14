package br.com.wargen.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.wargen.gerador.bean.UsuarioBean;
import br.com.wargen.gerador.manager.DBManager;

public class UsuarioDAO {
	
	public static void fazerLogin(String login, String senha, Connection conn) throws Exception{
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("fazerLogin"));
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			
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
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarUsuarioPorId"));
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
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
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarUsuarioPorLogin"));
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			
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
