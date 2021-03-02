package com.example.crudapp.client.Service;

import com.example.crudapp.client.Entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public User add(User user) {
        final String uri = "http://localhost:8088/admin/add";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        return restTemplate.postForObject(uri, user, User.class);
    }

    @Override
    public User[] listUsers() {
        final String uri = "http://localhost:8088/admin/users";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        ResponseEntity<User[]> result = restTemplate.getForEntity(uri, User[].class);
        User[] users = result.getBody();
        return users;
    }

    @Override
    public Boolean delete(Long id) {
        final String uri = "http://localhost:8088/admin/delete/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        Boolean result = restTemplate.getForObject(uri + id, Boolean.class);
        return result;
    }

    @Override
    public void update(User user) {
        final String uri = "http://localhost:8088/admin/update";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        restTemplate.postForObject(uri, user, User.class);
    }

    @Override
    public User getUserByName(String email) {
        final String uri = "http://localhost:8088/users/get/";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        return restTemplate.getForObject(uri + email, User.class);
    }

    @Override
    public User getUserById(Long id) {
        final String uri = "http://localhost:8088/admin/users/";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        return restTemplate.getForObject(uri + id, User.class);
    }
}
