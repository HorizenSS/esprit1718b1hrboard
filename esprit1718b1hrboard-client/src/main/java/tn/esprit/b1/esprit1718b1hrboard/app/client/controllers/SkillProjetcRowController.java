/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSlider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SkillProjetcRowController extends ListCell<Skill> {

	@FXML
	private Label lblskillName;
	@FXML
	private Label lblSkillType;
	@FXML
	private JFXCheckBox checkSkillProject;
	@FXML
	private AnchorPane skillRow;
	@FXML
	private JFXSlider slideSkillLevel;

	private FXMLLoader mLLoader;

	private ProjectHasSkillServiceRemote proxyProjecthasSkill;
	ProjectHasSkill projectHasSkill;

	public SkillProjetcRowController() {
	}

	@Override
	protected void updateItem(Skill skill, boolean empty) {
		super.updateItem(skill, empty);
		if (empty || skill == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/SkillProjetcRow.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// slideSkillLevel.getStyleClass().clear();
			lblskillName.setText(skill.getName());
			lblSkillType.setText(skill.getType());
			slideSkillLevel.setValue(0.5);
			checkSkillProject.setSelected(false);

			if (SkillsRequiredInProjectPopUpController.skillsInProject.isEmpty()) {
				lblskillName.setText(skill.getName());
				lblSkillType.setText(skill.getType());
				slideSkillLevel.setValue(0.5);
				checkSkillProject.setSelected(false);

			} else {
				for (ProjectHasSkill projectHasSkill : SkillsRequiredInProjectPopUpController.skillsInProject) {

					if (projectHasSkill.getProjectHasSkillPk().getIdSkill() == skill.getId()) {
//						System.out.println("SKILL IN ROW !!!!!");
//						System.out.println(skill);
//						System.out.println("SKILL IN PROJECT !!!!!");
//						System.out.println(projectHasSkill.getSkill());
						lblskillName.setText(skill.getName());
						lblSkillType.setText(skill.getType());
						slideSkillLevel.setValue(projectHasSkill.getLevel());
						checkSkillProject.setSelected(true);
						break;
					}

				}

			}

//			System.out.println("****************BEFOR CHECK REMOVE******************");
//			System.out.println(SkillsRequiredInProjectPopUpController.skillsInProject);

			ProjectHasSkillPk projectHasSkillPk = new ProjectHasSkillPk();
			projectHasSkillPk.setIdSkill(skill.getId());
			projectHasSkillPk.setIdProject(ProjectRowController.selectedProject.getId());
			projectHasSkill = new ProjectHasSkill();
			projectHasSkill.setProjectHasSkillPk(projectHasSkillPk);
			checkSkillProject.setOnAction(e -> {
				if (checkSkillProject.isSelected()) {
					Float level = (float) slideSkillLevel.getValue();
					projectHasSkill.setLevel(level);
//					System.out.println("chekSkill selected !!!!");
					SkillsRequiredInProjectPopUpController.skillsInProject.add(projectHasSkill);

				} else {
//					System.out.println("checkSkill NOT selected !!");

					SkillsRequiredInProjectPopUpController.skillsInProject.remove(projectHasSkill);
					// System.out.println(projectHasSkillRm);

//					System.out.println("****************AFTER CHECK REMOVE******************");
//					System.out.println(SkillsRequiredInProjectPopUpController.skillsInProject);
					// System.out.println(tempTest);
				}

			});

			slideSkillLevel.setOnMouseReleased(ev -> {
//				System.out.println("/**********VALUE OF SLIDER : " + slideSkillLevel.getValue());
				if (checkSkillProject.isSelected()) {
					SkillsRequiredInProjectPopUpController.skillsInProject.remove(projectHasSkill);
					projectHasSkill.setLevel((float) slideSkillLevel.getValue());
					SkillsRequiredInProjectPopUpController.skillsInProject.add(projectHasSkill);

				}

			});

			setText(null);
			setGraphic(skillRow);

		}
	}
}
