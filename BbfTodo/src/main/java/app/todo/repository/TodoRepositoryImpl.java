package app.todo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import app.todo.model.Todo;;

@Service
@Repository //  
public interface TodoRepositoryImpl extends CrudRepository<Todo, Long> {
	// Returning number of completed todos
	int countByCompleted(boolean completed);
	// Finding all completed todos
	//Iterable<Todo> getCompletedStatus(boolean completed);
	//String getText();
	//default void addSomeCustomers() {
	Iterable<Todo> findByCompleted(boolean completed);
	//}
	
}


/*
import app.todo.model.Message;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;






@Component
public class TodoRepository {

  private SessionFactory sessionFactory;

  public TodoRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Message saveMessage(Message message) {
    Session session = sessionFactory.getCurrentSession();
    session.save(message);
    return message;
  }

  public List<Message> getMessages() {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message";							// Quering the data base
    Query<Message> query = session.createQuery(hql, Message.class);
    return query.list();
  }
}
*/