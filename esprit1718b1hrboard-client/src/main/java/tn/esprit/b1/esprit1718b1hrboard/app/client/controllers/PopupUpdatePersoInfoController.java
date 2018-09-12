/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeService;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PopupUpdatePersoInfoController implements Initializable {

	@FXML
	private JFXTextField lblCin;
	@FXML
	private JFXTextField lblPass;
	@FXML
	private JFXTextField lblStatus;
	@FXML
	private JFXTextField lblChild;
	@FXML
	private JFXTextField lblRib;
	@FXML
	private JFXTextField lblWorkedHrs;
	@FXML
	private JFXButton confirmUpEmployeeBtn;
	@FXML
	private ImageView errorCin;
	@FXML
	private ImageView errorPassport;
	@FXML
	private ImageView errorStatus;
	@FXML
	private ImageView errorRib;
	@FXML
	private ImageView errorWorkedHrs;
	@FXML
	private ImageView errorChild;

	private EmployeeServiceRemote proxyEmployee;

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lblCin.getStyleClass().clear();
		lblPass.getStyleClass().clear();
		lblChild.getStyleClass().clear();
		lblRib.getStyleClass().clear();
		lblRib.getStyleClass().clear();
		lblWorkedHrs.getStyleClass().clear();
		confirmUpEmployeeBtn.getStyleClass().clear();

		populateInfoPerso();

		// TODO
	}

	@FXML
	private void OnActionConfirmUpEmployee(ActionEvent event) {

		Boolean ok = true;

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);

		if (lblCin.getText().equals("")) {
			ok = false;
			errorCin.setVisible(true);
		} else {
			errorCin.setVisible(false);
		}
		if (lblChild.getText().equals("")) {
			ok = false;
			errorChild.setVisible(true);
		} else {
			errorChild.setVisible(false);
		}
		if (lblPass.getText().equals("")) {
			ok = false;
			errorPassport.setVisible(true);
		} else {
			errorPassport.setVisible(false);
		}
		if (lblRib.getText().equals("")) {
			ok = false;
			errorRib.setVisible(true);
		} else {
			errorRib.setVisible(false);
		}
		if (lblStatus.getText().equals("")) {
			ok = false;
			errorStatus.setVisible(true);
		} else {
			errorStatus.setVisible(false);
		}
		if (lblWorkedHrs.getText().equals("")) {
			ok = false;
			errorWorkedHrs.setVisible(true);
		} else {
			errorWorkedHrs.setVisible(false);
		}

		if (ok == true) {
			Employee emp = EmployeesGeneralListController.employee;

			emp.setCin(lblCin.getText());
			Integer ch = Integer.parseInt(lblChild.getText());
			emp.setChildNumber(ch);
			emp.setCode("2018esprit" + lblCin.getText());
			emp.setSocialStaus(lblStatus.getText());
			Integer wh = Integer.parseInt(lblWorkedHrs.getText());
			emp.setWorkedHours(wh);
			Long rib = Long.parseLong(lblRib.getText());
			emp.setRibAccount(rib);
			emp.setNumPassport(lblPass.getText());

			proxyEmployee.update(emp);

			proxyEmployee.update(emp);

			Pane child = null;

			try {
				child = FXMLLoader.load(getClass().getResource("/views/profilePane.fxml"));
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

			refreshPa.getChildren().clear();
			refreshPa.getChildren().add(child);

		}
	}

	void populateInfoPerso() {

		Employee employee = EmployeesGeneralListController.employee;

		if (employee.getCin() != null) {
			lblCin.setText(employee.getCin());
		} else {
			lblCin.setText("");
		}
		if (employee.getNumPassport() != null) {
			lblPass.setText(employee.getNumPassport());
		} else {
			lblPass.setText("");
		}
		if (employee.getSocialStaus() != null) {
			lblStatus.setText(employee.getSocialStaus());
		} else {
			lblStatus.setText("");
		}
		if (employee.getChildNumber() != null) {
			lblChild.setText(employee.getChildNumber().toString());
		} else {
			lblChild.setText("");
		}
		if (employee.getRibAccount() != null) {
			lblRib.setText(employee.getRibAccount().toString());
		} else {
			lblRib.setText("");
		}
		if (employee.getWorkedHours() != null) {
			lblWorkedHrs.setText(employee.getWorkedHours().toString());
		} else {
			lblWorkedHrs.setText("");
		}
	}

}
