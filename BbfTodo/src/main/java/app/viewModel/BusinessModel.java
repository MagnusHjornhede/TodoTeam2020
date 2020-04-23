package app.viewModel;
import java.util.ArrayList;
import app.todo.model.*;
// import javax.validation.Valid;
public class BusinessModel {


		//@Valid
		private ArrayList<Todo> todoList = new ArrayList<Todo>();
		
		public BusinessModel() {}
		
		public BusinessModel(ArrayList<Todo> todoList) {
			this.todoList = todoList;
		}

		public ArrayList<Todo> getTodoList() {
			return todoList;
		}

		public void setTodoList(ArrayList<Todo> todoList) {
			this.todoList = todoList;
		}
	
//		public Iterable<Todo> getCompletedStatus(boolean completed){
//			 return completed;
//		}
}
