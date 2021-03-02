package com.example.crudapp.client.Controller;

import com.example.crudapp.client.Service.RoleService;
import com.example.crudapp.client.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserMainController {

    private final RoleService roleService;
    private final UserService userService;

    public UserMainController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin/{id}")
    public String printTable(Model model, @PathVariable Long id) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles", roleService.roleList());
        model.addAttribute("authUser", userService.getUserById(id));
        return "admin";
    }

    @PostMapping(value = "/login")
    public String loginPage(HttpSession session, HttpServletRequest request) {
        session.setAttribute("email", request.getParameter("email"));
        return "login";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/user/{id}")
    public String userPage(@PathVariable Long id, Model model) {
        model.addAttribute("authUser", userService.getUserById(id));
        return "user";
    }
}
