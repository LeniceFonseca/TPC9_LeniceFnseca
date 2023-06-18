create database pedradadigital;
use pedradadigital;


create table usuarios (
  id int auto_increment primary key,
  nome varchar(100) not null,
  apelido varchar(100) not null,
  email varchar(100) not null,
  username varchar(100) not null,
  password varchar(100) not null,
  categoria varchar(100) not null,
  alcunha varchar(100),
  pseudonimo varchar(100),
  biografia varchar(100)
);

create table pedradafoto (
	id int auto_increment primary key,
    imagem varchar(100) not null,
	legenda text,
	autor varchar(100) not null,
	data varchar(100) not null
);

create table comentarios (
	id int auto_increment primary key,
    autorcomentario varchar(100) not null,
	datacomentario varchar(100) not null,
	post varchar(100) not null,
	comentario text
);

create table fotos (
	id int auto_increment primary key,
    nome varchar(100),
	fotoperfil varchar(100)
);

insert into fotos (nome, fotoperfil) values
  ('lens', 'file:src/main/resources/com/example/tp3_lenicefonseca/images/lens.jpg'),
  ('jotaP', 'file:src/main/resources/com/example/tp3_lenicefonseca/images/jotaP.jpg'),
  ('mali', 'file:src/main/resources/com/example/tp3_lenicefonseca/images/mali.jpg'),
  ('tt', 'file:src/main/resources/com/example/tp3_lenicefonseca/images/tt.jpg'),
  ('juninha', 'file:src/main/resources/com/example/tp3_lenicefonseca/images/juninha.jpg');
  









