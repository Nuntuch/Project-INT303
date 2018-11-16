--liquibase formatted sql

--changeset naijab:1542402566735-1
CREATE TABLE ads (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, `description` VARCHAR(50) NULL, type VARCHAR(20) NOT NULL, position VARCHAR(20) NOT NULL, gender_target VARCHAR(20) NOT NULL, is_show VARCHAR(20) NOT NULL, CONSTRAINT PK_ADS PRIMARY KEY (id), UNIQUE (id));

--changeset naijab:1542402566735-2
CREATE TABLE bill (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, address_id INT NOT NULL, create_at datetime NOT NULL, update_at datetime NOT NULL, bill_status VARCHAR(20) NOT NULL, shipping_status VARCHAR(20) NOT NULL, shipping_type VARCHAR(45) NOT NULL, total_price VARCHAR(45) NOT NULL, promotion_id INT NULL, CONSTRAINT PK_BILL PRIMARY KEY (id));

--changeset naijab:1542402566735-3
CREATE TABLE product_category (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, promotion_id INT NULL, CONSTRAINT PK_PRODUCT_CATEGORY PRIMARY KEY (id));

--changeset naijab:1542402566735-4
CREATE TABLE product_favorite (product_id INT NOT NULL, user_id INT NOT NULL, CONSTRAINT PK_PRODUCT_FAVORITE PRIMARY KEY (product_id, user_id));

--changeset naijab:1542402566735-5
CREATE TABLE product_image (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(100) NOT NULL, product_id INT NOT NULL, CONSTRAINT PK_PRODUCT_IMAGE PRIMARY KEY (id), UNIQUE (name));

--changeset naijab:1542402566735-6
CREATE TABLE product_promotion (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, `description` VARCHAR(100) NULL, minimum_paid DECIMAL(7, 2) NULL, rate INT NOT NULL, type VARCHAR(20) NOT NULL, number_can_use INT NOT NULL, start_date datetime NOT NULL, end_date datetime NOT NULL, CONSTRAINT PK_PRODUCT_PROMOTION PRIMARY KEY (id));

--changeset naijab:1542402566735-7
CREATE TABLE product_question (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, product_id INT NOT NULL, question VARCHAR(200) NOT NULL, answer VARCHAR(200) NULL, create_at datetime NOT NULL, update_at datetime NOT NULL, CONSTRAINT PK_PRODUCT_QUESTION PRIMARY KEY (id));

--changeset naijab:1542402566735-8
CREATE TABLE product_review (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, product_id INT NOT NULL, star DECIMAL(1, 1) NOT NULL, comment VARCHAR(100) NULL, create_at datetime NOT NULL, CONSTRAINT PK_PRODUCT_REVIEW PRIMARY KEY (id));

--changeset naijab:1542402566735-9
CREATE TABLE product_sale (id INT AUTO_INCREMENT NOT NULL, product_id INT NOT NULL, quantity INT NOT NULL, amount DECIMAL(7, 2) NOT NULL, bill_id INT NOT NULL, create_at datetime NOT NULL, price_per_unit DECIMAL(7, 2) NOT NULL, CONSTRAINT PK_PRODUCT_SALE PRIMARY KEY (id));

--changeset naijab:1542402566735-10
CREATE TABLE product_stock (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, category_id INT NOT NULL, promotion_id INT NULL, detail VARCHAR(500) NULL, amount_in_stock INT NOT NULL, brand VARCHAR(30) NOT NULL, price DECIMAL(7, 2) NOT NULL, status VARCHAR(20) NOT NULL, create_at datetime NOT NULL, update_at datetime NOT NULL, is_show VARCHAR(20) NOT NULL, CONSTRAINT PK_PRODUCT_STOCK PRIMARY KEY (id));

--changeset naijab:1542402566735-11
CREATE TABLE user_account (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(100) NOT NULL, password VARCHAR(60) NOT NULL, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, dob date NULL, age INT NULL, mobile VARCHAR(20) NOT NULL, type INT NOT NULL, gender VARCHAR(20) NOT NULL, active_token VARCHAR(60) NOT NULL, active_status VARCHAR(20) NOT NULL, forgot_password_token VARCHAR(60) NULL, total_point INT NOT NULL, is_subscribe VARCHAR(20) NOT NULL, CONSTRAINT PK_USER_ACCOUNT PRIMARY KEY (id), UNIQUE (email), UNIQUE (active_token), UNIQUE (forgot_password_token));

--changeset naijab:1542402566735-12
CREATE TABLE user_address (id INT AUTO_INCREMENT NOT NULL, receive_name VARCHAR(100) NOT NULL, street VARCHAR(100) NOT NULL, district VARCHAR(50) NOT NULL, city VARCHAR(50) NOT NULL, zipcode VARCHAR(20) NOT NULL, country VARCHAR(50) NOT NULL, user_id INT NOT NULL, type VARCHAR(20) NOT NULL, CONSTRAINT PK_USER_ADDRESS PRIMARY KEY (id));

--changeset naijab:1542402566735-13
CREATE TABLE user_type (id INT AUTO_INCREMENT NOT NULL, level VARCHAR(20) NOT NULL, `group` VARCHAR(20) NOT NULL, discount INT NOT NULL, minimum_point INT NOT NULL, CONSTRAINT PK_USER_TYPE PRIMARY KEY (id), UNIQUE (level));

--changeset naijab:1542402566735-14
CREATE INDEX address_id ON bill(address_id);

--changeset naijab:1542402566735-15
CREATE INDEX bill_id ON product_sale(bill_id);

--changeset naijab:1542402566735-16
CREATE INDEX category_id ON product_stock(category_id);

--changeset naijab:1542402566735-17
CREATE INDEX product_id ON product_image(product_id);

--changeset naijab:1542402566735-18
CREATE INDEX product_id ON product_question(product_id);

--changeset naijab:1542402566735-19
CREATE INDEX product_id ON product_review(product_id);

--changeset naijab:1542402566735-20
CREATE INDEX product_id ON product_sale(product_id);

--changeset naijab:1542402566735-21
CREATE INDEX promotion_id ON bill(promotion_id);

--changeset naijab:1542402566735-22
CREATE INDEX promotion_id ON product_category(promotion_id);

--changeset naijab:1542402566735-23
CREATE INDEX promotion_id ON product_stock(promotion_id);

--changeset naijab:1542402566735-24
CREATE INDEX type ON user_account(type);

--changeset naijab:1542402566735-25
CREATE INDEX user_id ON bill(user_id);

--changeset naijab:1542402566735-26
CREATE INDEX user_id ON product_favorite(user_id);

--changeset naijab:1542402566735-27
CREATE INDEX user_id ON product_question(user_id);

--changeset naijab:1542402566735-28
CREATE INDEX user_id ON product_review(user_id);

--changeset naijab:1542402566735-29
CREATE INDEX user_id ON user_address(user_id);

--changeset naijab:1542402566735-30
ALTER TABLE bill ADD CONSTRAINT bill_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-31
ALTER TABLE bill ADD CONSTRAINT bill_ibfk_2 FOREIGN KEY (promotion_id) REFERENCES product_promotion (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-32
ALTER TABLE bill ADD CONSTRAINT bill_ibfk_3 FOREIGN KEY (address_id) REFERENCES user_address (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-33
ALTER TABLE product_category ADD CONSTRAINT product_category_ibfk_1 FOREIGN KEY (promotion_id) REFERENCES product_promotion (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-34
ALTER TABLE product_favorite ADD CONSTRAINT product_favorite_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-35
ALTER TABLE product_favorite ADD CONSTRAINT product_favorite_ibfk_2 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-36
ALTER TABLE product_image ADD CONSTRAINT product_image_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-37
ALTER TABLE product_question ADD CONSTRAINT product_question_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-38
ALTER TABLE product_question ADD CONSTRAINT product_question_ibfk_2 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-39
ALTER TABLE product_review ADD CONSTRAINT product_review_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-40
ALTER TABLE product_review ADD CONSTRAINT product_review_ibfk_2 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-41
ALTER TABLE product_sale ADD CONSTRAINT product_sale_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-42
ALTER TABLE product_sale ADD CONSTRAINT product_sale_ibfk_2 FOREIGN KEY (bill_id) REFERENCES bill (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-43
ALTER TABLE product_stock ADD CONSTRAINT product_stock_ibfk_1 FOREIGN KEY (category_id) REFERENCES product_category (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-44
ALTER TABLE product_stock ADD CONSTRAINT product_stock_ibfk_2 FOREIGN KEY (promotion_id) REFERENCES product_promotion (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-45
ALTER TABLE user_account ADD CONSTRAINT user_account_ibfk_1 FOREIGN KEY (type) REFERENCES user_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402566735-46
ALTER TABLE user_address ADD CONSTRAINT user_address_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

