package dat3.security.dto;

public class UserCreationRequest {
    private String username;
    private String password;
    private String email;

    public UserCreationRequest() {
    }

    public UserCreationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
