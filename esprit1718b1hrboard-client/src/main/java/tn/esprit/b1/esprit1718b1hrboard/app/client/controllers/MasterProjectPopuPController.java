/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

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
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MasterProjectPopuPController implements Initializable {

	@FXML
	private AnchorPane skillsProjectPane;
	@FXML
	private JFXButton confirmMastreBtn;
	@FXML
	private JFXComboBox<String> masterCombo;

	private ObservableList<String> emps;

	private EmployeeServiceRemote proxyEmployee;
	private TaskServiceRemote proxyTask;
	private EmployeeHasSkillServiceRemote proxyEmpHasskil;
	private ProjectHasSkillServiceRemote proxyProjetHasSkil;

	private List<Employee> employees = new ArrayList<>();
	public static List<Employee> employeesInProject = new ArrayList<>();
	private Set<Employee> employeesWithSkills = new HashSet<>();
	
	private ProjectServiceRemote proxyProject;
	
	public static AnchorPane refreshPa;


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		masterCombo.getStyleClass().clear();
		confirmMastreBtn.getStyleClass().clear();
		populateComboMaster();

		// TODO
	}

	public void populateComboMaster() {

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";

		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		employeesInProject = proxyEmployee.findAllEmployeesByProject(ProjectRowController.selectedProject);
		employees = proxyEmployee.findAll();

		if (ProjectRowController.selectedProject.getProjectMaster() != null) {
			masterCombo.setPromptText(
					ProjectRowController.selectedProject.getProjectMaster().getFirstName().toUpperCase() + " "
							+ ProjectRowController.selectedProject.getProjectMaster().getLastName().toUpperCase());
		} else {
			masterCombo.setPromptText("SELETE A MASTER");
		}

		for (Employee employee : employees) {
			Float note = (float) 0;
			for (ProjectHasSkill projectHasSkill : ProjectRowController.selectedProject.getSkillsInProject()) {
				for (EmployeeHasSkill employeeHasSkill : employee.getSpecificationsSkills()) {
					if (projectHasSkill.getSkill() != null) {
						if (employeeHasSkill.getSkill().getId() == projectHasSkill.getSkill().getId()) {
							note += employeeHasSkill.getLevel() * (projectHasSkill.getLevel() / 10);
							employee.setLevelProject(note);
							// System.out.println("entred !!!!");
							employeesWithSkills.add(employee);
							// break;
						}
					}

				}
			}

		}

		for (Employee employee : employeesWithSkills) {
			System.out.println(employee);
		}
		emps = FXCollections.observableArrayList();
		for (Employee employee : employeesWithSkills) {
			if(employee.getFirstName()!=null && employee.getLastName()!=null){
				emps.add(employee.getFirstName().toUpperCase() + " " + employee.getLastName().toUpperCase());
			}
		}
		masterCombo.setItems(emps);

	}

	@FXML
	private void OnActionConfirmMaster(ActionEvent event) {
		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);
		Project projectUpdate = ProjectRowController.selectedProject;
		List<Employee> empMasterUp = new ArrayList<>();
		empMasterUp.addAll(employeesWithSkills);
		if (masterCombo.getSelectionModel().getSelectedIndex() != -1) {
			projectUpdate.setProjectMaster(empMasterUp.get(masterCombo.getSelectionModel().getSelectedIndex()));
			proxyProject.update(projectUpdate);
			
		}
		
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
