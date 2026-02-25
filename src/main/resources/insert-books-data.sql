-- =============================================================
-- SCRIPT: Insertar 20 libros nuevos en la BD
-- Imágenes: Open Library Covers API (https://covers.openlibrary.org)
-- Fotos autores: Wikimedia Commons (dominio público)
-- Columnas JPA (snake_case): id auto-generado por la BD
-- Ejecútalo directamente en PostgreSQL contra la BD "books"
-- =============================================================

BEGIN;

-- 1. Cien años de soledad
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Cien años de soledad',
    'Gabriel García Márquez',
    '1967-05-30',
    'Realismo Mágico',
    9780307474728,
    5,
    true,
    150,
    52.20,
    'https://covers.openlibrary.org/b/isbn/9780307474728-L.jpg',
    'La historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo. Una obra que define el realismo mágico y ha vendido más de 50 millones de ejemplares en todo el mundo.',
    'Editorial Sudamericana',
    'Español',
    471,
    1,
    'Gabriel García Márquez (1927-2014) fue un escritor colombiano, Premio Nobel de Literatura en 1982. Es el máximo exponente del realismo mágico latinoamericano y autor de obras como El amor en los tiempos del cólera y Crónica de una muerte anunciada.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg'
);

-- 2. El amor en los tiempos del cólera
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El amor en los tiempos del cólera',
    'Gabriel García Márquez',
    '1985-11-01',
    'Romance',
    9780307387264,
    5,
    true,
    120,
    48.50,
    'https://covers.openlibrary.org/b/isbn/9780307387264-L.jpg',
    'Una historia de amor que se desarrolla a lo largo de más de cincuenta años, desde la juventud hasta la vejez. Florentino Ariza espera durante décadas recuperar el amor de Fermina Daza en una novela sobre la paciencia, la pasión y el tiempo.',
    'Editorial Oveja Negra',
    'Español',
    464,
    2,
    'Gabriel García Márquez (1927-2014) fue un escritor colombiano, Premio Nobel de Literatura en 1982. Es el máximo exponente del realismo mágico latinoamericano y autor de obras como Cien años de soledad y Crónica de una muerte anunciada.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg'
);

-- 3. La metamorfosis
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'La metamorfosis',
    'Franz Kafka',
    '1915-10-15',
    'Ficción',
    9780553213690,
    5,
    true,
    80,
    18.90,
    'https://covers.openlibrary.org/b/isbn/9780553213690-L.jpg',
    'Gregor Samsa amanece convertido en un insecto gigantesco. A través de esta transformación, Kafka explora la alienación, la identidad y las relaciones familiares en una de las obras más influyentes de la literatura del siglo XX.',
    'Kurt Wolff Verlag',
    'Español',
    128,
    1,
    'Franz Kafka (1883-1924) fue un escritor checo de lengua alemana, considerado uno de los escritores más influyentes del siglo XX. Sus obras, publicadas en su mayoría de forma póstuma, exploran temas como la alienación, la burocracia y la culpa.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Franz_Kafka%2C_1923.jpg/220px-Franz_Kafka%2C_1923.jpg'
);

-- 4. Crimen y castigo
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Crimen y castigo',
    'Fiódor Dostoievski',
    '1866-01-01',
    'Literatura Clásica',
    9780486454139,
    5,
    true,
    95,
    38.00,
    'https://covers.openlibrary.org/b/isbn/9780486454139-L.jpg',
    'Rodion Raskolnikov, un estudiante en San Petersburgo, comete un asesinato creyendo que su superioridad intelectual lo justifica. La novela narra su lucha psicológica con la culpa y la redención en un análisis profundo de la moral humana.',
    'Russki Vestnik',
    'Español',
    671,
    2,
    'Fiódor Dostoievski (1821-1881) fue un novelista ruso cuyas obras exploran la psicología humana y las grandes cuestiones filosóficas y espirituales. Es autor también de Los hermanos Karamazov, El idiota y El jugador.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Dostoevsky_1872.jpg/220px-Dostoevsky_1872.jpg'
);

-- 5. Anna Karenina
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Anna Karenina',
    'León Tolstói',
    '1878-01-01',
    'Literatura Clásica',
    9780143035008,
    5,
    true,
    70,
    44.00,
    'https://covers.openlibrary.org/b/isbn/9780143035008-L.jpg',
    'Anna Karenina, una aristócrata casada, se enamora apasionadamente del conde Vronsky y abandona su familia por él. Tolstói entrelaza su tragedia con la historia de Levin, un terrateniente que busca el sentido de la vida en el campo ruso.',
    'Russki Vestnik',
    'Español',
    864,
    3,
    'León Tolstói (1828-1910) fue un escritor ruso considerado uno de los más grandes novelistas de la historia. Sus obras más célebres son Guerra y paz y Anna Karenina, monumentos de la literatura universal.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/220px-L.N.Tolstoy_Prokudin-Gorsky.jpg'
);

-- 6. El gran Gatsby
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El gran Gatsby',
    'F. Scott Fitzgerald',
    '1925-04-10',
    'Ficción',
    9780743273565,
    5,
    true,
    110,
    29.90,
    'https://covers.openlibrary.org/b/isbn/9780743273565-L.jpg',
    'Jay Gatsby, un misterioso millonario que organiza lujosas fiestas en su mansión de Long Island, busca reconquistar a Daisy Buchanan, el amor de su juventud. Una crítica mordaz del sueño americano ambientada en los años locos de los años veinte.',
    'Charles Scribner Sons',
    'Español',
    180,
    1,
    'F. Scott Fitzgerald (1896-1940) fue un escritor estadounidense, figura destacada de la Era del Jazz. El gran Gatsby (1925) es considerada su obra maestra y uno de los grandes clásicos de la literatura norteamericana.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/F_Scott_Fitzgerald_1921.jpg/220px-F_Scott_Fitzgerald_1921.jpg'
);

-- 7. Matar a un ruiseñor
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Matar a un ruiseñor',
    'Harper Lee',
    '1960-07-11',
    'Ficción',
    9780061935466,
    5,
    true,
    100,
    33.00,
    'https://covers.openlibrary.org/b/isbn/9780061935466-L.jpg',
    'Scout Finch, una niña de Alabama, observa cómo su padre, el abogado Atticus Finch, defiende a un hombre negro acusado injustamente. Una novela sobre la inocencia, la justicia y el racismo en el sur de Estados Unidos durante la Gran Depresión.',
    'J.B. Lippincott',
    'Español',
    336,
    1,
    'Harper Lee (1926-2016) fue una escritora estadounidense. Matar a un ruiseñor, su primera novela publicada en 1960, ganó el Premio Pulitzer en 1961 y se convirtió en un símbolo de la lucha por los derechos civiles.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Harper_Lee_-_foto_1.JPG/220px-Harper_Lee_-_foto_1.JPG'
);

-- 8. El nombre de la rosa
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El nombre de la rosa',
    'Umberto Eco',
    '1980-01-01',
    'Misterio',
    9780156001311,
    5,
    true,
    85,
    40.00,
    'https://covers.openlibrary.org/b/isbn/9780156001311-L.jpg',
    'El fraile franciscano Guillermo de Baskerville y su novicio Adso de Melk investigan una serie de misteriosos asesinatos en una abadía medieval italiana. Un thriller filosófico que mezcla misterio, semiótica y teología medieval.',
    'Bompiani',
    'Español',
    502,
    1,
    'Umberto Eco (1932-2016) fue un escritor, filósofo y semiólogo italiano. Conocido tanto por sus ensayos académicos como por sus novelas, El nombre de la rosa (1980) fue su debut novelístico y un fenómeno editorial mundial.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Umberto_Eco_-_Festival_Economia_2013.jpg/220px-Umberto_Eco_-_Festival_Economia_2013.jpg'
);

-- 9. Dune
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Dune',
    'Frank Herbert',
    '1965-08-01',
    'Ciencia Ficción',
    9780441013593,
    5,
    true,
    130,
    42.00,
    'https://covers.openlibrary.org/b/isbn/9780441013593-L.jpg',
    'En el planeta desértico Arrakis, el joven Paul Atreides se convierte en líder de los Fremen y en el mesías de un mundo que controla la especia más valiosa del universo. Una saga épica de política, ecología y religión.',
    'Chilton Books',
    'Español',
    688,
    1,
    'Frank Herbert (1920-1986) fue un escritor estadounidense de ciencia ficción. Dune (1965) es considerada una de las mejores novelas de ciencia ficción jamás escritas, ganadora del Premio Hugo y el Premio Nébula.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Frank_Herbert.jpg/220px-Frank_Herbert.jpg'
);

-- 10. El hobbit
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El hobbit',
    'J.R.R. Tolkien',
    '1937-09-21',
    'Fantasía',
    9780547928227,
    5,
    true,
    200,
    34.00,
    'https://covers.openlibrary.org/b/isbn/9780547928227-L.jpg',
    'Bilbo Bolsón, un hobbit tranquilo que vive en su agujero del suelo, es arrastrado por el mago Gandalf y una compañía de enanos en una aventura para recuperar el tesoro custodiado por el dragón Smaug en la Montaña Solitaria.',
    'Allen and Unwin',
    'Español',
    310,
    1,
    'J.R.R. Tolkien (1892-1973) fue un escritor, poeta, filólogo y profesor universitario británico. Creador de la Tierra Media, es autor de El hobbit y El señor de los anillos, pilares de la literatura fantástica moderna.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/J._R._R._Tolkien%2C_ca._1925.jpg/220px-J._R._R._Tolkien%2C_ca._1925.jpg'
);

-- 11. Fundación
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Fundación',
    'Isaac Asimov',
    '1951-01-01',
    'Ciencia Ficción',
    9780553293357,
    5,
    true,
    115,
    35.00,
    'https://covers.openlibrary.org/b/isbn/9780553293357-L.jpg',
    'El matemático Hari Seldon predice la caída del Imperio Galáctico usando la psicohistoria y funda la Fundación para preservar el conocimiento humano. El inicio de una saga épica sobre el destino de la humanidad a lo largo de miles de años.',
    'Gnome Press',
    'Español',
    255,
    1,
    'Isaac Asimov (1920-1992) fue un escritor y bioquímico estadounidense, uno de los grandes maestros de la ciencia ficción. Autor prolífico de más de 500 obras, es conocido por la saga Fundación y las Leyes de la Robótica.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Isaac.Asimov01.jpg/220px-Isaac.Asimov01.jpg'
);

-- 12. El extranjero
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El extranjero',
    'Albert Camus',
    '1942-06-01',
    'Ficción',
    9780679720201,
    5,
    true,
    90,
    22.00,
    'https://covers.openlibrary.org/b/isbn/9780679720201-L.jpg',
    'Meursault, un funcionario en Argelia, mata a un árabe en la playa y luego enfrenta el juicio de la sociedad. Camus presenta un retrato del hombre absurdo, indiferente a las convenciones sociales, en esta novela clave del existencialismo.',
    'Gallimard',
    'Español',
    159,
    1,
    'Albert Camus (1913-1960) fue un escritor y filósofo argelino-francés, Premio Nobel de Literatura en 1957. Figura central del existencialismo, desarrolló la filosofía del absurdo en obras como El extranjero, El mito de Sísifo y La peste.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Albert_Camus%2C_gagnant_de_prix_Nobel%2C_portrait_en_buste%2C_pos%C3%A9_au_bureau%2C_faisant_face_%C3%A0_gauche%2C_cigarette_de_tabagisme.jpg/220px-Albert_Camus%2C_gagnant_de_prix_Nobel%2C_portrait_en_buste%2C_pos%C3%A9_au_bureau%2C_faisant_face_%C3%A0_gauche%2C_cigarette_de_tabagisme.jpg'
);

-- 13. El conde de Montecristo
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El conde de Montecristo',
    'Alexandre Dumas',
    '1844-08-28',
    'Histórica',
    9780140449266,
    5,
    true,
    75,
    55.00,
    'https://covers.openlibrary.org/b/isbn/9780140449266-L.jpg',
    'Edmond Dantès, un joven marinero acusado injustamente de traición, escapa del castillo de If tras catorce años de prisión. Enriquecido con un fabuloso tesoro, regresa como el misterioso Conde de Montecristo para vengarse de sus enemigos.',
    'Journal des Débats',
    'Español',
    1276,
    4,
    'Alexandre Dumas (1802-1870) fue un escritor francés, uno de los más populares de la literatura francesa y universal. Autor de más de 100 novelas, entre ellas Los tres mosqueteros y El conde de Montecristo, obras que han sido adaptadas innumerables veces.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Alexandre_Dumas.jpg/220px-Alexandre_Dumas.jpg'
);

-- 14. Los miserables
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Los miserables',
    'Victor Hugo',
    '1862-04-03',
    'Histórica',
    9780451419439,
    5,
    true,
    65,
    58.00,
    'https://covers.openlibrary.org/b/isbn/9780451419439-L.jpg',
    'Jean Valjean, un ex presidiario que busca redención, es perseguido implacablemente por el inspector Javert. A través de sus vidas entrelazadas, Hugo narra la miseria y la injusticia del París del siglo XIX en una de las novelas más importantes de la literatura francesa.',
    'A. Lacroix, Verboeckhoven et Cie.',
    'Español',
    1488,
    2,
    'Victor Hugo (1802-1885) fue un poeta, novelista y dramaturgo francés. Figura central del romanticismo francés, es autor de Los miserables y El jorobado de Notre Dame, obras que han trascendido el tiempo y la cultura.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Victor_Hugo-Hachette.jpg/220px-Victor_Hugo-Hachette.jpg'
);

-- 15. Orgullo y Prejuicio
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Orgullo y Prejuicio',
    'Jane Austen',
    '1813-01-28',
    'Romance',
    9780141439518,
    5,
    true,
    140,
    28.00,
    'https://covers.openlibrary.org/b/isbn/9780141439518-L.jpg',
    'Elizabeth Bennet y el orgulloso señor Darcy superan sus prejuicios mutuos para encontrar el amor verdadero. Con ironía y agudeza social, Austen retrata la sociedad inglesa de principios del siglo XIX y los dilemas del matrimonio y la independencia femenina.',
    'T. Egerton',
    'Español',
    432,
    3,
    'Jane Austen (1775-1817) fue una novelista británica cuya obra critica agudamente las costumbres de la sociedad inglesa de su época. Sus novelas más célebres son Orgullo y Prejuicio, Sentido y Sensibilidad y Emma.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/CassandraAusten-JaneAusten%28c.1810%29_hires.jpg/220px-CassandraAusten-JaneAusten%28c.1810%29_hires.jpg'
);

-- 16. Frankenstein
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Frankenstein',
    'Mary Shelley',
    '1818-01-01',
    'Ciencia Ficción',
    9780141439471,
    4,
    true,
    88,
    24.00,
    'https://covers.openlibrary.org/b/isbn/9780141439471-L.jpg',
    'El joven científico Víctor Frankenstein crea un ser a partir de cuerpos sin vida. La criatura, rechazada por todos, busca venganza contra su creador. Considerada la primera novela de ciencia ficción, explora los límites de la ciencia y la responsabilidad del creador.',
    'Lackington, Hughes, Harding, Mavor and Jones',
    'Español',
    280,
    2,
    'Mary Shelley (1797-1851) fue una novelista, cuentista y dramaturga británica. Con solo 18 años concibió Frankenstein (1818), considerada la primera novela de ciencia ficción moderna y una obra pionera del género de terror.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mary_Wollstonecraft_Shelley_Rothwell.jpg/220px-Mary_Wollstonecraft_Shelley_Rothwell.jpg'
);

-- 17. El retrato de Dorian Gray
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El retrato de Dorian Gray',
    'Oscar Wilde',
    '1890-07-01',
    'Ficción',
    9780141439570,
    5,
    true,
    92,
    26.50,
    'https://covers.openlibrary.org/b/isbn/9780141439570-L.jpg',
    'Dorian Gray, un joven de extraordinaria belleza, desea que un retrato suyo envejezca en su lugar mientras él permanece joven. Su pacto le permite vivir una vida de placeres y vicios, mientras la corrupción de su alma se refleja solo en el cuadro.',
    'Ward, Lock and Company',
    'Español',
    254,
    1,
    'Oscar Wilde (1854-1900) fue un escritor, poeta y dramaturgo irlandés. Figura del esteticismo victoriano, es conocido por obras como El retrato de Dorian Gray, La importancia de llamarse Ernesto y El abanico de Lady Windermere.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Oscar_Wilde_Sarony.jpg/220px-Oscar_Wilde_Sarony.jpg'
);

-- 18. Pedro Páramo
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Pedro Páramo',
    'Juan Rulfo',
    '1955-03-19',
    'Realismo Mágico',
    9780802133939,
    5,
    true,
    60,
    20.00,
    'https://covers.openlibrary.org/b/isbn/9780802133939-L.jpg',
    'Juan Preciado viaja al pueblo de Comala buscando a su padre Pedro Páramo, solo para encontrar un lugar habitado por muertos y susurros. Una novela corta pero fundamental que influyó profundamente en toda la literatura latinoamericana posterior.',
    'Fondo de Cultura Económica',
    'Español',
    124,
    1,
    'Juan Rulfo (1917-1986) fue un escritor, guionista y fotógrafo mexicano. A pesar de haber publicado solo dos libros, Pedro Páramo y El llano en llamas, es reconocido como uno de los escritores más influyentes de la literatura latinoamericana.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Juan_Rulfo.jpg/220px-Juan_Rulfo.jpg'
);

-- 19. Siddhartha
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'Siddhartha',
    'Hermann Hesse',
    '1922-01-01',
    'Ficción',
    9780553208849,
    4,
    true,
    105,
    21.00,
    'https://covers.openlibrary.org/b/isbn/9780553208849-L.jpg',
    'Siddhartha, un joven de la India antigua, abandona su vida privilegiada en busca de la iluminación espiritual. Su viaje lo lleva a través del ascetismo, el amor y los negocios hasta encontrar su propio camino hacia la sabiduría en las orillas del río.',
    'S. Fischer Verlag',
    'Español',
    152,
    1,
    'Hermann Hesse (1877-1962) fue un escritor, poeta y pintor alemán nacionalizado suizo, Premio Nobel de Literatura en 1946. Sus novelas como Siddhartha, El lobo estepario y El juego de los abalorios exploran la búsqueda espiritual y el autoconocimiento.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hermann_Hesse.jpg/220px-Hermann_Hesse.jpg'
);

-- 20. El alquimista
INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)
VALUES (
    'El alquimista',
    'Paulo Coelho',
    '1988-01-01',
    'Ficción',
    9780062315007,
    4,
    true,
    180,
    27.00,
    'https://covers.openlibrary.org/b/isbn/9780062315007-L.jpg',
    'Santiago, un joven pastor andaluz, emprende un viaje hacia las pirámides de Egipto siguiendo un sueño recurrente. A lo largo de su aventura descubre que la clave de la felicidad está en escuchar el corazón y perseguir la propia leyenda personal.',
    'HarperCollins',
    'Español',
    208,
    1,
    'Paulo Coelho (1947) es un escritor brasileño, uno de los autores más vendidos de la historia con más de 350 millones de libros vendidos en más de 170 países. El alquimista, publicado en 1988, es su obra más célebre y ha sido traducida a 80 idiomas.',
    'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Paulo_Coelho_2013.jpg/220px-Paulo_Coelho_2013.jpg'
);

COMMIT;

-- =============================================================
-- Verificar resultado: muestra todos los libros insertados
-- =============================================================
SELECT
    id,
    title,
    author,
    category,
    price,
    current_stock,
    CASE WHEN image_url IS NOT NULL THEN 'SI' ELSE 'NO' END AS tiene_imagen,
    CASE WHEN description IS NOT NULL THEN 'SI' ELSE 'NO' END AS tiene_descripcion
FROM books
ORDER BY id DESC
LIMIT 25;
