BEGIN;
INSERT INTO reservations(id, flight_id,passenger_id)
values (NEXTVAL('reservationsSequence'),1,1);
COMMIT;
