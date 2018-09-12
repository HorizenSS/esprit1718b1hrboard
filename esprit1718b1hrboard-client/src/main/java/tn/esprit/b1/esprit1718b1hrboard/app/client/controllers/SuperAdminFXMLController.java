/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ilyes
 */
public class SuperAdminFXMLController {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXButton AccoutAddGUI;
    @FXML
    private JFXButton AccoutDeletGui;

	private Pane p;
	public static int direction = -1;
    @FXML
    private AnchorPane ChangePane;
    @FXML
    private Label xx;
    @FXML
    private void GoToAccoutAdd(ActionEvent event) {
    
    	try {
      p = FXMLLoader.load(getClass().getResource("/views/AddAccoutAdminFxml.fxml"));
           ChangePane.getChildren().clear();
            ChangePane.getChildren().add(p);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToAccoutDelete(ActionEvent event) {

    	try {
      p = FXMLLoader.load(getClass().getResource("/views/DeleteEmploye.fxml"));
           ChangePane.getChildren().clear();
            ChangePane.getChildren().add(p);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

