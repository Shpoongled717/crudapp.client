package com.example.crudapp.client.Config;

import com.example.crudapp.client.Entity.User;
import com.example.crudapp.client.Service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    public LoginSuccessHandler(RoleService roleService) {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User user = (User) (authentication.getPrincipal());
        if (AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                .contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/" + user.getId());
        } else {
            httpServletResponse.sendRedirect("/user/" + user.getId());
        }
    }
}
