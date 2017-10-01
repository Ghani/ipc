INSERT INTO `role` VALUES (1,'ADMIN');



INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (50000,'root','',NULL);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (0,'Green TAG','',50000);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (1,'RED TAGG','',50000);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (2,'Well Control','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (3,'BOP','',2);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (4,'Stuffing box','',2);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (5,'Rises','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (6,'Flanges','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (7,'Gages','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (8,'BHA','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (9,'Lifting Gears Slings','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (10,'TREATING IRONS','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (11	,'SHORT JOINT','',10);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (12	,'LONG JOINT','',10);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (13,'Swivel','',10);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (14	,'Tee & Elbow And Y JOINT','',10);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (15,'Plug Valves','',10);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (16,'Check Valves','',10);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (17,'CTU','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (18,'CTU-24','',17);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (19,'Pump','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (20,'Pump-16','',19);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (21,'N2 Uint','',0);
INSERT INTO category (`category_id`,`name`,`description`,`parent`) VALUES (22,'N2 Uint -1','',21);
