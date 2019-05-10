package com.todo.todolist.service;

import com.todo.todolist.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo save(Todo todo);

    Todo update(Todo todo, long id);
}
