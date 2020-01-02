package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InitialScreenController {
	
	@FXML private Button btnVenda;
	
	@FXML private Button btnCadFuncionario;
	
	@FXML private Button btnCadFornecedor;
	
	@FXML private Button btnCadCliente;
	
	@FXML private Button btnProduto;
	
	public void cadastrarFuncionario (ActionEvent event) throws IOException {
		Stage cadastroFuncionarioStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CadastroFuncionario.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		cadastroFuncionarioStage.setTitle("Gestão de funcionários");
		cadastroFuncionarioStage.setScene(scene);
		cadastroFuncionarioStage.show();
	}

	public void cadastrarFornecedor (ActionEvent event) throws IOException {
		Stage cadastroFornecedorStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CadastroFornecedor.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		cadastroFornecedorStage.setTitle("Gestão de fornecedores");
		cadastroFornecedorStage.setScene(scene);
		cadastroFornecedorStage.show();
	}
	
	public void cadastrarCliente (ActionEvent event) throws IOException {
		Stage cadastroClienteStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CadastroCliente.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		cadastroClienteStage.setTitle("Gestão de clientes");
		cadastroClienteStage.setScene(scene);
		cadastroClienteStage.show();
	}
	
	public void vender (ActionEvent event) throws IOException {
		Stage VendaStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Venda.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		VendaStage.setTitle("Realizar venda");
		VendaStage.setScene(scene);
		VendaStage.show();
	}
	
	public void gerenciarEstoque (ActionEvent event) {
		// TODO
	}
	
	public static void exibirDialogoInformacao (String informacao) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informação");
		alert.setHeaderText(null);
		alert.setContentText(informacao);
		
		alert.showAndWait();
	}
	
	public static void exibirDialogoErro (String erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(erro);
		
		alert.showAndWait();
	}
	
	public static boolean exibirDialogoConfirmacao (String confirmacao) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText(null);
		alert.setContentText(confirmacao);
		
		Optional<ButtonType> opcao = alert.showAndWait();
		
		if (opcao.get() == ButtonType.OK) 
			return true;
		
		return false;
	}
}
