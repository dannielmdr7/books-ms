#!/usr/bin/env python3
"""
Genera books-2000.sql con 2000 libros sin campos nulos.
Ejecutar: python generate_books_sql.py
Límites BD: image_url/author_photo_url/title/author/category/editorial ≤255, language ≤50
"""
import os
import random
import sys
from datetime import datetime, timedelta

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))

# Límites de PostgreSQL (VARCHAR por defecto en JPA)
MAX_VARCHAR = 255
MAX_LANGUAGE = 50

# URL corta de respaldo si alguna excede el límite
FALLBACK_AUTHOR_PHOTO = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg"


def escape_sql(s: str) -> str:
    """Escapa comillas simples para SQL."""
    return s.replace("'", "''")


def truncate(s: str, max_len: int) -> str:
    """Trunca string a max_len caracteres."""
    if len(s) <= max_len:
        return s
    return s[: max_len - 3] + "..."


AUTHORS = [
    "Gabriel García Márquez", "Isabel Allende", "Mario Vargas Llosa", "Jorge Luis Borges",
    "Julio Cortázar", "Pablo Neruda", "Gabriela Mistral", "Octavio Paz", "Carlos Fuentes",
    "Laura Esquivel", "Juan Rulfo", "Elena Poniatowska", "Fernando del Paso", "Rosario Castellanos",
    "Alfonso Reyes", "Sor Juana Inés de la Cruz", "Rubén Darío", "José Martí", "Miguel Ángel Asturias",
    "Alejo Carpentier", "Juan Carlos Onetti", "Adolfo Bioy Casares", "Roberto Bolaño",
    "Clarice Lispector", "Machado de Assis", "Paulo Coelho", "Jorge Amado", "Graciliano Ramos",
    "Gabriel García Márquez", "Ernest Hemingway", "William Faulkner", "F. Scott Fitzgerald",
    "Jane Austen", "Charles Dickens", "Virginia Woolf", "George Orwell", "Aldous Huxley",
    "Albert Camus", "Jean-Paul Sartre", "Marcel Proust", "Franz Kafka", "Thomas Mann",
    "Herman Hesse", "Stefan Zweig", "Italo Calvino", "Umberto Eco", "Elena Ferrante",
    "Haruki Murakami", "Yukio Mishima", "Junichiro Tanizaki", "Gabriel García Márquez",
]

TITLE_BASES = [
    "La casa", "El jardín", "El tiempo", "La memoria", "Los sueños", "El viaje",
    "La sombra", "El eco", "Las cenizas", "El reflejo", "La luz", "El silencio",
    "El olvido", "La frontera", "El horizonte", "Las flores", "El río", "La montaña",
    "El mar", "La noche", "El amanecer", "La tormenta", "El viento", "La lluvia",
    "Cien años", "El amor", "La guerra", "La paz", "El misterio", "La verdad",
]

CATEGORIES = [
    "Ficción", "Romance", "Realismo Mágico", "Literatura Clásica", "Thriller",
    "Ciencia Ficción", "Fantasía", "Biografía", "Ensayo", "Poesía", "Infantil",
    "Histórica", "Policíaca", "Drama", "Comedia", "Aventuras", "Mitología",
]

EDITORIALES = [
    "Editorial Planeta", "Alfaguara", "Anagrama", "Tusquets", "Seix Barral",
    "Editorial Sudamericana", "Random House", "Penguin", "Debolsillo", "Austral",
    "Cátedra", "Espasa", "Siruela", "Acantilado", "Galaxia Gutenberg",
]

LANGUAGES = ["Español", "Español", "Español", "Inglés", "Portugués", "Francés", "Italiano"]

# 100 ISBNs reales (Open Library) - portadas que sí existen
BOOK_COVER_ISBNS = [
    9780451524935, 9780307474278, 9780156013987, 9788408163435, 9781451673319,
    9788437604572, 9780374528379, 9780375702242, 9788478884452, 9780307389732,
    9788467035445, 9788491050407, 9780307474728, 9780307387264, 9780553213690,
    9780486454139, 9780143035008, 9780743273565, 9780061935466, 9780156001311,
    9780441013593, 9780547928227, 9780553293357, 9780679720201, 9780140449266,
    9780451419439, 9780141439518, 9780141439471, 9780141439570, 9780802133939,
    9780553208849, 9780062315007, 9780141187396, 9780140283334, 9780385472572,
    9780316769488, 9780060883281, 9781400033416, 9780307387899, 9780307277670,
    9780385720950, 9780141182636, 9780141439600, 9780142000670, 9780060529702,
    9780553382568, 9780143105426, 9780060850527, 9780385333484, 9780141187161,
    9780571225407, 9780679735779, 9780143034902, 9780141439495, 9780141185264,
    9780060913089, 9780316042673, 9780385494248, 9780141439617, 9780141183703,
    9780140184780, 9780141187299, 9780141198967, 9780141182805, 9780141439587,
    9780140283297, 9780141439723, 9780141439730, 9780141439549, 9780141439686,
    9780143105952, 9780141187466, 9780141439861, 9780141439878, 9780141441147,
    9780141442465, 9780143105426, 9780060935463, 9780061120084, 9780060977494,
    9780062502179, 9780143034902, 9780141186315, 9780140188511, 9780140449266,
    9780141439600, 9780141439518, 9780140449266, 9780451524935, 9780307474278,
    9780156013987, 9788408163435, 9781451673319, 9788437604572, 9780374528379,
    9780375702242, 9788478884452, 9780307389732, 9788467035445, 9788491050407,
]

# 100 fotos de autores (Wikimedia Commons)
AUTHOR_PHOTO_URLS_RAW = [
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Dan_Brown_bookcover.jpg/220px-Dan_Brown_bookcover.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Antoine_de_Saint_Exup%C3%A9ry.jpg/220px-Antoine_de_Saint_Exup%C3%A9ry.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Carlos_Ruiz_Zaf%C3%B3n.jpg/220px-Carlos_Ruiz_Zaf%C3%B3n.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Ray_Bradbury_%281975%29_-cropped.jpg/220px-Ray_Bradbury_%281975%29_-cropped.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Miguel_de_Cervantes_J%C3%A1uregui.jpg/220px-Miguel_de_Cervantes_J%C3%A1uregui.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Dostoevsky_1872.jpg/220px-Dostoevsky_1872.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Franz_Kafka%2C_1923.jpg/220px-Franz_Kafka%2C_1923.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/220px-L.N.Tolstoy_Prokudin-Gorsky.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/F_Scott_Fitzgerald_1921.jpg/220px-F_Scott_Fitzgerald_1921.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Harper_Lee_-_foto_1.JPG/220px-Harper_Lee_-_foto_1.JPG",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Umberto_Eco_-_Festival_Economia_2013.jpg/220px-Umberto_Eco_-_Festival_Economia_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Frank_Herbert.jpg/220px-Frank_Herbert.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/J._R._R._Tolkien%2C_ca._1925.jpg/220px-J._R._R._Tolkien%2C_ca._1925.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Isaac.Asimov01.jpg/220px-Isaac.Asimov01.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Alexandre_Dumas.jpg/220px-Alexandre_Dumas.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Victor_Hugo-Hachette.jpg/220px-Victor_Hugo-Hachette.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/CassandraAusten-JaneAusten%28c.1810%29_hires.jpg/220px-CassandraAusten-JaneAusten%28c.1810%29_hires.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mary_Wollstonecraft_Shelley_Rothwell.jpg/220px-Mary_Wollstonecraft_Shelley_Rothwell.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Oscar_Wilde_Sarony.jpg/220px-Oscar_Wilde_Sarony.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Juan_Rulfo.jpg/220px-Juan_Rulfo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hermann_Hesse.jpg/220px-Hermann_Hesse.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Paulo_Coelho_2013.jpg/220px-Paulo_Coelho_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Albert_Camus_1945_%28cropped%29.jpg/220px-Albert_Camus_1945_%28cropped%29.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Franz_Kafka%2C_1923.jpg/220px-Franz_Kafka%2C_1923.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Dan_Brown_bookcover.jpg/220px-Dan_Brown_bookcover.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Antoine_de_Saint_Exup%C3%A9ry.jpg/220px-Antoine_de_Saint_Exup%C3%A9ry.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Carlos_Ruiz_Zaf%C3%B3n.jpg/220px-Carlos_Ruiz_Zaf%C3%B3n.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Ray_Bradbury_%281975%29_-cropped.jpg/220px-Ray_Bradbury_%281975%29_-cropped.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Miguel_de_Cervantes_J%C3%A1uregui.jpg/220px-Miguel_de_Cervantes_J%C3%A1uregui.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Dostoevsky_1872.jpg/220px-Dostoevsky_1872.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/220px-L.N.Tolstoy_Prokudin-Gorsky.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/F_Scott_Fitzgerald_1921.jpg/220px-F_Scott_Fitzgerald_1921.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Harper_Lee_-_foto_1.JPG/220px-Harper_Lee_-_foto_1.JPG",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Umberto_Eco_-_Festival_Economia_2013.jpg/220px-Umberto_Eco_-_Festival_Economia_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Frank_Herbert.jpg/220px-Frank_Herbert.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/J._R._R._Tolkien%2C_ca._1925.jpg/220px-J._R._R._Tolkien%2C_ca._1925.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Isaac.Asimov01.jpg/220px-Isaac.Asimov01.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Alexandre_Dumas.jpg/220px-Alexandre_Dumas.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Victor_Hugo-Hachette.jpg/220px-Victor_Hugo-Hachette.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/CassandraAusten-JaneAusten%28c.1810%29_hires.jpg/220px-CassandraAusten-JaneAusten%28c.1810%29_hires.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mary_Wollstonecraft_Shelley_Rothwell.jpg/220px-Mary_Wollstonecraft_Shelley_Rothwell.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Oscar_Wilde_Sarony.jpg/220px-Oscar_Wilde_Sarony.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Juan_Rulfo.jpg/220px-Juan_Rulfo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hermann_Hesse.jpg/220px-Hermann_Hesse.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Paulo_Coelho_2013.jpg/220px-Paulo_Coelho_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Alexandre_Dumas.jpg/220px-Alexandre_Dumas.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Franz_Kafka%2C_1923.jpg/220px-Franz_Kafka%2C_1923.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Miguel_de_Cervantes_J%C3%A1uregui.jpg/220px-Miguel_de_Cervantes_J%C3%A1uregui.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Dostoevsky_1872.jpg/220px-Dostoevsky_1872.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/220px-L.N.Tolstoy_Prokudin-Gorsky.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/F_Scott_Fitzgerald_1921.jpg/220px-F_Scott_Fitzgerald_1921.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Harper_Lee_-_foto_1.JPG/220px-Harper_Lee_-_foto_1.JPG",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Umberto_Eco_-_Festival_Economia_2013.jpg/220px-Umberto_Eco_-_Festival_Economia_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Frank_Herbert.jpg/220px-Frank_Herbert.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/J._R._R._Tolkien%2C_ca._1925.jpg/220px-J._R._R._Tolkien%2C_ca._1925.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Isaac.Asimov01.jpg/220px-Isaac.Asimov01.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Victor_Hugo-Hachette.jpg/220px-Victor_Hugo-Hachette.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/CassandraAusten-JaneAusten%28c.1810%29_hires.jpg/220px-CassandraAusten-JaneAusten%28c.1810%29_hires.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mary_Wollstonecraft_Shelley_Rothwell.jpg/220px-Mary_Wollstonecraft_Shelley_Rothwell.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Oscar_Wilde_Sarony.jpg/220px-Oscar_Wilde_Sarony.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Juan_Rulfo.jpg/220px-Juan_Rulfo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hermann_Hesse.jpg/220px-Hermann_Hesse.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Paulo_Coelho_2013.jpg/220px-Paulo_Coelho_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Albert_Camus_1945_%28cropped%29.jpg/220px-Albert_Camus_1945_%28cropped%29.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Dan_Brown_bookcover.jpg/220px-Dan_Brown_bookcover.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Antoine_de_Saint_Exup%C3%A9ry.jpg/220px-Antoine_de_Saint_Exup%C3%A9ry.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Carlos_Ruiz_Zaf%C3%B3n.jpg/220px-Carlos_Ruiz_Zaf%C3%B3n.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Ray_Bradbury_%281975%29_-cropped.jpg/220px-Ray_Bradbury_%281975%29_-cropped.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Miguel_de_Cervantes_J%C3%A1uregui.jpg/220px-Miguel_de_Cervantes_J%C3%A1uregui.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Dostoevsky_1872.jpg/220px-Dostoevsky_1872.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Franz_Kafka%2C_1923.jpg/220px-Franz_Kafka%2C_1923.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/220px-L.N.Tolstoy_Prokudin-Gorsky.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/F_Scott_Fitzgerald_1921.jpg/220px-F_Scott_Fitzgerald_1921.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Harper_Lee_-_foto_1.JPG/220px-Harper_Lee_-_foto_1.JPG",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Umberto_Eco_-_Festival_Economia_2013.jpg/220px-Umberto_Eco_-_Festival_Economia_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Frank_Herbert.jpg/220px-Frank_Herbert.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/J._R._R._Tolkien%2C_ca._1925.jpg/220px-J._R._R._Tolkien%2C_ca._1925.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Isaac.Asimov01.jpg/220px-Isaac.Asimov01.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Alexandre_Dumas.jpg/220px-Alexandre_Dumas.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Victor_Hugo-Hachette.jpg/220px-Victor_Hugo-Hachette.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/CassandraAusten-JaneAusten%28c.1810%29_hires.jpg/220px-CassandraAusten-JaneAusten%28c.1810%29_hires.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mary_Wollstonecraft_Shelley_Rothwell.jpg/220px-Mary_Wollstonecraft_Shelley_Rothwell.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Oscar_Wilde_Sarony.jpg/220px-Oscar_Wilde_Sarony.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Juan_Rulfo.jpg/220px-Juan_Rulfo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hermann_Hesse.jpg/220px-Hermann_Hesse.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Paulo_Coelho_2013.jpg/220px-Paulo_Coelho_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Gabriel_Garcia_Marquez.jpg/220px-Gabriel_Garcia_Marquez.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Cortazar_Gatto.jpg/220px-Cortazar_Gatto.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Franz_Kafka%2C_1923.jpg/220px-Franz_Kafka%2C_1923.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Juan_Rulfo.jpg/220px-Juan_Rulfo.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Hermann_Hesse.jpg/220px-Hermann_Hesse.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Paulo_Coelho_2013.jpg/220px-Paulo_Coelho_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Alexandre_Dumas.jpg/220px-Alexandre_Dumas.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Victor_Hugo-Hachette.jpg/220px-Victor_Hugo-Hachette.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Dostoevsky_1872.jpg/220px-Dostoevsky_1872.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/220px-L.N.Tolstoy_Prokudin-Gorsky.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/F_Scott_Fitzgerald_1921.jpg/220px-F_Scott_Fitzgerald_1921.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Harper_Lee_-_foto_1.JPG/220px-Harper_Lee_-_foto_1.JPG",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Umberto_Eco_-_Festival_Economia_2013.jpg/220px-Umberto_Eco_-_Festival_Economia_2013.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Frank_Herbert.jpg/220px-Frank_Herbert.jpg",
]

# Filtrar URLs que excedan VARCHAR(255) - reemplazar por fallback
AUTHOR_PHOTO_URLS = [
    u if len(u) <= MAX_VARCHAR else FALLBACK_AUTHOR_PHOTO
    for u in AUTHOR_PHOTO_URLS_RAW
]
while len(AUTHOR_PHOTO_URLS) < 100:
    AUTHOR_PHOTO_URLS.append(FALLBACK_AUTHOR_PHOTO)
AUTHOR_PHOTO_URLS = AUTHOR_PHOTO_URLS[:100]


def random_date(start_year=1900, end_year=2024):
    """Genera fecha aleatoria."""
    start = datetime(start_year, 1, 1)
    end = datetime(end_year, 12, 31)
    delta = (end - start).days
    return start + timedelta(days=random.randint(0, delta))


def generate_isbn(used_isbns):
    """Genera ISBN único de 13 dígitos (978 + 10 dígitos)."""
    while True:
        isbn = 9780000000000 + random.randint(0, 9999999999)
        if isbn not in used_isbns:
            used_isbns.add(isbn)
            return isbn


def generate_book(i, used_isbns):
    """Genera un libro con todos los campos no nulos."""
    author = random.choice(AUTHORS)
    title_base = random.choice(TITLE_BASES)
    title = f"{title_base} {random.choice(['del', 'de', 'en', 'con'])} {random.choice(['olvido', 'tiempo', 'mar', 'sueño', 'corazón'])}" if i > 50 else f"{title_base} #{i}"
    category = random.choice(CATEGORIES)
    isbn = generate_isbn(used_isbns)
    pages = random.randint(80, 750)
    price = round(random.uniform(8.99, 89.99), 2)

    desc = f"Obra literaria que explora temas universales. {title} es una lectura imprescindible en la categoría de {category}."
    bio = f"{author} es un reconocido autor en el género de {category}. Su obra ha trascendido fronteras y generaciones."

    idx = i % 100
    image_url = f"https://covers.openlibrary.org/b/isbn/{BOOK_COVER_ISBNS[idx]}-L.jpg"
    author_photo_url = AUTHOR_PHOTO_URLS[idx]
    editorial = truncate(escape_sql(random.choice(EDITORIALES)), MAX_VARCHAR)
    language = truncate(random.choice(LANGUAGES), MAX_LANGUAGE)

    return (
        truncate(escape_sql(title), MAX_VARCHAR),
        truncate(escape_sql(author), MAX_VARCHAR),
        random_date().strftime("%Y-%m-%d"),
        truncate(escape_sql(category), MAX_VARCHAR),
        isbn,
        random.randint(1, 5),
        random.random() > 0.1,
        random.randint(20, 500),
        price,
        truncate(image_url, MAX_VARCHAR),
        escape_sql(desc),
        editorial,
        language,
        pages,
        random.randint(1, 5),
        escape_sql(bio),
        truncate(author_photo_url, MAX_VARCHAR),
    )


def main():
    output_path = os.path.join(SCRIPT_DIR, "..", "books-2000.sql")
    used_isbns = set()
    batch_size = 50
    total = 2000

    # Validación: verificar que todas las URLs cumplan VARCHAR(255)
    long_urls = [u for u in AUTHOR_PHOTO_URLS if len(u) > MAX_VARCHAR]
    if long_urls:
        print(f"ERROR: {len(long_urls)} URLs de autor exceden {MAX_VARCHAR} caracteres", file=sys.stderr)
        sys.exit(1)
    sample_img = f"https://covers.openlibrary.org/b/isbn/{BOOK_COVER_ISBNS[0]}-L.jpg"
    if len(sample_img) > MAX_VARCHAR:
        print(f"ERROR: image_url excede {MAX_VARCHAR} caracteres", file=sys.stderr)
        sys.exit(1)

    with open(output_path, "w", encoding="utf-8") as f:
        f.write("-- =============================================================\n")
        f.write("-- SCRIPT: 2000 libros - Reemplaza datos existentes\n")
        f.write("-- Generado automáticamente. Sin campos nulos.\n")
        f.write("-- Ejecutar contra PostgreSQL: psql -d books -f books-2000.sql\n")
        f.write("-- Incluye ROLLBACK al inicio por si hubo transacción abortada en sesión previa\n")
        f.write("-- =============================================================\n\n")

        f.write("-- Limpiar transacción abortada previa (si existe)\n")
        f.write("ROLLBACK;\n\n")
        f.write("BEGIN;\n\n")
        f.write("TRUNCATE TABLE books RESTART IDENTITY CASCADE;\n\n")

        for batch_start in range(0, total, batch_size):
            batch_end = min(batch_start + batch_size, total)
            rows = []
            for i in range(batch_start, batch_end):
                book = generate_book(i, used_isbns)
                visible = "true" if book[6] else "false"
                row = f"    ('{book[0]}', '{book[1]}', '{book[2]}', '{book[3]}', {book[4]}, {book[5]}, {visible}, {book[7]}, {book[8]}, '{book[9]}', '{book[10]}', '{book[11]}', '{book[12]}', {book[13]}, {book[14]}, '{book[15]}', '{book[16]}')"
                rows.append(row)

            f.write("INSERT INTO books (title, author, publication_date, category, isbn, valoration, is_visible, current_stock, price, image_url, description, editorial, language, pages, edition, biography, author_photo_url)\n")
            f.write("VALUES\n")
            f.write(",\n".join(rows))
            f.write(";\n\n")

        f.write("COMMIT;\n")
        f.write("\n-- Total: 2000 libros insertados.\n")

    print(f"Generado: {output_path} ({total} libros)")


if __name__ == "__main__":
    random.seed(42)
    main()
