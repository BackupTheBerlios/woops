DROP TABLE ActivitySequence;
DROP TABLE Activity;
DROP TABLE ActivitySequenceType;
DROP TABLE User;
DROP TABLE UserRole;
DROP TABLE ActivityState;


CREATE TABLE UserRole (
       code VARCHAR(10) NOT NULL
     , name VARCHAR(30) NOT NULL
     , PRIMARY KEY (code)
)ENGINE=InnoDB;

CREATE TABLE User (
       id INT NOT NULL AUTO_INCREMENT
     , firstName VARCHAR(50) NOT NULL
     , lastName VARCHAR(50) NOT NULL
     , login VARCHAR(20) NOT NULL
     , password VARCHAR(10) NOT NULL
     , role VARCHAR(10) NOT NULL
     , UNIQUE UQ_User_login (login)
     , PRIMARY KEY (id)
     , CONSTRAINT FK_User_role FOREIGN KEY (role)
                  REFERENCES UserRole (code)
)ENGINE=InnoDB;

CREATE TABLE ActivitySequenceType (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(15) NOT NULL
     , UNIQUE UQ_ActivitySequenceType_name (name)
     , PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE ActivityState (
       name VARCHAR(50) NOT NULL
     , progress INT DEFAULT 0
     , PRIMARY KEY (name)
)ENGINE=InnoDB;

CREATE TABLE Activity (
       id INT NOT NULL AUTO_INCREMENT
     , name VARCHAR(50) NOT NULL
     , details TEXT
     , startDate DATE
     , endDate DATE
     , user INT NOT NULL
     , state VARCHAR(50) NOT NULL
     , PRIMARY KEY (id)
     , INDEX (user)
     , UNIQUE KEY `name` (`name`)
     , CONSTRAINT FK_Activity_user FOREIGN KEY (user)
                  REFERENCES User (id)
     , CONSTRAINT FK_Activity_state FOREIGN KEY (state)
                  REFERENCES ActivityState (name)            
)ENGINE=InnoDB;
--CREATE INDEX IX_Activity_name ON Activity (name ASC);

CREATE TABLE ActivitySequence (
       id INT NOT NULL AUTO_INCREMENT
     , successor INT NOT NULL
     , predecessor INT NOT NULL
     , linkType INT NOT NULL
     , UNIQUE UQ_ActivitySequence_successor_predecessor (successor, predecessor)
     , PRIMARY KEY (id)
     , INDEX (linkType)
     , CONSTRAINT FK_ActivitySequence_linkType FOREIGN KEY (linkType)
                  REFERENCES ActivitySequenceType (id)
     , INDEX (successor)
     , CONSTRAINT FK_ActivitySequence_successor FOREIGN KEY (successor)
                  REFERENCES Activity (id)
     , INDEX (predecessor)
     , CONSTRAINT FK_ActivitySequence_predecessor FOREIGN KEY (predecessor)
                  REFERENCES Activity (id)
)ENGINE=InnoDB;



-- Insertions intiales
INSERT INTO ActivitySequenceType(name) VALUES ('finishToStart');
INSERT INTO ActivitySequenceType(name) VALUES ('finishToFinish');
INSERT INTO ActivitySequenceType(name) VALUES ('startToStart');
INSERT INTO ActivitySequenceType(name) VALUES ('startToFinish');
INSERT INTO ActivityState(name) VALUES ('created');
INSERT INTO ActivityState(name) VALUES ('inProgress');
INSERT INTO ActivityState(name) VALUES ('finished');
INSERT INTO UserRole VALUES ('admin', 'administrator');
INSERT INTO UserRole VALUES ('dev', 'developer');
