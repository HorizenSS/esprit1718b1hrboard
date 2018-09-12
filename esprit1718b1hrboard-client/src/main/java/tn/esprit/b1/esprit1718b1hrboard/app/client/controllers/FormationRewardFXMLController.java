/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * @author ilyes
 */
public class FormationRewardFXMLController implements Initializable {
	
    @FXML
    private Pane transition;
    @FXML
    private Button gotoformation;
    @FXML
    private Button gotoreward;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/RewardFXML.fxml"));
            transition.getChildren().clear();
            transition.getChildren().add(P);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void GoToRewards(ActionEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/RewardFXML.fxml"));
            transition.getChildren().clear();
            transition.getChildren().add(P);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoformation(ActionEvent event) {
    }

    @FXML
    private void gotoreward(ActionEvent event) {
    	try {
            Pane P = FXMLLoader.load(getClass().getResource("/views/RewardFXML.fxml"));
            transition.getChildren().clear();
            transition.getChildren().add(P);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
