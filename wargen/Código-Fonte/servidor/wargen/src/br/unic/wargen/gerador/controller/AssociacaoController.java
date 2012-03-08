package br.unic.wargen.gerador.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.unic.wargen.gerador.bean.AssociacaoBean;
import br.unic.wargen.gerador.dao.AssociacaoDAO;
import br.unic.wargen.gerador.enums.TipoBanco;
import br.unic.wargen.gerador.manager.DBManager;
import br.unic.wargen.gerador.manager.FileManager;

public class AssociacaoController {

	public static void excluirAssociacaoPorId(int id) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);	
			conn.setAutoCommit(false);
			
			AssociacaoDAO.excluirAssociacaoPorId(id, conn);
			
			conn.commit();
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
	
	public static void inserirAssociacao(AssociacaoBean associacao) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			conn.setAutoCommit(false);
			
			AssociacaoDAO.inserirAssociacao(associacao, conn);
			FileManager.criarArquivoParametros(associacao);
			
			conn.commit();
		}
		catch (Exception e) {
			conn.rollback();
			throw e;
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public static AssociacaoBean carregarAssociacaoPorId(int id) throws Exception {

		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return AssociacaoDAO.carregarAssociacaoPorId(id, conn);
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

	public static ArrayList<AssociacaoBean> listarAssociacoes() throws Exception {
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return AssociacaoDAO.listarAssociacoes(conn);
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
