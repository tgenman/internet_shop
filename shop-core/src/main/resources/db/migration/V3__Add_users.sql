insert into user (id, email, enabled, first_name, last_name, password, date_of_birth)
values (2, 'user10@s.ru', true, 'Petr10', 'Petrov10', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (3, 'user1@s.ru', true, 'Petr1', 'Petrov1', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (4, 'user2@s.ru', true, 'Petr2', 'Petrov2', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (5, 'user3@s.ru', true, 'Petr3', 'Petrov3', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (6, 'user4@s.ru', true, 'Petr4', 'Petrov4', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (7, 'user5@s.ru', true, 'Petr5', 'Petrov5', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (8, 'user6@s.ru', true, 'Petr6', 'Petrov6', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (9, 'user7@s.ru', true, 'Petr7', 'Petrov7', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (10, 'user8@s.ru', true, 'Petr8', 'Petrov8', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990'),
       (11, 'user9@s.ru', true, 'Petr9', 'Petrov9', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG', '24.07.1990');

insert into user_roles (user_id, roles)
values (2, 'ROLE_USER'),
       (3, 'ROLE_USER'),
       (4, 'ROLE_USER'),
       (5, 'ROLE_USER'),
       (6, 'ROLE_USER'),
       (7, 'ROLE_USER'),
       (8, 'ROLE_USER'),
       (9, 'ROLE_USER'),
       (10, 'ROLE_USER'),
       (11, 'ROLE_USER');

insert into address (id, active,  postal_code, country, city, street, building, flat)
values (1, true, 123456, 'Russia', 'Saint-Petesburg', 'some', '10', '20'),
       (2, true, 123457, 'Russia', 'Saint-Petesburg', 'some', '20', '90'),
       (3, true, 123458, 'Russia', 'Saint-Petesburg', 'some', '30', '100'),
       (4, true, 123456, 'Russia', 'Saint-Petesburg', 'some', '10', '20'),
       (5, true, 123457, 'Russia', 'Saint-Petesburg', 'some', '20', '90'),
       (6, true, 123458, 'Russia', 'Saint-Petesburg', 'some', '30', '100'),
       (7, true, 123456, 'Russia', 'Saint-Petesburg', 'some', '10', '20'),
       (8, true, 123457, 'Russia', 'Saint-Petesburg', 'some', '20', '90'),
       (9, true, 123458, 'Russia', 'Saint-Petesburg', 'some', '30', '100'),
       (10, true, 123456, 'Russia', 'Saint-Petesburg', 'some', '10', '20'),
       (11, true, 123457, 'Russia', 'Saint-Petesburg', 'some', '20', '90'),
       (12, true, 123458, 'Russia', 'Saint-Petesburg', 'some', '30', '100');

insert into user_addresses (user_id, address_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3);