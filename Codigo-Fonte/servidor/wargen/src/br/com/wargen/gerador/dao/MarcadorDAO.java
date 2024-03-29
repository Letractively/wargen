package br.com.wargen.gerador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import br.com.wargen.gerador.bean.MarcadorBean;
import br.com.wargen.gerador.manager.DBManager;

public class MarcadorDAO {

	public static void excluirMarcadorPorId(int id, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("excluirMarcadorPorId"));			
			stmt.setInt(1, id);
			stmt.execute();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public static void inserirMarcador(MarcadorBean marcador, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("inserirMarcador"));			
			stmt.setInt(1, marcador.getUsuario().getId());
			stmt.setInt(2, marcador.getArquivoMarcador().getId());
			
			if (marcador.getArquivoImprimir() != null &&
				marcador.getArquivoImprimir().getId() > 0) {			
				stmt.setInt(3, marcador.getArquivoImprimir().getId());
			}
			else
			{
				stmt.setNull(3, Types.NULL);
			}
			
			stmt.execute();
		}
		catch (Exception e) {
			throw e;
		}
	}

	public static MarcadorBean carregarMarcadorPorId(int id, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("carregarMarcadorPorId"));
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Marcador n�o encontrado");
			}
			
			MarcadorBean marcador = new MarcadorBean();			
			marcador.setId(id);
			marcador.setUsuario(UsuarioDAO.carregarUsuarioPorId(rs.getInt("usuario"), conn));
			marcador.setArquivoMarcador(ArquivoDAO.carregarArquivoPorId(rs.getInt("marcador"), conn));
			marcador.getArquivoMarcador().setCaminho("/marcadores/" + marcador.getArquivoMarcador().getFullName());
			
			if (rs.getInt("arquivo_imprimir") > 0) {
				marcador.setArquivoImprimir(ArquivoDAO.carregarArquivoPorId(rs.getInt("arquivo_imprimir"), conn));
			}
			
			return marcador;
		}
		catch (Exception e) {
			throw e;
		}
	}

	public static ArrayList<MarcadorBean> listarMarcadoresPorUsuario(int usuarioId, Connection conn) throws Exception {
		try {
			PreparedStatement stmt = conn.prepareStatement(DBManager.getInstance().getQueryFile().getProperty("listarMarcadoresPorUsuario"));
			stmt.setInt(1, usuarioId);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<MarcadorBean> marcadores = new ArrayList<MarcadorBean>();
			
			while (rs.next()) {
				marcadores.add(carregarMarcadorPorId(rs.getInt("id"), conn));
			}
			
			return marcadores;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean verificarMarcadorAssociado(int marcadorId, Connection conn) throws Exception {
		try {			
			PreparedStatement stmt = conn.prepareStatement(new DBManager().getQueryFile().getProperty("verificarMarcadorAssociado"));
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
