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
import java.sql.Date;
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
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.TaskPk;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddTaskPopUpController implements Initializable {

	@FXML
	private AnchorPane skillsProjectPane;
	@FXML
	private JFXButton confirmTaskBtn;
	@FXML
	private JFXTextField taskName;
	@FXML
	private JFXSlider progressBarTask;
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
	private JFXSlider importanceBar;

	private TaskServiceRemote proxyTask;

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		confirmTaskBtn.getStyleClass().clear();
		taskName.getStyleClass().clear();
		progressBarTask.getStyleClass().clear();
		importanceBar.getStyleClass().clear();
		startDate.getStyleClass().clear();
		endDate.getStyleClass().clear();
	}

	@FXML
	private void OnActionConfirmTask(ActionEvent event) {

		Boolean ok = true;
		Task task = new Task();
		TaskPk taskPk = new TaskPk();
		taskPk.setIdEmployee(EmployeesGeneralListController.employee.getId());
		taskPk.setIdProject(ProjectEmployeeRowController.selectedProject.getId());
		String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
		proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTask);

		if (taskName.getText().equals("")) {// A verifier que le nom du task
											// n'existe pas pour le project
											// selectionné !
			errorName.setVisible(true);
			ok = false;
		} else {
			errorName.setVisible(false);
			// ok = true;
		}
		if (startDate.getValue() == null) {
			errorStartdate.setVisible(true);
			ok = false;

		} else {
			errorStartdate.setVisible(false);
			// ok = true;
		}
		if (endDate.getValue() == null) {
			errorEnddate.setVisible(true);
			ok = false;
		} else {
			errorEnddate.setVisible(false);
			// ok = true;

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
			} else if (ProjectEmployeeRowController.selectedProject.getStartDate()
					.after(Date.valueOf(startDate.getValue()))
					|| ProjectEmployeeRowController.selectedProject.getEndDate()
							.before(Date.valueOf(startDate.getValue()))
					|| ProjectEmployeeRowController.selectedProject.getStartDate()
							.after(Date.valueOf(endDate.getValue()))
					|| ProjectEmployeeRowController.selectedProject.getEndDate()
							.before(Date.valueOf(endDate.getValue()))) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("ERROR DATE");
				alert.setContentText("THE TASK PERIODE MUST BE INCLUDED IN THE PROJECT PERIODE");
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						alert.close();
					}

				});
			} else {
				taskPk.setName(taskName.getText());
				task.setTaskPk(taskPk);
				task.setEmployee(EmployeesGeneralListController.employee);
				task.setProject(ProjectEmployeeRowController.selectedProject);
				task.setStartDate(java.sql.Date.valueOf(startDate.getValue()));
				task.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
				task.setImportance((float) importanceBar.getValue());
				task.setTaskNote((float) progressBarTask.getValue());

				proxyTask.save(task);

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/ListTasksEmployee.fxml"));
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
