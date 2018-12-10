insert into user (id, email, enabled, first_name, last_name, password)
    values (1, 'admin@admin.ru', true, 'Ivan', 'Adminov', '$2a$10$h2ZnoB9oIoJFF9m7mKhNG.W.OiS74eRcXeADC30qa0z6UnEqC0AeG');

insert into user_roles (user_id, roles)
    values (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');
