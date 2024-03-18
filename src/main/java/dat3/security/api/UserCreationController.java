package dat3.security.api;

import dat3.security.dto.UserCreationRequest;
import dat3.security.entity.UserWithRoles;
import dat3.security.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth/")
public class UserCreationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("opret")
    public ResponseEntity<?> signUp(@RequestBody UserCreationRequest request) {
        try {
            // Check if username already exists
            if (userDetailsService.loadUserByUsername(request.getUsername()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
            }

            // Create new user entity
            UserWithRoles newUser = new UserWithRoles(
                    request.getUsername(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getEmail()
            );

            // Save new user to database
            userDetailsService.saveUser(newUser);

            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }
}
