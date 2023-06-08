package ea.ciges.generatejson.controller;

import ea.ciges.generatejson.utils.AuthenticationRequest;
import ea.ciges.generatejson.utils.AuthenticationResponse;
import ea.ciges.generatejson.utils.TokenResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import java.util.stream.Collectors;
import ea.ciges.generatejson.utils.Rol;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    // Generate a secure key
    private final KeyPair keyPair;

    public AuthenticationController() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Initialize with a key size of 2048 bits
        keyPair = keyGen.generateKeyPair();
    }

    @PostMapping("/signin")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        // Aquí puedes validar la solicitud de inicio de sesión y verificarla con las credenciales almacenadas

        long expirationTimeInMillisecondsForAccessToken = 864000000; // 10 días
        long expirationTimeInMillisecondsForRefreshToken = 864000000 * 2; // 20 días
        Date issueDate = new Date();
        Date expirationDateForAccessToken = new Date(issueDate.getTime() + expirationTimeInMillisecondsForAccessToken);
        Date expirationDateForRefreshToken = new Date(issueDate.getTime() + expirationTimeInMillisecondsForRefreshToken);

        List<String> roles = Optional.ofNullable(authenticationRequest.getRoles())
                .orElse(Collections.emptySet())
                .stream()
                .map(Rol::getRolNombre)
                .collect(Collectors.toList());

        TokenResponse access_token = new TokenResponse();
        access_token.setIss("http://generator-json:8400");
        access_token.setSub(authenticationRequest.getUsername()); // El sujeto suele ser el nombre de usuario
        access_token.setJti("mnb23vcsrt756yuiomnbvcx98ertyuiop");
        access_token.setRoles(roles);
        access_token.setExp(expirationDateForAccessToken.getTime() / 1000); // El tiempo de expiración debe estar en segundos

        TokenResponse refresh_token = new TokenResponse();
        refresh_token.setIss("http://generator-json:8400");
        refresh_token.setSub(authenticationRequest.getUsername()); // El sujeto suele ser el nombre de usuario
        refresh_token.setJti("mnb23vcsrt756yuiomnbvcx98ertyuiop");
        refresh_token.setRoles(roles);
        refresh_token.setExp(expirationDateForRefreshToken.getTime() / 1000); // El tiempo de expiración debe estar en segundos

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccess_token(access_token);
        authenticationResponse.setRefresh_token(refresh_token);
        authenticationResponse.setExp(expirationDateForAccessToken.getTime() / 1000); // El tiempo de expiración debe estar en segundos

        return authenticationResponse;
    }




    @GetMapping("/jwks.json")
    public ResponseEntity getJwks() throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPublicKeySpec spec = new RSAPublicKeySpec(publicKey.getModulus(), publicKey.getPublicExponent());
        String rsaPublicKey = Base64.getEncoder().encodeToString(KeyFactory.getInstance("RSA").generatePublic(spec).getEncoded());

        Map<String, Object> jwksMap = new HashMap<>();
        Map<String, Object> jwkMap = new HashMap<>();
        jwkMap.put("kty", "RSA");
        jwkMap.put("alg", "RS256");
        jwkMap.put("use", "sig");
        jwkMap.put("n", rsaPublicKey);
        jwkMap.put("e", publicKey.getPublicExponent().toString());
        jwksMap.put("keys", Collections.singletonList(jwkMap));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(jwksMap, headers, HttpStatus.OK);
    }

}
