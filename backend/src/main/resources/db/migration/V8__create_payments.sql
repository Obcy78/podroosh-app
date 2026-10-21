CREATE TABLE payment
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    expense_id BIGINT        NOT NULL,
    paid_by_id BIGINT        NOT NULL,
    name       VARCHAR(125),
    amount     NUMERIC(6, 2) NOT NULL,
    paid_at    TIMESTAMPTZ,
    CONSTRAINT fk_payment_expense FOREIGN KEY (expense_id) REFERENCES expense (id),
    CONSTRAINT fk_payment_member FOREIGN KEY (paid_by_id) REFERENCES trip_member (id)

)