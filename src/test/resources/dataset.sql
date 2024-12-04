--PRODUCT TABLE
INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_WATERMELON', 'Watermelon', '1000', '100', '2025-01-11T11:00:00-03:00');

INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_BANANA', 'Banana', '5999', '50', '2025-01-11T11:00:00-03:00');

--PROMOTION TABLE
INSERT INTO promotion (id, code, description, id_product, active, required_quantity, price, amount, free_quantity, creation_date)
VALUES ('ID_PROMOTION_BANANA', 'BUY_X_GET_Y_FREE', 'Promotion for banana BUY_X_GET_Y_FREE', 'ID_PRODUCT_BANANA', true, 2, null, null, 1, '2025-01-11T11:00:00-03:00');