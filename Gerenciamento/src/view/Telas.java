package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
	
	private static Stage primaryStage;

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void setPrimaryStage(Stage primaryStage) {
		Telas.primaryStage = primaryStage;
	}

	public void start(Stage pS) throws Exception {
		setPrimaryStage(pS);
		pS.setResizable(false);
		telaLogin();
		
	}

	public static void telaLogin() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaDeLogin.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}
	
	public static void telaMenu() throws Exception{
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaDeMenu.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void telaEstoque() throws Exception{
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaDeEstoque.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void telaCadastroP() throws Exception{
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaNovoProduto.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void telaClientes() throws Exception{
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaCliente.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void telaCadastroC() throws Exception{
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaCadastroClientes.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();

	}
	
}
