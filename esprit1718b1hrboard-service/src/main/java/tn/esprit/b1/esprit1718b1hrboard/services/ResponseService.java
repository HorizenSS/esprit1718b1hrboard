package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Response;
import tn.esprit.b1.esprit1718b1hrboard.entities.Topic;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;
@Stateless
public class ResponseService extends GenericDAO<Response> implements  ResponseServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;
	ResponseServiceLocal proxyResponse;
	String jndiEmployee = "esprit1718b1hrboard-ear/esprit1718b1hrboard-service/ResponseService!tn.esprit.b1.esprit1718b1hrboard.services.ResponseServiceRemote";

	public ResponseService() {
		super(Response.class);
	}

	@Override
	public List<Response> getResponse() {
		List<Response> empl = new ArrayList<Response>();
		TypedQuery<Response> query = entityManager
				.createQuery("SELECT e FROM Response e ORDER  BY e.rating DESC", Response.class);
		empl = query.getResultList();

		return empl;		
	}

}
