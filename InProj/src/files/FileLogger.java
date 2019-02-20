package files;

import UserAndMessage.Message;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogger {

    private FileWriter writerF;
    private BufferedWriter writer;
    private static final String FILENAME = "C:\\Users\\marka\\Desktop\\messenger.txt";
    private static final String FILENAMEEDITED = "C:\\Users\\marka\\Desktop\\editedMessages.txt";
    private static final String FILENAMEDELETED = "C:\\Users\\marka\\Desktop\\deletedMessages.txt";
    
    //Open the file or create it if not found
    public void initFile() throws MessengerException {
        try {
            writerF = new FileWriter(FILENAME, true);
        } catch (IOException ex) {
            throw new MessengerException(ex.getMessage(), ex);
        }
    }

    public void writeToFile(Message msg) throws MessengerException, IOException {
        
        writer = new BufferedWriter(new FileWriter(FILENAME, true));
        
        try {
            writer.append("The " + msg.getSender() + " send to " + msg.getReceiver() + " this " + msg.getSubmit_date_hour() + " the follow message: \n" + msg.getMessage_info() );
            writer.newLine();
        } catch (IOException ex) {
            throw new MessengerException(ex.getMessage(), ex);
        }
        finally {
            writer.close();
        }
    }

    //Open the file or create it if not found
    public void initEditedFile() throws MessengerException {
        try {
            writerF = new FileWriter(FILENAMEEDITED, true);
        } catch (IOException ex) {
            throw new MessengerException(ex.getMessage(), ex);
        }
    }

    public void writeEditedFile(Message msg) throws MessengerException, IOException {
        
        writer = new BufferedWriter(new FileWriter(FILENAMEEDITED, true));
        
        try {
            writer.append( msg.getSubmit_date_hour() + " a message has been edited. The edited message is: " + msg.getMessage_info() );
            writer.newLine();
        } catch (IOException ex) {
            throw new MessengerException(ex.getMessage(), ex);
        }
        finally {
            writer.close();
        }
    }
    
        //Open the file or create it if not found
    public void initDeletedFile() throws MessengerException {
        try {
            writerF = new FileWriter(FILENAMEDELETED, true);
        } catch (IOException ex) {
            throw new MessengerException(ex.getMessage(), ex);
        }
    }

    public void writeDeletedFile(Message msg) throws MessengerException, IOException {
        
        writer = new BufferedWriter(new FileWriter(FILENAMEDELETED, true));
        
        try {
            writer.append("The message with id: " + msg.getMessageId() +" has been deleted from the user: " + msg.getReceiver() + " this " + msg.getSubmit_date_hour() + " by " + msg.getSender() +"!");
            writer.newLine();
        } catch (IOException ex) {
            throw new MessengerException(ex.getMessage(), ex);
        }
        finally {
            writer.close();
        }
    }
    
}