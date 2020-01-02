package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class CadastroProdutoController implements Initializable{
	
	private ProdutoDAO dao;
    
	private Produto produtoSelecionado;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new ProdutoDAO();
	}

}
