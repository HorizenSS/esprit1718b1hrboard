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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectService;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProjectRowController extends ListCell<Project> {

	@FXML
	private AnchorPane projectRow;
	@FXML
	private Pane projectMasterBtn;
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
	private Pane projectTeamBtn;
	@FXML
	private Pane projectSkillsBtn;
	@FXML
	private Pane deleteProjectBtn;
	@FXML
	private Pane updateProjectBtn;

	private FXMLLoader mLLoader;

	private AnchorPane refreshPane;
	private StackPane stackPopUp;

	public static JFXDialog dialog;

	public static Project selectedProject;

	private ProjectServiceRemote proxyProject;

	/**
	 * Initializes the controller class.
	 */

	public ProjectRowController(StackPane panePop, AnchorPane paneRefresh) {
		refreshPane = paneRefresh;
		stackPopUp = panePop;
	}

	@Override
	protected void updateItem(Project project, boolean empty) {
		super.updateItem(project, empty);
		if (empty || project == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/projectRow.fxml"));
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
				lblProjectName.setText("*****");
			}
			if (project.getEndDate() != null) {
				lblEndDate.setText(project.getEndDate().toString());

			} else {
				lblEndDate.setText("*****");

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
			if (project.getProjectMaster() != null) {
				lblMasterName.setText(
						project.getProjectMaster().getFirstName() + " " + project.getProjectMaster().getLastName());
			} else {
				lblMasterName.setText("*****");
			}

			projectSkillsBtn.setOnMouseClicked(ev -> {
				selectedProject = project;
				SkillsRequiredInProjectPopUpController.refreshPa = refreshPane;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/SkillsRequiredInProjectPopUp.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout absLayout = new JFXDialogLayout();
				absLayout.setMaxWidth(310);
				absLayout.setMinWidth(310);
				absLayout.setPrefWidth(310);
				absLayout.setPrefHeight(300);
				absLayout.setMaxHeight(300);
				absLayout.setMinHeight(300);

				absLayout.getChildren().add(child);

				dialog = new JFXDialog(stackPopUp, absLayout, JFXDialog.DialogTransition.CENTER);
				dialog.show();
			});

			projectTeamBtn.setOnMouseClicked(ev -> {

				selectedProject = project;
				TeamListProjectController.refreshPa = refreshPane;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/TeamListProject.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout absLayout = new JFXDialogLayout();
				absLayout.setMaxWidth(310);
				absLayout.setMinWidth(310);
				absLayout.setPrefWidth(310);
				absLayout.setPrefHeight(300);
				absLayout.setMaxHeight(300);
				absLayout.setMinHeight(300);

				absLayout.getChildren().add(child);

				dialog = new JFXDialog(stackPopUp, absLayout, JFXDialog.DialogTransition.CENTER);
				dialog.show();

			});

			projectMasterBtn.setOnMouseClicked(ev -> {

				selectedProject = project;
				MasterProjectPopuPController.refreshPa = refreshPane;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/MasterProjectPopuP.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout absLayout = new JFXDialogLayout();
				absLayout.setMaxWidth(206);
				absLayout.setMinWidth(206);
				absLayout.setPrefWidth(206);
				absLayout.setPrefHeight(182);
				absLayout.setMaxHeight(182);
				absLayout.setMinHeight(182);

				absLayout.getChildren().add(child);

				dialog = new JFXDialog(stackPopUp, absLayout, JFXDialog.DialogTransition.CENTER);
				dialog.show();

			});

			updateProjectBtn.setOnMouseClicked(ev -> {
				UpdateProjectController.refreshPa = refreshPane;
				selectedProject = project;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/UpdateProject.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFXDialogLayout absLayout = new JFXDialogLayout();
				absLayout.setMaxWidth(310);
				absLayout.setMinWidth(310);
				absLayout.setPrefWidth(310);
				absLayout.setPrefHeight(300);
				absLayout.setMaxHeight(300);
				absLayout.setMinHeight(300);

				absLayout.getChildren().add(child);

				dialog = new JFXDialog(stackPopUp, absLayout, JFXDialog.DialogTransition.CENTER);
				dialog.show();

			});

			deleteProjectBtn.setOnMouseClicked(ev -> {
				String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
				proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Delete Confirmation");
				alert.setContentText("Are you sure to delete this Project ?");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						proxyProject.removeProject(project);
						Pane child = null;
						try {
							child = FXMLLoader.load(getClass().getResource("/views/ListProjects.fxml"));
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						refreshPane.getChildren().clear();
						refreshPane.getChildren().add(child);

						System.out.println("************deleting************");
					}
				});

			});

			setText(null);
			setGraphic(projectRow);

		}
	}

}
