package br.unic.ra.gerador.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.unic.ra.gerador.bean.AssociacaoBean;
import br.unic.ra.gerador.dao.AssociacaoDAO;
import br.unic.ra.gerador.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;
import br.unic.ra.gerador.manager.FileManager;

public class AssociacaoController {

	public static void excluirAssociacao(int id) throws Exception {
		
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
