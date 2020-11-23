CREATE DATABASE ESCALADE CHARACTER SET 'utf8';

USE ESCALADE;

create table USER (
    id INT AUTO_INCREMENT NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    roles VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
    )ENGINE = INNODB;

create table SITE (
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50) NOT NULL,
	region VARCHAR(50) NOT NULL,
	department VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	is_equipped BOOLEAN NOT NULL,
	is_tagged BOOLEAN NOT NULL,
	areas_number INT NOT NULL,
	creator_id INT NOT NULL,
	PRIMARY KEY (id)
	)ENGINE = INNODB;

create table COMMENT (
	id INT AUTO_INCREMENT NOT NULL,
	description VARCHAR(600) NOT NULL,
	user_id INT NOT NULL,
	site_id INT NOT NULL,
	time_stamp TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
	)ENGINE = INNODB;

create table TOPO (
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50) NOT NULL,
	localization VARCHAR(50) NOT NULL,
	description VARCHAR(500) NOT NULL,
	date DATE NOT NULL,
	reservation ENUM('A','R','U','I') NOT NULL,
	user_id INT NOT NULL,
	site_id INT NOT NULL,
	PRIMARY KEY (id)
	)ENGINE = INNODB;

create table AREA (
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(100) NOT NULL,
	routes_number INT NOT NULL,
	cotations VARCHAR(10) NOT NULL,
	site_id INT NOT NULL,
	PRIMARY KEY (id)
	)ENGINE = INNODB;

ALTER TABLE SITE ADD CONSTRAINT users_sites_fk
FOREIGN KEY (creator_id)
REFERENCES USER (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE COMMENT ADD CONSTRAINT users_comments_fk
FOREIGN KEY (user_id)
REFERENCES USER (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE COMMENT ADD CONSTRAINT sites_comments_fk
FOREIGN KEY (site_id)
REFERENCES SITE (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TOPO ADD CONSTRAINT users_topos_fk
FOREIGN KEY (user_id)
REFERENCES USER (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TOPO ADD CONSTRAINT sites_topos_fk
FOREIGN KEY (site_id)
REFERENCES SITE (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE AREA ADD CONSTRAINT sites_areas_fk
FOREIGN KEY (site_id)
REFERENCES SITE (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;