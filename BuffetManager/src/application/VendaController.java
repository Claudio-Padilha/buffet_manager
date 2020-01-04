package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import application.ProdutoDAO;

public class VendaController implements Initializable {

	@FXML ComboBox <String> comBoxFormaPagamento;
	
	@FXML ComboBox <String> comBoxProduto;
	
	@FXML ComboBox <String> comBoxCategoria;
	
	@FXML TextField qtde;
	
	@FXML Button btnAdd;
	
	@FXML Button btnDelete;
	
	@FXML ListView <String> itensVenda;
	
	@FXML Button btnRegistrarVenda;
	
	@FXML DialogPane total;
	
	private ObservableList <String> itensCarrinho = FXCollections.observableArrayList("");
	private ObservableList <Produto> produtosComBox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// use observable array list to put item dynamically
		
		ObservableList<String> categorias = FXCollections.observableArrayList("Bebida", "Refeição");
		comBoxCategoria.setItems(categorias);
		
		comBoxFormaPagamento.getItems().addAll("Dinheiro","Cartão de débito", "Cartão de Crédito");
		
		total.contentTextProperty().setValue("0.0");
	}
	
	@FXML public void povoarComboBoxProdutos() {
		
		comBoxProduto.getItems().clear();
		ProdutoDAO dao = new ProdutoDAO();
		produtosComBox = FXCollections.observableArrayList(dao.buscarPorCategoria(comBoxCategoria.getSelectionModel().getSelectedItem()));
		
		produtosComBox.forEach((prod) -> {
			comBoxProduto.getItems().add(prod.getNome());
		});
	}
	
	@FXML public void add() {
		
		if (this.comBoxProduto.getSelectionModel().getSelectedItem() != null) {	
			
			String selectedItem = comBoxProduto.getSelectionModel().getSelectedItem();
			Double []valor = new Double[1];
			
			produtosComBox.forEach((prod) -> {
				if (prod.getNome().equals(selectedItem)) {
					valor[0] = prod.getPreco();
				}
			});
			
			Double valorUnid = (Double.parseDouble(qtde.getText()) * valor[0]);
			
			String aux = this.comBoxProduto.getSelectionModel().getSelectedItem() + "       " + qtde.getText() +
						 "                    R$ " + valorUnid.toString();
			itensVenda.getItems().add(aux);
			
			Double novoTotal = Double.parseDouble(total.contentTextProperty().getValue().replace("Total R$ ", "")) + 
							   Double.parseDouble(qtde.getText()) * valor[0];
			total.contentTextProperty().setValue("Total R$ " + String.valueOf(novoTotal));
		}else {
			InitialScreenController.exibirDialogoErro("Selecione um produto!");
		}
	}
	
	@FXML public void delete() {
		if (itensVenda.getSelectionModel().getSelectedItem() != null) {
			String selectedItem = itensVenda.getSelectionModel().getSelectedItem();
			
			int index = selectedItem.lastIndexOf(" ");
			Double diferenca = Double.parseDouble(selectedItem.substring(index + 1, selectedItem.length()));
			Double novoTotal = Double.parseDouble(total.contentTextProperty().getValue().replace("Total R$ ", "")) - diferenca;
			total.contentTextProperty().setValue("Total R$ " + String.valueOf(novoTotal));
			
			itensVenda.getItems().remove(itensVenda.getSelectionModel().getSelectedIndex());
		}
	}
}
