--liquibase formatted sql

--changeset dshparko:1
CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    username  VARCHAR(64) NOT NULL UNIQUE,
    firstname VARCHAR(64),
    lastname  VARCHAR(64),
    email     VARCHAR(255),
    password  varchar(128) default '{noop}123',
    role      VARCHAR(32)
);