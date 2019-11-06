package com.Kh033Java.travelbook.security;

import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class UserFactory {

    public UserFactory() {
    }

    public static DetailsForAuthentication create(User user) {
        return new DetailsForAuthentication(
                user.getId(),
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getType())
                ).collect(Collectors.toList());
    }
}
