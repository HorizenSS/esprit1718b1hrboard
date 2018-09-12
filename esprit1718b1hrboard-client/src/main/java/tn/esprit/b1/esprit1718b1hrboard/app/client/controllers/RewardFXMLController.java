/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.FxUtilTest;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RewardFXMLController implements Initializable {

    @FXML
    private ListView<Employee> employeeranking;
    private ObservableList<Employee> employee;

    @FXML
    private ComboBox<String> trierparnote;
    @FXML
    private Button SelectEmployeeBest;
    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    private ObservableList<String> com;
    private ObservableList<String> com1;
    public  Employee empl;
    public static URL url1; 
    public static  ResourceBundle rb1;
    public static String  combo;
    @FXML
    private ComboBox<String> employeecombo;
    @FXML
    private Button promotion;
    private FXMLLoader mLLoader;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 com	= FXCollections.observableArrayList();
    	 com1	= FXCollections.observableArrayList();


     	com.add("Mounth Ranking");
     	com.add("Year Ranking");
     	
     	trierparnote.setItems((ObservableList<String>) com);

    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
    	  employee = FXCollections.observableArrayList();
    	  Long millis = System.currentTimeMillis();
	       Date date = new Date(millis);
	       System.out.println(date);
    	  for (Employee ee : proxyEmployee.findAll())
    	  { 	  

    		  employee.add(ee);
    		  com1.add(ee.getLastName());
    	  }
    	  employeecombo.setItems((ObservableList<String>) com1);

    	  
    	  System.out.println(employee);
    	  employeeranking.setItems(employee);
    	  employeeranking.setCellFactory(EmployeeList2-> new  RowEmployeeRewardkFXMLController());
    	  url1 = url;
    		rb1 = rb ;
employeecombo.setEditable(true);    		
FxUtilTest.autoCompleteComboBoxPlus(employeecombo, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()));

    }    


    @FXML
    private void SelectTheBestEmployee(ActionEvent event) {
		List<Employee> empl = new ArrayList<Employee>();	
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
System.out.println(trierparnote.getValue());
    	if (trierparnote.getValue().equals("Mounth Ranking"))
    		
    	{  INITAAT();
    		combo = "Mounth Ranking";
    		empl= null;
    		empl=proxyEmployee.BestEmployeeMounth();
    		System.out.println(empl);
    	}
    	else if (trierparnote.getValue().equals("Year Ranking"))
    	{ INITAAT();
    		combo="Year Ranking";
    	empl= null;
    		empl=proxyEmployee.BestEmployeeYear();
    		System.out.println(empl);
    	}
    	else if (trierparnote.getValue().trim().isEmpty())
    	{Alert alert = new Alert(AlertType.WARNING);

     	alert.setTitle("Please");
     	alert.setHeaderText("Rank the Employee");
     	alert.setContentText("RETURN");
     	alert.showAndWait().ifPresent(response -> {
     		if (response == ButtonType.OK) {
        
      }});}
  	  employee = FXCollections.observableArrayList();
  	  for (Employee ee :empl)
	  { 	  

		  employee.add(ee);
	  }
	  
	  System.out.println(employee);
	  employeeranking.setItems(employee);
	  employeeranking.setCellFactory(EmployeeList2-> new  RowEmployeeRewardkFXMLController());

    }
    public void INITAAT(){
     	this.initialize(url1, rb1);
     }

    @FXML
    private void Promote(ActionEvent event) throws IOException {
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
        empl= proxyEmployee.findbyname(employeecombo.getValue());
        
        System.out.println(empl);
        if (empl.getPost()!=null)
        {   FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/views/PrmoteEmployee.fxml"));
        PrmoteEmployeeController test = fXMLLoader.getController();
        System.out.println(empl);
        test.emp=empl;
        System.out.println(empl);
        Parent root = (Parent) fXMLLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();}
        else {Alert alert = new Alert(AlertType.WARNING);

     	alert.setTitle("ERROR");
     	alert.setHeaderText("Employe does not have a post");
     	alert.setContentText("RETURN");
     	alert.showAndWait().ifPresent(response -> {
     		if (response == ButtonType.OK) {
        
      }});}
    }

    
}
