/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.calendarfx.model.Entry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.CalenderApp;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOfferPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RowCodidateJobOfferController extends ListCell<Candidate> {

	@FXML
	private AnchorPane rowCodidatJobOffer;
	@FXML
	private ImageView imageCodidatJobOffer;
	@FXML
	private Label nameCodidatJobOffer;
	@FXML
	private Label mailCodidatJobOffer;
	@FXML
	private Label adreseeCodidatJobOffer;
	@FXML
	private Label phonenumberCodidatJobOffer;
	@FXML
	private Label diplomeCodidatJobOffer;
	@FXML
	private Label experienceCodidatJobOffer;
	@FXML
	private Label skiil1CodidatJobOffer;
	@FXML
	private Label skill2CodidatJobOffer;
	@FXML
	private Label skill3CodidatJobOffer;
	@FXML
	private Label noteCodidatJobOffer;
	@FXML
	private JFXButton takeinterviewCodidatJobOfferBttn;
	@FXML
	private JFXButton rejectedCodidatJobOfferBttn;
	private FXMLLoader mLLoader;
	public static JobOffer jo = new JobOffer();

	@Override
	protected void updateItem(Candidate condidate, boolean empty) {
		super.updateItem(condidate, empty);

		if (empty || condidate == null) {
			setText(null);
			setGraphic(null);
		} else {

			if (mLLoader == null) {

				mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowCodidateJobOffer.fxml"));
				mLLoader.setController(this);

				try {
					mLLoader.load();
				} catch (IOException e) {
					System.out.println("ma7abech iloadi ");
				}

			}
			nameCodidatJobOffer.setText(condidate.getFirstName() + " " + condidate.getLastName());
			mailCodidatJobOffer.setText(condidate.getEmail());
			adreseeCodidatJobOffer.setText(condidate.getAddress());
			phonenumberCodidatJobOffer.setText(condidate.getPhoneNumber().toString());
			diplomeCodidatJobOffer.setText(condidate.getDiplomas());
			experienceCodidatJobOffer.setText(condidate.getWorkExperiences());
			Set<CandidateHasSkill> chs = new HashSet();
			List<CandidateHasSkill> chss = new ArrayList();
			chs = condidate.getSpecificationsSkillsCand().stream().sorted((e1, e2) -> e1.getLevel() - e2.getLevel())
					.collect(Collectors.toSet());
			chss = chs.stream().collect(Collectors.toList());
			skiil1CodidatJobOffer.setText(chss.get(0).getSkill().getName());
			skill2CodidatJobOffer.setText(chss.get(1).getSkill().getName());
			skill3CodidatJobOffer.setText(chss.get(2).getSkill().getName());
			////////////////////// calcul note
			Float note = 0.0f;

			List<JobOfferHasSkill> johs = new ArrayList();
			johs = jo.getJobOfferSkills();
			int coef = jo.getJobOfferSkills().get(0).getLevel() + jo.getJobOfferSkills().get(1).getLevel()
					+ jo.getJobOfferSkills().get(2).getLevel();
			for (CandidateHasSkill ch : chss) {
				if (ch.getSkill().getId() == johs.get(0).getSkill().getId()) {
					note += ch.getLevel() * johs.get(0).getLevel();
				}
				if (ch.getSkill().getId() == johs.get(1).getSkill().getId()) {
					note += ch.getLevel() * johs.get(1).getLevel();
				}
				if (ch.getSkill().getId() == johs.get(2).getSkill().getId()) {
					note += ch.getLevel() * johs.get(2).getLevel();
				}
			}

			note = note / coef;
			final Float note1 = note ;
			String jndiCandidatHasJobOffer = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasJobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote";
			CandidateHasJobOfferServiceRemote proxy = (CandidateHasJobOfferServiceRemote) EJBLookupUtil
					.doLookup(jndiCandidatHasJobOffer);
			noteCodidatJobOffer.setText(new DecimalFormat("#.#").format(note));
			if (note >= 6) {
				noteCodidatJobOffer.setTextFill(Color.RED);
			} else {
				noteCodidatJobOffer.setTextFill(Color.web("#00BFFF"));
			}
			takeinterviewCodidatJobOfferBttn.setOnMouseClicked(e -> {
				String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
				InterviewServiceRemote proxy2 = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
				List<Interview> l = new ArrayList();
				l = proxy2.findAll();
				Stage s = new Stage();
				Map<LocalDate, List<Entry<?>>> map = new HashMap();// 6,7
				List<Entry<?>> listtransition = new ArrayList();
				listtransition = CalenderApp.startt(s, l);
				
				final List<Entry<?>> listentrydebut = listtransition;
				s.setOnCloseRequest(a -> {
					List<Entry<?>> listentry = new ArrayList();
					LocalDate l3 = LocalDate.of(2018, 1, 1);
		            LocalDate l4 = LocalDate.of(2018, 12, 30);
		        	Map<LocalDate, List<Entry<?>>> result = CalenderApp.birthdays.findEntries(l3 ,l4, ZoneId.systemDefault());
		        	for (Map.Entry<LocalDate, List<Entry<?>>> entry : result.entrySet()) {
						for (Entry<?> ent : entry.getValue()) {
							listentry.add(ent);
						}
					}
		        	for (Entry<?> entrynew : listentry) {
		        		Boolean test = false;
						for (Entry<?> entryold : listentrydebut) {
							if(entrynew.getId().equals(entryold.getId())){
								test = true;
								break;
							}
						}
						if(test==true){
							Interview i = new Interview();
							i=proxy2.findByName(entrynew.getTitle());
							i.setTimebegin(Time.valueOf(entrynew.getStartTime()));
							i.setTimeend(Time.valueOf(entrynew.getEndTime()));
							Date date = Date.from(entrynew.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
							i.setInteviewDate(date);
							proxy2.update(i);
						}else{
							Interview i = new Interview();
							i.setTimebegin(Time.valueOf(entrynew.getStartTime()));
							i.setTimeend(Time.valueOf(entrynew.getEndTime()));
							i.setInterviewType(condidate.getFirstName()+"."+condidate.getLastName()+jo.getId());
							Date date = Date.from(entrynew.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
							i.setInteviewDate(date);
							i.setAttempt(false);
							i.setCandidate(condidate);
							i.setJobOffer(jo);
							List<Interview> verif = new ArrayList();
							verif = proxy2.findAll();
							List<String> verifnom = new ArrayList();
							for (Interview interview1 : verif) {
								verifnom.add(interview1.getInterviewType());
							}
							if(verifnom.contains(i.getInterviewType())){
							}else{
								proxy2.addinterview(i);
								CandidateHasJobOffer candidateHasJobOffer = new CandidateHasJobOffer();
								candidateHasJobOffer = proxy.findByid(condidate, jo);
								candidateHasJobOffer.setApplicationStatus(false);
								candidateHasJobOffer.setNote(note1);
								proxy.update(candidateHasJobOffer);
								JobOfferPopUpController.j = jo;
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
								RowJobOfferHirringController.dialogSkill = new JFXDialog(RowJobOfferHirringController.condidate_see, dialogLayout, JFXDialog.DialogTransition.CENTER);
								RowJobOfferHirringController.dialogSkill.show();
							}
							
						}
					}
		        	for (Entry<?> entryold : listentrydebut) {
		        		Boolean test = false;
		        		for (Entry<?> entrynew : listentry) {
		        			if(entryold.getId().equals(entrynew.getId())){
		        				test = true;
		        				break;
		        			}
		        		}
		        		if(test==false){
		        			Interview i = new Interview();
		        			i = proxy2.findByName(entryold.getTitle());
		        			System.out.println(i.getInterviewType());
		        			proxy2.removeInterview(i);
		        		}
		        	}
				});
			});
			rejectedCodidatJobOfferBttn.setOnMouseClicked(e -> {
				CandidateHasJobOffer candidateHasJobOffer = new CandidateHasJobOffer();
				candidateHasJobOffer = proxy.findByid(condidate, jo);
				proxy.removeCandidateHasSkill(candidateHasJobOffer);
				JobOfferPopUpController.j = jo;
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
				RowJobOfferHirringController.dialogSkill = new JFXDialog(RowJobOfferHirringController.condidate_see, dialogLayout, JFXDialog.DialogTransition.CENTER);
				RowJobOfferHirringController.dialogSkill.show();
			});
			setText(null);
			setGraphic(rowCodidatJobOffer);
		}
	}

}
