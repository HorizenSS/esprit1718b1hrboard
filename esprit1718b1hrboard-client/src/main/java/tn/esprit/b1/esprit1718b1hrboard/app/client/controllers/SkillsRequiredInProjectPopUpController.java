/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SkillsRequiredInProjectPopUpController implements Initializable {

    @FXML
	private JFXListView<Skill> skillsProjectListview;
    @FXML
	private JFXButton confirmSkillsBtn;

	private SkillServiceRemote proxySkill;
	private ProjectServiceRemote proxyProject;

	public ObservableList<Skill> skillsObsList;

	List<Skill> skillslist = new ArrayList<>();
	public static Set<ProjectHasSkill> skillsInProject = new HashSet<>();

	private ProjectHasSkillServiceRemote proxyProjecthasSkill;
    @FXML
    private AnchorPane skillsProjectPane;
    
	public static AnchorPane refreshPa;
	


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		confirmSkillsBtn.getStyleClass().clear();
		displayAllSkills();
	}

	public void displayAllSkills() {

//		System.out.println("*********************List of skill in project*********************** ");
//		System.out.println(ProjectRowController.selectedProject.getSkillsInProject());
		
		System.out.println(ProjectRowController.selectedProject);

		skillsInProject = ProjectRowController.selectedProject.getSkillsInProject();

		String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";

		proxySkill = (SkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);

		skillslist = proxySkill.findAll();

		skillsObsList = FXCollections.observableArrayList(skillslist);
		skillsProjectListview.setItems(skillsObsList);

		skillsProjectListview.setCellFactory(skillEmployeeLv -> new SkillProjetcRowController());
	}

    @FXML
	private void OnActionConfirmSkills(ActionEvent event) {

		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

		String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectHasSkillServiceRemote";

		proxyProjecthasSkill = (ProjectHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);

		proxyProjecthasSkill.removeByProject(ProjectRowController.selectedProject);
//
//		ProjectRowController.selectedProject.setSkillsInProject(skillsInProject);

		System.out.println("LIST FINALE UPDATE");
		System.out.println(ProjectRowController.selectedProject);
		//System.out.println(ProjectRowController.selectedProject.getSkillsInProject());

		for (ProjectHasSkill projectHasSkill : skillsInProject) {
			System.out.println(projectHasSkill);
			proxyProjecthasSkill.save(projectHasSkill);

		}

		System.out.println("UPDATE PROJECT DONE !!");
		
		Pane child = null;

		try {
			child = FXMLLoader.load(getClass().getResource("/views/ListProjects.fxml"));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		refreshPa.getChildren().clear();
		refreshPa.getChildren().add(child);

		ProjectRowController.dialog.close();

	}


}
