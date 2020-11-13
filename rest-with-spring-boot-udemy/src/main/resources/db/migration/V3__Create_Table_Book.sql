CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(80) NOT NULL,
  `launch_date` date NOT NULL,
  `price` double NOT NULL,
  `title` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
)