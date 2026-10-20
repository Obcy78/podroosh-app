CREATE TABLE equipment
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    trip_id        BIGINT NOT NULL,
    provided_by_id BIGINT,
    description    VARCHAR(255),
    status         VARCHAR(20),
    CONSTRAINT fk_equipment_trip FOREIGN KEY (trip_id) REFERENCES trip (id),
    CONSTRAINT fk_equipment_trip_member FOREIGN KEY (provided_by_id) REFERENCES trip_member (id)
)