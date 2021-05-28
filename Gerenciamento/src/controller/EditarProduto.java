package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BO.BaseInterBO;
import model.BO.ProdutoBO;
import model.VO.ProdutoVO;
import view.Telas;

public class EditarProduto implements Initializable{
	@FXML private TextField nomeE;
	@FXML private TextField descricaoE;
	@FXML private TextField qntdE;
	@FXML private TextField precoE;
	@FXML private Label error;
	
	BaseInterBO<ProdutoVO> bo = new ProdutoBO();
	
	private static ProdutoVO lastSelected;

	public static ProdutoVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected() {
		EditarProduto.lastSelected = EstoqueController.getLastSelected();
	}
	
	public void initialize(URL local, ResourceBundle resources) {
		try {
			loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadData() throws IOException {
		EditarProduto.setLastSelected();
		nomeE.setText(lastSelected.getNome());
		descricaoE.setText(lastSelected.getDescricao());
		qntdE.setText(String.valueOf(lastSelected.getQuantidade()));
		precoE.setText(String.valueOf(lastSelected.getPreco()));
	}
	
	public void back() throws Exception {
		Telas.telaEstoque();
	}
	
	public void confirmar() throws Exception {
		try {
			ProdutoVO vo = new ProdutoVO();
			vo.setNome(nomeE.getText());
			vo.setDescricao(descricaoE.getText());
			if (qntdE.getText().matches("^[0-9]*$")) {
				vo.setQuantidade(Integer.parseInt(qntdE.getText()));
			}
			String temp = precoE.getText().replaceAll(".", "");
			if (temp.matches("^[0-9]*$")) {
				vo.setPreco(Double.parseDouble(precoE.getText()));
			}
			vo.setId(lastSelected.getId());
			bo.editar(vo);
			Telas.telaEstoque();	
		} catch (Exception e) {
			error.setText("Há campos inválidos!");
			error.setVisible(true);
			throw new Exception ("Há campos inválidos!");
		}
			
	}
}
