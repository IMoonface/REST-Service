package com.example.rest.model;

/**
 * Class that represents a Todo.
 * @author Marc Uxa
 *
 */
public class Todo {

	private int id;
	private String name;
	private String description;
	private Task[] tasks;
	
	/**
	 * Default constructor.
	 */
	public Todo() {}
	
	/**
	 * Constructor of the class.
	 * @param id of the Todo.
	 * @param name of the Todo.
	 * @param description of the Todo.
	 * @param tasks of the Todo.
	 */
	public Todo(int id, String name, String description, Task[] tasks) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.tasks = tasks;
	}
	
	/**
	 * Returns the id of the Todo.
	 * @return id of the Todo.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id of the Todo.
	 * @param id of the Todo.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the name of the Todo.
	 * @return name of the Todo.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the Todo.
	 * @param name that should be set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the description of the Todo.
	 * @return description of the Todo.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the Todo.
	 * @param description that should be set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the Tasks of the Todo.
	 * @return tasks of the Todo.
	 */
	public Task[] getTasks() {
		return tasks;
	}
	
	/**
	 * Sets the Tasks of the Todo.
	 * @param tasks that should be set.
	 */
	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
	}  
}