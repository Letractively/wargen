package br.unic.ra.gerador.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import br.unic.ra.gerador.bean.ArquivoBean;
import br.unic.ra.gerador.core.enums.TipoBanco;
import br.unic.ra.gerador.manager.DBManager;

public class ArquivoDAO {

	private Connection conn;
	private DBManager dbManager;
	private Properties queryFile;
	private PreparedStatement stmt;	
	
	public ArquivoDAO() throws IOException {
		this.dbManager = DBManager.getInstance();
	}
	
	public boolean check(String nome, String extensao) throws Exception {
		try {
			
			conn = dbManager.getConnection(TipoBanco.MySQL);
			
			stmt = conn.prepareStatement(queryFile.getProperty("arquivoCheckByNomeExtensao"));
			stmt.setString(1, nome);
			stmt.setString(2, extensao);
	
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt(1) > 0;
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
	
	public void deleteDeleteByNomeExtensao(String nome, String extensao) throws Exception {
		try {
			conn = dbManager.getConnection(TipoBanco.MySQL);
			
			stmt = conn.prepareStatement(queryFile.getProperty("arquivoDeleteByNomeExtensao"));
			stmt.setString(1, nome);
			stmt.setString(2, extensao);
			stmt.execute();
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

	public void insert(ArquivoBean arquivo) throws Exception {
		try {
			conn = dbManager.getConnection(TipoBanco.MySQL);
			
			stmt = conn.prepareStatement(queryFile.getProperty("arquivoInsert"));
			stmt.setString(1, arquivo.getNome());
			stmt.setString(2, arquivo.getExtensao());
			stmt.setString(3, arquivo.getNomeApresentacao());
			stmt.execute();
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

	public ArquivoBean loadById(int id) throws Exception {
		try {
			stmt = conn.prepareStatement(queryFile.getProperty("loadArquivoById"));
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Arquivo não encontrado");
			}
			
			ArquivoBean arquivo = new ArquivoBean();
			arquivo.setId(id);
			arquivo.setNome(rs.getString(1));
			arquivo.setExtensao(rs.getString(2));
			arquivo.setNomeApresentacao(rs.getString(3));
			
			return arquivo;
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

	public ArquivoBean loadByNomeExtensao(String nome, String extensao) throws Exception {
		try {
			conn = dbManager.getConnection(TipoBanco.MySQL);
			
			stmt = conn.prepareStatement(queryFile.getProperty("loadArquivoByNomeExtensao"));
			stmt.setString(1, nome);
			stmt.setString(2, extensao);
			ResultSet rs = stmt.executeQuery();
			
			if (!(rs.next())) {
				throw new Exception("Arquivo não encontrado");
			}
			
			ArquivoBean arquivo = new ArquivoBean();
			arquivo.setId(rs.getInt(1));
			arquivo.setNome(nome);
			arquivo.setExtensao(extensao);
			arquivo.setNomeApresentacao(rs.getString(2));
			
			return arquivo;
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
