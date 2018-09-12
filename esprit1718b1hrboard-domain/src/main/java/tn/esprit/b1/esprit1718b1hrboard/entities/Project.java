package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String type;
	private Date startDate;
	private Date endDate;
	private Double gain;
	private Float clientAppreciation;

	private Set<Task> tasks;
	private Set<ProjectHasSkill> skillsInProject;

	private Employee projectMaster;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	public Float getClientAppreciation() {
		return clientAppreciation;
	}

	public void setClientAppreciation(Float clientAppreciation) {
		this.clientAppreciation = clientAppreciation;
	}

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<ProjectHasSkill> getSkillsInProject() {
		return skillsInProject;
	}

	public void setSkillsInProject(Set<ProjectHasSkill> skillsInProject) {
		this.skillsInProject = skillsInProject;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Employee getProjectMaster() {
		return projectMaster;
	}

	public void setProjectMaster(Employee projectMaster) {
		this.projectMaster = projectMaster;
	}

	public Project() {
		super();
	}

	public Project(Integer id, String name, String type, Date startDate, Date endDate, Double gain,
			Float clientAppreciation) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.gain = gain;
		this.clientAppreciation = clientAppreciation;
	}

	public Project(String name, String type, Date startDate, Date endDate, Double gain, Float clientAppreciation) {
		super();
		this.name = name;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.gain = gain;
		this.clientAppreciation = clientAppreciation;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", type=" + type + ", startDate=" + startDate + ", endDate="
				+ endDate + ", gain=" + gain + ", clientAppreciation=" + clientAppreciation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// @Override
	// public String toString() {
	// return "Project [id=" + id + ", name=" + name + ", type=" + type + ",
	// startDate=" + startDate + ", endDate="
	// + endDate + ", gain=" + gain + ", clientAppreciation=" +
	// clientAppreciation + ", tasks=" + tasks
	// + ", skillsInProject=" + skillsInProject + "]";
	// }

}
