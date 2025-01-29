-- Inserindo dados de clientes
INSERT INTO tb_customer (id, name, is_anonymous) VALUES (1, 'João Silva', false);
INSERT INTO tb_customer (id, name, is_anonymous) VALUES (2, 'Maria Oliveira', true);
INSERT INTO tb_customer (id, name, is_anonymous) VALUES (3, 'Carlos Pereira', false);

-- Inserindo dados de mesas
INSERT INTO tb_table (number, status) VALUES (0, 'RETIRADA');
INSERT INTO tb_table (number, status) VALUES (1, 'LOCAL');
INSERT INTO tb_table (number, status) VALUES (2, 'LOCAL');

-- Inserindo dados de produtos
INSERT INTO tb_product (id, name, unit_price, category) VALUES (1, 'Produto A', 29.99, 'Bebidas');
INSERT INTO tb_product (id, name, unit_price, category) VALUES (2, 'Produto B', 49.99, 'Comidas');
INSERT INTO tb_product (id, name, unit_price, category) VALUES (3, 'Produto C', 19.99, 'Sobremesas');

-- Inserindo dados de pedidos
INSERT INTO tb_customer_order (id, table_id, customer_id) VALUES (1, 1, 1);
INSERT INTO tb_customer_order (id, table_id, customer_id) VALUES (2, 2, 2);
INSERT INTO tb_customer_order (id, table_id, customer_id) VALUES (3, 3, 3);

-- Inserindo itens de pedidos
INSERT INTO tb_order_item (id, order_id, product_id, quantity) VALUES (1, 1, 1, 2);
INSERT INTO tb_order_item (id, order_id, product_id, quantity) VALUES (2, 1, 2, 1);
INSERT INTO tb_order_item (id, order_id, product_id, quantity) VALUES (3, 2, 3, 3);