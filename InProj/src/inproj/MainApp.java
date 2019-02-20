/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inproj;

import files.MessengerException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author marka
 */
public class MainApp {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, MessengerException, IOException {
        // TODO code application logic here
        
        LoginScreen menu = new LoginScreen();
        menu.LoginScreenMenu();
    }
    
}
