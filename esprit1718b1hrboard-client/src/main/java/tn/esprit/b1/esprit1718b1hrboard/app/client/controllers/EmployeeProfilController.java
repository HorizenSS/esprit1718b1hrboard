/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployeeProfilController implements Initializable {

	@FXML
	private AnchorPane profilePane;
	private AnchorPane benefitsPane;
	@FXML
	private AnchorPane absencesPane;
	@FXML
	private AnchorPane projectsPane;

	public static Pane paneAbs;
	@FXML
	private Tab profilTab;
	@FXML
	private Tab absTab;
	@FXML
	private Tab projectTab;

	// public static int changePane = 0;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		Pane child;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/profilePane.fxml"));
			profilePane.getChildren().add(child);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void profileChange(Event event) {

		Pane child;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/profilePane.fxml"));
			profilePane.getChildren().add(child);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void benefitsChange(Event event) {

		Pane child;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/BenefitsPane.fxml"));
			benefitsPane.getChildren().add(child);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void absencesChanges(Event event) {

		Pane child;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/ListAbscenceAndDelays.fxml"));
			absencesPane.getChildren().add(child);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void projectsChange(Event event) {

		Pane child;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/EmployeeProjects.fxml"));
			projectsPane.getChildren().add(child);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
