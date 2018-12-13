insert into category(id, category_name)
values
(1, 'All'),
(2, 'Voice Assistant'),
(3, 'Smartwatch'),
(4, 'Smartphone'),
(5, 'Ultrabook'),
(6, 'Processor'),
(7, 'Micro PC'),
(8, 'Headphone');

insert into product(id, active, category_id, title, brand, price, color, quantity, size, weight, day_of_warranty, filename)
values
(1, true, 2, 'Station', 'Yandex',  150, 'Black', 15, '141x231x141mm', 3, 14, 'yandex.jpg'),
(2, true, 3, 'Versa', 'Fitbit', 148, 'Gray/Silver Aluminium', 3, '-', 1, 14, 'fitbit.jpg'),
(3, true, 3, 'Watch Series 3', 'Apple', 378, 'Space Gray', 20, '-', 1, 60, 'apple_watch.jpg'),
(4, true, 3, 'Vívoactive 3', 'Garmin', 199, 'White', 10, '-', 1, 30, 'garmin.jpg'),
(5, true, 3, 'Watch 2 Classic', 'Huawei', 184, 'Black', 3, '-', 1, 30, 'huawai_watch.jpg'),
(6, true, 3, 'Gen 4 Explorist HR', 'Fossil', 255, 'Stainless Steel', 4, '-', 1, 30, 'Fossil.jpg'),
(7, true, 2, 'Echo Dot (2nd Generation)', 'Amazon', 30, 'Black', 2, '32x84x84mm', 1, 30, 'alexa_echo.jpg'),
(8, true, 4, 'Galaxy S8', 'Samsung', 359, 'Black', 10, '5.9x2.7x0.3inches', 1, 30, 'galaxi8.jpg'),
(9, true, 4, 'Helio P25 Octa Core', 'Elephone', 245, 'Black', 10, '6.1x3x0.2inches', 1, 30, '111.jpg'),
(10, true, 4, 'iPhone 7 Plus', 'Apple', 760, 'White', 10, '6.2x0.3x3.1inches', 1, 30, 'iphone7.jpg'),
(11, true, 4, 'Mate 20 Pro', 'HUAWEI', 200, 'Black', 10, '2.9x0.3x6.2inches', 1, 30, 'huawei7.jpg'),
(12, true, 4, 'S7', 'Ulefone', 50, 'Gold', 10, '145.8*70.8*10.8mm', 1, 30, 'ulefon.jpg'),
(13, true, 5, 'Zenbook UX310UQ-FC134T', 'ASUS', 882, 'Gray', 10, '223x323x18mm', 2, 30, 'ultrabooZenbook.jpg'),
(14, true, 5, 'Yoga 730-13IKB', 'Lenovo', 1290, 'Grey', 10, '216x307x14', 2, 30, 'ultrabookYoga.jpg'),
(15, true, 5, 'Swift 1 SF114-32-P2FA', 'Acer', 500, 'Gold', 10, '228x323x14', 1, 30, 'ultrabookSwift.jpg'),
(16, true, 5, 'ThinkPad X1 Carbon 6', 'Lenovo', 1800, 'Black', 10, '217x323x16', 1, 30, 'ultrabookLenovo.jpg'),
(17, true, 5, 'XPS 13 9360-3614', 'Dell', 1200, 'Grey', 10, '200x304x15', 1, 30, 'ultrabookDell.jpg'),
(18, true, 6,  'Ryzen Threadripper 1900X BOX', 'AMD', 400, '', 10, '-', 1, 30, 'amdRyzen.jpg'),
(19, true, 6, 'Core i9-7960X BOX', 'Intel', 2000, '', 10, '-', 1, 30, 'processorCorei9-7960XBOX.jpg'),
(20, true, 6, 'Xeon E5-1630 v4 OEM', 'Intel', 500, '', 10, '', 1, 30, 'processorXeon.jpg'),
(21, true, 6, 'Core i7-7740X OEM', 'Intel', 350, '', 10, '-', 1, 30, 'processorCorei7.jpg'),
(22, true, 7, 'Tinker Board', 'ASUS', 90, '', 10, '85x54', 1, 30, 'microAsus.jpg'),
(23, true, 7, 'Pi 3 Model B+', 'Raspberry', 50, '', 10, '85x56', 1, 30, 'microRas.jpg'),
(24, true, 7, 'PI PC', 'Orange', 25, '', 10, '85x55', 1, 30, 'microOrange.jpg'),
(25, true, 8, 'M2 AEBT', 'Sennheiser', 300, 'Beige', 10, '-', 1, 30, 'headSen.jpg'),
(26, true, 8, 'SB Jam', 'Creative', 50, 'Black', 10, '-', 1, 30, 'headCreat.jpg'),
(27, true, 8, 'Freebuds CM-H1', 'Huawei', 140, 'Black', 10, '-', 1, 30, 'headFreebuds.jpg'),
(28, true, 8, 'HA-S20BT-H', 'JVC', 30, 'White', 10, '-', 1, 30, 'headJVC.jpg'),
(29, true, 8, 'Major III', 'MARSHALL', 180, 'Black', 10, '-', 1, 30, 'headMarshal.jpg'),
(30, true, 3, 'Gear Sport', 'Samsung', 159, 'Black', 18, '-', 1, 30, 'gearsport.jpg');