use RFP_Strategy_DB;


DROP TABLE IF EXISTS Rfp_relations;
DROP TABLE IF EXISTS Relation;
DROP TABLE IF EXISTS Rfp_actors;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Department;
DROP TABLE IF EXISTS Rfp;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Actor;
DROP TABLE IF EXISTS Rfp_status;
DROP TABLE IF EXISTS Teamtrade_appreciation;
DROP TABLE IF EXISTS Relation_type;
DROP TABLE IF EXISTS Relation_quality;
DROP TABLE IF EXISTS Civility;
DROP TABLE IF EXISTS Actor_type;
DROP TABLE IF EXISTS Actor_role;

/********** Reference tables ****************/
CREATE TABLE Rfp_status (
	rfp_status_id int NOT NULL AUTO_INCREMENT, 
	name varchar(50) NOT NULL UNIQUE, 
	PRIMARY KEY (rfp_status_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Actor_role (
	role_id int NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL UNIQUE, 
	PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Actor_type (
	actor_type_id int NOT NULL AUTO_INCREMENT, 
	name varchar(50) NOT NULL UNIQUE, 
	PRIMARY KEY (actor_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Civility (
	civility_id int NOT NULL AUTO_INCREMENT, 
	name varchar(12) NOT NULL UNIQUE, 
	PRIMARY KEY (civility_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Teamtrade_appreciation (
	appreciation_id int NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL UNIQUE, 
	rating int default 0, 
	PRIMARY KEY (appreciation_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Relation_quality (
	quality_id int NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL UNIQUE, 
	PRIMARY KEY (quality_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Relation_type (
	type_id int NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL UNIQUE, 
	PRIMARY KEY (type_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/************ Other tables ******************/
CREATE TABLE Actor (
	actor_id int NOT NULL AUTO_INCREMENT, 
	teamtrade_appreciation_id int NOT NULL, 
	actor_type_id int NOT NULL, 
	PRIMARY KEY (actor_id), 
	FOREIGN KEY FK_TEAMTRADE_APPRECIATION (teamtrade_appreciation_id) REFERENCES Teamtrade_appreciation (appreciation_id) ON UPDATE CASCADE, 
	FOREIGN KEY FK_ACTOR_TYPE (actor_type_id) REFERENCES Actor_type (actor_type_id) ON UPDATE CASCADE, 
	INDEX IDX_TEAMTRADE_APPRECIATION (teamtrade_appreciation_id), 
	INDEX IDX_ACTOR_TYPE (actor_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Company (
	company_id int NOT NULL, 
	name varchar(200) NOT NULL UNIQUE, 
	siren_number varchar(200), 
    logo varchar(200),
    FOREIGN KEY FK_ACTOR (company_id) REFERENCES Actor(actor_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    INDEX IDX_ACTOR (company_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Client (
	client_id int NOT NULL AUTO_INCREMENT, 
    company_id int NOT NULL UNIQUE,
	PRIMARY KEY (client_id),
    FOREIGN KEY FK_COMPAGNY (company_id) REFERENCES Company (company_id) ON UPDATE CASCADE,
    INDEX IDX_COMPAGNY (company_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Rfp (
	rfp_id int NOT NULL AUTO_INCREMENT, 
	name varchar(200) NOT NULL, 
	startdate datetime NOT NULL, 
	enddate datetime NOT NULL, 
	rfp_status_id int NOT NULL, 
    client_id int NOT NULL,
	PRIMARY KEY (rfp_id), 
	FOREIGN KEY FK_STATUS (rfp_status_id) REFERENCES Rfp_status (rfp_status_id) ON UPDATE CASCADE, 
    FOREIGN KEY FK_CLIENT (client_id) REFERENCES Client (client_id) ON UPDATE CASCADE, 
	INDEX IDX_STATUS (rfp_status_id),
    INDEX IDX_CLIENT (client_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Department (
	department_id int NOT NULL AUTO_INCREMENT, 
	name varchar(200) NOT NULL, 
	company_id int NOT NULL, 
	PRIMARY KEY (department_id), 
	FOREIGN KEY FK_COMPAGNY (company_id) REFERENCES Company (company_id) ON UPDATE CASCADE, 
	INDEX IDX_COMPAGNY (company_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Person (
	person_id int NOT NULL, 
	firstname varchar(200), 
	lastname varchar(200), 
	civility_id int NOT NULL, 
	department_id int NOT NULL, 
	manager_id int NOT NULL, 
    avatar varchar(200),
	FOREIGN KEY FK_CIVILITY (civility_id) REFERENCES Civility (civility_id) ON UPDATE CASCADE, 
	FOREIGN KEY FK_DEPARTMENT (department_id) REFERENCES Department (department_id) ON UPDATE CASCADE, 
	FOREIGN KEY FK_PERSON (manager_id) REFERENCES Person (person_id) ON UPDATE CASCADE, 
    FOREIGN KEY FK_ACTOR (person_id) REFERENCES Actor(actor_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    INDEX IDX_CIVILITY (civility_id), 
    INDEX IDX_DEPARTMENT (department_id),
    INDEX IDX_MANAGER (manager_id),
    INDEX IDX_ACTOR (person_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Rfp_actors (
	rfp_actors_id int NOT NULL AUTO_INCREMENT, 
	rfp_id int NOT NULL, 
	actor_id int NOT NULL, 
	role_id int NOT NULL, 
	PRIMARY KEY (rfp_actors_id), 
	FOREIGN KEY FK_ACTOR (actor_id) REFERENCES Actor (actor_id) , 
	FOREIGN KEY FK_RFP (rfp_id) REFERENCES Rfp (rfp_id) , 
	FOREIGN KEY FK_ROLE (role_id) REFERENCES Actor_role (role_id), 
	INDEX IDX_ACTOR (actor_id), 
	INDEX IDX_RFP (rfp_id), 
	INDEX IDX_ROLE(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Relation (
	relation_id int NOT NULL AUTO_INCREMENT, 
	actor_from int NOT NULL, 
	actor_to int NOT NULL, 
	relation_type_id int NOT NULL, 
	relation_quality_id int NOT NULL, 
	relation_title varchar(200), 
	relation_comment varchar(500), 
	PRIMARY KEY (relation_id), 
	FOREIGN KEY FK_ACTOR_FROM (actor_from) REFERENCES Actor (actor_id) ON DELETE CASCADE ON UPDATE CASCADE, 
	FOREIGN KEY FK_ACTOR_TO (actor_to) REFERENCES Actor (actor_id) ON DELETE CASCADE ON UPDATE CASCADE, 
	FOREIGN KEY FK_RELATION_TYPE (relation_type_id) REFERENCES Relation_type (type_id) ON UPDATE CASCADE, 
	FOREIGN KEY FK_RELATION_QUALITY (relation_quality_id) REFERENCES Relation_quality (quality_id) ON UPDATE CASCADE, 
	INDEX IDX_ACTOR_FROM (actor_from), 
	INDEX IDX_ACTOR_TO (actor_to), 
	INDEX IDX_RELATION_TYPE (relation_type_id), 
	INDEX IDX_RELATION_QUALITY (relation_quality_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Rfp_relations (
	rfp_relations_id int NOT NULL AUTO_INCREMENT, 
	rfp_id int NOT NULL, 
	relation_id int NOT NULL, 
	PRIMARY KEY (rfp_relations_id), 
	FOREIGN KEY FK_RFP (rfp_id) REFERENCES Rfp (rfp_id) ON DELETE CASCADE ON UPDATE CASCADE, 
	FOREIGN KEY FK_RELATION (relation_id) REFERENCES Relation (relation_id) ON DELETE CASCADE ON UPDATE CASCADE, 
	INDEX IDX_RFP (rfp_id), 
	INDEX IDX_RELATION (relation_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/***** Initial data for references table ***********************************************/
INSERT INTO Actor_role (name) VALUES ('Associate'), ('Customer'), ('Competitor');
INSERT INTO Actor_type (name) VALUES ('Company'), ('Person');
INSERT INTO Civility (name) VALUES ('Mr'), ('Mrs');
INSERT INTO Relation_type (name) VALUES ('Friendship'), ('Hierarchy'), ('Influence'), ('Previous collaboration');
INSERT INTO Rfp_status (name) VALUES ('In Progress'), ('Lost'), ('Short Listed'), ('Won');
INSERT INTO Relation_quality (name) VALUES ('Bad'), ('Good'), ('Very Bad'), ('Very Good');
INSERT INTO Teamtrade_appreciation (name, rating) VALUES ('Favorable', 1), ('Very favorable', 2), ('Neutral', 0), ('Unfavorable', -1), ('Very Unfavorable', -2);