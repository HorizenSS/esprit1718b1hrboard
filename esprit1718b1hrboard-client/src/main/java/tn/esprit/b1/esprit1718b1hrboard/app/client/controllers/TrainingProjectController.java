/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class TrainingProjectController implements Initializable {

	@FXML
	private AnchorPane PTrainingPaneId;
	@FXML
	private Label Label;
	@FXML
	private ListView<Project> ProjectListId;
	@FXML
	private ListView<Employee> ProjectTeamId;
	@FXML
	private ListView<ProjectHasSkill> ProjectSkillId;
	@FXML
	private StackPane project_Stackpane;
	ProjectServiceRemote proxyProject;
	String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
	ProjectHasSkillServiceRemote proxyProjectHasSkill;
	String jndiProjectHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote";
	EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	
	public static List<Employee> empl;
	public static Employee empSelected;
	@FXML
	private Label Label1;
	@FXML
	private Label Label2;
	public static Set<ProjectHasSkill> projectSkill;
	public static Project projectSelected;


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);
			System.out.println("aaaaaa");
			ObservableList<Project> ps = FXCollections.observableArrayList(proxyProject.findAll());
			System.out.println("bbbb");
			ProjectListId.setItems(ps);
			System.out.println("cccc");
			ProjectListId.setCellFactory(ProjectListView -> new RowPTrainingController(project_Stackpane, PTrainingPaneId));
			ProjectListId.getSelectionModel().selectedItemProperty().addListener((v,ov,nv) -> {
		  		  if(nv != null){
		  			  FillTeamsList(nv);
		  			  FillSkillList(nv);
		  		  }
		  	  });
			
		}

		catch (Exception e) {
			System.out.println("erreur getall training ou affichage des celulles de la liste");
		}
	}

	public void FillTeamsList(Project p) {
		if(p != null){
			projectSelected=p;
			proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
        	List<Employee> projecteam = proxyEmployee.findAllEmployeesByProject(p);
        	ObservableList<Employee> ptj = FXCollections.observableArrayList(projecteam);
        	ProjectTeamId.setItems(ptj);
        	ProjectTeamId.setCellFactory(EmployeeListView -> new RowEmployeeHasProjectController(project_Stackpane, PTrainingPaneId));
		}
	}
	public void FillSkillList(Project p) {
		if(p != null){		
			 projectSkill= p.getSkillsInProject(); 
        	Set<ProjectHasSkill> skills = new HashSet<>();
        	for(ProjectHasSkill prhs : projectSkill){
        		skills.add(prhs);
        		}
        	ObservableList<ProjectHasSkill> psj = FXCollections.observableArrayList(skills);
        	ProjectSkillId.setItems(psj);
        	ProjectSkillId.setCellFactory(SkillListView -> new RowSkillHasProjectController(project_Stackpane, PTrainingPaneId));
		}
	}
	
}
