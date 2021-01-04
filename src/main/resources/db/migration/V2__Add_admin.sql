INSERT INTO users(id, active, password, username)
    VALUES (1, true, '$2y$12$ViEeUddRWPdTnrTxgbWHU.E8fNQfNZ/SV.97jD1A83Wu2EPGznl5e', 'pl_admin');

INSERT INTO user_roles(user_id, user_roles)
    VALUES (1, 'ROLE_ADMIN');
