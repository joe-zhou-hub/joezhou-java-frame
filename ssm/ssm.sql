DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`     int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name`   varchar(255) DEFAULT NULL COMMENT '姓名',
    `age`    int(3)       DEFAULT NULL COMMENT '年龄',
    `gender` tinyint(2)   DEFAULT NULL COMMENT '性别'
) AUTO_INCREMENT = 21 COMMENT '用户表';

INSERT INTO `user`
VALUES (1, '赵四', 18, 1),
       (2, '刘能', 37, 1),
       (3, '赵玉田', 37, 1),
       (4, '刘英', 38, 0),
       (5, '王天来', 39, 1),
       (6, '王云', 41, 0),
       (7, '刘志', 42, 1),
       (8, '王大拿', 43, 1),
       (9, '王木生', 44, 1),
       (10, '宋晓峰', 45, 1),
       (11, '瓶底子', 46, 1),
       (12, '豁牙子', 47, 1),
       (13, '王小蒙', 47, 0),
       (14, '王老七', 48, 1),
       (15, '宋富贵', 49, 1),
       (16, '谢飞机', 12, 1),
       (17, '谢广坤', 45, 1),
       (18, '谢永强', 47, 1),
       (19, '皮长山', 22, 1),
       (20, '小梁', 28, 1);
COMMIT;