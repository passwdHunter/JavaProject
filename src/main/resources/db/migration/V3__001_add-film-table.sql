CREATE TABLE film (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    duration_minutes INT NOT NULL,
    genre VARCHAR(50),
    director VARCHAR(100),
    release_year INT,
    age_rating VARCHAR(10),
    poster_url VARCHAR(255)
);

CREATE INDEX idx_film_title ON film(title);
