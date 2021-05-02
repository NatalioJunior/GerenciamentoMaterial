package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import model.BO.BaseInterBO;
import model.BO.ClienteBO;
import model.VO.ClienteVO;
import view.Telas;

public class ClienteController {
	@FXML private TextField nomeC;
	@FXML private TextField Cpf_Cnpj;
	@FXML private Region antiButton;
	@FXML private Label error;
	
	@FXML private AnchorPane overlay;
	
	ClienteVO vo = new ClienteVO();
	BaseInterBO<ClienteVO> bo = new ClienteBO();
	
	public void back() throws Exception {
		Telas.telaMenu();
	}
	
	public void cadastrar() throws Exception {
		try {
			vo.setCadastroPessoa(Cpf_Cnpj.getText());
			vo.setNome(nomeC.getText());
		}
		catch (Exception e) {
			error.setVisible(true);
			throw new Exception("Há campos inváidos!");
		}
		antiButton.setVisible(true);
		overlay.setVisible(true);
	}
	
	public void yesC() throws IOException {
		bo.cadastrar(vo);
		nomeC.setText("");
		Cpf_Cnpj.setText("");
		antiButton.setVisible(false);
		overlay.setVisible(false);
	}

	public void notC() {
		antiButton.setVisible(false);
		overlay.setVisible(false);
	}
}
