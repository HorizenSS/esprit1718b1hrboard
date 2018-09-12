/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class JobOfferHirringController implements Initializable {

    @FXML
    private AnchorPane JobOffer_hirring_scene;
    @FXML
    private ListView<JobOffer> ListeJobOffer;
    @FXML
    private Button add_jobOffer_button;
    @FXML
    private StackPane jobOffer_stackPane;
    public static JFXDialog dialogSkill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	String jndiJobOffer ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/JobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote";
        try {
			JobOfferServiceRemote proxy = (JobOfferServiceRemote) EJBLookupUtil.doLookup(jndiJobOffer);
			ObservableList<JobOffer> names = FXCollections.observableArrayList(proxy.findAll());
			ListeJobOffer.setItems(names);
			ListeJobOffer.setCellFactory(JobOfferListView -> new RowJobOfferHirringController(jobOffer_stackPane));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur getall offerjob ou affichage des celulles de la liste");
		}
        
    	
        add_jobOffer_button.setOnMouseClicked(e -> {

			Pane child = null;
			try {
				child = FXMLLoader.load(getClass().getResource("/views/AddNewJobOfferPopUp.fxml"));
			} catch (IOException ex) {
			}

			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			dialogLayout.setMaxWidth(220);
			dialogLayout.setMinWidth(220);
			dialogLayout.setPrefWidth(220);

			dialogLayout.setPrefHeight(276);
			dialogLayout.setMaxHeight(276);
			dialogLayout.setMinHeight(276);

			dialogLayout.getChildren().add(child);

			dialogSkill = new JFXDialog(jobOffer_stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);

			dialogSkill.show();
        });
        
    }    
    
}
