CREATE TABLE hall (
    id SERIAL PRIMARY KEY,
    cinema_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    hall_type VARCHAR(20) NOT NULL,
    screen_type VARCHAR(20),
    CONSTRAINT fk_hall_cinema FOREIGN KEY (cinema_id) REFERENCES cinema(id) ON DELETE CASCADE
);

CREATE INDEX idx_hall_cinema ON hall(cinema_id);
