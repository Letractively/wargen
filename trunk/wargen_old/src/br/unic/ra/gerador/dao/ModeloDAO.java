package br.unic.ra.gerador.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import br.unic.ra.gerador.bean.ModeloBean;
import br.unic.ra.gerador.core.dao.UsuarioDAO;
import br.unic.ra.gerador.core.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;

public class ModeloDAO {

	private Connection conn;
	private DBManager dbManager;
	private Properties queryFile;
	private PreparedStatement stmt;
	
	public ModeloDAO() throws IOException {
		this.dbManager = DBManager.getInstance();
	}

	public void insert(ModeloBean modelo) throws Exception {
		try {
			conn = dbManager.getConnection(TipoBanco.MySQL);
			
			stmt = conn.prepareStatement(queryFile.getProperty("modeloInsert"));
			
			ArquivoDAO arquivoDAO = new ArquivoDAO();
			
			if ((modelo.getModelo() != (null))) {
				arquivoDAO.insert(modelo.getModelo());
				
				modelo.setModelo(arquivoDAO.loadByNomeExtensao(modelo.getModelo().getNome(), modelo.getModelo().getExtensao()));
			}
			
			if (!(modelo.getImagem() == (null))) {
				arquivoDAO.insert(modelo.getImagem());
				
				modelo.setImagem(arquivoDAO.loadByNomeExtensao(modelo.getImagem().getNome(), modelo.getImagem().getExtensao()));
			}
			
			stmt.setInt(1, modelo.getUsuario().getId());
			stmt.setInt(2, (modelo.getModelo() == (null) ? null : modelo.getModelo().getId()));
			stmt.setObject(3, null);
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

	public ModeloBean loadById(int id) throws Exception {
		try {
			conn = dbManager.getConnection(TipoBanco.MySQL);
			
			stmt = conn.prepareStatement(queryFile.getProperty("loadModeloById"));
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Modelo não encontrado");
			}
			
			ArquivoDAO arquivoDAO = new ArquivoDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			ModeloBean modelo = new ModeloBean();
			modelo.setId(id);
			modelo.setUsuario(usuarioDAO.loadById(rs.getInt(1)));
			modelo.setModelo(arquivoDAO.loadById(rs.getInt(2)));
			modelo.setImagem((rs.getInt(3) < 1) ? null : arquivoDAO.loadById(rs.getInt(3)));
			
			return modelo;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public ArrayList<ModeloBean> loadByUsuario(int usuario) throws Exception {
		try {
			conn = dbManager.getConnection(TipoBanco.MySQL);
		
			stmt = conn.prepareStatement(queryFile.getProperty("loadModelosByUsuario"));
			stmt.setInt(1, usuario);
			
			ResultSet rs = stmt.executeQuery();
	
			ArrayList<ModeloBean> modelos = new ArrayList<ModeloBean>();
			
			while (rs.next()) {
				modelos.add(loadById(rs.getInt(1)));
			}
			
			return modelos;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
}
