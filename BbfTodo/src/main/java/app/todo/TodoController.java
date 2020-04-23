package app.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

//import com.example.fabrikam.TodoDemo.TodoItem;
//import com.example.fabrikam.TodoDemo.TodoListViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.todo.*;
import app.todo.model.Todo;

import app.todo.repository.*;

import app.viewModel.*;

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

	@PostMapping("/add")
	public @ResponseBody String addTodoApi(@RequestParam String text) {
		Todo todo = new Todo(text);
		repository.save(todo);
		return "Stored Todo";
	}

//	    @RequestMapping("/test")
// 	    public String addItem(@ModelAttribute Todo inTodo) {
// 	    	Todo todo = new Todo(inTodo.getText());
// 	    	repository.save(todo);
// 	    	
// 	    	return "index";
// 	    }

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Todo> getAllData() {
		// This returns a JSON or XML with the users
		
		return repository.findAll();
	}
	
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
	
	@GetMapping(path = "/active2")
	public @ResponseBody Iterable<Todo>   getAllActive(Model model) {
		//Iterable<Todo> todos= repository.findByCompleted(false);
		//buildModel(model, todos);
		//model.addAttribute("filter","Active");
		
		return repository.findByCompleted(true);
	//	repository.findAll();
	// Iterable<Todo> todos=repository.findCompleted(false);
	//		 buildModel(model,todos);
		
		
//		Iterable<String> list = () -> StreamSupport.stream(raw.spliterator(), false)
//		        .filter(text -> !text.isEmpty())
//		        .iterator();
//		
		
	}
	
	  @RequestMapping("/")
	    public String index3(Model model) {
	      //  ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findAll();
	        //model.addAttribute("items", todoList);
	       // model.addAttribute("newitem", new TodoItem());
	      //  model.addAttribute("items", new TodoListViewModel(todoList));
	        return "index3";
	    }
	
	//	private void buildModel(Model model, Iterable<Todo> todos) {
	//		model.addAttribute("todos", todos);
		//	int countCompleted = repository
			
		//	model.addAttribute("countCompleted", countCompleted);
		//	int countActive = repository.countCompleted(false);
		//	model.addAttribute("countActive", countActive);
		//	boolean allComplete = (countActive == 0);
		//	model.addAttribute("allComplete", allComplete);
	//	}
	
	
//	    @PostMapping("/addTodo")
//	    public String addTodo(ModelMap model , @Valid Todo todo, BindingResult result) {
//	      if(result.hasErrors()) {
//	    	  return "/all";
//	      }
//	      repository.sa
//	    }

//	    public void saveInstructor(Instructor instructor) {
//			//Transaction transaction = null;
//			Session session = HibernateUtil.getSessionFactory().openSession()) {
//				// start a transaction
//			//	transaction = session.beginTransaction();
//				// save the student object
//				session.save(instructor);
//				// commit transaction
//			//	transaction.commit();
//			
//				e.printStackTrace();
//			}
//		}

//	    @GetMapping("/api/messages")
//	    @ResponseBody
//	    public ResponseEntity<List<Todo>> getMessages() {
//	      List<Todo> messages = messageService.getMessages();
//	      return ResponseEntity.ok(messages);
//	    }

}
