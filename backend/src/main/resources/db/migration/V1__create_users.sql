CREATE TABLE app_user
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(125) not null,
    surname VARCHAR(125) not null,
    status  VARCHAR(20),
    created TIMESTAMPTZ  NOT NULL DEFAULT NOW()
)