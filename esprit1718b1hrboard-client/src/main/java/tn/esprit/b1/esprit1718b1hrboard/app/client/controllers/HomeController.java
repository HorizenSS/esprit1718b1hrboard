/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class HomeController implements Initializable {

	@FXML
	private StackPane rootPane;
	@FXML
	private VBox groupCompany;
	@FXML
	private AnchorPane homeEntrepriseBtn;
	@FXML
	private AnchorPane homeEmployeesBtn;
	@FXML
	private VBox groupTraining;
	@FXML
	private AnchorPane homeHiringBtn;
	@FXML
	private AnchorPane homePayrollBtn;
	@FXML
	private VBox groupReports;
	@FXML
	private AnchorPane homeTalentMgBtn;
	@FXML
	private AnchorPane homeLeaveMgBtn;
	@FXML
	private VBox groupSettings;
	@FXML
	private AnchorPane homeAnalyticsBtn;
	@FXML
	private AnchorPane homeActivityLogsBtn;
	@FXML
	private StackPane out_home_id;

	public static int direction = -1;// Variable de chargement du pricipal pane
										// selon le choix du l'utilisateur

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void OnClickHomeEntreprise(MouseEvent event) {
		direction = 1;
		Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/Navigation.fxml"));
			Scene scene = new Scene(root);
			principalStage.setScene(scene);
			principalStage.show();
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void OnClickHomeEmployees(MouseEvent event) {
		direction = 2;
		Parent root;
		try {
			
			root = FXMLLoader.load(getClass().getResource("/Views/Navigation.fxml"));
			Scene scene = new Scene(root);
			Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
			s.setScene(scene);
			s.show();
		} catch (IOException e) {
		}
		
		Pane newpane;
        try {
            newpane = FXMLLoader.load(getClass().getResource("/views/EmployeeHomeSpace.fxml"));
            NavigationController.sceneprincipal.getChildren().clear();
            NavigationController.sceneprincipal.getChildren().add(newpane);
        } catch (IOException ex) {
            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@FXML
	private void OnClickHomeLeaveMg(MouseEvent event) {
		direction = 3;
		Parent root;
		try {

			Pane child = FXMLLoader.load(getClass().getResource("/views/FormationRewardFXML.fxml"));
			NavigationController.initializeScene = child;
			root = FXMLLoader.load(getClass().getResource("/Views/Navigation.fxml"));
			Scene scene = new Scene(root);
			Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
			s.setScene(scene); 
			s.show();
		} catch (IOException e) {
		}
	}

	@FXML
	private void OnClickHomeHiring(MouseEvent event) {
		direction = 6;
		Parent root;
		try {

			Pane child = FXMLLoader.load(getClass().getResource("/views/Hirring.fxml"));
			NavigationController.initializeScene = child;	
			root = FXMLLoader.load(getClass().getResource("/Views/Navigation.fxml"));
			Scene scene = new Scene(root);
			Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
			s.setScene(scene);
			s.show();
		} catch (IOException e) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
		}
		Pane newpane;
        try {
            newpane = FXMLLoader.load(getClass().getResource("/views/JobOfferHirring.fxml"));
            HirringController.pane_hirring.getChildren().add(newpane);

        } catch (IOException ex) {
            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	}

	@FXML
	private void OnClickHomePayroll(MouseEvent event) {
		
		direction = 4;
		Parent root;
		try {

			Pane child = FXMLLoader.load(getClass().getResource("/views/Payroll.fxml"));
			NavigationController.initializeScene = child;	
			root = FXMLLoader.load(getClass().getResource("/Views/Navigation.fxml"));
			Scene scene = new Scene(root);
			Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
			s.setScene(scene);
			s.show();
		} catch (IOException e) { // TODO Auto-generated catch
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	

	@FXML
	private void OnClickHomeTalenMg(MouseEvent event) {
		direction = 5;
		Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		;
		principalStage.setTitle("");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/Navigation.fxml"));
			Scene scene = new Scene(root);
			principalStage.setScene(scene);
			principalStage.show();

		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void OnClickHomeAnalytics(MouseEvent event) {
		direction = 7;
		Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/Navigation.fxml"));
			Scene scene = new Scene(root);
			principalStage.setScene(scene);
			principalStage.show();
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void OnClickHomeActivityLogs(MouseEvent event) {
	}

}