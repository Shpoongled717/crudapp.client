package com.example.crudapp.client.Controller;

import com.example.crudapp.client.Entity.User;
import com.example.crudapp.client.Service.RoleService;
import com.example.crudapp.client.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserRestController {

    private final RoleService roleService;
    private final UserService userService;

    public UserRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/admin/add")
    public User addUser(@ModelAttribute User user, @RequestParam Long[] roleSet) {
        user.setRoleSet(roleService.getRolesByIds(roleSet));
        return userService.add(user);
    }

    @PostMapping(value = "/admin/update")
    public User updateUser(@ModelAttribute User user, @RequestParam Long[] roleSet) {
        user.setRoleSet(roleService.getRolesByIds(roleSet));
        userService.update(user);
        return user;
    }

    @PostMapping(value = "/admin/delete/{id}")
    public Boolean deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
