package app.todo;

import java.util.stream.StreamSupport;

import app.todo.model.Todo;
import app.todo.repository.TodoRepositoryImpl;


// moved logic from controller class to this place
public class Logic {

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
