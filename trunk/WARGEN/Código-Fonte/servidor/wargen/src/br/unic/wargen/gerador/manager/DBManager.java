package br.unic.wargen.gerador.manager;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import br.unic.wargen.gerador.enums.TipoBanco;
import br.unic.wargen.gerador.factory.ConnectionFactory;
import br.unic.wargen.gerador.properties.DatabaseProperties;

public class DBManager {
	
	private static DBManager ref;
	private static Properties queryFile;
	
	public DBManager() throws IOException {
		loadQueryFile();
	}
	
	public static final DBManager getInstance() throws IOException {
		if (ref == null) {
			ref = new DBManager();
		}		
		return ref;
	}
	
	private void loadQueryFile() throws IOException {
		queryFile = new Properties();
		queryFile.load(getClass().getResourceAsStream(DatabaseProperties.QUERY_FILE));
	}
	
	public Properties getQueryFile() {
		return queryFile;
	}
	
	public Connection getConnection(TipoBanco tipoBanco) throws Exception{

		return ConnectionFactory.getConnection(tipoBanco);		
		
	}
	
}
