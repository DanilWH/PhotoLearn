DELETE FROM user_roles;
DELETE FROM users;

INSERT INTO users (id, active, password, username) VALUES
    (1, true, '$2y$12$DmqwbW1gYVmKUfqZEwhjw.MExxVw9RK3/Gqn6pq0jciwhujDCwBxa', 'pl_admin'),
    (2, true, '$2y$12$VPngvsG5wSYVQ4v2OUTH2ey86c9.n44BZMO31ABvnONgZVwObIiYa', 'pl_teacher'),
    (3, true, '$2y$12$z8lZe.w/C3caRolGa4JYjuzWvP7OeZXY5q0RFitOT8TrJ1hd0jriC', 'pl_student');

INSERT INTO user_roles (user_id, user_roles) VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_TEACHER'),
    (3, 'ROLE_STUDENT');
