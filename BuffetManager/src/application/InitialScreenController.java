package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitialScreenController {
	
	@FXML private Button btnVenda;
	
	@FXML private Button btnCadFuncionario;
	
	@FXML private Button btnCadFornecedor;
	
	@FXML private Button btnCadCliente;
	
	@FXML private Button btnEstoque;
	
	public void cadastrarFuncionario (ActionEvent event) throws IOException {
		Stage cadastroFuncionarioStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CadastroFuncionario.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		cadastroFuncionarioStage.setScene(scene);
		cadastroFuncionarioStage.show();
	}

	public void cadastrarFornecedor (ActionEvent event) throws IOException {
		Stage cadastroFornecedorStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CadastroFornecedor.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		cadastroFornecedorStage.setScene(scene);
		cadastroFornecedorStage.show();
	}
	
	public void cadastrarCliente (ActionEvent event) throws IOException {
		Stage cadastroClienteStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CadastroCliente.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		cadastroClienteStage.setScene(scene);
		cadastroClienteStage.show();
	}
	
	public void vender (ActionEvent event) throws IOException {
		Stage VendaStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Venda.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		VendaStage.setScene(scene);
		VendaStage.show();
	}
	
	public void gerenciarEstoque (ActionEvent event) {
		// TODO
	}
}
