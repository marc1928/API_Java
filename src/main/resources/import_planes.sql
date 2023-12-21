BEGIN;
INSERT INTO planes(id, operator, model, registration, capacity)
VALUES(NEXTVAL('planeSequence'), 'Airbus', 'MSJLH A380',
       'F-QSDF', 20);
INSERT INTO planes(id, operator, model, registration, capacity)
VALUES(NEXTVAL('planeSequence'), 'AirCon', 'BOEING MAE', 'F-SLOP', 50);
COMMIT;
