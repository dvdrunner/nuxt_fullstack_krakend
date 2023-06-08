package ea.ciges.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ea.ciges.model.Rol;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private String username;


    private String password;

    private Set<Rol> roles = new HashSet<>();
}
