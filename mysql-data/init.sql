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

insert into `user`
  (`name`, `password`, `username`, `mail_address`, `authorities`)
values
  ("terry","123","phandinhthe","phandinhthe1991@gmail.com","ADMIN"),
	("the","123","terry_phan1991","terry_phan1991@gmail.com","ADMIN"),
	("phan","123","trn.frank2802","trn.frank2802@gmail.com","ADMIN"),
	("trn","123","trn","trn.frank2802@yahoo.com","GUEST");

