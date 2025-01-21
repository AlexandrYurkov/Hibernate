BEGIN;

DROP TABLE IF EXISTS buy;
DROP TABLE IF EXISTS buyer;
DROP TABLE IF EXISTS product;

CREATE TABLE buyer (
    id SERIAL primary key,
    name TEXT not null
);

CREATE TABLE product (
    id SERIAL primary key,
    title text not null,
    price integer not null
);

CREATE TABLE buy (
    buyer_id INTEGER not null REFERENCES buyer (id) on delete cascade,
    product_id INTEGER not null REFERENCES product (id) on delete cascade,
    price integer not null,
    primary key (buyer_id, product_id)
);


INSERT INTO buyer (name) VALUES ('test_10');
INSERT INTO buyer (name) VALUES ('test_20');
INSERT INTO product (title, price) VALUES ('product_10', 10);
INSERT INTO product (title, price) VALUES ('product_20', 20);
INSERT INTO buy (buyer_id, product_id, price) VALUES (1, 1, 10);
INSERT INTO buy (buyer_id, product_id, price) VALUES (2, 2, 20);
COMMIT;