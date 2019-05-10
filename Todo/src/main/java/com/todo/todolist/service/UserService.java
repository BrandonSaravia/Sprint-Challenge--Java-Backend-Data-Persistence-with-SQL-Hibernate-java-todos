package com.todo.todolist.service;

import com.todo.todolist.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);
}
