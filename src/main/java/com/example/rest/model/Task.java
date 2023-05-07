package com.example.rest.model;

/**
 * Class that represents a Task.
 * @author Marc Uxa
 * 
 */
public class Task {

	private String name;
	private String description;
	
	/**
	 * Default constructor.
	 */
	public Task() {}
	
	/**
	 * Constructor of the class.
	 * @param name of the Task.
	 * @param description of the Task.
	 */
	public Task(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * Returns the name of the Task.
	 * @return name of the Task.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Task.
	 * @param name that should be set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the description of the Task.
	 * @return description of the Task.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the Task.
	 * @param description that should be set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}