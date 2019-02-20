package inproj;

import UserAndMessage.Check;
import UserAndMessage.User;
import files.MessengerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marka
 */
public class LoginScreen {

    private String username;
    private String password;
    private boolean isUser = false;

    public LoginScreen() {

    }

    public LoginScreen(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void LoginScreenMenu() throws SQLException, IOException {
        Scanner credentials = new Scanner(System.in);
        Menu menu = new Menu();
        Check check = new Check();

        System.out.println("Welcome to my application. Give your credentials below.");
        System.out.println("Give your username: ");

        String username2 = credentials.next().toLowerCase();
        boolean exists = false;
        while (exists == false) {
            if (check.checkUser(username2) == false) {
                System.out.println("You are not registered, speak with admin for your credentials!Write again your username: ");
                username2 = credentials.next().toLowerCase();
            } else {
                exists = true;
                break;
            }
        }

        System.out.println("Give your password: ");
        String password2 = credentials.next().toLowerCase();

        User user = new User();
        ArrayList<User> users = user.AllUsers();
        for (User u : users) {
            if (username2.equals("admin") && password2.equals("admin")) {
                try {
                    menu.adminMenu(username2);
                    break;
                } catch (MessengerException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (u.getUsername().equals(username2) && u.getPassword().equals(password2)) {
                int r1 = u.getRole();
                try {
                    menu.simpleUserMenu(r1, username2);
                } catch (MessengerException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            }

        }//for
    }
}
