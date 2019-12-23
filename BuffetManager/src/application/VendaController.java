package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VendaController implements Initializable {

	@FXML ComboBox <String> comBoxCliente;
	
	@FXML ComboBox <String> comBoxProduto;
	
	@FXML TextField qtde;
	
	@FXML Button btnAdd;
	
	@FXML Button btnDelete;
	
	@FXML ListView <String> itensVenda;
	
	@FXML Button btnRegistrarVenda;
	
	@FXML DialogPane total;
	
	ObservableList <String> itensCarrinho = FXCollections.observableArrayList("");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// use observable array list to put item dynamically
		
		comBoxCliente.getItems().addAll("Alex", "César", "Maria", "Brinque Feliz", "Roupa Legal");
		
		comBoxProduto.getItems().addAll("Executivo", "Fitness", "Gourmet");
		
		total.contentTextProperty().setValue("0.0");
	}
	
	public void add(ActionEvent event) {
		
		if (this.comBoxProduto.getSelectionModel().getSelectedItem() != null && qtde.getText() != null) {	
			String aux = this.comBoxProduto.getSelectionModel().getSelectedItem() + "    " + qtde.getText();
			itensVenda.getItems().add(aux);
			
			Double novoTotal = Double.parseDouble(total.contentTextProperty().getValue().replace("Total R$ ", "")) + Double.parseDouble(qtde.getText()) * 10.0;
			total.contentTextProperty().setValue("Total R$ " + String.valueOf(novoTotal));
		}
	}
	
	public void delete(ActionEvent event) {
		if (itensVenda.getSelectionModel().getSelectedItem() != null) {
			String selectedItem = itensVenda.getSelectionModel().getSelectedItem();
			
			int index = selectedItem.lastIndexOf(" ");
			Double diferenca = Double.parseDouble(selectedItem.substring(index + 1, selectedItem.length()));
			Double novoTotal = Double.parseDouble(total.contentTextProperty().getValue().replace("Total R$ ", "")) - diferenca * 10;
			total.contentTextProperty().setValue("Total R$ " + String.valueOf(novoTotal));
			
			itensVenda.getItems().remove(itensVenda.getSelectionModel().getSelectedIndex());
		}
	}
}
