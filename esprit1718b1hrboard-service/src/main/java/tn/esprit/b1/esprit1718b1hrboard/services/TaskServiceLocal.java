package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.TaskPk;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface TaskServiceLocal extends IGenericDAO<Task> {

	
	public List<Task> findTasksByEmployee(Employee employee);
	public List<Task> findTasksByname(String task);
	public List<Task> findTasksByEmployeeAndProject(Employee employee, Project project);
	public void removeTaskByEmployee(Employee employee);
	public void removeTask(Task task);
	public int empIdTasks();
	public Task findById(TaskPk taskPk);
	
	public List<Task> findOnlyTasks(Employee employee);
}
