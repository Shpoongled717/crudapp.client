package com.example.crudapp.client.Service;

import com.example.crudapp.client.Entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    RestTemplate restTemplate = new RestTemplate();

    public Role[] roleList() {
        final String uri = "http://localhost:8088/roles";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        ResponseEntity<Role[]> result = restTemplate.getForEntity(uri, Role[].class);
        return result.getBody();
    }

    public Set<Role> getRolesByIds(Long[] ids) {
        final String uri = "http://localhost:8088/roles";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        ResponseEntity<Set> result = restTemplate.postForEntity(uri, ids, Set.class);
        return result.getBody();
    }

    public Role getRoleById(Long id) {
        final String uri = "http://localhost:8088/roles/get/";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        Role result = restTemplate.getForObject(uri + id, Role.class);
        return result;
    }
}
