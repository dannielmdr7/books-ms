-- =============================================================
-- ACTUALIZACIÓN DE DATOS DE LIBROS EXISTENTES
-- Imágenes: Open Library Covers API (gratuito, sin registro)
-- Formato: https://covers.openlibrary.org/b/isbn/{ISBN}-L.jpg
-- =============================================================

UPDATE books SET
    price         = 35.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9780451524935-L.jpg',
    description   = 'Una novela distópica que describe un futuro totalitario donde el Gran Hermano controla cada aspecto de la vida de las personas. Winston Smith trabaja reescribiendo el pasado para el Partido, mientras lucha en secreto por su libertad y humanidad.',
    editorial     = 'Secker & Warburg',
    language      = 'Español',
    pages         = 328,
    edition       = 1,
    biography     = 'George Orwell (1903-1950), seudónimo de Eric Arthur Blair, fue un escritor y periodista británico. Conocido por sus obras críticas del totalitarismo, entre ellas Rebelión en la granja y 1984.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg'
WHERE title = '1984';

UPDATE books SET
    price         = 38.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9780307474278-L.jpg',
    description   = 'Un asesinato en el Louvre y pistas en los cuadros de Da Vinci llevan al profesor Robert Langdon a descubrir una conspiración religiosa que podría sacudir los cimientos del mundo occidental. Thriller de acción y misterio que mezcla historia y ficción.',
    editorial     = 'Doubleday',
    language      = 'Español',
    pages         = 656,
    edition       = 4,
    biography     = 'Dan Brown (1964) es un escritor estadounidense conocido por sus novelas de suspense. El código Da Vinci, publicado en 2003, se convirtió en uno de los libros más vendidos de la historia.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Dan_Brown_bookcover.jpg/220px-Dan_Brown_bookcover.jpg'
WHERE title = 'El código Da Vinci';

UPDATE books SET
    price         = 22.50,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9780156013987-L.jpg',
    description   = 'Un aviador que cae en el desierto del Sahara conoce a un pequeño príncipe venido de un asteroide lejano. A través de sus conversaciones, Antoine de Saint-Exupéry nos ofrece una reflexión poética sobre la amistad, el amor y el sentido de la vida.',
    editorial     = 'Gallimard',
    language      = 'Español',
    pages         = 96,
    edition       = 3,
    biography     = 'Antoine de Saint-Exupéry (1900-1944) fue un escritor y aviador francés. El Principito, publicado en 1943, es una de las obras más traducidas y vendidas de la historia de la literatura.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Antoine_de_Saint_Exup%C3%A9ry.jpg/220px-Antoine_de_Saint_Exup%C3%A9ry.jpg'
WHERE title = 'El Principito';

UPDATE books SET
    price         = 42.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9788408163435-L.jpg',
    description   = 'En la Barcelona de posguerra, el joven Daniel Sempere descubre en el Cementerio de los Libros Olvidados una misteriosa novela de Julián Carax. Su búsqueda sobre el autor lo arrastrará hacia un laberinto de secretos, amor y tragedia.',
    editorial     = 'Planeta',
    language      = 'Español',
    pages         = 544,
    edition       = 1,
    biography     = 'Carlos Ruiz Zafón (1964-2020) fue un escritor español, reconocido mundialmente por la tetralogía El cementerio de los libros olvidados. La sombra del viento es su obra más célebre.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Carlos_Ruiz_Zaf%C3%B3n.jpg/220px-Carlos_Ruiz_Zaf%C3%B3n.jpg'
WHERE title = 'La sombra del viento';

UPDATE books SET
    price         = 30.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9781451673319-L.jpg',
    description   = 'En un futuro donde los libros están prohibidos y los bomberos los queman, Guy Montag es un bombero que comienza a cuestionar su rol en la sociedad. Una profética distopía sobre la censura, la ignorancia y el poder de la cultura escrita.',
    editorial     = 'Ballantine Books',
    language      = 'Español',
    pages         = 256,
    edition       = 2,
    biography     = 'Ray Bradbury (1920-2012) fue un escritor estadounidense de ciencia ficción y fantasía. Fahrenheit 451 (1953) es su obra más influyente y un clásico indiscutible del género distópico.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Ray_Bradbury_%281975%29_-cropped.jpg/220px-Ray_Bradbury_%281975%29_-cropped.jpg'
WHERE title = 'Fahrenheit 451';

UPDATE books SET
    price         = 36.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9788437604572-L.jpg',
    description   = 'Rayuela es una novela experimental que puede leerse de múltiples maneras. Sigue a Horacio Oliveira, un argentino en París obsesionado con encontrar el sentido de la existencia, en una búsqueda intelectual y amorosa que desafía las convenciones literarias.',
    editorial     = 'Sudamericana',
    language      = 'Español',
    pages         = 736,
    edition       = 1,
    biography     = 'Julio Cortázar (1914-1984) fue un escritor argentino, uno de los maestros del cuento latinoamericano y figura clave del Boom. Rayuela, publicada en 1963, es considerada su obra maestra.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg'
WHERE title = 'Rayuela';

UPDATE books SET
    price         = 45.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9780374528379-L.jpg',
    description   = 'A través del proceso del parricidio que enfrenta a los hermanos Karamazov, Dostoievski explora las grandes preguntas de la existencia humana: la fe, la moral, el libre albedrío y la responsabilidad. Una cumbre de la literatura universal.',
    editorial     = 'The Russian Messenger',
    language      = 'Español',
    pages         = 1024,
    edition       = 2,
    biography     = 'Fiódor Dostoievski (1821-1881) fue un escritor ruso cuyas novelas exploran la psicología humana y las cuestiones filosóficas y espirituales. Es considerado uno de los escritores más grandes de la historia.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg/220px-Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg'
WHERE title = 'Los hermanos karamazov';

UPDATE books SET
    price         = 32.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9780375702242-L.jpg',
    description   = 'El príncipe Mishkin regresa a San Petersburgo tras años de tratamiento en Suiza. Su bondad extrema y su epilepsia lo convierten en un extraño en la sociedad rusa, mientras se ve envuelto entre el amor de dos mujeres y las intrigas del mundo burgués.',
    editorial     = 'Russki Vestnik',
    language      = 'Español',
    pages         = 688,
    edition       = 1,
    biography     = 'Fiódor Dostoievski (1821-1881) fue un escritor ruso cuyas novelas exploran la psicología humana y las cuestiones filosóficas y espirituales. Es considerado uno de los escritores más grandes de la historia.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg/220px-Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg'
WHERE title = 'El idiota';

UPDATE books SET
    price         = 32.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9788478884452-L.jpg',
    description   = 'Harry Potter descubre el día de su undécimo cumpleaños que es un mago. Ingresa a Hogwarts, la escuela de magia, donde hará amigos entrañables y enfrentará al oscuro mago Voldemort. El inicio de la saga más vendida de la historia.',
    editorial     = 'Salamandra',
    language      = 'Español',
    pages         = 264,
    edition       = 1,
    biography     = 'J.K. Rowling (1965) es una escritora británica conocida mundialmente por la saga Harry Potter, compuesta por siete novelas que han vendido más de 500 millones de ejemplares en todo el mundo.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg'
WHERE title = 'Harry Potter y la piedra filosofal';

UPDATE books SET
    price         = 28.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9780307389732-L.jpg',
    description   = 'Santiago Nasar muere acuchillado a la puerta de su casa. Todos en el pueblo sabían que lo iban a matar, pero nadie hizo nada para evitarlo. García Márquez reconstruye el crimen a través de testimonios fragmentados en esta magistral novela corta.',
    editorial     = 'La Oveja Negra',
    language      = 'Español',
    pages         = 192,
    edition       = 1,
    biography     = 'Gabriel García Márquez (1927-2014) fue un escritor colombiano, Premio Nobel de Literatura en 1982. Es el máximo exponente del realismo mágico y autor de Cien años de soledad.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg'
WHERE title = 'Crónica de una muerte anunciada';

UPDATE books SET
    price         = 48.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9788467035445-L.jpg',
    description   = 'Alonso Quijano, un hidalgo manchego que ha perdido la razón leyendo libros de caballerías, se convierte en Don Quijote de la Mancha y sale al mundo a vivir aventuras junto a su fiel escudero Sancho Panza. La novela más influyente de la literatura occidental.',
    editorial     = 'Francisco de Robles',
    language      = 'Español',
    pages         = 1072,
    edition       = 5,
    biography     = 'Miguel de Cervantes (1547-1616) fue un soldado, novelista, poeta y dramaturgo español. Don Quijote de la Mancha, publicada entre 1605 y 1615, es considerada la primera novela moderna de la literatura universal.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Miguel_de_Cervantes_J%C3%A1uregui.jpg/220px-Miguel_de_Cervantes_J%C3%A1uregui.jpg'
WHERE title = 'Don Quijote de la Mancha';

UPDATE books SET
    price         = 20.00,
    image_url     = 'https://covers.openlibrary.org/b/isbn/9788491050407-L.jpg',
    description   = 'En las frías noches de San Petersburgo, el joven narrador solitario conoce a Nastenka, una muchacha que espera el regreso de su amado. Durante cuatro noches blancas comparten sus sueños y sus anhelos en una conmovedora historia de amor y soledad.',
    editorial     = 'Otechestvennye Zapiski',
    language      = 'Español',
    pages         = 112,
    edition       = 2,
    biography     = 'Fiódor Dostoievski (1821-1881) fue un escritor ruso cuyas novelas exploran la psicología humana y las cuestiones filosóficas y espirituales. Es considerado uno de los escritores más grandes de la historia.',
    author_photo_url = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg/220px-Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%84%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg'
WHERE title LIKE '%Noches blancas%';

-- =============================================================
-- CATEGORÍAS INICIALES (se insertan solo si no existen)
-- =============================================================

-- Categorías iniciales (se insertan solo si no existen)
INSERT INTO categories (name, description, slug)
SELECT 'Ficción', 'Novelas y obras de ficción literaria', 'ficcion'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'ficcion');

INSERT INTO categories (name, description, slug)
SELECT 'Ciencia Ficción', 'Obras de ciencia ficción y fantasía', 'ciencia-ficcion'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'ciencia-ficcion');

INSERT INTO categories (name, description, slug)
SELECT 'Romance', 'Novelas románticas y de amor', 'romance'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'romance');

INSERT INTO categories (name, description, slug)
SELECT 'Misterio', 'Thrillers y novelas de misterio', 'misterio'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'misterio');

INSERT INTO categories (name, description, slug)
SELECT 'Histórica', 'Novelas históricas y biografías', 'historica'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'historica');

INSERT INTO categories (name, description, slug)
SELECT 'Literatura Clásica', 'Obras clásicas de la literatura universal', 'literatura-clasica'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'literatura-clasica');

INSERT INTO categories (name, description, slug)
SELECT 'Juvenil', 'Libros dirigidos a jóvenes y adolescentes', 'juvenil'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'juvenil');

INSERT INTO categories (name, description, slug)
SELECT 'No Ficción', 'Libros de no ficción, ensayos y documentales', 'no-ficcion'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'no-ficcion');

INSERT INTO categories (name, description, slug)
SELECT 'Fantasía', 'Mundos imaginarios, magia y aventura épica', 'fantasia'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'fantasia');

INSERT INTO categories (name, description, slug)
SELECT 'Realismo Mágico', 'Literatura donde lo fantástico convive con lo cotidiano', 'realismo-magico'
WHERE NOT EXISTS (SELECT 1 FROM categories WHERE slug = 'realismo-magico');
