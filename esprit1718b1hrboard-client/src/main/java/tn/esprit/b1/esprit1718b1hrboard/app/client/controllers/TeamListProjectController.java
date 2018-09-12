/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TeamListProjectController implements Initializable {

	@FXML
	private AnchorPane employeesProjectPane;
	@FXML
	private JFXListView<Employee> employeesProjectListview;
	@FXML
	private JFXButton confirmEmployeesBtn;

	private List<Employee> employees = new ArrayList<>();
	public static List<Employee> employeesInProject = new ArrayList<>();
	private Set<Employee> employeesWithSkills = new HashSet<>();

	public ObservableList<Employee> employeesObsList;

	private EmployeeServiceRemote proxyEmployee;
	private TaskServiceRemote proxyTask;
	private EmployeeHasSkillServiceRemote proxyEmpHasskil;
	private ProjectHasSkillServiceRemote proxyProjetHasSkil;
	public static Set<Task> tasks = new HashSet<>();

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		confirmEmployeesBtn.getStyleClass().clear();
		displayAllEmployees();
	}

	public void displayAllEmployees() {

		// System.out.println("*********************List of skill in
		// project*********************** ");
		// System.out.println(ProjectRowController.selectedProject.getSkillsInProject());

		// skillsInProject =
		// ProjectRowController.selectedProject.getSkillsInProject();

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
		// String jndiProjectHasSkill =
		// "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote";

		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		employeesInProject = proxyEmployee.findAllEmployeesByProject(ProjectRowController.selectedProject);
		employees = proxyEmployee.findAll();

		for (Employee employee : employees) {
			for (EmployeeHasSkill employeeHasSkill : employee.getSpecificationsSkills()) {
				System.out.println(employeeHasSkill);
			}
		}

		System.out.println("skills in project !!!!!!!!!!");
		for (ProjectHasSkill projectHasSkill : ProjectRowController.selectedProject.getSkillsInProject()) {
			System.out.println(projectHasSkill);
		}
		for (Employee employee : employees) {
			Float note = (float) 0;
			for (ProjectHasSkill projectHasSkill : ProjectRowController.selectedProject.getSkillsInProject()) {
				for (EmployeeHasSkill employeeHasSkill : employee.getSpecificationsSkills()) {
					if (projectHasSkill.getSkill() != null) {
						if (employeeHasSkill.getSkill().getId() == projectHasSkill.getSkill().getId()) {
							note += employeeHasSkill.getSkillNote() * (projectHasSkill.getLevel() / 10);
							employee.setLevelProject(note);
							// System.out.println("entred !!!!");
							if (ProjectRowController.selectedProject.getProjectMaster() != null
									&& ProjectRowController.selectedProject.getProjectMaster().getId() == employee
											.getId()) {
								System.out.println("it's a master !!");
							} else {
								employeesWithSkills.add(employee);

							}
							// break;
						}
					}

				}
			}

		}

		// for (Employee employee : employeesWithSkills) {
		// System.out.println(employee);
		// System.out.println(employee.getLevelProject());
		//
		// }
		//
		// System.out.println("!!!!!!!!!!!! Les employees dans ce projet
		// !!!!!!!!!");
		// for (Employee employee : employeesInProject) {
		// System.out.println(esmployee);
		// }

		System.out.println("!!!!!!!!!!!!!tasks in this projec !!!!!!!!!!!");
		for (Task task : ProjectRowController.selectedProject.getTasks()) {
			System.out.println(task);
		}
		tasks = ProjectRowController.selectedProject.getTasks();

		employeesObsList = FXCollections.observableArrayList(employeesWithSkills);
		employeesProjectListview.setItems(employeesObsList);

		employeesProjectListview.setCellFactory(skillEmployeeLv -> new TeamProjectRowController());
	}

	@FXML
	private void OnActionConfirmEmployees(ActionEvent event) {

//		System.out.println("BEFORE UPDATE");
//		for (Task task : tasks) {
//			System.out.println(task);
//		}
		
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
