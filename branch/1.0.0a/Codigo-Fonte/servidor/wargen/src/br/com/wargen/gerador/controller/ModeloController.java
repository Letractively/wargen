package br.com.wargen.gerador.controller;

import java.sql.Connection;
import java.util.ArrayList;

import br.com.wargen.gerador.bean.ModeloBean;
import br.com.wargen.gerador.dao.ArquivoDAO;
import br.com.wargen.gerador.dao.ModeloDAO;
import br.com.wargen.gerador.enums.TipoBanco;
import br.com.wargen.gerador.manager.DBManager;
import br.com.wargen.gerador.manager.FileManager;

public class ModeloController {

	public static void excluirModeloPorId(int id, String caminhoArquivo) throws Exception {

		Connection conn = null;
		ModeloBean modelo = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			conn.setAutoCommit(false);
			
			modelo = ModeloDAO.carregarModeloPorId(id, conn);

			ModeloDAO.excluirModeloPorId(id, conn);
			ArquivoDAO.excluirArquivoPorId(modelo.getArquivoModelo().getId(), conn);
			
			FileManager.excluirArquivo(caminhoArquivo + modelo.getArquivoModelo().getCaminho());
			
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
	
	public static void inserirModelo(ModeloBean modelo) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			conn.setAutoCommit(false);
			
			if (ArquivoController.verificarArquivoExisteBanco(modelo.getArquivoModelo())) {
				throw new Exception("O modelo já existe. Por favor, escolha outro modelo.");
			}
			
			ArquivoDAO.inserirArquivo(modelo.getArquivoModelo(), conn);
			modelo.setArquivoModelo(ArquivoDAO.carregarArquivoPorNomeExtensao(modelo.getArquivoModelo().getNome(),
																	   modelo.getArquivoModelo().getExtensao(),
																	   conn));

			if (modelo.getArquivoModelo() == null ||
				modelo.getArquivoModelo().getId() < 1) {
				throw new Exception("Erro ao carregar atributo 'modelo'");
			}
			
			ModeloDAO.inserirModelo(modelo, conn);
			
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

	public static ModeloBean carregarModeloPorId(int id) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return ModeloDAO.carregarModeloPorId(id, conn);
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
	
	public static ArrayList<ModeloBean> listarModelosPorUsuario(int usuarioId) throws Exception {
		
		Connection conn = null;
		
		try {			
			conn = DBManager.getInstance().getConnection(TipoBanco.MySQL);
			return ModeloDAO.listarModelosPorUsuario(usuarioId, conn);
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
