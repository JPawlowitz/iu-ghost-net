--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Debian 16.1-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT fki7bw306cygo4wb49xr1ppght2;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT fkff24jgw1xirybk9oahnx3p3m4;
ALTER TABLE ONLY public.reporter DROP CONSTRAINT fkdhlc3upry7lwdyctb5yuqi7so;
ALTER TABLE ONLY public.salvager DROP CONSTRAINT fkcxsgfcr9c6p52v5xm9vskd5pm;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT fk9icajroeuike5qt4cydic7x1s;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT fk1702sxl4s37na30iijhr2wavw;
ALTER TABLE ONLY public.salvager DROP CONSTRAINT salvager_pkey;
ALTER TABLE ONLY public.reporter DROP CONSTRAINT reporter_pkey;
ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
ALTER TABLE ONLY public.gpscoordinate DROP CONSTRAINT gpscoordinate_pkey;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT ghostnet_reporter_id_key;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT ghostnet_pkey;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT ghostnet_longitude_id_key;
ALTER TABLE ONLY public.ghostnet DROP CONSTRAINT ghostnet_latitude_id_key;
ALTER TABLE public.person ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.gpscoordinate ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.ghostnet ALTER COLUMN id DROP DEFAULT;
DROP TABLE public.salvager;
DROP TABLE public.reporter;
DROP SEQUENCE public.person_id_seq;
DROP TABLE public.person;
DROP SEQUENCE public.gpscoordinate_id_seq;
DROP TABLE public.gpscoordinate;
DROP SEQUENCE public.ghostnet_id_seq;
DROP TABLE public.ghostnet;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ghostnet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ghostnet (
    id integer NOT NULL,
    latitude_id integer,
    longitude_id integer,
    reporter_id integer,
    salvager_id integer,
    size integer NOT NULL,
    status character varying(255),
    CONSTRAINT ghostnet_status_check CHECK (((status)::text = ANY ((ARRAY['Gemeldet'::character varying, 'Bergung'::character varying, 'Geborgen'::character varying, 'Verschollen'::character varying])::text[])))
);


ALTER TABLE public.ghostnet OWNER TO postgres;

--
-- Name: ghostnet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ghostnet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ghostnet_id_seq OWNER TO postgres;

--
-- Name: ghostnet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ghostnet_id_seq OWNED BY public.ghostnet.id;


--
-- Name: gpscoordinate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gpscoordinate (
    degrees double precision NOT NULL,
    id integer NOT NULL,
    minutes double precision NOT NULL,
    seconds double precision NOT NULL,
    hemisphere character varying(255),
    CONSTRAINT gpscoordinate_hemisphere_check CHECK (((hemisphere)::text = ANY ((ARRAY['Norden'::character varying, 'Süden'::character varying, 'Westen'::character varying, 'Osten'::character varying])::text[])))
);


ALTER TABLE public.gpscoordinate OWNER TO postgres;

--
-- Name: gpscoordinate_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.gpscoordinate_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.gpscoordinate_id_seq OWNER TO postgres;

--
-- Name: gpscoordinate_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.gpscoordinate_id_seq OWNED BY public.gpscoordinate.id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.person_id_seq OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- Name: reporter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reporter (
    id integer NOT NULL,
    phonenumber character varying(255)
);


ALTER TABLE public.reporter OWNER TO postgres;

--
-- Name: salvager; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salvager (
    id integer NOT NULL,
    phonenumber character varying(255)
);


ALTER TABLE public.salvager OWNER TO postgres;

--
-- Name: ghostnet id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet ALTER COLUMN id SET DEFAULT nextval('public.ghostnet_id_seq'::regclass);


--
-- Name: gpscoordinate id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gpscoordinate ALTER COLUMN id SET DEFAULT nextval('public.gpscoordinate_id_seq'::regclass);


--
-- Name: person id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- Data for Name: ghostnet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ghostnet (id, latitude_id, longitude_id, reporter_id, salvager_id, size, status) FROM stdin;
1	1	2	1	\N	22	Gemeldet
5	9	10	5	\N	42	Gemeldet
3	5	6	3	6	12	Bergung
2	3	4	2	7	4	Geborgen
4	7	8	4	8	42	Bergung
\.


--
-- Data for Name: gpscoordinate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gpscoordinate (degrees, id, minutes, seconds, hemisphere) FROM stdin;
3	1	20	24	Osten
55	2	7	48	Norden
12	3	43	48	Westen
44	4	47	16.8	Norden
30	5	21	18.81	Osten
33	6	41	45.63	Norden
65	7	44	16.26	Westen
143	8	57	53.43	Süden
65	9	44	16.26	Westen
143	10	57	53.43	Süden
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id, name) FROM stdin;
1	Max Mustermelder
2	John Doe
3	John Doe
4	John Doe
5	John Doe
6	Max Musterberger
7	Max Musterberger
8	Joe Salvager
\.


--
-- Data for Name: reporter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reporter (id, phonenumber) FROM stdin;
1	+49 1234 5678-123
2	
3	
4	
5	
\.


--
-- Data for Name: salvager; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salvager (id, phonenumber) FROM stdin;
6	+49 9876 5678-978
7	+49 9876 5678-978
8	+49 1234 5678-567
\.


--
-- Name: ghostnet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ghostnet_id_seq', 5, true);


--
-- Name: gpscoordinate_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.gpscoordinate_id_seq', 10, true);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 8, true);


--
-- Name: ghostnet ghostnet_latitude_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT ghostnet_latitude_id_key UNIQUE (latitude_id);


--
-- Name: ghostnet ghostnet_longitude_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT ghostnet_longitude_id_key UNIQUE (longitude_id);


--
-- Name: ghostnet ghostnet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT ghostnet_pkey PRIMARY KEY (id);


--
-- Name: ghostnet ghostnet_reporter_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT ghostnet_reporter_id_key UNIQUE (reporter_id);


--
-- Name: gpscoordinate gpscoordinate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gpscoordinate
    ADD CONSTRAINT gpscoordinate_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: reporter reporter_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporter
    ADD CONSTRAINT reporter_pkey PRIMARY KEY (id);


--
-- Name: salvager salvager_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salvager
    ADD CONSTRAINT salvager_pkey PRIMARY KEY (id);


--
-- Name: ghostnet fk1702sxl4s37na30iijhr2wavw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT fk1702sxl4s37na30iijhr2wavw FOREIGN KEY (reporter_id) REFERENCES public.reporter(id);


--
-- Name: ghostnet fk9icajroeuike5qt4cydic7x1s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT fk9icajroeuike5qt4cydic7x1s FOREIGN KEY (longitude_id) REFERENCES public.gpscoordinate(id);


--
-- Name: salvager fkcxsgfcr9c6p52v5xm9vskd5pm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salvager
    ADD CONSTRAINT fkcxsgfcr9c6p52v5xm9vskd5pm FOREIGN KEY (id) REFERENCES public.person(id);


--
-- Name: reporter fkdhlc3upry7lwdyctb5yuqi7so; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporter
    ADD CONSTRAINT fkdhlc3upry7lwdyctb5yuqi7so FOREIGN KEY (id) REFERENCES public.person(id);


--
-- Name: ghostnet fkff24jgw1xirybk9oahnx3p3m4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT fkff24jgw1xirybk9oahnx3p3m4 FOREIGN KEY (latitude_id) REFERENCES public.gpscoordinate(id);


--
-- Name: ghostnet fki7bw306cygo4wb49xr1ppght2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ghostnet
    ADD CONSTRAINT fki7bw306cygo4wb49xr1ppght2 FOREIGN KEY (salvager_id) REFERENCES public.salvager(id);


--
-- PostgreSQL database dump complete
--

