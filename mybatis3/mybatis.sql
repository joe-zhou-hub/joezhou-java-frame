CREATE TABLE IF NOT EXISTS `student`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '学生姓名',
    `gender` TINYINT(1)   NULL COMMENT '学生性别',
    `age`    INT(3)       NULL COMMENT '学生年龄',
    `info`   VARCHAR(500) NULL COMMENT '学生信息'
)
    COMMENT '学生表';