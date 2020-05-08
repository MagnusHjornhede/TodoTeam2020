package app.todo;

import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.todo.model.Todo;
import app.todo.repository.*;
 
@Controller
@RequestMapping("/api")
public class TodoController {

	// constructor injected
	private TodoRepositoryImpl repository;

	@Autowired
	public TodoController(TodoRepositoryImpl repository) {
		this.repository = repository;
	}

	@GetMapping("/welcome")
	public ModelAndView welcome() {
		// Building up a mv and send it to welcome html
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("message", "Todo API, use Postman to execute the commands");
		return mv;
	}
	
//	/**
//	 * Method that returns number of "active" todos
//	 * @return
//	 */
//	@GetMapping("/activetodos")
//	 private int activeNumbers() {
//	       return repository.countByCompleted(false);
//	    }
//	
	/**
	 * Method that check the repository for a specific id
	 * @param id
	 * @return
	 */
	@GetMapping("/getinfogson")
	public @ResponseBody Optional<Todo> getinfo(@RequestParam long id) {
		return repository.findById(id);
	}
	
/**
 * Updating a specific todo and all it's attributes
 * @param id
 * @param text
 * @param completed
 * @return
 */
	@RequestMapping("/update2")
	public @ResponseBody String updateSingleTodo(@RequestParam long id, @RequestParam String text,
			@RequestParam boolean completed) {
		Todo todo = repository.findById(id).orElse(null);
		todo.setText(text);
		todo.setCompletedStatus(completed);
		repository.save(todo);
		return "Updated todo";
	}

/**
 * Deleting a specific post in the repository
 * @param id
 * @return
 */
	@DeleteMapping("/deletepost")
	public @ResponseBody String methodDelete(@RequestParam long id) {

		repository.deleteById(id);
		return "Post deleted";
	}
	
/**
 * Adding one todo to the repository.
 * @param text
 * @return
 */
	@PostMapping("/add")
	public @ResponseBody String addTodoApi(@RequestParam String text) {
		Todo todo = new Todo(text);
		repository.save(todo);
		return "Added todo";
	}
/**
 * Dumping the entire repository with .findAll() .
 * @return   All entities.
 */
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Todo> getAllDaata() {
		// This returns a JSON or XML with the users
		return repository.findAll();
	}
/**
 * Returning the numbers of todos in repository with count().
 * @return int 
 */
	@GetMapping(path = "/totalnumber")
	public @ResponseBody long getNumberTodos() {
		// This returns a JSON or XML with the users
		// Getting total of number of elements in repository
		return repository.count();
	}

//  Important info about spring jpa
//	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
/**
 * Returns all active todos in repository with findByCompleted(false) as iterable.
 * @param model  (optional)
 * @return Iterable<Todo> 
 */
	@GetMapping(path = "/active")
	public @ResponseBody Iterable<Todo> getAllActive(Model model) {
		return repository.findByCompleted(false);
	}

/**
 * Returns completed todos in repository.
 * @param model  (optional)
 * @return Iterable<Todo>
 */
	@GetMapping(path = "/done")
	public @ResponseBody Iterable<Todo> getAllDone(Model model) {
		return repository.findByCompleted(true);
	}
/**
 * Toggles active/done on one todo in repository.
 * It reads current boolean completed status of the todo and inverts it.  
 * @param id  id of the element to be modified.
 * @return String  confirmation message.
 */
	@RequestMapping("/toggleone")
	public @ResponseBody String toggleOneElement(@RequestParam long id) {
		// Getting specific element by Id
		Todo todo = repository.findById(id).orElse(null);
		// Inverting the boolean value
		boolean completed = todo.getCompletedStatus();
		todo.setCompletedStatus(!completed);
		// Saving data
		repository.save(todo);

		return "Inverting todo status";
	}

	/**
	 * Toggles all todos done/active.
	 * Reading all elements from repository as iterable and reads all elements 
	 * that are completed in another iterable list.
	 * These iterables are compared to find out if the entire list is already
	 * completed or not. If not all elements are completed then the list must 
	 * toggle completed property as completed. Other case is that the entire list is already 
	 * is set as completed and then set that as false for every element.
	 * 
	 * @return string confirmation
	 */
	@GetMapping("/toggleall")
	public @ResponseBody String toggleAllElements() {
		Iterable<Todo> elements = repository.findAll();
		Iterable<Todo> elementsCompleted = repository.findByCompleted(true);
		// If all are completed then set all active
		if (StreamSupport.stream(elements.spliterator(), false).count() == StreamSupport
				.stream(elementsCompleted.spliterator(), false).count()) {
			{
				for (Todo element : elements) {
					// boolean completed = element.getCompletedStatus();
					element.setCompletedStatus(false);
					repository.save(element);
				}
			}
			return "Setting all false";
		} else
			for (Todo element : elements) {
				// Setting all to true
				element.setCompletedStatus(true);
				repository.save(element);
			}

		return "Setting all true";
	}

}

// Ignore this
//@RequestMapping("/update")
//public @ResponseBody String updateThisThing(@ModelAttribute BusinessModel todos) {
//	for (Todo todo : todos.getTodoList()) {
//		Todo element = new Todo(todo.getText());
//		element.setId(todo.getId());
//		element.setCompletedStatus(todo.getCompletedStatus());
//		repository.save(element);
//	}
//	return "Updated todo";
//}

//@GetMapping("/")
//public String mainer(Model model) {
//	ArrayList<Todo> todoList = (ArrayList<Todo>) repository.findAll();
//	model.addAttribute("NotDoneTodos", activeNumbers());
//	model.addAttribute("newItem", new Todo());
//	model.addAttribute("items", new BusinessModel(todoList));
//	return "index"; // Hmmm change to "todos" --- clean up
//}