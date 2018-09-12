/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailsSkillsPopUpController implements Initializable {

	@FXML
	private AnchorPane popupSkilDetails;
	@FXML
	private Label nameSkill;
	@FXML
	private Label typeSkill;
	@FXML
	private Label exepSkill;
	@FXML
	private Label certifSkill;
	@FXML
	private Label levelSkill;

	public static EmployeeHasSkill employeeHasSkill;
    @FXML
    private Button updateSkillBtn;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if (employeeHasSkill.getSkill() != null) {
			if (employeeHasSkill.getSkill().getName() != null) {
				nameSkill.setText(employeeHasSkill.getSkill().getName());
			} else {
				nameSkill.setText("*****");

			}
			if (employeeHasSkill.getSkill().getType() != null) {
				typeSkill.setText(employeeHasSkill.getSkill().getType());
			} else {
				typeSkill.setText("*****");
			}
		}
		if (employeeHasSkill.getLevel() != null) {
			exepSkill.setText(employeeHasSkill.getLevel().toString());
		} else {
			exepSkill.setText("*****");
		}
		if (employeeHasSkill.getCertifcation() != null) {
			certifSkill.setText(employeeHasSkill.getCertifcation().toString());
		} else {
			certifSkill.setText("******");
		}
		if (employeeHasSkill.getSkillNote() != null) {
			levelSkill.setText(employeeHasSkill.getSkillNote().toString());
		} else {
			levelSkill.setText("******");

		}

		// TODO
	}

    @FXML
    private void OnActionUpdateSkill(ActionEvent event) {
    	
    	
    }

}
