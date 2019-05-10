package com.todo.todolist;

import com.todo.todolist.model.Role;
import com.todo.todolist.model.Todo;
import com.todo.todolist.model.User;
import com.todo.todolist.model.UserRoles;
import com.todo.todolist.repo.RolesRepository;
import com.todo.todolist.repo.TodoRepository;
import com.todo.todolist.repo.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RolesRepository rolerepos;
    UsersRepository userrepos;
    TodoRepository todorepos;

    public SeedData(RolesRepository rolerepos, UsersRepository userrepos, TodoRepository todorepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos = todorepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        rolerepos.save(r1);
        rolerepos.save(r2);

        User u1 = new User("barnbarn", "ILuvM4th!", users);
        User u2 = new User("admin", "password", admins);
        User u3 = new User("Bob", "password", users);
        User u4 = new User("Jane", "password", users);

        // the date and time string should get coverted to a datetime Java data type. This is done in the constructor!
        u4.getTodos().add(new Todo("Finish java-orders-swagger", "2019-01-13 04:04:04", u4));
        u4.getTodos().add(new Todo("Feed the turtles", "2019-03-01 04:04:04", u4));
        u4.getTodos().add(new Todo("Complete the sprint challenge", "2019-02-22 04:04:04", u4));

        u3.getTodos().add(new Todo("Walk the dogs", "2019-01-17 04:04:04", u3));
        u3.getTodos().add(new Todo("provide feedback to my instructor", "2019-02-13 04:04:04", u3));

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
        userrepos.save(u4);
    }
}