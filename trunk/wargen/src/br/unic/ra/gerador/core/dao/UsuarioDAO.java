package br.unic.ra.gerador.core.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import br.unic.ra.gerador.core.bean.UsuarioBean;
import br.unic.ra.gerador.core.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;

public class UsuarioDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private Properties queryFile;
	private ResultSet rs;
	private DBManager dbManager;
	
	public UsuarioDAO() throws IOException {
		dbManager = DBManager.getInstance();
	}
	
	public boolean doLogin(String login, String senha) throws Exception{
		try {

			queryFile = dbManager.getQueryFile();
			conn = dbManager.getConnection(TipoBanco.MySQL);
			stmt = conn.prepareStatement(queryFile.getProperty("usuarioDoLogin"));
			stmt.setString(1, login);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			return rs.next();
			
		}
		catch (Exception exc){
			throw exc;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public UsuarioBean loadById(int id) throws Exception{
		try {

			queryFile = dbManager.getQueryFile();
			conn = dbManager.getConnection(TipoBanco.MySQL);
			stmt = conn.prepareStatement(queryFile.getProperty("usuarioLoadById"));
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Usuário não encontrado");
			}
			
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(id);
			usuario.setNome(rs.getString("nome"));
			usuario.setLogin(rs.getString("login"));
			usuario.setStatus(rs.getBoolean(("status")));
			
			return usuario;
			
		}
		catch (Exception exc){
			throw exc;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public UsuarioBean loadByLogin(String login) throws Exception{
		try {

			queryFile = dbManager.getQueryFile();
			conn = dbManager.getConnection(TipoBanco.MySQL);
			stmt = conn.prepareStatement(queryFile.getProperty("usuarioLoadByLogin"));
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Usuário não encontrado");
			}
			
			UsuarioBean usuario = new UsuarioBean();
			usuario.setId(rs.getInt("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setLogin(login);
			usuario.setStatus(rs.getBoolean(("status")));
			
			return usuario;
			
		}
		catch (Exception exc){
			throw exc;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
