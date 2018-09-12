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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class AddNewTrainingController implements Initializable {

    @FXML
    private AnchorPane addNTraining;
    @FXML
    private JFXTextField placeId;
    @FXML
    private JFXTextField typeId;
    @FXML
    private JFXButton AddBtnId;
    @FXML
    private JFXTextArea SubjectId;
    @FXML
    private JFXComboBox<String> trainerId;
    @FXML
    private JFXDatePicker startDateId;
    @FXML
    private JFXDatePicker endDateId;
    @FXML
    private Pane pane_scene;
    private ObservableList<String> employees;
    private ObservableList<String> skills;
   @FXML
    private JFXComboBox<String> nameId;
    public static JFXDialog dialogSkill;
    public static TrainingNormalController tn;
    @FXML
    private StackPane Stack_scene;
    @FXML
    private Label labelNewTraining;
   
    TrainingServiceRemote proxyTraining;
    String jndiTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TrainingService!tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote";
  
    EmployeeServiceRemote proxyEmployees;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    SkillServiceRemote proxySkill;
    String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";
    
   
   
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	proxySkill = (SkillServiceRemote)EJBLookupUtil.doLookup(jndiSkill);
    	List<Skill> skillsList = new ArrayList<>();
    	skillsList=proxySkill.findAll();
    	skills = FXCollections.observableArrayList();
    	for(Skill SKILL : skillsList){
    		skills.add( SKILL.getName());
		}
    	nameId.setItems((ObservableList<String>) skills);
    	nameId.setOnAction(e->{
    		proxyEmployees = (EmployeeServiceRemote)EJBLookupUtil.doLookup(jndiEmployee);
        	List<Employee> employeeList = new ArrayList<>();
        	employeeList = proxyEmployees.EmployeeBySkill(nameId.getSelectionModel().getSelectedItem());
        	
        	employees = FXCollections.observableArrayList();
    		for(Employee EMP : employeeList){
    			employees.add( EMP.getFirstName()+" "+EMP.getLastName());    			
    		}
    		
    		trainerId.setItems((ObservableList<String>) employees);
    		
    	});
    
    }    

	@FXML
    private void OnAddNewTrainingClicked(ActionEvent event) {
    	proxyTraining = (TrainingServiceRemote)EJBLookupUtil.doLookup(jndiTraining);
    	proxyEmployees = (EmployeeServiceRemote)EJBLookupUtil.doLookup(jndiEmployee);
    	System.out.println(trainerId.getValue());
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
        	proxyTraining.save(training);
        	tn.INITAAT();
        	tn.finish();
        	
    	}else{
    		WarningPaneController.msg="Verify your dates !";
    		Pane child = null;
			try {
				child = FXMLLoader.load(getClass().getResource("/views/MessageError.fxml"));
			} catch (IOException ex) {
			}
    		JFXDialogLayout dialogLayout = new JFXDialogLayout();
			dialogLayout.getChildren().add(child);
			dialogSkill = new JFXDialog(Stack_scene, dialogLayout, JFXDialog.DialogTransition.CENTER);
			dialogSkill.show();
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
	    		dialogSkill.close();
			}};
			th.start();
    		System.out.println("verifier les dates");
    	}
    	
    }
    
}
