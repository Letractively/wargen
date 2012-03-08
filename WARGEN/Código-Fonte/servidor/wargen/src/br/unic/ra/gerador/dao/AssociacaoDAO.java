package br.unic.ra.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.unic.ra.gerador.bean.AssociacaoBean;
import br.unic.ra.gerador.manager.DBManager;

public class AssociacaoDAO {

	private static PreparedStatement stmt;

	public static void excluirAssociacaoPorId(int id, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("excluirAssociacao"));
			stmt.setInt(1, id);		
			stmt.execute();
		}
		catch (Exception exc){
			throw exc;
		}
	}
	
	public static void inserirAssociacao(AssociacaoBean associacao, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("inserirAssociacao"));
			stmt.setInt(1, associacao.getUsuario().getId());
			stmt.setInt(2, associacao.getModelo().getId());
			stmt.setInt(3, associacao.getMarcador().getId());
			stmt.setString(4, associacao.getDescricao());
			stmt.setBoolean(5, associacao.isPublico());
			stmt.setBoolean(6, associacao.isMovimento());
			stmt.setBoolean(7, associacao.isRotacao());
			stmt.setBoolean(8, associacao.isEscala());
			
			stmt.execute();
		}
		catch (Exception exc){
			throw exc;
		}
	}
	
	public static AssociacaoBean carregarAssociacaoPorId(int id, Connection conn) throws Exception {
		try {
			stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarAssociacaoPorId"));
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				return null;
			}
			
			AssociacaoBean associacao = new AssociacaoBean();
			
			associacao.setId(id);
			associacao.setUsuario(UsuarioDAO.carregarUsuarioPorId(rs.getInt("usuario"), conn));
			associacao.setModelo(ModeloDAO.carregarModeloPorId(rs.getInt("modelo"), conn));
			associacao.setMarcador(MarcadorDAO.carregarMarcadorPorId(rs.getInt("marcador"), conn));
			associacao.setDescricao(rs.getString("descricao"));
			associacao.setPublico(rs.getBoolean("publico"));
			associacao.setMovimento(rs.getBoolean("movimento"));
			associacao.setRotacao(rs.getBoolean("rotacao"));
			associacao.setEscala(rs.getBoolean("escala"));
			
			return associacao;
		}
		catch (Exception exc){
			throw exc;
		}
	}

	public static ArrayList<AssociacaoBean> listarAssociacoes(Connection conn) throws Exception {
		
		ArrayList<AssociacaoBean> listaAssociacoes = null;
		AssociacaoBean associacao = null;
		
		try {
			stmt = conn.prepareStatement(new DBManager().getQueryFile().getProperty("listarAssociacoes"));
			
			ResultSet rs = stmt.executeQuery();
			
			listaAssociacoes = new ArrayList<AssociacaoBean>();
			
			while (rs.next()) {	
				associacao = new AssociacaoBean();
				associacao.setId(rs.getInt("id"));
				associacao.setUsuario(UsuarioDAO.carregarUsuarioPorId(rs.getInt("usuario"), conn));
				associacao.setModelo(ModeloDAO.carregarModeloPorId(rs.getInt("modelo"), conn));
				associacao.setMarcador(MarcadorDAO.carregarMarcadorPorId(rs.getInt("marcador"), conn));
				associacao.setDescricao(rs.getString("descricao"));
				associacao.setPublico(rs.getBoolean("publico"));
				associacao.setMovimento(rs.getBoolean("movimento"));
				associacao.setRotacao(rs.getBoolean("rotacao"));
				associacao.setEscala(rs.getBoolean("escala"));
				
				listaAssociacoes.add(associacao);
			}
			
			return listaAssociacoes;
		}
		catch (Exception e) {
			throw e;
		}
	}

}
