package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.Todo;
import tn.esprit.b1.esprit1718b1hrboard.utilities.GenericDAO;

@Stateless
public class TodoService extends GenericDAO<Todo> implements TodoServiceLocal,TodoServiceRemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	public TodoService( ) {
		super(Todo.class);
	}

	@Override
	public List<Todo> findAllTodosBytask(Task task) {
		List<Todo> todos = null;

		TypedQuery<Todo> query = entityManager.createQuery("SELECT a FROM Todo a WHERE a.task = :task",
				Todo.class);
		todos = query.setParameter("task", task).getResultList();

		return todos;	
	}

}
