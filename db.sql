
--
-- Name: demands; Type: TABLE; Schema: public; Owner: sweater_user
--

CREATE TABLE public.demands (
    id integer NOT NULL,
    product_id integer NOT NULL,
    amount integer NOT NULL,
    price integer NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    CONSTRAINT demands_price_check CHECK ((price > 0))
);


ALTER TABLE public.demands OWNER TO sweater_user;

--
-- Name: demands_id_seq; Type: SEQUENCE; Schema: public; Owner: sweater_user
--

CREATE SEQUENCE public.demands_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.demands_id_seq OWNER TO sweater_user;

--
-- Name: demands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sweater_user
--

ALTER SEQUENCE public.demands_id_seq OWNED BY public.demands.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: sweater_user
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    amount integer NOT NULL
);


ALTER TABLE public.products OWNER TO sweater_user;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: sweater_user
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO sweater_user;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sweater_user
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: purchases; Type: TABLE; Schema: public; Owner: sweater_user
--

CREATE TABLE public.purchases (
    id integer NOT NULL,
    product_id integer NOT NULL,
    amount integer NOT NULL,
    price integer NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    CONSTRAINT purchases_price_check CHECK ((price > 0))
);


ALTER TABLE public.purchases OWNER TO sweater_user;

--
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: sweater_user
--

CREATE SEQUENCE public.purchases_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO sweater_user;

--
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sweater_user
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- Name: demands id; Type: DEFAULT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.demands ALTER COLUMN id SET DEFAULT nextval('public.demands_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- Data for Name: demands; Type: TABLE DATA; Schema: public; Owner: sweater_user
--

INSERT INTO demands VALUES
(6,	38,	8,	40000,	'2019-02-20'),
(7,	38,	6,	35000,	'2019-04-20'),
(8,	38,	5,	41000,	'2019-06-20'),
(9,	43,	10,	500,	'2019-12-30');


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: sweater_user
--

INSERT INTO products VALUES
(43,	'Spoon',	0),
(41,	'Phone',	0),
(42,	'Bread',	0),
(38,	'Car',	4);


--
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: sweater_user
--

INSERT INTO purchases VALUES
(10,	38,	10,	20000,	'2019-01-20'),
(11,	38,	5,	23000,	'2019-03-20'),
(12,	38,	8,	19000,	'2019-05-20'),
(15,	43,	5,	200,	'2019-01-10'),
(16,	43,	5,	200,	'2019-01-10');


--
-- Name: demands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sweater_user
--

SELECT pg_catalog.setval('public.demands_id_seq', 10, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sweater_user
--

SELECT pg_catalog.setval('public.products_id_seq', 53, true);


--
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sweater_user
--

SELECT pg_catalog.setval('public.purchases_id_seq', 18, true);


--
-- Name: demands demands_pk; Type: CONSTRAINT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.demands
    ADD CONSTRAINT demands_pk PRIMARY KEY (id);


--
-- Name: products products_pk; Type: CONSTRAINT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


--
-- Name: purchases purchases_pk; Type: CONSTRAINT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pk PRIMARY KEY (id);


--
-- Name: products_name_uindex; Type: INDEX; Schema: public; Owner: sweater_user
--

CREATE UNIQUE INDEX products_name_uindex ON public.products USING btree (upper((name)::text));


--
-- Name: demands demands_products_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.demands
    ADD CONSTRAINT demands_products_id_fk FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: purchases purchases_products_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: sweater_user
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_products_id_fk FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

