package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import assistant.InterList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.BO.BaseInterBO;
import model.BO.CompraBO;
import model.VO.ClienteVO;
import model.VO.CompraVO;
import model.VO.ProdutoVO;
import view.Telas;

public class ListaCompras implements Initializable{
	
	@FXML private TableView<CompraVO> tabelaCompras;
	@FXML private TableColumn<CompraVO, ProdutoVO> idP;
	@FXML private TableColumn<CompraVO, ProdutoVO> nomeP;
	@FXML private TableColumn<CompraVO, ClienteVO> nomeC;
	@FXML private TableColumn<CompraVO, Integer> qntdP;
	@FXML private TableColumn<CompraVO, Double> valorCompra;
	@FXML private TableColumn<CompraVO, ProdutoVO> precoP;
	
	@FXML private TextField dataCompra;
	@FXML private TextField cpfC;
	@FXML private TextField total;
	
	@FXML private Button buttonRem;
	@FXML private AnchorPane overlay;
	
	BaseInterBO<CompraVO> bo = new CompraBO();
	CompraVO vo = new CompraVO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void loadData() throws IOException{
		ObservableList<CompraVO> compras = FXCollections.observableArrayList(); 
		InterList<CompraVO> listaC = bo.listar();
		CompraVO compra = listaC.removeFirst();
		
		
		while(compra != null) {
			compras.add(compra);
			compra = listaC.removeFirst();
		}
		
		idP.setCellValueFactory(new PropertyValueFactory<>("produto"));
		idP.setCellFactory(coluna -> {
		    return new TableCell<CompraVO, ProdutoVO>(){
		        @Override
		        protected void updateItem(ProdutoVO item, boolean empty) {
		            super.updateItem(item, empty);
		            if(item != null && !empty) {
		                setText(String.valueOf(item.getId()));
		            } else {
		                setText("");
		            }
		        }
		    };
		 });
		nomeP.setCellValueFactory(new PropertyValueFactory<>("produto"));
		nomeP.setCellFactory(coluna -> {
		    return new TableCell<CompraVO, ProdutoVO>(){
		        @Override
		        protected void updateItem(ProdutoVO item, boolean empty) {
		            super.updateItem(item, empty);
		            if(item != null && !empty) {
		                setText(item.getNome());
		            } else {
		                setText("");
		            }
		        }
		    };
		 });
		nomeC.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		nomeC.setCellFactory(coluna -> {
		    return new TableCell<CompraVO, ClienteVO>(){
		        @Override
		        protected void updateItem(ClienteVO item, boolean empty) {
		            super.updateItem(item, empty);
		            if(item != null && !empty) {
		                setText(item.getNome());
		            } else {
		                setText("");
		            }
		        }
		    };
		 });
		qntdP.setCellValueFactory(new PropertyValueFactory<CompraVO, Integer>("quantidade"));
		valorCompra.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		valorCompra.setCellFactory(coluna -> {
		    return new TableCell<CompraVO, Double>(){
		        protected void updateItem(Double item, boolean empty) {
		            super.updateItem(item, empty);
		            if(item != null && !empty) {		     
		                setText(String.valueOf(item / qntdP.getCellObservableValue(getIndex()).getValue()));
		            } else {
		                setText("");
		            }
		        }
		    };
		 });
		precoP.setCellValueFactory(new PropertyValueFactory<>("produto"));
		precoP.setCellFactory(coluna -> {
		    return new TableCell<CompraVO, ProdutoVO>(){
		        protected void updateItem(ProdutoVO item, boolean empty) {
		            super.updateItem(item, empty);
		            if(item != null && !empty) {		     
		                setText(String.valueOf(item.getPreco()));
		            } else {
		                setText("");
		            }
		        }
		    };
		 });
		
		tabelaCompras.setItems(compras);		
	}

	public void excluir() {
		overlay.setVisible(true);
		tabelaCompras.setDisable(true);
	}
	
	public void back() throws Exception {
		Telas.telaMenu();
	}
	
	public void notC() {
		overlay.setVisible(false);
		tabelaCompras.setDisable(false);
	}
	
	public void yesC() throws IOException {
		try {
			bo.deletar(check());
			tabelaCompras.getItems().removeAll(check());
			overlay.setVisible(false);
			tabelaCompras.setDisable(false);
		} catch (IOException e) {
			throw new IOException("Erro ao excluir!");
		}
	}
	
	public CompraVO check() {
		CompraVO check = tabelaCompras.getSelectionModel().getSelectedItem();
		if (check == null) {
			buttonRem.setDisable(true);
			return check;
		}
		else {
			buttonRem.setDisable(false);
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyy");
			dataCompra.setText(s.format(check.getDataCompra().getTime()));
			cpfC.setText(check.getCliente().getCadastroPessoa());
			total.setText(String.valueOf(check.getValorTotal()));
			return check;
		}
	}

}
