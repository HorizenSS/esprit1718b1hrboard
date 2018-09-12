/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class RowSkillHasProjectController extends ListCell<ProjectHasSkill> {

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane ROWEmpID;
    @FXML
    private Label labelSkillId;
    @FXML
    private Label lebelLeveld;
    @FXML
    private Label labelExperienceId;
    public AnchorPane panerefresh;
	public StackPane a;
	private FXMLLoader mLLoader;
	
    /**
     * Initializes the controller class.
     */
	public RowSkillHasProjectController(StackPane pane, AnchorPane panerefr) {
		a = pane;
		panerefresh = panerefr;
	}
	@Override
	protected void updateItem(ProjectHasSkill projectSkill, boolean empty) {
		super.updateItem(projectSkill, empty);

		if (empty || projectSkill == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowSkillHasProject.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					System.out.println("ma7abech iloadi ");
				}

			}
			labelSkillId.setText(projectSkill.getSkill().getName());
			lebelLeveld.setText(projectSkill.getLevel().toString());
			labelExperienceId.setText(projectSkill.getExperience().toString());
			
			}
			
			
			setText(null);
			setGraphic(ROWEmpID);

		

	}
}
