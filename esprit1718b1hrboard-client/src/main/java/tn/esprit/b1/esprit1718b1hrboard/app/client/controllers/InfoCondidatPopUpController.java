/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Candidate;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InfoCondidatPopUpController implements Initializable {

    @FXML
    private AnchorPane infoCondidatpane;
    @FXML
    private Label adresseCondidate;
    @FXML
    private Label lastnameCondidate;
    @FXML
    private Label emailCondidate;
    @FXML
    private Label skill1Condidate;
    @FXML
    private Label numberCondidate;
    @FXML
    private Label firstnameCondidate;
    @FXML
    private Label expirienceCondidate;
    @FXML
    private Label DiplomaCondidate;
    @FXML
    private Label skill2Condidate;
    @FXML
    private Label skill3Condidate;
    public static Candidate c ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> skillname = new ArrayList();
        Set<String> sk = new HashSet();
        firstnameCondidate.setText(c.getFirstName());
        lastnameCondidate.setText(c.getLastName());
        adresseCondidate.setText(c.getAddress());
        numberCondidate.setText(c.getPhoneNumber().toString());
        emailCondidate.setText(c.getEmail());
        expirienceCondidate.setText(c.getWorkExperiences());
        DiplomaCondidate.setText(c.getDiplomas());
        System.out.println(c.getSpecificationsSkillsCand());
        sk = c.getSpecificationsSkillsCand().stream().sorted((e1,e2) -> e1.getLevel() - e2.getLevel()).map(e -> e.getSkill().getName()).collect(Collectors.toSet());
        skillname = sk .stream().collect(Collectors.toList());
        System.out.println(skillname);
        skill1Condidate.setText(skillname.get(0));
        skill2Condidate.setText(skillname.get(1));
        skill3Condidate.setText(skillname.get(2));
    }    
    
}
