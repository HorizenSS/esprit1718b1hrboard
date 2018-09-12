/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.EmployeeHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfilePaneController implements Initializable {

	@FXML
	private VBox skillsVbox;
	@FXML
	private StackPane generaleInfosStackPane;

	@FXML
	private Label lblFirstLastName;
	@FXML
	private Label lblAddress;
	@FXML
	private Label lblPost;
	@FXML
	private Label lblGrade;
	@FXML
	private Label lblDepartement;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblGender;
	@FXML
	private Label lblBirthDate;
	@FXML
	private Label lblNationality;
	@FXML
	private Label lblPhoneEmergency;
	@FXML
	private Label lblAddressEmergency;
	@FXML
	private Label lblCodeWork;
	@FXML
	private Label lblCin;
	@FXML
	private Label lblPassport;
	@FXML
	private Label lblSocialStatus;
	@FXML
	private Label lblChildsNb;
	@FXML
	private Label lblRib;
	@FXML
	private Label lblWorkedHrs;
	@FXML
	private Label lblVacationDays;
	@FXML
	private Label lblTypeContract;
	@FXML
	private Label lblSartDate;
	@FXML
	private Label lblEndDate;
	@FXML
	private Label lblRequiredHrs;
	@FXML
	private Label lblPricePerHrs;

	Employee employee = EmployeesGeneralListController.employee;

	EmployeeHasSkillServiceRemote proxyEmpHasSkill;
	ProjectServiceRemote proxyProject;
	TaskServiceRemote proxyTask;
	EmployeeServiceRemote proxyEmployee;

	List<EmployeeHasSkill> empHasSkills;
	@FXML
	private StackPane stackPaneDetails;

	public static JFXDialog dialog;

	private JFXDialog dialogSkill;
	private JFXDialog dialogSkillUp;
	@FXML
	private AnchorPane AllInfosPane;
	@FXML
	private ImageView profilPicDetail;
	@FXML
	private Label projectsIn;
	@FXML
	private Label tasksIn;
	@FXML
	private Label projectAfter;
	@FXML
	private Label ProjectBefore;
	@FXML
	private ProgressBar progressMonth;
	@FXML
	private ProgressBar progressYear;
	@FXML
	private JFXButton ConfirmDeleteEmpBtn;

	public static AnchorPane refreshPa;
	@FXML
	private AnchorPane generalInfosPane;
	@FXML
	private Button editGeneralInfoBtn;
	@FXML
	private Button editGeneralInfoBtn1;
	@FXML
	private Button addSkillBtn;
	@FXML
	private Label lblBank;
	@FXML
	private Label prjNb;
	@FXML
	private Button updateEmergencyBtn;
	@FXML
	private Button updatePersoInfoBtn;
	@FXML
	private Button updateContractBtn;
	@FXML
	private StackPane emergencyStack;
	@FXML
	private StackPane detailsStack;
	@FXML
	private StackPane contractStack;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		setEmployeeDetails();
		populateProjectPane();
		ConfirmDeleteEmpBtn.getStyleClass().clear();

		// TODO
	}

	@FXML
	private void OnActioneditGeneralInfos(ActionEvent event) {
		GeneralInfosPopUpController.refreshPa = AllInfosPane;
		Pane child = null;

		try {
			child = FXMLLoader.load(getClass().getResource("/views/GeneralInfosPopUp.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setMaxWidth(229);
		dialogLayout.setMinWidth(229);
		dialogLayout.setPrefWidth(229);
		dialogLayout.setPrefHeight(414);
		dialogLayout.setMaxHeight(414);
		dialogLayout.setMinHeight(414);

		dialogLayout.getChildren().add(child);

		dialog = new JFXDialog(generaleInfosStackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);

		dialog.show();

	}

	public void setEmployeeDetails() {
		if (employee == null) {
			return;
		}

		String jndiEmployeeHasSkill = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeHasSkillService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeHasSkillServiceRemote";
		proxyEmpHasSkill = (EmployeeHasSkillServiceRemote) EJBLookupUtil.doLookup(jndiEmployeeHasSkill);

		empHasSkills = proxyEmpHasSkill.findSkillsDetailsByEmployee(employee);

		for (EmployeeHasSkill employeeHasSkill : empHasSkills) {
			Label label = new Label(employeeHasSkill.getSkill().getName());
			label.setStyle("-fx-font-size: 14 ; -fx-text-fill: #6a7d9a; ");
			Float moy = employeeHasSkill.getSkillNote() / 10;
			ProgressBar progressBar = new ProgressBar(moy);
			progressBar.setPrefWidth(200.0);
			progressBar.setPrefHeight(10.0);
			progressBar.setMaxHeight(10.0);
			progressBar.setMinHeight(10.0);
			VBox vBox = new VBox();
			vBox.getChildren().add(label);
			vBox.getChildren().add(progressBar);

			vBox.onMouseEnteredProperty().set(e -> {

				DetailsSkillsPopUpController.employeeHasSkill = employeeHasSkill;

				Pane child = null;
				try {
					child = FXMLLoader.load(getClass().getResource("/views/DetailsSkillsPopUp.fxml"));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				JFXDialogLayout dialogLayout = new JFXDialogLayout();
				dialogLayout.setMaxWidth(220);
				dialogLayout.setMinWidth(220);
				dialogLayout.setPrefWidth(220);

				dialogLayout.setPrefHeight(276);
				dialogLayout.setMaxHeight(276);
				dialogLayout.setMinHeight(276);

				dialogLayout.getChildren().add(child);

				dialogSkill = new JFXDialog(stackPaneDetails, dialogLayout, JFXDialog.DialogTransition.TOP);

				dialogSkill.show();
				dialogSkill.setOnMouseClicked(ev2 -> {
					UpdateSkillPopPupController.refreshPa = AllInfosPane;

					System.out.println("!!!!!!!!! VBOX CLICKED !!!!!!!!");
					UpdateSkillPopPupController.employeeHasSkill = employeeHasSkill;

					Pane child2 = null;
					try {
						child2 = FXMLLoader.load(getClass().getResource("/views/UpdateSkillPopPup.fxml"));
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

					JFXDialogLayout dialogLayout2 = new JFXDialogLayout();
					dialogLayout2.setMaxWidth(220);
					dialogLayout2.setMinWidth(220);
					dialogLayout2.setPrefWidth(220);

					dialogLayout2.setPrefHeight(276);
					dialogLayout2.setMaxHeight(276);
					dialogLayout2.setMinHeight(276);

					dialogLayout2.getChildren().add(child2);

					dialogSkillUp = new JFXDialog(stackPaneDetails, dialogLayout2, JFXDialog.DialogTransition.CENTER);
					dialogSkillUp.show();

				});

				dialogSkill.onMouseExitedProperty().set(e2 -> dialogSkill.close());

			});

			skillsVbox.getChildren().add(vBox);

		}

		if (employee.getProfilPic() != null) {
			profilPicDetail.setImage(new Image("http://localhost/ImagesHR/" + employee.getProfilPic()));

		}

		if (employee.getAddress() != null) {
			lblAddress.setText(employee.getAddress());
		} else {
			lblAddress.setText("*******");

		}
		if (employee.getEmergencyAddress() != null) {
			lblAddressEmergency.setText(employee.getEmergencyAddress());
		} else {
			lblAddressEmergency.setText("******");

		}
		if (employee.getChildNumber() != null) {
			lblChildsNb.setText("CHILDS NBR: " + employee.getChildNumber().toString());
		} else {
			lblChildsNb.setText("CHILDS NBR: " + "***");

		}
		if (employee.getCin() != null) {
			lblCin.setText("NIC: " + employee.getCin());

		} else {
			lblCin.setText("NIC: " + "*****");

		}
		if (employee.getCode() != null) {
			lblCodeWork.setText("ID : " + employee.getCode());
		} else {
			lblCodeWork.setText("ID : " + "****");

		}
		if (employee.getPost() != null) {
			if (employee.getPost().getDepartement() != null) {
				if (employee.getPost().getDepartement().getName() != null) {
					lblDepartement.setText(employee.getPost().getDepartement().getName());
				} else {
					lblDepartement.setText("*****");

				}
			} else {
				lblDepartement.setText("*****");

			}
		} else {
			lblDepartement.setText("*****");

		}
		if (employee.getEmail() != null) {
			lblEmail.setText(employee.getEmail());
		} else {
			lblEmail.setText("*****");

		}
		if (employee.getFirstName() != null && employee.getLastName() == null) {
			lblFirstLastName.setText(employee.getFirstName().toUpperCase());
		} else if (employee.getFirstName() == null && employee.getLastName() != null) {
			lblFirstLastName.setText(employee.getLastName().toUpperCase());

		} else if (employee.getFirstName() == null && employee.getLastName() == null) {
			lblFirstLastName.setText("********");

		} else {
			lblFirstLastName
					.setText(employee.getFirstName().toUpperCase() + " " + employee.getLastName().toUpperCase());

		}
		if (employee.getGender() != null) {
			lblGender.setText(employee.getGender());
		} else {
			lblGender.setText("*****");

		}
		if (employee.getGrade() != null) {
			lblGrade.setText("GRADE: " + employee.getGrade());
		} else {
			lblGrade.setText("GRADE: " + "******");

		}
		if (employee.getNationality() != null) {
			lblNationality.setText(employee.getNationality());

		} else {
			lblNationality.setText("******");

		}
		if (employee.getNumPassport() != null) {
			lblPassport.setText("PASSPORT: " + employee.getNumPassport());
		} else {
			lblPassport.setText("PASSPORT: " + "*****");

		}
		if (employee.getPhoneNumber() != null) {
			lblPhone.setText(employee.getPhoneNumber().toString());
		} else {
			lblPhone.setText("********");

		}
		if (employee.getEmergencyPhone() != null) {
			lblPhoneEmergency.setText(employee.getEmergencyPhone().toString());
		} else {
			lblPhoneEmergency.setText("*******");

		}
		if (employee.getPost() != null) {
			if (employee.getPost().getEntitled() != null) {
				lblPost.setText(employee.getPost().getEntitled());
			} else {
				lblPost.setText("******");

			}
		} else {
			lblPost.setText("******");
		}
		if (employee.getContract() != null) {
			if (employee.getContract().getHourPrice() != null) {
				lblPricePerHrs.setText("WAGE/HR: " + employee.getContract().getHourPrice().toString());
			} else {
				lblPricePerHrs.setText("WAGE/HR: " + "****");

			}
		} else {
			lblPricePerHrs.setText("WAGE/HR: " + "****");

		}
		if (employee.getContract() != null) {
			if (employee.getContract().getRequiredHoursNb() != null) {
				lblRequiredHrs.setText("REQUIRED HRS: " + employee.getContract().getRequiredHoursNb().toString());

			} else {
				lblRequiredHrs.setText("REQUIRED HRS: " + "****");

			}
		} else {
			lblRequiredHrs.setText("REQUIRED HRS: " + "****");

		}
		if (employee.getRibAccount() != null) {
			lblRib.setText("RIB: " + employee.getRibAccount().toString());
		} else {
			lblRib.setText("RIB: " + "*****");
		}
		if (employee.getSocialStaus() != null) {
			lblSocialStatus.setText("SOCIAL STATUS: " + employee.getSocialStaus());
		} else {
			lblSocialStatus.setText("SOCIAL STATUS: " + "*****");
		}
		if (employee.getContract() != null) {
			if (employee.getContract().getType() != null) {
				lblTypeContract.setText(employee.getContract().getType());
			} else {
				lblTypeContract.setText("*****");
			}
		} else {
			lblTypeContract.setText("*****");
		}
		if (employee.getNationality() != null) {
			lblNationality.setText(employee.getNationality());
		} else {
			lblNationality.setText("*******");

		}
		if (employee.getVacationDays() != null) {
			lblVacationDays.setText("VACATION DAYS: " + employee.getVacationDays().toString());
		} else {
			lblVacationDays.setText("VACATION DAYS: " + "****");
		}
		if (employee.getWorkedHours() != null) {
			lblWorkedHrs.setText("WORKED HRS: " + employee.getWorkedHours().toString());
		} else {
			lblWorkedHrs.setText("WORKED HRS: " + "*****");

		}
		if (employee.getBirthDate() != null) {
			lblBirthDate.setText(employee.getBirthDate().toString());
		} else {
			lblBirthDate.setText("*****");

		}
		if (employee.getContract() != null) {
			if (employee.getContract().getEndDate() != null) {
				lblEndDate.setText("END DATE :" + employee.getContract().getEndDate().toString());
			} else {
				lblEndDate.setText("END DATE :" + "*****");

			}
		} else {
			lblEndDate.setText("END DATE :" + "*****");

		}
		if (employee.getStartDay() != null) {
			lblSartDate.setText("START DATE :" + employee.getStartDay().toString());
		} else {
			lblSartDate.setText("START DATE :" + "*****");
		}

	}

	@FXML
	private void OnActionAddSkillBtn(ActionEvent event) {

		AddDetailSkillEmpController.refreshPa = AllInfosPane;

		Pane child2 = null;
		try {
			child2 = FXMLLoader.load(getClass().getResource("/views/AddDetailSkillEmp.fxml"));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		JFXDialogLayout dialogLayout2 = new JFXDialogLayout();
		dialogLayout2.setMaxWidth(220);
		dialogLayout2.setMinWidth(220);
		dialogLayout2.setPrefWidth(220);

		dialogLayout2.setPrefHeight(400);
		dialogLayout2.setMaxHeight(400);
		dialogLayout2.setMinHeight(400);

		dialogLayout2.getChildren().add(child2);

		dialogSkillUp = new JFXDialog(stackPaneDetails, dialogLayout2, JFXDialog.DialogTransition.CENTER);
		dialogSkillUp.show();

	}

	void populateProjectPane() {

		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

		String jndiTasks = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
		proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTasks);

		List<Project> projectDetails = new ArrayList<>();
		List<Project> projectDetailsMaster = new ArrayList<>();

		List<Task> TaskEmp = new ArrayList<>();

		Set<Project> AllProjects = new HashSet<>();

		projectDetails = proxyProject.findAllProjectsByEmployee(EmployeesGeneralListController.employee);
		projectDetailsMaster = proxyProject.findProjectByMaster(EmployeesGeneralListController.employee);

		TaskEmp = proxyTask.findTasksByEmployee(EmployeesGeneralListController.employee);

		AllProjects.addAll(projectDetails);
		AllProjects.addAll(projectDetailsMaster);

		Integer i = 0, j = 0, k = 0, l = 0;

		for (Project project : AllProjects) {
			if (project.getStartDate() != null && project.getEndDate() != null) {
				if (project.getStartDate().before(Date.valueOf(LocalDate.now()))
						&& project.getEndDate().after(Date.valueOf(LocalDate.now()))) {
					i++;
				}
			}
		}

		for (Project project : AllProjects) {
			if (project.getStartDate() != null && project.getEndDate() != null) {
				if (project.getStartDate().after(Date.valueOf(LocalDate.now()))) {
					j++;
				}
			}
		}

		for (Project project : AllProjects) {
			if (project.getStartDate() != null && project.getEndDate() != null) {
				if (project.getEndDate().before(Date.valueOf(LocalDate.now()))) {
					k++;
				}
			}
		}

		for (Task task : TaskEmp) {
			if (task.getStartDate() != null && task.getEndDate() != null) {
				if (task.getStartDate().before(Date.valueOf(LocalDate.now()))
						&& task.getEndDate().after(Date.valueOf(LocalDate.now()))) {
					l++;
				}
			}
		}

		projectsIn.setText(i.toString());
		projectAfter.setText(j.toString());
		ProjectBefore.setText(k.toString());
		tasksIn.setText(l.toString());

		if (EmployeesGeneralListController.employee.getAppreciationMonth() != null) {
			progressMonth.setProgress(EmployeesGeneralListController.employee.getAppreciationMonth() / 10);
		} else {
			progressMonth.setProgress(0);
		}

		if (EmployeesGeneralListController.employee.getAppreciationYear() != null) {
			progressYear.setProgress(EmployeesGeneralListController.employee.getAppreciationYear() / 10);
		} else {
			progressYear.setProgress(0);
		}

	}

	@FXML
	private void OnActionDeleteEmp(ActionEvent event) {

		String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/EmployeeService!tn.esprit.b1.esprit1718b1hrboard.services.EmployeeServiceRemote";
		proxyEmployee = (EmployeeServiceRemote) EJBLookupUtil.doLookup(jndiEmployee);

		String jndiProject = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ProjectService!tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote";
		proxyProject = (ProjectServiceRemote) EJBLookupUtil.doLookup(jndiProject);

		String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
		proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTask);

		Employee employee = proxyEmployee.find(EmployeesGeneralListController.employee.getId());

		List<Project> projectList = proxyProject.findProjectByMaster(employee);

		if (!projectList.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("DELETE ERROR");
			alert.setContentText("THIS EMPLOYEE IS A MASTER OF AT LEAST ONE PROJECT ! ==> AFFECT A SBSTITUTE");

			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					return;

				}

			});
		} else {
			proxyTask.removeTaskByEmployee(employee);
			Employee empDel = proxyEmployee.find(proxyTask.empIdTasks());
			proxyEmployee.delete(empDel);
			Pane child = null;

			try {
				child = FXMLLoader.load(getClass().getResource("/views/EmployeesGeneralList.fxml"));
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

			refreshPa.getChildren().clear();
			refreshPa.getChildren().add(child);
		}

	}

	@FXML
	private void closeDetails(MouseEvent event) {
	}

	@FXML
	private void showSkillDetails(MouseEvent event) {
	}

	@FXML
	private void OnActionupdateEmergencyBtn(ActionEvent event) {
	}

	@FXML
	private void OnActionupdatePersoInfo(ActionEvent event) {
		PopupUpdatePersoInfoController.refreshPa = AllInfosPane;

		Pane child2 = null;
		try {
			child2 = FXMLLoader.load(getClass().getResource("/views/PopupUpdatePersoInfo.fxml"));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		JFXDialogLayout dialogLayout2 = new JFXDialogLayout();
		dialogLayout2.setMaxWidth(229);
		dialogLayout2.setMinWidth(229);
		dialogLayout2.setPrefWidth(229);
		dialogLayout2.setPrefHeight(235);
		dialogLayout2.setMaxHeight(235);
		dialogLayout2.setMinHeight(235);

		dialogLayout2.getChildren().add(child2);

		dialogSkillUp = new JFXDialog(detailsStack, dialogLayout2, JFXDialog.DialogTransition.CENTER);
		dialogSkillUp.show();
	}

	@FXML
	private void OnActionupdateContact(ActionEvent event) {
	}

}
