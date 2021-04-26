package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML private TextField idUsuario;
	@FXML private PasswordField idSenha;
	@FXML private Button idEntrar;
	
	public void entrar(ActionEvent event) throws Exception{
		System.out.println("Inicializado!");
	}
}
