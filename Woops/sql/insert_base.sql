INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'Bernard', 'Cherbonneau', 'bernard', 'bernard', 'dev' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'Claude', 'Aubry', 'claude', 'claude', 'dev' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'root', 'root', 'woops', 'woops', 'admin' );

INSERT INTO `BreakdownElement` (`prefix`, `name`, `kind`) VALUES ('T', 'Test', 1);

INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Analyser le probl?me', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Identifier cas d''utilisation', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Sp?cifier cas d''utilisation', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Organiser exigences', '', 2, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Analyser architecture', '', 2, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Programmer la solution', 'Impl?menter les cas d''utilisation', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Programmer les tests d?veloppeur', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'D?rouler l''it?ration', '', 2, 'created', 1 );



INSERT INTO `Activity` (`name`, `state`, `bde` ) VALUES  ( 'LIBRE COMME L AIR !', 'created', 1 );