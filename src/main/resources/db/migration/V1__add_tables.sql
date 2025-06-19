CREATE TABLE public.user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.cinema (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE public.film (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    duration_minutes INTEGER NOT NULL CHECK (duration_minutes > 0),
    release_date DATE
);

CREATE TABLE public.hall (
    id SERIAL PRIMARY KEY,
    cinema_id INTEGER NOT NULL REFERENCES public.cinema(id) ON DELETE CASCADE,
    name VARCHAR(50) NOT NULL,
    capacity INTEGER NOT NULL CHECK (capacity > 0)
);

CREATE TABLE public.session (
    id SERIAL PRIMARY KEY,
    film_id INTEGER NOT NULL REFERENCES public.film(id) ON DELETE CASCADE,
    hall_id INTEGER NOT NULL REFERENCES public.hall(id) ON DELETE CASCADE,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL CHECK (end_time > start_time)
);

CREATE TABLE public.ticket (
    id SERIAL PRIMARY KEY,
    session_id INTEGER NOT NULL REFERENCES public.session(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES public.user(id) ON DELETE SET NULL,
    seat_number INTEGER NOT NULL CHECK (seat_number > 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('AVAILABLE', 'BOOKED', 'PURCHASED', 'CANCELLED')),
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.payment (
    id SERIAL PRIMARY KEY,
    ticket_id INTEGER NOT NULL REFERENCES public.ticket(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES public.user(id) ON DELETE SET NULL,
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.review (
    id SERIAL PRIMARY KEY,
    film_id INTEGER NOT NULL REFERENCES public.film(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES public.user(id) ON DELETE SET NULL,
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 10),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.notification (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES public.user(id) ON DELETE SET NULL,
    message TEXT NOT NULL,
    type VARCHAR(20) NOT NULL CHECK (type IN ('TICKET_PURCHASE', 'SESSION_REMINDER', 'PROMOTION')),
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);