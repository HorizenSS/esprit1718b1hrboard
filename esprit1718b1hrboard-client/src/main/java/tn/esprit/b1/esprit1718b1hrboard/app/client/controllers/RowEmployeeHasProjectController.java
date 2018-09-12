/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Majdi Rabie
 */
public class RowEmployeeHasProjectController extends ListCell<Employee> {

	@FXML
	private StackPane stackPane;
	@FXML
	private AnchorPane ROWEmpID;
	@FXML
	private ImageView ImgId;
	@FXML
	private Label labelNameId;
	@FXML
	private Label postID;
	@FXML
	private Label emailID;
	@FXML
	private Label skillNameId2;

	@FXML
	private Label skillNameId;

	@FXML
	private Label lebelSkillId;

	@FXML
	private Pane paneId;

	@FXML
	private Label levelSkillId2;
	@FXML
	private JFXCheckBox checkboxEmployeId;

	private FXMLLoader mLLoader;
	public static Project selectedProject;
	public AnchorPane panerefresh;
	public StackPane a;
	public static JFXDialog dialogEmployee;
	public static List<Employee> employeeslected = new ArrayList<>();
	/**
	 * Initializes the controller class.
	 */
	EmployeeServiceRemote proxyEmployee;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";

	public RowEmployeeHasProjectController(StackPane pane, AnchorPane panerefr) {
		a = pane;
		panerefresh = panerefr;
	}

	@Override
	protected void updateItem(Employee employee, boolean empty) {
		super.updateItem(employee, empty);

		if (empty || employee == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/RowEmployeeHasProject.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					System.out.println("ma7abech iloadi ");
				}

			}
			labelNameId.setText(employee.getFirstName() + " " + employee.getLastName());
			postID.setText(employee.getPost().getEntitled());
			emailID.setText(employee.getEmail());
			List<Float> i = new ArrayList();
			List<String> n = new ArrayList();
			Set<EmployeeHasSkill> l = new HashSet<>();
			l = employee.getSpecificationsSkills();
			Set<ProjectHasSkill> prhs = TrainingProjectController.projectSelected.getSkillsInProject();
			System.out.println(TrainingProjectController.projectSelected.getSkillsInProject());
			for (ProjectHasSkill projectHasSkill : prhs) {
				for (EmployeeHasSkill employeeHasSkill : l) {
					if (employeeHasSkill.getSkill().getName().equals(projectHasSkill.getSkill().getName())) {
						i.add(employeeHasSkill.getSkillNote());
						n.add(employeeHasSkill.getSkill().getName());
					}
				}
			}
			for (String aa : n) {
				System.out.println(aa);
			}
			for (Float aa : i) {
				System.out.println(aa);
			}

			if (i.get(0) != null) {

				lebelSkillId.setText(new DecimalFormat("#.#").format(i.get(0)));
				skillNameId.setText(n.get(0));

				Set<ProjectHasSkill> phs = TrainingProjectController.projectSelected.getSkillsInProject();

				for (ProjectHasSkill ppp : phs) {

					if (ppp.getLevel() > i.get(0) && ppp.getSkill().getName().equals(n.get(0))) {

						paneId.setStyle("-fx-background-color: #F63F76;");
						lebelSkillId.setTextFill(Color.BLACK);

					}
				}
			} else {
				lebelSkillId.setText("hasn't");
				skillNameId.setText("***");
				levelSkillId2.setText("hasn't");
				skillNameId2.setText("***");
			}
			if (!(i.size() < 2)) {

				levelSkillId2.setText(new DecimalFormat("#.#").format(i.get(1)));
				skillNameId2.setText(n.get(1));
				System.out.println(TrainingProjectController.projectSelected.getSkillsInProject());
				Set<ProjectHasSkill> phs = TrainingProjectController.projectSelected.getSkillsInProject();

				for (ProjectHasSkill ppp : phs) {

					if (ppp.getLevel() > i.get(1) && ppp.getSkill().getName().equals(n.get(1))) {

						paneId.setStyle("-fx-background-color: #F63F76;");
						levelSkillId2.setTextFill(Color.BLACK);
					} else {
					}
				}
			} else {

				levelSkillId2.setText("hasn't");
				skillNameId2.setText("***");
			}
			setOnMouseClicked(e -> {
				TrainingProjectController.empSelected = employee;
				System.out.println(TrainingProjectController.empSelected.getFirstName());
				Pane child = null;
				try {
					child = FXMLLoader.load(getClass().getResource("/views/AddTrainingProjectWithListEmployee.fxml"));
				} catch (IOException ex) {
				}

				JFXDialogLayout dialogLayout = new JFXDialogLayout();
				dialogLayout.getChildren().add(child);
				dialogEmployee = new JFXDialog(a, dialogLayout, JFXDialog.DialogTransition.CENTER);
				dialogEmployee.show();

			});
			checkboxEmployeId.setOnAction((ev) -> {
				if (checkboxEmployeId.isSelected()) {
					employeeslected.add(employee);
				} else if (!checkboxEmployeId.isSelected() && employeeslected.contains(employee)) {
					employeeslected.remove(employee);
				}
				for (Employee emp : employeeslected) {
					System.out.println(emp.getFirstName());
				}
			});
			setText(null);
			setGraphic(ROWEmpID);

		}

	}

}
