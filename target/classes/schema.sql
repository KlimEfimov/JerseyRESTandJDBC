DROP TABLE IF EXISTS product, category, products_categories, customer, order_detail;

CREATE TABLE product (id SERIAL PRIMARY KEY, name TEXT, price NUMERIC, quantity INTEGER, available BOOLEAN);
CREATE TABLE category (id SERIAL PRIMARY KEY, name TEXT, type TEXT);
CREATE TABLE products_categories (product_id INTEGER REFERENCES product(id), category_id INTEGER REFERENCES category(id), CONSTRAINT products_categories_pk PRIMARY KEY(product_id,category_id) );
CREATE TABLE customer (id SERIAL PRIMARY KEY, name TEXT);
CREATE TABLE order_detail (id SERIAL PRIMARY KEY, order_status TEXT, customer_id INTEGER REFERENCES customer(id), total_amount NUMERIC);

INSERT INTO product (name, price, quantity, available) VALUES ('Product1', 1.1, 1, true), ('Product2', 2.2, 2, false), ('Product3', 3.3, 3, true);
INSERT INTO category (name, type) VALUES ('Category1', 'TYPE_1'), ('Category2', 'TYPE_1'), ('Category3', 'TYPE_2');
INSERT INTO products_categories (product_id, category_id) VALUES (1, 1), (1, 3), (2, 1), (2, 2), (3, 2), (3, 3);
INSERT INTO customer (name) VALUES ('Customer1'), ('Customer2'), ('Customer3'), ('Customer4');
INSERT INTO order_detail (order_status, customer_id, total_amount) VALUES ('STATUS_1', 4, 1.1), ('STATUS_2', 3, 2.2), ('STATUS_3', 3, 3.3);