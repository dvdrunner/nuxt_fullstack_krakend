package ea.ciges.service;

import ea.ciges.dto.UserDto;
import ea.ciges.model.Usuario;
import ea.ciges.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;


    public Mono<String> login(UserDto userDto) {
        try {
            if (userDto.getUsername() == null) {
                throw new RuntimeException("Username cannot be null");
            }

            Usuario user = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword())
                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));

            WebClient webClient = webClientBuilder.build();

            user.setPassword(null);
            // Replace with the URL of your token generator service
            return webClient.post()
                    .uri("http://generator-json:8080/api/auth/signin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(user))
                    .retrieve()
                    .bodyToMono(String.class);
        } catch (RuntimeException e) {
            log.error("Error: {}", e.getMessage());
            return Mono.error(e);
        }
    }




}




