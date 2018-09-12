package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Response implements Serializable {
	
	@Override
	public String toString() {
		return "Response [responsePk=" + responsePk + ", content=" + ", postDate=" + postDate + ", rating="
				+ rating + ", employee=" + employee + ", topic=" + topic + "]";
	}

	public Response(ReponsePk responsePk, Date postDate, Float rating, Employee employee, Topic topic) {
		super();
		this.responsePk = responsePk;
		this.postDate = postDate;
		this.rating = rating;
		this.employee = employee;
		this.topic = topic;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReponsePk responsePk;
	private Date postDate;
	private Float rating;
	
	public Response(ReponsePk responsePk, String content, Date postDate, Float rating, Employee employee, Topic topic) {
		super();
		this.responsePk = responsePk;
		this.postDate = postDate;
		this.rating = rating;
		this.employee = employee;
		this.topic = topic;
	}
	
	public Response() {
		super();
	}

	private Employee employee;
	private Topic topic;
	
	
	
	
	@EmbeddedId
	public ReponsePk getResponsePk() {
		return responsePk;
	}
	public void setResponsePk(ReponsePk responsePk) {
		this.responsePk = responsePk;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	@ManyToOne
	@JoinColumn(name = "idEmployee", referencedColumnName = "id", insertable = false, updatable = false)
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne
	@JoinColumn(name = "idTopic", referencedColumnName = "id", insertable = false, updatable = false)
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	
	
	

	
}
