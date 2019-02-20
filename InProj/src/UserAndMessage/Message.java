/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserAndMessage;

import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marka
 */
public class Message {
    
    private int messageId;
    private Timestamp submit_date_hour;
    private String sender;
    private String receiver;
    private String message_info;

    public Message(){
        
    }
    
    public Message(int messageId, Timestamp submit_date_hour, String sender, String receiver, String message_info) {
        this.messageId = messageId;
        this.submit_date_hour = submit_date_hour;
        this.sender = sender;
        this.receiver = receiver;
        this.message_info = message_info;
    }

    public int getMessageId() {
        return messageId;
    }

    public Timestamp getSubmit_date_hour() {
        return submit_date_hour;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage_info() {
        return message_info;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setSubmit_date_hour(Timestamp submit_date_hour) {
        this.submit_date_hour = submit_date_hour;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMessage_info(String message_info) {
        this.message_info = message_info;
    }

    @Override
    public String toString() {
       
        return "The user " + receiver + " send you the message : " + message_info + " this day and hour " + submit_date_hour + " with id :" + messageId +" \n ";
    }
    
    public static List<Message> selectMessage(String username) throws SQLException{
        
    Database db = new Database();
    List<Message> list = new ArrayList();

    try (Connection conn = db.connectToDB()) {
        String sql = "SELECT messageId, submit_date_hour, sender, receiver, messageData FROM messages WHERE receiver = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Message message = new Message();
                message.messageId = rs.getInt(1);
                message.submit_date_hour = rs.getTimestamp(2);
                message.receiver = rs.getString(3);
                message.sender = rs.getString(4);
                message.message_info = rs.getString(5);
                list.add(message);
            }
            }catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        }
        return list;
    }
    
    public static List<Message> selectAllMessages() throws SQLException{
        
    Database db = new Database();
    List<Message> list = new ArrayList();

    try (Connection conn = db.connectToDB()) {
        String sql = "SELECT messageId, submit_date_hour, sender, receiver, messageData FROM messages ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
                ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Message message = new Message();
                message.messageId = rs.getInt(1);
                message.submit_date_hour = rs.getTimestamp(2);
                message.receiver = rs.getString(3);
                message.sender = rs.getString(4);
                message.message_info = rs.getString(5);
                list.add(message);
            }
            }catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        }
        return list;
    }
       
}

