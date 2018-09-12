/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.entities.Vacation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class NewResRowController extends ListCell<Resignation> implements Initializable {

	@FXML
	private ImageView EmployeePic;
	@FXML
	private Label lblFirstLastName;
	@FXML
	private Label employeePostLbl;
	@FXML
	private Label DemandSubmissionDateLbl;
	@FXML
	private Label RegistredLbl;
	@FXML
	private ImageView Status;
	@FXML
	private AnchorPane MainHandler;

	private FXMLLoader mLLoader;

	public static Resignation vv;

	EmployeeServiceRemote proxyEmployee;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@Override
	protected void updateItem(Resignation Vacc, boolean empty) {
		super.updateItem(Vacc, empty);

		if (empty || Vacc == null) {

			setText(null);
			setGraphic(null);

		} else {
			if (mLLoader == null) {
				SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
				mLLoader = new FXMLLoader(getClass().getResource("/views/NewResRow.fxml"));
				mLLoader.setController(this);
				vv = Vacc;
				try {
					mLLoader.load();
				} catch (IOException e) {
				}
				javafx.scene.image.Image ix = new javafx.scene.image.Image(
						getClass().getResourceAsStream("/icons/X.png"));
				javafx.scene.image.Image iv = new javafx.scene.image.Image(
						getClass().getResourceAsStream("/icons/V.png"));
				if (Vacc.getStatus() != null) {
					if (Vacc.getStatus() == true) {
						Status.setImage(iv);
						Status.setVisible(true);
						RegistredLbl.setVisible(true);
					} else {
						Status.setImage(ix);
						Status.setVisible(true);
						RegistredLbl.setVisible(true);
					}
					DemandSubmissionDateLbl.setText("Responded in : " + sd.format(Vacc.getResponseDate()));
				} else {
					Status.setVisible(false);
					RegistredLbl.setVisible(false);
					DemandSubmissionDateLbl.setText("Submitted in : " + sd.format(Vacc.getSubmissionDate()));
				}
				proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
				Employee emp = proxyEmployee.EmployeeByResignation(Vacc);
				lblFirstLastName.setText(emp.getFirstName() + " " + emp.getLastName());
				employeePostLbl.setText(emp.getPost().getEntitled());
				setText(null);
				setGraphic(MainHandler);

			}

		}

	}

}
