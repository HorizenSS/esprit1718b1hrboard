/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;
import tn.esprit.b1.esprit1718b1hrboard.entities.CandidateHasJobOffer;
import tn.esprit.b1.esprit1718b1hrboard.entities.JobOffer;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.CandidateServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.JobOfferServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class JobOfferPopUpController implements Initializable {

    @FXML
    private AnchorPane popup_liste_jobOffer;
    @FXML
    private ListView<Candidate> condidate_list_popup_jobOffer;
    public static JobOffer j = new JobOffer();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String jndiCandidateHasJobOffer ="esprit1718b1hrboard-ear/esprit1718b1hrboard-service/CandidateHasJobOfferService!tn.esprit.b1.esprit1718b1hrboard.services.CandidateHasJobOfferServiceRemote";
        try {
        	CandidateHasJobOfferServiceRemote proxy = (CandidateHasJobOfferServiceRemote) EJBLookupUtil.doLookup(jndiCandidateHasJobOffer);
        	List<CandidateHasJobOffer> lc = new ArrayList();
        	List<Candidate> lc2 = new ArrayList();
        	lc = proxy.findAll();
        	for (CandidateHasJobOffer candidate : lc) {
        		if((candidate.getJobOffer().getId()==j.getId())&&(candidate.getApplicationStatus()==true)){
        			lc2.add(candidate.getCandidate());
        		}
			}
			ObservableList<Candidate> names = FXCollections.observableArrayList(lc2);
			condidate_list_popup_jobOffer.setItems(names);
			condidate_list_popup_jobOffer.setCellFactory(JobOfferListView -> new RowCodidateJobOfferController());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("erreur getall offerjob ou affichage des celulles de la liste");
		}
    	
    }    
    
}
