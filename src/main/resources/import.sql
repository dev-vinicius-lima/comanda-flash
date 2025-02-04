-- Limpar tabelas antes de inserir dados
DELETE FROM tb_order_item;
DELETE FROM tb_customer_order;
DELETE FROM tb_email_send;
DELETE FROM tb_product_category;
DELETE FROM tb_product;
DELETE FROM tb_category;
DELETE FROM tb_customer;
DELETE FROM tb_table;
DELETE FROM users;

-- Inserir categorias
INSERT INTO tb_category (name) VALUES ('Bebidas');
INSERT INTO tb_category (name) VALUES ('Comidas');

-- Inserir produtos
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url) VALUES ('Cerveja Pilsen', 100, 10, 5.0, 'img_url_cerveja_pilsen');
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url) VALUES ('Cerveja IPA', 80, 8, 7.0, 'img_url_cerveja_ipa');
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url) VALUES ('Coca-Cola', 50, 5, 4.0, 'img_url_coca_cola');
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url) VALUES ('Guaraná Antártica', 60, 6, 4.0, 'img_url_guarana');
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url) VALUES ('Amendoim', 200, 20, 3.0, 'img_url_amendoim');
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url) VALUES ('Batata Frita', 150, 15, 5.0, 'img_url_batata_frita');

-- Inserir clientes
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Vinicius', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Maila', false);

-- Inserir mesas
INSERT INTO tb_table (number, status) VALUES (0, 'RETIRADA');
INSERT INTO tb_table (number, status) VALUES (1, 'LOCAL');

-- Inserir usuários
INSERT INTO users (id, login, password, role) VALUES ('1', 'admin', 'password', 'ADMIN');
INSERT INTO users (id, login, password, role) VALUES ('2', 'user', 'password', 'USER');

-- Inserir e-mails enviados
INSERT INTO tb_email_send (email, name) VALUES ('vinicius@example.com', 'Vinicius Lima');

-- Inserir pedidos de clientes
INSERT INTO tb_customer_order (customer_id, table_id, status) VALUES (1, 1, 'Aberta');
INSERT INTO tb_customer_order (customer_id, table_id, status) VALUES (2, 2, 'Fechada');

-- Inserir itens de pedidos
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 1, 2); -- 2 Cervejas Pilsen
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 3, 1); -- 1 Coca-Cola
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (2, 2, 3); -- 3 Cervejas IPA
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (2, 5, 2); -- 2 Amendoins

-- Associar produtos a categorias
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1); -- Cerveja Pilsen -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1); -- Cerveja IPA -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 1); -- Coca-Cola -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 1); -- Guaraná Antártica -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 2); -- Amendoim -> Comidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 2); -- Batata Frita -> Comidas