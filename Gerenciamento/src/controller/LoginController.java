package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.Telas;

public class LoginController {
	@FXML private TextField idUsuario;
	@FXML private PasswordField idSenha;
	@FXML private Label error;
	
	public void autenticar(ActionEvent event) throws Exception{
		String usuario = idUsuario.getText();
		String senha = idSenha.getText();
		if (usuario.equals("cpfdono") && senha.equals("nomedono")) {
			Telas.telaMenu();
		}
		else {
			error.setVisible(true);
		}
	}
}
