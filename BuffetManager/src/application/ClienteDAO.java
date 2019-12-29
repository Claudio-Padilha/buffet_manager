package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ClienteDAO {
	private Connection connection;
	
	public ClienteDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarCliente(Cliente c) {
		String sql = "insert into clientes (nome, telefone, email, endereco) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, c.getNome());
			statement.setString(2, c.getTel());
			statement.setString(3, c.getEmail());
			statement.setString(4, c.getEndereco());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void atualizarCliente(Cliente c) {
		String sql = "update clientes set nome=?, telefone=?, email=?, endereco=? where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, c.getNome());
			statement.setString(2, c.getTel());
			statement.setString(3, c.getEmail());
			statement.setString(4, c.getEndereco());
			statement.setInt(5, c.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id) {
		String sql = "delete from clientes where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List <Cliente> consultar(String nome) {
		
		List <Cliente> clientes = new ArrayList<>();
		
		String sql = "select * from clientes where nome like '%" + nome + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result =statement.executeQuery();
			
			while (result.next()) {
				Cliente cliente = new Cliente(result.getString("nome"), result.getString("telefone"),
								  			  result.getString("email"), result.getString("endereco"));
				cliente.setId(result.getInt("id"));
				
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return clientes;
	}
}
