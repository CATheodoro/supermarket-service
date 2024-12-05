--PRODUCT TABLE
INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_WATERMELON', 'Watermelon', '1000', '100', '2025-01-11T11:00:00-03:00');

INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_BANANA', 'Banana', '5999', '50', '2025-01-11T11:00:00-03:00');

INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_SODA', 'Soda', '999', '50', '2025-01-11T11:00:00-03:00');

INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_RICE', 'Rice', '1500', '10', '2025-01-11T11:00:00-03:00');

INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_BEAN', 'Bean', '1500', '10', '2025-01-11T11:00:00-03:00');

INSERT INTO product (id, name, price, stock_quantity, creation_date)
VALUES ('ID_PRODUCT_BREAD', 'Bread', '100', '10', '2025-01-11T11:00:00-03:00');

--PROMOTION TABLE
INSERT INTO promotion (id, code, description, id_product, active, required_quantity, price, amount, free_quantity, creation_date)
VALUES ('ID_PROMOTION_BANANA', 'BUY_X_GET_Y_FREE', 'Promotion for banana BUY_X_GET_Y_FREE', 'ID_PRODUCT_BANANA', true, 2, null, null, 1, '2025-01-11T11:00:00-03:00');

INSERT INTO promotion (id, code, description, id_product, active, required_quantity, price, amount, free_quantity, creation_date)
VALUES ('ID_PROMOTION_SODA', 'QTY_BASED_PRICE_OVERRIDE', 'Promotion for soda QTY_BASED_PRICE_OVERRIDE', 'ID_PRODUCT_SODA', true, 2, 100, null, null, '2025-01-11T11:00:00-03:00');

INSERT INTO promotion (id, code, description, id_product, active, required_quantity, price, amount, free_quantity, creation_date)
VALUES ('ID_PROMOTION_RICE', 'FLAT_PERCENT', 'Promotion for soda FLAT_PERCENT', 'ID_PRODUCT_RICE', true, null, null, 10, null, '2025-01-11T11:00:00-03:00');

--CART TABLE
INSERT INTO cart (id, total_price, discount, final_price)
VALUES ('ID_CART_WITHOUT_CART_ITEM', 0, 0, 0);


--CART ITEM TABLE
--INSERT INTO cart_item (id, quantity, unit_price, id_product, id_promotion, cart_id)
--VALUES
--('ITEM_ID_01', 2, 500, 'PRODUCT_ID_01', 'PROMO_ID_01', 'ID_CART_01'),
--('ITEM_ID_02', 1, 500, 'PRODUCT_ID_02', NULL, 'ID_CART_01');