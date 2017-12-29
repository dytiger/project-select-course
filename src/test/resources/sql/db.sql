-- 数据库脚本

DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS rel_course_users;


CREATE TABLE course
(
	id int(11) primary key auto_increment,
	name varchar(30) NOT NULL,
	teach_time varchar(30) NOT NULL,
	teacher varchar(30) NOT NULL,
	credit int(2) DEFAULT 5 NOT NULL
);

CREATE TABLE users
(
	id int(11) primary key auto_increment,
	name varchar(30) NOT NULL,
	password varchar(36) NOT NULL,
	level int(2) DEFAULT 0 NOT NULL
);

CREATE TABLE rel_course_users
(
	user_id int(11),
	course_id int(11),
	primary key (user_id,course_id)
);

INSERT INTO course VALUES
(1,'大学英文阅读','周三上午','李灵',3),
(2,'大学语文','周一下午','刘伟传',3),
(3,'应用文写作','周二下午','张晓',1),
(4,'数据库原理','周一晚上','李琦',2),
(5,'C语言程序设计','周三晚上','肖儒',6),
(6,'URL概论','周五下午','卢鼎',2),
(7,'高等数学提高','周一上午','刘智英',3),
(8,'现代企业管理','周五上午','张小北',2),
(9,'游泳','周三下午','郝森',2);

INSERT INTO users VALUES
(1,'admin','1',99),
(2,'张扬','1',0),
(3,'刘辉','1',0),
(4,'李丰','1',0),
(5,'王青林','1',0),
(6,'陈雅','1',0),
(7,'李震','1',0);




INSERT INTO rel_course_users VALUES
(2,1),
(2,8),
(3,2),
(3,3),
(3,9),
(3,7),
(4,6),
(4,5),
(5,5),
(5,1),
(5,3),
(6,1),
(6,2),
(6,3),
(2,7),
(2,3),
(3,4),
(7,1),
(7,5),
(7,6),
(7,7),
(7,8),
(7,9);

COMMIT;