package dat3.security.api;

import dat3.security.dto.UserWithRolesRequest;
import dat3.security.dto.UserWithRolesResponse;
import dat3.security.service.UserWithRolesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRoleController {

  UserWithRolesService userWithRolesService;

  public UserWithRoleController(UserWithRolesService userWithRolesService) {
    this.userWithRolesService = userWithRolesService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<UserWithRolesResponse>> getAllUsersWithRoles() {
    List<UserWithRolesResponse> users = userWithRolesService.getAllUsersWithRoles();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @PostMapping
  @Operation(summary = "Add a new UserWithRoles user",
             description = "If a default role is defined (app.default-role ), this role will be assigned to the user.")
  public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
    return userWithRolesService.addUserWithRoles(request);
  }




  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/add-role/{username}/{role}")
  @Operation(summary = "Add a role to a UserWithRoles", description = "Caller must be authenticated with the role ADMIN")
  public UserWithRolesResponse addRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.addRole(username, role);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/remove-role/{username}/{role}")
  @Operation(summary = "Removes a role from a UserWithRoles", description = "Caller must be authenticated with the role ADMIN")
  public UserWithRolesResponse removeRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.removeRole(username, role);
  }
}
