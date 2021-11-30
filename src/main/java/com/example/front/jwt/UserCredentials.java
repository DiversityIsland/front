package com.example.front.jwt;

        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.security.core.GrantedAuthority;

        import java.security.Principal;
        import java.util.ArrayList;
        import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
public class UserCredentials implements Principal {

    private String name;
    private Collection<GrantedAuthority> authorities;

    public UserCredentials(String username, Collection<String> userRoles) {
        authorities = new ArrayList<>();
        setName(username);
        for (String role : userRoles) {
            this.authorities.add(() -> role);
        }
    }
}

