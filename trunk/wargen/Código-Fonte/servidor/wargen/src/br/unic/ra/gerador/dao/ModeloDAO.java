package br.unic.ra.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import br.unic.ra.gerador.bean.ModeloBean;
import br.unic.ra.gerador.manager.DBManager;

public class ModeloDAO {

	private static PreparedStatement stmt;

	public static void excluirModeloPorId(int id, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("excluirModeloPorId"));			
			stmt.setInt(1, id);
			stmt.execute();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public static void inserirModelo(ModeloBean modelo, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("inserirModelo"));			
			stmt.setInt(1, modelo.getUsuario().getId());
			stmt.setInt(2, modelo.getArquivoModelo().getId());
			
			if (modelo.getImagem() != null &&
				modelo.getImagem().getId() > 0) {			
				stmt.setInt(3, modelo.getImagem().getId());
			}
			else
			{
				stmt.setNull(3, Types.NULL);
			}
			
			stmt.execute();
		}
		catch (Exception exc){
			throw exc;
		}
	}

	public static ModeloBean carregarModeloPorId(int id, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarModeloPorId"));
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				return null;
			}
			
			ModeloBean modelo = new ModeloBean();
			modelo.setId(id);
			modelo.setUsuario(UsuarioDAO.carregarUsuarioPorId(rs.getInt("usuario"), conn));
			modelo.setArquivoModelo(ArquivoDAO.carregarArquivoPorId(rs.getInt("modelo"), conn));
			modelo.getArquivoModelo().setCaminho("/modelos/" + modelo.getArquivoModelo().getFullName());
			
			if (rs.getInt("imagem") > 0) {
				modelo.setImagem(ArquivoDAO.carregarArquivoPorId(rs.getInt("imagem"), conn));				
			}
			
			return modelo;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public static ArrayList<ModeloBean> listarModelosPorUsuario(int usuario, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("listarModelosPorUsuario"));
			stmt.setInt(1, usuario);
			
			ResultSet rs = stmt.executeQuery();
	
			ArrayList<ModeloBean> modelos = new ArrayList<ModeloBean>();
			
			while (rs.next()) {
				modelos.add(carregarModeloPorId(rs.getInt(1), conn));
			}
			
			return modelos;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
