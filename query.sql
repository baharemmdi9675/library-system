CREATE TABLE member
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL
);

Insert INTO member (username)
VALUES ('Ali');
