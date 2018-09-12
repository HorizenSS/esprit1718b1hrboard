/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProjectEmployeeRowController extends ListCell<Project> {

	@FXML
	private Label lblMasterName;
	@FXML
	private Label lblProjectName;
	@FXML
	private Label lblStartDate;
	@FXML
	private Label lblEndDate;
	@FXML
	private Label lblStatus;
	@FXML
	private ProgressIndicator clientFeedbackProgress;
	@FXML
	private Label valueProject;
	@FXML
	private AnchorPane projectRow;
	@FXML
	private Pane tasksEmployeeBtn;

	public StackPane widgetProject;

	private FXMLLoader mLLoader;

	public static JFXDialog dialog;

	public static Project selectedProject;

	public ProjectEmployeeRowController(StackPane anchorPane) {
		widgetProject = anchorPane;
	}

	@Override
	protected void updateItem(Project project, boolean empty) {
		super.updateItem(project, empty);
		if (empty || project == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/projectEmployeeRow.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (project.getName() != null) {
				lblProjectName.setText(project.getName());
			} else {
				lblProjectName.setText("******");
			}
			if (project.getEndDate() != null) {
				lblEndDate.setText(project.getEndDate().toString());
			} else {
				lblEndDate.setText("******");
			}
			if (project.getStartDate() != null) {
				lblStartDate.setText(project.getStartDate().toString());
			} else {
				lblStartDate.setText("*****");
			}
			if (project.getGain() != null) {
				valueProject.setText(project.getGain().toString());
			} else {
				valueProject.setText("*****");
			}
			if (project.getClientAppreciation() != null) {
				clientFeedbackProgress.setProgress(project.getClientAppreciation() / 10);

			} else {
				clientFeedbackProgress.setProgress(0.1);

			}
			
			if(project.getProjectMaster()!=null){
				if (project.getProjectMaster().getFirstName() == null || project.getProjectMaster().getLastName() == null) {
					lblMasterName.setText("*******");
				} else {
					lblMasterName.setText(
							project.getProjectMaster().getFirstName() + " " + project.getProjectMaster().getLastName());

				}
			}
			else{
				lblMasterName.setText("*******");

			}
			
	

			tasksEmployeeBtn.setOnMouseClicked(ev -> {

				selectedProject = project;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/ListTasksEmployee.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout tasksLayout = new JFXDialogLayout();
				tasksLayout.setMaxWidth(482);
				tasksLayout.setMinWidth(482);
				tasksLayout.setPrefWidth(482);
				tasksLayout.setPrefHeight(377);
				tasksLayout.setMaxHeight(377);
				tasksLayout.setMinHeight(377);

				tasksLayout.getChildren().add(child);

				dialog = new JFXDialog(widgetProject, tasksLayout, JFXDialog.DialogTransition.RIGHT);
				dialog.show();

			});

			setText(null);
			setGraphic(projectRow);

		}
	}

}
