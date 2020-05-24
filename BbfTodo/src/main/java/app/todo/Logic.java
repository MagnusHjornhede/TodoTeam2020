package app.todo;

import java.util.stream.StreamSupport;

import app.todo.model.Todo;
import app.todo.repository.TodoRepositoryImpl;


// moved logic from controller class to this place
public class Logic {

	
     /* Toggles all todos done/active. Reading all elements from repository as
     * iterable and reads all elements that are completed in another iterable list.
     * These iterables are compared to find out if the entire list is already
     * completed or not. If not all elements are completed then the list must toggle
     * completed property as completed. Other case is that the entire list is
     * already is set as completed and then set that as false for every element.
     */	
	public String extractedRequest(TodoRepositoryImpl repository) throws Exception {
		Iterable<Todo> elements = repository.findAll();
		Iterable<Todo> elementsCompleted = repository.findByCompleted(true);
		if (elements.equals(elementsCompleted)) {
			for (Todo element : elements) {
				element.setCompletedStatus(false);
				repository.save(element);
			}
		} else {
			for (Todo element : elements) {
				// Setting all to true
				element.setCompletedStatus(true);
				repository.save(element);
			}
		}
		return "Setting all true";

	}

}
