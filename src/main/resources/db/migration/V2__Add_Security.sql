drop table messages;

drop table accounts;

create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);

create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `contents` text NOT NULL,
  `author` varchar_ignorecase(50) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

ALTER TABLE `messages` ADD FOREIGN KEY (`author`) REFERENCES `users`(`username`);

