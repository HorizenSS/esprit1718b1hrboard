/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListTasksEmployeeController implements Initializable {

	@FXML
	private JFXRadioButton allTasks;
	@FXML
	private JFXRadioButton doingTasks;
	@FXML
	private JFXRadioButton toDoTasks;
	@FXML
	private JFXRadioButton doneTasks;
	@FXML
	private JFXButton newTaskBtn;
	@FXML
	private ToggleGroup filter;
	@FXML
	private JFXListView<Task> tasksListView;

	public ObservableList<Task> tasksObsList;

	TaskServiceRemote proxyTask;

	Employee employee = EmployeesGeneralListController.employee;
	Project project = ProjectEmployeeRowController.selectedProject;

	List<Task> tasklist = new ArrayList<>();
	List<Task> tasklistFinal = new ArrayList<>();
	@FXML
	private AnchorPane taskListPane;

	public static JFXDialog dialog;
	@FXML
	private StackPane StackPopPup;

	// public URL url1;
	// public ResourceBundle rb1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// url1 = url;
		// rb1 = rb ;
		displayAllTasks();
		// TODO
	}

	public void displayAllTasks() {

		String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
		proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTask);

		tasklist = proxyTask.findTasksByEmployeeAndProject(employee, project);

		for (Task task : tasklist) {
			if (!task.getTaskPk().getName().equals("TASK1")) {
				tasklistFinal.add(task);
			}
		}

		tasksObsList = FXCollections.observableArrayList(tasklistFinal);

		tasksListView.refresh();

		tasksListView.setItems(tasksObsList);

		tasksListView.setCellFactory(projectEmployeeLv -> new RowTaskController(taskListPane, StackPopPup));

		allTasks.setOnAction(e -> {
			List<Task> taskslistFilter = new ArrayList<>();
			taskslistFilter.addAll(tasklistFinal);
			tasksObsList = FXCollections.observableArrayList(taskslistFilter);

			tasksListView.setItems(tasksObsList);

			tasksListView.setCellFactory(projectEmployeeLv -> new RowTaskController(taskListPane, StackPopPup));

			System.out.println("***********test Rdio Done***********");

		});

		doneTasks.setOnAction(e -> {
			System.out.println("***********test Rdio Done***********");
			List<Task> taskslistFilter = new ArrayList<>();
			for (Task task : tasklistFinal) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(task.getEndDate());
				LocalDate endDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				// System.out.println(endDate);
				if (LocalDate.now().isAfter(endDate)) {
					taskslistFilter.add(task);
				}
			}

			tasksObsList = FXCollections.observableArrayList(taskslistFilter);

			tasksListView.setItems(tasksObsList);

			tasksListView.setCellFactory(projectEmployeeLv -> new RowTaskController(taskListPane, StackPopPup));

		});

		toDoTasks.setOnAction(e -> {
			System.out.println("***********test Rdio toDo***********");
			List<Task> taskslistFilter = new ArrayList<>();
			for (Task task : tasklistFinal) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(task.getStartDate());
				LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				if (LocalDate.now().isBefore(startDate)) {
					taskslistFilter.add(task);
				}
			}

			tasksObsList = FXCollections.observableArrayList(taskslistFilter);

			tasksListView.setItems(tasksObsList);

			tasksListView.setCellFactory(projectEmployeeLv -> new RowTaskController(taskListPane, StackPopPup));

		});

		doingTasks.setOnAction(e -> {
			System.out.println("***********test Rdio Doing***********");

			List<Task> taskslistFilter = new ArrayList<>();
			for (Task task : tasklistFinal) {
				Calendar calendar = new GregorianCalendar();
				Calendar calendar2 = new GregorianCalendar();
				calendar.setTime(task.getStartDate());
				calendar2.setTime(task.getEndDate());
				LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				LocalDate endDate = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH) + 1,
						calendar2.get(Calendar.DAY_OF_MONTH));
				if (LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate)) {
					taskslistFilter.add(task);
				}

			}
			tasksObsList = FXCollections.observableArrayList(taskslistFilter);

			tasksListView.setItems(tasksObsList);

			tasksListView.setCellFactory(projectEmployeeLv -> new RowTaskController(taskListPane, StackPopPup));

		});

	}

	// public void INITAAT(){
	// RowTaskController.listTasksEmployeeController=this;
	// this.initialize(url1, rb1);
	// }

	// public ListTasksEmployeeController() {
	// tasksListView.refresh();
	// }

	@FXML
	private void onActionAddTask(ActionEvent event) {

		AddTaskPopUpController.refreshPa = taskListPane;
		Pane child = null;

		try {
			child = FXMLLoader.load(getClass().getResource("/views/AddTaskPopUp.fxml"));
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

		dialog = new JFXDialog(StackPopPup, tasksLayout, JFXDialog.DialogTransition.BOTTOM);
		dialog.show();
	}

}
