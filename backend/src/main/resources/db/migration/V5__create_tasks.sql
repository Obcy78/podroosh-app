CREATE TABLE task
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    trip_id        BIGINT NOT NULL,
    trip_member_id BIGINT NOT NULL,
    name           VARCHAR(125),
    description    VARCHAR(255),
    status         VARCHAR(20),
    CONSTRAINT fk_task_trip FOREIGN KEY (trip_id) REFERENCES trip (id),
    CONSTRAINT fk_task_trip_member FOREIGN KEY (trip_member_id) REFERENCES trip_member (id)

)