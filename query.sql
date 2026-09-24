CREATE TABLE member
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email    VARCHAR            NOT NULL
);

Insert INTO member (username)
VALUES ('Ali');


CREATE TABLE book
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR not null

);

CREATE TABLE loan
(
    id          SERIAL PRIMARY KEY,
    user_id     integer not null references member (id),
    book_id     integer not null references book (id),
    active_loan bool    not null
);



select m.id, m.username, b.name
from loan l
         join member m on l.user_id = m.id
         join book b on l.book_id = b.id;






