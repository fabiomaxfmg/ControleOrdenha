CREATE TABLE public.auditoria
(
    data dproducaodata NOT NULL,
    operacao doperacao COLLATE pg_catalog."default",
    usuario name,
    codigo SERIAL,
    CONSTRAINT pk_auditoria PRIMARY KEY (codigo)
);
CREATE TABLE public.inseminacao
(
    codigo SERIAL,
    data date,
    cod_situacao situacao,
    observacao character varying(50) COLLATE pg_catalog."default",
    brinco_vaca dbrinco,
    cod_inseminador integer,
    CONSTRAINT pk_inseminacao PRIMARY KEY (codigo),
    CONSTRAINT fk_ins_situacao FOREIGN KEY (cod_situacao)
        REFERENCES public.inssituacao (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_inseminacao_inseminador FOREIGN KEY (cod_inseminador)
        REFERENCES public.inseminador (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_inseminacao_vaca FOREIGN KEY (brinco_vaca)
        REFERENCES public.vaca (brinco) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.inseminador
(
    codigo serial,
    nome character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT pk_inseminador PRIMARY KEY (codigo)
);

CREATE TABLE public.inssituacao
(
    codigo SERIAL,
    nome character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT pk_inssituacao PRIMARY KEY (codigo)
);
CREATE TABLE public.producao
(
    data dproducaodata NOT NULL,
    producao_litros integer,
    codigo SERIAL,
    mediapervaca double precision,
    nr_ordenha_uteis integer,
    CONSTRAINT pk_producao PRIMARY KEY (codigo)
);
CREATE TABLE public.raca
(
    codigo SERIAL,
    descricao character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT pk_raca PRIMARY KEY (codigo)
);
CREATE TABLE public.touro
(
    codigo SERIAL,
    nome character varying(30) COLLATE pg_catalog."default",
    cod_raca integer,
    CONSTRAINT pk_touro PRIMARY KEY (codigo),
    CONSTRAINT fk_tour_raca FOREIGN KEY (cod_raca)
        REFERENCES public.raca (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.usuario
(
    codigo SERIAL,
    nome character varying(40) COLLATE pg_catalog."default",
    login character varying(30) COLLATE pg_catalog."default",
    senha character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT pk_usuario PRIMARY KEY (codigo)
);

CREATE TABLE public.vaca
(
    brinco dbrinco NOT NULL,
    nome character varying(40) COLLATE pg_catalog."default" DEFAULT 'Jairo'::character varying,
    data_nascimento date,
    cod_situacao integer NOT NULL,
    brinco_mae dbrinco,
    cod_raca integer NOT NULL,
    cod_touro integer,
    CONSTRAINT pk_vaca PRIMARY KEY (brinco),
    CONSTRAINT fk_mae_vaca FOREIGN KEY (brinco_mae)
        REFERENCES public.vaca (brinco) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_situacao FOREIGN KEY (cod_situacao)
        REFERENCES public.vacasituacao (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vaca_raca FOREIGN KEY (cod_raca)
        REFERENCES public.raca (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_vaca_touro FOREIGN KEY (cod_touro)
        REFERENCES public.touro (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.vacasituacao
(
    codigo SERIAL,
    nome character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT pk_vacasituacao PRIMARY KEY (codigo)
);

CREATE DOMAIN public.dbrinco
    AS integer;

ALTER DOMAIN public.dbrinco OWNER TO postgres;

ALTER DOMAIN public.dbrinco
    ADD CONSTRAINT dbrinco_check CHECK (VALUE > 0);
	
CREATE DOMAIN public.doperacao
    AS character varying(10)
    DEFAULT NULL::character varying;

ALTER DOMAIN public.doperacao OWNER TO postgres;

ALTER DOMAIN public.doperacao
    ADD CONSTRAINT doperacao_check CHECK (VALUE::text = ANY (ARRAY['LEITE'::character varying::text, 'INSEMINAR'::character varying::text]));

CREATE DOMAIN public.dproducaodata
    AS date
    DEFAULT '2019-10-01'::date;
	
CREATE DOMAIN public.situacao
    AS integer
    DEFAULT 0;

ALTER DOMAIN public.situacao OWNER TO postgres;

ALTER DOMAIN public.situacao
    ADD CONSTRAINT situacao_check CHECK (VALUE >= 0 AND VALUE <= 3);
ALTER DOMAIN public.dproducaodata OWNER TO postgres;

CREATE FUNCTION public.atualizar_prod_media() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
		NEW.mediapervaca := NEW.producao_litros / NEW.nr_ordenha_uteis;
		RETURN NEW;
	END;
	$$;


ALTER FUNCTION public.atualizar_prod_media() OWNER TO postgres;
CREATE FUNCTION public.audit_inseminar() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        -- Registrar quem fez o "leite"
        INSERT INTO auditoria(data, operacao, usuario)VALUES('now','INSEMINAR',CURRENT_USER);
   		RETURN NEW;
   END;
$$;
CREATE FUNCTION public.audit_leite() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        -- Registrar quem fez o leite
        INSERT INTO auditoria(data, operacao, usuario)VALUES('now','LEITE',CURRENT_USER);
    	RETURN NEW;
	END;
$$;


ALTER FUNCTION public.audit_leite() OWNER TO postgres;

--
-- TOC entry 200 (class 1255 OID 24882)
-- Name: inserir_prod_media(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.inserir_prod_media() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
		NEW.mediapervaca := NEW.producao_litros / NEW.nr_ordenha_uteis;
		RETURN NEW;
	END;
	$$;


ALTER FUNCTION public.inserir_prod_media() OWNER TO postgres;

CREATE TRIGGER atualizar_prod_media BEFORE UPDATE ON public.producao FOR EACH ROW EXECUTE PROCEDURE public.atualizar_prod_media();


--
-- TOC entry 2081 (class 2620 OID 24886)
-- Name: audit_inseminar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER audit_inseminar AFTER INSERT OR UPDATE ON public.inseminacao FOR EACH ROW EXECUTE PROCEDURE public.audit_inseminar();


--
-- TOC entry 2083 (class 2620 OID 24887)
-- Name: audit_leite; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER audit_leite AFTER INSERT OR UPDATE ON public.producao FOR EACH ROW EXECUTE PROCEDURE public.audit_leite();


--
-- TOC entry 2082 (class 2620 OID 24885)
-- Name: inserir_prod_media; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER inserir_prod_media BEFORE INSERT ON public.producao FOR EACH ROW EXECUTE PROCEDURE public.inserir_prod_media();



create role usuario
grant all on all tables in schema public to usuario
grant all on all sequences in schema public to usuario
