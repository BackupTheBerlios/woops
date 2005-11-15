DROP TABLE IF EXISTS `woops`.`chocolat`;
CREATE TABLE  `woops`.`chocolat` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `NOM` varchar(45) NOT NULL default '',
  `FABRICANT` varchar(45) NOT NULL default '',
  `CALORIE` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO Chocolat VALUES (1,'Chocolat1','Fab1',5);
INSERT INTO Chocolat VALUES (2,'Chocolat2','Fab2',7);
INSERT INTO Chocolat VALUES (3,'Chocolat3','Fab3',10);