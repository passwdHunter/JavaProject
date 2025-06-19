CREATE TABLE session (
    id SERIAL PRIMARY KEY,
    film_id INT NOT NULL,
    hall_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    base_price DECIMAL(10,2) NOT NULL,
    current_price DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_session_film FOREIGN KEY (film_id) REFERENCES film(id) ON DELETE CASCADE,
    CONSTRAINT fk_session_hall FOREIGN KEY (hall_id) REFERENCES hall(id) ON DELETE CASCADE
);

CREATE INDEX idx_session_film ON session(film_id);
CREATE INDEX idx_session_hall ON session(hall_id);
CREATE INDEX idx_session_time ON session(start_time);
