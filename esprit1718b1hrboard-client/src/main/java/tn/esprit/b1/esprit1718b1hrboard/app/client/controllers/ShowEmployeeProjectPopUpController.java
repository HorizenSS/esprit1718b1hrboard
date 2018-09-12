/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class ShowEmployeeProjectPopUpController implements Initializable {

    @FXML
    private Label lblProjectName;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private Label valueProject;
    
    public static Project pj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	if(pj != null){
    		lblProjectName.setText(pj.getName());
    		SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy");
    		lblEndDate.setText(sd.format(pj.getEndDate()));
    		lblStartDate.setText(sd.format(pj.getStartDate()));
    		valueProject.setText(pj.getGain().toString()+" DT");
    	}
    }

    
    
}
