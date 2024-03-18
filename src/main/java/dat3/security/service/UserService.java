package dat3.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;

@Service
public class UserService {

    @Autowired
    private UserWithRolesRepository userWithRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(UserWithRoles user) {
        // Kontroller, om brugernavnet allerede eksisterer i databasen
        if (userWithRolesRepository.existsById(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // Krypter adgangskoden før du gemmer den i databasen
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // Gem den nye bruger i databasen
        userWithRolesRepository.save(user);
    }
}
