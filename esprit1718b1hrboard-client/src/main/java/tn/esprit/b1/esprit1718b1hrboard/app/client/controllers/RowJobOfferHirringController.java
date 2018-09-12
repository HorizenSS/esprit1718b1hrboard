/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RowJobOfferHirringController extends ListCell<JobOffer> {

	@FXML
	private StackPane joboffer_row_stackpane;
	@FXML
	private AnchorPane jobOffer_row_pane;
	@FXML
	private Label dateOfCreation_jobOffer;
	@FXML
	private Label skill1_jobOffer_label;
	@FXML
	private Label skill2_jobOffer_label;
	@FXML
	private Label skill3_jobOffer_label;
	@FXML
	private Spinner<Integer> skill1_jobOffer_spinbtn;
	@FXML
	private Spinner<Integer> skill2_jobOffer_spinbtn;
	@FXML
	private Spinner<Integer> skill3_jobOffer_spinbtn;
	@FXML
	private TextField description_jobOffer;
	@FXML
	private RadioButton cdi_offerType_radiobtn;
	@FXML
	private ToggleGroup offer;
	@FXML
	private RadioButton cdd_offerType_radiobtn;
	@FXML
	private RadioButton trainship_offerType_radiobtn;
	@FXML
	private Button saveChange_btn;
	@FXML
	private Button deleteJobOffer_btn;
	@FXML
	private Button seeCondidate_btn;
	@FXML
	private TextField entitled_jobOffer1;
	private FXMLLoader mLLoader;
	public static JFXDialog dialogSkill;
	public static StackPane condidate_see;

	public RowJobOfferHirringController(StackPane jobOffer_stackPane) {
		condidate_see = jobOffer_stackPane;
	}

	@Override
	protected void updateItem(JobOffer jobOffer, boolean empty) {
		super.updateItem(jobOffer, empty);

		if (empty || jobOffer == null) {
			setText(null);
			setGraphic(null);
		} else {

			if (mLLoader == null) {

				mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowJobOfferHirring.fxml"));
				mLLoader.setController(this);

				try {
					mLLoader.load();
				} catch (IOException e) {
					System.out.println("ma7abech iloadi ");
				}

			}
			seeCondidate_btn.setOnMouseClicked(e -> {
				RowCodidateJobOfferController.jo = jobOffer;
				JobOfferPopUpController.j = jobOffer;
				Pane child = null;
				try {
					child = FXMLLoader.load(getClass().getResource("/views/JobOfferPopUp.fxml"));
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

				dialogSkill = new JFXDialog(condidate_see, dialogLayout, JFXDialog.DialogTransition.CENTER);

				dialogSkill.show();
			});
			// System.out.println(jobOffer.getJobOfferSkills());
			SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
					Math.round(jobOffer.getJobOfferSkills().get(0).getLevel()));
			SpinnerValueFactory<Integer> score2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
					Math.round(jobOffer.getJobOfferSkills().get(1).getLevel()));
			SpinnerValueFactory<Integer> score3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10,
					Math.round(jobOffer.getJobOfferSkills().get(2).getLevel()));
			skill1_jobOffer_spinbtn.setValueFactory(score);
			skill2_jobOffer_spinbtn.setValueFactory(score2);
			skill3_jobOffer_spinbtn.setValueFactory(score3);
			dateOfCreation_jobOffer.setText(jobOffer.getOfferDate().toString());
			skill1_jobOffer_label.setText(jobOffer.getJobOfferSkills().get(0).getSkill().getName());
			skill2_jobOffer_label.setText(jobOffer.getJobOfferSkills().get(1).getSkill().getName());
			skill3_jobOffer_label.setText(jobOffer.getJobOfferSkills().get(2).getSkill().getName());
			description_jobOffer.setText(jobOffer.getDescription());

			if (jobOffer.getOfferType().equals("CDI")) {
				cdi_offerType_radiobtn.setSelected(true);
			} else if (jobOffer.getOfferType().equals("CDD")) {
				cdd_offerType_radiobtn.setSelected(true);
			} else {
				trainship_offerType_radiobtn.setSelected(true);
			}
			entitled_jobOffer1.setText(jobOffer.getEntitled());
			deleteJobOffer_btn.setOnMouseClicked(e -> {
				if (!jobOffer.getJobOfferInscriptions().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Can Not Delete This Job Offer");
					alert.setContentText("Check If There Is Candidate Postuled In This Job Offer");
					alert.show();
				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("CONFIRMATION");
					alert.setHeaderText("Save Confiramtion");
					alert.setContentText("Do You Want To Delete the Job Offer");

					alert.showAndWait().ifPresent(response -> {
						if (response == ButtonType.OK) {
							String jndiJobOffer = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/JobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote";
							JobOfferServiceRemote proxy = (JobOfferServiceRemote) EJBLookupUtil.doLookup(jndiJobOffer);

							proxy.delete(jobOffer);
							Pane newpane;
							try {
								newpane = FXMLLoader.load(getClass().getResource("/views/JobOfferHirring.fxml"));
								HirringController.pane_hirring.getChildren().add(newpane);
							} catch (IOException ex) {
								Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					});
				}
			});
			saveChange_btn.setOnMouseClicked(e -> {
				System.out.println(description_jobOffer.getText());
				System.out.println("teeestete");
				if ((description_jobOffer.getText() == "") || (entitled_jobOffer1.getText() == "")) {

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Check Fields");
					alert.setContentText("There Some Fields Empty");
					alert.show();
				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("CONFIRMATION");
					alert.setHeaderText("Save Confiramtion");
					alert.setContentText("Do You Want To Change the Job Offer");

					alert.showAndWait().ifPresent(response -> {
						if (response == ButtonType.OK) {
							String jndiJobOffer = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/JobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote";
							JobOfferServiceRemote proxy = (JobOfferServiceRemote) EJBLookupUtil.doLookup(jndiJobOffer);
							jobOffer.setDescription(description_jobOffer.getText());
							jobOffer.setEntitled(entitled_jobOffer1.getText());

							List<JobOfferHasSkill> johs = new ArrayList();
							johs = jobOffer.getJobOfferSkills();
							JobOfferHasSkill jo1 = new JobOfferHasSkill();
							JobOfferHasSkill jo2 = new JobOfferHasSkill();
							JobOfferHasSkill jo3 = new JobOfferHasSkill();
							jo1 = johs.get(0);
							jo2 = johs.get(1);
							jo3 = johs.get(2);
							jo1.setLevel(skill1_jobOffer_spinbtn.getValue());
							jo2.setLevel(skill2_jobOffer_spinbtn.getValue());
							jo3.setLevel(skill3_jobOffer_spinbtn.getValue());

							johs.set(0, jo1);
							johs.set(1, jo2);
							johs.set(2, jo3);
							jobOffer.setJobOfferSkills(johs);

							if (cdi_offerType_radiobtn.isSelected()) {
								jobOffer.setOfferType("CDI");
							} else if (cdd_offerType_radiobtn.isSelected()) {
								jobOffer.setOfferType("CDD");
							} else {
								jobOffer.setOfferType("Trainship");
							}

							proxy.update(jobOffer);

							Pane newpane;
							try {
								newpane = FXMLLoader.load(getClass().getResource("/views/JobOfferHirring.fxml"));
								HirringController.pane_hirring.getChildren().add(newpane);
							} catch (IOException ex) {
								Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					});
				}
			});
			setText(null);
			setGraphic(jobOffer_row_pane);

		}

	}

}
