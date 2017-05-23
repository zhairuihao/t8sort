-- ======================================================================================================================================================================================
-- 创建主表，关系表：
-- 其中
-- Ancestor代表祖先节点
-- Descendant代表后代节点
-- Distance 祖先距离后代的距离
-- ======================================================================================================================================================================================
CREATE TABLE nodeInfo (
	node_id INT NOT NULL AUTO_INCREMENT,
	node_name VARCHAR (255),
	PRIMARY KEY (`node_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE nodeRelationship (
	ancestor INT NOT NULL,
	descendant INT NOT NULL,
	distance INT NOT NULL,
	PRIMARY KEY (ancestor, descendant)
) DEFAULT CHARSET = utf8;
-- ======================================================================================================================================================================================
-- （创建存储过程）
-- ======================================================================================================================================================================================

CREATE DEFINER = `root`@`localhost` PROCEDURE `AddNode`(`_parent_name` varchar(255),`_node_name` varchar(255))
    COMMENT '添加数据（创建存储过程）\r\n '
BEGIN
	DECLARE _ancestor INT;
	DECLARE _descendant INT;
	DECLARE _parent INT;
	IF NOT EXISTS(SELECT node_id From nodeinfo WHERE node_name = _node_name)
	THEN
		INSERT INTO nodeinfo (node_name) VALUES(_node_name);
		SET _descendant = (SELECT node_id FROM nodeinfo WHERE node_name = _node_name);
		INSERT INTO noderelationship (ancestor,descendant,distance) VALUES(_descendant,_descendant,0);
		IF EXISTS (SELECT node_id FROM nodeinfo WHERE node_name = _parent_name)
		THEN
			SET _parent = (SELECT node_id FROM nodeinfo WHERE node_name = _parent_name);
			INSERT INTO noderelationship (ancestor,descendant,distance) SELECT ancestor,_descendant,distance+1 from noderelationship where descendant = _parent;
		END IF;
	END IF;
END;
-- ======================================================================================================================================================================================
-- 插入数据：
-- ======================================================================================================================================================================================
call AddNode('TEST', 'FOOD');
call AddNode('FOOD', 'FRUIT');
call AddNode('FOOD', 'MEAT');
call AddNode('FRUIT', 'RED');
call AddNode('FRUIT', 'YELLOW');
call AddNode('RED', 'CHERRY');
call AddNode('YELLOW', 'BANANA');
call AddNode('MEAT', 'BEEF');
call AddNode('MEAT', 'PORK');
-- ======================================================================================================================================================================================
-- 查询FOOD下所有的子节点：
-- ======================================================================================================================================================================================
SELECT
	n3.node_name
FROM
	nodeinfo n1
INNER JOIN noderelationship n2 ON n1.node_id = n2.ancestor
INNER JOIN nodeinfo n3 ON n2.descendant = n3.node_id
WHERE
	n1.node_name = 'FOOD'
AND n2.distance != 0
-- ======================================================================================================================================================================================
-- 查询FOOD下直属子节点：
-- ======================================================================================================================================================================================
SELECT
	n3.node_name
FROM
	nodeinfo n1
INNER JOIN noderelationship n2 ON n1.node_id = n2.ancestor
INNER JOIN nodeinfo n3 ON n2.descendant = n3.node_id
WHERE
	n1.node_name = 'FOOD'
AND n2.distance = 1
-- ======================================================================================================================================================================================
-- 查询Fruit所处的层级：
-- ======================================================================================================================================================================================
SELECT
	n2.*, n3.node_name
FROM
	nodeinfo n1
INNER JOIN noderelationship n2 ON n1.node_id = n2.descendant
INNER JOIN nodeinfo n3 ON n2.ancestor = n3.node_id
WHERE
	n1.node_name = 'Fruit'
ORDER BY
	n2.distance DESC

