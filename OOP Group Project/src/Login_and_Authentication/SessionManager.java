package Login_and_Authentication;

public class SessionManager { // used to store the current authenticated user and a Login_and_Authentication.User Object
    private static User authenticatedUser;

    public static void setAuthenticatedUser(User user) {
        authenticatedUser = user;
    }

    public static User getAuthenticatedUser() {
        return authenticatedUser;
    }

}
