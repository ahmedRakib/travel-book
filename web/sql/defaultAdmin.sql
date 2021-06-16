delete from users_authorities where authorities_id=1;
delete from authorities where authority='admin';

insert into authorities(id, authority, username) values(1, 'admin', 'admin@travelbook.com');

insert into users(id, black_listed, email, enabled, password, username) values(100, false, 'admin@travelbook.com', true, '$2a$10$moMK8THSVmwk5uvCnc5zKebboTmqiaqBMfUPMqHt6kew4i6QzwyqK', 'admin@travelbook.com');

insert into users_authorities(users_id, authorities_id) values(100, 1);
