drop database if exists checkPoint;
create database checkPoint;
use checkPoint;

CREATE TABLE IF NOT EXISTS game (
	game_id int AUTO_INCREMENT NOT NULL UNIQUE,
	`title` varchar(255) NOT NULL,
	platform varchar(255),
	release_year int,
	genre varchar(255),
	publisher varchar(255),
	thumbnail varchar(255),
	PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS user (
	user_id int AUTO_INCREMENT NOT NULL UNIQUE,
	username varchar(75) NOT NULL UNIQUE,
	email varchar(105) NOT NULL UNIQUE,
	password varchar(255) NOT NULL,
	first_name varchar(105) NOT NULL,
	last_name varchar(105) NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_lists (
	user_list_id int AUTO_INCREMENT NOT NULL UNIQUE,
	user_id int NOT NULL,
	list_name varchar(255) NOT NULL,
	PRIMARY KEY (user_list_id)
);

CREATE TABLE IF NOT EXISTS user_list_items (
	user_list_items_id int AUTO_INCREMENT NOT NULL UNIQUE,
	user_list_id int NOT NULL,
	game_id int NOT NULL,
	PRIMARY KEY (user_list_items_id)
);

CREATE TABLE IF NOT EXISTS reviews (
	review_id int AUTO_INCREMENT NOT NULL UNIQUE,
	user_id int NOT NULL,
	game_id int NOT NULL,
	rating decimal(2,1),
	review_body text,
	created timestamp NOT NULL,
	PRIMARY KEY (review_id)
);

CREATE TABLE IF NOT EXISTS game_log (
	game_log_id int AUTO_INCREMENT NOT NULL UNIQUE,
	user_id int NOT NULL,
	game_id int NOT NULL,
	notes text NOT NULL,
	`status` varchar(200),
	log_date timestamp NOT NULL,
	updated timestamp,
	PRIMARY KEY (game_log_id)
);



ALTER TABLE user_lists ADD CONSTRAINT user_lists_fk1 FOREIGN KEY (user_id) REFERENCES user(user_id);
ALTER TABLE user_list_items ADD CONSTRAINT user_list_items_fk1 FOREIGN KEY (user_list_id) REFERENCES user_lists(user_list_id);

ALTER TABLE user_list_items ADD CONSTRAINT user_list_items_fk2 FOREIGN KEY (game_id) REFERENCES game(game_id);
ALTER TABLE reviews ADD CONSTRAINT reviews_fk1 FOREIGN KEY (user_id) REFERENCES user(user_id);

ALTER TABLE reviews ADD CONSTRAINT reviews_fk2 FOREIGN KEY (game_id) REFERENCES game(game_id);
ALTER TABLE game_log ADD CONSTRAINT game_log_fk1 FOREIGN KEY (user_id) REFERENCES user(user_id);

ALTER TABLE game_log ADD CONSTRAINT game_log_fk2 FOREIGN KEY (game_id) REFERENCES game(game_id);