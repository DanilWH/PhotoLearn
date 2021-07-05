DELETE FROM photo_results;

INSERT INTO photo_results (id, description, filename, tutorial_id, user_id) VALUES
    (1, 'Description', 'filename.extension', 2, 3);

ALTER SEQUENCE hibernate_sequence RESTART WITH 2;
