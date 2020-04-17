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
  private Integer id;

  @Column(name = "text", nullable = false, length = 128)
  private String text;

  @Column(name = "completed", nullable = false)
  private boolean complete;

  public Todo() {
  }

  public Todo(String text) {
    this.text = text;
    this.complete = false;
  }

  public Integer getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public boolean getCompletedStatus() {
    return complete;
  }

  @Override
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

	
	

