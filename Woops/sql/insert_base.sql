INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'Bernard', 'Cherbonneau', 'bernard', 'bernard', '2' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'Claude', 'Aubry', 'claude', 'claude', '2' );
INSERT INTO `User` (`firstName`, `lastName`, `login`, `password`, `role` ) VALUES  ( 'root', 'root', 'woops', 'woops', '1' );

INSERT INTO `BreakdownElement` (`prefix`, `name`, `kind`) VALUES ('T', 'Test', 1);
INSERT INTO `BreakdownElement` (`prefix`, `name`, `kind`) VALUES ('W', 'Woops', 1);

INSERT INTO `UserBDE` (`bde`, `user`) SELECT b.id, u.id FROM `BreakdownElement` as b, `User` as u WHERE `prefix`='W' AND `lastName`='Cherbonneau' AND `firstName`='Bernard';
INSERT INTO `UserBDE` (`bde`, `user`) SELECT b.id, u.id FROM `BreakdownElement` as b, `User` as u WHERE `prefix`='T' AND `lastName`='Cherbonneau' AND `firstName`='Bernard';

INSERT INTO `UserBDE` (`bde`, `user`) SELECT b.id, u.id FROM `BreakdownElement` as b, `User` as u WHERE `prefix`='W' AND `lastName`='Aubry' AND `firstName`='Claude';
INSERT INTO `UserBDE` (`bde`, `user`) SELECT b.id, u.id FROM `BreakdownElement` as b, `User` as u WHERE `prefix`='T' AND `lastName`='Aubry' AND `firstName`='Claude';

INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Analyser le probleme', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Identifier cas d''utilisation', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Specifier cas d''utilisation', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Organiser exigences', '', 2, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Analyser architecture', '', 2, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Realiser le CU_part_2', 'Afficher les activites a realiser', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Programmer la solution', 'Implementer les cas d''utilisation', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Programmer les tests developpeur', '', 1, 'created', 1 );
INSERT INTO `Activity` (`name`, `details`, `user`, `state`, `bde` ) VALUES  ( 'Derouler l''iteration', '', 2, 'created', 1 );

INSERT INTO `ActivitySequence` (`predecessor`,`successor`,`linkType`) VALUES ( 1,2,1 );


INSERT INTO `Activity` (`name`, `state`, `bde` ) VALUES  ( 'Programmer les tests unitaires !', 'created', 1 );