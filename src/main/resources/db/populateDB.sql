DELETE FROM USER_ROLES;
DELETE FROM RESTAURANTS;
DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM USERS;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO RESTAURANTS (NAME, ADDRESS)
VALUES ('Канопус', 'ул Волкова 26'),
       ('Ганимед', 'ул Волкова 25');

INSERT INTO VOTES (DATE, TIME, RESTAURANT_ID, USER_ID)
VALUES ('2015-05-30', '10:00:00', 100002, 100000),
       ('2015-05-30', '10:00:00', 100003, 100001),
       ('2015-05-31', '10:00:00', 100003, 100000),
       ('2015-05-31', '10:00:00', 100003, 100001);

INSERT INTO DISHES (DATE, DISH_NAME, RESTAURANT_ID, PRICE)
VALUES ('2015-05-30', 'борщ', 100002, 56000),
       ('2015-05-30', 'голубцы', 100002, 10001),
       ('2015-05-30', 'компот', 100002, 20000),
       ('2015-05-30', 'макароны', 100002, 30000),
       ('2015-05-30', 'солянка', 100003, 10001),
       ('2016-05-30', 'манты', 100003, 20000),
       ('2015-05-30', 'чай', 100003, 30000),
       ('2015-05-30', 'перловка', 100003, 10001),
       ('2015-05-31', 'борщ', 100002, 10001),
       ('2016-05-31', 'арбуз', 100002, 20000),
       ('2016-05-31', 'ешь', 100002, 30000),
       ('2015-05-31', 'ром', 100002, 10001),
       ('2016-05-31', 'отвар', 100003, 20000),
       ('2016-05-31', 'утка', 100003, 30000),
       ('2016-05-31', 'огузок', 100003, 20000),
       ('2016-05-31', 'долма', 100003, 30000);