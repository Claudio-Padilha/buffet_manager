package application;

import java.sql.Connection;

public class VendaDAO {
	private Connection connection;
	
	public VendaDAO() {
		connection = new ConnectionFactory().getConnection();
	}
}
