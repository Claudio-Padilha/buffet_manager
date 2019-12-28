package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
	
	private Connection connection;
	
	public FuncionarioDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void cadastrar(Funcionario f) {
		String sql = "insert into funcionario (nome, cpf, rg, endereco, telefone, email, cargo, salario) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, f.getNome());
			statement.setString(2, f.getCpf());
			statement.setString(3, f.getRg());
			statement.setString(4, f.getEndereco());
			statement.setString(5, f.getTel());
			statement.setString(6, f.getEmail());
			statement.setString(7, f.getCargo());
			statement.setDouble(8, f.getSalario());
			// statement.setDate(9, f.getData());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void atualizar(Funcionario f) {
		String sql = "update into funcionario set nome=?, telefone=? where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, f.getNome());
			// statement.setDate(2, f.getData());
			statement.setString(3, f.getTel());
			statement.setInt(4, f.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id) {
		String sql = "delete from funcionario where id=?";
		
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
	
	public List <Funcionario> consultar(String nome) {
		
		List <Funcionario> funcionarios = new ArrayList<>();
		
		String sql = "select * from funcionario where nome like '%" + nome + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result =statement.executeQuery();
			
			while (result.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(result.getString("nome"));
				funcionario.setCpf(result.getString("cpf"));
				funcionario.setRg(result.getString("rg"));
				funcionario.setEndereco(result.getString("endereco"));
				funcionario.setTel(result.getString("telefone"));
				funcionario.setEmail(result.getString("email"));
				// funcionario.setData(result.getDate("dataContratacao"));
				funcionario.setCargo(result.getString("cargo"));
				funcionario.setId(result.getInt("id"));
				
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return funcionarios;
	}
}
