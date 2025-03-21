drop database if exists checkPoint_test;
create database checkPoint_test;
use checkPoint_test;

CREATE TABLE IF NOT EXISTS game (
	game_id int AUTO_INCREMENT NOT NULL UNIQUE,
	title varchar(255) NOT NULL,
	platform varchar(255),
	release_year int,
	genre varchar(255),
	publisher varchar(255),
	thumbnail varchar(255),
	PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS `user` (
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


delimiter //
create procedure set_known_good_state()
begin
 delete from game_log;
 alter table game_log auto_increment = 1;

 delete from reviews;
 alter table reviews auto_increment = 1;

  delete from user_list_items;
  alter table user_list_items auto_increment = 1;

  delete from user_lists;
  alter table user_lists auto_increment = 1;

  delete from user;
  alter table user auto_increment = 1;

  delete from game;
  alter table game auto_increment = 1;

    insert into game (title, platform, release_year, genre, publisher, thumbnail)
        values
        ('Super Mario Bros.', 'NES', 1985, 'Platformer', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png'),
        ('The Legend of Zelda', 'NES', 1986, 'Action-Adventure', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/4/41/The_Legend_of_Zelda_NES.png'),
        ('Metroid', 'NES', 1986, 'Action-Adventure', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/3/39/Metroid_box_art.jpg'),
        ('Final Fantasy', 'NES', 1987, 'Role-Playing', 'Square', 'https://upload.wikimedia.org/wikipedia/en/3/3e/Final_Fantasy_NES_box_art.jpg'),
        ('Stardew Valley', 'PC', 2016, 'Simulation', 'ConcernedApe', 'https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png'),
        ('The Witcher 3: Wild Hunt', 'PC', 2015, 'Action-Adventure', 'CD Projekt', 'https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg'),
        ('The Elder Scrolls V: Skyrim', 'PC', 2011, 'Action-Adventure', 'Bethesda', 'https://upload.wikimedia.org/wikipedia/en/1/15/The_Elder_Scrolls_V_Skyrim_cover.png'),
        ('Halo: Combat Evolved', 'Xbox', 2001, 'First-Person Shooter', 'Bungie', 'https://upload.wikimedia.org/wikipedia/en/b/b9/Halo_-_Combat_Evolved_%28XBox_version_-_box_art%29.jpg'),
        ('The Last of Us', 'PS3', 2013, 'Action-Adventure', 'Naughty Dog', 'https://upload.wikimedia.org/wikipedia/en/4/46/Video_Game_Cover_The_Last_of_Us.png'),
        ('Red Dead Redemption 2', 'PS4', 2018, 'Action-Adventure', 'Rockstar', 'https://upload.wikimedia.org/wikipedia/en/4/44/Red_Dead_Redemption_II.jpg');

   insert into user (username, email, password, first_name, last_name)
        values
        ('testUser1', 'email@email.com', '$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G', 'Test', 'User'),
        ('testUser2', 'mail@cox.net', '$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G', 'Test2', 'User2'),
        ('testUser3', 'ye@haw.com', '$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G', 'Test3', 'User3');


        insert into user_lists (user_id, list_name)
        values
        (1, 'Favorites'),
        (2, 'Wishlist'),
        (1, 'Best RPGs'),
        (2, 'Best Platformers'),
        (3, 'Best Action-Adventure');

        insert into user_list_items (user_list_id, game_id)
        values
        (1, 1),
        (1, 2),
        (1, 3);

        insert into game_log (user_id, game_id, notes, status, log_date, updated)
               values
               (1, 1, 'This game is a classic!', 'Playing', '2021-01-01 12:00:00', '2021-01-01 12:00:00'),
               (1, 2, 'Great game, but a little too hard.', 'Playing', '2021-01-02 12:00:00', '2021-01-02 12:00:00'),
               (1, 3, 'I love the exploration in this game.', 'Playing', '2021-01-03 12:00:00', '2021-01-03 12:00:00');

  insert into reviews (user_id, game_id, rating, review_body, created)
        values
        (1, 1, 5.0, 'This game is a classic!', '2021-01-01 12:00:00'),
        (1, 2, 4.5, 'Great game, but a little too hard.', '2021-01-02 12:00:00');

end//
delimiter ;