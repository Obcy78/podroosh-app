CREATE TABLE trip
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    startDate   TIMESTAMPTZ,
    endDate     TIMESTAMPTZ,
    description VARCHAR(255),
    status      VARCHAR(20)
)