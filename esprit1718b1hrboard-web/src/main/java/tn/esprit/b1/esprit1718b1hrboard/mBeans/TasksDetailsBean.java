package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.Todo;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TodoServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

@ManagedBean
@SessionScoped
public class TasksDetailsBean {

	@EJB
	TaskServiceLocal taskService;

	@EJB
	TodoServiceLocal todoService;

	private Project selectedProject;

	private List<Task> taskEmpList;
	private List<Task> tasklistFinal;
	private Task selectedTask;
	private int showProgress = 0;
	private float progressUp = 5;

	private List<Todo> todos;

	private String toDoContent;

	private String task1 = "task1";
	private String task2 = "task2";
	private String task3 = "task3";
	private String task4 = "task4";

	private int todos1 = 0;
	private int todos2 = 0;
	private int todos3 = 0;
	private int todos4;

	private int alltodos;

	@PostConstruct
	public void init() {
		selectedProject = SessionUser.projectSelected;

		taskEmpList = taskService.findOnlyTasks(SessionUser.employeeConnected);

		task1 = taskEmpList.get(0).getTaskPk().getName();
		task2 = taskEmpList.get(1).getTaskPk().getName();
		task3 = taskEmpList.get(2).getTaskPk().getName();
		todos1 = taskEmpList.get(0).getTodos().size();
		todos2 = taskEmpList.get(1).getTodos().size();
		todos3 = taskEmpList.get(2).getTodos().size();

		alltodos = todos1 + todos2 + todos3;

		// todos1 = tasklistFinal.get(0).getTodos().size();
		// todos2 = tasklistFinal.get(1).getTodos().size();
		// todos3 = tasklistFinal.get(2).getTodos().size();
		// todos3 = 6;
		// System.out.println(taskEmpList);
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public List<Task> getTaskEmpList() {
		return taskEmpList;
	}

	public void setTaskEmpList(List<Task> taskEmpList) {
		this.taskEmpList = taskEmpList;
	}

	public List<Task> getTasklistFinal() {
		return tasklistFinal;
	}

	public void setTasklistFinal(List<Task> tasklistFinal) {
		this.tasklistFinal = tasklistFinal;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

	public int getShowProgress() {
		return showProgress;
	}

	public void setShowProgress(int showProgress) {
		this.showProgress = showProgress;
	}

	public float getProgressUp() {
		return progressUp;
	}

	public void setProgressUp(float progressUp) {
		this.progressUp = progressUp;
	}

	public String getToDoContent() {
		return toDoContent;
	}

	public void setToDoContent(String toDoContent) {
		this.toDoContent = toDoContent;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public String getTask1() {
		return task1;
	}

	public void setTask1(String task1) {
		this.task1 = task1;
	}

	public String getTask2() {
		return task2;
	}

	public void setTask2(String task2) {
		this.task2 = task2;
	}

	public String getTask3() {
		return task3;
	}

	public void setTask3(String task3) {
		this.task3 = task3;
	}

	public String getTask4() {
		return task4;
	}

	public void setTask4(String task4) {
		this.task4 = task4;
	}

	public int getTodos1() {
		return todos1;
	}

	public void setTodos1(int todos1) {
		this.todos1 = todos1;
	}

	public int getTodos2() {
		return todos2;
	}

	public void setTodos2(int todos2) {
		this.todos2 = todos2;
	}

	public int getTodos3() {
		return todos3;
	}

	public void setTodos3(int todos3) {
		this.todos3 = todos3;
	}

	public int getTodos4() {
		return todos4;
	}

	public void setTodos4(int todos4) {
		this.todos4 = todos4;
	}

	public int getAlltodos() {
		return alltodos;
	}

	public void setAlltodos(int alltodos) {
		this.alltodos = alltodos;
	}

	public void printAllEmployeeTask() {

		taskEmpList = taskService.findTasksByEmployee(SessionUser.employeeConnected);
	}

	public void printTaskSelected() {

		System.out.println("!!!!!!!!MY TASK " + selectedTask);
		progressUp = selectedTask.getTaskNote().intValue();
		todos = todoService.findAllTodosBytask(selectedTask);
		System.out.println(todos);
		showProgress = 1;

	}

	public void updateSelectedTask() {

		System.out.println("pleaaaaaaaaaaase" + progressUp);

		System.out.println("OKKKKKKK !");

		// taskService.removeTask(selectedTask);
		Float pr = progressUp;
		selectedTask.setTaskNote(pr);
		taskService.update(selectedTask);

	}

	public void addTodoTask() {

		Todo todo = new Todo();
		todo.setContent(toDoContent);
		todo.setStatus("noteDone");
		todo.setTask(selectedTask);
		todoService.save(todo);

		todos = todoService.findAllTodosBytask(selectedTask);

		taskEmpList = taskService.findOnlyTasks(SessionUser.employeeConnected);
		task1 = taskEmpList.get(0).getTaskPk().getName();
		task2 = taskEmpList.get(1).getTaskPk().getName();
		task3 = taskEmpList.get(2).getTaskPk().getName();
		todos1 = taskEmpList.get(0).getTodos().size();
		todos2 = taskEmpList.get(1).getTodos().size();
		todos3 = taskEmpList.get(2).getTodos().size();
		alltodos = todos1 + todos2 + todos3;

	}

	public void updateToDostatusTask(Todo todo) {

		if (todo.getStatus().equals("noteDone")) {
			todo.setStatus("done");
			todoService.update(todo);

		} else if (todo.getStatus().equals("done")) {
			todo.setStatus("noteDone");
			todoService.update(todo);

		}

	}

	public int statusTodo(String status) {

		if (status.equals("noteDone")) {
			return 1;
		} else if (status.equals("done")) {
			return 2;
		}

		return 0;

	}

}
