package ea.ciges.generatejson.utils;

import lombok.Data;

import java.util.List;

@Data
public class TokenResponse {
    private String iss;
    private String jti;
    private List<String> roles;
    private long exp;
    private String sub;


}
