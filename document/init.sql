USE travelbook;

CREATE TABLE campaigns
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title        VARCHAR(255)                      NOT NULL,
    place        VARCHAR(255)                      NOT NULL,
    start_time   DATETIME                          NOT NULL,
    duration     BIGINT                            NOT NULL,
    budgets      FLOAT                             NOT NULL,
    created_by   BIGINT                            NOT NULL,
    created_date DATE                              NOT NULL
);


CREATE TABLE users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR(255)                      NOT NULL,
    password VARCHAR(255)                      NOT NULL,
#     email VARCHAR(255)                      NOT NULL,
    enabled  BOOLEAN                           NOT NULL
);


CREATE TABLE authorities
(
    id        int AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) NULL,
    authority VARCHAR(255) NULL
);
