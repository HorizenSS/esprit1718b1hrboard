package tn.esprit.b1.esprit1718b1hrboard.app.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Interface extends Application {

	@Override
	public void start(Stage stage) throws Exception {

// HEAD
		Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));

	//	Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
// branch 'master' of http://bitbox.tn:2015/root/esprit1718b1hrboard.git
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
