create table invitations(
    `id` bigint NOT NULL AUTO_INCREMENT,
     `email` varchar_ignorecase(150) NOT NULL,
     `token` varchar_ignorecase(200) NOT NULL,
     `community` bigint NOT NULL,
     `author` varchar_ignorecase(150) NOT NULL,
     PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

ALTER TABLE `invitations` ADD FOREIGN KEY (`community`) REFERENCES `communities`(`id`);
ALTER TABLE `invitations` ADD FOREIGN KEY (`author`) REFERENCES `users`(`username`);

ALTER TABLE users ADD COLUMN invitation bigint NULL;
ALTER TABLE `users` ADD FOREIGN KEY (`invitation`) REFERENCES `invitations`(`id`);
