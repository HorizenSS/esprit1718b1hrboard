/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Contract;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Interview;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasSkillSeviceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkill;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HirringInterviewPopUpController implements Initializable {

	@FXML
	private JFXButton hireInterviewBttn;
	@FXML
	private Spinner<Integer> contractDurationHirringInterviewSpinBttn;
	@FXML
	private Spinner<Integer> scoreAccordedHirringInterviewSpinBttn;
	@FXML
	private JFXTextArea notehirringHirringInterview;
	@FXML
	private Spinner<Integer> hoursworkHirringInterviewSpinBttn;
	@FXML
	private JFXTextField hourPriceHirringInterview;
	public static Candidate condidat;
	public static Interview intervieww;
	

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		SpinnerValueFactory<Integer> hoursworked = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 60, 40);
		SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 5);
		SpinnerValueFactory<Integer> contractDuration = new SpinnerValueFactory.IntegerSpinnerValueFactory(6, 60, 12);
		hoursworkHirringInterviewSpinBttn.setValueFactory(hoursworked);
		scoreAccordedHirringInterviewSpinBttn.setValueFactory(score);
		contractDurationHirringInterviewSpinBttn.setValueFactory(contractDuration);

		hireInterviewBttn.setOnMouseClicked(hire -> {
			Boolean testfloat=true;
			try{
				Float.parseFloat(hourPriceHirringInterview.getText());
			}catch(NumberFormatException e){
				testfloat=false;
			}
			if(hourPriceHirringInterview.getText().equals("")
					||(notehirringHirringInterview.getText().equals(""))
					||!testfloat){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Check Fields");
				alert.setContentText("There Some Fields Empty Or Filded Wrong");
				alert.show();
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Save Confiramtion");
				alert.setContentText("Do You Want To Hire This Condidate");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {

						////////////////////////////////////////////////////////////////////////////////////////////
						//////////////////////////////// Creation Du
						//////////////////////////////////////////////////////////////////////////////////////////// Contract///////////////////////////////////////
						Contract c = new Contract();
						LocalDate d = LocalDate.now().plusMonths(contractDurationHirringInterviewSpinBttn.getValue());
						Date datefincontract = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
						c.setHourPrice(Double.parseDouble(hourPriceHirringInterview.getText()));
						c.setRequiredHoursNb(hoursworkHirringInterviewSpinBttn.getValue());
						c.setEndDate(datefincontract);
						c.setType(intervieww.getJobOffer().getOfferType());
						////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////// Creation d'un
						//////////////////////////////////////////////////////////////////////////////////////////// employee//////////////////////////////////////
						Employee e = new Employee();
						e.setFirstName(condidat.getFirstName());
						e.setLastName(condidat.getLastName());
						e.setAddress(condidat.getAddress());
						e.setEmail(condidat.getEmail());
						e.setPhoneNumber(condidat.getPhoneNumber());
						LocalDate d2 = LocalDate.now();
						Date datestartcontract = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
						e.setStartDay(datestartcontract);
						e.setEndDay(datefincontract);
						e.setContract(c);
						Year year = Year.now();
						e.setCode("ReplaceMe"+condidat.getFirstName()+condidat.getLastName());
						e.setAppreciationMonth(Float.valueOf(score.getValue()));
						String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
						EmployeeServiceRemote proxy = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
						proxy.save(e);
						e = proxy.findbymail(e.getEmail());
						String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
						EmployeeHasSkillServiceRemote proxy2 = (EmployeeHasSkillServiceRemote) EJBLookupUtil
								.doLookup(jndiEmployeeHasSkill);
						Set<CandidateHasSkill> skillscondidat = condidat.getSpecificationsSkillsCand().stream().collect(Collectors.toSet());
						for (CandidateHasSkill condidatskill : skillscondidat) {
							EmployeeHasSkill es = new EmployeeHasSkill();
							EmployeeHasSkillPk esp = new EmployeeHasSkillPk();
							esp.setIdEmployee(e.getId());
							esp.setIdSkill(condidatskill.getSkill().getId());
							es.setEmployeeHasSkillPk(esp);
							es.setCertifcation(condidatskill.getCertifcation());
							es.setLevel(condidatskill.getLevel());
							es.setSkill(condidatskill.getSkill());
							es.setSkillNote(Float.valueOf(condidatskill.getSkillExperience()));
							proxy2.save(es);
						}
						
						
						String jndiCondidatehasjoboffer = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasJobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote";
						CandidateHasJobOfferServiceRemote proxy4 = (CandidateHasJobOfferServiceRemote) EJBLookupUtil.doLookup(jndiCondidatehasjoboffer);
						List<CandidateHasJobOffer> chjo =new ArrayList();
						chjo = proxy4.findAll();
						for (CandidateHasJobOffer candidateHasJobOffer : chjo) {
							if(candidateHasJobOffer.getCandidate().getId()==intervieww.getCandidate().getId()){
								proxy4.removeCandidateHasSkill(candidateHasJobOffer);
							}
						}
						
						
						String jndiInterview = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/InterviewService!tn.esprit.b1.esprit1718b1hrboard.services.InterviewServiceRemote";
						InterviewServiceRemote proxy5 = (InterviewServiceRemote) EJBLookupUtil.doLookup(jndiInterview);
						proxy5.removeInterview(intervieww);
						
						
						String jndiCondidate = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote";
						CandidateServiceRemote proxy3 = (CandidateServiceRemote) EJBLookupUtil.doLookup(jndiCondidate);
						proxy3.delete(condidat);
						
						
						
						System.out.println("hirringggggggg done ");
						RowCondidatInterviewController.dialogSkill.close();
						Pane newpane;
				        try {
				            newpane = FXMLLoader.load(getClass().getResource("/views/InterviewHirring.fxml"));
				            HirringController.pane_hirring.getChildren().add(newpane);
				        } catch (IOException ex) {
				            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
				        }
					}

				});
			}
			
		});
	}

}
