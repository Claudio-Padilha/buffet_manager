package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	private Connection connection;
	
	public ProdutoDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarProduto(Produto p) {
		String sql = "insert into produtos (preco, nome, categoria) values (?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDouble(1, p.getPreco());
			statement.setString(2, p.getNome());
			statement.setString(3, p.getCategoria());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id) {
		String sql = "delete from produtos where id=?";
		
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
	
	public void atualizarProduto(Produto p) {
		String sql = "update produtos set nome=?, preco=?, categoria=? where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, p.getNome());
			statement.setDouble(2, p.getPreco());
			statement.setString(3, p.getCategoria());
			statement.setInt(4, p.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List <Produto> consultar(String nome) {
		
		List <Produto> produtos = new ArrayList<>();
		
		String sql = "select * from produtos where nome like '%" + nome + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result =statement.executeQuery();
			
			while (result.next()) {
				Produto produto = new Produto(result.getString("nome"), result.getDouble("preco"),
								  			  result.getString("categoria"));
				produto.setId(result.getInt("id"));
				
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return produtos;
	}
	
	public List <Produto> buscarPorCategoria(String categoria) {
		List <Produto> produtos = new ArrayList<>();
		
		String sql = "select * from produtos where categoria=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, categoria);
			
			ResultSet result =statement.executeQuery();
			
			while (result.next()) {
				Produto produto = new Produto(result.getString("nome"), result.getDouble("preco"),
			  			  result.getString("categoria"));
				produto.setId(result.getInt("id"));

				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return produtos;
	}
}
