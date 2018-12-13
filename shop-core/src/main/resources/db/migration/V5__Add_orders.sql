insert into order_entity(id, user_id, total, address_string,
                         status_of_delivery, status_of_payment, type_of_delivery, type_of_payment)

VALUES (1, 2, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (2, 2, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (3, 2, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (4, 3, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (5, 3, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (6, 3, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (7, 4, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (8, 4, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH'),
       (9, 4, 1, 'address', 'WAITING_FOR_SHIPMENT', 'PAID', 'POSTAMAT', 'NONCASH');





insert into order_entity_product(order_entity_id, list_of_products_key, list_of_products)
values (1, 1, 1),
       (1, 2, 1),
       (1, 3, 1),
       (2, 4, 1),
       (2, 5, 1),
       (2, 6, 1),
       (3, 7, 1),
       (3, 8, 1),
       (3, 9, 1),
       (4, 10, 1),
       (4, 11, 1),
       (4, 12, 1),
       (5, 13, 1),
       (5, 14, 1),
       (5, 15, 1),
       (6, 16, 1),
       (6, 17, 1),
       (6, 18, 1),
       (7, 19, 1),
       (7, 20, 1),
       (7, 21, 1),
       (8, 22, 1),
       (8, 23, 1),
       (8, 24, 1);