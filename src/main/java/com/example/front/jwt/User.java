package com.example.front.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.UnaryOperator;

@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private Collection<GrantedAuthority> userRoles = new ArrayList<>();

    public User(String username, String password, String email, Collection<String> userRoles) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        for (String role : userRoles) {
            this.userRoles.add(() -> role);
        }
    }

    public User(String username, String password, String email, String... userRoles) {
        this(username, password, email, Arrays.asList(userRoles));
    }
    
    
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
    }
    
}
