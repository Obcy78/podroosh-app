CREATE TABLE trip_member
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id   BIGINT NOT NULL,
    status    VARCHAR(20),
    role      VARCHAR(20),
    joined_at TIMESTAMPTZ,
    CONSTRAINT fk_trip_member_user FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_trip_member_trip FOREIGN KEY (user_id) REFERENCES trip (id)
)