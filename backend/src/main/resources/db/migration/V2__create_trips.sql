CREATE TABLE trip
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    start_date  DATE,
    end_date    DATE,
    description VARCHAR(255),
    status      VARCHAR(20),
    CONSTRAINT chk_trip_dates
        CHECK (end_date IS NULL OR start_date IS NULL OR end_date >= start_date)
)