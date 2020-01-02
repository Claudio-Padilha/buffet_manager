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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroFornecedorController implements Initializable{
	
	private FornecedorDAO dao;
    
	private Fornecedor fornecedorSelecionado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new FornecedorDAO();
		
		idFornecedores.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeFornecedores.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cnpjFornecedores.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		enderecoFornecedores.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		telefoneFornecedores.setCellValueFactory(new PropertyValueFactory<>("tel"));
		emailFornecedores.setCellValueFactory(new PropertyValueFactory<>("email"));
		infoProdutosFornecedores.setCellValueFactory(new PropertyValueFactory<>("infoProdutos"));
	}
	
	@FXML private TabPane abasFornecedores;
	@FXML private Tab abaCadastrarFornecedor;
	@FXML private Tab abaConsultarFornecedor;
	@FXML private Tab abaAtualizarFornecedor;

	@FXML private TextField nomeNovoFornecedor;
	@FXML private TextField cnpjNovoFornecedor;
	@FXML private TextField enderecoNovoFornecedor;
	@FXML private TextField telefoneNovoFornecedor;
	@FXML private TextField emailNovoFornecedor;
	@FXML private TextArea infoProdutosNovoFornecedor;
	
	@FXML private TextField txtConsultaFornecedor;
	@FXML private TableView<Fornecedor> tabelaFornecedores;
	@FXML private TableColumn<Fornecedor, String> nomeFornecedores;
	@FXML private TableColumn<Fornecedor, String> cnpjFornecedores;
	@FXML private TableColumn<Fornecedor, String> enderecoFornecedores;
	@FXML private TableColumn<Fornecedor, String> telefoneFornecedores;
	@FXML private TableColumn<Fornecedor, String> emailFornecedores;
	@FXML private TableColumn<Fornecedor, Integer> idFornecedores;
	@FXML private TableColumn<Fornecedor, String> infoProdutosFornecedores;
	
	@FXML private TextField nomeAtualizarFornecedor;
	@FXML private TextField cnpjAtualizarFornecedor;
	@FXML private TextField enderecoAtualizarFornecedor;
	@FXML private TextField telefoneAtualizarFornecedor;
	@FXML private TextField emailAtualizarFornecedor;
	@FXML private TextArea infoProdutosAtualizarFornecedor;
	
	@FXML void salvarNovoFornecedor() {
		Fornecedor fornecedor = new Fornecedor(nomeNovoFornecedor.getText(), cnpjNovoFornecedor.getText(), enderecoNovoFornecedor.getText(),
											   telefoneNovoFornecedor.getText(), emailNovoFornecedor.getText(), infoProdutosNovoFornecedor.getText());
		
		try {
			dao.cadastrarFornecedor(fornecedor);
			InitialScreenController.exibirDialogoInformacao ("Fornecedor Cadastrado com sucesso!");
			limparCadastroNovoFornecedor();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao cadastrar fornecedor");
			e.printStackTrace();
		}
		
		
	}
	
	@FXML void limparCadastroNovoFornecedor() {
		nomeNovoFornecedor.clear();
		cnpjNovoFornecedor.clear();
		enderecoNovoFornecedor.clear();
		telefoneNovoFornecedor.clear();
		emailNovoFornecedor.clear();
		infoProdutosNovoFornecedor.clear();
	}
	
	@FXML void consultarFornecedor() {
		try {
			
			ArrayList<Fornecedor> resultado = (ArrayList<Fornecedor>) dao.consultarFornecedor(txtConsultaFornecedor.getText());
			Collections.sort(resultado);
			
			if (resultado.isEmpty() ) {
				InitialScreenController.exibirDialogoInformacao("Nenhum Fornecedor encontrado");	
			}
				tabelaFornecedores.setItems(FXCollections.observableList(resultado));
				
		}catch(Exception e) {
			InitialScreenController.exibirDialogoErro("Falha na consulta");
			e.printStackTrace();
		}
	}
	
	@FXML void deletarFornecedor() {
		if (tabelaFornecedores.getSelectionModel().getSelectedItem() == null) {
			InitialScreenController.exibirDialogoErro("Nenhum Fornecedor Selecionado");
			
		}else {
			if (InitialScreenController.exibirDialogoConfirmacao("Confirmar exclusão do cliente selecionado?")) {
				try {
					dao.deletar(tabelaFornecedores.getSelectionModel().selectedItemProperty().get().getId());
					InitialScreenController.exibirDialogoConfirmacao("Fornecedor deletado com sucesso!");
					consultarFornecedor();
					
				} catch (Exception e) {
					InitialScreenController.exibirDialogoErro("Falha ao deletar o fornecedor!");
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML void atualizarFornecedor() {
		fornecedorSelecionado.setNome(nomeAtualizarFornecedor.getText());
		fornecedorSelecionado.setCnpj(cnpjAtualizarFornecedor.getText());
		fornecedorSelecionado.setEndereco(enderecoAtualizarFornecedor.getText());
		fornecedorSelecionado.setTel(telefoneAtualizarFornecedor.getText());
		fornecedorSelecionado.setEmail(emailAtualizarFornecedor.getText());
		fornecedorSelecionado.setInfoProdutos(infoProdutosAtualizarFornecedor.getText());
		
		try {
			dao.atualizarFornecedor(fornecedorSelecionado);
			InitialScreenController.exibirDialogoInformacao ("Fornecedor atualizado com sucesso!");
			abasFornecedores.getSelectionModel().select(abaConsultarFornecedor);
			abaAtualizarFornecedor.setDisable(true);
			consultarFornecedor();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao atualizar fornecedor");
			e.printStackTrace();
		}
	}
	
	@FXML void exibirAbaAtualizacaoFornecedor() {
    	fornecedorSelecionado = tabelaFornecedores.getSelectionModel().getSelectedItem();
    	
    	if (fornecedorSelecionado == null) {
    		InitialScreenController.exibirDialogoErro("Nenhum Fornecedor Selecionado");
			
		}else {
			nomeAtualizarFornecedor.setText(fornecedorSelecionado.getNome());
			cnpjAtualizarFornecedor.setText(fornecedorSelecionado.getCnpj());
			enderecoAtualizarFornecedor.setText(fornecedorSelecionado.getEndereco());
			telefoneAtualizarFornecedor.setText(fornecedorSelecionado.getTel());
			emailAtualizarFornecedor.setText(fornecedorSelecionado.getEmail());
			infoProdutosAtualizarFornecedor.setText(fornecedorSelecionado.getInfoProdutos());
			abasFornecedores.getSelectionModel().select(abaAtualizarFornecedor);
		}
    }
	
	@FXML void gerenciarAbasFornecedor() {	
		if (abaAtualizarFornecedor.isDisable())
			abaAtualizarFornecedor.setDisable(false);
		else
			abaAtualizarFornecedor.setDisable(true);
		limparCadastroNovoFornecedor();
    }
	
}	
