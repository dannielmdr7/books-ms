DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10,2),
    image_url TEXT,
    description TEXT,
    editorial VARCHAR(255),
    language VARCHAR(50),
    pages INTEGER,
    edition INTEGER,
    biography TEXT,
    author_photo_url TEXT
);


INSERT INTO books (
    title, price, image_url, description, editorial, 
    language, pages, edition, biography, author_photo_url
) VALUES 
('1984', 35.00, 'https://covers.openlibrary.org/b/isbn/9780451524935-L.jpg', 'Una novela distópica que describe un futuro totalitario...', 'Secker & Warburg', 'Español', 328, 1, 'George Orwell (1903-1950)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg'),

('El código Da Vinci', 38.00, 'https://covers.openlibrary.org/b/isbn/9780307474278-L.jpg', 'Un asesinato en el Louvre y pistas en los cuadros...', 'Doubleday', 'Español', 656, 4, 'Dan Brown (1964)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Dan_Brown_bookcover.jpg/220px-Dan_Brown_bookcover.jpg'),

('El Principito', 22.50, 'https://covers.openlibrary.org/b/isbn/9780156013987-L.jpg', 'Un aviador que cae en el desierto del Sahara...', 'Gallimard', 'Español', 96, 3, 'Antoine de Saint-Exupéry (1900-1944)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Antoine_de_Saint_Exup%C3%A9ry.jpg/220px-Antoine_de_Saint_Exup%C3%A9ry.jpg'),

('La sombra del viento', 42.00, 'https://covers.openlibrary.org/b/isbn/9788408163435-L.jpg', 'En la Barcelona de posguerra...', 'Planeta', 'Español', 544, 1, 'Carlos Ruiz Zafón (1964-2020)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Carlos_Ruiz_Zaf%C3%B3n.jpg/220px-Carlos_Ruiz_Zaf%C3%B3n.jpg'),

('Fahrenheit 451', 30.00, 'https://covers.openlibrary.org/b/isbn/9781451673319-L.jpg', 'En un futuro donde los libros están prohibidos...', 'Ballantine Books', 'Español', 256, 2, 'Ray Bradbury (1920-2012)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Ray_Bradbury_%281975%29_-cropped.jpg/220px-Ray_Bradbury_%281975%29_-cropped.jpg'),

('Rayuela', 36.00, 'https://covers.openlibrary.org/b/isbn/9788437604572-L.jpg', 'Rayuela es una novela experimental...', 'Sudamericana', 'Español', 736, 1, 'Julio Cortázar (1914-1984)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg'),

('Los hermanos Karamazov', 45.00, 'https://covers.openlibrary.org/b/isbn/9780374528379-L.jpg', 'A través del proceso del parricidio...', 'The Russian Messenger', 'Español', 1024, 2, 'Fiódor Dostoievski (1821-1881)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg/220px-Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg'),

('El idiota', 32.00, 'https://covers.openlibrary.org/b/isbn/9780375702242-L.jpg', 'El príncipe Mishkin regresa a San Petersburgo...', 'Russki Vestnik', 'Español', 688, 1, 'Fiódor Dostoievski (1821-1881)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg/220px-Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%84%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg'),

('Harry Potter y la piedra filosofal', 32.00, 'https://covers.openlibrary.org/b/isbn/9788478884452-L.jpg', 'Harry Potter descubre el día de su undécimo cumpleaños...', 'Salamandra', 'Español', 264, 1, 'J.K. Rowling (1965)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg'),

('Crónica de una muerte anunciada', 28.00, 'https://covers.openlibrary.org/b/isbn/9780307389732-L.jpg', 'Santiago Nasar muere acuchillado...', 'La Oveja Negra', 'Español', 192, 1, 'Gabriel García Márquez (1927-2014)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg'),

('Don Quijote de la Mancha', 48.00, 'https://covers.openlibrary.org/b/isbn/9788467035445-L.jpg', 'Alonso Quijano, un hidalgo manchego...', 'Francisco de Robles', 'Español', 1072, 5, 'Miguel de Cervantes (1547-1616)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Miguel_de_Cervantes_J%C3%A1uregui.jpg/220px-Miguel_de_Cervantes_J%C3%A1uregui.jpg'),

('Noches blancas', 20.00, 'https://covers.openlibrary.org/b/isbn/9788491050407-L.jpg', 'En las frías noches de San Petersburgo...', 'Otechestvennye Zapiski', 'Español', 112, 2, 'Fiódor Dostoievski (1821-1881)...', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%82_%D0%A4.%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg/220px-Vasily_Perov_-_%D0%9F%D0%BE%D1%80%D1%82%D1%80%D0%B5%D1%84%D0%9C.%D0%94%D0%BE%D1%81%D1%82%D0%BE%D0%B5%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_-_Google_Art_Project.jpg');