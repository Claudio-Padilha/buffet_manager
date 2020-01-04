package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroProdutoController implements Initializable{
	
	private ProdutoDAO dao;
    
	private Produto produtoSelecionado;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new ProdutoDAO();
		
		ObservableList<String> categorias = FXCollections.observableArrayList("Bebida", "Refeição");
		comboBoxCategoria.setItems(categorias);
		comboBoxAtualizarCategoria.setItems(categorias);
		
		idProdutos.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeProdutos.setCellValueFactory(new PropertyValueFactory<>("nome"));
		precoProdutos.setCellValueFactory(new PropertyValueFactory<>("preco"));
		categoriaProdutos.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		
	}
	
	@FXML private TabPane abasProduto;
	
	@FXML private Tab abaCadastrarProduto;
	@FXML private Tab abaConsultarProduto;
	@FXML private Tab abaAtualizarProduto;
	
	@FXML private ComboBox<String> comboBoxCategoria;
	@FXML private TextField nomeNovoProduto;
	@FXML private TextField precoNovoProduto;
	
	
	@FXML private TextField txtConsultaProduto;
	@FXML private TableView<Produto> tabelaProdutos;
	@FXML private TableColumn<Cliente, String> nomeProdutos;
	@FXML private TableColumn<Cliente, Double> precoProdutos;
	@FXML private TableColumn<Cliente, String> categoriaProdutos;
	@FXML private TableColumn<Cliente, Integer> idProdutos;
	
	@FXML private ComboBox<String> comboBoxAtualizarCategoria;
	@FXML private TextField nomeAtualizarProduto;
	@FXML private TextField precoAtualizarProduto;
	
		
	@FXML void salvarNovoproduto() {
		Produto produto = new Produto(nomeNovoProduto.getText(), Double.parseDouble(precoNovoProduto.getText()),
									  comboBoxCategoria.getSelectionModel().getSelectedItem().toString());
		
		try {
			dao.cadastrarProduto(produto);
			InitialScreenController.exibirDialogoInformacao ("Produto Cadastrado com sucesso!");
			limparCadastroNovoProduto();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao cadastrar produto");
			e.printStackTrace();
		}
	}
	
	@FXML void deletarProduto() {
		if (tabelaProdutos.getSelectionModel().getSelectedItem() == null) {
			InitialScreenController.exibirDialogoErro("Nenhum Produto Selecionado");
			
		}else {
			if (InitialScreenController.exibirDialogoConfirmacao("Confirmar exclusão do produto selecionado?")) {
				try {
					dao.deletar(tabelaProdutos.getSelectionModel().selectedItemProperty().get().getId());
					InitialScreenController.exibirDialogoConfirmacao("Produto deletado com sucesso!");
					consultarProduto();
					
				} catch (Exception e) {
					InitialScreenController.exibirDialogoErro("Falha ao deletar o produto!");
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML void consultarProduto() {
		try {
			
			ArrayList<Produto> resultado = (ArrayList<Produto>) dao.consultar(txtConsultaProduto.getText());
			Collections.sort(resultado);
			
			if (resultado.isEmpty() ) {
				InitialScreenController.exibirDialogoInformacao("Nenhum produto encontrado");	
			}
				tabelaProdutos.setItems(FXCollections.observableList(resultado));
				
		}catch(Exception e) {
			InitialScreenController.exibirDialogoErro("Falha na consulta");
			e.printStackTrace();
		}
	}
	
	@FXML void exibirAbaAtualizacaoProduto() {
    	produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
    	
    	if (produtoSelecionado == null) {
    		InitialScreenController.exibirDialogoErro("Nenhum Produto Selecionado");
			
		}else {
			nomeAtualizarProduto.setText(produtoSelecionado.getNome());
			precoAtualizarProduto.setText(produtoSelecionado.getPreco().toString());
			comboBoxAtualizarCategoria.getSelectionModel().select(produtoSelecionado.getCategoria());
			abasProduto.getSelectionModel().select(abaAtualizarProduto);
		}
    }
	
	@FXML void atualizarProduto() {
		produtoSelecionado.setNome(nomeAtualizarProduto.getText());
		produtoSelecionado.setPreco(Double.parseDouble(precoAtualizarProduto.getText()));
		produtoSelecionado.setCategoria(comboBoxAtualizarCategoria.getSelectionModel().getSelectedItem());
		
		try {
			dao.atualizarProduto(produtoSelecionado);
			InitialScreenController.exibirDialogoInformacao ("Produto atualizado com sucesso!");
			abasProduto.getSelectionModel().select(abaConsultarProduto);
			abaAtualizarProduto.setDisable(true);
			consultarProduto();
		}catch (Exception e){
			InitialScreenController.exibirDialogoErro("Falha ao atualizar funcionário");
			e.printStackTrace();
		}
	}

	@FXML void limparCadastroNovoProduto() {
		nomeNovoProduto.clear();
		precoNovoProduto.clear();
	}
	
	@FXML void gerenciarAbasProduto() {	
		if (abaAtualizarProduto.isDisable())
			abaAtualizarProduto.setDisable(false);
		else
			abaAtualizarProduto.setDisable(true);
		limparCadastroNovoProduto();
    }
}
