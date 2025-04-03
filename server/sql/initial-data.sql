use checkPoint;

 insert into game (title, platform, release_year, genre, publisher, thumbnail)
        values
        ('Elden Ring', 'PC', 2022, 'Action RPG', 'FromSoftware', 'https://m.media-amazon.com/images/I/6110RSDn3PL._AC_SL1050_.jpg'),
		('God of War Ragnarök', 'PS5', 2022, 'Action-Adventure', 'Santa Monica Studio', 'https://upload.wikimedia.org/wikipedia/en/e/ee/God_of_War_Ragnar%C3%B6k_cover.jpg'),
		('Hogwarts Legacy', 'PC', 2023, 'Action RPG', 'Avalanche Software', 'https://gamefaqs.gamespot.com/a/box/8/9/9/767899_front.jpg'),
		('Baldur\'s Gate 3', 'PC', 2023, 'Role-Playing', 'Larian Studios', 'https://upload.wikimedia.org/wikipedia/en/1/12/Baldur%27s_Gate_3_cover_art.jpg'),
		('Resident Evil 4 (Remake)', 'PS5', 2023, 'Survival Horror', 'Capcom', 'https://upload.wikimedia.org/wikipedia/en/d/df/Resident_Evil_4_remake_cover_art.jpg'),
		('The Legend of Zelda: Tears of the Kingdom', 'Switch', 2023, 'Action-Adventure', 'Nintendo', 'https://pbs.twimg.com/media/GA_RkPhXAAA4z0y.jpg'),
		 ('Stardew Valley', 'PC', 2016, 'Simulation', 'ConcernedApe', 'https://i.redd.it/2ppo5tnap89a1.jpg'),
        ('Spider-Man 2', 'PS5', 2023, 'Action-Adventure', 'Insomniac Games', 'https://upload.wikimedia.org/wikipedia/en/0/0f/SpiderMan2PS5BoxArt.jpeg'),
		('Starfield', 'PC', 2023, 'Action RPG', 'Bethesda', 'https://images.launchbox-app.com/ddbdab50-3a4f-4d28-b6de-35b7a6a72e82.jpg'),
		('Final Fantasy XVI', 'PS5', 2023, 'Action RPG', 'Square Enix', 'https://m.media-amazon.com/images/I/81CixOru0zL.jpg'),
		('Diablo IV', 'PC', 2023, 'Action RPG', 'Blizzard Entertainment', 'https://upload.wikimedia.org/wikipedia/en/thumb/1/1c/Diablo_IV_cover_art.png/250px-Diablo_IV_cover_art.png'),
		('Cyberpunk 2077: Phantom Liberty', 'PC', 2023, 'Action RPG', 'CD Projekt', 'https://cyberpunklibrarian.com/infopump/images/33.jpg'),	
		('Super Mario Odyssey ', 'PC', 2017, 'Puzzle-Strategy', 'Nintendo', 'https://www.nintendoworldreport.com/media/43895/5/1.jpg'),
		 ('The Witcher 3: Wild Hunt', 'PC', 2015, 'Action-Adventure', 'CD Projekt', 'https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg'),
        ('The Elder Scrolls V: Skyrim', 'PC', 2011, 'Action-Adventure', 'Bethesda', 'https://upload.wikimedia.org/wikipedia/en/1/15/The_Elder_Scrolls_V_Skyrim_cover.png'),
        ('Halo: Combat Evolved', 'Xbox', 2001, 'First-Person Shooter', 'Bungie', 'https://upload.wikimedia.org/wikipedia/en/thumb/8/80/Halo_-_Combat_Evolved_%28XBox_version_-_box_art%29.jpg/220px-Halo_-_Combat_Evolved_%28XBox_version_-_box_art%29.jpg'),
        ('The Last of Us', 'PS3', 2013, 'Action-Adventure', 'Naughty Dog', 'https://upload.wikimedia.org/wikipedia/en/thumb/4/46/Video_Game_Cover_-_The_Last_of_Us.jpg/220px-Video_Game_Cover_-_The_Last_of_Us.jpg'),
        ('Red Dead Redemption 2', 'PS4', 2018, 'Action-Adventure', 'Rockstar', 'https://upload.wikimedia.org/wikipedia/en/4/44/Red_Dead_Redemption_II.jpg'),
        ('Street Fighter 6', 'PS5', 2023, 'Fighting', 'Capcom', 'https://upload.wikimedia.org/wikipedia/en/9/94/Street_Fighter_6_box_art.jpg'),
		('Mortal Kombat 1', 'PS5', 2023, 'Fighting', 'NetherRealm Studios', 'https://upload.wikimedia.org/wikipedia/en/thumb/5/5b/Mortal_Kombat_1_key_art.jpeg/220px-Mortal_Kombat_1_key_art.jpeg'),
		('Legend of Zelda: Breath of The Wild', 'PC', 2017, 'Action RPG', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/thumb/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg/250px-The_Legend_of_Zelda_Breath_of_the_Wild.jpg'),
		('Sea of Stars', 'PC', 2023, 'Role-Playing', 'Sabotage Studio', 'https://upload.wikimedia.org/wikipedia/en/0/0e/Sea_of_Stars_cover_art.jpg'),
		('Hi-Fi Rush', 'PC', 2023, 'Rhythm Action', 'Tango Gameworks', 'https://upload.wikimedia.org/wikipedia/en/f/fc/Hi-Fi_Rush_cover_art.jpg'),
		('Pikmin 4', 'Switch', 2023, 'Puzzle-Strategy', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/thumb/4/4d/Pikmin_icon_4.jpg/220px-Pikmin_icon_4.jpg'),
		('Super Mario Bros. Wonder', 'Switch', 2023, 'Platformer', 'Nintendo', 'https://nintendoeverything.com/wp-content/uploads/Super-Mario-Wonder-boxart.jpg'),
		

        ('Super Mario Bros.', 'NES', 1985, 'Platformer', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png'),
        ('The Legend of Zelda', 'NES', 1986, 'Action-Adventure', 'Nintendo', 'https://upload.wikimedia.org/wikipedia/en/4/41/Legend_of_zelda_cover_%28with_cartridge%29_gold.png'),
        ('Metroid', 'NES', 1986, 'Action-Adventure', 'Nintendo', 'https://i.ebayimg.com/images/g/UUEAAOSw1Xlkjmjc/s-l1200.jpg'),
        ('Final Fantasy', 'NES', 1987, 'Role-Playing', 'Square', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d8/FF1_USA_boxart.jpg/250px-FF1_USA_boxart.jpg');

        insert into user (username, email, password, first_name, last_name)
        values
        ('test', 'email@email.com', 'password', 'Test', 'User'),
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
        (1, 3),
        (1, 4),
        (2, 5),
        (2, 6),
        (2, 7),
        (2, 8),
        (3, 9),
        (3, 10);

        insert into reviews (user_id, game_id, rating, review_body, created)
        values
        (1, 1, 5.0, 'This game is a classic!', '2021-01-01 12:00:00'),
        (1, 2, 4.5, 'Great game, but a little too hard.', '2021-01-02 12:00:00'),
        (1, 3, 4.0, 'I love the exploration in this game.', '2021-01-03 12:00:00'),
        (1, 4, 4.5, 'A great RPG that still holds up.', '2021-01-04 12:00:00'),
        (2, 5, 5.0, 'This game is so relaxing and fun.', '2021-01-05 12:00:00'),
        (2, 6, 5.0, 'One of the best games I have ever played.', '2021-01-06 12:00:00'),
        (2, 7, 4.5, 'A great open-world game with a lot to do.', '2021-01-07 12:00:00'),
        (2, 8, 4.0, 'A great game, but the story is a little weak.', '2021-01-08 12:00:00'),
        (3, 9, 5.0, 'One of the best games ever made.', '2021-01-09 12:00:00'),
        (3, 10, 5.0, 'A great story with amazing characters.', '2021-01-10 12:00:00');


        insert into game_log (user_id, game_id, notes, status, log_date, updated)
        values
        (1, 1, 'This game is a classic!', 'Playing', '2021-01-01 12:00:00', '2021-01-01 12:00:00'),
        (1, 2, 'Great game, but a little too hard.', 'Playing', '2021-01-02 12:00:00', '2021-01-02 12:00:00'),
        (1, 3, 'I love the exploration in this game.', 'Playing', '2021-01-03 12:00:00', '2021-01-03 12:00:00'),
        (1, 4, 'A great RPG that still holds up.', 'Playing', '2021-01-04 12:00:00', '2021-01-04 12:00:00'),
        (2, 5, 'This game is so relaxing and fun.', 'Playing', '2021-01-05 12:00:00', '2021-01-05 12:00:00'),
        (2, 6, 'One of the best games I have ever played.', 'Playing', '2021-01-06 12:00:00', '2021-01-06 12:00:00'),
        (2, 7, 'A great open-world game with a lot to do.', 'Playing', '2021-01-07 12:00:00', '2021-01-07 12:00:00'),
        (2, 8, 'A great game, but the story is a little weak.', 'Playing', '2021-01-08 12:00:00', '2021-01-08 12:00:00'),
        (3, 9, 'One of the best games ever made.', 'Playing', '2021-01-09 12:00:00', '2021-01-09 12:00:00'),
        (3, 10, 'A great story with amazing characters.', 'Playing', '2021-01-10 12:00:00', '2021-01-10 12:00:00');