/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTraining;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasTrainingPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;
import javafx.scene.control.ListCell;
/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class AddTrainingProjectWithListEmployeeController extends ListCell<Employee>implements Initializable {

    @FXML
    private StackPane Stack_scene;
    @FXML
    private AnchorPane addNTraining;
    @FXML
    private Pane pane_scene;
    @FXML
    private JFXComboBox<String> nameId;
    @FXML
    private Label nameProjectId;
    @FXML
    private JFXListView<Employee> listEmployeeId;
    @FXML
    private JFXButton createBtnId;
    @FXML
    private JFXTextField placeId;
    @FXML
    private JFXTextField typeId;
    @FXML
    private JFXTextArea SubjectId;
    @FXML
    private JFXComboBox<String> trainerId;
    @FXML
    private JFXDatePicker startDateId;
    @FXML
    private JFXDatePicker endDateId;

    EmployeeServiceRemote proxyEmployees;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    TrainingServiceRemote proxyTraining;
    String jndiTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TrainingService!tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote";
    EmployeeHasTrainingServiceRemote proxyEmployeeHasTraining;
	String jndiEmployeeHasTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasTrainingService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceRemote";

    private ObservableList<String> employee;
    public static JFXDialog dialogTraining;
    List<Employee> employees;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    employees = RowEmployeeHasProjectController.employeeslected;
//    for(Employee empppp : employees){
//    System.out.println(empppp.getFirstName());
//    }
    ObservableList<Employee> emp = FXCollections.observableArrayList(employees);
    listEmployeeId.setItems(emp);
    listEmployeeId.setCellFactory(EmployeeListView -> new RowEmpHasProjController(Stack_scene,addNTraining));
    List<String> skills = new ArrayList<>();
    Set<ProjectHasSkill> prhs = TrainingProjectController.projectSelected.getSkillsInProject();
    nameProjectId.setText(TrainingProjectController.projectSelected.getName());
    System.out.println("##################################################");
	System.out.println(TrainingProjectController.projectSelected.getSkillsInProject());
	
	for (ProjectHasSkill projectHasSkill : prhs) {
		skills.add(projectHasSkill.getSkill().getName());
		}
	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	 System.out.println(skills);
	 ObservableList<String> ss = FXCollections.observableArrayList(skills);
	 nameId.setItems(ss);
	 nameId.setOnAction(e->{
 		proxyEmployees = (EmployeeServiceRemote)EJBLookupUtil.doLookup(jndiEmployee);
     	List<Employee> employeeList = new ArrayList<>();
     	employeeList = proxyEmployees.EmployeeBySkill(nameId.getSelectionModel().getSelectedItem());
     	
     	employee = FXCollections.observableArrayList();
 		for(Employee EMP : employeeList){
 			employee.add( EMP.getFirstName()+" "+EMP.getLastName());    			
 		}
 		trainerId.setItems(employee);
 		
 	});
    }
    @FXML
    private void OnCreateTrainingClicked(ActionEvent event) {
    	proxyTraining = (TrainingServiceRemote)EJBLookupUtil.doLookup(jndiTraining);
    	proxyEmployees = (EmployeeServiceRemote)EJBLookupUtil.doLookup(jndiEmployee);
    	//System.out.println(trainerId.getValue());
    	String nomPrenom = trainerId.getValue();
    	String[] parts = nomPrenom.split(" ");
    	String firstName = parts[0]; 
    	String lastName = parts[1];
    	Employee emp;
    	emp = proxyEmployees.findbynameComplet(firstName,lastName);
    	Date startdate = java.sql.Date.valueOf(startDateId.getValue());
    	Date enddate = java.sql.Date.valueOf(endDateId.getValue());
    	int comparison = enddate.compareTo(startdate);
    	if(comparison >= 0){
    		Training training = new Training(placeId.getText(),nameId.getValue(), typeId.getText(), SubjectId.getText(),startdate, enddate,emp);
        	int tid = proxyTraining.addTraining(training);
        	////////////////////////////////
        	
        	
        	for (Employee ep : employees) {
    			EmployeeHasTrainingPk emppk1 = new EmployeeHasTrainingPk();
    			emppk1.setIdEmployee(ep.getId());
    			emppk1.setIdTraining(tid);
    			EmployeeHasTraining emphasT = new EmployeeHasTraining();
    			emphasT.setEmployeeHasTrainingPk(emppk1);
    			proxyEmployeeHasTraining = (EmployeeHasTrainingServiceRemote) EJBLookupUtil.doLookup(jndiEmployeeHasTraining);
    			proxyEmployeeHasTraining.save(emphasT);
        	}
        	
        	
        	///////////////////////////////
        	System.out.println(tid);
        	
        	}else{
        		WarningPaneController.msg="Verify your dates !";
        		Pane child = null;
    			try {
    				child = FXMLLoader.load(getClass().getResource("/views/MessageError.fxml"));
    			} catch (IOException ex) {
    			}
        		JFXDialogLayout dialogLayout = new JFXDialogLayout();
    			dialogLayout.getChildren().add(child);
    			dialogTraining = new JFXDialog(Stack_scene, dialogLayout, JFXDialog.DialogTransition.CENTER);
    			dialogTraining.show();
    			Thread th = new Thread(){
    				public void run(){

    					int count = 0;
    					boolean x = true;
    					try
    		        {
    					while(x == true)
    		            {count++;
    		                Thread.sleep(1000);
    		                if(count == 2)
    		                	x=false;
    		            }
    		        }
    		        catch(InterruptedException e)
    		        {   
    		        }
    					dialogTraining.close();
    			}};
    			th.start();
        		System.out.println("verifier les dates");
        		
        	}
    	RowEmployeeHasProjectController.dialogEmployee.close();
    }
    
    
    
}
