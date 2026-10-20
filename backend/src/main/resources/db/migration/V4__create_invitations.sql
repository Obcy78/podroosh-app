CREATE TABLE Invitation
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    trip_id     BIGINT NOT NULL,
    status      VARCHAR(20),
    description VARCHAR(255),
    created_at  TIMESTAMPTZ,
    expired_at  TIMESTAMPTZ,
    CONSTRAINT fk_invitation_user FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_invitation_trip FOREIGN KEY (user_id) REFERENCES trip (id)

)