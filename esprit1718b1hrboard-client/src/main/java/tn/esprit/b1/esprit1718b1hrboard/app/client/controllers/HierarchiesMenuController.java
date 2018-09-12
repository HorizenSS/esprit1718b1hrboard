/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class HierarchiesMenuController implements Initializable {

    @FXML
    private AnchorPane HierarchiesMainPane;
    @FXML
    private JFXButton DepartementsButton;
    @FXML
    private JFXButton EmployeesButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void OnDepartementsHierarchyClicked(MouseEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/DepartmentsHierarchy.fxml"));
            HierarchiesMainPane.getChildren().clear();
            HierarchiesMainPane.getChildren().add(P);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnEmployeesHierarchyClicked(MouseEvent event) {
    }
    
}
