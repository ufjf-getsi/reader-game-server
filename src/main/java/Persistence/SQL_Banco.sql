--É necessário criar um banco de dados com as seguintes informações
--Nome: readergameserver, user: root, senha: 1234
--Não utilizar, somente Postgres

create table game (
game_identifier integer primary key generated always as identity,
name varchar(100),
currentPlayer integer,
turnsLeft integer,
turnOrder varchar(100)
);

create table player (
player_identifier integer primary key generated always as identity,
player_identifier_in_game integer,
name varchar(100),
position integer,
team integer,
points integer,
fk_game_identifier integer,
foreign key (fk_game_identifier) references game (game_identifier)
);

create table image (
image_identifier integer primary key generated always as identity,
path varchar (100),
fk_game_identifier integer,
foreign key (fk_game_identifier) references game (game_identifier)
);