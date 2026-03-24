DROP TABLE IF EXISTS departments,
employees,
salary,
phone_number_type,
employees_phone_numbers,
customers,
products,
sales CASCADE;

CREATE TABLE departments(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE salary(
    id SERIAL PRIMARY KEY,
    salary INT NOT NULL
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    departments_id INT REFERENCES departments(id),
    reports_to INT,
    salary INT REFERENCES salary(id)
);

CREATE TABLE phone_number_type(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE employees_phone_numbers(
    id SERIAL PRIMARY KEY,
    employees_id INT NOT NULL REFERENCES employees(id),
    phone_number VARCHAR(32) NOT NULL UNIQUE,
    phone_number_type_id INT NOT NULL REFERENCES phone_number_type(id)
);

CREATE TABLE customers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE sales(
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES customers(id),
    employees_id INT NOT NULL REFERENCES employees(id),
    sale_date DATE NOT NULL,
    products_id INT NOT NULL REFERENCES products(id),
    price INT NOT NULL
);

INSERT INTO
    departments (name)
VALUES
    ('Sales'),
    ('Financing'),
    ('Accounting'),
    ('Marketing');

INSERT INTO
    salary (id, salary)
VALUES
    (1, 1400),
    (2, 1450),
    (3, 1150),
    (4, 600),
    (5, 1450),
    (6, 1960),
    (7, 2460),
    (8, 1665),
    (9, 1000),
    (10, 840),
    (11, 935);

INSERT INTO
    employees (
        id,
        first_name,
        last_name,
        departments_id,
        reports_to,
        salary
    )
VALUES
    (1, 'Jack', 'Ofalltrades', 1, NULL, 1),
    (2, 'John', 'Doe', 2, NULL, 2),
    (3, 'Alan', 'Ginsberg', 3, NULL, 3),
    (4, 'Ram', 'Somebody', NULL, NULL, 4),
    (5, 'Sales', 'Dude', 1, 1, 5),
    (6, 'Erkle', 'Jones', 1, 1, 6),
    (7, 'Neeraj', 'Bahri', 1, 1, 7),
    (8, 'Jing', 'Chu', 1, 1, 8),
    (9, 'Ernie', 'Borgnine', 2, 2, 9),
    (10, 'Frank', 'LaPoint', 3, 3, 10),
    (11, 'Debbie', 'Grey', 3, 3, 11);

INSERT INTO
    phone_number_type (name)
VALUES
    ('home'),
    ('work'),
    ('mobile'),
    ('office');

INSERT INTO
    employees_phone_numbers (employees_id, phone_number, phone_number_type_id)
VALUES
    (1, '301-333-1111', 1),
    (1, '410-444-4444', 3),
    (1, '555-555-5555', 2),
    (1, '410-444-4445', 3),
    (2, '301-333-2222', 1),
    (2, '410-444-4446', 3),
    (2, '555-555-5556', 2),
    (3, '301-333-3333', 1),
    (3, '410-444-4447', 3),
    (4, '123-132-1234', 1),
    (5, '123-123-0987', 3);

INSERT INTO
    customers (name)
VALUES
    ('Acme'),
    ('J&R'),
    ('The Best Company, Inc.'),
    ('Angel Investors, LLC'),
    ('Lawyers R Us');

INSERT INTO
    products (name)
VALUES
    ('consulting'),
    ('servers'),
    ('pos'),
    ('staffing'),
    ('furniture');

INSERT INTO
    sales (
        customer_id,
        employees_id,
        sale_date,
        products_id,
        price
    )
VALUES
    (1, 1, '2014-04-01', 1, 2500),
    (1, 6, '2014-03-01', 1, 2550),
    (2, 6, '2014-03-03', 2, 256700),
    (2, 6, '2014-04-03', 3, 1256700),
    (4, 1, '2014-04-10', 1, 2506),
    (2, 7, '2014-04-11', 4, 12383),
    (4, 8, '2014-05-10', 1, 8230),
    (5, 8, '2014-05-09', 2, 15270),
    (5, 1, '2014-05-07', 3, 87230),
    (1, 8, '2014-05-07', 1, 6230);
