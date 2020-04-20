package app.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	    private TodoRepository  repository;
	    
	    @GetMapping("/welcome")
	    public ModelAndView welcome() {
	    	ModelAndView mv= new ModelAndView("welcome");
	    	mv.addObject("message","Hello and this is ModelandView");
	    	//model.addAttribute("message", "Hello and welcome to ProjectTodo");
	    	
	    	return mv;
	    
	    
	    }
	    // Aggregate root

//	    @GetMapping("/employees")
//	    Iterable<Todo> all() {
//	      return repository.findAll();
//	    }
	    
	    
	    
//	    @GetMapping("/countries")
//	    public ModelAndView getCountries() {
//
//	        List<Todo> countries = (List<Todo>) repository.findAll();
//
//	        HashMap<String, Object> params = new HashMap<String, Object>();
//	        params.put("countries", countries);
//
//	        return new ModelAndView("index", params);
//	    }
	    
	
	    @GetMapping(path="/all")
	    public @ResponseBody Iterable<Todo> getAllData() {
	      // This returns a JSON or XML with the users
	      return repository.findAll();
	    }
	    
	    
//	    @GetMapping("/api/messages")
//	    @ResponseBody
//	    public ResponseEntity<List<Todo>> getMessages() {
//	      List<Todo> messages = messageService.getMessages();
//	      return ResponseEntity.ok(messages);
//	    }
		
}

	    
	    
	    
	    
	    
