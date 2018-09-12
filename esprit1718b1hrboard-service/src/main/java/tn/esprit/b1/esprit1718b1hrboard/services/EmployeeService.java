package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Account;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Project;
import tn.esprit.b1.esprit1718b1hrboard.entities.Resignation;
import tn.esprit.b1.esprit1718b1hrboard.entities.Skill;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class EmployeeService extends GenericDAO<Employee> implements EmployeeServiceRemote, EmployeeServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public EmployeeService() {
		super(Employee.class);
	}

	AccountServiceRemote proxyEmployee;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/AccountService!tn.esprit.b1.esprit1718b1hrboard.services.AccountServiceRemote";

	@Override
	public Employee accessAccount(Account account) {
		Employee employee = null;

		try {
			employee = entityManager

					.createQuery("SELECT e FROM Employee e WHERE e.id=:l", Employee.class)
					.setParameter("l", account.getEmployee().getId()).getSingleResult();
		} catch (Exception e) {
		}
		return employee;
	}

	@Override
	public Employee findByCode(String code) {

		Employee employee = null;
		// methode with named Query
		// Map<String, Object> parametres = new HashMap<>();
		// parametres.put("code", code);
		// String query = "findEmployeeByCode";
		// employee = findOneResult(query, parametres);

		TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.code = :code",
				Employee.class);
		employee = (Employee) query.setParameter("code", code).getSingleResult();
		return employee;

	}

	public List<Employee> EmployeeWithoutAccount(Account account, Employee employee) {

		List<Employee> empl = new ArrayList<Employee>();
		List<Employee> empl1 = new ArrayList<Employee>();
		Long millis = System.currentTimeMillis();
		
		LocalDate d = LocalDate.now();
		Date da = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
		empl = super.findAll();
		for (Employee e : empl) {
			if(!da.equals(null)){
				if ((e.getAccount() == null) && (da.compareTo(e.getEndDay()) < 0))
					empl1.add(e);
			}
			
		}
		return empl1;
	}

	@Override
	public List<Employee> EmployeeWithAccountToDelete(Account account) {
		List<Employee> empl = new ArrayList<Employee>();
		empl = super.findAll();
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		List<Employee> employeedelete = new ArrayList<Employee>();
		for (Employee x : empl) {

			if ((date.compareTo(x.getEndDay()) >= 0)) {

				employeedelete.add(x);
			}

		}
		return employeedelete;
	}

	@Override
	public List<Employee> EmployeeWhoWillBeDelete() {

		List<Employee> empl = new ArrayList<Employee>();

		empl = super.findAll();

		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		List<Employee> employeedelete = new ArrayList<Employee>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.get(Calendar.YEAR);

		for (Employee x : empl) {
			Calendar calendar2 = new GregorianCalendar();

			calendar2.setTime(x.getEndDay());
			System.out.println(calendar2.get(Calendar.YEAR));

			if (((calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
					&& (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
					)) {
				employeedelete.add(x);

			}

		}
		return employeedelete;
	}

	@Override
	public Employee Employeeafficher() {
		List emp = new ArrayList<Employee>();
		Employee employee = null;
		try {
			employee = entityManager

					.createQuery("SELECT * FROM Employee", Employee.class).getSingleResult();
			emp.add(employee);

		} catch (Exception e) {
		}
		return (Employee) emp;
	}

	@Override
	public Employee findbyname(String name) {
		Employee employee = null;
		try {
			TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = :name",
					Employee.class);
			employee = (Employee) query.setParameter("name", name).getSingleResult();
		} catch (Exception e) {
		}
		return employee;

	}

	@Override
	public Employee findbymail(String email) {
		Employee employee = null;

		TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.email = :email",
				Employee.class);
		employee = (Employee) query.setParameter("email", email).getSingleResult();
		return employee;

	}

	@Override
	public List<Employee> BestEmployeeMounth() {
		List<Employee> empl = new ArrayList<Employee>();
		TypedQuery<Employee> query = entityManager
				.createQuery("SELECT e FROM Employee e ORDER  BY e.appreciationMonth DESC", Employee.class);
		empl = query.getResultList();

		return empl;
	}

	@Override
	public List<Employee> BestEmployeeYear() {
		List<Employee> empl = new ArrayList<Employee>();
		TypedQuery<Employee> query = entityManager
				.createQuery("SELECT e FROM Employee e ORDER  BY e.appreciationYear DESC", Employee.class);
		empl = query.getResultList();

		return empl;
	}

	@Override
	public Employee EmployeeByResignation(Resignation R) {
		Employee employee = null;
		TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.resignation = :resign",
				Employee.class);
		employee = (Employee) query.setParameter("resign", R).getSingleResult();
		return employee;
	}

	@Override
	public List<Employee> EmployeeBySkill(String skill) {
	List<Employee> employees =null;
		
		TypedQuery<Employee> query = entityManager.createQuery("SELECT DISTINCT e FROM Employee e JOIN e.specificationsSkills s WHERE s.skill.name = :skill ORDER BY s.skillNote DESC",Employee.class);
		employees = query.setParameter("skill", skill).getResultList();
		
		return employees;

		
	}

//	public List<Employee> EmployeeHasSkill(String skill) {
//		List<Employee> employees =null;
//			
//			TypedQuery<Employee> query = entityManager.createQuery("SELECT DISTINCT e FROM Employee e JOIN e.specificationsSkills s WHERE s.skill = :skill",Employee.class);
//			employees = query.setParameter("skill", skill).getResultList();
//			
//			return employees;
//
//			
//		}

	public List<Employee> EmployeeHasSkill(String skill) {
		List<Employee> employees = null;

		TypedQuery<Employee> query = entityManager.createQuery(
				"SELECT DISTINCT e FROM Employee e JOIN e.specificationsSkills s WHERE s.skill = :skill",
				Employee.class);
		employees = query.setParameter("skill", skill).getResultList();

		return employees;

	}
//	@Override
//	public List<Employee> EmployeeById() {
//		
//			List<Employee> empl = new ArrayList<Employee>();	
//			TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e ", Employee.class);
//			empl=query.getResultList();
//
//			return empl;
//		
//	}
	
	@Override
	public Employee findbynameComplet(String firstName, String lastName) {
		Employee employee = null;
		try {
				TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName And e.lastName =:lastName", Employee.class);
				employee = (Employee) query.setParameter("firstName", firstName).setParameter("lastName", lastName).getSingleResult();
	} catch (Exception e) {
	}
				return employee;

	}
		
	

	

	@Override
	public List<Employee> findAllEmployeesByProject(Project project) {
		List<Employee> employees = null;

		TypedQuery<Employee> query = entityManager.createQuery(
				"SELECT DISTINCT e FROM Employee e JOIN e.tasks t WHERE t.project = :project", Employee.class);
		employees = query.setParameter("project", project).getResultList();
		return employees;
	}

	// @Override
	// public List<Employee> EmployeeById() {
	//
	// List<Employee> empl = new ArrayList<Employee>();
	// TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM
	// Employee e ", Employee.class);
	// empl=query.getResultList();
	//
	// return empl;
	//
	// }

}
