package com.example.crudapp.client.Service;

import com.example.crudapp.client.Entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Role[] roleList();

    Set<Role> getRolesByIds(Long[] ids);

    Role getRoleById(Long id);
}
