USE chat;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients (
	id SERIAL PRIMARY KEY,
	login varchar(64), 
	password varchar(64),
	nickname varchar(64)
);

INSERT INTO clients (login, password, nickname) VALUES
	('Ivan','123','ivan-igorevich'),
	('Dmitry','111','nifont');
	