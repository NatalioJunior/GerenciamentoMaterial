package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import model.BO.BaseInterBO;
import model.BO.ProdutoBO;
import model.VO.ProdutoVO;
import view.Telas;

public class NovoProdutoController {
	@FXML private TextField nomeP;
	@FXML private TextField descP;
	@FXML private TextField qntdP;
	@FXML private TextField valorP;
	@FXML private TextField idP;
	@FXML private Label error;
	@FXML private Region buttonC;
	@FXML private AnchorPane overlay;
	
	ProdutoVO vo = new ProdutoVO();
	BaseInterBO<ProdutoVO> bo = new ProdutoBO();

	public void confirmar(ActionEvent event) throws Exception{
		try {
			vo.setNome(nomeP.getText());
			vo.setDescricao(descP.getText());
			vo.setQuantidade(Integer.parseInt(qntdP.getText()));
			vo.setPreco(Double.parseDouble(valorP.getText()));
			vo.setId(Integer.parseInt(idP.getText()));
		}
		catch (Exception e) {
			error.setVisible(true);
			throw new IOException("Há campos inválidos!");
		}
		buttonC.setVisible(true);
		overlay.setVisible(true);
	}
	
	public void yesC(ActionEvent event) throws Exception {
		bo.cadastrar(vo);
		Telas.telaEstoque();
	}
	
	public void notC(ActionEvent event) throws Exception {
		overlay.setVisible(false);
		buttonC.setVisible(false);
	}
	
	public void voltar(ActionEvent event) throws Exception {
		Telas.telaEstoque();
	}
	
}
