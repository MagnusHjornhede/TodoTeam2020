package app.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import app.todo.model.Todo;;
@Repository
public interface TodoRepositoryImpl extends CrudRepository<Todo, Long> {
	// Returning number of completed todos
	int countByCompleted(boolean completed);
	// Finding all completed todos
	Iterable<Todo> findByCompleted(boolean completed);
	//}
	
}


