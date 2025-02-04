--
-- Inserindo dados de emails para ser disparado o alerta do estoque
INSERT INTO tb_email_send (name, email) VALUES ('Vinicius Lima', 'viniciuslimaes@hotmail.com');
--INSERT INTO tb_email_send (name, email) VALUES ('Maila Gomes', 'mailagomes04@gmail.com');
--INSERT INTO tb_email_send (name, email) VALUES ('Gleriston Castro', 'xpanium@gmail.com');
--
--
-- Inserindo dados de categorias
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Bebidas', NOW(), NOW());
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Comidas', NOW(), NOW());
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Sobremesas', NOW(), NOW());



-- Inserindo dados de clientes
INSERT INTO tb_customer (name, is_anonymous) VALUES ('João Silva', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Maria Oliveira', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Carlos Pereira', false);
--
-- Inserindo dados de mesas
INSERT INTO tb_table (number, status) VALUES (0, 'RETIRADA');
INSERT INTO tb_table (number, status) VALUES (1, 'LOCAL');
INSERT INTO tb_table (number, status) VALUES (2, 'LOCAL');

-- Inserindo dados de pedidos
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (1, 1);
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (2, 2);
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (3, 3);

-- Inserindo dados de produtos
INSERT INTO tb_product (name, stock, low_stock_threshold, unit_price, img_url, created_at, updated_at)
VALUES
    ('Cerveja Heineken', 100, 5, 5.99, 'https://example.com/heineken.jpg', NOW(), NOW()),
    ('Cerveja Stella Artois', 80, 5, 6.49, 'https://example.com/stella.jpg', NOW(), NOW()),
    ('Cerveja Budweiser', 120, 5, 4.99, 'https://example.com/budweiser.jpg', NOW(), NOW()),
    ('Refrigerante Coca-Cola', 200, 10, 3.99, 'https://example.com/coca-cola.jpg', NOW(), NOW()),
    ('Refrigerante Guaraná Antarctica', 150, 10, 3.49, 'https://example.com/guarana.jpg', NOW(), NOW());

-- Associando produtos às categorias (relacionamento muitos-para-muitos)
-- Use os IDs reais dos produtos e categorias após a inserção
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1); -- Cerveja Heineken -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1); -- Cerveja Stella Artois -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 1); -- Cerveja Budweiser -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 1); -- Refrigerante Coca-Cola -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 1); -- Refrigerante Guaraná Antarctica -> Bebidas

-- Inserindo itens de pedidos
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (2, 3, 3);