CREATE TABLE trip
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    start_date   TIMESTAMPTZ,
    end_date     TIMESTAMPTZ,
    description VARCHAR(255),
    status      VARCHAR(20)
)