package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Departement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;
	private String place;

	private List<Post> posts;

	// relational attributes

	private Departement superiorDep;
	private List<Departement> underDeps;
	
//	@Override
//	public String toString() {
//		return "Departement [id=" + id + ", name=" + name + ", description=" + description + ", place=" + place
//				+ ", posts=" + posts + ", superiorDep=" + superiorDep + ", underDeps=" + underDeps + "]";
//	}
	
	/* To String with minimum of fields*/
	@Override
	public String toString() {
		return ""+ name + " (Description : " + description +")";
	}
	
	

	public Departement(Integer id, String name, String description, String place, Departement superiorDep) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.place = place;
		this.superiorDep = superiorDep;
	}







	public Departement(String name, String description, String place, Departement superiorDep) {
		super();
		this.name = name;
		this.description = description;
		this.place = place;
		this.superiorDep = superiorDep;
	}

	public Departement() {
		super();
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

	@Column(columnDefinition = "TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@ManyToOne
	public Departement getSuperiorDep() {
		return superiorDep;
	}

	public void setSuperiorDep(Departement superiorDep) {
		this.superiorDep = superiorDep;
	}

	@OneToMany(mappedBy = "superiorDep",cascade={CascadeType.REMOVE}, fetch=FetchType.EAGER)
	public List<Departement> getUnderDeps() {
		return underDeps;
	}

	public void setUnderDeps(List<Departement> underDeps) {
		this.underDeps = underDeps;
	}

	@OneToMany(mappedBy = "departement")
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Departement other = (Departement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public boolean checkExistance(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departement other = (Departement) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
}
