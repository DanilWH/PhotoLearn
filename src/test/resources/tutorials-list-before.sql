DELETE FROM tutorials;

INSERT INTO tutorials (id, content, created_on, title, updated_on, user_id) VALUES
    (1, 'Content of the tutorial', '2021-07-03 16:26:30.237773', 'Learn to edit photos', '2021-07-03 16:26:30.237773', 1),
    (2, 'content', '2021-07-03 16:26:30.237773', 'pl_admin', '2021-07-03 16:26:30.237773', 1),
    (3, 'The second users tutorial content', '2021-07-03 16:26:30.237773', 'Photo editing', '2021-07-03 16:26:30.237773', 2),
    (4, 'A fly is flying', '2021-07-03 16:26:30.237773', 'A fly', '2021-07-03 16:26:30.237773', 1);

ALTER SEQUENCE tutorials_id_seq RESTART WITH 10;
