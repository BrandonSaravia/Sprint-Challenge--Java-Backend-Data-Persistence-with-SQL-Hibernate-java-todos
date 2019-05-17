package com.todo.todolist.controller;

import com.todo.todolist.model.Todo;
import com.todo.todolist.service.TodoService;
import com.todo.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/users/todo/{userid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUserTodoItem(@Valid @RequestBody Todo newTodo, @PathVariable long userid) throws URISyntaxException {
        newTodo = todoService.save(newTodo);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTodoURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/todoid").buildAndExpand(newTodo.getTodoid()).toUri();
        responseHeaders.setLocation(newTodoURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/admin/zoos/{id}")
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long id) {
        todoService.update(updateTodo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserName(Authentication authentication) {
            return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }
}



