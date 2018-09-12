/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.EmployeeTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.PostsTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.ProjectTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Reward;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.RewardServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

import org.controlsfx.control.CheckComboBox;
import javafx.beans.property.SimpleStringProperty;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class GiveAwardToEmployee2FXMLController implements Initializable {

    @FXML
    private Button SendAwardt;
    @FXML
    private JFXTextArea EncouragementEmployee;
    @FXML
    private ComboBox<String> AwardsEmployee;
    
    @FXML
    private TableView<ProjectTable> EmpoyeeProject;
    @FXML
    private TableColumn<ProjectTable, String> columnProject;
    @FXML
    private TableColumn<ProjectTable, String> columnedebuteDate;
    @FXML
    private TableColumn<ProjectTable, String> colomneEndDate;
    @FXML
    private JFXDatePicker dateDepart;
    @FXML
    private JFXDatePicker DateArriver;
    @FXML
    private Text x;
    private ObservableList<String> ac;
	private ObservableList<ProjectTable> list_project;
	TaskServiceRemote proxyEmployee;
	RewardServiceRemote proxyp;

    @FXML
    private Label error;
    private ObservableList<String> com1;

    public String combo;
    public	static Employee employestatic;
    @FXML
    private JFXTextField tripdestination;
    public static URL url1; 
    public static  ResourceBundle rb1;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	error.setText(null);
    	  url1 = url;
    		rb1 = rb ;
    	 FXMLLoader fXMLLoader1 = new FXMLLoader(getClass().getResource("/views/RewardFXML.fxml"));
         RewardFXMLController test = fXMLLoader1.getController();
    	combo=test.combo;
    	 com1	= FXCollections.observableArrayList();
    	

    	EmpoyeeProject.setVisible(false);
    	dateDepart.setVisible(false);
    	DateArriver.setVisible(false);
    	tripdestination.setVisible(false);
    	 com1.add("gift card");
    	 	
    	ac	= FXCollections.observableArrayList();
    	ac.add("car");
    	ac.add("trip");
    
    	if(combo.equals("Mounth Ranking"))
    	{	AwardsEmployee.setItems((ObservableList<String>) com1);}
    	if(combo.equals("Year Ranking"))
    	{	AwardsEmployee.setItems((ObservableList<String>) ac);
    	}

    	populateEmployeeTableView();
    	EmpoyeeProject.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends ProjectTable> observable, ProjectTable oldValue,
						ProjectTable newValue) -> {
					if (newValue == null) {
						return;
					}
					try {
						error.setText("error");
					} catch (Exception e) {
					}
				});
    	AwardsEmployee.getSelectionModel().selectedItemProperty().addListener((v,ov,nv) -> {
  		  if(nv != null){
  			 if (nv.equals("trip"))
  	    	 {	
  				 EmpoyeeProject.setVisible(true);
  	     	dateDepart.setVisible(true);
  	     	DateArriver.setVisible(true);
  	    	tripdestination.setVisible(true);}
  			 else 
 	    	 {	INITAAT();
  				}
  			 
  	    			  }
	  });
    //	EmpoyeeProject.getSelectionModel().selectedItemProperty()
		//.addListener((ObservableValue<? extends ProjectTable> observable, ProjectTable oldValue,
		//		ProjectTable newValue) -> {
		//	if (newValue == null) {
			//	return;
		//	}
			// System.out.println(newValue.getId());

		//});
    	}    
    
    
    
    
    private void populateEmployeeTableView() {
    	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";

		proxyEmployee = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Task> projectlist = new ArrayList<>();
        projectlist = proxyEmployee.findTasksByEmployee(employestatic);
		// System.out.println(employeeList);
    	Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		list_project = FXCollections.observableArrayList();
		for (Task emp : projectlist) {
if(date.before(emp.getEndDate()))
{
 SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
 ProjectTable pt = new ProjectTable(emp.getProject().getName(),sd.format(emp.getStartDate()),sd.format(emp.getEndDate()));
			list_project.add(pt);}
		}
		columnProject.setCellValueFactory(new PropertyValueFactory<>("pojects"));
		columnedebuteDate.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
	colomneEndDate.setCellValueFactory(new PropertyValueFactory<>("datefin"));
		
	EmpoyeeProject.getItems().clear();
	EmpoyeeProject.getItems().addAll(list_project);	
	}
//    private void choisirAward(KeyEvent event) {
//    	 if (AwardsEmployee.getValue().equals("Voyage"))
//    	 {	EmpoyeeProject.setVisible(true);
//     	dateDepart.setVisible(true);
//     	DateArriver.setVisible(true);
//    	tripdestination.setVisible(true);}
//    	else
//    	{INITAAT();}

    	 
   // }
     @FXML
    private void AddAccount(ActionEvent event) {
    	 int x = 0 ;
         Long millis2 = System.currentTimeMillis();
	       Date datejj = new Date(millis2);

       	
     	 if (AwardsEmployee.getValue().equals("trip"))
    	 {	String jndip= "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/RewardService!tn.esprit.b1.esprit1718b1hrboard.services.RewardServiceRemote";

    		proxyp = (RewardServiceRemote) EJBLookupUtil.doLookup(jndip);
    		List<Task> projectlist = new ArrayList<>();
            projectlist = proxyEmployee.findTasksByEmployee(employestatic);
            Long millis = System.currentTimeMillis();
 	       Date datej = new Date(millis);
 	      SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
 	     Date date = java.sql.Date.valueOf(dateDepart.getValue());
 	     Date date1 = java.sql.Date.valueOf(DateArriver.getValue());
 	     System.out.println(projectlist);
 	       if((projectlist.isEmpty()))
 	       { if (date.compareTo(date1)<=0){
 	    	  Reward r = new Reward(null, EncouragementEmployee.getText(), datej, date, null, tripdestination.getText(), null);
 	    	    proxyp.save(r);
 	    	 	Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 	    		principalStage.hide();
 	    		 	       }
 	       else {
 	    	   Alert alert = new Alert(AlertType.WARNING);

    	 	alert.setTitle("ERROR DATE");
    	 	alert.setHeaderText("CHANGE DATE");
    	 	alert.setContentText("RETURN");
    	 	alert.showAndWait().ifPresent(response -> {
    	 		if (response == ButtonType.OK) {
    	     error.setText("change the dates ");
    	 	 dateDepart.setValue(null);
    	 	DateArriver.setValue(null);
    	  }});}
}
    		for (Task emp : projectlist) {
   
     System.out.println(date);
     System.out.println(date1);
     System.out.println(date);
     if (((emp.getEndDate().getDate() - date.getDate()>=0)&&(emp.getEndDate().getDate() - date1.getDate()<=0))|| ((emp.getStartDate().getDate() - date.getDate()<=0)&&(emp.getEndDate().getDate() - date1.getDate()>=0)))
     { 			
    	 x++;
     }	 
    		}
    		if(x!=0){
    			Alert alert = new Alert(AlertType.WARNING);

    		 	alert.setTitle("ERROR DATE");
    		 	alert.setHeaderText("CHANGE DATE");
    		 	alert.setContentText("RETURN");
    		 	alert.showAndWait().ifPresent(response -> {
    		 		if (response == ButtonType.OK) {
    		     error.setText("change the dates ");
    		 	 dateDepart.setValue(null);
    		 	DateArriver.setValue(null);
    		 	INITAAT();
    		  }});
    		}else{
    	    Reward r = new Reward(null, EncouragementEmployee.getText(), datej, date, null, tripdestination.getText(), null);
    	    proxyp.save(r);
            System.out.println("xxxx");
         	Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		principalStage.hide();
    	     }
      
    	 }else 
    	 {
       		 Reward rr = new Reward(null, "Good Job", datejj, null, null, null, null);
       		 System.out.println(rr);
     	     proxyp.save(rr);
             System.out.println("xxxx");
          	Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     		principalStage.hide();
       	 }
            
    }
     public void INITAAT(){
     	this.initialize(url1, rb1);
     }

    
}
