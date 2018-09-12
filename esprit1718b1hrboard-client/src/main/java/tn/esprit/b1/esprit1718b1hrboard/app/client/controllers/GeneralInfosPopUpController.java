/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.Validation;
import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementService;
import tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.PostService;
import tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GeneralInfosPopUpController implements Initializable {

	@FXML
	private JFXButton confirmUpEmployeeBtn;
	@FXML
	private Label lblAddress;
	@FXML
	private JFXTextField areaAdress;
	@FXML
	private Label lblDepartement;
	@FXML
	private JFXComboBox<String> comboDep;
	@FXML
	private Label lblPost;
	@FXML
	private JFXComboBox<String> comboPost;
	@FXML
	private Label lblGrade;
	@FXML
	private JFXTextField areaGrade;
	@FXML
	private Label lblEmail;
	@FXML
	private JFXTextField areaEmail;
	@FXML
	private Label lblPhone;
	@FXML
	private JFXTextField areaPhone;
	@FXML
	private Label lblGender;
	@FXML
	private JFXComboBox<String> comboGender;
	@FXML
	private Label lblBirthDate;
	@FXML
	private JFXDatePicker birthDate;
	@FXML
	private Label lblNationality;
	@FXML
	private JFXTextField areaNationality;

	private Employee employeeUp = EmployeesGeneralListController.employee;
	@FXML
	private Label lblAddress1;
	@FXML
	private ImageView errorFirstName;
	@FXML
	private Label lblAddress11;
	@FXML
	private ImageView errorLastName;
	@FXML
	private ImageView errorAdress;
	@FXML
	private ImageView errorGrade;
	@FXML
	private ImageView errorEmali;
	@FXML
	private ImageView errorPhone;
	@FXML
	private ImageView errorBirth;
	@FXML
	private ImageView errorNationality;
	@FXML
	private JFXTextField areaFirstName;
	@FXML
	private JFXTextField areaLastName;

	private DepartementServiceRemote proxyDep;
	private PostServiceRemote proxyPost;
	private EmployeeServiceRemote proxyEmployee;
	private ObservableList<String> obsGenders;
	private ObservableList<String> obsPosts;
	private ObservableList<String> obsDeps;

	private List<String> genders = new ArrayList<>();
	private List<String> depsName = new ArrayList<>();
	private List<String> postName = new ArrayList<>();

	private List<Departement> depsList = new ArrayList<>();
	private List<Post> postList = new ArrayList<>();

	public static AnchorPane refreshPa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		confirmUpEmployeeBtn.getStyleClass().clear();
		areaAdress.getStyleClass().clear();
		areaEmail.getStyleClass().clear();
		areaGrade.getStyleClass().clear();
		areaNationality.getStyleClass().clear();
		areaPhone.getStyleClass().clear();
		birthDate.getStyleClass().clear();
		areaFirstName.getStyleClass().clear();
		areaLastName.getStyleClass().clear();
		populateEmployeeInfos();

		// TODO
	}

	public void populateEmployeeInfos() {
		genders.add("MALE");
		genders.add("FEMALE");
		obsGenders = FXCollections.observableArrayList();

		obsGenders.addAll(genders);
		comboGender.setItems(obsGenders);

		if (employeeUp.getFirstName() != null) {
			areaFirstName.setText(employeeUp.getFirstName());
		} else {
			areaFirstName.setText("");

		}
		if (employeeUp.getLastName() != null) {
			areaLastName.setText(employeeUp.getLastName());

		} else {
			areaLastName.setText("");
		}
		if (employeeUp.getAddress() != null) {
			areaAdress.setText(employeeUp.getAddress());
		} else {
			areaAdress.setText("");
		}
		if (employeeUp.getEmail() != null) {
			areaEmail.setText(employeeUp.getEmail());
		} else {
			areaEmail.setText("");
		}
		if (employeeUp.getPhoneNumber() != null) {
			areaPhone.setText(employeeUp.getPhoneNumber().toString());
		} else {
			areaPhone.setText("");
		}
		if (employeeUp.getNationality() != null) {
			areaNationality.setText(employeeUp.getNationality());
		} else {
			areaNationality.setText("");
		}
		if (employeeUp.getGrade() != null) {
			areaGrade.setText(employeeUp.getGrade());
		} else {
			areaGrade.setText("");
		}
		if (employeeUp.getBirthDate() != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(employeeUp.getBirthDate());
			LocalDate dateBirth = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH));
			birthDate.setValue(dateBirth);
		} else {
			birthDate.setValue(null);
		}
		if (employeeUp.getGender() != null) {
			comboGender.setPromptText(employeeUp.getGender().toUpperCase());
		} else {
			comboGender.setPromptText("SELECT GENDER");
		}

		if (employeeUp.getPost() != null) {
			if (employeeUp.getPost().getEntitled() != null) {
				comboPost.setPromptText(employeeUp.getPost().getEntitled().toUpperCase());
			}
			if (employeeUp.getPost().getDepartement() != null) {
				populateComboDeps();
				polutateComboPost();
				if (employeeUp.getPost().getDepartement().getName() != null) {
					comboDep.setPromptText(employeeUp.getPost().getDepartement().getName());
				}
			} else {
				comboDep.setPromptText("SELECT ONE");
				populateComboDeps();
			}

		} else {
			comboPost.setPromptText("SELECT ONE");
			populateComboDeps();
		}

		comboDep.setOnAction(ev -> {
			if (comboDep.getSelectionModel().getSelectedIndex() != -1) {
				String jndiPost = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
				proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndiPost);

				postList = proxyPost.findPostsByDep(depsList.get(comboDep.getSelectionModel().getSelectedIndex()));

				postName.clear();
				for (Post post : postList) {
					postName.add(post.getEntitled().toUpperCase());
				}

				obsPosts = FXCollections.observableArrayList();
				obsPosts.addAll(postName);
				comboPost.setItems(obsPosts);
				comboPost.setPromptText("SELECT ONE");

			}

		});
	}

	public void populateComboDeps() {
		String jndiDep = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/DepartementService!tn.esprit.b1.esprit1718b1hrboard.services.DepartementServiceRemote";
		proxyDep = (DepartementServiceRemote) EJBLookupUtil.doLookup(jndiDep);

		depsList = proxyDep.findAll();
		for (Departement departement : depsList) {
			depsName.add(departement.getName().toUpperCase());
		}
		obsDeps = FXCollections.observableArrayList();
		obsDeps.addAll(depsName);
		comboDep.setItems(obsDeps);

	}

	public void polutateComboPost() {
		String jndiPost = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/PostService!tn.esprit.b1.esprit1718b1hrboard.services.PostServiceRemote";
		proxyPost = (PostServiceRemote) EJBLookupUtil.doLookup(jndiPost);

		postList = proxyPost.findPostsByDep(employeeUp.getPost().getDepartement());

		for (Post post : postList) {
			postName.add(post.getEntitled().toUpperCase());
		}

		obsPosts = FXCollections.observableArrayList();
		obsPosts.addAll(postName);
		comboPost.setItems(obsPosts);

	}

	@FXML
	private void OnActionConfirmUpEmployee(ActionEvent event) {

		Boolean ok = true;

		if (areaAdress.getText().equals("")) {
			errorAdress.setVisible(true);
			ok = false;
		} else {
			errorAdress.setVisible(false);

		}
		if (areaFirstName.getText().equals("") || Validation.containsAlphabetOnly(areaFirstName.getText().toString()) == false) {
			errorFirstName.setVisible(true);
			ok = false;
		} else {
			errorFirstName.setVisible(false);
		}
		if (areaLastName.getText().equals("")||Validation.containsAlphabetOnly(areaLastName.getText().toString()) ==false) {
			errorLastName.setVisible(true);
			ok = false;
		} else {
			errorLastName.setVisible(false);
		}

		if (areaEmail.getText().equals("")||Validation.test_email(areaEmail.getText().toString())==false) {
			errorEmali.setVisible(true);
			ok = false;
		} else {
			errorEmali.setVisible(false);
		}
		if (areaGrade.getText().equals("")) {
			errorGrade.setVisible(true);
			ok = false;
		} else {
			errorGrade.setVisible(false);
		}
		if (areaNationality.getText().equals("")) {
			errorNationality.setVisible(true);
			ok = false;
		} else {
			errorNationality.setVisible(false);
		}
		if (areaPhone.getText().equals("")||Validation.isIntConvertible(areaPhone.getText().toString())==false) {
			errorPhone.setVisible(true);
			ok = false;
		} else {
			errorPhone.setVisible(false);
		}
		if (birthDate.getValue() == null) {
			errorBirth.setVisible(true);
			ok = false;
		} else {
			errorBirth.setVisible(false);
		}

		if (comboGender.getSelectionModel().getSelectedIndex() != -1) {
			employeeUp.setGender(comboGender.getSelectionModel().getSelectedItem());
		}
		if (comboPost.getSelectionModel().getSelectedIndex() != -1) {
			employeeUp.setPost(postList.get(comboPost.getSelectionModel().getSelectedIndex()));
		}

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);

		if (ok == true) {
			employeeUp.setFirstName(areaFirstName.getText());
			employeeUp.setLastName(areaLastName.getText());
			employeeUp.setAddress(areaAdress.getText());
			employeeUp.setEmail(areaEmail.getText());
			employeeUp.setNationality(areaNationality.getText());
			Long ph = Long.parseLong(areaPhone.getText());
			employeeUp.setPhoneNumber(ph);
			employeeUp.setGrade(areaGrade.getText());
			employeeUp.setBirthDate(Date.valueOf(birthDate.getValue()));

			proxyEmployee.update(employeeUp);

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

		// ProfilePaneController.dialog.close();
	}

}
