package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String entitled;
	private String description;
	private String codePost;

	private List<Employee> employees;
	private Departement departement;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntitled() {
		return entitled;
	}

	public void setEntitled(String entitled) {
		this.entitled = entitled;
	}

	public String getDescription() {
		return description;
	}

	@Column(columnDefinition = "TEXT")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodePost() {
		return codePost;
	}

	public void setCodePost(String codePost) {
		this.codePost = codePost;
	}

	@OneToMany(mappedBy = "post")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@ManyToOne
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Post() {
		super();
	}

	public Post(Integer id, String entitled, String description, String codePost) {
		super();
		this.id = id;
		this.entitled = entitled;
		this.description = description;
		this.codePost = codePost;
	}

	public Post(String entitled, String description, String codePost) {
		super();
		this.entitled = entitled;
		this.description = description;
		this.codePost = codePost;
	}

	/* To String with minimum of fields */
	@Override
	public String toString() {
		return "Post [id=" + id + ", entitled=" + entitled + ", description=" + description + ", codePost=" + codePost
				+ ", departement=" + departement + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codePost == null) ? 0 : codePost.hashCode());
		result = prime * result + ((departement == null) ? 0 : departement.hashCode());
		result = prime * result + ((entitled == null) ? 0 : entitled.hashCode());
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
		Post other = (Post) obj;
		if (codePost == null) {
			if (other.codePost != null)
				return false;
		} else if (!codePost.equals(other.codePost))
			return false;
		if (departement == null) {
			if (other.departement != null)
				return false;
		} else if (!departement.equals(other.departement))
			return false;
		if (entitled == null) {
			if (other.entitled != null)
				return false;
		} else if (!entitled.equals(other.entitled))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// @Override
	// public String toString() {
	// return "Post [id=" + id + ", entitled=" + entitled + ", description=" +
	// description + ", codePost=" + codePost
	// + ", employees=" + employees + ", departement=" + departement + "]";
	// }
	

}
