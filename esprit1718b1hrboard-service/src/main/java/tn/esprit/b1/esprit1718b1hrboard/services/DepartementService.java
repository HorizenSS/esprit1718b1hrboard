package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class DepartementService extends GenericDAO<Departement>
		implements DepartementServiceRemote, DepartementServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public DepartementService() {
		super(Departement.class);
	}

	@Override
	public List<Departement> findTopDeps() {
		TypedQuery<Departement> query = entityManager.createQuery("SELECT p FROM Departement p WHERE p.superiorDep = NULL", Departement.class);
		return query.getResultList();
	}

	@Override
	public void RemoveDepartmentHierarchy(Departement dep) {
		List<Departement> Ldep = new ArrayList<>();
			if(!dep.getUnderDeps().isEmpty()){
				Ldep = dep.getUnderDeps();
				for (Departement dd : Ldep){
					entityManager.remove(dd);
				}
			}
			entityManager.remove(dep);
	}

	@Override
	public List<Departement> findDepsByNames(String Name) {
		TypedQuery<Departement> query = entityManager.createQuery("SELECT d FROM Departement d WHERE d.name LIKE :depname", Departement.class)
				.setParameter("depname", '%'+Name+'%');
		return query.getResultList();
	}
	
	
	
	

}
