/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.MD5;
import tn.esprit.b1.esprit1718b1hrboard.entities.Access;
import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 *
 * @author danml
 */
public class LoginController implements Initializable {

	private Label label;
	@FXML
	private StackPane rootPane;

	Account account;
	Employee employee;
	EmployeeServiceRemote proxyEmployee;
	AccountServiceRemote proxyAccount;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private ImageView imgProgress;

	private void handleButtonAction(ActionEvent event) {
		System.out.println("You clicked me!");
		label.setText("Hello World!");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String jndiAccount = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AccountService!tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote";
					String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
					Context context;
					try {
						context = new InitialContext();
						proxyEmployee = (EmployeeServiceRemote) context.lookup(jndiEmployee);
						proxyAccount = (AccountServiceRemote) context.lookup(jndiAccount);
					} catch (NamingException e) {
						e.printStackTrace();
						System.out.println("naming ejb error");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		handleValidation();
		imgProgress.setVisible(false);

	}

    @FXML
	private void login(ActionEvent event) {

		account = null;
		MD5 md = new MD5();
		String mdp = md.hash(txtPassword.getText());
		System.out.println(mdp);
		System.out.println(txtPassword.getText());
		System.out.println(proxyAccount.findAll());
		System.out.println(txtPassword.getText());
		account = proxyAccount.login( txtUsername.getText(),mdp);
		if (account == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Login Failed");
			alert.setContentText("Eaither Username Or Login Are Wrong");
			alert.show();
		} else {
			 employee = proxyEmployee.accessAccount(account);
			imgProgress.setVisible(true);
			PauseTransition pauseTransition = new PauseTransition();
			pauseTransition.setDuration(Duration.seconds(3));
			pauseTransition.setOnFinished(ev -> {
				completeLogin(account);

			});
			pauseTransition.play();
		}

	}

	private void handleValidation() {
		RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
		fieldValidator.setMessage("Input required");
		fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
		txtUsername.getValidators().add(fieldValidator);
		txtUsername.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
					if (!newVal) {
						txtUsername.validate();

					}
				});
		RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
		fieldValidator2.setMessage("Input required");
		fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
		txtPassword.getValidators().add(fieldValidator2);
		txtPassword.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (!newValue) {
						txtPassword.validate();
					}
				});

	}

	private void completeLogin(Account account) {

		btnLogin.getScene().getWindow().hide();
		try { if (account.getAccess()== Access.SYSTEMADMIN)
		{
			
			imgProgress.setVisible(false);
			Stage dashboardStage = new Stage();
			dashboardStage.setTitle("");
			Parent root = FXMLLoader.load(getClass().getResource("/views/SuperAdminFXML.fxml"));
			Scene scene = new Scene(root);
			dashboardStage.setScene(scene);
			dashboardStage.show();
			
			
		} else {
			imgProgress.setVisible(false);
			Stage dashboardStage = new Stage();
			dashboardStage.setTitle("");
			Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
			Scene scene = new Scene(root);
			dashboardStage.setScene(scene);
			dashboardStage.show();}
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}



}
