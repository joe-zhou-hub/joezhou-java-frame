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
    `name`   VARCHAR(50)  NOT NULL COMMENT '教师姓名',
    `gender` TINYINT(1)   NULL COMMENT '教师性别',
    `age`    INT(3)       NULL COMMENT '教师年龄',
    `info`   VARCHAR(500) NULL COMMENT '教师信息'
)
    COMMENT '教师表';

CREATE TABLE IF NOT EXISTS `worker`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '工人姓名',
    `gender` TINYINT(1)   NULL COMMENT '工人性别',
    `age`    INT(3)       NULL COMMENT '工人年龄',
    `info`   VARCHAR(500) NULL COMMENT '工人信息'
)
    COMMENT '工人表';

CREATE TABLE IF NOT EXISTS `leader`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '领导姓名',
    `gender` TINYINT(1)   NULL COMMENT '领导性别',
    `age`    INT(3)       NULL COMMENT '领导年龄',
    `info`   VARCHAR(500) NULL COMMENT '领导信息'
)
    COMMENT '领导表';

CREATE TABLE IF NOT EXISTS `user`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '用户姓名',
    `gender` TINYINT(1)   NULL COMMENT '用户性别',
    `age`    INT(3)       NULL COMMENT '用户年龄',
    `info`   VARCHAR(500) NULL COMMENT '用户信息'
)
    COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `dept`
(
    `deptno` INT(2)      NOT NULL PRIMARY KEY,
    `dname`  VARCHAR(14) NULL COMMENT '部门名',
    `loc`    VARCHAR(13) NULL COMMENT '部门地址'
)
    COMMENT '部门表';

INSERT INTO mybatis.`dept`
VALUES (10, 'ACCOUNTING', 'NEW YORK'),
       (20, 'RESEARCH', 'DALLAS'),
       (30, 'SALES', 'CHICAGO'),
       (40, 'OPERATIONS', 'BOSTON');
COMMIT;

CREATE TABLE IF NOT EXISTS `emp`
(
    `empno`    INT(4)      NOT NULL PRIMARY KEY,
    `ename`    VARCHAR(10) NULL COMMENT '员工姓名',
    `job`      VARCHAR(9)  NULL COMMENT '员工工作',
    `mgr`      INT(4)      NULL COMMENT '上级领导编号',
    `hiredate` DATE        NULL COMMENT '入职日期',
    `sal`      INT(7)      NULL COMMENT '员工月薪',
    `comm`     INT(7)      NULL COMMENT '员工补助',
    `deptno`   INT(2)      NULL COMMENT '部门表外键',
    CONSTRAINT FK_EMP_DEPT
        FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`)
)
    COMMENT '员工表';

INSERT INTO mybatis.`emp`
VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800, null, 20),
       (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300, 30),
       (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250, 500, 30),
       (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975, null, 20),
       (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30),
       (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850, null, 30),
       (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450, null, 10),
       (7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000, null, 20),
       (7839, 'KING', 'PRESIDENT', null, '1981-11-17', 5000, null, 10),
       (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500, 0, 30),
       (7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100, null, 20),
       (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950, null, 30),
       (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000, null, 20),
       (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300, null, 10);
COMMIT;

CREATE TABLE IF NOT EXISTS `dog`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '小狗姓名',
    `gender` TINYINT(1)   NULL COMMENT '小狗性别',
    `age`    INT(3)       NULL COMMENT '小狗年龄',
    `info`   VARCHAR(500) NULL COMMENT '小狗信息'
)
    COMMENT '小狗表';

CREATE TABLE IF NOT EXISTS `cat`
(
    `id`     INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`   VARCHAR(50)  NOT NULL COMMENT '小猫姓名',
    `gender` TINYINT(1)   NULL COMMENT '小猫性别',
    `age`    INT(3)       NULL COMMENT '小猫年龄',
    `info`   VARCHAR(500) NULL COMMENT '小猫信息'
)
    COMMENT '小猫表';



