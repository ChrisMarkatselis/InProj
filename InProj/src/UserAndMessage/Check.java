package UserAndMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marka
 */
public class Check {
   
    List<User> lst = new ArrayList();
    List<Message> lstm = new ArrayList();
    
    private String username;

    public Check(){}
    /**
     * @param newUsername
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean checkUser(String newUsername) throws SQLException{
        boolean usernameExist = false;
        User user = new User();
        lst = user.AllUsers();
        
        for(User usr: lst){
            String newUser = newUsername;
            String u1 = usr.getUsername();
                        
            if(newUser.equals(u1)){
                usernameExist = true;
                break;
            }
            else {
                usernameExist = false;
                
            }
        }
        return usernameExist;
    }
    
    /**
     * @param newMessageId
     * @param mUsername
     * @return
     * @throws SQLException
     */
    public boolean checkMessage(int newMessageId,String mUsername) throws SQLException{
        boolean messageExist = false;
        lstm = Message.selectMessage(mUsername);
        for (Message nMsg : lstm){
            int newMsgId = newMessageId;
            int mId = nMsg.getMessageId() ;
            if(newMsgId == mId){
                messageExist = true;
                break;
            }
            else {
                messageExist = false;
            }  
        }
        return messageExist;
      }

    public boolean checkMessages(int newMessageId) throws SQLException{
        boolean messageExist = false;
        lstm = Message.selectAllMessages();
        for (Message nMsg : lstm){
            
            int mId = nMsg.getMessageId() ;
            if(newMessageId == mId){
                messageExist = true;
            }
            else {
                messageExist = false;
            }  
        }
        return messageExist;
      }
}
