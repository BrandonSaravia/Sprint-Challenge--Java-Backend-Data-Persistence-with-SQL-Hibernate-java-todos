package com.todo.todolist.service;

import com.todo.todolist.model.Todo;
import com.todo.todolist.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;


    @Transactional
    @Override
    public Todo save(Todo todo) {

        return todoRepository.save(todo);

    }

    @Transactional
    @Override
    public Todo update(Todo todo, long id) throws EntityNotFoundException {
        Todo currentTodo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (todo.getDescription() != null) {
            currentTodo.setDescription(todo.getDescription());
        }

        return todoRepository.save(currentTodo);
    }

    @Override
    public List<Todo> findByUserName(String username) {
        List<Todo> list = new ArrayList<>();
        todoRepository.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
