package sk.stu.ikt.libraryserver.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import sk.stu.ikt.libraryserver.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements GrantedAuthority {

    Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }
}