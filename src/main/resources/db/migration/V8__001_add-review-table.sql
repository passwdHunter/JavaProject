CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    film_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 10),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_review_film FOREIGN KEY (film_id) REFERENCES film(id) ON DELETE CASCADE,
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);

CREATE INDEX idx_review_film ON review(film_id);
CREATE INDEX idx_review_user ON review(user_id);
