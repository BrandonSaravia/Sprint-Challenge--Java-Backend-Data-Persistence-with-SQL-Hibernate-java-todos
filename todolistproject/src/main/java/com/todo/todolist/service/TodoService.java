package com.todo.todolist.service;

import com.todo.todolist.model.Todo;

import java.util.List;

public interface TodoService {
    Todo save(Todo todo);

    Todo update(Todo todo, long id);

    List<Todo> findByUserName (String username);


}
