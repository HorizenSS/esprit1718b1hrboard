/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddUpdateprojectPopUpController implements Initializable {

	@FXML
	private AnchorPane skillsProjectPane;
	@FXML
	private JFXButton confirmProjectBtn;
	@FXML
	private JFXTextField projectName;
	@FXML
	private JFXTextField value;
	@FXML
	private JFXSlider feedback;
	@FXML
	private JFXDatePicker startDate;
	@FXML
	private JFXDatePicker endDate;
	@FXML
	private ImageView errorName;
	@FXML
	private ImageView errorStartdate;
	@FXML
	private ImageView errorEnddate;
	@FXML
	private ImageView errorVlaue;

	ProjectServiceRemote proxyProject;

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		confirmProjectBtn.getStyleClass().clear();
		projectName.getStyleClass().clear();
		value.getStyleClass().clear();
		startDate.getStyleClass().clear();
		endDate.getStyleClass().clear();
		feedback.getStyleClass().clear();
		// TODO
	}

	@FXML
	private void OnActionConfirmProject(ActionEvent event) {

		Boolean ok = true;
		Project project = new Project();
		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";

		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

		if (projectName.getText().equals("")) {
			errorName.setVisible(true);
			ok = false;
		} else {
			errorName.setVisible(false);
			ok = true;
		}
		if (startDate.getValue() == null) {
			errorStartdate.setVisible(true);
			ok = false;

		} else {
			errorStartdate.setVisible(false);
			ok = true;
		}
		if (endDate.getValue() == null) {
			errorEnddate.setVisible(true);
			ok = false;
		} else {
			errorEnddate.setVisible(false);
			ok = true;

		}
		if (value.getText().equals("")) {
			project.setGain(null);

		} else {
			Double val = Double.parseDouble(value.getText());
			project.setGain(val);
		}

		if (ok == true) {
			if (startDate.getValue().isAfter(endDate.getValue())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("ERROR DATE");
				alert.setContentText("THE START DATE CANNOT BE BEFORE THE ENDDATE");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						alert.close();
					}

				});
			} else {
				project.setName(projectName.getText());
				Date dateEnd = java.sql.Date.valueOf(endDate.getValue());
				project.setEndDate(dateEnd);
				Date dateStart = java.sql.Date.valueOf(startDate.getValue());
				project.setStartDate(dateStart);
				project.setClientAppreciation((float) feedback.getValue());
				proxyProject.save(project);
				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/ListProjects.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				refreshPa.getChildren().clear();
				refreshPa.getChildren().add(child);

			}

		}

	}

}
