/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class TrainingNormalController implements Initializable {

	@FXML
	private AnchorPane NTrainingPaneId;
	@FXML
	private Label Label;
	@FXML
	private ListView<Training> TrainingListId;
	@FXML
	private StackPane training_StackPane;

	TrainingServiceRemote proxyTraining;
	String jndiTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TrainingService!tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote";
	@FXML
	private JFXButton AddTrainingId;
	public static JFXDialog dialogSkill;
	public static JFXDialog dialogTraining;
	public URL url1;
	public ResourceBundle rb1;
	public static Training trSelected;
    @FXML
    private JFXRadioButton nameSearchRadio;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXTextField searchnameID;
	public TrainingNormalController() {
		super();
	}

	@FXML
	void OnMouseClicked() {
//		Pane child = null;
//		try {
//			child = FXMLLoader.load(getClass().getResource("/views/ListEmployeeToTrainingFXML.fxml"));
//		} catch (IOException ex) {
//		}
//
//		JFXDialogLayout dialogLayout = new JFXDialogLayout();
//		dialogLayout.getChildren().add(child);
//		dialogTraining = new JFXDialog(training_StackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
//		dialogTraining.show();
		trSelected = TrainingListId.getSelectionModel().getSelectedItem();
		System.out.println(trSelected);
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			proxyTraining = (TrainingServiceRemote) EJBLookupUtil.doLookup(jndiTraining);
			System.out.println("aaaaaa");
			ObservableList<Training> ps = FXCollections.observableArrayList(proxyTraining.findAll());
			System.out.println("bbbb");
			TrainingListId.setItems(ps);
			System.out.println("cccc");
			TrainingListId.setCellFactory(TrainingListView -> new RowNTrainingController(training_StackPane,NTrainingPaneId));
		}

		catch (Exception e) {
			System.out.println("erreur getall training ou affichage des celulles de la liste");
		}
	}

	@FXML
	private void OnAddTrainingClicked(ActionEvent event) {

		AddTrainingId.setOnMouseClicked(e -> {
			AddNewTrainingController.tn = this;
			Pane child = null;
			try {
				child = FXMLLoader.load(getClass().getResource("/views/AddNewTraining.fxml"));
				
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

			dialogSkill = new JFXDialog(training_StackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);

			dialogSkill.show();
		});
	}
	
	public void INITAAT() {
		this.initialize(url1, rb1);
	}

	public void finish() {
		dialogSkill.close();
	}

    @FXML
    private void OnSearchNameClicked(KeyEvent event) {
    	
    	
    	proxyTraining = (TrainingServiceRemote) EJBLookupUtil.doLookup(jndiTraining);
    	
    	List<Training> lt = new ArrayList();
    	lt = proxyTraining.findAll();
    	List<Training> lt2 = new ArrayList();
    	lt2 = lt.stream().filter(e -> e.getTrainingName().toLowerCase().contains(searchnameID.getText().toLowerCase())).collect(Collectors.toList());
		ObservableList<Training> ps = FXCollections.observableArrayList(lt2);
		TrainingListId.setItems(ps);
		TrainingListId.setCellFactory(TrainingListView -> new RowNTrainingController(training_StackPane,NTrainingPaneId));
		
		
		
		
		
		
		
		
		
		
    }

}
