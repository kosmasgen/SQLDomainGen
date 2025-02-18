CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    c_name VARCHAR(100)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    description VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);
