package com.todo.todolist.repo;

import com.todo.todolist.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository  extends CrudRepository<Todo, Long> {
}
