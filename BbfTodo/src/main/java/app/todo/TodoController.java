package app.todo;

import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.todo.model.Todo;
import app.todo.repository.*;
import app.viewModel.BusinessModel;

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
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("message", "Todo API");
		// model.addAttribute("message", "Hello and welcome to ProjectTodo");
		return mv;
	}

	@GetMapping("/getinfoGson")
	public @ResponseBody Optional<Todo> getinfo(@RequestParam long id) {
		//ModelAndView mv = new ModelAndView("welcome");
		return repository.findById(id);
	}

	@RequestMapping("/update2")
	public @ResponseBody String updateSingleTodo(@RequestParam long id,@RequestParam String text, @RequestParam boolean completed ) {
		Todo todo = repository.findById(id).orElse(null);
		todo.setText(text);
		todo.setCompletedStatus(completed);
			repository.save(todo);
		return "Updated todo";
	}
@RequestMapping("/update")
public @ResponseBody String updateThisThing(@ModelAttribute BusinessModel todos) {
	for(Todo todo : todos.getTodoList()) {
		Todo element = new Todo(todo.getText());
		element.setId(todo.getId());
		element.setCompletedStatus(todo.getCompletedStatus());	
		repository.save(element);
	
	}
	return "Updated todo";
}

	@DeleteMapping("/deletepost")
	public @ResponseBody String methodDelete(@RequestParam long id) {
		 
		repository.deleteById(id);
		return "Post deleted";
	}

	@PostMapping("/add")
	public @ResponseBody String addTodoApi(@RequestParam String text) {
		Todo todo = new Todo(text);
		repository.save(todo);
		return "Added todo";
	}

	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Todo> getAllDaata() {
		// This returns a JSON or XML with the users
		return repository.findAll();
	}

	@GetMapping(path = "/totalNumber")
	public @ResponseBody long getNumberTodos() {
		// This returns a JSON or XML with the users
		// Getting number of elements in repository
		return repository.count();
	}

//  Important info about spring jpa
//	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

	@GetMapping(path = "/active")
	public @ResponseBody Iterable<Todo> getAllActive(Model model) {
		return repository.findByCompleted(false);
	}

	//
	@GetMapping(path = "/done")
	public @ResponseBody Iterable<Todo> getAllDone(Model model) {
		return repository.findByCompleted(true);
	}

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

	// @RequestMapping
	@GetMapping("/toggleAll")
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
