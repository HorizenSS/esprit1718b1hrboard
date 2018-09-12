/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RowCondidatInterviewController extends ListCell<Interview> {

	@FXML
	private AnchorPane rowCodidatInterview;
	@FXML
	private StackPane infocondidatStackPane;
	@FXML
	private ImageView imageCodidatInterview;
	@FXML
	private Label nameCondidatInterview;
	@FXML
	private Label noteCodidatInterview;
	@FXML
	private JFXButton HireCondidatInterview;
	@FXML
	private JFXButton RejectCondidatInterview;
	@FXML
	private Label posetdJobOfferInterview;
	@FXML
	private Label dateInterview;
	@FXML
	private JFXButton reportInterviewBttn;
	private FXMLLoader mLLoader;
	public static JFXDialog dialogSkill;
	private StackPane condidate_see;
	

	public RowCondidatInterviewController(StackPane stackPane) {
		condidate_see=stackPane;
	}



	@Override
	protected void updateItem(Interview condidate, boolean empty) {
		super.updateItem(condidate, empty);
		
		
		if (empty || condidate == null) {
			setText(null);
			setGraphic(null);
		} else {

			if (mLLoader == null) {

				mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowCodidateInterview.fxml"));
				mLLoader.setController(this);

				try {
					mLLoader.load();
				} catch (IOException e) {
					System.out.println("ma7abech iloadi ");
				}

			}
			nameCondidatInterview.setText(condidate.getCandidate().getFirstName() + " " + condidate.getCandidate().getLastName());
			imageCodidatInterview.setOnMouseEntered(e -> {
				InfoCondidatPopUpController.c=condidate.getCandidate();
				Pane child = null;
				try {
					child = FXMLLoader.load(getClass().getResource("/views/InfoCondidatPopUp.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				JFXDialogLayout dialogLayout = new JFXDialogLayout();
				dialogLayout.setMaxWidth(220);
				dialogLayout.setMinWidth(220);
				dialogLayout.setPrefWidth(220);

				dialogLayout.setPrefHeight(276);
				dialogLayout.setMaxHeight(276);
				dialogLayout.setMinHeight(276);

				dialogLayout.getChildren().add(child);

				dialogSkill = new JFXDialog(condidate_see, dialogLayout, JFXDialog.DialogTransition.CENTER);

				dialogSkill.show();
				dialogSkill.onMouseExitedProperty().set(e2 -> dialogSkill.close());
			});
			reportInterviewBttn.setVisible(false);
			reportInterviewBttn.setOnMouseClicked(e -> {

				Pane child = null;
				try {
					child = FXMLLoader.load(getClass().getResource("/views/ReportInterviewPopUp.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				JFXDialogLayout dialogLayout = new JFXDialogLayout();
				dialogLayout.setMaxWidth(220);
				dialogLayout.setMinWidth(220);
				dialogLayout.setPrefWidth(220);
				dialogLayout.setPrefHeight(276);
				dialogLayout.setMaxHeight(276);
				dialogLayout.setMinHeight(276);
				dialogLayout.getChildren().add(child);
				dialogSkill = new JFXDialog(condidate_see, dialogLayout, JFXDialog.DialogTransition.CENTER);
				dialogSkill.show();
			});
			HireCondidatInterview.setOnMouseClicked(e -> {
				HirringInterviewPopUpController.intervieww=condidate;
				HirringInterviewPopUpController.condidat=condidate.getCandidate();
				Pane child = null;
				try {
					child = FXMLLoader.load(getClass().getResource("/views/HirringInterviewPopUp.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				JFXDialogLayout dialogLayout = new JFXDialogLayout();
				dialogLayout.setMaxWidth(220);
				dialogLayout.setMinWidth(220);
				dialogLayout.setPrefWidth(220);
				dialogLayout.setPrefHeight(276);
				dialogLayout.setMaxHeight(276);
				dialogLayout.setMinHeight(276);
				dialogLayout.getChildren().add(child);
				dialogSkill = new JFXDialog(condidate_see, dialogLayout, JFXDialog.DialogTransition.CENTER);
				dialogSkill.show();
			});
			posetdJobOfferInterview.setText(condidate.getJobOffer().getEntitled());
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            String startDate = sd.format(condidate.getInteviewDate());
			dateInterview.setText(startDate);
			RejectCondidatInterview.setOnMouseClicked(e -> {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Confiramtion");
				alert.setContentText("Do You Want To Reject the Condidate");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						String jndiInterview ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
			        	InterviewServiceRemote proxyInterview = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
			        	System.out.println(condidate.getId());
			        	condidate.setAttempt(true);
			        	proxyInterview.update(condidate);
			        	String jndiICandidateHasJobOffer ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasJobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote";
						CandidateHasJobOfferServiceRemote proxyCandidateHasJobOffer = (CandidateHasJobOfferServiceRemote) EJBLookupUtil.doLookup(jndiICandidateHasJobOffer);
						CandidateHasJobOffer chjo = new CandidateHasJobOffer();
						chjo=proxyCandidateHasJobOffer.findByid(condidate.getCandidate(),condidate.getJobOffer());
						proxyCandidateHasJobOffer.removeCandidateHasSkill(chjo);
			        	Pane newpane;
				        try {
				            newpane = FXMLLoader.load(getClass().getResource("/views/InterviewHirring.fxml"));
				            HirringController.pane_hirring.getChildren().add(newpane);
				        } catch (IOException ex) {
				            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
				        }
					}
				});
			});
			String jndiICandidateHasJobOffer ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasJobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote";
			CandidateHasJobOfferServiceRemote proxyCandidateHasJobOffer = (CandidateHasJobOfferServiceRemote) EJBLookupUtil.doLookup(jndiICandidateHasJobOffer);
			CandidateHasJobOffer chjo = new CandidateHasJobOffer();
			chjo=proxyCandidateHasJobOffer.findByid(condidate.getCandidate(),condidate.getJobOffer());
			System.out.println(chjo);
			noteCodidatInterview.setText(new DecimalFormat("#.#").format(chjo.getNote()));
            if(chjo.getNote()>=6){
            	noteCodidatInterview.setTextFill(Color.RED);
            }else{
            	noteCodidatInterview.setTextFill(Color.web("#00BFFF"));
            }
			setText(null);
			setGraphic(rowCodidatInterview);
		}

	}
}
