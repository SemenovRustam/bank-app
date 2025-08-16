CREATE SCHEMA IF NOT EXISTS account_schema;

CREATE TABLE account_schema.users (
 id BIGSERIAL PRIMARY KEY,
 login VARCHAR NOT NULL,
 name VARCHAR NOT NULL,
 birthdate DATE NOT NULL
);

CREATE TABLE account_schema.accounts (
    id SERIAL PRIMARY KEY,
    currency VARCHAR(50) NOT NULL,
    value NUMERIC(19, 4) NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES account_schema.users(id) ON DELETE CASCADE
);