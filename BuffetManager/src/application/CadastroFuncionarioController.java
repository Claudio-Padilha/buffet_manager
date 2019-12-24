package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroFuncionarioController implements Initializable{
	@FXML private TextField nomeNovoFuncionario;
	
	@FXML private TextField telefoneNovoFuncionario;
	
	@FXML private TextField salarioNovoFuncionario;
	
	@FXML private ResourceBundle resources;

    @FXML private URL location;
    
    @FXML private TableColumn<Funcionario, Integer> idFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> nomeFuncionarios;
    
    @FXML private TableColumn<Funcionario, Date> dataAdmFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> telFuncionarios;

    private FuncionarioDAO dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new FuncionarioDAO();
		
		// idFuncionarios.setCellValueFactory(new PropertyValueFactory<>("id"));
		// nomeFuncionarios.setCellValueFactory(new PropertyValueFactory<>("nome"));
		// dataAdmFuncionarios.setCellValueFactory(new PropertyValueFactory<>("data_contatacao"));
		// telFuncionarios.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	}
	
	private void exibirDialogoInformacao (String informacao) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informação");
		alert.setHeaderText(null);
		alert.setContentText(informacao);
		
		alert.showAndWait();
	}
	
	private void exibirDialogoErro (String erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(erro);
		
		alert.showAndWait();
	}
	
	@FXML void salvarNovoFuncionario() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nomeNovoFuncionario.getText());
		funcionario.setTel(telefoneNovoFuncionario.getText());
		funcionario.setSalario(Double.parseDouble(salarioNovoFuncionario.getText()));
		
		dao.cadastrar(funcionario);
	}
}
