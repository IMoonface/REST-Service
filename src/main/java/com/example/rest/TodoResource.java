package com.example.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.rest.model.Task;
import com.example.rest.model.Todo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * Class that represents the implementation of the "REST" service functions.
 * @author Marc Uxa
 *
 */
@Path("/todos")
public class TodoResource {

	/** To save the Todos */
	private static Map<Integer, Todo> DB = new HashMap<>();

	/**
	 * Returns a list of all Todos.
	 * @return List of all Todos.
	 */
	@GET
	@Produces("application/json")
	public ArrayList<Todo> getAllTodos() {
		return new ArrayList<Todo>(DB.values());
	}

	/**
	 * Creates a new Todo.
	 * @param todo which should be created.
	 * @return Status code 201 for successful creation and the Todo (in the body of the response)
	 * or status code 400 if not all mandatory inputs are provided.
	 */
	@POST
	@Produces("application/json")
	public Response createTodo(Todo todo) {
		
		if(!isValidTodo(todo)) {
			return Response.status(400).entity("Please provide all mandatory inputs").build();
		}

		if (todo.getDescription().isBlank()) {
			todo.setDescription(todo.getName() + " consists of " + todo.getTasks().length + " tasks");
		}

		todo.setId(DB.values().size() + 1);

		DB.put(todo.getId(), todo);

		return Response.status(201).entity(DB.get(todo.getId())).build();
	}

	/**
	 * Returns a specific Todo.
	 * @param id of the Todo that should be returned.
	 * @return Status code 200 if the Todo was found and the Todo (in the body of the response)
	 * or status code 404 if the Todo does not exist.
	 */
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getTodoById(@PathParam("id") int id) {
		Todo todo = DB.get(id);

		if(todo == null){
			return Response.status(404).build();
		}

		return Response.status(200).entity(todo).build();
	}

	/**
	 * Updates an already existing Todo.
	 * @param id of the Todo which should be updated.
	 * @param todo with the updated content.
	 * @return Status code 200 if the todo was updated
	 * or status code 400 if not all mandatory inputs are provided.
	 */
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateTodo(@PathParam("id") int id, Todo todo) {
		if(!isValidTodo(todo)) {
			return Response.status(400).entity("Please provide all mandatory inputs").build();
		}

		if (todo.getDescription().isBlank()) {
			todo.setDescription(todo.getName() + " consists of " + todo.getTasks().length + " tasks");
		}

		DB.get(id).setName(todo.getName());
		DB.get(id).setDescription(todo.getDescription());
		DB.get(id).setTasks(todo.getTasks());

		return Response.status(200).build();
	}

	/**
	 * Deletes a Todo.
	 * @param id of the Todo that should be deleted.
	 * @return Status code 204 if the Todo was successfully deleted
	 * or status code 404 if the Todo does not exist.
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteTodo(@PathParam("id") int id) {
		Todo todo = DB.get(id);
		
		if(todo == null) {
			return Response.status(404).build();
		}

		DB.remove(todo.getId());

		return Response.status(204).build();
	}

	/** Test data */
	static {
		Task[] tasks = {new Task("Name", "Test"), new Task("Second Name", "Second Test")};

		Todo todo = new Todo();
		todo.setId(1);
		todo.setName("Todo number " + todo.getId());
		todo.setDescription("This is a test Todo number " + todo.getId());
		todo.setTasks(tasks);

		Todo todoSecond = new Todo();
		todoSecond.setId(2);
		todoSecond.setName("Todo number " + todoSecond.getId());
		todoSecond.setDescription("This is a test Todo number " + todoSecond.getId());
		todoSecond.setTasks(tasks);

		DB.put(todo.getId(), todo);
		DB.put(todoSecond.getId(), todoSecond);
	}
	
	/**
	 * Checks the content of a todo for all mandatory inputs.
	 * @param todo which content should be checked.
	 * @return true if the content is valid or false if the content is invalid.
	 */
	public boolean isValidTodo(Todo todo) {
		if(todo == null || todo.getName().isBlank() || todo.getTasks() == null) {
			return false;
		}
        
		for(int i = 0; i < todo.getTasks().length; i++) {
			if (todo.getTasks()[i].getName().isBlank()) {
				return false;
			}
		}
		
		return true;
	}
}