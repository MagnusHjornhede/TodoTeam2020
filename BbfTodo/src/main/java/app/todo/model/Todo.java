package app.todo.model;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "todos")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "text", nullable = false, length = 128)
  private String text;

  @Column(name = "completed", nullable = false)
  private boolean completed;

  public Todo() {
  }

  public Todo(String text) {
    this.text = text;
    this.completed = false;
  }

public Todo(String text, boolean completed) {
	this.text= text;
	this.completed= completed;
}

  
  public Long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public boolean getCompletedStatus() {
    return completed;
  }

  @Override // Delete?
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Todo todo = (Todo) object;
    return Objects.equals(id, todo.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

	
	

