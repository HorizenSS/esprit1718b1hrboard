/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployeesMgmntSpaceController implements Initializable {
    
    @FXML
    private Button EmployeesMgmntBtn;
    @FXML
    private Button ProjectsEmpBtn;
    @FXML
    private AnchorPane InsideEmployeesWorkSpace;
    public static Pane inside;

    // public static Pane InsideEmployeesWorkSpaceOutside;
    public static int changePane = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inside = InsideEmployeesWorkSpace;
        if (changePane == 1) {
            Pane child;
            try {
                child = FXMLLoader.load(getClass().getResource("/views/EmployeesGeneralList.fxml"));
                InsideEmployeesWorkSpace.getChildren().clear();
                InsideEmployeesWorkSpace.getChildren().add(child);
                
            } catch (IOException e) {
            }
            
        }
    }
    
    @FXML
    private void OnActionEmployeesMgnt(ActionEvent event) {
        
        Pane child;
        try {
            child = FXMLLoader.load(getClass().getResource("/views/EmployeesGeneralList.fxml"));
            InsideEmployeesWorkSpace.getChildren().clear();
            InsideEmployeesWorkSpace.getChildren().add(child);
        } catch (IOException e) {
        }
        
    }
    
    
    @FXML
    private void OnActionProjectsEmp(ActionEvent event) {
        Pane child;
        try {
            child = FXMLLoader.load(getClass().getResource("/views/ListProjects.fxml"));
            InsideEmployeesWorkSpace.getChildren().clear();
            InsideEmployeesWorkSpace.getChildren().add(child);
            
        } catch (IOException e) {
        }
    }
    
}
