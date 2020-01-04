package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
	
	private Connection connection;
	
	public FornecedorDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarFornecedor(Fornecedor f) { 
		String sql = "insert into fornecedores (nome, cnpj, endereco, telefone, email, infoprodutos) values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, f.getNome());
			statement.setString(2, f.getCnpj());
			statement.setString(3, f.getEndereco());
			statement.setString(4, f.getTel());
			statement.setString(5, f.getEmail());
			statement.setString(6, f.getInfoProdutos());
			
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List <Fornecedor> consultarFornecedor(String nome) {
		
		List <Fornecedor> fornecedores = new ArrayList<>();
		
		String sql = "select * from fornecedores where nome like '%" + nome + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result =statement.executeQuery();
			
			while (result.next()) {
				Fornecedor fornecedor = new Fornecedor(result.getString("nome"), result.getString("cnpj"),
								  			 		   result.getString("endereco"), result.getString("telefone"), result.getString("email"),
								  			 		   result.getString("infoprodutos"));
				fornecedor.setId(result.getInt("id"));
				
				fornecedores.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return fornecedores;
	}
	
	public void deletar(Integer id) {
		String sql = "delete from fornecedores where id=?";
		
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
	
	public void atualizarFornecedor(Fornecedor f) {
		String sql = "update fornecedores set nome=?, cnpj=?, endereco=?, telefone=?, email=?, infoprodutos=? where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, f.getNome());
			statement.setString(2, f.getCnpj());
			statement.setString(3, f.getEndereco());
			statement.setString(4, f.getTel());
			statement.setString(5, f.getEmail());
			statement.setString(6, f.getInfoProdutos());
			statement.setInt(7, f.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
