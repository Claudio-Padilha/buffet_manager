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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VendaController implements Initializable {

	@FXML ComboBox <String> comBoxCliente;
	
	@FXML ComboBox <String> comBoxProduto;
	
	@FXML TextField qtde;
	
	@FXML Button btnAdd;
	
	@FXML ListView <String> itensVenda;
	
	@FXML Button btnRegistrarVenda;
	
	ObservableList <String> itensCarrinho = FXCollections.observableArrayList("");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// use observable array list to put item dynamically
		
		comBoxCliente.getItems().addAll("Alex", "César", "Maria", "Brinque Feliz", "Roupa Legal");
		
		comBoxProduto.getItems().addAll("Executivo", "Fit", "Gourmet");
	}
	
	public void add(ActionEvent event) {
		
		if (this.comBoxProduto.getSelectionModel().getSelectedItem() != null && qtde.getText() != null) {	
			String aux = this.comBoxProduto.getSelectionModel().getSelectedItem() + "	" + qtde.getText();
			itensVenda.getItems().add(aux);
		}
	}
}
