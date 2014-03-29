CREATE TABLE `actions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar_ignorecase(100) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

ALTER TABLE `messages` ADD COLUMN `action` bigint NULL;

ALTER TABLE `messages` ADD FOREIGN KEY (`action`) REFERENCES `actions`(`id`);
