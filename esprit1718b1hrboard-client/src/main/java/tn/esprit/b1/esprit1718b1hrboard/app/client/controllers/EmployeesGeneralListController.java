/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels.EmployeeTable;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployeesGeneralListController implements Initializable {

	@FXML
	private Label lblEmail;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblLocation;
	@FXML
	private ToggleGroup filter;
	@FXML
	private Label ManageEmpBtn;
	@FXML
	private AnchorPane EmployeeListPane;
	@FXML
	private TableView<EmployeeTable> tableEmployees;
	@FXML
	private TableColumn<EmployeeTable, String> idEmployee;
	@FXML
	private TableColumn<EmployeeTable, String> nameEmployee;
	@FXML
	private TableColumn<EmployeeTable, String> postEmployee;
	@FXML
	private TableColumn<EmployeeTable, String> departementEmployee;
	@FXML
	private AnchorPane fabPane;
	@FXML
	private Label lblNationality;
	@FXML
	private Label lblGrade;
	@FXML
	private Label lblWage;
	@FXML
	private Label lblWorkedHrs;
	@FXML
	private Label lblRequiredHrs;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private JFXRadioButton nameSearchRadio;
	@FXML
	private JFXRadioButton postSerchRadio;
	@FXML
	private JFXRadioButton departementSearchRadio;
	@FXML
	private JFXTextField critiriaSearch;

	EmployeeServiceRemote proxyEmployee;

	private ObservableList<EmployeeTable> list_employees;

	private FilteredList<EmployeeTable> filterData;

	private SortedList<EmployeeTable> sortedEmplyeesList;

	public static Employee employee;
	@FXML
	private Label lblProfil;
	@FXML
	private ImageView profilPic;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setRipples();
		populateEmployeeTableView();

		tableEmployees.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends EmployeeTable> observable, EmployeeTable oldValue,
						EmployeeTable newValue) -> {
					lblProfil.setVisible(true);
					ManageEmpBtn.setVisible(true);
					if (newValue == null) {
						return;
					}
					getEmployeeInfo(newValue.getId());
					// System.out.println(newValue.getId());

				});

	}

	@FXML
	private void OnActionMGEmployee(MouseEvent event) {

		/* Initialize the scene */

		ProfilePaneController.refreshPa = EmployeeListPane;
		Pane child;
		try {
			child = FXMLLoader.load(getClass().getResource("/views/EmployeeProfil.fxml"));
			EmployeeListPane.getChildren().clear();
			EmployeeListPane.getChildren().add(child);
		} catch (IOException e) {
		}

	}

	private void setRipples() {
		JFXRippler fXRippler = new JFXRippler(ManageEmpBtn);
		fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
		fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
		fabPane.getChildren().add(fXRippler);
	}

	private void populateEmployeeTableView() {

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);
		List<Employee> employeeList = new ArrayList<>();

		employeeList = proxyEmployee.findAll();
		// System.out.println(employeeList);
		list_employees = FXCollections.observableArrayList();

		for (Employee emp : employeeList) {
			String postName;
			String depName;
			String codeEmployee;
			String names = null;
			if (emp.getFirstName() != null && emp.getLastName() != null) {
				names = emp.getFirstName().toUpperCase() + " " + emp.getLastName().toUpperCase();
			} else if (emp.getFirstName() == null && emp.getLastName() != null) {
				names = emp.getLastName().toUpperCase();
			} else if (emp.getFirstName() != null && emp.getLastName() == null) {
				names = emp.getFirstName().toUpperCase();
			} else {
				names = "*********";
			}
			if (emp.getPost() == null) {
				postName = "*****";
				depName = "******";
			} else {
				postName = emp.getPost().getEntitled();
				depName = emp.getPost().getDepartement().getName();
			}
			if (emp.getCode() == null) {
				codeEmployee = "******";
			} else {
				codeEmployee = emp.getCode();
			}
			// if (emp.getPost().getDepartement().getName() == null) {
			// depName = "******";
			// } else {
			// depName = emp.getPost().getDepartement().getName();
			// }

			EmployeeTable employeeTable = new EmployeeTable(codeEmployee, names, postName, depName);
			System.out.println(employeeTable);
			list_employees.add(employeeTable);

		}

		idEmployee.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameEmployee.setCellValueFactory(new PropertyValueFactory<>("names"));
		postEmployee.setCellValueFactory(new PropertyValueFactory<>("posts"));
		departementEmployee.setCellValueFactory(new PropertyValueFactory<>("deps"));

		tableEmployees.getItems().clear();
		tableEmployees.getItems().addAll(list_employees);

		/* test the radio buttons */

		if (nameSearchRadio.isSelected()) {
		} else if (postSerchRadio.isSelected()) {
			System.out.println("post selected");
		} else {
			System.out.println("departement selected");
		}

	}

	/* SEARCH THE SELECTED EMPLOYEE */
	public void getEmployeeInfo(String code) {

		if (code == null || code.equals("******")) {
			return;
		}

		employee = proxyEmployee.findByCode(code);
		if (employee.getProfilPic() != null) {
			profilPic.setImage(new Image("http://localhost/ImagesHR/" + employee.getProfilPic()));

		}

		if (employee.getEmail() != null) {
			lblEmail.setText(employee.getEmail());
		} else {
			lblEmail.setText("******");

		}
		if (employee.getNationality() != null) {
			lblNationality.setText(employee.getNationality());
		} else {
			lblNationality.setText("******");
		}
		if (employee.getGrade() != null) {
			lblGrade.setText(employee.getGrade());
		} else {
			lblGrade.setText("****");
		}
		if (employee.getAddress() != null) {
			lblLocation.setText(employee.getAddress());
		} else {
			lblLocation.setText("*****");
		}
		if (employee.getFirstName() != null) {
			lblFirstName.setText(employee.getFirstName().toUpperCase());
		} else {
			lblFirstName.setText("*****");
		}
		if (employee.getLastName() != null) {
			lblLastName.setText(employee.getLastName().toUpperCase());
		} else {
			lblLastName.setText("*****");
		}
		/* TO Add a condition of the null fields */
		if (employee.getContract() != null) {
			if (employee.getContract().getHourPrice() != null) {
				lblWage.setText(employee.getContract().getHourPrice().toString());
			} else {
				lblWage.setText("*****");
			}

		} else {
			lblWage.setText("*****");
		}
		if (employee.getWorkedHours() != null) {
			lblWorkedHrs.setText(employee.getWorkedHours().toString());
		} else {
			lblWorkedHrs.setText("****");

		}
		if (employee.getContract() != null) {
			if (employee.getContract().getRequiredHoursNb() != null) {
				lblRequiredHrs.setText(employee.getContract().getRequiredHoursNb().toString());
			} else {
				lblRequiredHrs.setText("****");
			}
		} else {
			lblRequiredHrs.setText("******");
		}
		if (employee.getPhoneNumber() != null) {
			lblPhone.setText(employee.getPhoneNumber().toString());

		} else {
			lblPhone.setText("****");
		}

		// System.out.println(employee);

	}

	/* Seach emloyees with specified critiria */

	@FXML
	private void searchEmployees(KeyEvent event) {
		filterData = new FilteredList<>(list_employees, p -> true);

		critiriaSearch.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
			filterData.setPredicate(employee -> {

				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}

				String typedText = newvalue.toLowerCase();
				if (nameSearchRadio.isSelected()) {
					if (employee.getNames().toLowerCase().indexOf(typedText) != -1) {

						return true;
					}
				} else if (postSerchRadio.isSelected()) {
					if (employee.getPosts().toLowerCase().indexOf(typedText) != -1) {

						return true;
					}
				} else {
					if (employee.getDeps().toLowerCase().indexOf(typedText) != -1) {

						return true;
					}

				}

				return false;
			});
			sortedEmplyeesList = new SortedList<>(filterData);
			sortedEmplyeesList.comparatorProperty().bind(tableEmployees.comparatorProperty());
			tableEmployees.setItems(sortedEmplyeesList);

		});

	}

}
