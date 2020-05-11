package app.todo;

import java.util.stream.StreamSupport;

import app.todo.model.Todo;
import app.todo.repository.TodoRepositoryImpl;

public class Logic {

	
	
	public String extractedRequest(TodoRepositoryImpl repository) throws Exception {
		Iterable<Todo> elements = repository.findAll();
		Iterable<Todo> elementsCompleted = repository.findByCompleted(true);
		if (elements.equals(elementsCompleted)) {
			for (Todo element : elements) {
				// boolean completed = element.getCompletedStatus();
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

//	private boolean extracted(Iterable<Todo> elements, Iterable<Todo> elementsCompleted) {
//		return StreamSupport.stream(elements.spliterator(), false).count() == StreamSupport
//				.stream(elementsCompleted.spliterator(), false).count();
//	}

}
