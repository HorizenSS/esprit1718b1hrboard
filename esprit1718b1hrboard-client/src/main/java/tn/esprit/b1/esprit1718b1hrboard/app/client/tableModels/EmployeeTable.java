package tn.esprit.b1.esprit1718b1hrboard.app.client.tableModels;

import javafx.beans.property.SimpleStringProperty;

/*Class tableEmployee to easly populate the table view of the employees */
public class EmployeeTable {

	private final SimpleStringProperty id;
	private final SimpleStringProperty names;
	private final SimpleStringProperty posts;
	private final SimpleStringProperty deps;

	public EmployeeTable(String id, String name, String posts, String deps) {
		this.id = new SimpleStringProperty(id);
		this.names = new SimpleStringProperty(name);
		this.posts = new SimpleStringProperty(posts);
		this.deps = new SimpleStringProperty(deps);

	}

	public void setId(String id) {
		this.id.set(id);
	}

	public void setNames(String names) {
		this.names.set(names);
	}

	public void setPosts(String posts) {
		this.posts.set(posts);
	}

	public void setDeps(String deps) {
		this.deps.set(deps);
	}

	public String getId() {
		return id.get();
	}

	public String getNames() {

		return names.get();
	}

	public String getPosts() {

		return posts.get();
	}

	public String getDeps() {

		return deps.get();
	}

	public SimpleStringProperty idProperty() {
		return id;
	}

	public SimpleStringProperty namesProperty() {
		return names;
	}

	public SimpleStringProperty postsProperty() {
		return posts;
	}

	public SimpleStringProperty depsProperty() {
		return deps;
	}

	@Override
	public String toString() {
		return "EmployeeTable [id=" + id + ", names=" + names + ", posts=" + posts + ", deps=" + deps + "]";
	}

}
