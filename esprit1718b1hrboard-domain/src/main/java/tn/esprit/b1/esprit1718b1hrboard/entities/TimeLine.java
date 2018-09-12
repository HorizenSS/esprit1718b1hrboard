package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TimeLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String content;
	private Date datePost;
	private Integer likes;
	
	private Employee employee;
	
	public TimeLine() {
		// TODO Auto-generated constructor stub
	}
	
	public TimeLine(String content, Date datePost, Integer likes) {
		super();
		this.content = content;
		this.datePost = datePost;
		this.likes = likes;
	}



	public TimeLine(Integer id, String content, Date datePost, Integer likes) {
		super();
		this.id = id;
		this.content = content;
		this.datePost = datePost;
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "TimeLine [id=" + id + ", content=" + content + ", datePost=" + datePost + ", likes=" + likes + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
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

	public Date getDatePost() {
		return datePost;
	}

	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	
	
	

}
