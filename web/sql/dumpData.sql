
#  USER
INSERT INTO travelbook.users (id, black_listed, email, enabled, password, username) VALUES (101, false, 'shakif@travelbook.com', true, '$2a$10$moMK8THSVmwk5uvCnc5zKebboTmqiaqBMfUPMqHt6kew4i6QzwyqK', 'shakif@travelbook.com');
INSERT INTO travelbook.users (id, black_listed, email, enabled, password, username) VALUES (102, false, 'emon@travelbook.com', true, '$2a$10$UBRTJ5JixcxWRfnAX1nrdeZKL9XZnvYkITtWzfuTiAy7BYKHXWeFu', 'emon@travelbook.com');
INSERT INTO travelbook.users (id, black_listed, email, enabled, password, username) VALUES (103, false, 'rakib@travelbook.com', true, '$2a$10$KGgWSI6RKDlS.mN9yv4RkucZNa3Ja1YsbJcFsZExaiPYsBU0C1cSS', 'rakib@travelbook.com');
INSERT INTO travelbook.users (id, black_listed, email, enabled, password, username) VALUES (104, false, 'amlan@travelbook.com', true, '$2a$10$eL5Q8kCA81Ux1SCpJ/3uLejI5DrMylfasJkfqcsVu35hwQFo/dC4y', 'amlan@travelbook.com');

# Campaign
INSERT INTO travelbook.campaigns (id, budgets, campaigns_approval_status_id, campaigns_status_id, cities_id, countries_id, created_date, description, duration, ratings, participant_number, place, remaining_seats, start_time, states_id, title, user_id) VALUES (1, 375, 2, 1, 42597, 231, '2021-02-01 14:06:47.549000', 'A Destination For The New Millennium Description', 5, -1, 15, 'Center of Anniston ', 13, '2021-02-02 18:00:00', 3919, 'A Destination For The New Millennium.', 102);
INSERT INTO travelbook.campaigns (id, budgets, campaigns_approval_status_id, campaigns_status_id, cities_id, countries_id, created_date, description, duration, ratings, participant_number, place, remaining_seats, start_time, states_id, title, user_id) VALUES (2, 450, 2, 1, 19014, 82, '2021-02-01 14:37:55.354000', 'This triumphant neoclassical arch is Berlin’s most famous monument and the only remaining gate of the 14 that originally surrounded the city when it was a proud Prussian metropolis. Since then, Napoleon and Hitler have stormed through it and the world watched as thousands of Berliners swarmed the site with sledgehammers to topple the nearby Wall in 1989. Ever since, this Acropolis-inspired 1791 monument has come to symbolize German reunification. Conveniently located within easy walking distance of a trio of boldfaced Berlin sites (Tiergarten Park, the Reichstag, and The Holocaust Memorial), the Brandenburg Gate serves as a central meeting place for tourists.', 3, -1, 20, 'Brandenburg Gate', 19, '2021-03-12 18:00:00', 1359, 'Experience Tourism These Are As Education In Themselves.', 103);

# Campaign Image
INSERT INTO travelbook.campaigns_images (id, campaigns_id, image) VALUES (1, 1, '/images/1612188407614_1608834267264_130777675_1517238351808344_3003077461651241080_n.jpg');
INSERT INTO travelbook.campaigns_images (id, campaigns_id, image) VALUES (2, 1, '/images/1612188407634_1608834267268_131900320_220378106433001_4388345882250426001_n.jpg');
INSERT INTO travelbook.campaigns_images (id, campaigns_id, image) VALUES (3, 2, '/images/1612190275406_brandenburger_tor_fruehling_650696492_gettyimages_sborisov.jpg');
INSERT INTO travelbook.campaigns_images (id, campaigns_id, image) VALUES (4, 2, '/images/1612190275419_BrandenburgGate_2018_GettyImages-549093677-min.jpg');

# Campaign Participants
INSERT INTO travelbook.campaigns_participants (id, campaigns_id, user_id) VALUES (1, 1, 103);
INSERT INTO travelbook.campaigns_participants (id, campaigns_id, user_id) VALUES (2, 1, 101);
INSERT INTO travelbook.campaigns_participants (id, campaigns_id, user_id) VALUES (3, 2, 101);
