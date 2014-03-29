create table communities(
    `id` bigint NOT NULL AUTO_INCREMENT,
     `name` varchar_ignorecase(100) NOT NULL,
     PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

ALTER TABLE users ADD COLUMN community bigint NULL;

ALTER TABLE `users` ADD FOREIGN KEY (`community`) REFERENCES `communities`(`id`);
