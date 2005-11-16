CREATE DATABASE IF NOT EXISTS woops;

CREATE TABLE IF NOT EXISTS woops.User (
       id INT NOT NULL AUTO_INCREMENT
     , firstName VARCHAR(50) NOT NULL
     , lastName VARCHAR(50) NOT NULL
     , login VARCHAR(20) NOT NULL
     , password VARCHAR(10) NOT NULL
     , UNIQUE UQ_User_login (login)
     , PRIMARY KEY (id)
)TYPE=InnoDB;

CREATE TABLE IF NOT EXISTS woops.ActivitySequenceType (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(15) NOT NULL
     , UNIQUE UQ_ActivitySequenceType_name (name)
     , PRIMARY KEY (id)
)TYPE=InnoDB;

CREATE TABLE IF NOT EXISTS woops.Activity (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(50) NOT NULL
     , details TEXT
     , user INT NOT NULL
     , UNIQUE UQ_Activity_name (name)
     , PRIMARY KEY (id)
     , INDEX (user)
     , CONSTRAINT FK_Activity_user FOREIGN KEY (user)
                  REFERENCES woops.User (id)
)TYPE=InnoDB;
CREATE INDEX IX_Activity_name ON woops.Activity (name ASC);

CREATE TABLE IF NOT EXISTS woops.ActivitySequence (
       id INT NOT NULL AUTO_INCREMENT
     , sucessor INT NOT NULL
     , predecessor INT NOT NULL
     , linkType INT NOT NULL
     , UNIQUE UQ_ActivitySequence_successor_predecessor (sucessor, predecessor)
     , PRIMARY KEY (id)
     , INDEX (linkType)
     , CONSTRAINT FK_ActivitySequence_linkType FOREIGN KEY (linkType)
                  REFERENCES woops.ActivitySequenceType (id)
     , INDEX (sucessor)
     , CONSTRAINT FK_ActivitySequence_successor FOREIGN KEY (sucessor)
                  REFERENCES woops.Activity (id)
     , INDEX (predecessor)
     , CONSTRAINT FK_ActivitySequence_predecessor FOREIGN KEY (predecessor)
                  REFERENCES woops.Activity (id)
)TYPE=InnoDB;

