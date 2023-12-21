BEGIN;
INSERT INTO passengers(id, surname, firstname, email)
VALUES (NEXTVAL('passengersSequence'), 'Marc', 'Samuel', 'marc@example.com');
INSERT INTO passengers(id, surname, firstname, email)
VALUES (NEXTVAL('passengersSequence'), 'Donald', 'Karl', 'donald@example.com');
COMMIT;
