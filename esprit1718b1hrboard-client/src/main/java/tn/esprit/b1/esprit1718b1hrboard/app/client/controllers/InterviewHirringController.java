/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InterviewHirringController implements Initializable {

	@FXML
	private AnchorPane interviewPane;
	@FXML
	private JFXRadioButton condidatNameIntervieiwRadioBttn;
	@FXML
	private ToggleGroup cherch;
	@FXML
	private JFXRadioButton dateIntervieiwRadioBttn;
	@FXML
	private JFXRadioButton jobOfferEntitledIntervieiwRadioBttn;
	@FXML
	private ListView<Interview> interviewList;
	@FXML
	private JFXDatePicker dateBeginDatePickerInterview;
	@FXML
	private JFXButton finddateInterviewBttn;
	@FXML
	private JFXTextField criterecherchTextFieldInterview;
	@FXML
	private JFXDatePicker dateendDatePickerInterview;
	@FXML
	private StackPane interviewHirringStackpane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (dateIntervieiwRadioBttn.isSelected()) {
			dateBeginDatePickerInterview.setVisible(true);
			dateendDatePickerInterview.setVisible(true);
			finddateInterviewBttn.setVisible(true);
			criterecherchTextFieldInterview.setVisible(false);
		} else {
			dateBeginDatePickerInterview.setVisible(false);
			dateendDatePickerInterview.setVisible(false);
			finddateInterviewBttn.setVisible(false);
			criterecherchTextFieldInterview.setVisible(true);
		}
		condidatNameIntervieiwRadioBttn.setOnMouseClicked(e -> {
			dateBeginDatePickerInterview.setVisible(false);
			dateendDatePickerInterview.setVisible(false);
			finddateInterviewBttn.setVisible(false);
			criterecherchTextFieldInterview.setVisible(true);
		});
		dateIntervieiwRadioBttn.setOnMouseClicked(e -> {
			dateBeginDatePickerInterview.setVisible(true);
			dateendDatePickerInterview.setVisible(true);
			finddateInterviewBttn.setVisible(true);
			criterecherchTextFieldInterview.setVisible(false);
		});
		jobOfferEntitledIntervieiwRadioBttn.setOnMouseClicked(e -> {
			dateBeginDatePickerInterview.setVisible(false);
			dateendDatePickerInterview.setVisible(false);
			finddateInterviewBttn.setVisible(false);
			criterecherchTextFieldInterview.setVisible(true);
		});
		String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
		InterviewServiceRemote proxy = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
		List<Interview> i = new ArrayList();
		List<Interview> i2 = new ArrayList();
		List<Candidate> c = new ArrayList();
		i = proxy.findAll();
		for (Interview interview : i) {
			if (!interview.getAttempt()) {
				i2.add(interview);
			}
		}

		ObservableList<Interview> names = FXCollections.observableArrayList(i2);
		interviewList.setItems(names);
		interviewList.setCellFactory(JobOfferListView -> new RowCondidatInterviewController(interviewHirringStackpane));

		finddateInterviewBttn.setOnMouseClicked(e -> {
			List<Interview> i1 = new ArrayList();
			i1 = proxy.findAll();
			List<Interview> i3 = new ArrayList();

			if ((dateendDatePickerInterview.getValue() != null) && (dateBeginDatePickerInterview.getValue() != null)) {
				Date date = Date
						.from(dateendDatePickerInterview.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date date2 = Date
						.from(dateBeginDatePickerInterview.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				i3 = i1.stream().filter(e1 -> e1.getInteviewDate().before(date) && e1.getInteviewDate().after(date2))
						.collect(Collectors.toList());
			} else if ((dateendDatePickerInterview.getValue() == null)
					&& (dateBeginDatePickerInterview.getValue() != null)) {
				Date date2 = Date
						.from(dateBeginDatePickerInterview.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				i3 = i1.stream().filter(e1 -> e1.getInteviewDate().after(date2)).collect(Collectors.toList());
			} else if ((dateendDatePickerInterview.getValue() != null)
					&& (dateBeginDatePickerInterview.getValue() == null)) {
				Date date = Date
						.from(dateendDatePickerInterview.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				i3 = i1.stream().filter(e1 -> e1.getInteviewDate().before(date)).collect(Collectors.toList());
			} else {
				i3 = i1;
			}
			if (i3.isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Sorry");
				alert.setContentText("there's not a result ");
				alert.show();
			} else {
				ObservableList<Interview> namess = FXCollections.observableArrayList(i3);
				interviewList.setItems(namess);
				interviewList.setCellFactory(
						JobOfferListView -> new RowCondidatInterviewController(interviewHirringStackpane));
			}
		});
	}

	@FXML
	private void chercherinterview(KeyEvent event) {
		if (condidatNameIntervieiwRadioBttn.isSelected()) {
			if (criterecherchTextFieldInterview.getText() == "") {
				String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
				InterviewServiceRemote proxy = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
				List<Interview> i = new ArrayList();
				List<Interview> i2 = new ArrayList();
				i = proxy.findAll();
				for (Interview interview : i) {
					if (!interview.getAttempt()) {
						i2.add(interview);
					}
				}

				ObservableList<Interview> names = FXCollections.observableArrayList(i2);
				interviewList.setItems(names);
				interviewList.setCellFactory(
						JobOfferListView -> new RowCondidatInterviewController(interviewHirringStackpane));
			} else {
				String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
				InterviewServiceRemote proxy = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
				List<Interview> i = new ArrayList();
				List<Interview> i2 = new ArrayList();
				i = proxy.findAll();
				i2 = i.stream().filter(
						e -> e.getCandidate().getFirstName().toLowerCase().contains(criterecherchTextFieldInterview.getText().toLowerCase()))
						.collect(Collectors.toList());
				ObservableList<Interview> names = FXCollections.observableArrayList(i2);
				interviewList.setItems(names);
				interviewList.setCellFactory(
						JobOfferListView -> new RowCondidatInterviewController(interviewHirringStackpane));
			}
		} else if (jobOfferEntitledIntervieiwRadioBttn.isSelected()) {
			if (criterecherchTextFieldInterview.getText() == "") {
				String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
				InterviewServiceRemote proxy = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
				List<Interview> i = new ArrayList();
				List<Interview> i2 = new ArrayList();
				i = proxy.findAll();
				for (Interview interview : i) {
					if (!interview.getAttempt()) {
						i2.add(interview);
					}
				}
				if (i2.isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("Sorry");
					alert.setContentText("there's not a result ");
					alert.show();
				} else {
					ObservableList<Interview> names = FXCollections.observableArrayList(i2);
					interviewList.setItems(names);
					interviewList.setCellFactory(
							JobOfferListView -> new RowCondidatInterviewController(interviewHirringStackpane));
				}
			} else {
				String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
				InterviewServiceRemote proxy = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
				List<Interview> i = new ArrayList();
				List<Interview> i2 = new ArrayList();
				i = proxy.findAll();
				i2 = i.stream()
						.filter(e -> e.getJobOffer().getEntitled().toLowerCase().contains(criterecherchTextFieldInterview.getText().toLowerCase()))
						.collect(Collectors.toList());
				if (i2.isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("Sorry");
					alert.setContentText("there's not a result ");
					alert.show();
				} else {
					ObservableList<Interview> names = FXCollections.observableArrayList(i2);
					interviewList.setItems(names);
					interviewList.setCellFactory(
							JobOfferListView -> new RowCondidatInterviewController(interviewHirringStackpane));
				}
			}
		}
	}
}
