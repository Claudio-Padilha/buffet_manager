package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroFuncionarioController implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new FuncionarioDAO();
		
		nomeFuncionarios.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cpfFuncionarios.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		rgFuncionarios.setCellValueFactory(new PropertyValueFactory<>("rg"));
		enderecoFuncionarios.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		telefoneFuncionarios.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		emailFuncionarios.setCellValueFactory(new PropertyValueFactory<>("email"));
		cargoFuncionarios.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		dataAdmFuncionarios.setCellValueFactory(new PropertyValueFactory<>("data_contratacao"));
		
	}
	
	@FXML private TextField nomeNovoFuncionario;
	
	@FXML private TextField cpfNovoFuncionario;
	
	@FXML private TextField rgNovoFuncionario;
	
	@FXML private TextField enderecoNovoFuncionario;
	
	@FXML private TextField telefoneNovoFuncionario;
	
	@FXML private TextField emailNovoFuncionario;
	
	@FXML private TextField cargoNovoFuncionario;
	
	@FXML private TextField salarioNovoFuncionario;
	
	@FXML private TextField txtConsultaFuncionario;	
	
	@FXML private TableView<Funcionario> tabelaFuncionarios;	
    
    @FXML private TableColumn<Funcionario, String> nomeFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> cpfFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> rgFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> enderecoFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> telefoneFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> emailFuncionarios;
    
    @FXML private TableColumn<Funcionario, String> cargoFuncionarios;
    
    @FXML private TableColumn<Funcionario, Date> dataAdmFuncionarios;
    
    @FXML private ResourceBundle resources;

    @FXML private URL location;

    private FuncionarioDAO dao;
	
	@FXML void salvarNovoFuncionario() {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nomeNovoFuncionario.getText());
		funcionario.setCpf(cpfNovoFuncionario.getText());
		funcionario.setRg(rgNovoFuncionario.getText());
		funcionario.setEndereco(enderecoNovoFuncionario.getText());
		funcionario.setTel(telefoneNovoFuncionario.getText());
		funcionario.setEmail(emailNovoFuncionario.getText());
		funcionario.setCargo(cargoNovoFuncionario.getText());
		funcionario.setSalario(Double.parseDouble(salarioNovoFuncionario.getText()));
		
		try {
			dao.cadastrar(funcionario);
			exibirDialogoInformacao ("Funcionário Cadastrado com sucesso!");
			limparCadastroNovoFuncionario();
		}catch (Exception e){
			exibirDialogoErro("Falha ao cadastrar funcionário");
			e.printStackTrace();
		}
		
		
	}
	
	@FXML void limparCadastroNovoFuncionario() {
		nomeNovoFuncionario.clear();
		cpfNovoFuncionario.clear();
		rgNovoFuncionario.clear();
		enderecoNovoFuncionario.clear();
		telefoneNovoFuncionario.clear();
		emailNovoFuncionario.clear();
		cargoNovoFuncionario.clear();
		salarioNovoFuncionario.clear();
	}
	
	@FXML void consultarFuncionario() {
		
		try {
			
			List<Funcionario> resultado = dao.consultar(txtConsultaFuncionario.getText());
			
			if (resultado.isEmpty()) {
				exibirDialogoInformacao("Nenhum funcionário encontrado");
				
			}else {
				tabelaFuncionarios.setItems(FXCollections.observableList(resultado));
				
			}
		}catch(Exception e) {
			exibirDialogoErro("Falha na consulta");
			e.printStackTrace();
		}
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
}