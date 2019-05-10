package com.todo.todolist.service;

import com.todo.todolist.model.Todo;
import com.todo.todolist.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepos;

    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        todoRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Todo save(Todo todo) {
        return todoRepos.save(todo);
    }

    @Transactional
    @Override
    public Todo update(Todo todo, long id) throws EntityNotFoundException {

        Todo currentTodo = todoRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (todo.getDescription() != null) {
            currentTodo.setDescription(todo.getDescription());
        }

        return todoRepos.save(currentTodo);

    }

}
