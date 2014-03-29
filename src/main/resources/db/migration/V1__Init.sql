CREATE TABLE `accounts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `contents` text NOT NULL,
  `author` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`author`) REFERENCES `accounts` (`id`)
) DEFAULT CHARSET=utf8;

