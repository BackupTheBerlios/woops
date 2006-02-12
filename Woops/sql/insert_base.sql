INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'Bernard', 'Cherbonneau', 'bernard', 'bernard', 'dev' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'Claude', 'Aubry', 'claude', 'claude', 'dev' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'root', 'root', 'woops', 'woops', 'admin' );

INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Analyser le probl?me', '', 1, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Identifier cas d''utilisation', '', 1, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Sp?cifier cas d''utilisation', '', 1, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Organiser exigences', '', 2, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Analyser architecture', '', 2, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Programmer la solution', 'Impl?menter les cas d''utilisation', 1, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Programmer les tests d?veloppeur', '', 1, 'created' );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'D?rouler l''it?ration', '', 2, 'created' );



INSERT INTO `Activity` (`name`, `details`, `state` ) VALUES  ( 'LIBRE COMME L AIR !', '', 'created' );