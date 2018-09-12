/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateSkillPopPupController implements Initializable {

	@FXML
	private AnchorPane popupSkilDetails;
	@FXML
	private Label entitledArea;
	@FXML
	private Spinner<Integer> yerasExp;
	@FXML
	private JFXCheckBox chekCertif;
	@FXML
	private JFXSlider levelExp;
	@FXML
	private ImageView errorEntitled;
	@FXML
	private ImageView errorType;
	@FXML
	private ImageView errorExperience;
	@FXML
	private JFXButton ConfirmUpdateBtn;

	public static EmployeeHasSkill employeeHasSkill;
	@FXML
	private Label skillAre;

	EmployeeHasSkillServiceRemote proxyEmpHasSkill;

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ConfirmUpdateBtn.getStyleClass().clear();
		populateSkillUpdate();
		// TODO
	}

	public void populateSkillUpdate() {

		if (employeeHasSkill.getSkill().getName() != null) {
			entitledArea.setText(employeeHasSkill.getSkill().getName());
		} else {
			entitledArea.setText("");
		}
		if (employeeHasSkill.getCertifcation() != null) {
			if (employeeHasSkill.getCertifcation() == true) {
				chekCertif.setSelected(true);
			} else {
				chekCertif.setSelected(false);
			}
		} else {
			chekCertif.setSelected(false);
		}
		if (employeeHasSkill.getSkill().getType() != null) {
			skillAre.setText(employeeHasSkill.getSkill().getType());
		} else {
			skillAre.setText("");
		}
		if (employeeHasSkill.getSkillNote() != null) {
			levelExp.setValue(employeeHasSkill.getSkillNote());
		} else {
			levelExp.setValue(0.1);

		}
		if (employeeHasSkill.getLevel() != null) {
			SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,
					employeeHasSkill.getLevel());
			yerasExp.setValueFactory(score);
		} else {
			SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
			yerasExp.setValueFactory(score);
		}

	}

	@FXML
	private void OnActionConfirmUpdate(ActionEvent event) {
		Boolean ok = true;

		String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
		proxyEmpHasSkill = (EmployeeHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiEmployeeHasSkill);

		if (yerasExp.getValue() == null) {
			ok = false;
			errorExperience.setVisible(true);
		} else {
			errorExperience.setVisible(false);

		}

		if (ok == true) {
			employeeHasSkill.setLevel(yerasExp.getValue());
			employeeHasSkill.setCertifcation(chekCertif.isSelected());
			employeeHasSkill.setSkillNote((float) levelExp.getValue());
			proxyEmpHasSkill.update(employeeHasSkill);

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

}
