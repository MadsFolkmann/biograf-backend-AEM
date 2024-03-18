package dat3.security_demo.api;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dat3.security_demo.entity.SpecialUser;
import dat3.security_demo.repository.SpecialUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/")
public class SpecialUsersController {

    private final SpecialUserRepository specialUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SpecialUsersController(SpecialUserRepository specialUserRepository, PasswordEncoder passwordEncoder) {
        this.specialUserRepository = specialUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/specialUsers")
    public ResponseEntity<String> createSpecialUser(@RequestBody SpecialUser specialUser) {
        try {
            // Krypter adgangskoden, før du gemmer brugeren
            String encodedPassword = passwordEncoder.encode(specialUser.getPassword());
            specialUser.setPassword(encodedPassword);

            // Gem den specielle bruger i databasen
            specialUserRepository.save(specialUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Special user created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create special user: " + e.getMessage());
        }
    }
}
