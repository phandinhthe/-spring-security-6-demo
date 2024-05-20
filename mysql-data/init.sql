use `spring_security`;
create table `user` (
  `id` int not null auto_increment,
  `name` varchar(255) not null,
  `username` varchar(255) not null,
  `mail_address` varchar(255) not null,
  `password` varchar(255) not null,
  `authorities` text,
  primary key(`id`)
);

