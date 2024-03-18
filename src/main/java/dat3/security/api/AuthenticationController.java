package dat3.security.api;

import dat3.security.dto.LoginRequest;
import dat3.security.dto.LoginResponse;
import dat3.security.dto.OpretRequest;
import dat3.security.dto.OpretResponse;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.service.UserDetailsServiceImp;
import dat3.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

  @Value("${app.token-issuer}")
  private String tokenIssuer;

  @Value("${app.token-expiration}")
  private long tokenExpiration;

  private AuthenticationManager authenticationManager;

  UserService userService;

  JwtEncoder encoder;

  public AuthenticationController(AuthenticationConfiguration authenticationConfiguration, JwtEncoder encoder) throws Exception {
    this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    this.encoder = encoder;
  }

  @PostMapping("opret")
  @Operation(summary = "Opret", description = "Use this to create a new user")
  public ResponseEntity<OpretResponse> opret(@RequestBody OpretRequest request) {
    try {
      // Opret en UserWithRoles-objekt baseret på request
      UserWithRoles userWithRoles = new UserWithRoles();
      userWithRoles.setUsername(request.getUsername());
      userWithRoles.setPassword(request.getPassword());

      // Tilføj en standardrolle til den nye bruger
      Role defaultRole = new Role("ROLE_USER");
      userWithRoles.addRole(defaultRole);

      // Gem den nye bruger i din database eller brug en serviceklasse til at håndtere oprettelsen
      // Eksempel:
      userService.createUser(userWithRoles);

      // Opret en OpretResponse med det oprettede brugernavn og adgangskode
      OpretResponse response = new OpretResponse(userWithRoles.getUsername(), userWithRoles.getPassword());

      // Returner en succesrespons og den oprettede bruger
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      // Hvis der opstår en fejl under oprettelsen, kast en fejlrespons
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
    }
  }


  @PostMapping("login")
  @Operation(summary = "Login", description = "Use this to login and get a token")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

    try {
      UsernamePasswordAuthenticationToken uat = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
      //The authenticate method will use the loadUserByUsername method in UserDetailsServiceImp
      Authentication authentication = authenticationManager.authenticate(uat);

      UserWithRoles user = (UserWithRoles) authentication.getPrincipal();
      Instant now = Instant.now();
      long expiry = tokenExpiration;
      String scope = authentication.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(joining(" "));

      JwtClaimsSet claims = JwtClaimsSet.builder()
              .issuer(tokenIssuer)  //Only this for simplicity
              .issuedAt(now)
              .expiresAt(now.plusSeconds(tokenExpiration))
              .subject(user.getUsername())
              .claim("roles", scope)
              .build();
      JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
      String token = encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
      List<String> roles = user.getRoles().stream().map(role -> role.getRoleName()).toList();
      return ResponseEntity.ok()
              .body(new LoginResponse(user.getUsername(), token, roles));

    } catch (BadCredentialsException | AuthenticationServiceException e) {
      // AuthenticationServiceException is thrown if the user is not found
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UserDetailsServiceImp.WRONG_USERNAME_OR_PASSWORD);
    }
  }
}
