/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXNodesList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.KeyValue;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class NavigationController implements Initializable {

	@FXML
	private Label widget_name_id;
	@FXML
	private StackPane out_id;
	@FXML
	private ImageView cnx_image_id;
	@FXML
	private Label cnx_name_id;
	@FXML
	private Label cnx_role_id;
	@FXML
	private JFXButton btnHome;
	@FXML
	private JFXButton btnEntreprise;
	@FXML
	private JFXButton btnEmployee;
	@FXML
	private JFXButton btnHiring;
	@FXML
	private JFXButton btnPayroll;
	@FXML
	private JFXButton btnTalentMg;
	@FXML
	private JFXButton btnLeaveMg;
	@FXML
	private JFXButton btnAnalytics;
	@FXML
	private JFXButton btnActivityLogs;
	@FXML
	private Pane principal_scene_id;
	@FXML
	private AnchorPane fullparent_id;
	public static Pane initializeScene;
	public static Pane sceneprincipal;
	// public static Pane initializeScene2;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		sceneprincipal = principal_scene_id;
		if (HomeController.direction == 1) {// condition du chargement de la
											// pane demandée par le user
			try {
				initializeScene = FXMLLoader.load(getClass().getResource("/views/EntrepriseMenu.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(initializeScene);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (HomeController.direction == 2) {// condition du chargement de la
											// pane demandée par le user
			// principal_scene_id.getChildren().clear();
			// principal_scene_id.getChildren().add(initializeScene);
			try {
				initializeScene = FXMLLoader.load(getClass().getResource("/views/EmployeeHomeSpace.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(initializeScene);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (HomeController.direction == 3) {// condition du chargement de la
											// pane demandée par le user
			try {
				Pane P = FXMLLoader.load(getClass().getResource("/views/LeaveMenu.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(P);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (HomeController.direction == 5) {// condition du chargement de la
											// pane demandée par le user
			try {
				Pane P = FXMLLoader.load(getClass().getResource("/views/FormationRewardFXML.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(P);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		if (HomeController.direction == 4) {// condition du chargement de la
											// pane demandée par le user
			try {
				Pane P = FXMLLoader.load(getClass().getResource("/views/Payroll.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(P);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (HomeController.direction == 6) {// condition du chargement de la
											// pane demandée par le user
			try {
				Pane newpane = FXMLLoader.load(getClass().getResource("/views/Hirring.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(newpane);

			} catch (IOException e) {
				System.out.println("error OnClickHiring NavigationController");
			}
		}
		if (HomeController.direction == 7) {// condition du chargement de la
			// pane demandée par le user
			try {
				Pane newpane = FXMLLoader.load(getClass().getResource("/views/TrainingMenu.fxml"));
				principal_scene_id.getChildren().clear();
				principal_scene_id.getChildren().add(newpane);

			} catch (IOException e) {
				System.out.println("error OnClickHiring NavigationController");
			}
		}
		setUpFabs(); // Method to disconnect or close the software

	}

	/**
	 * Initializes the controller class.
	 */

	@FXML
	private void OnClickEntrprise(ActionEvent event) {
		try {
			Pane P = FXMLLoader.load(getClass().getResource("/views/EntrepriseMenu.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(P);
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void OnClickHiring(ActionEvent event) {
		try {
			Pane newpane = FXMLLoader.load(getClass().getResource("/views/Hirring.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(newpane);

		} catch (IOException e) {
			System.out.println("error OnClickHiring NavigationController");
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
	private void OnClickEmployees(ActionEvent event) {

		try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/EmployeeHomeSpace.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(child);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void OnClickLeaveMg(ActionEvent event) {
		try {
			Pane P = FXMLLoader.load(getClass().getResource("/views/LeaveMenu.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(P);
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void OnClickPayroll(ActionEvent event) {
		try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/Payroll.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void OnClickTalentMg(ActionEvent event) {
		try {
			Pane P = FXMLLoader.load(getClass().getResource("/views/FormationRewardFXML.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(P);
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void OnClickAnalytics(ActionEvent event) {
	}

	@FXML
	private void OnClickActivityLogs(ActionEvent event) {
		try {
			Pane P = FXMLLoader.load(getClass().getResource("/views/TrainingMenu.fxml"));
			principal_scene_id.getChildren().clear();
			principal_scene_id.getChildren().add(P);
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/* Methode to disconnect or close the app */
	private void setUpFabs() {
		// Setting up icons for button
		FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CIRCLE_ALT_NOTCH);
		icon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");

		FontAwesomeIconView closeicon = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
		closeicon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");
		FontAwesomeIconView logicon = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK_ALT);
		logicon.setStyle("-fx-fill:#ffffff;-fx-size:13px;");

		JFXButton button1 = new JFXButton();
		Label label1 = new Label("G1");
		button1.setGraphic(icon);
		label1.setStyle("-fx-text-fill:WHITE");
		button1.setButtonType(JFXButton.ButtonType.RAISED);
		button1.setStyle("-fx-pref-width:30px;-fx-background-color:#F6C574;"
				+ "-fx-background-radius:30px;-fx-pref-height:30px;");

		JFXButton button2 = new JFXButton();
		button2.setTooltip(new Tooltip("Log off"));
		button2.setButtonType(JFXButton.ButtonType.RAISED);
		button2.setGraphic(logicon);
		button2.setStyle("-fx-pref-width:30px;-fx-background-color:#F6C574;"
				+ "-fx-background-radius:30px;-fx-pref-height:30px;");
		button2.setOnAction((ActionEvent event) -> {
			try {
				fullparent_id.getScene().getWindow().hide();

				Parent rood = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
				Scene scene = new Scene(rood);
				Stage driverStage = new Stage();
				driverStage.setScene(scene);
				driverStage.show();
			} catch (IOException ex) {
				// Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE,
				// null, ex);
			}
		});

		JFXButton button3 = new JFXButton();
		button3.setButtonType(JFXButton.ButtonType.RAISED);
		button3.setTooltip(new Tooltip("Exit"));
		button3.setGraphic(closeicon);
		button3.setStyle("-fx-pref-width:30px;-fx-background-color:#F87951;"
				+ "-fx-background-radius:30px;-fx-pref-height:30px;");
		button3.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});

		JFXNodesList nodesList = new JFXNodesList();
		nodesList.setSpacing(10);

		nodesList.addAnimatedNode(button1, (expanded) -> {
			return new ArrayList<KeyValue>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					// add(new KeyValue(button1.rotateProperty(), expanded ? 360
					// : 0, Interpolator.EASE_BOTH));
				}
			};
		});
		nodesList.addAnimatedNode(button2);
		nodesList.addAnimatedNode(button3);

		out_id.getChildren().add(nodesList);

	}

}