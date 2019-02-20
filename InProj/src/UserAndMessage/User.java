package UserAndMessage;

import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marka
 */
public class User {
    
    private int userId;
    private String username;
    private String password;
    private int role;
    private List<Message> listmessage = new ArrayList();
    
    public User() {
    }

    public User(int userId, String username, String password, int role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SimpleUser{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
    
    public ArrayList<User> AllUsers() throws SQLException{
        Database db = new Database();
        ArrayList<User> list = new ArrayList();
        
        try (Connection conn = db.connectToDB()) {
            String sql = "SELECT userId, username, password, role FROM users";
            try (PreparedStatement pstmt = conn.prepareStatement(sql); 
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    User user = new User();
                    user.userId = rs.getInt(1);
                    user.username = rs.getString(2);
                    user.password = rs.getString(3);
                    user.role = rs.getInt(4);
                    list.add(user);
                }
            }catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        }
        return list;
    }
    
}
