package br.unic.ra.gerador.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.unic.ra.gerador.bean.ArquivoBean;
import br.unic.ra.gerador.dao.ArquivoDAO;
import br.unic.ra.gerador.dao.MarcadorDAO;
import br.unic.ra.gerador.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;
import br.unic.ra.gerador.manager.FileManager;

public class ArquivoController {
	
	public static boolean verificarArquivoExisteBanco(ArquivoBean arquivo) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return ArquivoDAO.verificarArquivoExisteBanco(arquivo, conn);
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
	
	public static void excluirArquivoPorId(int id, String caminhoArquivo) throws Exception {

		Connection conn = null;
		ArquivoBean arquivo = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			conn.setAutoCommit(false);
			
			arquivo = ArquivoDAO.carregarArquivoPorId(id, conn);

			MarcadorDAO.excluirMarcadorPorId(id, conn);
			ArquivoDAO.excluirArquivoPorId(arquivo.getId(), conn);
			
			FileManager.excluirArquivo(caminhoArquivo + arquivo.getCaminho());
			
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
	
	public static void excluirPorNomeExtensao(String nome, String extensao) throws Exception {

		Connection conn = null;
		
		try {
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			ArquivoDAO.excluirArquivoPorNomeExtensao(nome, extensao, conn);
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

	public static void inserirArquivo(ArquivoBean arquivo) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			
			if (verificarArquivoExisteBanco(arquivo)) {
				throw new Exception("O arquivo já existe. Por favor, escolha outro arquivo.");
			}
			
			ArquivoDAO.inserirArquivo(arquivo, conn);
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

	public static ArquivoBean carregarArquivoPorId(int id) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return ArquivoDAO.carregarArquivoPorId(id, conn);
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

	public static ArquivoBean carregarArquivoPorNomeExtensao(String nome, String extensao) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return ArquivoDAO.carregarArquivoPorNomeExtensao(nome, extensao, conn);
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
	
	public static ArrayList<ArquivoBean> listarArquivosPorUsuario(int usuarioId) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return ArquivoDAO.listarArquivosPorUsuario(usuarioId, conn);
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
