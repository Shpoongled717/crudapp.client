package com.example.crudapp.client.Service;

import com.example.crudapp.client.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User add(User user);

    User[] listUsers();

    Boolean delete(Long id);

    void update(User user);

    User getUserByName(String username);

    User getUserById(Long id);
}
