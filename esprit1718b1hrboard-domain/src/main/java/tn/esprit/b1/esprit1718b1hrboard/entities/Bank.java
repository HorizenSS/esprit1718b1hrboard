package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String agencyAddres;
	private String contactEmail;

	private List<Employee> employees;
	
	@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + ", agencyAddres=" + agencyAddres + ", contactEmail=" + contactEmail
				+ ", employees=" + employees + "]";
	}
	public Bank(){
		
	}
	public Bank(String name, String agencyAddres, String contactEmail) {
		super();
		this.name = name;
		this.agencyAddres = agencyAddres;
		this.contactEmail = contactEmail;
	}
	
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

	public String getAgencyAddres() {
		return agencyAddres;
	}

	public void setAgencyAddres(String agencyAddres) {
		this.agencyAddres = agencyAddres;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@OneToMany(mappedBy = "bank")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

}
