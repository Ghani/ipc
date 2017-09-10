INSERT INTO `role` VALUES (1,'ADMIN');



CREATE TABLE category(
        category_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(20) NOT NULL,
        description VARCHAR(100) NOT NULL,
        parent INT DEFAULT NULL
);


INSERT INTO category 
VALUES
(1,'ELECTRONICS','ELECTRONICS',NULL),
(2,'TELEVISIONS','TELEVISIONS',1),
(3,'TUBE','TUBE',2),
(4,'LCD','LCD',2),
(5,'PLASMA','PLASMA',2),
(6,'PORTABLE ELECTRONICS','PORTABLE ELECTRONICS',1),
(7,'MP3 PLAYERS','MP3 PLAYERS',6),
(8,'FLASH','FLASH',7),
(9,'CD PLAYERS','CD PLAYERS',6),
(10,'2 WAY RADIOS','2 WAY RADIOS',6);

SELECT * FROM category ORDER BY category_id;


SELECT t1.name AS lev1, t2.name as lev2, t3.name as lev3, t4.name as lev4
FROM category AS t1
LEFT JOIN category AS t2 ON t2.parent = t1.category_id
LEFT JOIN category AS t3 ON t3.parent = t2.category_id
LEFT JOIN category AS t4 ON t4.parent = t3.category_id
WHERE t1.name = 'ELECTRONICS';




CREATE TABLE `tree_node` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `data_body` text,
    `node_deleted` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `tree_hierarchy` (
    `ancestor` int(10) unsigned NOT NULL,
    `descendant` int(10) unsigned NOT NULL,
    PRIMARY KEY (`ancestor`,`descendant`),
    KEY `descendant` (`descendant`),
    CONSTRAINT `tree_hierarchy_ibfk_1` FOREIGN KEY (`ancestor`) REFERENCES `tree_node` (`id`),
    CONSTRAINT `tree_hierarchy_ibfk_2` FOREIGN KEY (`descendant`) REFERENCES `tree_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



select * from tree_hierarchy;

INSERT INTO tree_hierarchy (ancestor, descendant) SELECT ancestor, 1 FROM tree_hierarchy WHERE descendant=null UNION ALL SELECT 1, 1;



SELECT
    tree_node.id,
    tree_node.node_deleted,
    tree_node.data_body,
    tree_hierarchy.ancestor AS parent_id
FROM tree_node
    JOIN tree_hierarchy ON tree_node.id=tree_hierarchy.ancestor
WHERE tree_hierarchy.descendant=1
    AND tree_node.node_deleted IS NULL
UNION
SELECT
    tree_node.id,
    tree_node.node_deleted,
    tree_node.data_body,
    tree_hierarchy.ancestor AS parent_id
FROM tree_node
    JOIN tree_hierarchy ON tree_node.id=tree_hierarchy.descendant
WHERE tree_hierarchy.ancestor=1
    AND tree_node.node_deleted IS NULL;

    
CREATE TABLE item(
        serial_number VARCHAR(50) PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        description VARCHAR(100) NOT NULL,
        note VARCHAR(200) NOT NULL,
        certification_date  datetime NOT NULL,
        created datetime NOT NULL,
        modified datetime NOT NULL,
        category_id INT NOT NULL,
        INDEX par_ind (category_id),
		CONSTRAINT fk_category FOREIGN KEY (category_id)
		REFERENCES category(category_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE  
 );