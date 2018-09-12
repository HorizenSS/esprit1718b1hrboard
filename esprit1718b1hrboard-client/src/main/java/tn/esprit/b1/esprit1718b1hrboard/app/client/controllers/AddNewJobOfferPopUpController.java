/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

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
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Contract;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOfferHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddNewJobOfferPopUpController implements Initializable {

    @FXML
    private AnchorPane addNewJobOffer;
    @FXML
    private JFXTextField entitledNewJobOffer;
    @FXML
    private JFXTextField DescriptionNewJobOffer;
    @FXML
    private JFXRadioButton cdiNewJobOfferRadioBttn;
    @FXML
    private ToggleGroup type;
    @FXML
    private JFXRadioButton cddNewJobOfferRadioBttn;
    @FXML
    private JFXRadioButton trainshipNewJobOfferRadioBttn;
    @FXML
    private JFXComboBox<String> skill1NewJobOfferCheckbox;
    @FXML
    private JFXComboBox<String> skill2NewJobOfferCheckbox;
    @FXML
    private JFXComboBox<String> skill3NewJobOfferCheckbox;
    @FXML
    private Spinner<Integer> skill1NewJobOfferSpinBttn;
    @FXML
    private Spinner<Integer> skill3NewJobOfferSpinBttn;
    @FXML
    private Spinner<Integer> skill2NewJobOfferSpinBttn;
    private ObservableList<String> sk ;
    private ObservableList<String> sk1 ;
    private ObservableList<String> sk2 ;
    @FXML
    private JFXButton addJobOfferbttn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 5);
    	SpinnerValueFactory<Integer> score2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 5);
    	SpinnerValueFactory<Integer> score3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 5);
    	skill1NewJobOfferSpinBttn.setValueFactory(score);
    	skill2NewJobOfferSpinBttn.setValueFactory(score2);
    	skill3NewJobOfferSpinBttn.setValueFactory(score3);
    	skill1NewJobOfferCheckbox.setPromptText("Choose skill");
    	skill2NewJobOfferCheckbox.setPromptText("Choose skill");
    	skill3NewJobOfferCheckbox.setPromptText("Choose skill");
    	String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";
		SkillServiceRemote proxy = (SkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);
		String jndiJobOffer = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/JobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote";
		JobOfferServiceRemote proxyjoboffer = (JobOfferServiceRemote) EJBLookupUtil.doLookup(jndiJobOffer);
		String jndiJobOfferHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/JobOfferHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.JobOfferHasSkillServiceRemote";
		JobOfferHasSkillServiceRemote proxyjobofferHasSkill = (JobOfferHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiJobOfferHasSkill);
		List<String> skill = new ArrayList();
		skill = proxy.findAll().stream().map(e -> e.getName()).collect(Collectors.toList());
		sk = FXCollections.observableArrayList();
		sk1 = FXCollections.observableArrayList();
		sk2 = FXCollections.observableArrayList();
		sk.addAll(skill);
		sk1.addAll(skill);
		sk2.addAll(skill);
		skill1NewJobOfferCheckbox.setItems(sk);
		skill2NewJobOfferCheckbox.setItems(sk1);
		skill3NewJobOfferCheckbox.setItems(sk2);
		
		skill1NewJobOfferCheckbox.setOnAction(e -> {
			String s = skill1NewJobOfferCheckbox.getValue();
			sk1.remove(s);
			sk2.remove(s);
			for (String string : sk) {
				if((!sk1.contains(string))&&(!sk2.contains(string))&&(string!=s)){
					sk1.add(string);
					sk2.add(string);
				}
			}
			skill2NewJobOfferCheckbox.setItems(sk1);
			skill3NewJobOfferCheckbox.setItems(sk2);
		});
		skill2NewJobOfferCheckbox.setOnAction(e -> {
			String s = skill2NewJobOfferCheckbox.getValue();
			sk.remove(s);
			sk2.remove(s);
			for (String string : sk1) {
				if((!sk.contains(string))&&(!sk2.contains(string))&&(string!=s)){
					sk.add(string);
					sk2.add(string);
				}
			}
			skill1NewJobOfferCheckbox.setItems(sk);
			skill3NewJobOfferCheckbox.setItems(sk2);
		});
		skill3NewJobOfferCheckbox.setOnAction(e -> {
			String s = skill3NewJobOfferCheckbox.getValue();
			sk.remove(s);
			sk1.remove(s);
			for (String string : sk2) {
				if((!sk.contains(string))&&(!sk1.contains(string))&&(string!=s)){
					sk.add(string);
					sk1.add(string);
				}
			}
			skill1NewJobOfferCheckbox.setItems(sk);
			skill2NewJobOfferCheckbox.setItems(sk1);
		});
		addJobOfferbttn.setOnMouseClicked(e -> {
			System.out.println(skill1NewJobOfferCheckbox.getValue());
			if((entitledNewJobOffer.getText().equals(""))
					||(DescriptionNewJobOffer.getText().equals(""))
					||(skill1NewJobOfferCheckbox.getValue()==null)
					||(skill2NewJobOfferCheckbox.getValue()==null)
					||(skill3NewJobOfferCheckbox.getValue()==null)){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Check Fields");
				alert.setContentText("There Some Fields Empty");
				alert.show();
			}else{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION");
				alert.setHeaderText("Save Confiramtion");
				alert.setContentText("Do You Want To Add a New Job Offer");

				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						JobOffer jo = new JobOffer();
						jo.setDescription(DescriptionNewJobOffer.getText());
						jo.setEntitled(entitledNewJobOffer.getText());
						Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
						jo.setOfferDate(date);
						String typecontract="";
						if(cdiNewJobOfferRadioBttn.isSelected()){
							typecontract = cdiNewJobOfferRadioBttn.getText();
						}else if (cddNewJobOfferRadioBttn.isSelected()){
							typecontract = cddNewJobOfferRadioBttn.getText();
						}else if (trainshipNewJobOfferRadioBttn.isSelected()){
							typecontract = trainshipNewJobOfferRadioBttn.getText();
						}
						jo.setOfferType(typecontract);
						proxyjoboffer.save(jo);
						
						///////////////////////////////////get the job offer
						JobOffer jo1 = new JobOffer();
						jo1 = proxyjoboffer.JobOfferByEntitled(jo.getEntitled());
						
						
						////////////////////////////////////////////get skills///////
						String comboskill1,comboskill2,comboskill3;
						Skill skill1,skill2,skill3;
						comboskill1 = skill1NewJobOfferCheckbox.getValue();
						comboskill2 = skill2NewJobOfferCheckbox.getValue();
						comboskill3 = skill3NewJobOfferCheckbox.getValue();
						skill1 = proxy.SkillByname(comboskill1);
						skill2 = proxy.SkillByname(comboskill2);
						skill3 = proxy.SkillByname(comboskill3);
						////////////////////////////////////adding JobOfferHasSkillPk
						JobOfferHasSkillPk hasSkillPk1 = new JobOfferHasSkillPk();
						JobOfferHasSkillPk hasSkillPk2 = new JobOfferHasSkillPk();
						JobOfferHasSkillPk hasSkillPk3 = new JobOfferHasSkillPk();
						hasSkillPk1.setIdJobOffer(jo1.getId());
						hasSkillPk1.setIdSkill(skill1.getId());
						hasSkillPk2.setIdJobOffer(jo1.getId());
						hasSkillPk2.setIdSkill(skill2.getId());
						hasSkillPk3.setIdJobOffer(jo1.getId());
						hasSkillPk3.setIdSkill(skill3.getId());
						/////////////////////////////////////Adding JobOfferHasSkill
						JobOfferHasSkill johs = new JobOfferHasSkill();
						JobOfferHasSkill johs2 = new JobOfferHasSkill();
						JobOfferHasSkill johs3 = new JobOfferHasSkill();
						
						johs.setJobOfferHasSkillPk(hasSkillPk1);
						johs.setLevel(skill1NewJobOfferSpinBttn.getValue());
						
						johs2.setJobOfferHasSkillPk(hasSkillPk2);
						johs2.setLevel(skill2NewJobOfferSpinBttn.getValue());
						
						johs3.setJobOfferHasSkillPk(hasSkillPk3);
						johs3.setLevel(skill3NewJobOfferSpinBttn.getValue());
						
						proxyjobofferHasSkill.save(johs);
						proxyjobofferHasSkill.save(johs2);
						proxyjobofferHasSkill.save(johs3);
						
						Pane newpane;
				        try {
				            newpane = FXMLLoader.load(getClass().getResource("/views/JobOfferHirring.fxml"));
				            HirringController.pane_hirring.getChildren().add(newpane);
				        } catch (IOException ex) {
				            Logger.getLogger(HirringController.class.getName()).log(Level.SEVERE, null, ex);
				        }
				        JobOfferHirringController.dialogSkill.close();
					}
				});

			}
			
						
		});
    }    

    
}
