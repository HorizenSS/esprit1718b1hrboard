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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class TrainingMenuController implements Initializable {

    @FXML
    private JFXButton NTrainingBtnId;
    @FXML
    private JFXButton PTrainnigBtnId;
    @FXML
    private StackPane MenusPaneId;
    JFXButton currentselectedButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnNormalTrainigClicked(ActionEvent event) {
    	PTrainnigBtnId.setStyle(" -fx-border-width: 0 0 0 0;\n"
                + "    -fx-border-color:  #2A3F54;"
                + "-fx-background-radius: #324f6b;");
    	NTrainingBtnId.setStyle(" -fx-border-width: 0 0 4 0;\n"
                + "    -fx-border-color:  #2A3F54;"
                + "-fx-background-radius: #324f6b;");
    	try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/TrainingNormal.fxml"));
			MenusPaneId.getChildren().clear();
			MenusPaneId.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void OnProjectTrainingClicked(ActionEvent event) {
    	NTrainingBtnId.setStyle(" -fx-border-width: 0 0 0 0;\n"
                + "    -fx-border-color:  #2A3F54;"
                + "-fx-background-radius: #324f6b;");
    	PTrainnigBtnId.setStyle(" -fx-border-width: 0 0 4 0;\n"
                + "    -fx-border-color:  #2A3F54;"
                + "-fx-background-radius: #324f6b;");
    
    	try {
			Pane child = FXMLLoader.load(getClass().getResource("/views/TrainingProject.fxml"));
			MenusPaneId.getChildren().clear();
			MenusPaneId.getChildren().add(child);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
   
}
