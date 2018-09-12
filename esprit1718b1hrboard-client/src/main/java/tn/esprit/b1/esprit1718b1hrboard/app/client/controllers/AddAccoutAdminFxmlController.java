
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ResignationServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class AddAccoutAdminFxmlController implements Initializable {

	@FXML
	private ListView<Employee> EmployeeWithoutAccount;
	private ObservableList<Employee> employee;

	private TextField SearchEmployeeWithoutAccount;
	EmployeeServiceRemote proxyEmployee;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
	@FXML
	private AnchorPane listviewpane;

	public static URL url1;
	public static ResourceBundle rb1;
	@FXML
	private CustomTextField searchemployee;
	@FXML
	private JFXButton search;
	List<Employee> emp = new ArrayList<Employee>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Displayall();
		url1 = url;
		rb1 = rb;
		employee = null;

	}

	public void INITAAT() {
		this.initialize(url1, rb1);
	}

	@FXML
	private void searchemployeename(ActionEvent event) {
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Employee> le = proxyEmployee.findAll();
		if (searchemployee.getText().equals("")) {
			ObservableList<Employee> res = FXCollections.observableArrayList(le);
			EmployeeWithoutAccount.setItems(res);
			EmployeeWithoutAccount.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		} else {
			List<Employee> ef = le.stream()
					.filter(emp ->emp.getLastName().toLowerCase().contains(searchemployee.getText().toLowerCase()))
					.collect(Collectors.toList()) ;
			List<Employee> fr = new ArrayList<>();
			for (Employee ep : ef) {
				fr.add(ep);
			}
			ObservableList<Employee> rs = FXCollections.observableArrayList(fr);
			EmployeeWithoutAccount.setItems(rs);
			EmployeeWithoutAccount.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		}
	}

	
	public void Displayall() {
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);

		// System.out.println(es.getAll().toString());
		employee = FXCollections.observableArrayList();

		for (Employee e : proxyEmployee.EmployeeWithoutAccount(null, null)) {

			employee.add(e);
		}
		System.out.println(employee);
	
		EmployeeWithoutAccount.setItems(employee);
		EmployeeWithoutAccount.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());

		

	}
	public void Displayall2() {
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);

		Employee p = proxyEmployee.find(1);
		System.out.println(p.getEmail());
		// System.out.println(es.getAll().toString());
		employee = FXCollections.observableArrayList();

		for (Employee e :emp) {
        
			employee.add(e);
		}
	
		
		System.out.println(employee);
		EmployeeWithoutAccount.setItems(employee);
		EmployeeWithoutAccount.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());

		

	}

    @FXML
    private void cherchinstantane(KeyEvent event) {
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Employee> le = proxyEmployee.findAll();
		if (searchemployee.getText().equals("")) {
			ObservableList<Employee> res = FXCollections.observableArrayList(le);
			EmployeeWithoutAccount.setItems(res);
			EmployeeWithoutAccount.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		} else {
			List<Employee> ef = le.stream()
					.filter(emp ->emp.getLastName().toLowerCase().contains(searchemployee.getText().toLowerCase()))
					.collect(Collectors.toList()) ;
			List<Employee> fr = new ArrayList<>();
			for (Employee ep : ef) {
				fr.add(ep);
			}
			ObservableList<Employee> rs = FXCollections.observableArrayList(fr);
			EmployeeWithoutAccount.setItems(rs);
			EmployeeWithoutAccount.setCellFactory(EmployeeList -> new RowEmployeeADDFXMLController());
		}
    }
 

}
