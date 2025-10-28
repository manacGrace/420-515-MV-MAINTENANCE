
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `test`;

DROP TABLE IF EXISTS `persons`;
CREATE TABLE IF NOT EXISTS `persons` (
  `personid` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


INSERT INTO `persons` (`personid`, `lastName`, `age`, `genre`) VALUES
	(1, 'Neal', 1, 'Male'),
	(2, 'Phil', 123, 'Female'),
	(3, 'Amadou', 245, 'NonBinaire'),
	(4, 'Pheng', 123, 'Female'),
	(5, 'Smith', 32, 'Male'),
	(6, 'Jones', 45, 'Female'),
	(7, 'Taylor', 29, 'Male'),
	(8, 'Brown', 36, 'Female'),
	(9, 'Davis', 54, 'Male'),
	(10, 'Miller', 22, 'Female'),
	(11, 'Wilson', 39, 'Male'),
	(12, 'Moore', 27, 'Female'),
	(13, 'Anderson', 50, 'Male'),
	(14, 'Thomas', 33, 'Female'),
	(15, 'Jackson', 40, 'Male'),
	(16, 'White', 28, 'Female'),
	(17, 'Harris', 19, 'Male'),
	(18, 'Martin', 41, 'Female'),
	(19, 'Thompson', 26, 'Male'),
	(20, 'Garcia', 38, 'Female'),
	(21, 'Martinez', 21, 'Male'),
	(22, 'Roberts', 47, 'Female'),
	(23, 'Gonzalez', 30, 'Male'),
	(24, 'King', 25, 'Female'),
	(25, 'Lopez', 48, 'Male'),
	(26, 'Hill', 35, 'Female'),
	(27, 'Scott', 22, 'Male'),
	(28, 'Adams', 55, 'Female'),
	(29, 'Baker', 31, 'Male'),
	(30, 'Nelson', 37, 'Female'),
	(31, 'Carter', 43, 'Male'),
	(32, 'Mitchell', 20, 'Female'),
	(33, 'Perez', 34, 'Male'),
	(34, 'Robinson', 23, 'Female'),
	(35, 'Murphy', 29, 'Male'),
	(36, 'Cook', 50, 'Female'),
	(37, 'Rogers', 19, 'Male'),
	(38, 'Perry', 41, 'Female'),
	(39, 'Gray', 27, 'Male'),
	(40, 'James', 33, 'Female'),
	(41, 'Watson', 28, 'Male'),
	(42, 'Brooks', 44, 'Female'),
	(43, 'Kelly', 51, 'Male'),
	(44, 'Sanders', 26, 'Female'),
	(45, 'Price', 39, 'Male'),
	(46, 'Bennett', 32, 'Female'),
	(47, 'Wood', 45, 'Male'),
	(48, 'Barnes', 23, 'Female'),
	(49, 'Ross', 29, 'Male'),
	(50, 'Henderson', 38, 'Female'),
	(51, 'Cole', 53, 'Male');

	DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=LATIN1_SWEDISH_CI;


DROP TABLE IF EXISTS `series`;
CREATE TABLE `series` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `nbepisodes` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `views` BIGINT(20) DEFAULT NULL,
  `nbRate` BIGINT(20) DEFAULT 0,
  `totalVote` BIGINT(20) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


INSERT INTO `series` (`id`, `title`, `genre`, `nbepisodes`, `score`, `views`, `nbRate`, `totalVote`) VALUES
(1, 'Breaking Bad', 'Crime', 62, 4, 75000000, 1000, 4500),
(2, 'Game of Thrones', 'Fantasy', 73, 3, 95000000, 1200, 3600),
(3, 'Stranger Things', 'Sci-Fi', 34, 1, 88000000, 800, 1600),
(4, 'The Office', 'Comedy', 201, 2, 132000000, 1500, 3000),
(5, 'Friends', 'Comedy', 236, 5, 140000000, 2000, 10000),
(6, 'The Simpsons', 'Animation', 750, 0, 89000000, 1300, 1300),
(7, 'Rick and Morty', 'Animation', 61, 4, 72000000, 900, 3600),
(8, 'Better Call Saul', 'Crime', 63, 3, 68000000, 800, 2400),
(9, 'The Mandalorian', 'Sci-Fi', 24, 2, 90000000, 700, 1400),
(10, 'House of the Dragon', 'Fantasy', 10, 5, 56000000, 400, 2000),
(11, 'Loki', 'Sci-Fi', 12, 1, 49000000, 300, 300),
(12, 'WandaVision', 'Sci-Fi', 9, 0, 45000000, 200, 0),
(13, 'The Boys', 'Action', 24, 4, 70000000, 500, 2000),
(14, 'Peaky Blinders', 'Crime', 36, 2, 64000000, 600, 1200),
(15, 'Sherlock', 'Crime', 13, 3, 52000000, 400, 1200),
(16, 'The Crown', 'Drama', 50, 1, 40000000, 300, 300),
(17, 'The Witcher', 'Fantasy', 24, 5, 95000000, 700, 3500),
(18, 'Arcane', 'Animation', 9, 0, 87000000, 250, 0),
(19, 'Narcos', 'Crime', 30, 3, 78000000, 600, 1800),
(20, 'Dark', 'Sci-Fi', 26, 2, 72000000, 500, 1000),
(21, 'Squid Game', 'Thriller', 9, 4, 125000000, 1500, 6000),
(22, 'Money Heist', 'Crime', 41, 5, 132000000, 1600, 8000),
(23, 'Black Mirror', 'Sci-Fi', 27, 1, 69000000, 400, 400),
(24, 'The Last of Us', 'Drama', 9, 2, 18500000, 200, 400),
(25, 'Chernobyl', 'Drama', 5, 3, 9500000, 100, 300),
(26, 'True Detective', 'Crime', 24, 0, 7200000, 50, 0),
(27, 'Seinfeld', 'Comedy', 180, 4, 118000000, 1200, 4800),
(28, 'How I Met Your Mother', 'Comedy', 208, 1, 121000000, 1100, 1100),
(29, 'Brooklyn Nine-Nine', 'Comedy', 153, 3, 119000000, 900, 2700),
(30, 'Lost', 'Drama', 121, 2, 38200000, 500, 1000),
(31, 'Gotham', 'Crime', 100, 3, 47000000, 400, 1200),
(32, 'Vikings', 'Action', 89, 4, 56000000, 600, 2400),
(33, 'The Flash', 'Sci-Fi', 151, 3, 72000000, 700, 2100),
(34, 'Arrow', 'Action', 170, 2, 65000000, 600, 1200),
(35, 'Supernatural', 'Fantasy', 327, 4, 88000000, 800, 3200),
(36, 'The Umbrella Academy', 'Action', 30, 4, 52000000, 300, 1200),
(37, 'Daredevil', 'Action', 39, 3, 42000000, 250, 750),
(38, 'Jessica Jones', 'Action', 39, 3, 40000000, 250, 750),
(39, 'Agents of S.H.I.E.L.D.', 'Action', 136, 2, 46000000, 300, 600),
(40, 'The Expanse', 'Sci-Fi', 62, 4, 48000000, 400, 1600),
(41, 'Lost in Space', 'Sci-Fi', 43, 3, 30000000, 200, 600),
(42, 'Fringe', 'Sci-Fi', 100, 3, 37000000, 250, 750),
(43, 'Battlestar Galactica', 'Sci-Fi', 75, 4, 42000000, 300, 1200),
(44, 'Stargate SG-1', 'Sci-Fi', 214, 3, 52000000, 350, 1050),
(45, 'Smallville', 'Action', 218, 3, 38000000, 300, 900),
(46, 'Dexter', 'Crime', 96, 4, 64000000, 500, 2000),
(47, 'House', 'Drama', 177, 5, 98000000, 800, 4000),
(48, 'Mad Men', 'Drama', 92, 4, 67000000, 500, 2000),
(49, 'Homeland', 'Thriller', 96, 3, 45000000, 300, 900),
(50, 'Prison Break', 'Action', 90, 4, 58000000, 400, 1600);


DROP TABLE IF EXISTS `Playlist`;
CREATE TABLE `Playlist` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `serieId` BIGINT(20) NOT NULL,
  `userId` BIGINT(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `Playlist` (`serieID`, `userId`)
VALUES (44, 1), (45, 1), (46, 1) ,(50,1);



DROP TABLE IF EXISTS `Vote`;
CREATE TABLE `Vote` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `serieId` BIGINT(20) NOT NULL,
  `userId` BIGINT(11) NOT NULL,
  `score` INT(11) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;




