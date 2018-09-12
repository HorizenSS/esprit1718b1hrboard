package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.TaskPk;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class TaskService extends GenericDAO<Task> implements TaskServiceRemote, TaskServiceLocal {

	public TaskService() {
		super(Task.class);
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Task> findTasksByEmployee(Employee employee) {
		List<Task> employeeTasks = null;

		TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.employee = :employee",
				Task.class);
		employeeTasks = query.setParameter("employee", employee).getResultList();

		return employeeTasks;
	}

	@Override
	public List<Task> findTasksByEmployeeAndProject(Employee employee, Project project) {
		List<Task> employeeTasks = null;

		TypedQuery<Task> query = entityManager
				.createQuery("SELECT t FROM Task t WHERE t.employee = :employee AND t.project = :project", Task.class);
		employeeTasks = query.setParameter("employee", employee).setParameter("project", project).getResultList();

		return employeeTasks;
	}

	int idManager;

	@Override
	public void removeTaskByEmployee(Employee employee) {
		entityManager.createQuery("DELETE FROM Task t WHERE t.employee = :employee").setParameter("employee", employee)
				.executeUpdate();

		idManager = employee.getId();

	}

	@Override
	public void removeTask(Task task) {
		// TODO Auto-generated method stub
		entityManager.createQuery("DELETE FROM Task t WHERE t.taskPk = :task").setParameter("task", task.getTaskPk())
				.executeUpdate();
	}

	@Override
	public int empIdTasks() {

		return idManager;
	}

	public List<Task> findTasksByname(String task) {
		List<Task> employeeTasks = null;

		TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.name = :employee ",
				Task.class);
		employeeTasks = query.setParameter("employee", task).getResultList();

		return employeeTasks;
	}

	@Override
	public Task findById(TaskPk taskPk) {

		Task task = null;

		TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.taskPk = :taskPk", Task.class)
				.setParameter("taskPk", taskPk);

		task = query.getSingleResult();

		return task;
	}

	@Override
	public List<Task> findOnlyTasks(Employee employee) {
		List<Task> tasklist;
		List<Task> tasklistFinal = new ArrayList<>();
		
		tasklist = this.findTasksByEmployee(employee);
		for (Task task : tasklist) {
			if (!task.getTaskPk().getName().equals("TASK1")) {
				tasklistFinal.add(task);
			}
		}		
		return tasklistFinal;
	}

}
