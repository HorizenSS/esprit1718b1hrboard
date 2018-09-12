/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.Training;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasTrainingServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
/**
 *
 * @author Majdi Rabie
 */
public class RowNTrainingController extends ListCell<Training> {

    @FXML
    private AnchorPane row;

    @FXML
    private Label libelle_trainerName_id;

    @FXML
    private Text decription_Subject_id;

    @FXML
    private Label libelle_place_id1;
    
    @FXML
    private Label libelle_Subject;
    @FXML
    private Label libelle_place;
    @FXML
    private Label dateDepart_lbl;
    @FXML
    private Label dateDepart_lbl1;
    @FXML
    private Label training_name_Id;
    
    @FXML
    private Label startDateId;

    @FXML
    private Label EndDateId;
    
    @FXML
    private Label training_type_Id1;
    
    @FXML
    private StackPane stackPane;

    
    @FXML
    private JFXButton addEmployeeBtnId;
    
    @FXML
    private Button editTrainingBtnId;

    @FXML
    private Button deleteTrainingBtnId;
    public AnchorPane panerefresh;
    public StackPane a;
    public static JFXDialog dialog;    
    public static JFXDialog dialogTraining;
    private AnchorPane employee_see;
    private FXMLLoader mLLoader;
    TrainingServiceRemote proxyTraining;
	String jndiTraining = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TrainingService!tn.esprit.b1.esprit1718b1hrboard.services.TrainingServiceRemote";

     
    
    public RowNTrainingController(StackPane pane, AnchorPane panerefr ) {
    		a=pane;
    		panerefresh=panerefr;
    }

	@Override
    protected void updateItem(Training training, boolean empty) {
        super.updateItem(training, empty);         
        ListEmployeeToTrainingFXMLController.tr=training;
					 if (empty || training == null) {
				            setText(null);
				            setGraphic(null);
				        } else {
				            if (mLLoader == null) {
				                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowNTraining.fxml"));
				                mLLoader.setController(this);
				                try {
				                    mLLoader.load();
				                } catch (IOException e) {
				                    System.out.println("ma7abech iloadi ");
				                }

				            }
				        training_name_Id.setText(training.getTrainingName());
			            libelle_trainerName_id.setText(training.getTrainer().getFirstName()+" "+training.getTrainer().getLastName());
			            decription_Subject_id.setText(training.getSubject());
			            libelle_place_id1.setText(training.getPlace());
			            training_type_Id1.setText(training.getTrainingtype());
			            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			            String startDate = sd.format(training.getTarainingDateStart());
			            startDateId.setText(startDate);
			            String endDate = sd.format(training.getTarainingDateEnd());
			            EndDateId.setText(endDate);
			            addEmployeeBtnId.setOnAction((ev)->{
			            	TrainingNormalController.trSelected=training;
			            	Pane child = null;
			        		try {
			        			child = FXMLLoader.load(getClass().getResource("/views/ListEmployeeToTrainingFXML.fxml"));
			        		} catch (IOException ex) {
			        		}

			        		JFXDialogLayout dialogLayout = new JFXDialogLayout();
			        		dialogLayout.getChildren().add(child);
			        		dialogTraining = new JFXDialog(a, dialogLayout, JFXDialog.DialogTransition.CENTER);
			        		dialogTraining.show();
			        		
			            	
			            	
			            });
			            
			            editTrainingBtnId.setOnAction((ev) -> {		
			            	UpdateTrainingController.refreshPa=panerefresh;
			            	TrainingNormalController.trSelected=training;
			            	Pane child = null;
			        		try {
			        			child = FXMLLoader.load(getClass().getResource("/views/UpdateTraining.fxml"));
			        		} catch (IOException ex) {
			        		}

			        		JFXDialogLayout dialogLayout = new JFXDialogLayout();
			        		dialogLayout.getChildren().add(child);
			        		dialogTraining = new JFXDialog(a, dialogLayout, JFXDialog.DialogTransition.CENTER);
			        		dialogTraining.show();
			        		
			            });
			            deleteTrainingBtnId.setOnAction((ev)->{
			            	TrainingNormalController.trSelected=training;
			            	proxyTraining = (TrainingServiceRemote) EJBLookupUtil.doLookup(jndiTraining);
			            	Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("CONFIRMATION");
							alert.setHeaderText("Delete Confiramtion");
							alert.setContentText("Are you sure to delete this Training ?");
							alert.showAndWait().ifPresent(response -> {
								if (response == ButtonType.OK) {
									proxyTraining.delete(training);	
									Pane child = null;

									try {
										child = FXMLLoader.load(getClass().getResource("/views/TrainingNormal.fxml"));
									} catch (IOException ex) {
										// TODO Auto-generated catch block
										ex.printStackTrace();
									}
									
									a.getChildren().clear();
									a.getChildren().add(child);
									
									
									System.out.println("************deleting************");
								}
			            	
			            	
			            });
			            });
			            
				            setText(null);
				            setGraphic(row);

				        }

	
    }

	@FXML
	private void AddEmployeeBtnClicked(ActionEvent event) {

		
	 }
	@FXML
    void OnActionDeleteTrainingClicked(ActionEvent event) {

    }

    @FXML
    void OnActioneditTrainingClicked(ActionEvent event) {

    }
}
