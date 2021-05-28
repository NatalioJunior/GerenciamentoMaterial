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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import model.BO.BaseInterBO;
import model.BO.ProdutoBO;
import model.VO.ProdutoVO;
import view.Telas;

public class EstoqueController implements Initializable {

	@FXML private TableView<ProdutoVO> tableProdutos;
	@FXML private TableColumn<ProdutoVO, Integer> idColumn;
	@FXML private TableColumn<ProdutoVO, String> nomeColumn;
	@FXML private TableColumn<ProdutoVO, Integer> qntdColumn;
	@FXML private TableColumn<ProdutoVO, Double> precoColumn;
	@FXML private Button buttonRem;
	@FXML private Button buttonEx;
	@FXML private Region antiButton;
	@FXML private Region antiButton2;
	@FXML private Label error;
	@FXML private TextField procurar;
	
	@FXML private AnchorPane overlayExcluir;
	@FXML private AnchorPane overlayExcluido;
	
	@FXML private AnchorPane overlayExpandir;
	@FXML private TextArea descricaoP;
	
	BaseInterBO<ProdutoVO> bo = new ProdutoBO();
	ProdutoVO vo = new ProdutoVO();
	
	private static ProdutoVO lastSelected;

	public static ProdutoVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(ProdutoVO lastSelected) {
		EstoqueController.lastSelected = lastSelected;
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
		ObservableList<ProdutoVO> produtos = FXCollections.observableArrayList();
		InterList<ProdutoVO> listaP = bo.listar();
		ProdutoVO produto = listaP.removeFirst();
		
		while (produto != null) {
			produtos.add(produto);
			produto = listaP.removeFirst();
		}
		
		idColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("id"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
		qntdColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantidade"));
		precoColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
		
		tableProdutos.setItems(produtos);
	}
	
	public void cadastrarP (ActionEvent event) throws Exception{
		Telas.telaCadastroP();
	}
	
	public void back() throws Exception {
		Telas.telaMenu();
	}

	//métodos para overlay de excluir
	public void excluir (ActionEvent event) throws Exception{
		if (check() == null) {
			throw new Exception("Nenhum campo selecionado!");
		}
		else {
			antiButton.setVisible(true);
			overlayExcluir.setVisible(true);
			tableProdutos.setDisable(true);
		}
	}
	
	public void buscar() throws IOException {
		ObservableList<ProdutoVO> produtos = FXCollections.observableArrayList();
		if (!procurar.getText().equals("")) {
			vo.setId(-1);
			if (procurar.getText().matches("^[0-9]*$")) {
				vo.setId(Integer.parseInt(procurar.getText()));
			}
			vo.setNome(procurar.getText());
			InterList<ProdutoVO> listaC = bo.pesquisar(vo);
			ProdutoVO produto = listaC.removeFirst();
			
			while(produto != null) {
				produtos.add(produto);
				produto = listaC.removeFirst();
			}
			
			idColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("id"));
			nomeColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
			qntdColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantidade"));
			precoColumn.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
			
			tableProdutos.setItems(produtos);
		}
		else {
			error.setText("Erro procurar: Campo em branco!");
			error.setVisible(true);
		}
	}
	
	public void yesRem(ActionEvent event) throws IOException {
		try {
			bo.deletar(check());
			tableProdutos.getItems().removeAll(check());
			overlayExcluir.setVisible(false);
			overlayExcluido.setVisible(true);
			tableProdutos.setDisable(false);
		}
		catch (IOException e) {
			error.setText("Erro ao excluir, verifique se há compras cadastradas com este produto!");
			error.setVisible(true);
			throw new IOException("Erro ao excluir, verifique se há compras cadastradas com este produto!");
		}
	}

	public void notRem() {
		antiButton.setVisible(false);
		overlayExcluir.setVisible(false);
		tableProdutos.setDisable(false);
		error.setVisible(false);
	}

	public void closeRem() {
		antiButton.setVisible(false);
		overlayExcluido.setVisible(false);
		tableProdutos.setDisable(false);
	}
	
	public void cadastrar() throws Exception {
		Telas.telaCadastroP();
	}
	
	
	//métodos para overlay de expandir
	public void expandir(ActionEvent event) throws Exception {
		if (check() == null) {
			throw new Exception("Nenhum campo selecionado!");
		}
		else {
			antiButton2.setVisible(true);
			overlayExpandir.setVisible(true);
			descricaoP.setText(check().getDescricao());
			tableProdutos.setDisable(true);
			procurar.setVisible(false);
		}
	}

	public void voltarEx() {
		antiButton2.setVisible(false);
		overlayExpandir.setVisible(false);
		tableProdutos.setDisable(false);
		procurar.setVisible(true);
	}
	
	public void editar() throws Exception {
		Telas.editarProduto();
	}

	public ProdutoVO check() {
		ProdutoVO check = tableProdutos.getSelectionModel().getSelectedItem();
		if (check == null) {
			buttonRem.setDisable(true);
			buttonEx.setDisable(true);
			return check;
		}
		else {
			EstoqueController.setLastSelected(check);
			buttonRem.setDisable(false);
			buttonEx.setDisable(false);
			return check;
		}
		
	}
	
}
