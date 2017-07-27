DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT  INTO meals (userId,datetime, description, calories) VALUES
  (100000,TIMESTAMP '2017-05-16 12:00:38','test1',500),
  (100000,TIMESTAMP '2017-05-16 10:00:38','test2',500),
  (100001,TIMESTAMP '2017-05-16 10:00:38','testA1',500);