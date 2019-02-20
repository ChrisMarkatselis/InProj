/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserAndMessage;

import java.sql.Timestamp;

public class messageDate {

    public Timestamp messageTimestamp() {

        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        return timestamp;
        
    }

}
