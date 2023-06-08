package ea.ciges.generatejson.utils;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private TokenResponse access_token;
    private TokenResponse refresh_token;
    private long exp;

}


