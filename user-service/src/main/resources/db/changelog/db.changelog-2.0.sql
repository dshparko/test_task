--liquibase formatted sql

--changeset dshparko:1
INSERT INTO users (username, firstname, lastname, email, password, role)
VALUES ('ivanov', 'Ivan', 'Ivanov', 'ivan@example.com', '$2a$10$CFrU8exOjRLoMtCoCLZqMOdCv.kplTKu7jnMtWy/pVDnTpmyQAb32', 'ADMIN'),
       ('petrov', 'Petr', 'Petrov', 'petr@example.com', '$2a$10$pYVGjGVKcoOzz.DnJ1eNkuCkJ9u6Cf6.7W6CqTXsDmMYon5n8z5Ha', 'USER'),
       ('svetik', 'Sveta', 'Svetikova', 'sveta@example.com', '', 'USER'),
       ('vladik', 'Vlad', 'Vladikov', 'vlad@example.com', '', 'USER'),
       ('ksmith', 'Kate', 'Smith', 'kate@example.com', '', 'ADMIN');