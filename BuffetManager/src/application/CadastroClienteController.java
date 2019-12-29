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

public class CadastroClienteController implements Initializable{
	
	private ClienteDAO dao;
    
	private Cliente clienteSelecionado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new ClienteDAO();
		
		idClientes.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeClientes.setCellValueFactory(new PropertyValueFactory<>("nome"));
		telefoneClientes.setCellValueFactory(new PropertyValueFactory<>("tel"));
		emailClientes.setCellValueFactory(new PropertyValueFactory<>("email"));
		enderecoClientes.setCellValueFactory(new PropertyValueFactory<>("endereco"));
	}
	
	@FXML private TabPane abasCliente;
	
	@FXML private Tab abaCadastrarCliente;
	@FXML private Tab abaConsultarCliente;
	@FXML private Tab abaAtualizarCliente;
	
	@FXML private TextField nomeNovoCliente;
	@FXML private TextField telefoneNovoCliente;
	@FXML private TextField emailNovoCliente;
	@FXML private TextField enderecoNovoCliente;
	
	@FXML private TextField txtConsultaCliente;
	@FXML private TableView<Cliente> tabelaClientes;
	@FXML private TableColumn<Cliente, String> nomeClientes;
	@FXML private TableColumn<Cliente, String> telefoneClientes;
	@FXML private TableColumn<Cliente, String> emailClientes;
	@FXML private TableColumn<Cliente, String> enderecoClientes;
	@FXML private TableColumn<Cliente, Integer> idClientes;
	
	@FXML private TextField nomeAtualizarCliente;
	@FXML private TextField telefoneAtualizarCliente;
	@FXML private TextField emailAtualizarCliente;
	@FXML private TextField enderecoAtualizarCliente;
	
	@FXML void salvarNovoCliente() {
		Cliente cliente = new Cliente(nomeNovoCliente.getText(), telefoneNovoCliente.getText(),
									  emailNovoCliente.getText(), enderecoNovoCliente.getText());
		
		try {
			dao.cadastrarCliente(cliente);
			InitialScreenController.exibirDialogoInformacao ("Cliente Cadastrado com sucesso!");
			limparCadastroNovoCliente();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao cadastrar cliente");
			e.printStackTrace();
		}
		
		
	}
	
	@FXML void consultarCliente() {
		try {
			
			ArrayList<Cliente> resultado = (ArrayList<Cliente>) dao.consultar(txtConsultaCliente.getText());
			Collections.sort(resultado);
			
			if (resultado.isEmpty() ) {
				InitialScreenController.exibirDialogoInformacao("Nenhum cliente encontrado");	
			}
				tabelaClientes.setItems(FXCollections.observableList(resultado));
				
		}catch(Exception e) {
			InitialScreenController.exibirDialogoErro("Falha na consulta");
			e.printStackTrace();
		}
	}

	@FXML void deletarCliente() {
		if (tabelaClientes.getSelectionModel().getSelectedItem() == null) {
			InitialScreenController.exibirDialogoErro("Nenhum Cliente Selecionado");
			
		}else {
			if (InitialScreenController.exibirDialogoConfirmacao("Confirmar exclusão do cliente selecionado?")) {
				try {
					dao.deletar(tabelaClientes.getSelectionModel().selectedItemProperty().get().getId());
					InitialScreenController.exibirDialogoConfirmacao("Cliente deletado com sucesso!");
					consultarCliente();
					
				} catch (Exception e) {
					InitialScreenController.exibirDialogoErro("Falha ao deletar o cliente!");
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML void atualizarCliente() {
		clienteSelecionado.setNome(nomeAtualizarCliente.getText());
		clienteSelecionado.setTel(telefoneAtualizarCliente.getText());
		clienteSelecionado.setEmail(emailAtualizarCliente.getText());
		clienteSelecionado.setEndereco(enderecoAtualizarCliente.getText());
		
		try {
			dao.atualizarCliente(clienteSelecionado);
			InitialScreenController.exibirDialogoInformacao ("Funcionário atualizado com sucesso!");
			abasCliente.getSelectionModel().select(abaConsultarCliente);
			abaAtualizarCliente.setDisable(true);
			consultarCliente();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao atualizar funcionário");
			e.printStackTrace();
		}
	}
	
	@FXML void exibirAbaAtualizacaoCliente() {
    	clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
    	
    	if (clienteSelecionado == null) {
    		InitialScreenController.exibirDialogoErro("Nenhum Funcionário Selecionado");
			
		}else {
			nomeAtualizarCliente.setText(clienteSelecionado.getNome());
			enderecoAtualizarCliente.setText(clienteSelecionado.getEndereco());
			telefoneAtualizarCliente.setText(clienteSelecionado.getTel());
			emailAtualizarCliente.setText(clienteSelecionado.getEmail());
			abasCliente.getSelectionModel().select(abaAtualizarCliente);
		}
    }
	
	@FXML void limparCadastroNovoCliente() {
		nomeNovoCliente.clear();
		telefoneNovoCliente.clear();
		emailNovoCliente.clear();
		enderecoNovoCliente.clear();
	}
	
	@FXML void gerenciarAbasCliente() {	
		if (abaAtualizarCliente.isDisable())
			abaAtualizarCliente.setDisable(false);
		else
			abaAtualizarCliente.setDisable(true);
		limparCadastroNovoCliente();
    }
}
