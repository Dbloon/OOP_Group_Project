package Login_and_Authentication;

public class User {
    private final String username, password, role;
    private boolean isLocked;
    private int failedAttempts;
    private static final int MAX_FAILED_ATTEMPTS = 3; // Lock threshold

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.isLocked = false;
        this.role = role;
        this.failedAttempts = 0;
    }

    // Setters
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void resetFailedAttempts() {
        failedAttempts = 0;
        isLocked = false; // Unlock account when attempts reset
    }

    // Increment failed attempts and auto-lock if exceeded limit
    public void incrementFailedAttempts() {
        if (!isLocked) {
            failedAttempts++;
            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                isLocked = true;
                System.out.println("Account locked due to too many failed attempts.");
            }
        }
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome, " + username + "! Signed in as " + role + ".");
    }
}
