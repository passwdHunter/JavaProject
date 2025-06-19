CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    session_id INT NOT NULL,
    user_id INT,
    seat_number VARCHAR(10) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    purchase_time TIMESTAMP,
    CONSTRAINT fk_ticket_session FOREIGN KEY (session_id) REFERENCES session(id) ON DELETE CASCADE,
    CONSTRAINT fk_ticket_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE SET NULL
);

CREATE INDEX idx_ticket_session ON ticket(session_id);
CREATE INDEX idx_ticket_user ON ticket(user_id);
