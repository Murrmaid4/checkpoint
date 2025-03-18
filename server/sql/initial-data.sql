use checkPoint;

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