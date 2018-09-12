/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b1.esprit1718b1hrboard.app.client.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b1.esprit1718b1hrboard.app.client.util.EJBLookupUtil;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkill;
import tn.esprit.b1.esprit1718b1hrboard.entities.ProjectHasSkillPk;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.TaskPk;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceRemote;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TeamProjectRowController extends ListCell<Employee> {

	@FXML
	private AnchorPane skillRow;
	@FXML
	private Label lblemployeeName;
	@FXML
	private Label lblemployeePost;
	@FXML
	private JFXCheckBox checkEmployeeProject;
	@FXML
	private ProgressIndicator levelEmployee;

	private FXMLLoader mLLoader;

	/**
	 * Initializes the controller class.
	 */

	public TeamProjectRowController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateItem(Employee employee, boolean empty) {
		super.updateItem(employee, empty);
		if (empty || employee == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/views/TeamProjectRow.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			lblemployeeName.setText(employee.getFirstName() + " " + employee.getLastName());
			if (employee.getPost() != null) {
				lblemployeePost.setText(employee.getPost().getEntitled());
			}
			levelEmployee.setProgress(employee.getLevelProject() / 10);
			checkEmployeeProject.setSelected(false);

			if (TeamListProjectController.employeesInProject.isEmpty()) {
				lblemployeeName.setText(employee.getFirstName() + " " + employee.getLastName());
				if (employee.getPost() != null) {
					lblemployeePost.setText(employee.getPost().getEntitled());
				}
				levelEmployee.setProgress(employee.getLevelProject() / 10);
				checkEmployeeProject.setSelected(false);

			} else {
				for (Employee employeesInProject : TeamListProjectController.employeesInProject) {
					if (employeesInProject.getId() == employee.getId()) {
						lblemployeeName.setText(employee.getFirstName() + " " + employee.getLastName());
						if (employee.getPost() != null) {

							lblemployeePost.setText(employee.getPost().getEntitled());
						}
						levelEmployee.setProgress(employee.getLevelProject() / 10);
						checkEmployeeProject.setSelected(true);
						break;
					}

				}
			}

			TaskPk taskPk = new TaskPk();
			taskPk.setIdEmployee(employee.getId());
			taskPk.setIdProject(ProjectRowController.selectedProject.getId());
			taskPk.setName("TASK1");
			Task task = new Task();
			task.setTaskPk(taskPk);
			String jndiTask = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/TaskService!tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceRemote";
			TaskServiceRemote proxyTask = (TaskServiceRemote) EJBLookupUtil.doLookup(jndiTask);

			checkEmployeeProject.setOnAction(ev -> {
				if (checkEmployeeProject.isSelected()) {
//					System.out.println("!!!!!!!!!!check Employee selected !!!! " + employee);
					proxyTask.save(task);

				} else {
//					System.out.println("!!!!!!!!!! check Employee NOT selected !! " + employee);
					proxyTask.removeTaskByEmployee(employee);

				}

			});

			setText(null);
			setGraphic(skillRow);

		}
	}

}
