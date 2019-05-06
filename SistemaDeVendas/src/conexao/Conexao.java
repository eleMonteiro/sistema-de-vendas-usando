package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private final String host = "localhost";
	private final Integer port = 3306;
	private final String user = "root";
	private final String password = "root";
	private final String database = "control_estoque";
	private static Conexao conexao = null;
	
	private Conexao() {
		
	}
	
	public static Conexao getInstance() {
		if( conexao == null )
			conexao = new Conexao();
		return conexao;
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database, user,
					password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
