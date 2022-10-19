DROP TABLE endereco;
DROP TABLE municipio;
DROP TABLE foto_publicacao;
DROP TABLE publicacao;
DROP TABLE foto_anuncio;
DROP TABLE pedido;
DROP TABLE anuncio;
DROP TABLE categoria;
DROP TABLE tipo_servico;
DROP TABLE usuario;

begin;

create table usuario (
    id serial primary key,
    username varchar(100) unique not null,
    email varchar(100) unique not null,
    senha varchar(150),
    cpf varchar(11) unique not null,
    nome varchar(100) not null,
    telefone varchar(11) unique,
    tipo VARCHAR (20) NOT NULL,
    data_nascimento timestamp not null
);

create table municipio (
    id serial primary key,
    cidade varchar(100) not null,
    uf varchar(2) not null
);

create table endereco (
    id serial primary key,
    logradouro varchar(150) not null,
    numero varchar(10) not null,
    cep varchar(8),
    bairro varchar(100) not null,
    usuario_id integer references usuario(id),
    municipio_id integer references municipio(id)
);

create table publicacao (
    id serial primary key,
    titulo varchar(100) not null,
    descricao varchar(250) not null,
    local_envento varchar(500),
    usuario_id integer references usuario(id)
);

create table foto_publicacao (
    id serial primary key,
    foto varchar(300) not null,
    publicacao_id integer not null references publicacao(id),
    unique(foto, publicacao_id)
);

create table categoria (
    id serial primary key,
    descricao varchar(250) unique not null
);

create table tipo_servico (
    id serial primary key,
    descricao varchar(250) unique not null
);

create table anuncio (
    id serial primary key,
    titulo varchar(100) not null,
    descricao varchar(250),
    tipo varchar(15) check(tipo in ('PRODUTO','SERVICO')),
    preco varchar(15),
    categoria_id integer references categoria(id),
    tipo_servico_id integer references tipo_servico(id),
    usuario_id integer references usuario(id),
    unique (categoria_id, tipo_servico_id, usuario_id)
);

create table foto_anuncio(
    id serial primary key,
    foto varchar(300),
    anuncio_id integer not null references anuncio(id)
);

create table pedido (
    id serial primary key,
    quantidade int not null,
    data_hora timestamp default now(),
    situacao varchar(30),
    anuncio_id integer not null references anuncio(id),
    usuario_id integer references usuario(id)
);

commit;

INSERT INTO public.usuario(
	id, username, email, senha, cpf, nome, telefone, tipo, data_nascimento)
	VALUES (default, 'admin', 'admin@gmail.com', '123', '12345678910', 'administrador', '35998402230', 'ADMIN', '25/02/1997');
	
INSERT INTO public.usuario(
	id, username, email, senha, cpf, nome, telefone, tipo, data_nascimento)
	VALUES (default, 'userpadrao', 'userpadrao@gmail.com', '123456', '10340608910', 'usuario', '35999402050',  'CLIENT', '15/02/1998');
	

INSERT INTO public.usuario(
	id, username, email, senha, cpf, nome, telefone, tipo, data_nascimento)
	VALUES (default, 'gabriel', 'gabriel1000@gmail.com', '5678', '17349648912', 'gabriel', '35997402156', 'CLIENT', '14/10/1980');

INSERT INTO public.categoria(
	id, descricao)
	VALUES (DEFAULT, 'Casa Chale');


INSERT INTO public.categoria(
	id, descricao)
	VALUES (DEFAULT, 'Cama');


INSERT INTO public.categoria(
	id, descricao)
	VALUES (DEFAULT, 'Brinquedo');

INSERT INTO public.categoria(
	id, descricao)
	VALUES (DEFAULT, 'Roupa');

INSERT INTO public.categoria(
	id, descricao)
	VALUES (DEFAULT, 'Banho e Tosa');

INSERT INTO public.municipio(
	id, cidade, uf)
	VALUES (DEFAULT, 'Santa Rita do Sapucai', 'MG');


INSERT INTO public.municipio(
	id, cidade, uf)
	VALUES (DEFAULT, 'Belo Horizontee', 'MG');


INSERT INTO public.municipio(
	id, cidade, uf)
	VALUES (DEFAULT, 'Pouso Alegre', 'MG');

INSERT INTO public.endereco(
	id, logradouro, numero, cep, bairro, usuario_id, municipio_id)
	VALUES (DEFAULT, 'Rua:leopoldo de luna', '120', '37540000', 'Jardim Santo Antonio', '3', '1');


INSERT INTO public.endereco(
	id, logradouro, numero, cep, bairro, usuario_id, municipio_id)
	VALUES (DEFAULT, 'Rua:leopoldina', '160', '37550439', 'São João', '1', '2');


INSERT INTO public.endereco(
	id, logradouro, numero, cep, bairro, usuario_id, municipio_id)
	VALUES (DEFAULT, 'Rua:Natercia', '160', '37550439', 'São João', '2', '3');

INSERT INTO public.tipo_servico(
	id, descricao)
	VALUES (default, 'Banho e tosa');

INSERT INTO public.tipo_servico(
	id, descricao)
	VALUES (default, 'Nenhum');

INSERT INTO public.anuncio(
	id, titulo, descricao, tipo, preco, categoria_id, tipo_servico_id, usuario_id)
	VALUES (default, 'Au Miau', ' A empresa Auu Miau Pet Shop E Banho Tosa Me', 'SERVICO', 'R$ 30,00', '4', '1', '1');

INSERT INTO public.anuncio(
	id, titulo, descricao, tipo, preco, categoria_id, tipo_servico_id, usuario_id)
	VALUES (default, 'Casa Chale', 'Casinha de cachorro', 'PRODUTO', 'R$ 699,00', '1', '2', '1');

INSERT INTO public.anuncio(
	id, titulo, descricao, tipo, preco, categoria_id, tipo_servico_id, usuario_id)
	VALUES (default, 'Cama', 'Cama de pets', 'PRODUTO', 'R$ 117,00', '2', '2', '1');

INSERT INTO public.anuncio(
	id, titulo, descricao, tipo, preco, categoria_id, tipo_servico_id, usuario_id)
	VALUES (default, 'Brinquedo', 'biquedos pets', 'PRODUTO', 'R$ 79,00', '3', '2', '1');

INSERT INTO public.anuncio(
	id, titulo, descricao, tipo, preco, categoria_id, tipo_servico_id, usuario_id)
	VALUES (default, 'Roupa', 'roupinha pet', 'PRODUTO', 'R$ 63,00', '4', '2', '1');
    