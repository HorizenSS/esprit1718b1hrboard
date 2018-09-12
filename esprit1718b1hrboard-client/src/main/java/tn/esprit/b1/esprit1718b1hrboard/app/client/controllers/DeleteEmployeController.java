/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;


import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class DeleteEmployeController implements Initializable {

    @FXML
    private ListView<Employee> EmployeeToDelete;
    private ObservableList<Employee> employee;

    @FXML
    private JFXTextField SeachEmployee2;
    @FXML
    private ListView<Employee> EmployeeWhoGonnaBeDelete;
    private ObservableList<Employee> employee2;
    public URL url1; 
    public ResourceBundle rb1;
    @FXML
    private JFXTextField seachemploee1;
    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    @FXML
    private Button afficher;
    @FXML
    private ImageView img;
    List<Employee> emp ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Diplay();
  url1 = url;
  rb1 = rb ;
 
  
    	    }    

    @FXML
    private void afficheremployertodeletethismounth(ActionEvent event) {
    	
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
    	  employee2 = FXCollections.observableArrayList();
    	  
    	 System.out.println(proxyEmployee.EmployeeWhoWillBeDelete());
    	  for (Employee ee : proxyEmployee.EmployeeWhoWillBeDelete() )
    	  { 	  

    		  employee2.add(ee);
    	  }
    	  
    	  System.out.println(employee2);
    	  EmployeeWhoGonnaBeDelete.setItems(employee2);
    	  EmployeeWhoGonnaBeDelete.setCellFactory(EmployeeList2-> new  RowEmployeeCheckFXMLController());
    	  
    }

    @FXML
    private void searchwithname1(KeyEvent event) {
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Employee> le = proxyEmployee.EmployeeWithAccountToDelete(null);
		if (seachemploee1.getText().equals("")) {
			ObservableList<Employee> res = FXCollections.observableArrayList(le);
			EmployeeToDelete.setItems(res);
			EmployeeToDelete.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		} else {
			List<Employee> ef = le.stream()
					.filter(emp ->emp.getLastName().toLowerCase().contains(seachemploee1.getText().toLowerCase()))
					.collect(Collectors.toList()) ;
			List<Employee> fr = new ArrayList<>();
			for (Employee ep : ef) {
				fr.add(ep);
			}
			ObservableList<Employee> rs = FXCollections.observableArrayList(fr);
			EmployeeToDelete.setItems(rs);
			EmployeeToDelete.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		}
    }

    @FXML
    private void findwithname2(KeyEvent event) {

    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Employee> le = proxyEmployee.EmployeeWhoWillBeDelete();
		if (SeachEmployee2.getText().equals("")) {
			ObservableList<Employee> res = FXCollections.observableArrayList(le);
			EmployeeWhoGonnaBeDelete.setItems(res);
			EmployeeWhoGonnaBeDelete.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		} else {
			List<Employee> ef = le.stream()
					.filter(emp ->emp.getLastName().toLowerCase().contains(SeachEmployee2.getText().toLowerCase()))
					.collect(Collectors.toList()) ;
			List<Employee> fr = new ArrayList<>();
			for (Employee ep : ef) {
				fr.add(ep);
			}
			ObservableList<Employee> rs = FXCollections.observableArrayList(fr);
			EmployeeWhoGonnaBeDelete.setItems(rs);
			EmployeeWhoGonnaBeDelete.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		}
    }
    
    
    public void Diplay2() {
		employee = FXCollections.observableArrayList();

    	 for (Employee ee :emp)
    	  { 	  

    	  	  employee.add(ee);
    	  }
    	  System.out.println(employee);
    	  EmployeeWhoGonnaBeDelete.refresh();
    	  	EmployeeWhoGonnaBeDelete.setItems(employee);
    	  	EmployeeWhoGonnaBeDelete.setCellFactory(EmployeeList3->   new  RowEmployeeCheckFXMLController());
    	  	EmployeeWhoGonnaBeDelete.refresh();
    	    }    
    
    
    
    public void Diplay() {
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
 
    //System.out.println(es.getAll().toString());
  employee = FXCollections.observableArrayList();
  System.out.println(proxyEmployee.EmployeeWithAccountToDelete(null));
  for (Employee e : proxyEmployee.EmployeeWithAccountToDelete(null) )
  { 	  
      if(e.getAccount()!=null)
      {
	  employee.add(e);}
  }
  
  System.out.println(employee);
  EmployeeToDelete.setItems(employee);
  EmployeeToDelete.setCellFactory(EmployeeList1-> new  RowEmployeeDeleteFXMLController());

 
  
    	    }    
}
