INSERT INTO utilisateur(email, password, role, user_type)
VALUES ('tata@gmail.com', '$2a$12$I2Pnqij3JlT1.mmzfizQPuaVG5kfafT7HNM/OlPsBKJnnTBCMDKlK', 'ROLE_ADMIN', 'candidat');

INSERT INTO test(nom)
VALUES ('test_aptitude');

INSERT INTO question(question_type, nombre_points, id_test)
VALUES ('qcm', 3, 1);

INSERT INTO question_qcm(id, question)
VALUES (1, 'Qui est actuellement le président de la république française');

INSERT INTO reponse(reponse_type, id_question)
VALUES ('qcm', 1);

INSERT INTO reponse_qcm(id, response)
VALUES (1, 'Macron');

INSERT INTO reponse(reponse_type, id_question)
VALUES ('qcm', 1);

INSERT INTO reponse_qcm(id, response)
VALUES (2, 'Trump');

INSERT INTO reponse(reponse_type, id_question)
VALUES ('qcm', 1);

INSERT INTO reponse_qcm(id, response)
VALUES (3, 'Charles de Gaulle');

INSERT INTO question(question_type, nombre_points, id_test)
VALUES ('qcm', 2, 1);

INSERT INTO question_qcm(id, question)
VALUES (2, 'Quel extension portent les fichiers Word');

INSERT INTO reponse(reponse_type, id_question)
VALUES ('qcm', 2);

INSERT INTO reponse_qcm(id, response)
VALUES (4, '.txt');

INSERT INTO reponse(reponse_type, id_question)
VALUES ('qcm', 2);

INSERT INTO reponse_qcm(id, response)
VALUES (5, '.doc');
