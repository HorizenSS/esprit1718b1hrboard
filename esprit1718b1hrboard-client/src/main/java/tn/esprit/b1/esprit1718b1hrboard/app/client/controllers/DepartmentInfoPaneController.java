/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;

/**
 * FXML Controller class
 *
 * @author Ghassen
 */
public class DepartmentInfoPaneController implements Initializable {

    @FXML
    private Pane DepartmentInfoPane;
    @FXML
    private Label LablPlace;
    @FXML
    private Label lablSupDep;
    @FXML
    private Label lblDepartementName;
    @FXML
    private JFXTextArea DescriptionDep;
    public static Departement Depart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowDepInfo(Depart);
    }
    public void ShowDepInfo( Departement dep){
    	lblDepartementName.setText(dep.getName());
    	LablPlace.setText(dep.getPlace());
    	DescriptionDep.setText(dep.getDescription());
    	if(dep.getSuperiorDep()==null){
    		lablSupDep.setVisible(false);
    		}else{
    		lablSupDep.setText(dep.getSuperiorDep().getName());
    		lablSupDep.setVisible(true);
    	}
    }
    
}
