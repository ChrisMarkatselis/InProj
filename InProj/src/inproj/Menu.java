package inproj;

import UserAndMessage.Check;
import UserAndMessage.Message;
import UserAndMessage.User;
import UserAndMessage.messageDate;
import db.Database;
import files.FileLogger;
import files.MessengerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marka
 */
public class Menu {

    FileLogger file = new FileLogger();
    messageDate messageTimestamp = new messageDate();
    List<User> lst = new ArrayList();

    private String username;
    private String password;

    public Menu() {

    }

    public Menu(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void adminMenu(String username) throws SQLException, MessengerException, IOException {
        Database db = new Database();
        String exit2;
        Scanner choice2 = new Scanner(System.in);
        Scanner exit_choice = new Scanner(System.in);
        Scanner messageText = new Scanner(System.in);
        Scanner messageId = new Scanner(System.in);
        User user = new User();
        Check checkUser1 = new Check();
        Check checkMsg = new Check();
        Check checkMsgs = new Check();

        do {
            exit2 = "N";
            System.out.println("Choose what you want to do: \n"
                    + "1) Create a user \n"
                    + "2) View all users \n"
                    + "3) Delete a user \n"
                    + "4) Edit a user \n"
                    + "5) Send a message \n"
                    + "6) View all messages \n"
                    + "7) Edit a message \n"
                    + "8) Delete a message \n"
                    + "9) Go back to insert credentials");
            int get_choice = choice2.nextInt();

            switch (get_choice) {

                case 1:
                    System.out.println("Give the username for your user: ");
                    String username1 = choice2.next().toLowerCase();

                    if (username1.equals("admin")) {
                        System.out.println("You try to insert a user with admin credentials. You are not allowed!");
                        break;
                    } else if (checkUser1.checkUser(username1) == true) {
                        System.out.println("You are trying to input an existing username, chooce a new one!");
                        break;
                    } else {
                        System.out.println("Give the password for your user: ");
                        String password = choice2.next().toLowerCase();

                        System.out.println("Chooce the role for your user from below: \n"
                                + "1) View transacted data \n"
                                + "2) View and edit transacted data \n"
                                + "3) View, edit and delete transacted data.");

                        int get_choice2 = choice2.nextInt();
                        if (get_choice2 < 0 && get_choice > 4) {
                            System.out.println("You give wrong role to your user!");
                        } else {
                            switch (get_choice2) {
                                case 1:
                                    user.setRole(1);
                                    break;
                                case 2:
                                    user.setRole(2);
                                    break;
                                case 3:
                                    user.setRole(3);
                                    break;
                            }
                            db.insertUser(username1, password, get_choice2);
                        }
                    }

                    System.out.println("Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 2:

                    db.selectUsers();
                    System.out.println("Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 3:
                    Check ckUser = new Check();
                    db.selectUsers();
                    System.out.println("Give the username of the user you want to delete: ");
                    String usernameDelete = choice2.next().toLowerCase();
                    if (ckUser.checkUser(usernameDelete) == true) {
                        db.deleteUser(usernameDelete);
                    } else {
                        System.out.println("The user you select, is not included in our database.");
                        break;
                    }

                    System.out.println("Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 4:

                    db.selectUsers();
                    System.out.println("Give the username of the user you want to edit: ");
                    String first_name = choice2.next().toLowerCase();
                    if (checkUser1.checkUser(username) == false) {
                        System.out.println("The username don't exists.");
                        break;
                    } else {
                        System.out.println("I have to inform you tha the userId will not change, in users table! Choose what you want to change: \n"
                                + "1) Change the username. \n"
                                + "2) Change the password. \n"
                                + "3) Change the role. \n"
                                + "4) All the above.");
                        int getChoiceUpdate = choice2.nextInt();
                        if (getChoiceUpdate > 0 && getChoiceUpdate <5){
                        do {
                            switch (getChoiceUpdate) {

                                case 1:
                                    System.out.println("Give the new username for your user: ");
                                    String newUsername = choice2.next().toLowerCase();
                                    db.updateUserUsername(first_name, newUsername);
                                    System.out.println("You have edit the user " + first_name + " with the username " + newUsername + " !");
                                        
                                    System.out.println("Want to exit update username menu? (Y/N)");
                                    exit2 = exit_choice.next().toUpperCase();
                                    break;

                                case 2:
                                    System.out.println("Give the new password for your user: ");
                                    String newPassword = choice2.next().toLowerCase();
                                    db.updateUserPassword(first_name, newPassword);
                                    System.out.println("You have edit the user " + first_name + " with the password " + newPassword + " !");

                                    System.out.println("Want to exit update password menu? (Y/N)");
                                    exit2 = exit_choice.next().toUpperCase();
                                    break;

                                case 3:
                                    System.out.println("Give the new role for your user: \n"
                                            + "1) View transacted data \n"
                                            + "2) View and edit trancacted data \n"
                                            + "3) View, edit and delete trancacted data.");
                                    int newRole = choice2.nextInt();
                                    if (newRole > 0 && newRole < 4) {
                                        db.updateUserRole(first_name, newRole);
                                        System.out.println("You have edit the user " + first_name + " with the role " + newRole + " !");
                                    } else {
                                        System.out.println("The number you gave is not listed!");
                                        break;
                                    }

                                    System.out.println("Want to exit update role menu? (Y/N)");
                                    exit2 = exit_choice.next().toUpperCase();
                                    break;

                                case 4:
                                    System.out.println("Give the new username for your user: ");
                                    String newUsername4 = choice2.next().toLowerCase();

                                    System.out.println("Give the new password for your user: ");
                                    String newPassword4 = choice2.next().toLowerCase();
                                    
                                    System.out.println("Give the new role for your user: \n"
                                            + "1) View transacted data \n"
                                            + "2) View and edit trancacted data \n"
                                            + "3) View, edit and delete trancacted data.");
                                    
                                    int newRole4 = choice2.nextInt();
                                    if (newRole4 > 0 && newRole4 < 4) {
                                        db.updateUser(first_name, newUsername4, newPassword4, newRole4);
                                        System.out.println("You have update the user " + first_name + " into " + newUsername4 + " !");

                                    } else {
                                        System.out.println("The number you gave is not listed!");
                                        break;
                                    }

                                    System.out.println("Want to exit update user menu? (Y/N)");
                                    exit2 = exit_choice.next().toUpperCase();
                                    break;
                            }

                        } while (exit2.equals("N"));
                        System.out.println("Want to exit? (Y/N)");
                        exit2 = exit_choice.next().toUpperCase();
                        }
                        else{
                            System.out.println("The number is not listed!");
                        }
                    }
                    break;

                case 5:

                    Scanner messageCred = new Scanner(System.in);
                    System.out.println("Below you will see the registered users in order to choose the one you want to send a message: ");
                    db.selectUsers();
                    System.out.println("Choose a user to send message: ");
                    String user_choice1 = messageCred.next();

                    if (checkUser1.checkUser(user_choice1) == false) {
                        System.out.println("The username you choose, don't exists.");
                        break;
                    } else {
                        System.out.println("Your choice is " + user_choice1 + " in order to sent your message. Type what you want to send him: ");
                        String message1 = messageText.nextLine();
                        
                        db.sendMessage(username, user_choice1, message1);
                        
                        Message msgAdmin = new Message();
                        msgAdmin.setMessage_info(message1);
                        msgAdmin.setReceiver(user_choice1);
                        msgAdmin.setSender(username);
                        msgAdmin.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                        file.writeToFile(msgAdmin);
                    }

                    System.out.println("Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 6:

                    System.out.println("Below you will see all transacted data between users: ");
                    db.viewMessages();

                    System.out.println("Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 7:

                    System.out.println("Bellow you will see all transacted data between users: ");
                    db.viewMessages();
                    System.out.println("Select the message id you want to edit: ");
                    int msgSelected = messageId.nextInt();
                    if (checkMsgs.checkMessages(msgSelected) == true) {

                        System.out.println("Give the new message: ");
                        String message2 = messageText.nextLine();

                        Message msgAdmin = new Message();
                        msgAdmin.setMessage_info(message2);
                        msgAdmin.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                        file.writeEditedFile(msgAdmin);

                        db.updateMessage(msgSelected, message2);
                        System.out.println("You edit the message with id " + msgSelected);

                    } else {

                        System.out.println("The message id you insert does not exists.");
                    }

                    System.out.println("Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 8:

                    System.out.println("Belolw you will see all transacted data between users: ");
                    db.viewMessages();
                    System.out.println("Selecte the message id you want to delete: ");
                    int msgSelected1 = messageId.nextInt();
                    if (checkMsg.checkMessages(msgSelected1) == true) {
                        
                        System.out.println("Type the receiver of the message you want to delete: ");
                        String msgUserSelected = messageId.next();

                        Message msgAdmin = new Message();
                        
                        msgAdmin.setMessageId(msgSelected1);
                        msgAdmin.setReceiver(msgUserSelected);
                        msgAdmin.setSubmit_date_hour(messageTimestamp.messageTimestamp());
                        msgAdmin.setSender(username);

                        file.writeDeletedFile(msgAdmin);
                        
                        db.deleteMessage(msgUserSelected, msgSelected1);
                        
                        System.out.println("You have delete the message with id " + msgSelected1 + " from user " + msgUserSelected);

                    } else {
                        
                        System.out.println("The message id you insert does not exists.");
                        
                    }

                    System.out.println("Want to exit menu? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
                    break;

                case 9:

                    LoginScreen menu = new LoginScreen();
                    menu.LoginScreenMenu();
                    break;

                default:

                    System.out.println("Something when wrong. Want to exit? (Y/N)");
                    exit2 = exit_choice.next().toUpperCase();
            }
        } while (exit2.equals("N"));
    }

    
    
    
    
    
    
    public void simpleUserMenu(int role, String usernameUser) throws SQLException, MessengerException, IOException {
        List<Message> listmessage = Message.selectMessage(usernameUser);
        Database db = new Database();
        String exit_choice2 = "N";
        Scanner role2Exit = new Scanner(System.in);
        Check checkUser1 = new Check();
        Check checkMsg = new Check();
        
        
        System.out.println("Hello dear user!");
        do {
            switch (role) {
                case 1:
                    Scanner role1 = new Scanner(System.in);
                    Scanner role1message = new Scanner(System.in);
                    Scanner case1choice = new Scanner(System.in);
                    Scanner Role1 = new Scanner(System.in);
                    String exit_choice1Role = "Y";

                    System.out.println(listmessage);

                    System.out.println("You have the authority to view and edit the transacted data between users, as you can go back to main menu. What you want to do: \n"
                            + "1) View all messages \n"
                            + "2) Send message to a user \n"
                            + "3) Return main menu ");

                    int getCase1Choice = case1choice.nextInt();

                    do {
                        switch (getCase1Choice) {
                            case 1:

                                System.out.println("Below you will view the transacted data between the users!");
                                db.viewMessages();

                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice1Role = Role1.next().toUpperCase();
                                break;

                            case 2:

                                System.out.println("Select the user you want to communicate with: ");
                                db.selectUsers();
                                String select1user = role1.next();
                                System.out.println("Give your message:");
                                String msg1 = role1message.nextLine();

                                db.sendMessage(usernameUser, select1user, msg1);

                                Message msg = new Message();
                                msg.setMessage_info(msg1);
                                msg.setReceiver(select1user);
                                msg.setSender(usernameUser);
                                msg.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                                file.writeToFile(msg);
                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice1Role = role1.next().toUpperCase();
                                break;

                            case 3:

                                LoginScreen menu = new LoginScreen();
                                menu.LoginScreenMenu();
                                break;
                        }
                    } while (exit_choice1Role.equals("N"));
                    System.out.println("Want to stay at menu? (Y/N)");
                    exit_choice2 = role2Exit.next().toUpperCase();
                    break;

                case 2:

                    Scanner role2 = new Scanner(System.in);
                    Scanner role2message = new Scanner(System.in);

                    System.out.println(listmessage);

                    String exit_choice2Role = "Y";

                    System.out.println("You have the authority to view and edit the transacted data between users, as you can go back to main menu. What you want to do: \n"
                            + "1) View all messages \n"
                            + "2) Edit a message \n"
                            + "3) Send message to a user \n"
                            + "4) Return main menu ");

                    int get_choice = role2.nextInt();

                    do {
                        switch (get_choice) {

                            case 1:
                                System.out.println("Below you will view the transacted data between the users!");
                                db.viewMessages();
                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice2Role = role2.next().toUpperCase();
                                break;

                            case 2:
                                System.out.println("Below you can edit the transacted data between the users! First you must sellect one message id from message list: ");
                                System.out.println(Message.selectMessage(usernameUser));
                                System.out.println("What message id you want to edit? ");
                                int choiceMessageId = role2.nextInt();
                                
                                if (checkMsg.checkMessages(choiceMessageId) == false) {

                                    System.out.println("Give the new message: ");
                                    String newMsg = role2message.nextLine();

                                    Message msg1 = new Message();
                                    
                                    msg1.setMessage_info(newMsg);
                                    msg1.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                                    file.writeEditedFile(msg1);

                                    db.updateMessage(choiceMessageId, newMsg);
                                    
                                    System.out.println("You edit the message with id " + choiceMessageId);

                                } else {

                                    System.out.println("The message id you insert does not exists.");
                                }

                                
                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice2Role = role2.next().toUpperCase();
                                break;

                            case 3:

                                Scanner user2Choice = new Scanner(System.in);
                                db.selectUsers();
                                System.out.println("Select one user from above: ");
                                String select2user = user2Choice.next();
                            
                                if (checkUser1.checkUser(select2user) == true) {
                                    
                                    System.out.println("Your choice is " + select2user + " in order to sent your message. Type what you want to send him: ");
                                    String message1 = role2message.nextLine();
                                    db.sendMessage(usernameUser, select2user, message1);
                                    
                                    Message msg = new Message();
                                    msg.setMessage_info(message1);
                                    msg.setReceiver(select2user);
                                    msg.setSender(usernameUser);
                                    msg.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                                    file.writeToFile(msg);
                                    
                                } else {
                                    
                                    System.out.println("The username you choose, don't exists.");
                                    break;
                                    
                                }
                                
                                System.out.println("Your message was send!");

                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice2Role = role2.next().toUpperCase();
                                break;

                            case 4:
                                LoginScreen menu = new LoginScreen();
                                menu.LoginScreenMenu();
                                break;
                        }
                    } while (exit_choice2Role.equals("N"));
                    System.out.println("Want to stay at menu? (Y/N)");
                    exit_choice2 = role2Exit.next().toUpperCase();
                    break;
                case 3:

                    Scanner role3 = new Scanner(System.in);
                    Scanner role3message = new Scanner(System.in);
                    Scanner user3Choice = new Scanner(System.in);

                    System.out.println(listmessage);

                    String exit_choice3Role = "Y";

                    System.out.println("You have the authority to view and edit the transacted data between users, as you can go back to main menu. What you want to do: \n"
                            + "1) View all messages \n"
                            + "2) Edit a message \n"
                            + "3) Delete message from a user \n"
                            + "4) Send message to a user \n"
                            + "5) Return main menu ");

                    int get_choice3 = role3.nextInt();

                    do {
                        switch (get_choice3) {

                            case 1:
                                System.out.println("Below you will view the transacted data between the users!");
                                db.viewMessages();
                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice3Role = role3.next().toUpperCase();
                                break;

                            case 2:
                                System.out.println("Below you can edit the transacted data between the users! First you must sellect one message id from message list: ");
                                System.out.println(listmessage);
                                System.out.println("What message id you want to edit? ");
                                int choiceMessageId = role3.nextInt();
                                
                                if (checkMsg.checkMessages(choiceMessageId) == false) {

                                    System.out.println("Give the edited message:");
                                    String newMsg = role3message.nextLine();

                                    Message msg1 = new Message();
                                    
                                    msg1.setMessage_info(newMsg);
                                    msg1.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                                    file.writeEditedFile(msg1);

                                    db.updateMessage(choiceMessageId, newMsg);
                                    
                                    System.out.println("You edit the message with id " + choiceMessageId);

                                } else {

                                    System.out.println("The message id you insert does not exists.");
                                }
                                
                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice3Role = role3.next().toUpperCase();
                                break;

                            case 3:

                                System.out.println("Select one user from below in order to delete a message: ");
                                db.selectUsers();
                                String delete3userMessage = user3Choice.next();
                                List<Message> listmessageUser = Message.selectMessage(delete3userMessage);
                                System.out.println(listmessageUser);
                                
                                System.out.println("Select the message id from the message you want to delete: ");
                                int delete3message = user3Choice.nextInt();
                                
                                if (checkMsg.checkMessages(delete3message) == true) {
                                    
                                    System.out.println("Type the receiver of the message you want to delete: ");
                                    String delete3user = user3Choice.next();
                                    
                                    Message deleteMsg = new Message();
                                    
                                    deleteMsg.setMessageId(delete3message);
                                    deleteMsg.setSender(delete3user);
                                    deleteMsg.setSubmit_date_hour(messageTimestamp.messageTimestamp());
                                    deleteMsg.setReceiver(delete3userMessage);
                                    
                                    file.writeDeletedFile(deleteMsg);
                                    
                                    db.deleteMessage(delete3userMessage, delete3message);
                                    System.out.println("You have delete the message with id: " + delete3message + " from the database!");
                                    
                                } else {

                                    System.out.println("The message id you insert does not exists.");
                                }
                                

                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice3Role = role3.next().toUpperCase();
                                break;

                            case 4:

                                db.selectUsers();
                                System.out.println("Select one user from above: ");
                                String select3user = user3Choice.next();
                                System.out.println("Write your message: ");
                                String msg3 = role3message.nextLine();

                                db.sendMessage(usernameUser, select3user, msg3);
                                System.out.println("Your message was send!");

                                Message msg = new Message();
                                msg.setMessage_info(msg3);
                                msg.setReceiver(select3user);
                                msg.setSender(usernameUser);
                                msg.setSubmit_date_hour(messageTimestamp.messageTimestamp());

                                file.writeToFile(msg);

                                System.out.println("Want to exit this choice menu? (Y/N)");
                                exit_choice3Role = role3.next().toUpperCase();
                                break;

                            case 5:
                                LoginScreen menu = new LoginScreen();
                                menu.LoginScreenMenu();
                                break;
                        }
                    } while (exit_choice3Role.equals("N"));
                    System.out.println("Want to stay at menu? (Y/N)");
                    exit_choice2 = role2Exit.next().toUpperCase();
                    break;

                default:
                    System.out.println("You select to exit menu. Bye bye!");
                    break;

            }
        } while (exit_choice2.equals("Y"));
    }

}