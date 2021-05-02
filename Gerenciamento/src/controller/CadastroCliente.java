package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import assistant.InterList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import model.BO.BaseInterBO;
import model.BO.ClienteBO;
import model.VO.ClienteVO;
import view.Telas;

public class CadastroCliente implements Initializable {
	
	@FXML private TableView<ClienteVO> tableClientes;
	@FXML private TableColumn<ClienteVO, String> Cpf_Cnpj;
	@FXML private TableColumn<ClienteVO, String> nomeC;
	@FXML private Region antiButton;
	@FXML private Button buttonRem;
	
	@FXML private AnchorPane overlay;
	
	BaseInterBO<ClienteVO> bo = new ClienteBO();
	ObservableList<ClienteVO> clientes = FXCollections.observableArrayList(); 
	private static ClienteVO lastSelected;

	public static ClienteVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(ClienteVO lastSelected) {
		CadastroCliente.lastSelected = lastSelected;
	}

	@Override
	public void initialize(URL local, ResourceBundle resources) {
		try {
			loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadData() throws IOException {
		InterList<ClienteVO> listaC = bo.listar();
		ClienteVO cliente = listaC.removeFirst();
		
		while(cliente != null) {
			clientes.add(cliente);
			cliente = listaC.removeFirst();
		}
		
		Cpf_Cnpj.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("cadastroPessoa"));
		nomeC.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("nome"));
		
		tableClientes.setItems(clientes);
	}
	
	public void back() throws Exception {
		Telas.telaMenu();
	}
	
	public void excluir(ActionEvent event) throws Exception {
		if (check() == null) {
			throw new Exception("Nenhum campo selecionado!");
		}
		else {
			antiButton.setVisible(true);
			overlay.setVisible(true);
			tableClientes.setDisable(true);
		}
	}
	
	public void yesC() throws IOException {
		bo.deletar(check());
		tableClientes.getItems().removeAll(check());
		antiButton.setVisible(false);
		overlay.setVisible(false);
		tableClientes.setDisable(false);
	}
	
	public void notC() {
		antiButton.setVisible(false);
		overlay.setVisible(false);
		tableClientes.setDisable(false);
	}
	
	public ClienteVO check() {
		ClienteVO check = tableClientes.getSelectionModel().getSelectedItem();
		if (check == null) {
			buttonRem.setDisable(true);
			return check;
		}
		else {
			buttonRem.setDisable(false);
			return check;
		}
		
	}

}
