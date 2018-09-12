/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddDetailSkillEmpController implements Initializable {

	@FXML
	private AnchorPane popupSkilDetails;
	@FXML
	private Spinner<Integer> yerasExp;
	@FXML
	private JFXCheckBox chekCertif;
	@FXML
	private JFXSlider levelExp;
	@FXML
	private ImageView errorEntitled;
	@FXML
	private ImageView errorType;
	@FXML
	private ImageView errorExperience;
	@FXML
	private JFXButton ConfirmAddBtn;
	@FXML
	private JFXButton addFromSkillBtn;
	@FXML
	private JFXButton AddNewSkillBtn;
	@FXML
	private JFXTextField areaEntitled;
	@FXML
	private JFXTextField areaType;
	@FXML
	private ImageView errorSkill;
	@FXML
	private JFXComboBox<String> comboSkills;

	int changeMode;
	List<Skill> skillsCombo = new ArrayList<>();
	List<Skill> skillsComboClean = new ArrayList<>();
	List<String> skillsComboString = new ArrayList<>();

	private ObservableList<String> obsSkills;

	private SkillServiceRemote proxySkill;
	private EmployeeHasSkillServiceRemote proxyEmpHasSkill;

	Set<Skill> tempAllSkills = new HashSet<>();

	public static AnchorPane refreshPa;
	// Set<EmployeeHasSkill> emphsSkil =
	// EmployeesGeneralListController.employee.getSpecificationsSkills();
	Set<EmployeeHasSkill> emphsSkil;
	List<Skill> skillsEmp = new ArrayList<>();

	Set<Skill> skillFinal = new HashSet<>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		areaEntitled.getStyleClass().clear();
		areaType.getStyleClass().clear();
		comboSkills.getStyleClass().clear();
		addFromSkillBtn.getStyleClass().clear();
		AddNewSkillBtn.getStyleClass().clear();
		levelExp.getStyleClass().clear();
		ConfirmAddBtn.getStyleClass().clear();
		// TODO

		SpinnerValueFactory<Integer> score = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		yerasExp.setValueFactory(score);

		tempAllSkills.clear();
		skillsCombo.clear();
		tempAllSkills.clear();
		skillsComboClean.clear();
		skillsComboString.clear();
		skillFinal.clear();
		// emphsSkil.clear();
	}

	@FXML
	private void OnActionConfirmAdd(ActionEvent event) {

		Boolean ok = true;
		if (changeMode == 1) {
			if (yerasExp.getValue() == null) {
				ok = false;
				errorExperience.setVisible(true);
			} else {
				errorExperience.setVisible(false);
			}
			if (comboSkills.getSelectionModel().getSelectedIndex() == -1) {
				ok = false;
				errorSkill.setVisible(true);
			} else {
				errorSkill.setVisible(false);
			}

			if (ok == true) {
				String jndiEmpHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
				proxyEmpHasSkill = (EmployeeHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiEmpHasSkill);

				Skill skill = skillsComboClean.get(comboSkills.getSelectionModel().getSelectedIndex());

				EmployeeHasSkillPk employeeHasSkillPk = new EmployeeHasSkillPk();
				employeeHasSkillPk.setIdSkill(skill.getId());
				employeeHasSkillPk.setIdEmployee(EmployeesGeneralListController.employee.getId());

				EmployeeHasSkill employeeHasSkill = new EmployeeHasSkill();
				employeeHasSkill.setEmployeeHasSkillPk(employeeHasSkillPk);
				// employeeHasSkill.setSkill(skill);
				// employeeHasSkill.setEmployee(EmployeesGeneralListController.employee);
				employeeHasSkill.setCertifcation(chekCertif.isSelected());
				employeeHasSkill.setLevel(yerasExp.getValue());
				employeeHasSkill.setSkillNote((float) levelExp.getValue());

				tempAllSkills.clear();
				skillsCombo.clear();
				tempAllSkills.clear();
				skillsComboClean.clear();
				skillsComboString.clear();
				skillFinal.clear();
				// emphsSkil.clear();

				proxyEmpHasSkill.save(employeeHasSkill);

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/profilePane.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				refreshPa.getChildren().clear();
				refreshPa.getChildren().add(child);

			}
		} else {

			Boolean ok2 = true;

			if (yerasExp.getValue() == null) {
				ok2 = false;
				errorExperience.setVisible(true);
			} else {
				errorExperience.setVisible(false);

			}
			if (areaEntitled.getText().equals("")) {
				ok2 = false;
				errorEntitled.setVisible(true);
			} else {
				errorEntitled.setVisible(false);
			}
			if (areaType.getText().equals("")) {
				ok2 = false;
				errorType.setVisible(true);
			} else {
				errorType.setVisible(false);
			}

			if (ok2 == true) {
				String jndiEmpHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
				proxyEmpHasSkill = (EmployeeHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiEmpHasSkill);
				String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";
				proxySkill = (SkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);

				Skill skill = new Skill();
				skill.setName(areaEntitled.getText());
				skill.setType(areaType.getText());

				int idSkill = proxySkill.saveReturn(skill);

				EmployeeHasSkillPk employeeHasSkillPk = new EmployeeHasSkillPk();
				employeeHasSkillPk.setIdEmployee(EmployeesGeneralListController.employee.getId());
				employeeHasSkillPk.setIdSkill(idSkill);

				EmployeeHasSkill employeeHasSkill = new EmployeeHasSkill();
				employeeHasSkill.setEmployeeHasSkillPk(employeeHasSkillPk);
				employeeHasSkill.setCertifcation(chekCertif.isSelected());
				employeeHasSkill.setLevel(yerasExp.getValue());
				employeeHasSkill.setSkillNote((float) levelExp.getValue());

				proxyEmpHasSkill.save(employeeHasSkill);

				Pane child = null;

				try {
					child = FXMLLoader.load(getClass().getResource("/views/profilePane.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				refreshPa.getChildren().clear();
				refreshPa.getChildren().add(child);

			}

		}
	}

	@FXML
	private void OnActionAddFromSkill(ActionEvent event) {
		changeMode = 1;

		tempAllSkills.clear();
		skillsCombo.clear();
		tempAllSkills.clear();
		skillsComboClean.clear();
		skillsComboString.clear();
		skillFinal.clear();
		// emphsSkil.clear();

		String jndiSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/SkillService!tn.esprit.b1.esprit1718b1hrboard.services.SkillServiceRemote";
		proxySkill = (SkillServiceRemote) EJBLookupUtil.doLookup(jndiSkill);

		List<Skill> skillsRequestEmp = proxySkill.findSkillsByEmployee(EmployeesGeneralListController.employee);

		System.out.println("/***********************Skills employee from request**************************/");

		for (Skill skill : skillsRequestEmp) {
			System.out.println(skill);
			skillsEmp.add(skill);

		}

		skillsCombo = proxySkill.findAll();

		System.out.println("/**************************************************/");

		// for (EmployeeHasSkill employeeHasSkill : emphsSkil) {
		// System.out.println(employeeHasSkill.getSkill());
		// skillsEmp.add(employeeHasSkill.getSkill());
		// }

		System.out.println("/**************************************************/");

		for (Skill skill : skillsCombo) {
			System.out.println(skill);
		}

		for (Skill skillCom : skillsCombo) {
			tempAllSkills.add(skillCom);
		}

		// for (Iterator<Skill> skIterator = tempAllSkills.iterator();
		// skIterator.hasNext();) {
		// for (Iterator<Skill> skIterator2 = skillsEmp.iterator();
		// skIterator2.hasNext();) {
		// if (skIterator.next().getId() == skIterator2.next().getId()) {
		// skIterator.remove();
		// }
		// }
		// }

		if (tempAllSkills.containsAll(skillsEmp)) {
			System.out.println("/*****************CONTAIN!!!!!!!!***************/");
			tempAllSkills.removeAll(skillsEmp);

		}

		System.out.println("/*****************AFTER REMOVE***************/");

		for (Skill skill : tempAllSkills) {
			System.out.println(skill);
		}

		for (Skill skill : tempAllSkills) {
			if (skill.getName() != null) {
				skillsComboClean.add(skill);
			}

		}

		for (Skill skill : skillsComboClean) {
			if (skill.getName() != null) {
				skillsComboString.add(skill.getName());
			}
		}
		obsSkills = FXCollections.observableArrayList();

		obsSkills.addAll(skillsComboString);

		comboSkills.setItems(obsSkills);

		comboSkills.setDisable(false);
		areaEntitled.setDisable(true);
		areaType.setDisable(true);

	}

	@FXML
	private void OnActionAddNewSkill(ActionEvent event) {

		changeMode = 2;
		areaEntitled.setDisable(false);
		areaType.setDisable(false);
		areaType.setText("");
		areaEntitled.setText("");
		comboSkills.setPromptText("Choose Skill");
		comboSkills.setDisable(true);
		tempAllSkills.clear();
		skillsCombo.clear();
		tempAllSkills.clear();
		skillsComboClean.clear();
		skillsComboString.clear();
		skillFinal.clear();
		// emphsSkil.clear();

	}

	@FXML
	private void OnActionSelectedSkill(ActionEvent event) {

		if (comboSkills.getSelectionModel().getSelectedIndex() != -1) {
			areaEntitled.setText(skillsComboClean.get(comboSkills.getSelectionModel().getSelectedIndex()).getName());
			areaType.setText(skillsComboClean.get(comboSkills.getSelectionModel().getSelectedIndex()).getType());
		}

	}

}
