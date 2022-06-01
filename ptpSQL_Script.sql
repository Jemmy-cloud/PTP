CREATE DATABASE ptp;
use ptp;
CREATE TABLE users (
		userid INT NOT NULL,
        email VARCHAR(30) NOT NULL,
        user_password VARCHAR(30) NOT NULL,
        PRIMARY KEY (userid)
);


CREATE TABLE shows (
		showid INT NOT NULL,
        showname VARCHAR(60) NOT NULL,
        seasons INT NOT NULL,
        episodes INT NOT NULL,
        PRIMARY KEY (showid)
);


CREATE TABLE watched (
		userid INT NOT NULL,
        showid INT NOT NULL,
        progress INT,                     /* progress can be seasons/episodes watched..       plan to watch, currently watching, have finished watching*/
        PRIMARY KEY (userid, showid),
        FOREIGN KEY (userid) REFERENCES users(userid),
        FOREIGN KEY (showid) REFERENCES shows(showid)
);



INSERT INTO users VALUES (1234, 'bob@gmail.com', 'easy123');

INSERT INTO shows VALUES(01, 'Stranger Things', 4, 32);
INSERT INTO shows VALUES(02, 'Better Call Saul', 5, 50);
INSERT INTO shows VALUES(03, 'Breaking Bad', 5, 62);
INSERT INTO shows VALUES(04, 'Dark', 3, 26);
INSERT INTO shows VALUES(05, 'The Witcher', 2, 16);
INSERT INTO shows VALUES(06, 'The Walking Dead', 10, 153);
INSERT INTO shows VALUES(07, 'Bridgerton', 2, 16);
INSERT INTO shows VALUES(08, 'All Of Us Are Dead', 1, 12);
INSERT INTO shows VALUES(09, 'Outlander', 5, 67);
INSERT INTO shows VALUES(10, 'Mindhunter', 2, 19);

INSERT INTO watched VALUES (1234, 01, 31);     /* has watched 31 out of 32 episodes of stranger things */




