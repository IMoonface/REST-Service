# REST-Service Homework (Gradle Version)
A simple REST-Service created with the help of Java, Spring Boot and Gradle.
The service allow managing Todos.
A Todo contains an arbitrary list of subtasks and is
structured as described below:
```
{
  id [mandatory]
  name [mandatory]
  description
  tasks: [
    {
      name [mandatory]
      description
    }
  ]
}
```
The service support the following endpoints:
* **GET** /todos -> Returns a list of all Todos
* **POST** /todos -> Expects a Todo (without id) and returns a Todo with id
* **GET** /todos/{id} -> Returns a Todo
* **PUT** /todos/{id} -> Overwrites an existing Todo
* **DELETE** /todos/{id} -> Deletes a Todo
