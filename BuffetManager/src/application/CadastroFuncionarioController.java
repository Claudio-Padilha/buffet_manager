package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroFuncionarioController implements Initializable{
	
	private FuncionarioDAO dao;
    
	private Funcionario funcionarioSelecionado;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new FuncionarioDAO();
		
		idFuncionarios.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeFuncionarios.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cpfFuncionarios.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		rgFuncionarios.setCellValueFactory(new PropertyValueFactory<>("rg"));
		enderecoFuncionarios.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		telefoneFuncionarios.setCellValueFactory(new PropertyValueFactory<>("tel"));
		emailFuncionarios.setCellValueFactory(new PropertyValueFactory<>("email"));
		cargoFuncionarios.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		salarioFuncionarios.setCellValueFactory(new PropertyValueFactory<>("salario"));
		// dataAdmFuncionarios.setCellValueFactory(new PropertyValueFactory<>("data_contratacao"));
		
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
	
	@FXML private TextField nomeAtualizarFuncionario;
	@FXML private TextField cpfAtualizarFuncionario;
	@FXML private TextField rgAtualizarFuncionario;
	@FXML private TextField enderecoAtualizarFuncionario;
	@FXML private TextField telefoneAtualizarFuncionario;
	@FXML private TextField emailAtualizarFuncionario;
	@FXML private TextField cargoAtualizarFuncionario;
	@FXML private TextField salarioAtualizarFuncionario;	
	
	@FXML private TabPane abas;
	
	@FXML private Tab abaCadastrar;
	@FXML private Tab abaConsultar;
	@FXML private Tab abaAtualizar;
	
	@FXML private TableView<Funcionario> tabelaFuncionarios;	
    @FXML private TableColumn<Funcionario, String> nomeFuncionarios;
    @FXML private TableColumn<Funcionario, String> cpfFuncionarios;
    @FXML private TableColumn<Funcionario, String> rgFuncionarios;
    @FXML private TableColumn<Funcionario, String> enderecoFuncionarios;
    @FXML private TableColumn<Funcionario, String> telefoneFuncionarios;
    @FXML private TableColumn<Funcionario, String> emailFuncionarios;
    @FXML private TableColumn<Funcionario, String> cargoFuncionarios;
    @FXML private TableColumn<Funcionario, Integer> idFuncionarios;
    @FXML private TableColumn<Funcionario, Double> salarioFuncionarios;
    // @FXML private TableColumn<Funcionario, Date> dataAdmFuncionarios;
    
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    
    
    @FXML void gerenciarAbas() {	
		if (abaAtualizar.isDisable())
			abaAtualizar.setDisable(false);
		else
			abaAtualizar.setDisable(true);
		limparCadastroNovoFuncionario();
    }
    
    @FXML void exibirAbaAtualizacao() {
    	funcionarioSelecionado = tabelaFuncionarios.getSelectionModel().getSelectedItem();
    	
    	if (funcionarioSelecionado == null) {
    		InitialScreenController.exibirDialogoErro("Nenhum Funcionário Selecionado");
			
		}else {
			nomeAtualizarFuncionario.setText(funcionarioSelecionado.getNome());
			cpfAtualizarFuncionario.setText(funcionarioSelecionado.getCpf());
			rgAtualizarFuncionario.setText(funcionarioSelecionado.getRg());
			enderecoAtualizarFuncionario.setText(funcionarioSelecionado.getEndereco());
			telefoneAtualizarFuncionario.setText(funcionarioSelecionado.getTel());
			emailAtualizarFuncionario.setText(funcionarioSelecionado.getEmail());
			cargoAtualizarFuncionario.setText(funcionarioSelecionado.getCargo());
			salarioAtualizarFuncionario.setText(String.valueOf(funcionarioSelecionado.getSalario()));
			abas.getSelectionModel().select(abaAtualizar);
		}
    }
	
	@FXML void salvarNovoFuncionario() {
		Funcionario funcionario = new Funcionario(nomeNovoFuncionario.getText(), cpfNovoFuncionario.getText(), rgNovoFuncionario.getText(),
											      enderecoNovoFuncionario.getText(), telefoneNovoFuncionario.getText(), 
											      emailNovoFuncionario.getText(),
											      cargoNovoFuncionario.getText(), Double.parseDouble(salarioNovoFuncionario.getText()));
		
		try {
			dao.cadastrar(funcionario);
			InitialScreenController.exibirDialogoInformacao ("Funcionário Cadastrado com sucesso!");
			limparCadastroNovoFuncionario();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao cadastrar funcionário");
			e.printStackTrace();
		}
		
		
	}
	
	@FXML void atualizarFuncionario() {
		funcionarioSelecionado.setNome(nomeAtualizarFuncionario.getText());
		funcionarioSelecionado.setCpf(cpfAtualizarFuncionario.getText());
		funcionarioSelecionado.setRg(rgAtualizarFuncionario.getText());
		funcionarioSelecionado.setEndereco(enderecoAtualizarFuncionario.getText());
		funcionarioSelecionado.setTel(telefoneAtualizarFuncionario.getText());
		funcionarioSelecionado.setEmail(emailAtualizarFuncionario.getText());
		funcionarioSelecionado.setCargo(cargoAtualizarFuncionario.getText());
		funcionarioSelecionado.setSalario(Double.parseDouble(salarioAtualizarFuncionario.getText()));
		
		try {
			dao.atualizar(funcionarioSelecionado);
			InitialScreenController.exibirDialogoInformacao ("Funcionário atualizado com sucesso!");
			abas.getSelectionModel().select(abaConsultar);
			abaAtualizar.setDisable(true);
			consultarFuncionario();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao atualizar funcionário");
			e.printStackTrace();
		}
	}
	
	@FXML void deletarFuncionario() {
		if (tabelaFuncionarios.getSelectionModel().getSelectedItem() == null) {
			InitialScreenController.exibirDialogoErro("Nenhum Funcionário Selecionado");
			
		}else {
			if (InitialScreenController.exibirDialogoConfirmacao("Confirmar exclusão do funcionário selecionado?")) {
				try {
					dao.deletar(tabelaFuncionarios.getSelectionModel().selectedItemProperty().get().getId());
					InitialScreenController.exibirDialogoConfirmacao("Funcionário deletado com sucesso!");
					consultarFuncionario();
					
				} catch (Exception e) {
					InitialScreenController.exibirDialogoErro("Falha ao deletar o funcionário!");
					e.printStackTrace();
				}
			}
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
			
			ArrayList<Funcionario> resultado = (ArrayList<Funcionario>) dao.consultar(txtConsultaFuncionario.getText());
			Collections.sort(resultado);
			
			if (resultado.isEmpty() ) {
				InitialScreenController.exibirDialogoInformacao("Nenhum funcionário encontrado");	
			}
				tabelaFuncionarios.setItems(FXCollections.observableList(resultado));
				
		}catch(Exception e) {
			InitialScreenController.exibirDialogoErro("Falha na consulta");
			e.printStackTrace();
		}
	}
}