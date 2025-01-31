-- Inserindo dados de categorias
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Bebidas', NOW(), NOW());
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Comidas', NOW(), NOW());
INSERT INTO tb_category (name, created_at, updated_at) VALUES ('Sobremesas', NOW(), NOW());

-- Inserindo dados de produtos
INSERT INTO tb_product (name, unit_price, img_url, created_at, updated_at) VALUES ('Produto A', 29.99, 'url_imagem_a', NOW(), NOW());
INSERT INTO tb_product (name, unit_price, img_url, created_at, updated_at) VALUES ('Produto B', 49.99, 'url_imagem_b', NOW(), NOW());
INSERT INTO tb_product (name, unit_price, img_url, created_at, updated_at) VALUES ('Cerveja', 10.00, 'url_imagem_c', NOW(), NOW());

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