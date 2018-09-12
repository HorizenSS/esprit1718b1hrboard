/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.google.common.base.Optional;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RowTaskController extends ListCell<Task> {

	@FXML
	private AnchorPane projectRow;
	@FXML
	private Label lblTaskName;
	@FXML
	private Label lblStartDate;
	@FXML
	private Label lblEndDate;
	@FXML
	private ProgressIndicator taskNoteProgress;
	@FXML
	private JFXProgressBar importanceIndicator;
	@FXML
	private Pane updateTask;
	@FXML
	private Pane deleteTask;

	private FXMLLoader mLLoader;

	TaskServiceRemote proxyTask;

	AnchorPane anchorPane;
	StackPane popStack;

	public static Task selectedTask;

	public static JFXDialog dialog;

	// public static ListTasksEmployeeController listTasksEmployeeController;

	public RowTaskController(AnchorPane pane, StackPane stackPane) {
		// TODO Auto-generated constructor stub
		anchorPane = pane;
		popStack = stackPane;
	}

	@Override
	protected void updateItem(Task task, boolean empty) {
		super.updateItem(task, empty);
		if (empty || task == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/RowTask.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (task.getEndDate() != null) {
				lblEndDate.setText(task.getEndDate().toString());
			} else {
				lblEndDate.setText("*****");
			}
			if (task.getStartDate() != null) {
				lblStartDate.setText(task.getStartDate().toString());
			} else {
				lblStartDate.setText("******");

			}
			if (task.getTaskPk() != null) {
				if (task.getTaskPk().getName() != null) {
					lblTaskName.setText(task.getTaskPk().getName());
				} else {
					lblTaskName.setText("*******");
				}

			} else {
				lblTaskName.setText("*******");
			}
			if (task.getTaskNote() != null) {
				taskNoteProgress.setProgress(task.getTaskNote() / 10);
			} else {
				taskNoteProgress.setProgress(0.1);

			}
			if (task.getImportance() != null) {
				importanceIndicator.setProgress(task.getImportance() / 10);

			} else {
				taskNoteProgress.setProgress(0.1);
			}

			deleteTask.setOnMouseClicked(e -> {
				String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
				proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTask);

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Delete Confiramtion");
				alert.setContentText("Are you sure to delete this Task ?");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						proxyTask.removeTask(task);
						// ListTasksEmployeeController controller= new
						// ListTasksEmployeeController();
						// controller.displayAllTasks();
						Pane child = null;

						try {
							child = FXMLLoader.load(getClass().getResource("/views/ListTasksEmployee.fxml"));
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}

						anchorPane.getChildren().clear();
						anchorPane.getChildren().add(child);

						System.out.println("************deleting Task !!************");
					}
				});

				// listTasksEmployeeController.INITAAT();

				// ListTasksEmployeeController cc = new
				// ListTasksEmployeeController();
				// cc.displayAllTasks();

			});

			updateTask.setOnMouseClicked(ev -> {
				System.out.println("!!!!!!!!!!!!!!!!! task update clicked !!!!!!!!!!!!!!!!!!!!!");

				selectedTask = task;
				UpdateTaskEmployeePopUpController.refreshPa = anchorPane;

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/UpdateTaskEmployeePopUp.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				JFXDialogLayout tasksLayout = new JFXDialogLayout();
				tasksLayout.setMaxWidth(310);
				tasksLayout.setMinWidth(310);
				tasksLayout.setPrefWidth(310);
				tasksLayout.setPrefHeight(310);
				tasksLayout.setMaxHeight(310);
				tasksLayout.setMinHeight(310);

				tasksLayout.getChildren().add(child);

				dialog = new JFXDialog(popStack, tasksLayout, JFXDialog.DialogTransition.BOTTOM);
				dialog.show();

			});

			setText(null);
			setGraphic(projectRow);

		}
	}

}
