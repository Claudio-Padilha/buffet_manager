package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class FuncionarioDAO {
	
	private Connection connection;
	
	public FuncionarioDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void cadastrar(Funcionario f) {
		String sql = "insert into funcionario (nome, data_contratacao, telefone, salario) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, f.getNome());
			statement.setDate(2, f.getData());
			statement.setString(3, f.getTel());
			statement.setDouble(4, f.getSalario());
			
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
			statement.setDate(2, f.getData());
			statement.setString(3, f.getTel());
			statement.setInt(4, f.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void deletar(Integer id) {
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
	
	private List <Funcionario> consultar(String nome) {
		
		List <Funcionario> funcionarios = new ArrayList<>();
		
		String sql = "select * from funcionario where nome like '%" + nome + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result =statement.executeQuery();
			
			while (result.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(result.getInt("id"));
				funcionario.setNome(result.getString("nome"));
				funcionario.setData(result.getDate("data_contatacao"));
				
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return funcionarios;
	}
}
