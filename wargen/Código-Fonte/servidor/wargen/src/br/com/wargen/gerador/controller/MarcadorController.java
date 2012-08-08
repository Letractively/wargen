package br.com.wargen.gerador.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.com.wargen.gerador.bean.MarcadorBean;
import br.com.wargen.gerador.dao.ArquivoDAO;
import br.com.wargen.gerador.dao.MarcadorDAO;
import br.com.wargen.gerador.enums.TipoBanco;
import br.com.wargen.gerador.manager.DBManager;
import br.com.wargen.gerador.manager.FileManager;

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
			
			marcador.setArquivoMarcador(
					ArquivoDAO.carregarArquivoPorNomeExtensao(
							marcador.getArquivoMarcador().getNome(),
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

	public static ArrayList<MarcadorBean> listarMarcadoresPorUsuario(int usuarioId) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return MarcadorDAO.listarMarcadoresPorUsuario(usuarioId, conn);
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
