package ea.ciges.generatejson.utils;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AuthenticationRequest {
    private String username;
    private Set<Rol> roles = new HashSet<>();


}
