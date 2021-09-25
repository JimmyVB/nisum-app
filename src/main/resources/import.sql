
INSERT INTO usuarios (username, password, enabled, nombre, apellido) VALUES ('jimmy', '$2a$10$dx2ZMR27lmfgVtfzuC88T.22TBJ7e.XdhdeDnTm0nBZS/dGtuFUri', 1, 'Jimmy', 'Valdez');
INSERT INTO usuarios (username, password, enabled, nombre, apellido) VALUES ('admin', '$2a$10$3Rnj9yhWdHysH/Td9sohTuC0DtpHg0O9HTfauOxgj3PXtikbLbCV.', 1, 'Richard', 'Blas');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);


