package application;

import java.sql.Connection;

public class ProdutoDAO {
	private Connection connection;
	
	public ProdutoDAO() {
		connection = new ConnectionFactory().getConnection();
	}
}
