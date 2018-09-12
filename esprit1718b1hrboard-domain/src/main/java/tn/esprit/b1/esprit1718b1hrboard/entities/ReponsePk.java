package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReponsePk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idEmployee;
	private Integer idTopic;
	private String content; 

	
	public ReponsePk(Integer idEmployee, Integer idTopic, String content) {
		super();
		this.idEmployee = idEmployee;
		this.idTopic = idTopic;
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	public Integer getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(Integer idTopic) {
		this.idTopic = idTopic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
		result = prime * result + ((idTopic == null) ? 0 : idTopic.hashCode());
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
		ReponsePk other = (ReponsePk) obj;
		if (idEmployee == null) {
			if (other.idEmployee != null)
				return false;
		} else if (!idEmployee.equals(other.idEmployee))
			return false;
		if (idTopic == null) {
			if (other.idTopic != null)
				return false;
		} else if (!idTopic.equals(other.idTopic))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	public ReponsePk() {
		super();
	}
	
	
	
	
	
	
	
	

}
