package br.com.wargen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilitariosDB {
	
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://" + Configuracoes.SERVER_ADDRESS + "/" +
				"wargen",
				Configuracoes.SERVER_USER, 
				Configuracoes.SERVER_PASSWORD);
	}
	
	public static Connection getConexao(String endereco, String usuario, String senha) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://" + endereco + "/" +
															 "wargen",
															 usuario, 
															 senha);
	}
}
