package db;

import UserAndMessage.User;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author marka
 */
public class Database {
    
    User user = new User();
    
    public String getUsername(){
        String username = "christos";
        return username;
    }
    
    public String getPassword(){
        String password = "markatselhs4";
        return password;
    }
    public String getConnectionUrl(){
        //SQL connection string
        String sql = "jdbc:mysql://localhost:3306/indi_project?serverTimezone=Europe/Athens&characterEncoding=utf-8&autoReconnect=true";
        return sql;
    }
    
    public Connection connectToDB(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(this.getConnectionUrl(),this.getUsername(),this.getPassword());
        } catch(SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return conn;
    }

    public void selectUsers(){
        String sql = "SELECT userId, username, password, role FROM indi_project.users";
        try(Connection conn = this.connectToDB();
            java.sql.Statement stmt =  conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                System.out.println(rs.getInt("userId") + " | " + rs.getString("username") + " | " + rs.getString("password") + " | " + rs.getInt("role"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertUser(String username, String password, int role){
        String sql = "INSERT INTO users(username, password, role) VALUES(?,?,?)";
        
        try(Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, role);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUser(String username){
        String sql = "DELETE  FROM indi_project.users WHERE username = ? ";
        
        try (Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
        public void updateUser(String first_name, String newUsername, String newPassword, int role){
        String sql = "UPDATE indi_project.users SET username = ? , password = ? , role = ? WHERE username = ?";
        
        try (Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newPassword);
            pstmt.setInt(3, role);
            pstmt.setString(4, first_name);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public void updateUserUsername (String first_name, String username){
        String sql = "UPDATE indi_project.users SET username = ? WHERE username = ?";
        
        try (Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username); 
            pstmt.setString(2, first_name);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
        public void updateUserPassword (String first_name, String password){
        String sql = "UPDATE indi_project.users SET password = ? WHERE username = ?";
        
        try (Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, password); 
            pstmt.setString(2, first_name);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  
        public void updateUserRole (String first_name, int role){
        String sql = "UPDATE indi_project.users SET role = ? WHERE username = ?";
        
        try (Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, role); 
            pstmt.setString(2, first_name);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
        public void sendMessage(String sender, String receiver,String message) {
            String sql = "INSERT INTO messages(submit_date_hour,sender,receiver,messageData ) VALUES(?,?,?,?)";
        
            try(Connection conn = this.connectToDB();
                PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                pstmt.setString(2, sender);
                pstmt.setString(3, receiver);
                pstmt.setString(4, message);
                
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void viewMessages(){
            
            String sql = "SELECT messageId,submit_date_hour, sender, receiver, messageData FROM indi_project.messages";
            try(Connection conn = this.connectToDB();
                java.sql.Statement stmt =  conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){

                while(rs.next()){
                    System.out.println(rs.getInt("messageId") + " | " + rs.getTimestamp("submit_date_hour") + " | " + rs.getString("sender") + " | " + rs.getString("receiver") + " | " + rs.getString("messageData"));
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void updateMessage (int choiceMessageId, String messageData){
            String sql = "UPDATE indi_project.messages SET messageData = ?  WHERE messageId = ?";
        
            try (Connection conn = this.connectToDB();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, messageData);
                pstmt.setInt(2, choiceMessageId);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
        
        public void deleteMessage(String receiver, int messageId){
        String sql = "DELETE  FROM indi_project.messages WHERE receiver = ? AND messageID=? ";
        
        try (Connection conn = this.connectToDB();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, receiver);
            pstmt.setInt(2, messageId);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}

    
