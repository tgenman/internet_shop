insert into product (id, active, title, category, price, quantity, volume, weight, filename)
values
(1, true, 'Yandex Station', 'Voice Assistant', 150, 15, 1, 3, 'non'),    #TODO SIZE 141x231x141
(2, true, 'Fitbit Versa Gray/Silver Aluminium', 'Smartwatch', 148, 3, 1, 1, 'non'),
(3, true, 'Samsung Gear Sport - Black ', 'Smartwatch', 159, 18, 1, 1, 'non');


insert into product_parameter (product_id, parameters, parameters_key)
values
(1, 'Some', 's'),
(2, 'Some', 's'),
(3, 'Some', 's');