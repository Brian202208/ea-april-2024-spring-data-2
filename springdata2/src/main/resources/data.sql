-- Drop existing procedures and tables if they exist
DROP PROCEDURE IF EXISTS GenerateReviews;
DROP PROCEDURE IF EXISTS GenerateProducts;
DROP PROCEDURE IF EXISTS GenerateUsers;
DROP TABLE IF EXISTS temp_reviews;
DROP TABLE IF EXISTS temp_products;
DROP TABLE IF EXISTS temp_users;

-- Create temp_users table
CREATE TABLE temp_users (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            email VARCHAR(255) NOT NULL,
                            password VARCHAR(255) NOT NULL,
                            first_name VARCHAR(255) NOT NULL,
                            last_name VARCHAR(255) NOT NULL
);

-- Procedure to generate users
DELIMITER //
CREATE PROCEDURE GenerateUsers()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 100 DO
        INSERT INTO temp_users (email, password, first_name, last_name)
        VALUES (CONCAT('user', i, '@example.com'),
                CONCAT('password', i),
                CONCAT('FirstName', i),
                CONCAT('LastName', i));
        SET i = i + 1;
END WHILE;
END //
DELIMITER ;

-- Call GenerateUsers procedure
CALL GenerateUsers();

-- Create temp_products table
CREATE TABLE temp_products (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL,
                               price DECIMAL(10, 2) NOT NULL,
                               rating INT NOT NULL
);

-- Procedure to generate products
DELIMITER //
CREATE PROCEDURE GenerateProducts()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000 DO
        INSERT INTO temp_products (name, price, rating)
        VALUES (CONCAT('Product ', i),
                ROUND(RAND() * 1000 + 10, 2),
                FLOOR(RAND() * 5) + 1);
        SET i = i + 1;
END WHILE;
END //
DELIMITER ;

-- Call GenerateProducts procedure
CALL GenerateProducts();

-- Create temp_reviews table
CREATE TABLE temp_reviews (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              comment VARCHAR(255) NOT NULL,
                              id_user INT,
                              id_product INT,
                              FOREIGN KEY (id_user) REFERENCES temp_users(id),
                              FOREIGN KEY (id_product) REFERENCES temp_products(id)
);

-- Procedure to generate reviews
DELIMITER //
CREATE PROCEDURE GenerateReviews()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000 DO
        INSERT INTO temp_reviews (comment, id_user, id_product)
        VALUES (CONCAT('Review ', i),
                (SELECT id FROM temp_users ORDER BY RAND() LIMIT 1),
                (SELECT id FROM temp_products ORDER BY RAND() LIMIT 1));
        SET i = i + 1;
END WHILE;
END //
DELIMITER ;

-- Call GenerateReviews procedure
CALL GenerateReviews();
