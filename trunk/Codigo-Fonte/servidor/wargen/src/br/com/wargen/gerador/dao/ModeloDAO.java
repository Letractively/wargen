package br.com.wargen.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import br.com.wargen.gerador.bean.ModeloBean;
import br.com.wargen.gerador.manager.DBManager;

public class ModeloDAO {

	public static void excluirModeloPorId(int id, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("excluirModeloPorId"));			
			stmt.setInt(1, id);
			stmt.execute();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public static void inserirModelo(ModeloBean modelo, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("inserirModelo"));			
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
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarModeloPorId"));
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
	
	public static ArrayList<ModeloBean> listarModelosPorUsuario(int usuarioId, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("listarModelosPorUsuario"));
			stmt.setInt(1, usuarioId);
			
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
	
	public static boolean verificarModeloAssociado(int marcadorId, Connection conn) throws Exception {
		try {			
			PreparedStatement stmt = conn.prepareStatement(new DBManager().getQueryFile().getProperty("verificarModeloAssociado"));
			stmt.setInt(1, marcadorId);
	
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt(1) > 0;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
