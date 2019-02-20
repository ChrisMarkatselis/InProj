CREATE DATABASE indi_project ;

CREATE TABLE users (userId INT NOT NULL AUTO_INCREMENT, username VARCHAR(35) NOT NULL, password VARCHAR(35) NOT NULL, role INT NOT NULL, PRIMARY KEY (`userId`));

CREATE TABLE messages (messageId INT NOT NULL AUTO_INCREMENT, submit_date_hour TIMESTAMP NOT NULL, sender VARCHAR(35), receiver VARCHAR(35), messageData VARCHAR(255) NOT NULL, PRIMARY KEY (`messageId`));

INSERT INTO users (username, password, role) VALUES ("admin","admin",0);

INSERT INTO users (username,password,role) VALUES ("user1","user1",3);


