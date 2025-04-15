package Login_and_Authentication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginManager {
    public static User authenticate(String inputUsername, String inputPassword, int failedAttempts) {
        String username = "";
        String password = "";
        String role = "";
        int failedAttempt = 0;
        boolean isLocked = false;

        String filePath = "Employees.dat"; // Use a binary file for random access

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                username = raf.readUTF().trim();
                password = raf.readUTF().trim();
                role = raf.readUTF().trim();
                

                if (username.equals(inputUsername)){
                    if(isLocked){
                        System.out.println("Account is locked. Contact the administrator.");
                        return null;
                    }

                    if(password.equals(inputPassword)){
                        User user = new User(username, password, role,failedAttempts, false);
                        SessionManager.setAuthenticatedUser(user);
                        return user;
                    }else{
                        if(failedAttempts >= 3){
                            isLocked = true;
                            System.out.print("Account has been locked due to too many failed attempts");
                            return null;
                        }
                    }

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

