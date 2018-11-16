--liquibase formatted sql

--changeset naijab:1542402593261-1
CREATE TABLE ads (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, description VARCHAR(50), type VARCHAR(20) NOT NULL, position VARCHAR(20) NOT NULL, gender_target VARCHAR(20) NOT NULL, is_show VARCHAR(20) NOT NULL, CONSTRAINT PK_ADS PRIMARY KEY (id), UNIQUE (id));

--changeset naijab:1542402593261-2
CREATE TABLE bill (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, address_id INT NOT NULL, create_at TIMESTAMP NOT NULL, update_at TIMESTAMP NOT NULL, bill_status VARCHAR(20) NOT NULL, shipping_status VARCHAR(20) NOT NULL, shipping_type VARCHAR(45) NOT NULL, total_price VARCHAR(45) NOT NULL, promotion_id INT, CONSTRAINT PK_BILL PRIMARY KEY (id));

--changeset naijab:1542402593261-3
CREATE TABLE product_category (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, promotion_id INT, CONSTRAINT PK_PRODUCT_CATEGORY PRIMARY KEY (id));

--changeset naijab:1542402593261-4
CREATE TABLE product_favorite (product_id INT NOT NULL, user_id INT NOT NULL, CONSTRAINT PK_PRODUCT_FAVORITE PRIMARY KEY (product_id, user_id));

--changeset naijab:1542402593261-5
CREATE TABLE product_image (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(100) NOT NULL, product_id INT NOT NULL, CONSTRAINT PK_PRODUCT_IMAGE PRIMARY KEY (id), UNIQUE (name));

--changeset naijab:1542402593261-6
CREATE TABLE product_promotion (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, description VARCHAR(100), minimum_paid DECIMAL(7, 2), rate INT NOT NULL, type VARCHAR(20) NOT NULL, number_can_use INT NOT NULL, start_date TIMESTAMP NOT NULL, end_date TIMESTAMP NOT NULL, CONSTRAINT PK_PRODUCT_PROMOTION PRIMARY KEY (id));

--changeset naijab:1542402593261-7
CREATE TABLE product_question (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, product_id INT NOT NULL, question VARCHAR(200) NOT NULL, answer VARCHAR(200), create_at TIMESTAMP NOT NULL, update_at TIMESTAMP NOT NULL, CONSTRAINT PK_PRODUCT_QUESTION PRIMARY KEY (id));

--changeset naijab:1542402593261-8
CREATE TABLE product_review (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, product_id INT NOT NULL, star DECIMAL(1, 1) NOT NULL, comment VARCHAR(100), create_at TIMESTAMP NOT NULL, CONSTRAINT PK_PRODUCT_REVIEW PRIMARY KEY (id));

--changeset naijab:1542402593261-9
CREATE TABLE product_sale (id INT AUTO_INCREMENT NOT NULL, product_id INT NOT NULL, quantity INT NOT NULL, amount DECIMAL(7, 2) NOT NULL, bill_id INT NOT NULL, create_at TIMESTAMP NOT NULL, price_per_unit DECIMAL(7, 2) NOT NULL, CONSTRAINT PK_PRODUCT_SALE PRIMARY KEY (id));

--changeset naijab:1542402593261-10
CREATE TABLE product_stock (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NOT NULL, category_id INT NOT NULL, promotion_id INT, detail VARCHAR(500), amount_in_stock INT NOT NULL, brand VARCHAR(30) NOT NULL, price DECIMAL(7, 2) NOT NULL, status VARCHAR(20) NOT NULL, create_at TIMESTAMP NOT NULL, update_at TIMESTAMP NOT NULL, is_show VARCHAR(20) NOT NULL, CONSTRAINT PK_PRODUCT_STOCK PRIMARY KEY (id));

--changeset naijab:1542402593261-11
CREATE TABLE user_account (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(100) NOT NULL, password VARCHAR(60) NOT NULL, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, dob date, age INT, mobile VARCHAR(20) NOT NULL, type INT NOT NULL, gender VARCHAR(20) NOT NULL, active_token VARCHAR(60) NOT NULL, active_status VARCHAR(20) NOT NULL, forgot_password_token VARCHAR(60), total_point INT NOT NULL, is_subscribe VARCHAR(20) NOT NULL, CONSTRAINT PK_USER_ACCOUNT PRIMARY KEY (id), UNIQUE (email), UNIQUE (active_token), UNIQUE (forgot_password_token));

--changeset naijab:1542402593261-12
CREATE TABLE user_address (id INT AUTO_INCREMENT NOT NULL, receive_name VARCHAR(100) NOT NULL, street VARCHAR(100) NOT NULL, district VARCHAR(50) NOT NULL, city VARCHAR(50) NOT NULL, zipcode VARCHAR(20) NOT NULL, country VARCHAR(50) NOT NULL, user_id INT NOT NULL, type VARCHAR(20) NOT NULL, CONSTRAINT PK_USER_ADDRESS PRIMARY KEY (id));

--changeset naijab:1542402593261-13
CREATE TABLE user_type (id INT AUTO_INCREMENT NOT NULL, level VARCHAR(20) NOT NULL, "group" VARCHAR(20) NOT NULL, discount INT NOT NULL, minimum_point INT NOT NULL, CONSTRAINT PK_USER_TYPE PRIMARY KEY (id), UNIQUE (level));

--changeset naijab:1542402593261-14
CREATE INDEX address_id ON bill(address_id);

--changeset naijab:1542402593261-15
CREATE INDEX bill_id ON product_sale(bill_id);

--changeset naijab:1542402593261-16
CREATE INDEX category_id ON product_stock(category_id);

--changeset naijab:1542402593261-17
CREATE INDEX product_id ON product_image(product_id);

--changeset naijab:1542402593261-18
CREATE INDEX product_id ON product_question(product_id);

--changeset naijab:1542402593261-19
CREATE INDEX product_id ON product_review(product_id);

--changeset naijab:1542402593261-20
CREATE INDEX product_id ON product_sale(product_id);

--changeset naijab:1542402593261-21
CREATE INDEX promotion_id ON bill(promotion_id);

--changeset naijab:1542402593261-22
CREATE INDEX promotion_id ON product_category(promotion_id);

--changeset naijab:1542402593261-23
CREATE INDEX promotion_id ON product_stock(promotion_id);

--changeset naijab:1542402593261-24
CREATE INDEX type ON user_account(type);

--changeset naijab:1542402593261-25
CREATE INDEX user_id ON bill(user_id);

--changeset naijab:1542402593261-26
CREATE INDEX user_id ON product_favorite(user_id);

--changeset naijab:1542402593261-27
CREATE INDEX user_id ON product_question(user_id);

--changeset naijab:1542402593261-28
CREATE INDEX user_id ON product_review(user_id);

--changeset naijab:1542402593261-29
CREATE INDEX user_id ON user_address(user_id);

--changeset naijab:1542402593261-30
ALTER TABLE bill ADD CONSTRAINT bill_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-31
ALTER TABLE bill ADD CONSTRAINT bill_ibfk_2 FOREIGN KEY (promotion_id) REFERENCES product_promotion (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-32
ALTER TABLE bill ADD CONSTRAINT bill_ibfk_3 FOREIGN KEY (address_id) REFERENCES user_address (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-33
ALTER TABLE product_category ADD CONSTRAINT product_category_ibfk_1 FOREIGN KEY (promotion_id) REFERENCES product_promotion (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-34
ALTER TABLE product_favorite ADD CONSTRAINT product_favorite_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-35
ALTER TABLE product_favorite ADD CONSTRAINT product_favorite_ibfk_2 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-36
ALTER TABLE product_image ADD CONSTRAINT product_image_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-37
ALTER TABLE product_question ADD CONSTRAINT product_question_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-38
ALTER TABLE product_question ADD CONSTRAINT product_question_ibfk_2 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-39
ALTER TABLE product_review ADD CONSTRAINT product_review_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-40
ALTER TABLE product_review ADD CONSTRAINT product_review_ibfk_2 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-41
ALTER TABLE product_sale ADD CONSTRAINT product_sale_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_stock (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-42
ALTER TABLE product_sale ADD CONSTRAINT product_sale_ibfk_2 FOREIGN KEY (bill_id) REFERENCES bill (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-43
ALTER TABLE product_stock ADD CONSTRAINT product_stock_ibfk_1 FOREIGN KEY (category_id) REFERENCES product_category (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-44
ALTER TABLE product_stock ADD CONSTRAINT product_stock_ibfk_2 FOREIGN KEY (promotion_id) REFERENCES product_promotion (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-45
ALTER TABLE user_account ADD CONSTRAINT user_account_ibfk_1 FOREIGN KEY (type) REFERENCES user_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset naijab:1542402593261-46
ALTER TABLE user_address ADD CONSTRAINT user_address_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_account (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

