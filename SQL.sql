
CREATE TABLE raca(
	codigo SERIAL NOT NULL,
	descricao VARCHAR(100),
	CONSTRAINT pk_raca PRIMARY KEY (codigo)
);

CREATE TABLE touro(
	codigo SERIAL NOT NULL,
	nome VARCHAR(30),
	cod_raca INT,
	CONSTRAINT fk_tour_raca FOREIGN KEY (cod_raca) REFERENCES raca(codigo),
	CONSTRAINT pk_touro PRIMARY KEY (codigo)
);
CREATE TABLE vaca(
	brinco dbrinco NOT NULL,
	nome VARCHAR(40) DEFAULT 'Jairo',
	Data_nascimento DATE,
	situacao INT NOT NULL,  --FEITO NA PROGRAMAÇÃO
	brinco_mae dbrinco,
	cod_raca INT NOT NULL,
	cod_touro INT,
	CONSTRAINT pk_vaca PRIMARY KEY (brinco),
	CONSTRAINT fk_mae_vaca FOREIGN KEY (brinco_mae) REFERENCES vaca(brinco),
	CONSTRAINT fk_vaca_raca FOREIGN KEY (cod_raca) REFERENCES raca(codigo),
	CONSTRAINT fk_vaca_touro FOREIGN KEY (cod_touro) REFERENCES touro(codigo)
);
CREATE TABLE inseminador(
	codigo SERIAL,
	nome VARCHAR(30),
	CONSTRAINT pk_inseminador PRIMARY KEY (codigo)
);

CREATE TABLE inseminacao(
	codigo SERIAL,
	data DATE,
	situacao SITUACAO, --PROGRAMAÇão
	observacao VARCHAR(50),
	brinco_vaca dbrinco,
	cod_inseminador INT,
	CONSTRAINT pk_inseminacao PRIMARY KEY (codigo),
	CONSTRAINT fk_inseminacao_vaca FOREIGN KEY (brinco_vaca) REFERENCES vaca(brinco),
	CONSTRAINT fk_inseminacao_inseminador FOREIGN KEY(cod_inseminador) REFERENCES inseminador(codigo)
);

CREATE TABLE usuario(
	codigo SERIAL,
	nome VARCHAR(40),
	login VARCHAR(30),
	senha VARCHAR(30),
	CONSTRAINT pk_usuario PRIMARY KEY (codigo)
);

CREATE TABLE producao(
	data dProducaoData,
	producao_litros INT,
	nr_ordenha_uteis INT,
	mediapervaca float,
	CONSTRAINT pk_producao PRIMARY KEY (data)
);
CREATE TABLE auditoria(
	data dProducaoData,
	operacao dOperacao, --criar dominio--feito
	usuario name , --era int          
	CONSTRAINT pk_audit PRIMARY KEY (data)
	--constraint fk_audit_usuario FOREIGN KEY(usuario) REFERENCES usuario(codigo)
	
);
------------------------------
----criar visao de usuario---- feito
------------------------------
CREATE VIEW vUsuarios AS 
	SELECT nome,login
	FROM usuario;
	
--CREATE VIEW vAuditoria AS
--	SELECT a.data,a.operacao,u.nome,u.login
	--FROM auditoria a
	--JOIN usuario u ON a.usuario = u.codigo;
	


CREATE FUNCTION atualizar_prod_media() RETURNS TRIGGER AS $atualizar_prod_media$
	BEGIN
		NEW.mediapervaca := NEW.producao_litros / NEW.nr_ordenha_uteis;
		RETURN NEW;
	END;
	$atualizar_prod_media$ LANGUAGE plpgsql;

CREATE FUNCTION inserir_prod_media() RETURNS TRIGGER AS $inserir_prod_media$
	BEGIN
		NEW.mediapervaca := NEW.producao_litros / NEW.nr_ordenha_uteis;
		RETURN NEW;
	END;
	$inserir_prod_media$ LANGUAGE plpgsql;

CREATE FUNCTION audit_leite() RETURNS trigger AS $audit_leite$
    BEGIN
        -- Registrar quem fez o leite
        INSERT INTO auditoria(data, operacao, usuario)VALUES('now','LEITE',CURRENT_USER);
    	RETURN NEW;
	END;
$audit_leite$ LANGUAGE plpgsql;

CREATE FUNCTION audit_inseminar() RETURNS trigger AS $audit_inseminar$
    BEGIN
        -- Registrar quem fez o "leite"
        INSERT INTO auditoria(data, operacao, usuario)VALUES('now','INSEMINAR',CURRENT_USER);
   		RETURN NEW;
   END;
$audit_inseminar$ LANGUAGE plpgsql;

CREATE TRIGGER inserir_prod_media
BEFORE INSERT ON producao
    FOR EACH ROW EXECUTE PROCEDURE inserir_prod_media();

CREATE TRIGGER audit_inseminar
AFTER UPDATE OR INSERT ON inseminacao
    FOR EACH ROW EXECUTE PROCEDURE audit_inseminar();

CREATE TRIGGER audit_leite
AFTER UPDATE OR INSERT ON producao
    FOR EACH ROW EXECUTE PROCEDURE audit_leite();

CREATE TRIGGER atualizar_prod_media
AFTER UPDATE ON producao
    FOR EACH ROW EXECUTE PROCEDURE atualizar_prod_media();

CREATE DOMAIN dbrinco
AS INTEGER
CHECK(VALUE >0);

CREATE DOMAIN dProducaoData
AS DATE
DEFAULT 'now';

CREATE DOMAIN SITUACAO
AS INTEGER
DEFAULT 0  -- 0 SER BEM, 1 DOENTE, 2 FALECIDA E O 3 EU NÃO SEI, DESESPERADA TALVEZ, na inseminacao tmb, 0 sucesso, 1, não deu em nada, 2 em diante deu muito errado, tipo, nasceu um cachorro ou algo assim
CHECK (VALUE BETWEEN 0 AND 3);

CREATE DOMAIN dOperacao
AS VARCHAR(10)
DEFAULT NULL
CHECK(VALUE IN ('LEITE','INSEMINAR'))

insert into raca(descricao) values('Highlander')

insert into vaca(brinco, nome,situacao,cod_raca) values(120,'Jairo',1,1)

insert into producao(producao_litros,nr_ordenha_uteis) values (1450,40)

SELECT * from auditoria
ALTER TABLE auditoria
 ALTER COLUMN usuario TYPE name
 
 alter table auditoria
 drop constraint fk_audit_usuario
