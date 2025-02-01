
-- Inserindo dados de emails para ser disparado o alerta do estoque
INSERT INTO tb_email_send (name, email) VALUES ('Vinicius Lima', 'viniciuslimaes@hotmail.com');
--INSERT INTO tb_email_send (name, email) VALUES ('Maila Gomes', 'mailagomes04@gmail.com');
--INSERT INTO tb_email_send (name, email) VALUES ('Gleriston Castro', 'xpanium@gmail.com');


-- Inserindo dados de categorias
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Bebidas', NOW(), NOW());
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Comidas', NOW(), NOW());
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Sobremesas', NOW(), NOW());

-- Inserindo dados de produtos
INSERT INTO tb_product (name, unit_price, img_url, stock, created_at, updated_at) VALUES ('Heineken', 14.99, 'url_imagem_a', 8, NOW(), NOW());
INSERT INTO tb_product (name, unit_price, img_url, stock, created_at, updated_at) VALUES ('Brahma', 9.99, 'url_imagem_b', 7, NOW(), NOW());
INSERT INTO tb_product (name, unit_price, img_url, stock, created_at, updated_at) VALUES ('Budweiser', 10.00, 'url_imagem_c', 4, NOW(), NOW());

-- Associando produtos às categorias (relacionamento muitos-para-muitos)
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1); -- Produto A -> Bebidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2); -- Produto B -> Comidas
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 3); -- Produto C -> Sobremesas

-- Inserindo dados de clientes
INSERT INTO tb_customer (name, is_anonymous) VALUES ('João Silva', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Maria Oliveira', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Carlos Pereira', false);

-- Inserindo dados de mesas
INSERT INTO tb_table (number, status) VALUES (0, 'RETIRADA');
INSERT INTO tb_table (number, status) VALUES (1, 'LOCAL');
INSERT INTO tb_table (number, status) VALUES (2, 'LOCAL');

-- Inserindo dados de pedidos
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (1, 1);
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (2, 2);
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (3, 3);

-- Inserindo itens de pedidos
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (2, 3, 3);