--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_users; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.app_users (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.app_users OWNER TO badreddine;

--
-- Name: app_users_id_seq; Type: SEQUENCE; Schema: public; Owner: badreddine
--

CREATE SEQUENCE public.app_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_users_id_seq OWNER TO badreddine;

--
-- Name: app_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: badreddine
--

ALTER SEQUENCE public.app_users_id_seq OWNED BY public.app_users.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by bigint,
    updated_by bigint,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.category OWNER TO badreddine;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: badreddine
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO badreddine;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: badreddine
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: badreddine
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO badreddine;

--
-- Name: order_items; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.order_items (
    id bigint NOT NULL,
    quantity integer,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.order_items OWNER TO badreddine;

--
-- Name: order_items_id_seq; Type: SEQUENCE; Schema: public; Owner: badreddine
--

CREATE SEQUENCE public.order_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_items_id_seq OWNER TO badreddine;

--
-- Name: order_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: badreddine
--

ALTER SEQUENCE public.order_items_id_seq OWNED BY public.order_items.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by bigint,
    updated_by bigint,
    status character varying(255),
    text character varying(255)
);


ALTER TABLE public.orders OWNER TO badreddine;

--
-- Name: product; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by bigint,
    updated_by bigint,
    description character varying(255),
    discount integer NOT NULL,
    is_available boolean NOT NULL,
    name_product character varying(255),
    price real,
    quantity integer NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE public.product OWNER TO badreddine;

--
-- Name: product_image_files; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.product_image_files (
    id bigint NOT NULL,
    file_name character varying(255),
    product_id bigint NOT NULL,
    data oid
);


ALTER TABLE public.product_image_files OWNER TO badreddine;

--
-- Name: product_image_files_id_seq; Type: SEQUENCE; Schema: public; Owner: badreddine
--

CREATE SEQUENCE public.product_image_files_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_image_files_id_seq OWNER TO badreddine;

--
-- Name: product_image_files_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: badreddine
--

ALTER SEQUENCE public.product_image_files_id_seq OWNED BY public.product_image_files.id;


--
-- Name: product_rules; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.product_rules (
    product_id bigint NOT NULL,
    rule_id bigint NOT NULL
);


ALTER TABLE public.product_rules OWNER TO badreddine;

--
-- Name: refresh_token; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.refresh_token (
    id bigint NOT NULL,
    expiry_date timestamp without time zone NOT NULL,
    token character varying(255) NOT NULL,
    user_id bigint
);


ALTER TABLE public.refresh_token OWNER TO badreddine;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(60)
);


ALTER TABLE public.roles OWNER TO badreddine;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: badreddine
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO badreddine;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: badreddine
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: rule; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.rule (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by bigint,
    updated_by bigint,
    rule_order integer NOT NULL,
    rule_then character varying(255),
    rule_when character varying(255),
    type character varying(255)
);


ALTER TABLE public.rule OWNER TO badreddine;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: badreddine
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO badreddine;

--
-- Name: app_users id; Type: DEFAULT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.app_users ALTER COLUMN id SET DEFAULT nextval('public.app_users_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: order_items id; Type: DEFAULT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.order_items ALTER COLUMN id SET DEFAULT nextval('public.order_items_id_seq'::regclass);


--
-- Name: product_image_files id; Type: DEFAULT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_image_files ALTER COLUMN id SET DEFAULT nextval('public.product_image_files_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: app_users app_users_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT app_users_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: order_items order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: product_image_files product_image_files_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_image_files
    ADD CONSTRAINT product_image_files_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product_rules product_rules_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_rules
    ADD CONSTRAINT product_rules_pkey PRIMARY KEY (product_id, rule_id);


--
-- Name: refresh_token refresh_token_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: rule rule_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.rule
    ADD CONSTRAINT rule_pkey PRIMARY KEY (id);


--
-- Name: app_users uk4vj92ux8a2eehds1mdvmks473; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT uk4vj92ux8a2eehds1mdvmks473 UNIQUE (email);


--
-- Name: roles uk_nb4h0p6txrmfc0xbrd1kglp9t; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t UNIQUE (name);


--
-- Name: refresh_token uk_r4k4edos30bx9neoq81mdvwph; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT uk_r4k4edos30bx9neoq81mdvwph UNIQUE (token);


--
-- Name: product_image_files ukajnvf9s1pihhgb9cmlbn44s4t; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_image_files
    ADD CONSTRAINT ukajnvf9s1pihhgb9cmlbn44s4t UNIQUE (file_name);


--
-- Name: order_items uklbeyvdnar9f23o2koglpmb6tw; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT uklbeyvdnar9f23o2koglpmb6tw UNIQUE (order_id, product_id);


--
-- Name: app_users ukspsnwr241e9k9c8p5xl4k45ih; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT ukspsnwr241e9k9c8p5xl4k45ih UNIQUE (username);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: user_roles fkaf154i5th4vvgbahf8b8pa688; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkaf154i5th4vvgbahf8b8pa688 FOREIGN KEY (user_id) REFERENCES public.app_users(id);


--
-- Name: order_items fkbioxgbv59vetrxe0ejfubep1w; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: user_roles fkh8ciramu9cc9q3qcqiv4ue8a6; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: refresh_token fkj8ly9xk6do6ch2qbuxsc73hgu; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT fkj8ly9xk6do6ch2qbuxsc73hgu FOREIGN KEY (user_id) REFERENCES public.app_users(id);


--
-- Name: order_items fklf6f9q956mt144wiv6p1yko16; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fklf6f9q956mt144wiv6p1yko16 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_image_files fkms7xx756fkmpwug0hn6sccquf; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_image_files
    ADD CONSTRAINT fkms7xx756fkmpwug0hn6sccquf FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product_rules fko25tlrtv4jjjoigi7afunnenh; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_rules
    ADD CONSTRAINT fko25tlrtv4jjjoigi7afunnenh FOREIGN KEY (rule_id) REFERENCES public.rule(id);


--
-- Name: product_rules fkpuoyf5u0as7j42cb67w89su9c; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product_rules
    ADD CONSTRAINT fkpuoyf5u0as7j42cb67w89su9c FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: product fks52yuebqhifoknbruwxqoq01a; Type: FK CONSTRAINT; Schema: public; Owner: badreddine
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fks52yuebqhifoknbruwxqoq01a FOREIGN KEY (created_by) REFERENCES public.app_users(id);


--
-- PostgreSQL database dump complete
--

