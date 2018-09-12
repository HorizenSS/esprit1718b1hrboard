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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HirringController implements Initializable {

    @FXML
    private Button jobOffer_hiring_btn;
    @FXML
    private Button interview_hiring_btn;
    @FXML
    private Pane hiring_pane;
    
    public static Pane pane_hirring;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane_hirring = hiring_pane ;
    }    

    @FXML
    private void OnClickJobOffer(ActionEvent event) {
        jobOffer_hiring_btn.setStyle(" -fx-border-width: 0 0 4 0;\n"
                + "    -fx-border-color:  #ff2e44;"
                + "-fx-background-color:   #324f6b");
        interview_hiring_btn.setStyle(" -fx-border-width: 0 0 0 0;\n"
                + "    -fx-border-color:  #ff2e44;"
                + "-fx-background-color:   #324f6b");
        
        Pane newpane;
        try {
            newpane = FXMLLoader.load(getClass().getResource("/views/JobOfferHirring.fxml"));
            hiring_pane.getChildren().add(newpane);
        } catch (IOException ex) {
            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
			
    }

    @FXML
    private void OnClickInterviews(ActionEvent event) {
        interview_hiring_btn.setStyle(" -fx-border-width: 0 0 4 0;\n"
                + "    -fx-border-color:  #ff2e44;"
                		+ "-fx-background-color:   #324f6b");
        
        jobOffer_hiring_btn.setStyle(" -fx-border-width: 0 0 0 0;\n"
                + "    -fx-border-color:  #ff2e44;"
                + "-fx-background-color:   #324f6b");
        
        Pane newpane;
        try {
            newpane = FXMLLoader.load(getClass().getResource("/views/InterviewHirring.fxml"));
            hiring_pane.getChildren().add(newpane);
        } catch (IOException ex) {
            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
