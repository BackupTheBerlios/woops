INSERT INTO `User` (`firstName`, `lastName`, `login`, `password` ) VALUES  ( 'Bernard', 'Cherbonneau', 'bernard', 'bernard' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password` ) VALUES  ( 'Claude', 'Aubry', 'claude', 'claude' );


INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Analyser le problème', '', 1, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Identifier cas d''utilisation', '', 1, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Spécifier cas d''utilisation', '', 1, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Organiser exigences', '', 2, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Analyser architecture', '', 2, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Programmer la solution', 'Implémenter les cas d''utilisation', 1, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Programmer les tests développeur', '', 1, 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state` ) VALUES  ( 'Dérouler l''itération', '', 2, 1 );