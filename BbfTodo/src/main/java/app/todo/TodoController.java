package app.todo;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
import java.util.Optional;
//import java.util.stream.StreamSupport;
import java.util.stream.StreamSupport;

//import javax.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

//import com.example.fabrikam.TodoDemo.TodoItem;
//import com.example.fabrikam.TodoDemo.TodoListViewModel;
//import com.fasterxml.jackson.databind.ObjectMapper;

//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

//import app.todo.*;
import app.todo.model.Todo;

import app.todo.repository.*;

//import app.viewModel.*;

@Controller
@RequestMapping("/api")
public class TodoController {
	@Autowired
	private TodoRepositoryImpl repository;

	@GetMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("message", "Hello and this is TODO");
		// model.addAttribute("message", "Hello and welcome to ProjectTodo");
		return mv;
	}

	@GetMapping("/getinfo")
	public @ResponseBody Optional<Todo> getinfo(@RequestParam long id) {
		// ModelAndView mv = new ModelAndView("welcome");
		// mv.addObject("message", todo);
		// model.addAttribute("message", "Hello and welcome to ProjectTodo");
		return repository.findById(id);
	}
	
	@DeleteMapping("/deletepost")
	public String deletePost(@RequestParam long id) {
	repository.deleteById(id);	
		return "deleted";
	}

	
	
	
	@PostMapping("/add")
	public @ResponseBody String addTodoApi(@RequestParam String text) {
		Todo todo = new Todo(text);
		repository.save(todo);
		return "Stored Todo";
	}

	
	
	@PostMapping("/")
	public String createNewTodo(Todo todo) {
		repository.save(todo);
		return "redirect:/";
	}

	

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Todo> getAllData() {
		// This returns a JSON or XML with the users
		return repository.findAll();
	}

	@GetMapping(path = "/totalNumber")
	public @ResponseBody long getNumberTodos() {
		// This returns a JSON or XML with the users
		return repository.count();
	}

// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

	@GetMapping(path = "/active")
	public @ResponseBody Iterable<Todo> getAllActive(Model model) {
		return repository.findByCompleted(false);
	}

	@GetMapping(path = "/done")
	public @ResponseBody Iterable<Todo> getAllDone(Model model) {
		return repository.findByCompleted(true);
	}

	@RequestMapping("/")
	public String index3(Model model) {
		// ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findAll();
		// model.addAttribute("items", todoList);
		// model.addAttribute("newitem", new TodoItem());
		// model.addAttribute("items", new TodoListViewModel(todoList));
		return "index3";
	}

//	int ecounter = StreamSupport.stream(elements.spliterator(), false).count();
//	for (Object i : elements) {
//	    ecounter++;
//	}
//	
//	int ccounter = 0;
//	for (Object i : elementsCompleted) {
//	    ccounter++;
//	}
//	@DeleteMapping("/deletepost")
//	public String deletePost(@RequestParam long id) {
//	repository.deleteById(id);	
//		return "deleted";
//	}
	@RequestMapping("/toggleone")
	public String toggleOneElement(@RequestParam long id) {
		 Todo todo = repository.findById(id).orElse(null);
 
		boolean completed = todo.getCompletedStatus();
		   todo.setCompletedStatus(!completed);
		repository.save(todo);
	
		return"Inverting todo status";
	}
	
	
	//@RequestMapping
	@GetMapping("/toggleAll")
	public String toggleAllElements() {
		Iterable<Todo> elements= repository.findAll();
		Iterable<Todo> elementsCompleted= repository.findByCompleted(true);
		// If all are completed then set all active
		if(StreamSupport.stream(elements.spliterator(), false).count()== StreamSupport.stream(elementsCompleted.spliterator(), false).count()) {
			{
				for(Todo element:elements) {
				//boolean completed = element.getCompletedStatus();
				element.setCompletedStatus(false);
				repository.save(element);
			}}
			return "Setting false";
		}
		else
		for(Todo element:elements) {
			//boolean completed = element.getCompletedStatus();
			element.setCompletedStatus(true);
			repository.save(element);
		}
		//return "redirect:/";
		return"Setting true";
	}
	
//	    @GetMapping("/api/messages")
//	    @ResponseBody
//	    public ResponseEntity<List<Todo>> getMessages() {
//	      List<Todo> messages = messageService.getMessages();
//	      return ResponseEntity.ok(messages);
//	    }

}
//}
//Iterable<Todo> elements= repository.findAll();
//Iterable<Todo> elementsCompleted= repository.findByCompleted(true);
// If all are completed then set all active
//if(StreamSupport.stream(elements.spliterator(), false).count()== StreamSupport.stream(elementsCompleted.spliterator(), false).count()) {
//	{
//		for(Todo element:elements) {
//		//boolean completed = element.getCompletedStatus();
//		element.setCompletedStatus(false);
//		repository.save(element);
//	}}
//	return "Setting false";
//}
//else
//for(Todo element:elements) {
//	//boolean completed = element.getCompletedStatus();
//	element.setCompletedStatus(true);
//	repository.save(element);
//}
//return "redirect:/";