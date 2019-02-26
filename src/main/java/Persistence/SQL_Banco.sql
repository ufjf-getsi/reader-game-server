create table game (
game_identifier integer primary key generated always as identity,
name varchar(100),
players integer,
currentPlayer integer,
turnsLeft integer
);
