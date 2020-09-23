USE `deliverysql`;

CREATE TABLE `DELIVERY` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `total` int(11) NOT NULL,
  `fee` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `USER_DATA` (
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `FOOD_SERVICE` (
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `food_type` varchar(100) NOT NULL,
  `delivery_fee` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `FOOD_SERVICE_FK` FOREIGN KEY (`email`) REFERENCES `USER_DATA` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `FOOD_PRODUCT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `image_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `food_service` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOOD_PRODUCT_FK` (`food_service`),
  CONSTRAINT `FOOD_PRODUCT_FK` FOREIGN KEY (`food_service`) REFERENCES `FOOD_SERVICE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ITEM` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `food_product` int(11) NOT NULL,
  `delivery` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ITEM_FK` (`delivery`),
  KEY `ITEM_FK_1` (`food_product`),
  CONSTRAINT `ITEM_FK` FOREIGN KEY (`delivery`) REFERENCES `DELIVERY` (`id`),
  CONSTRAINT `ITEM_FK_1` FOREIGN KEY (`food_product`) REFERENCES `FOOD_PRODUCT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
