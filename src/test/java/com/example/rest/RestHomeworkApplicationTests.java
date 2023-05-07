package com.example.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rest.model.Task;
import com.example.rest.model.Todo;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Class that represents the tests.
 * @extends JerseyTest the parent class for testing Jersey-based applications using Jersey test framework. 
 * @author Marc Uxa
 * 
 */
@SpringBootTest
class RestHomeworkApplicationTests extends JerseyTest {
	/**
	 * Create the tested Jersey application
	 */
	@Override
	protected Application configure() {
		//Provide useful additional logging and debug information during test runs
		enable(TestProperties.LOG_TRAFFIC); 
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(TodoResource.class);
	}
	
	/**
	 * Tests if all Todos are being returned.
	 */
	@Test
	public void testGetAllTodos() {
		Response response = target("/todos").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return all todos", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
	/**
	 * Tests if a specific Todo is returned.
	 */
	@Test
	public void testGetTodoById() {
		Response output = target("/todos/1").request().get();
		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return todo", output.getEntity());
		System.out.println(output.getStatus());
		System.out.println(output.readEntity(String.class));
	}
	
	/**
	 * Tests if a non existent Todo is returned.
	 */
	@Test
	public void testGetTodoByIdFail() {
		Response output = target("/todo/1").request().get();
		assertEquals("Should return status 404", 404, output.getStatus());
		System.out.println(output.getStatus());
		System.out.println(output.readEntity(String.class));
	}
	
	/**
	 * Tests if a Todo is being created.
	 */
	@Test
	public void testCreateTodo() {
		Task[] tasks = {new Task("Name", "Test"), new Task("Second Name", "Second Test")};
		Todo todo = new Todo(1, "Todo number 1", "This is test Todo number 1", tasks);
		Response output = target("/todos").request().post(Entity.entity(todo, MediaType.APPLICATION_JSON));
		System.out.println(output.getStatus());
		assertEquals("Should return status 201", 201, output.getStatus());
	}
	
	/**
	 * Tests if a Todo is being created that misses the mandatory inputs.
	 */
	@Test
	public void testCreateTodoFail() {
		//Case 0: Todo is null.
		Todo todoCaseZero = null;
		Response outputCaseZero = target("/todos").request()
				.post(Entity.entity(todoCaseZero, MediaType.APPLICATION_JSON));
		System.out.println(outputCaseZero.getStatus());
		assertEquals("Should return status 400", 400, outputCaseZero.getStatus());
		
		//Case 1: Name of the Todo is blank.
		Task[] tasksCaseOne = {new Task("Name", "Test"), new Task("Second Name", "Second Test")};
		Todo todoCaseOne = new Todo(1, "", "This is test Todo number 1", tasksCaseOne);
		Response outputCaseOne = target("/todos").request()
				.post(Entity.entity(todoCaseOne, MediaType.APPLICATION_JSON));
		System.out.println(outputCaseOne.getStatus());
		assertEquals("Should return status 400", 400, outputCaseOne.getStatus());
		
		//Case 2: Tasks of the Todo are null.
		Todo todoCaseTwo = new Todo(1, "Todo number 1", "This is test Todo number 1", null);
		Response outputCaseTwo = target("/todos").request()
				.post(Entity.entity(todoCaseTwo, MediaType.APPLICATION_JSON));
		System.out.println(outputCaseTwo.getStatus());
		assertEquals("Should return status 400", 400, outputCaseTwo.getStatus());
		
		//Case 3: Name of a Task is blank.
		Task[] tasksCaseThree = {new Task("", "Test"), new Task("", "Second Test")};
		Todo todoCaseThree = new Todo(1, "Todo number 1", "This is test Todo number 1", tasksCaseThree);
		Response outputCaseThree = target("/todos").request()
				.post(Entity.entity(todoCaseThree, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", 400, outputCaseThree.getStatus());
		System.out.println(outputCaseThree.getStatus());
	}
	
	/**
	 * Tests if a specific Todo is being updated.
	 */
	@Test
	public void testUpdateTodo() {
		Task[] tasks = {new Task("Name", "Test"), new Task("Second Name", "Second Test")};
		Todo todo = new Todo(1, "Todo number 1", "This is test Todo number 1", tasks);
		Response output = target("/todos/1").request().put(Entity.entity(todo, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		System.out.println(output);
	}
	
	/**
	 * Tests if a specific Todo is being updated when the new content misses the mandatory inputs.
	 */
	@Test
	public void testUpdateTodoFail() {
		//Case 0: Name of the Todo is blank.
		Task[] tasksCaseZero = {new Task("Name", "Test"), new Task("Second Name", "Second Test")};
		Todo caseZero = new Todo(1, "", "This is test Todo number 1", tasksCaseZero);
		Response outputCaseZero = target("/todos/1").request()
				.put(Entity.entity(caseZero, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", 400, outputCaseZero.getStatus());
		System.out.println(outputCaseZero.getStatus());
		
		//Case 1: Tasks of the Todo are null.
		Todo caseOne = new Todo(1, "Todo number 1", "This is test Todo number 1", null);
		Response outputCaseOne = target("/todos/1").request()
				.put(Entity.entity(caseOne, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", 400, outputCaseOne.getStatus());
		System.out.println(outputCaseOne.getStatus());
		
		//Case 2: Name of a Task is blank.
		Task[] tasksCaseTwo = {new Task("Name", "Test"), new Task("", "Second Test")};
		Todo caseTwo = new Todo(1, "Todo number 1", "This is test Todo number 1", tasksCaseTwo);
		Response outputCaseTwo = target("/todos/1").request()
				.put(Entity.entity(caseTwo, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 400", 400, outputCaseTwo.getStatus());
		System.out.println(outputCaseTwo.getStatus());
	}
	
	/**
	 * Tests if a Todo is being deleted.
	 */
	@Test
	public void testDelete() {
		Response output = target("/todos/1").request().delete();
		assertEquals("Should return status 204", 204, output.getStatus());
	}
	
	/**
	 * Tests if a Todo is being deleted that does not exist.
	 */
	@Test
	public void testDeleteFail() {
		Response output = target("/todo/1").request().delete();
		assertEquals("Should return status 404", 404, output.getStatus());
	}
}
