BEGIN;
INSERT INTO flights(id, number, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, plane_id)
VALUES(NEXTVAL('flightsSequence'), 'XYZ789', 'City9', 'City10', '2023-12-01', '07:45:00', '2023-12-01', '17:45:00', 3);
INSERT INTO flights(id, number, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, plane_id)
VALUES(NEXTVAL('flightsSequence'), 'MNO321', 'City11', 'City12', '2023-12-02', '10:15:00', '2023-12-02', '20:15:00', 1);
INSERT INTO flights(id, number, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, plane_id)
VALUES(NEXTVAL('flightsSequence'), 'PQR567', 'City13', 'City14', '2023-12-03', '13:00:00', '2023-12-03', '23:00:00', 2);
INSERT INTO flights(id, number, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, plane_id)
VALUES(NEXTVAL('flightsSequence'), 'STU901', 'City15', 'City16', '2023-12-04', '15:30:00', '2023-12-04', '01:30:00', 3);
COMMIT;
