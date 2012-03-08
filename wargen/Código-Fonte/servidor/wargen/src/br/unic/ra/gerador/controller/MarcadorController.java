package br.unic.ra.gerador.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.unic.ra.gerador.bean.MarcadorBean;
import br.unic.ra.gerador.dao.ArquivoDAO;
import br.unic.ra.gerador.dao.MarcadorDAO;
import br.unic.ra.gerador.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;
import br.unic.ra.gerador.manager.FileManager;

public class MarcadorController {
	
	public static void excluirMarcadorPorId(int id, String caminhoArquivo) throws Exception {

		Connection conn = null;
		MarcadorBean marcador = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			conn.setAutoCommit(false);
			
			marcador = MarcadorDAO.carregarMarcadorPorId(id, conn);

			MarcadorDAO.excluirMarcadorPorId(id, conn);
			ArquivoDAO.excluirArquivoPorId(marcador.getArquivoMarcador().getId(), conn);
			
			FileManager.excluirArquivo(caminhoArquivo + marcador.getArquivoMarcador().getCaminho());
			
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
	
	public static void inserirMarcador(MarcadorBean marcador) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			conn.setAutoCommit(false);
			
			if (ArquivoController.verificarArquivoExisteBanco(marcador.getArquivoMarcador())) {
				throw new Exception("O marcador já existe. Por favor, escolha outro marcador.");
			}
			
			ArquivoDAO.inserirArquivo(marcador.getArquivoMarcador(), conn);
			marcador.setArquivoMarcador(ArquivoDAO.carregarArquivoPorNomeExtensao(marcador.getArquivoMarcador().getNome(),
																		   marcador.getArquivoMarcador().getExtensao(),
																		   conn));
			
			if (marcador.getArquivoMarcador() == null ||
				marcador.getArquivoMarcador().getId() < 1) {
				throw new Exception("Erro ao carregar atributo 'marcador'");
			}
			
			MarcadorDAO.inserirMarcador(marcador, conn);
			
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

	public static MarcadorBean carregarMarcadorPorId(int id) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return MarcadorDAO.carregarMarcadorPorId(id, conn);
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

	public static ArrayList<MarcadorBean> listarMarcadoresPorUsuario(int usuario) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return MarcadorDAO.listarMarcadoresPorUsuario(usuario, conn);
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
