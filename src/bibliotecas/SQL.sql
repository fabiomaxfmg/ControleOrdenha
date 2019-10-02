--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

-- Started on 2019-10-02 13:14:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 569 (class 1247 OID 24731)
-- Name: dbrinco; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.dbrinco AS integer
	CONSTRAINT dbrinco_check CHECK ((VALUE > 0));


ALTER DOMAIN public.dbrinco OWNER TO postgres;

--
-- TOC entry 574 (class 1247 OID 24786)
-- Name: doperacao; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.doperacao AS character varying(10) DEFAULT NULL::character varying
	CONSTRAINT doperacao_check CHECK (((VALUE)::text = ANY ((ARRAY['LEITE'::character varying, 'INSEMINAR'::character varying])::text[])));


ALTER DOMAIN public.doperacao OWNER TO postgres;

--
-- TOC entry 573 (class 1247 OID 24785)
-- Name: dproducaodata; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.dproducaodata AS date DEFAULT '2019-10-01'::date;


ALTER DOMAIN public.dproducaodata OWNER TO postgres;

--
-- TOC entry 571 (class 1247 OID 24783)
-- Name: situacao; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.situacao AS integer DEFAULT 0
	CONSTRAINT situacao_check CHECK (((VALUE >= 0) AND (VALUE <= 3)));


ALTER DOMAIN public.situacao OWNER TO postgres;

--
-- TOC entry 199 (class 1255 OID 24881)
-- Name: atualizar_prod_media(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.atualizar_prod_media() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
		NEW.mediapervaca := NEW.producao_litros / NEW.nr_ordenha_uteis;
		RETURN NEW;
	END;
	$$;


ALTER FUNCTION public.atualizar_prod_media() OWNER TO postgres;

--
-- TOC entry 202 (class 1255 OID 24884)
-- Name: audit_inseminar(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.audit_inseminar() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        -- Registrar quem fez o "leite"
        INSERT INTO auditoria(data, operacao, usuario)VALUES('now','INSEMINAR',CURRENT_USER);
   		RETURN NEW;
   END;
$$;


ALTER FUNCTION public.audit_inseminar() OWNER TO postgres;

--
-- TOC entry 201 (class 1255 OID 24883)
-- Name: audit_leite(); Type: FUNCTION; Schema: public; Owner: postgres
--

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 193 (class 1259 OID 24869)
-- Name: auditoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auditoria (
    data public.dproducaodata NOT NULL,
    operacao public.doperacao,
    usuario name
);


ALTER TABLE public.auditoria OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24840)
-- Name: inseminacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inseminacao (
    codigo integer NOT NULL,
    data date,
    cod_situacao public.situacao,
    observacao character varying(50),
    brinco_vaca public.dbrinco,
    cod_inseminador integer
);


ALTER TABLE public.inseminacao OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 24838)
-- Name: inseminacao_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inseminacao_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inseminacao_codigo_seq OWNER TO postgres;

--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 188
-- Name: inseminacao_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inseminacao_codigo_seq OWNED BY public.inseminacao.codigo;


--
-- TOC entry 187 (class 1259 OID 24832)
-- Name: inseminador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inseminador (
    codigo integer NOT NULL,
    nome character varying(30)
);


ALTER TABLE public.inseminador OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 24830)
-- Name: inseminador_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inseminador_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inseminador_codigo_seq OWNER TO postgres;

--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 186
-- Name: inseminador_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inseminador_codigo_seq OWNED BY public.inseminador.codigo;


--
-- TOC entry 196 (class 1259 OID 24972)
-- Name: inssituacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inssituacao (
    codigo integer NOT NULL,
    nome character varying(30)
);


ALTER TABLE public.inssituacao OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 24970)
-- Name: inssituacao_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inssituacao_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inssituacao_codigo_seq OWNER TO postgres;

--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 195
-- Name: inssituacao_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inssituacao_codigo_seq OWNED BY public.inssituacao.codigo;


--
-- TOC entry 192 (class 1259 OID 24864)
-- Name: producao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producao (
    data public.dproducaodata NOT NULL,
    producao_litros integer
);


ALTER TABLE public.producao OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24790)
-- Name: raca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.raca (
    codigo integer NOT NULL,
    descricao character varying(100)
);


ALTER TABLE public.raca OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 24788)
-- Name: raca_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.raca_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.raca_codigo_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 181
-- Name: raca_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.raca_codigo_seq OWNED BY public.raca.codigo;


--
-- TOC entry 184 (class 1259 OID 24798)
-- Name: touro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.touro (
    codigo integer NOT NULL,
    nome character varying(30),
    cod_raca integer
);


ALTER TABLE public.touro OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 24796)
-- Name: touro_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.touro_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.touro_codigo_seq OWNER TO postgres;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 183
-- Name: touro_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.touro_codigo_seq OWNED BY public.touro.codigo;


--
-- TOC entry 191 (class 1259 OID 24858)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    codigo integer NOT NULL,
    nome character varying(40),
    login character varying(30),
    senha character varying(30)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 24856)
-- Name: usuario_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_codigo_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_codigo_seq OWNED BY public.usuario.codigo;


--
-- TOC entry 185 (class 1259 OID 24809)
-- Name: vaca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaca (
    brinco public.dbrinco NOT NULL,
    nome character varying(40) DEFAULT 'Jairo'::character varying,
    data_nascimento date,
    cod_situacao integer NOT NULL,
    brinco_mae public.dbrinco,
    cod_raca integer NOT NULL,
    cod_touro integer
);


ALTER TABLE public.vaca OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 24980)
-- Name: vacasituacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vacasituacao (
    codigo integer NOT NULL,
    nome character varying(30)
);


ALTER TABLE public.vacasituacao OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 24978)
-- Name: vacasituacao_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vacasituacao_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vacasituacao_codigo_seq OWNER TO postgres;

--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 197
-- Name: vacasituacao_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vacasituacao_codigo_seq OWNED BY public.vacasituacao.codigo;


--
-- TOC entry 194 (class 1259 OID 24877)
-- Name: vusuarios; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vusuarios AS
 SELECT usuario.nome,
    usuario.login
   FROM public.usuario;


ALTER TABLE public.vusuarios OWNER TO postgres;

--
-- TOC entry 2049 (class 2604 OID 24843)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminacao ALTER COLUMN codigo SET DEFAULT nextval('public.inseminacao_codigo_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 24835)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminador ALTER COLUMN codigo SET DEFAULT nextval('public.inseminador_codigo_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 24975)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inssituacao ALTER COLUMN codigo SET DEFAULT nextval('public.inssituacao_codigo_seq'::regclass);


--
-- TOC entry 2045 (class 2604 OID 24793)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.raca ALTER COLUMN codigo SET DEFAULT nextval('public.raca_codigo_seq'::regclass);


--
-- TOC entry 2046 (class 2604 OID 24801)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.touro ALTER COLUMN codigo SET DEFAULT nextval('public.touro_codigo_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 24861)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN codigo SET DEFAULT nextval('public.usuario_codigo_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 24983)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacasituacao ALTER COLUMN codigo SET DEFAULT nextval('public.vacasituacao_codigo_seq'::regclass);


--
-- TOC entry 2212 (class 0 OID 24869)
-- Dependencies: 193
-- Data for Name: auditoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auditoria (data, operacao, usuario) FROM stdin;
2019-10-01	LEITE	postgres
\.


--
-- TOC entry 2208 (class 0 OID 24840)
-- Dependencies: 189
-- Data for Name: inseminacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inseminacao (codigo, data, cod_situacao, observacao, brinco_vaca, cod_inseminador) FROM stdin;
\.


--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 188
-- Name: inseminacao_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inseminacao_codigo_seq', 1, false);


--
-- TOC entry 2206 (class 0 OID 24832)
-- Dependencies: 187
-- Data for Name: inseminador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inseminador (codigo, nome) FROM stdin;
\.


--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 186
-- Name: inseminador_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inseminador_codigo_seq', 1, false);


--
-- TOC entry 2214 (class 0 OID 24972)
-- Dependencies: 196
-- Data for Name: inssituacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inssituacao (codigo, nome) FROM stdin;
1	Situação Inseminação1
2	Sit Insemin 2
\.


--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 195
-- Name: inssituacao_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inssituacao_codigo_seq', 2, true);


--
-- TOC entry 2211 (class 0 OID 24864)
-- Dependencies: 192
-- Data for Name: producao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.producao (data, producao_litros) FROM stdin;
2019-10-01	1450
\.


--
-- TOC entry 2201 (class 0 OID 24790)
-- Dependencies: 182
-- Data for Name: raca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.raca (codigo, descricao) FROM stdin;
1	Highlander
\.


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 181
-- Name: raca_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.raca_codigo_seq', 1, true);


--
-- TOC entry 2203 (class 0 OID 24798)
-- Dependencies: 184
-- Data for Name: touro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.touro (codigo, nome, cod_raca) FROM stdin;
\.


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 183
-- Name: touro_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.touro_codigo_seq', 1, false);


--
-- TOC entry 2210 (class 0 OID 24858)
-- Dependencies: 191
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (codigo, nome, login, senha) FROM stdin;
\.


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_codigo_seq', 1, false);


--
-- TOC entry 2204 (class 0 OID 24809)
-- Dependencies: 185
-- Data for Name: vaca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaca (brinco, nome, data_nascimento, cod_situacao, brinco_mae, cod_raca, cod_touro) FROM stdin;
120	Jairo	\N	1	\N	1	\N
\.


--
-- TOC entry 2216 (class 0 OID 24980)
-- Dependencies: 198
-- Data for Name: vacasituacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vacasituacao (codigo, nome) FROM stdin;
1	Situação 1
2	Situação 2
\.


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 197
-- Name: vacasituacao_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vacasituacao_codigo_seq', 2, true);


--
-- TOC entry 2068 (class 2606 OID 24876)
-- Name: pk_audit; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditoria
    ADD CONSTRAINT pk_audit PRIMARY KEY (data);


--
-- TOC entry 2062 (class 2606 OID 24845)
-- Name: pk_inseminacao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminacao
    ADD CONSTRAINT pk_inseminacao PRIMARY KEY (codigo);


--
-- TOC entry 2060 (class 2606 OID 24837)
-- Name: pk_inseminador; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminador
    ADD CONSTRAINT pk_inseminador PRIMARY KEY (codigo);


--
-- TOC entry 2070 (class 2606 OID 24977)
-- Name: pk_inssituacao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inssituacao
    ADD CONSTRAINT pk_inssituacao PRIMARY KEY (codigo);


--
-- TOC entry 2066 (class 2606 OID 24868)
-- Name: pk_producao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producao
    ADD CONSTRAINT pk_producao PRIMARY KEY (data);


--
-- TOC entry 2054 (class 2606 OID 24795)
-- Name: pk_raca; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.raca
    ADD CONSTRAINT pk_raca PRIMARY KEY (codigo);


--
-- TOC entry 2056 (class 2606 OID 24803)
-- Name: pk_touro; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.touro
    ADD CONSTRAINT pk_touro PRIMARY KEY (codigo);


--
-- TOC entry 2064 (class 2606 OID 24863)
-- Name: pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (codigo);


--
-- TOC entry 2058 (class 2606 OID 24814)
-- Name: pk_vaca; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaca
    ADD CONSTRAINT pk_vaca PRIMARY KEY (brinco);


--
-- TOC entry 2072 (class 2606 OID 24985)
-- Name: pk_vacasituacao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacasituacao
    ADD CONSTRAINT pk_vacasituacao PRIMARY KEY (codigo);


--
-- TOC entry 2084 (class 2620 OID 24888)
-- Name: atualizar_prod_media; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER atualizar_prod_media AFTER UPDATE ON public.producao FOR EACH ROW EXECUTE PROCEDURE public.atualizar_prod_media();


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


--
-- TOC entry 2080 (class 2606 OID 24996)
-- Name: fk_ins_situacao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminacao
    ADD CONSTRAINT fk_ins_situacao FOREIGN KEY (cod_situacao) REFERENCES public.inssituacao(codigo);


--
-- TOC entry 2079 (class 2606 OID 24851)
-- Name: fk_inseminacao_inseminador; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminacao
    ADD CONSTRAINT fk_inseminacao_inseminador FOREIGN KEY (cod_inseminador) REFERENCES public.inseminador(codigo);


--
-- TOC entry 2078 (class 2606 OID 24846)
-- Name: fk_inseminacao_vaca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inseminacao
    ADD CONSTRAINT fk_inseminacao_vaca FOREIGN KEY (brinco_vaca) REFERENCES public.vaca(brinco);


--
-- TOC entry 2074 (class 2606 OID 24815)
-- Name: fk_mae_vaca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaca
    ADD CONSTRAINT fk_mae_vaca FOREIGN KEY (brinco_mae) REFERENCES public.vaca(brinco);


--
-- TOC entry 2077 (class 2606 OID 24991)
-- Name: fk_situacao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaca
    ADD CONSTRAINT fk_situacao FOREIGN KEY (cod_situacao) REFERENCES public.vacasituacao(codigo);


--
-- TOC entry 2073 (class 2606 OID 24804)
-- Name: fk_tour_raca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.touro
    ADD CONSTRAINT fk_tour_raca FOREIGN KEY (cod_raca) REFERENCES public.raca(codigo);


--
-- TOC entry 2075 (class 2606 OID 24820)
-- Name: fk_vaca_raca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaca
    ADD CONSTRAINT fk_vaca_raca FOREIGN KEY (cod_raca) REFERENCES public.raca(codigo);


--
-- TOC entry 2076 (class 2606 OID 24825)
-- Name: fk_vaca_touro; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaca
    ADD CONSTRAINT fk_vaca_touro FOREIGN KEY (cod_touro) REFERENCES public.touro(codigo);


--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-10-02 13:14:37

--
-- PostgreSQL database dump complete
--

