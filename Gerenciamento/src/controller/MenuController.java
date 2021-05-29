package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import view.Telas;

public class MenuController {
	
	@FXML private AnchorPane overlay;
	
	public void estoqueProdutos(ActionEvent event) throws Exception{
			Telas.telaEstoque();
		}
	
	public void cadastrarCliente(ActionEvent event) throws Exception{
			Telas.telaClientes();
	}
	
	public void cadastros(ActionEvent event) {
		overlay.setVisible(true);
	}
	
	public void back() {
		overlay.setVisible(false);
	}
	
	public void compras() throws Exception {
		Telas.telaCarrinho();
	}
	
	public void listaCompras() throws Exception {
		Telas.telaCompras();
	}
	
	public void clientes() throws Exception{
		Telas.telaCadastroC();
	}
	
}
