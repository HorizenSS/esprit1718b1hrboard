package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaskPk taskPk;
	private Date startDate;
	private Date endDate;
	private Float importance;
	private Float taskNote;

	private Project project;
	private Employee employee;
	
	private List<Todo> todos;

	@EmbeddedId
	public TaskPk getTaskPk() {
		return taskPk;
	}

	public void setTaskPk(TaskPk taskPk) {
		this.taskPk = taskPk;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {	
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Float getImportance() {
		return importance;
	}

	public void setImportance(Float importance) {
		this.importance = importance;
	}

	public Float getTaskNote() {
		return taskNote;
	}

	public void setTaskNote(Float taskNote) {
		this.taskNote = taskNote;
	}

	@ManyToOne
	@JoinColumn(name = "idProject", referencedColumnName = "id", insertable = false, updatable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(name = "idEmployee", referencedColumnName = "id", insertable = false, updatable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@OneToMany(mappedBy="task",cascade = CascadeType.ALL,fetch= FetchType.EAGER)
	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Task() {
		super();
	}

	public Task(TaskPk taskPk, Date startDate, Date endDate, Float importance, Float taskNote) {
		super();
		this.taskPk = taskPk;
		this.startDate = startDate;
		this.endDate = endDate;
		this.importance = importance;
		this.taskNote = taskNote;
	}

	public Task(Date startDate, Date endDate, Float importance, Float taskNote) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.importance = importance;
		this.taskNote = taskNote;
	}

	@Override
	public String toString() {
		return "Task [taskPk=" + taskPk + ", startDate=" + startDate + ", endDate=" + endDate + ", importance="
				+ importance + ", taskNote=" + taskNote + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskPk == null) ? 0 : taskPk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskPk == null) {
			if (other.taskPk != null)
				return false;
		} else if (!taskPk.equals(other.taskPk))
			return false;
		return true;
	}
	
	
	
	
	




}
