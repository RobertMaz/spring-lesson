CREATE TABLE IF NOT EXISTS book
(
    id          bigserial PRIMARY KEY,
    name        varchar(20),
    description varchar(50),
    cost        integer,
    user_id     bigint
);
CREATE TABLE IF NOT EXISTS app_users
(
    id          bigserial PRIMARY KEY,
    name        varchar(20)
);