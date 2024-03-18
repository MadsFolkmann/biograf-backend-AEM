package dat3.security_demo.api;

import dat3.security.entity.Role;
import dat3.security_demo.entity.SpecialUser;
import dat3.security_demo.repository.SpecialUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class SpecialUsersController {

    private final SpecialUserRepository specialUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SpecialUsersController(SpecialUserRepository specialUserRepository, PasswordEncoder passwordEncoder) {
        this.specialUserRepository = specialUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/specialusers")
    public ResponseEntity<List<SpecialUser>> getSpecialUsers() {
        return ResponseEntity.ok(specialUserRepository.findAll());
    }

    @PostMapping("/specialusers")
    public ResponseEntity<String> createSpecialUser(@RequestBody SpecialUser specialUser) {
        try {
            // Encrypt the password before saving the user
            String encodedPassword = passwordEncoder.encode(specialUser.getPassword());

            // Set the encoded password back to the specialUser
            specialUser.setPassword(encodedPassword);

            // Set role to "User"
            specialUser.addRole(new Role("USER")); // Assuming you have a Role entity and a method to add a role to SpecialUser

            // Save the user with the encrypted password and role
            specialUserRepository.save(specialUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Special user created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create special user: " + e.getMessage());
        }
    }
}
