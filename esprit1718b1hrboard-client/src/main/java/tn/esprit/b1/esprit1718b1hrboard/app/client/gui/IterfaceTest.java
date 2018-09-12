/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tn.esprit.b1.esprit1718b1hrboard.app.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class IterfaceTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {

Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/views/SuperAdminFXML.fxml"));


		//Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));


		// Parent root =
		// FXMLLoader.load(getClass().getResource("/views/Shttps://www.youtube.com/watch?v=vUTI4bPdlgE&list=RDGMEMHDXYb1_DDSgDsobPsOFxpAVMvUTI4bPdlgEuperAdminFXML.fxml"));


		// Parent root =
		// FXMLLoader.load(getClass().getResource("/views/SuperAdminFXML.fxml"));

		// Parent root =
		// FXMLLoader.load(getClass().getResource("/views/SuperAdminFXML.fxml"));

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
