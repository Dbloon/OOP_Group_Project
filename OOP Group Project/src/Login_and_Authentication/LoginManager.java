package Login_and_Authentication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginManager {
    public static User authenticate(String inputUsername, String inputPassword) {
        String filePath = "Employees.dat"; // Use a binary file for random access

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String username = raf.readUTF().trim();
                String password = raf.readUTF().trim();
                String role = raf.readUTF().trim();

                if (username.equals(inputUsername) && password.equals(inputPassword)) {
                    User user = new User(username, password, role);
                    SessionManager.setAuthenticatedUser(user);
                    return user;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Authentication Failed");
        return null;
    }
}

