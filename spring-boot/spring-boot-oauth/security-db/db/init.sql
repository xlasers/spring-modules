-- password:1234
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_enabled` tinyint(1) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  `is_expired` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO security_demo.`user`
(id, username, password, is_enabled, is_locked, is_expired)
VALUES(1, 'admin', '$2a$10$TEgBeQtillRKk36lDOVCEeCQFdWqaNK1YlRUZfSKW8HOdXocYkbsm', 1, 0, 0);
INSERT INTO security_demo.`user`
(id, username, password, is_enabled, is_locked, is_expired)
VALUES(2, 'dev', '$2a$10$TCv100F1Xu.mMoxKAHa.HeaMJus49mlfwjuUJGWnLWQWHjDNZKxdi', 1, 0, 0);
INSERT INTO security_demo.`user`
(id, username, password, is_enabled, is_locked, is_expired)
VALUES(3, 'user', '$2a$10$tgm9/1L1C5rby2OfUoOlu.PiTe0nZcJJpNPNRPkEylkVALZdMyNSm', 1, 0, 0);

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `desc` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO security_demo.`role`
(id, name, `desc`)
VALUES(1, 'ROLE_admin', '管理员');
INSERT INTO security_demo.`role`
(id, name, `desc`)
VALUES(2, 'ROLE_dev', '开发者');
INSERT INTO security_demo.`role`
(id, name, `desc`)
VALUES(3, 'ROLE_user', '用户');


CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO security_demo.user_role
(id, u_id, r_id)
VALUES(1, 1, 1);
INSERT INTO security_demo.user_role
(id, u_id, r_id)
VALUES(2, 1, 2);
INSERT INTO security_demo.user_role
(id, u_id, r_id)
VALUES(3, 2, 2);
INSERT INTO security_demo.user_role
(id, u_id, r_id)
VALUES(4, 3, 3);
