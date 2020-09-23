INSERT INTO "USER_DATA" (EMAIL, PASSWORD) VALUES ('email1@email.com', 'pass1'),('email2@email.com', 'pass2')

INSERT INTO "FOOD_SERVICE" (email, name, address, food_type, delivery_fee, active) VALUES('email1@email.com', 'Pizzas 25', 'Street 89', 'PIZZA', 100, 1)

INSERT INTO "FOOD_PRODUCT" (name, price, description, image_url, food_service, active) VALUES('Pizza', 23500, 'Pinaple Pizza', 'imageUrl', 'email1@email.com', 1)

INSERT INTO "DELIVERY" (address, phone, total, fee, email, state) VALUES('Street 50', '555233564', 23600, 100, 'email5@email.com', 'PENDING')

INSERT INTO "ITEM" (food_product, delivery, amount) VALUES(1, 1, 1)

