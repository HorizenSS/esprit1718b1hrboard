/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployeeProjectsController implements Initializable {

	@FXML
	private ToggleGroup projectFilter;
	@FXML
	private JFXListView<Project> projectEmployeeListView;

	public ObservableList<Project> projectsObsList;

	@FXML
	private StackPane employeeProjectsStack;

	ProjectServiceRemote proxyProject;
	@FXML
	private ImageView imgProfilPic;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblPost;

	Employee employee = EmployeesGeneralListController.employee;

	List<Project> projectlist = new ArrayList<>();
	List<Project> projectMasterlist = new ArrayList<>();
	Set<Project> AllProject = new HashSet<>();
	@FXML
	private JFXRadioButton doingRadio;
	@FXML
	private JFXRadioButton allRadio;
	@FXML
	private JFXRadioButton toDoRadio;
	@FXML
	private JFXRadioButton doneRadio;
    @FXML
    private AnchorPane PaneEmployeeProjects;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

//		doingRadio.getStyleClass().clear();
//		toDoRadio.getStyleClass().clear();
//		doneRadio.getStyleClass().clear();
//		allRadio.getStyleClass().clear();
		PaneEmployeeProjects.getStylesheets().clear();
		employeeProjectsStack.getStylesheets().clear();
		doingRadio.setSelected(false);
		toDoRadio.setSelected(false);
		doneRadio.setSelected(false);
		
		


		
		if (employee.getFirstName() != null) {
			lblFirstName.setText(employee.getFirstName());
		} else {
			lblFirstName.setText("*****");
		}
		if (employee.getLastName() != null) {
			lblLastName.setText(employee.getLastName());
		} else {
			lblLastName.setText("******");
		}
		if (employee.getPost() != null) {
			if (employee.getPost().getEntitled() != null) {
				lblPost.setText(employee.getPost().getEntitled());
			} else {
				lblPost.setText("*******");
			}
		} else {
			lblPost.setText("*******");
		}

		displayAllProjects();
		// TODO
	}

	public void displayAllProjects() {

		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";

		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

		projectlist = proxyProject.findAllProjectsByEmployee(employee);
		projectMasterlist = proxyProject.findProjectByMaster(employee);

		AllProject.addAll(projectlist);
		AllProject.addAll(projectMasterlist);

		projectsObsList = FXCollections.observableArrayList(AllProject);

		projectEmployeeListView.setItems(projectsObsList);

		projectEmployeeListView
				.setCellFactory(projectEmployeeLv -> new ProjectEmployeeRowController(employeeProjectsStack));

		allRadio.setOnAction(e -> {
			List<Project> projectlistFilter = new ArrayList<>();
			projectlistFilter.addAll(AllProject);
			projectsObsList = FXCollections.observableArrayList(projectlistFilter);

			projectEmployeeListView.setItems(projectsObsList);

			projectEmployeeListView
					.setCellFactory(projectEmployeeLv -> new ProjectEmployeeRowController(employeeProjectsStack));

			System.out.println("***********test Rdio Done***********");

		});

		doneRadio.setOnAction(e -> {
			System.out.println("***********test Rdio Done***********");
			List<Project> projectlistFilter = new ArrayList<>();
			for (Project project : AllProject) {
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

			projectEmployeeListView.setItems(projectsObsList);

			projectEmployeeListView
					.setCellFactory(projectEmployeeLv -> new ProjectEmployeeRowController(employeeProjectsStack));

		});

		toDoRadio.setOnAction(e -> {
			System.out.println("***********test Rdio toDo***********");
			List<Project> projectlistFilter = new ArrayList<>();
			for (Project project : AllProject) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(project.getStartDate());
				LocalDate startDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				if (LocalDate.now().isBefore(startDate)) {
					projectlistFilter.add(project);
				}
			}

			projectsObsList = FXCollections.observableArrayList(projectlistFilter);

			projectEmployeeListView.setItems(projectsObsList);

			projectEmployeeListView
					.setCellFactory(projectEmployeeLv -> new ProjectEmployeeRowController(employeeProjectsStack));

		});

		doingRadio.setOnAction(e -> {
			System.out.println("***********test Rdio Doing***********");

			List<Project> projectlistFilter = new ArrayList<>();
			for (Project project : AllProject) {
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

			projectEmployeeListView.setItems(projectsObsList);

			projectEmployeeListView
					.setCellFactory(projectEmployeeLv -> new ProjectEmployeeRowController(employeeProjectsStack));

		});

	}
}
