package br.unic.ra.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import br.unic.ra.gerador.bean.ObjetoBean;
import br.unic.ra.gerador.core.dao.UsuarioDAO;

public class ObjetoDAO {

	private Connection conn;
	private Properties queryFile;
	private PreparedStatement stmt;

	public void delete(ObjetoBean objeto) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("objetoDeleteByUser"));
			stmt.setInt(1, objeto.getUsuario().getId());		
			stmt.execute();
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
	
	public void deleteByUsuario(int usuarioId) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("objetoDeleteByUser"));
			stmt.setInt(1, usuarioId);
			stmt.execute();
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
	
	public void insert(ObjetoBean objeto) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("objetoInsert"));
			stmt.setInt(1, objeto.getUsuario().getId());
			stmt.setInt(2, objeto.getModelo().getId());
			stmt.setInt(3, objeto.getMarcador().getId());
			stmt.setBoolean(4, objeto.isPublico());
			stmt.setString(5, objeto.getDescricao());
			
			stmt.execute();
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
	
	public ObjetoBean loadById(int id) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("loadObjetoById"));
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Objeto não encontrado");
			}
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			ModeloDAO modeloDAO = new ModeloDAO();
			MarcadorDAO marcadorDAO = new MarcadorDAO();
			ObjetoBean objeto = new ObjetoBean();
			
			objeto.setId(id);
			objeto.setUsuario(usuarioDAO.loadById(rs.getInt(1)));
			objeto.setModelo(modeloDAO.loadById(rs.getInt(2)));
			objeto.setMarcador(marcadorDAO.loadById(rs.getInt(3)));
			objeto.setPublico(rs.getBoolean(4));
			objeto.setDescricao(rs.getString(5));
			
			return objeto;
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
	
	public ArrayList<ObjetoBean> loadObjetos() throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("loadObjetos"));
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ObjetoBean> objetos = new ArrayList<ObjetoBean>();
			
			while (rs.next()) {
				objetos.add(loadById(rs.getInt(1)));
			}		
			
			return objetos;
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
	
	public ObjetoBean loadObjetoByUsuario(int usuarioId) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("loadObjetoIdByUsuario"));
			stmt.setInt(1, usuarioId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Objeto não encontrado");
			}		
			
			return loadById(rs.getInt(1));
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
	
	public ArrayList<ObjetoBean> loadObjetosByUsuario(int usuarioId) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("loadObjetosIdByUsuario"));
			stmt.setInt(1, usuarioId);
			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ObjetoBean> objetos = new ArrayList<ObjetoBean>();
			
			while (rs.next()) {
				objetos.add(loadById(rs.getInt(1)));
			}		
			
			return objetos;
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
