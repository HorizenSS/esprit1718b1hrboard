/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class RowPTrainingController extends ListCell<Project> {

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane row;
    @FXML
    private Label startDateId;
    @FXML
    private Label EndDateId;
    @FXML
    private Label libelle_Project_Master_id;
    @FXML
    private Button teamEmployeeBtnId;

    @FXML
    private Button listSkilProjectBtnId;
    @FXML
    private Label libelle_Subject;
    @FXML
    private Label dateDepart_lbl;
    @FXML
    private Label dateDepart_lbl1;
    @FXML
    private Text  nom_project_id;
    private FXMLLoader mLLoader;
    public static Project selectedProject;
    public AnchorPane panerefresh;
    public StackPane a;
    public static JFXDialog dialogProject;
    
    public TrainingProjectController trp;
    
    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
	EmployeeServiceRemote employeesInProject;
    
    
    /**
     * Initializes the controller class.
     */
    public RowPTrainingController(StackPane pane, AnchorPane panerefr) {
    		a=pane;
    		panerefresh=panerefr;
 	}

	@Override
    protected void updateItem(Project project, boolean empty) {
        super.updateItem(project, empty);         
        
					 if (empty || project == null) {
				            setText(null);
				            setGraphic(null);
				        } else {
				            if (mLLoader == null) {
				                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowPTraining.fxml"));
				                mLLoader.setController(this);
				                try {
				                    mLLoader.load();
				                } catch (IOException e) {
				                    System.out.println("ma7abech iloadi ");
				                }

				            }
				            libelle_Project_Master_id.setText(project.getProjectMaster().getFirstName()+" "+project.getProjectMaster().getLastName());
				            nom_project_id.setText(project.getName());
			            
			            
			            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			            String startDate = sd.format(project.getStartDate());
			            startDateId.setText(startDate);
			            String endDate = sd.format(project.getEndDate());
			            EndDateId.setText(endDate);
			            teamEmployeeBtnId.setOnAction(ev->{
			            	selectedProject=project;
			            	System.out.println(project);
			            	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
			            	List<Employee> projecteam = proxyEmployee.findAllEmployeesByProject(project);
			            	trp.empl = projecteam;
			            	System.out.println(projecteam);
			            	for(Employee emp : projecteam ){
			            	//System.out.println(emp);
			            	}
			            	
			        		//ObservableList<Employee> ps = FXCollections.observableArrayList(proxyEmployee.findAllEmployeesByProject(project)));
			            });
		            
				            setText(null);
				            setGraphic(row);

				        }

	
    }

 
	@FXML
    private void OnActionTeamClicked(ActionEvent event) {
    }

    @FXML
    private void OnActionSkillClicked(ActionEvent event) {
    }
   
}
