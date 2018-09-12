/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class PrmoteEmployeeController implements Initializable {

    @FXML
    private ComboBox<String> combopostpromote;
    @FXML
    private JFXTextArea descriptionpostpromote;
    @FXML
    private Button Promote;
    private FXMLLoader mLLoader;
    public static Employee emp;
    String jndi = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
    PostServiceRemote proxy;
    private ObservableList<String> com1;
    EmployeeServiceRemote proxyEmployee;
    String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
    private List<Post> depss = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   	 com1	= FXCollections.observableArrayList();
     proxy = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
     for(Post p:proxy.findAll())

     { if(p.getDepartement()==emp.getPost().getDepartement())
     { 	System.out.println(p.getEntitled());
    	 com1.add(p.getEntitled());}

     { if(p.getDepartement().equals(emp.getPost().getDepartement()))
     { 
    	 depss.add(p);
    	 com1.add(p.getEntitled());}
     }

	  combopostpromote.setItems((ObservableList<String>) com1);

	  


     }

    }    

    @FXML
    private void PromoteEmployee(ActionEvent event) {
    	proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);

      proxy = (PostServiceRemote) EJBLookupUtil.doLookup(jndi);
      Post p = depss.get(combopostpromote.getSelectionModel().getSelectedIndex());
      emp.setPost(p);
      proxyEmployee.update(emp);
      Stage principalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
  	principalStage.hide();
      

    }
    
}
