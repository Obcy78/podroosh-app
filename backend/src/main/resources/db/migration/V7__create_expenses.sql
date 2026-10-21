CREATE TABLE expense
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    trip_id     BIGINT        NOT NULL,
    description VARCHAR(255),
    type        VARCHAR(20),
    amount      NUMERIC(6, 2) NOT NULL check ( amount > 0 ),
    CONSTRAINT fk_expense_trip FOREIGN KEY (trip_id) REFERENCES trip (id)

)