/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

/**
 *
 * @author marka
 */
public class MessengerException extends Exception {
    
        public MessengerException(String message) {
            super(message);
        }
    
        public MessengerException(String message, Throwable cause) {
            super(message, cause);
        }
}
