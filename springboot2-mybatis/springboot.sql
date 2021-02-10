DROP TABLE IF EXISTS `STUDENT`;
CREATE TABLE `STUDENT`
(
    `id`     INT(11) AUTO_INCREMENT COMMENT '主键',
    `name`   VARCHAR(50) NOT NULL COMMENT '学生姓名',
    `gender` TINYINT(2) NOT NULL COMMENT '学生性别',
    `age`    TINYINT(3) COMMENT '学生年龄',
    `info`   VARCHAR(500) COMMENT '学生信息',
    PRIMARY KEY (`id`)
)
    COMMENT '学生表';

INSERT INTO `STUDENT`
VALUES (1, '赵四', 1, 58, '亚洲舞王'),
       (2, '刘能', 2, 59, '玉田花圃'),
       (3, '大脚', 0, 18, '大脚超市');
COMMIT;