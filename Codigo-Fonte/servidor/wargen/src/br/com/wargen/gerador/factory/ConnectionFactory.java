package br.com.wargen.gerador.factory;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.wargen.gerador.enums.TipoBanco;
import br.com.wargen.gerador.properties.DatabaseProperties;

public class ConnectionFactory {

	public static Connection getConnection(TipoBanco tipoBanco) throws Exception {

		try {

			if (tipoBanco == TipoBanco.MySQL) {
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://" + DatabaseProperties.MYSQL_ADDRESS + "/" +
																	 DatabaseProperties.MYSQL_DATABASE,
																	 DatabaseProperties.MYSQL_DATABASE_USER, 
																	 DatabaseProperties.MYSQL_DATABASE_PASSWORD);
			}
			else {
				throw new Exception("Tipo de banco n�o suportado.");
			}
		}
		catch(Exception exc) {
			throw exc;
		}
		
	}
	
}
