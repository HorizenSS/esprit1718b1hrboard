/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployeeHomeSpaceController implements Initializable {

	@FXML
	private AnchorPane ProjectsBtn;
	@FXML
	private AnchorPane TodayDelayBtn;
	@FXML
	private AnchorPane TodayAbsBtn;
	@FXML
	private AnchorPane EmployeeMgBtn;
	@FXML
	private JFXProgressBar waitBar;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void OnActionProjects(MouseEvent event) {
	}

	@FXML
	private void OnActionTodayDelay(MouseEvent event) {
	}

	@FXML
	private void OnActionTodayAbs(MouseEvent event) {
	}

	@FXML
	private void OnActionEmployeeMg(MouseEvent event) {

		waitBar.setVisible(true);

		EmployeesMgmntSpaceController.changePane = 1;
		// System.out.println("ok from employee mgnmt test !!!!");
		Pane child;
		// Pane child2;
		Parent root;
		try {

			// child2 =
			// FXMLLoader.load(getClass().getResource("/views/EmployeesGeneralList.fxml"));
			// EmployeesMgmntSpaceController.InsideEmployeesWorkSpaceOutside.getChildren().add(child2);
			child = FXMLLoader.load(getClass().getResource("/views/EmployeesMgmntSpace.fxml"));
			NavigationController.sceneprincipal.getChildren().clear();
			NavigationController.sceneprincipal.getChildren().add(child);

			
		} catch (IOException e) {
		}

	}

}
