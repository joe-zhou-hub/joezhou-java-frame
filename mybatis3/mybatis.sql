CREATE TABLE IF NOT EXISTS `student`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '学生姓名',
    `gender` TINYINT(1)   NULL COMMENT '学生性别',
    `age`    INT(3)       NULL COMMENT '学生年龄',
    `info`   VARCHAR(500) NULL COMMENT '学生信息'
)
    COMMENT '学生表';

CREATE TABLE IF NOT EXISTS `teacher`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL COMMENT '教师姓名',
    `gender` TINYINT(1) NULL COMMENT '教师性别',
    `age` INT(3) NULL COMMENT '教师年龄',
    `info` VARCHAR(500) NULL COMMENT '教师信息'
)
    COMMENT '教师表';

CREATE TABLE IF NOT EXISTS `worker`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL COMMENT '工人姓名',
    `gender` TINYINT(1) NULL COMMENT '工人性别',
    `age` INT(3) NULL COMMENT '工人年龄',
    `info` VARCHAR(500) NULL COMMENT '工人信息'
)
    COMMENT '工人表';

CREATE TABLE IF NOT EXISTS `leader`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL COMMENT '领导姓名',
    `gender` TINYINT(1) NULL COMMENT '领导性别',
    `age` INT(3) NULL COMMENT '领导年龄',
    `info` VARCHAR(500) NULL COMMENT '领导信息'
)
    COMMENT '领导表';

CREATE TABLE IF NOT EXISTS `user`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL COMMENT '用户姓名',
    `gender` TINYINT(1) NULL COMMENT '用户性别',
    `age` INT(3) NULL COMMENT '用户年龄',
    `info` VARCHAR(500) NULL COMMENT '用户信息'
)
    COMMENT '用户表';

