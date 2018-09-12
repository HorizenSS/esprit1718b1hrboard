package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Departement;
import tn.esprit.b1.esprit1718b1hrboard.entities.Employee;
import tn.esprit.b1.esprit1718b1hrboard.entities.Post;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class PostService extends GenericDAO<Post> implements PostServiceRemote, PostServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public PostService() {
		super(Post.class);
	}

	@Override
	public Post findPostCode(String Code) {
		Post p = null;
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.codePost = :Code", Post.class);
		p = (Post) query.setParameter("Code", Code).getSingleResult();
		return p;
	}

	@Override
	public List<Post> findPostsByDep(Departement dep) {
		List<Post> posts= null;
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.departement = :dep", Post.class);
		posts =  query.setParameter("dep", dep).getResultList();
		return posts;
	}

}
