CREATE SCHEMA IF NOT EXISTS randomdata;

CREATE TABLE NAME (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(20) UNIQUE,
    compuesto BOOLEAN DEFAULT FALSE
);