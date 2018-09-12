/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Reward;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.RewardServiceRemote;

import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class GiveAwardToEmployeeFXMLController implements Initializable {

    @FXML
    private Button SendAwardt;
    @FXML
    private JFXTextArea EncouragementEmployee;
    @FXML
    private ComboBox<String> AwardsEmployee;
    private ObservableList<Employee> employee;
    private ObservableList<String> ac;

    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    private JFXTextField bonus;
    @FXML
    private JFXTextField Bonus;
    RewardServiceRemote proxyp;
    @FXML
    private Text xx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   	 ac	= FXCollections.observableArrayList();

	 ac.add("gift card");
	 ac.add("bonus");
	 AwardsEmployee.setItems((ObservableList<String>) ac);
    }    
    @FXML
    private void choisirAward(KeyEvent event) {
    	}
 
    @FXML
    private void AddAccount(ActionEvent event) {
    	String jndip= "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/RewardService!tn.esprit.b1.esprit1718b1hrboard.services.RewardServiceRemote";
        Long millis = System.currentTimeMillis();
	       Date datej = new Date(millis);
		proxyp = (RewardServiceRemote) EJBLookupUtil.doLookup(jndip);
		Reward r= new Reward(null, EncouragementEmployee.getText(), datej, null, null, null, Double.valueOf(Bonus.getText()));
		  proxyp.save(r);
		 	Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			principalStage.hide();
    }

   
    
}
