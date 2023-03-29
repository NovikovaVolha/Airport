INSERT INTO gender (ID, gender)
VALUES
    (1, 'male'),
    (2, 'female');

INSERT INTO payment_status (ID, status)
VALUES
    (1, 'unpaid'),
    (2, 'paid'),
    (3, 'failed'),
    (4, 'expired'),
    (5, 'refunding'),
    (6, 'refunded');

INSERT INTO order_status (ID, status)
VALUES
    (1, 'new'),
    (2, 'payment received'),
    (3, 'payment failed'),
    (4, 'in progress'),
    (5, 'completed'),
    (6, 'closed'),
    (7, 'canceled');

INSERT INTO city (ID, name, country)
VALUES
    (1, 'Minsk', 'Belarus'),
    (2, 'Saint-Petersburg', 'Russia'),
    (3, 'Moscow', 'Russia'),
    (4, 'Istambul', 'Turkey'),
    (5, 'Warsaw', 'Poland'),
    (6, 'Berlin', 'Germany');


INSERT INTO passenger (ID, first_name, last_name, date_of_birth, gender_id, email, address)
VALUES
    (1, 'Ivan', 'Ivanov', '1956-01-01', 1, 'test@mail.ru', 'Minsk'),
    (2, 'Petr', 'Petrov', '1980-02-02', 1, 'email@mail.ru', 'Brest'),
    (3, 'Lidia', 'Kuprina', '1990-03-03', 2, 'mail@gmail.com', 'Hrodna'),
    (4, 'Anna', 'Karenina', '2000-04-04', 2, 'mail@yandex.ru', 'Homel'),
    (5, 'Fedor', 'Fedorov', '1985-05-05', 1, 'mail@mail.ru', 'Vitebsk');

INSERT INTO plane (ID, manufacturer, model, seats)
VALUES
    (1, 'Boeing', 'Boeing 727', '105'),
    (2, 'Boeing', 'Boeing 787', '305');

INSERT INTO staff (ID, first_name, last_name, date_of_birth, gender_id, title)
VALUES
    (1, 'John', 'Pilot', '1960-06-06', 1, 'captain'),
    (2, 'Vladislav', 'Pilot1', '1961-07-07', 1, 'first officer'),
    (3, 'Reban', 'Pilot2', '1962-08-08', 1, 'second officer'),
    (4, 'Vasiliy', 'Engineer', '1980-09-09', 1, 'flight engineer'),
    (5, 'Veronica', 'Stewardess', '1985-10-10', 2, 'stewardess'),
    (6, 'Rick', 'Steward', '1990-11-11', 1, 'steward');

INSERT INTO aircrew (ID, staff_id, plane_id)
VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 3, 1),
    (4, 4, 1),
    (5, 5, 1),
    (6, 6, 1);

INSERT INTO route (ID, departure_city_id, departure_date_time, arrival_city_id, arrival_date_time)
VALUES
    (1, 1, '2023-03-22 12-44-00', 3, '2023-03-22 14-00-00'),
    (2, 1, '2023-03-24 10-00-00', 4, '2023-03-24 16-00-00');

INSERT INTO plane_route (ID, plane_id, route_id)
VALUES
    (1, 1, 1),
    (2, 1, 2);

INSERT INTO orders (ID, order_status_id, passenger_id, plane_id, route_id)
VALUES
    (1, 2, 1, 1, 1),
    (2, 5, 2, 2, 2),
    (3, 1, 3, 1, 1);

INSERT INTO payment (ID, order_id, payment_status_id)
VALUES
    (1, 1, 2),
    (2, 2, 2),
    (3, 3, 1);

INSERT INTO ticket (ID, order_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);