-- Inserindo dados de clientes
INSERT INTO tb_customer (name, is_anonymous) VALUES ('João Silva', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Maria Oliveira', false);
INSERT INTO tb_customer (name, is_anonymous) VALUES ('Carlos Pereira', false);

-- Inserindo dados de mesas
INSERT INTO tb_table (number, status) VALUES (0, 'RETIRADA');
INSERT INTO tb_table (number, status) VALUES (1, 'LOCAL');
INSERT INTO tb_table (number, status) VALUES (2, 'LOCAL');

-- Inserindo dados de produtos
INSERT INTO tb_product (name, unit_price, category) VALUES ('Produto A', 29.99, 'Bebidas');
INSERT INTO tb_product (name, unit_price, category) VALUES ('Produto B', 49.99, 'Comidas');
INSERT INTO tb_product (name, unit_price, category) VALUES ('Produto C', 19.99, 'Sobremesas');

-- Inserindo dados de pedidos
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (1, 1);
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (2, 2);
INSERT INTO tb_customer_order (table_id, customer_id) VALUES (3, 3);

-- Inserindo itens de pedidos
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO tb_order_item (order_id, product_id, quantity) VALUES (2, 3, 3);