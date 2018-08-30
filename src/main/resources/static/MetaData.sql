-- Adminer 4.6.0 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

INSERT INTO `accounts` (`id`, `acc_no`, `acc_type`, `created_at`, `updated_at`, `bank_id`, `user_id`) VALUES
(1,	1001,	0,	'2018-08-30 16:24:09',	NULL,	1,	1),
(2,	1002,	0,	'2018-08-30 16:32:32',	NULL,	1,	1),
(3,	1003,	1,	'2018-08-30 16:24:09',	NULL,	2,	1),
(4,	1004,	1,	'2018-08-30 16:24:09',	NULL,	2,	1),
(5,	1001,	0,	'2018-08-30 16:24:09',	NULL,	3,	1),
(6,	1002,	0,	'2018-08-30 16:32:32',	NULL,	3,	1),
(7,	1003,	1,	'2018-08-30 16:24:09',	NULL,	4,	1),
(8,	1004,	1,	'2018-08-30 16:24:09',	NULL,	4,	1);

INSERT INTO `banks` (`id`, `address`, `corpId`, `created_at`, `name`, `updated_at`, `password_required`, `user_name_required`) VALUES
(1,	'Bangalore',	123,	'2018-08-30 12:26:20',	'Bank 1',	NULL,	CONV('1', 2, 10) + 0,	CONV('1', 2, 10) + 0),
(2,	'Mysore',	132,	'2018-08-30 12:57:43',	'Bank 2',	NULL,	CONV('1', 2, 10) + 0,	CONV('1', 2, 10) + 0),
(3,	'Belgum',	143,	'2018-08-30 16:17:28',	'Bank 3',	NULL,	CONV('1', 2, 10) + 0,	CONV('1', 2, 10) + 0),
(4,	'Bangalore',	154,	'2018-08-30 16:17:55',	'Bank 4',	NULL,	CONV('1', 2, 10) + 0,	CONV('1', 2, 10) + 0);

INSERT INTO `transactions` (`id`, `transaction_amount`, `created_at`, `transaction_id`, `type`, `updated_at`, `account_id`) VALUES
(1,	2000.00,	'2018-08-30 16:24:47',	'10011',	0,	NULL,	1),
(2,	1000.00,	'2018-08-30 16:43:53',	'10021',	1,	NULL,	2),
(3,	2000.00,	'2018-08-30 16:24:47',	'10031',	0,	NULL,	3),
(4,	100.00,	'2018-08-30 16:43:53',	'10041',	1,	NULL,	4),
(5,	234.00,	'2018-08-30 16:24:47',	'10051',	0,	NULL,	5),
(6,	432.00,	'2018-08-30 16:43:53',	'10061',	1,	NULL,	6),
(7,	1111.00,	'2018-08-30 16:24:47',	'10071',	0,	NULL,	7),
(8,	101100.00,	'2018-08-30 16:43:53',	'10081',	1,	NULL,	8),
(9,	12.00,	'2018-08-30 16:24:47',	'10012',	0,	NULL,	1),
(10,	123.00,	'2018-08-30 16:43:53',	'10022',	1,	NULL,	2),
(11,	111.00,	'2018-08-30 16:24:47',	'10032',	0,	NULL,	3),
(12,	112.00,	'2018-08-30 16:43:53',	'10042',	1,	NULL,	4);

INSERT INTO `users` (`id`, `created_at`, `password`, `updated_at`, `user_name`, `authToken`) VALUES
(1,	'2018-08-30 12:04:46',	'password',	'2018-08-30 16:41:59',	'arun',	'6c7463d5-10aa-44c1-a517-c752aa47cec0'),
(2,	'2018-08-30 16:55:23',	'password',	NULL,	'raju',	NULL);

-- 2018-08-30 11:29:10