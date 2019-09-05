--Inseminador(codigo, nome)

CREATE TABLE inseminador(
   codigo SERIAL NOT NULL,
   nome VARCHAR (99) NOT NULL,
   CONSTRAINT pk_inseminador PRIMARY KEY (codigo)
   );

--Raça (codigo, descricao)

CREATE TABLE raca(
   codigo SERIAL NOT NULL,
   descricao VARCHAR (40) NOT NULL,
   CONSTRAINT pk_raca PRIMARY KEY (codigo)
   );

--Touro (numero_nome, cod_raca#)
--cod_raca referencia raca(codigo)

CREATE TABLE touro(
   codigo SERIAL NOT NULL,
   nome VARCHAR (30) NOT NULL,
   cod_raca INT NOT NULL,
   CONSTRAINT pk_touro PRIMARY KEY (codigo),
   CONSTRAINT fk_touro_raca FOREIGN KEY (cod_raca) REFERENCES raca (codigo)
   );

--Vaca (codigo, nome, data, data_nascimento, cod_raca#, cod_mae#, cod_touro#)
--cod_raca referencia raca (codigo)
--cod_touro referencia touro (codigo)

CREATE TABLE vaca(
   codigo SERIAL NOT NULL,
   nome VARCHAR(30) NOT NULL,
   data_nascimento DATE NOT NULL,
   cod_raca INT NOT NULL,
   cod_mae INT NOT NULL,
   cod_touro INT NOT NULL,
   CONSTRAINT pk_vaca PRIMARY KEY (codigo),
   CONSTRAINT fk_vaca_raca FOREIGN KEY (cod_raca) REFERENCES raca (codigo),
   CONSTRAINT fk_vaca_vaca FOREIGN KEY (cod_mae) REFERENCES vaca (codigo),
   CONSTRAINT fk_vaca_touro FOREIGN KEY (cod_touro) REFERENCES touro (codigo)
   ); 
  
--Inseminação (codigo, data, situacao, observacao, cod_inseminador#)
--cod_inseminador referencia inseminador (codigo) 
--cod_vaca referencia vaca (codigo)

CREATE TABLE inseminacao(
   codigo SERIAL NOT NULL,
   data DATE NOT NULL,
   situacao INT NOT NULL,
   observacao VARCHAR (100) NOT NULL,
   cod_inseminador INT NOT NULL,
   cod_vaca INT NOT NULL,
   CONSTRAINT pk_inseminacao PRIMARY KEY (codigo),
   CONSTRAINT fk_inseminacao_inseminador FOREIGN KEY (cod_inseminador) REFERENCES inseminador (codigo),
   CONSTRAINT fk_inseminacao_vaca FOREIGN KEY (cod_vaca) REFERENCES vaca (codigo)
   );

--Produção (data, producao_em_litros)

CREATE TABLE producao(
   data DATE NOT NULL,
   producao_em_litros INT NOT NULL,
   CONSTRAINT pk_producao PRIMARY KEY (data)
   );

--Usuário(login, senha)

CREATE TABLE usuario (
   login VARCHAR (40) NOT NULL,
   senha INT NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (login)
   );