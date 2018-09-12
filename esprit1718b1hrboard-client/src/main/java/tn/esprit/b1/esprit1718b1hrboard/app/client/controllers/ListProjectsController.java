package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListProjectsController implements Initializable {

	@FXML
	private AnchorPane refreshPane;
	@FXML
	private JFXRadioButton doingRadio;
	@FXML
	private JFXRadioButton allRadio;
	@FXML
	private JFXRadioButton toDoRadio;
	@FXML
	private JFXRadioButton doneRadio;
	@FXML
	private StackPane popUpStack;
	@FXML
	private JFXListView<Project> projectListView;
	@FXML
	private JFXButton newProjectBtn;

	public ObservableList<Project> projectsObsList;

	ProjectServiceRemote proxyProject;

	public static JFXDialog dialog;

	List<Project> projectlist = new ArrayList<>();
	@FXML
	private ToggleGroup selectFilter;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		newProjectBtn.getStyleClass().clear();
		displayAllProjects();

	}

	public void displayAllProjects() {

		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";

		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

		projectlist = proxyProject.findAll();
		projectsObsList = FXCollections.observableArrayList(projectlist);
		projectListView.setItems(projectsObsList);

		projectListView.setCellFactory(projectEmployeeLv -> new ProjectRowController(popUpStack, refreshPane));

		allRadio.setOnAction(e -> {
			List<Project> projectlistFilter = new ArrayList<>();
			projectlistFilter.addAll(projectlist);
			projectsObsList = FXCollections.observableArrayList(projectlistFilter);

			projectListView.setItems(projectsObsList);

			projectListView.setCellFactory(projectEmployeeLv -> new ProjectRowController(popUpStack, refreshPane));

			System.out.println("***********test Rdio Done***********");

		});

		doneRadio.setOnAction(e -> {
			System.out.println("***********test Rdio Done***********");
			List<Project> projectlistFilter = new ArrayList<>();
			for (Project project : projectlist) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(project.getEndDate());
				LocalDate endDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				// System.out.println(endDate);
				if (LocalDate.now().isAfter(endDate)) {
					projectlistFilter.add(project);
				}
			}

			projectsObsList = FXCollections.observableArrayList(projectlistFilter);

			projectListView.setItems(projectsObsList);

			projectListView.setCellFactory(projectEmployeeLv -> new ProjectRowController(popUpStack, refreshPane));

		});

		toDoRadio.setOnAction(e -> {
			System.out.println("***********test Rdio toDo***********");
			List<Project> projectlistFilter = new ArrayList<>();
			for (Project project : projectlist) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(project.getStartDate());
				LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				if (LocalDate.now().isBefore(startDate)) {
					projectlistFilter.add(project);
				}
			}

			projectsObsList = FXCollections.observableArrayList(projectlistFilter);

			projectListView.setItems(projectsObsList);

			projectListView.setCellFactory(projectEmployeeLv -> new ProjectRowController(popUpStack, refreshPane));

		});

		doingRadio.setOnAction(e -> {
			System.out.println("***********test Rdio Doing***********");

			List<Project> projectlistFilter = new ArrayList<>();
			for (Project project : projectlist) {
				Calendar calendar = new GregorianCalendar();
				Calendar calendar2 = new GregorianCalendar();
				calendar.setTime(project.getStartDate());
				calendar2.setTime(project.getEndDate());
				LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				LocalDate endDate = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH) + 1,
						calendar2.get(Calendar.DAY_OF_MONTH));
				if (LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate)) {
					projectlistFilter.add(project);
				}

			}
			projectsObsList = FXCollections.observableArrayList(projectlistFilter);

			projectListView.setItems(projectsObsList);

			projectListView.setCellFactory(projectEmployeeLv -> new ProjectRowController(popUpStack, refreshPane));

		});
	}

	@FXML
	private void OnActionProjectAdd(ActionEvent event) {
		System.out.println("test adding new project!!");
		AddUpdateprojectPopUpController.refreshPa = refreshPane;
		Pane child = null;

		try {
			child = FXMLLoader.load(getClass().getResource("/views/AddUpdateprojectPopUp.fxml"));
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

		dialog = new JFXDialog(popUpStack, absLayout, JFXDialog.DialogTransition.CENTER);
		dialog.show();
	}

}
