DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    drink_choice VARCHAR(100)
);
