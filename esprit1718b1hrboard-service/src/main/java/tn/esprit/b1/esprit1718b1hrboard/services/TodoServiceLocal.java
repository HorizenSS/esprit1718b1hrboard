package tn.esprit.b1.esprit1718b1hrboard.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b1.esprit1718b1hrboard.entities.Task;
import tn.esprit.b1.esprit1718b1hrboard.entities.Todo;
import tn.esprit.b1.esprit1718b1hrboard.utilities.IGenericDAO;

@Local
public interface TodoServiceLocal extends IGenericDAO<Todo> {
	
	public List<Todo> findAllTodosBytask(Task task);

}
