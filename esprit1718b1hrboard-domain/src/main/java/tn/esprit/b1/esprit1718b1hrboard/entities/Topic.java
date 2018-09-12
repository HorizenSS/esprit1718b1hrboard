package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Topic implements Serializable {

	public Topic() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	private Integer id;
	private String content;
	private Date postDate;
   

	public Topic(String subject, String content, Date postDate, String title, Employee employee) {
		super();
		this.subject = subject;
		this.content = content;
		this.postDate = postDate;
		this.title = title;
		this.employee = employee;
	}

	private String title;
	private Employee employee;
	public Topic(String content, Date postDate, String title, Employee employee, List<Response> employeesResponses) {
		super();
		this.content = content;
		this.postDate = postDate;
		this.title = title;
		this.employee = employee;
		this.employeesResponses = employeesResponses;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private List<Response> employeesResponses;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	
	public Topic(String content, Date postDate, String title, Employee employee) {
		super();
		this.content = content;
		this.postDate = postDate;
		this.title = title;
		this.employee = employee;
	}

	public Topic(String content, Date postDate, Employee employee, List<Response> employeesResponses) {
		super();
		this.content = content;
		this.postDate = postDate;
		this.employee = employee;
		this.employeesResponses = employeesResponses;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@OneToMany(mappedBy="topic")
	public List<Response> getEmployeesResponses() {
		return employeesResponses;
	}

	public void setEmployeesResponses(List<Response> employeesResponses) {
		this.employeesResponses = employeesResponses;
	}

}
