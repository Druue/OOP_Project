INSERT INTO
    details(id, description, name, image)
VALUES
    (1 , 'this is a student use', 'Thomas','thomas.png' ),
    (2 , 'this is a staff user', 'Sophie','sophie.png'),
    (3 , 'this is a guest user', 'Nikko', 'nikko.png'),
    (4 , 'this is a admin user', 'Justin', 'justin.png'),
    (5, 'bike', 'racing bike', 'racing_bike.png'),
    (6, 'bike', 'normal bike', 'normal_bike.png'),
    (7, 'room', 'water', 'water_room.png'),
    (8, 'room', 'sun', 'sun_room.png'),
    (9, 'room', 'australia', 'australia_room.png'),
    (10, 'room', 'europe', 'europe_room.png'),
    (11, 'room', 'asia', 'asia_room.png'),
    (12, 'room', 'africa', 'africa_room.png'),
    (13, 'room', 'america', 'america_room.png');


INSERT  INTO
    User(user_id, email, password, userkind, username, details)
VALUES
    (010 , 'test@student.tudelft.nl', 'test', 2, 'teststudent',null ),
    (020 , 'test@tudelft.nl', 'test', 3, 'teststaffmember',null),
    (030 , 'test@guest.tudelft.nl', 'test', 1, 'testguest',null),
    (040 , 'test@admin.tudelft.nl', 'test', 4, 'testadmin',null);

INSERT INTO
    Timeslots(timeslots_id)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9);

INSERT INTO
    timeslot(id, end, start)
VALUES
    (1, '2020-06-02 22:00:00', '2020-06-02 06:30:00'),
    (2, '2020-06-02 00:00:00', '2020-06-02 08:00:00'),
    (3, '2020-06-02 18:00:00', '2020-06-02 08:00:00'),
    (4, '2020-06-02 20:00:00', '2020-06-02 08:00:00'),
    (5, '2020-06-02 21:30:00', '2020-06-02 08:30:00'),
    (6, '2020-06-02 21:30:00', '2020-06-02 08:30:00'),
    (7, '2020-06-02 21:30:00', '2020-06-02 08:30:00'),
    (8, '2020-06-02 21:30:00', '2020-06-02 08:30:00'),
    (9, '2020-06-02 21:30:00', '2020-06-02 08:30:00');

INSERT  INTO
   building(id, details, opening_hours)
VALUES
    (36, 4, 1),
    (28, 4, 2),
    (22, 4, 3),
    (34, 4, 4),
    (33, 4, 5),
    (8, 4, 6),
    (23, 4, 7),
    (26, 4, 8),
    (21, 4, 9);



INSERT INTO
    reservable(id, details)
VALUES
    (1, 4),
    (2, 4),
    (3, 4),
    (4, 4),
    (5, 4),
    (6, 4),
    (7, 4),
    (8, 4),
    (9, 4),
    (10, 4),
    (11, 4),
    (12, 4),
    (13, 4);


INSERT INTO
    room(capacity, for_employee, has_projector, id)
VALUES
    (8, 1, 0, 7),
    (8, 1, 1, 8),
    (8, 1, 0, 9 ),
    (6, 0, 0, 10),
    (6, 1, 1, 11),
    (6,1,0, 12),
    (12, 1, 1, 13);

INSERT INTO
    Bike(id)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6);







