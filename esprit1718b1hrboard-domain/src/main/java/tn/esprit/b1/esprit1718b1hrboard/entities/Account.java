package tn.esprit.b1.esprit1718b1hrboard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String login;
	private String password;
	private Date lastConnection;
	private Date creationDate;
	private Access access; // Access redirections "roles"

	private Employee employee;

	@Override
	public String toString() {
		return "Account [id=" + id + ", login=" + login + ", password=" + password + ", lastConnection="
				+ lastConnection + ", creationDate=" + creationDate + ", access=" + access + ", employee=" + employee
				+ "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	@Enumerated(EnumType.STRING)
	public Access getAccess() {
		return access;
	}

	public Account(Integer id, String login, String password, Date lastConnection, Date creationDate, Access access,
			Employee employee) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.lastConnection = lastConnection;
		this.creationDate = creationDate;
		this.access = access;
		this.employee = employee;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	@OneToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Account() {
		super();
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Account(Integer id, String login, String password, Date lastConnection, Date creationDate, Access access) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.lastConnection = lastConnection;
		this.creationDate = creationDate;
		this.access = access;
	}

	public Account(String login, String password, Date lastConnection, Date creationDate, Access access) {
		super();
		this.login = login;
		this.password = password;
		this.lastConnection = lastConnection;
		this.creationDate = creationDate;
		this.access = access;
	}

}
