package tn.esprit.b1.esprit1718b1hrboard.mBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.TaskPk;
import tn.esprit.b1.esprit1718b1hrboard.services.ProjectServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.services.TaskServiceLocal;
import tn.esprit.b1.esprit1718b1hrboard.utils.SessionUser;

@ManagedBean
@RequestScoped
public class TaskBean {

	@EJB
	TaskServiceLocal taskService;

	@EJB
	ProjectServiceLocal projectService;

	private List<Task> tasklist = new ArrayList<>();
	private List<Task> tasklistFinal = new ArrayList<>();
	
	private List<Task> taskEmpList;

	private Project projectSelected;
	private String ajaxVar;

	private String ajaxVarTask;
	private Task taskSelected;

	@PostConstruct
	public void init() {
		System.out.println("!!!!!!!!!!!!!!!!!!SYS OUT!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(ajaxVar);
	}

	public List<Task> getTasklist() {
		return tasklist;
	}

	public void setTasklist(List<Task> tasklist) {
		this.tasklist = tasklist;
	}

	public List<Task> getTasklistFinal() {
		return tasklistFinal;
	}

	public void setTasklistFinal(List<Task> tasklistFinal) {
		this.tasklistFinal = tasklistFinal;
	}

	public Project getProjectSelected() {
		return projectSelected;
	}

	public void setProjectSelected(Project projectSelected) {
		this.projectSelected = projectSelected;
	}

	public String getAjaxVar() {
		return ajaxVar;
	}

	public void setAjaxVar(String ajaxVar) {
		this.ajaxVar = ajaxVar;
	}

	public String getAjaxVarTask() {
		return ajaxVarTask;
	}

	public void setAjaxVarTask(String ajaxVarTask) {
		this.ajaxVarTask = ajaxVarTask;
	}

	public Task getTaskSelected() {
		return taskSelected;
	}

	public void setTaskSelected(Task taskSelected) {
		this.taskSelected = taskSelected;
	}
	

	public List<Task> getTaskEmpList() {
		return taskEmpList;
	}

	public void setTaskEmpList(List<Task> taskEmpList) {
		this.taskEmpList = taskEmpList;
	}

	public void tasksProject() {

		Integer idProject = Integer.parseInt(ajaxVar);
		projectSelected = projectService.find(idProject);
		SessionUser.projectSelected = projectSelected;

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + projectSelected);

		tasklist = taskService.findTasksByEmployeeAndProject(SessionUser.employeeConnected, projectSelected);

		for (Task task : tasklist) {
			if (!task.getTaskPk().getName().equals("TASK1")) {
				tasklistFinal.add(task);
			}
		}
	}
	
	public String redirectDetailsTask(){
		
		 return "/template/tasksDetails?faces-redirect=true";
		
	}
	
	public void printAllEmployeeTask(){
		
		taskEmpList = taskService.findTasksByEmployee(SessionUser.employeeConnected);
	}

	public void printTask() {

//		return "/template/home?faces-redirect=true";

		 System.out.println("success !!!!!!");
		
		 TaskPk taskPk = new TaskPk();
		
		 taskPk.setIdEmployee(SessionUser.employeeConnected.getId());
		 taskPk.setIdProject(SessionUser.projectSelected.getId());
		 taskPk.setName(ajaxVarTask);
		
		 taskSelected = taskService.findById(taskPk);
		
		 System.out.println(taskSelected);

	}
}
