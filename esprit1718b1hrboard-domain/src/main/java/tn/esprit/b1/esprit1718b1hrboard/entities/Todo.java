package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Todo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String content;
	private String importance;
	private String status;
	
	private Task task;
	
	
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
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}

	public Todo() {
	}
	
	public Todo(String content, String importance, String status) {
		super();
		this.content = content;
		this.importance = importance;
		this.status = status;
	}
	public Todo(Integer id, String content, String importance, String status) {
		super();
		this.id = id;
		this.content = content;
		this.importance = importance;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", content=" + content + ", importance=" + importance + ", status=" + status + "]";
	}

	
	
	

	
	
	
	
	
	

}
