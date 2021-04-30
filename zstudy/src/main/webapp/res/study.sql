CREATE SCHEMA IF NOT EXISTS ssafy_study DEFAULT CHARACTER SET utf8 ;
USE ssafy_study ;

DROP TABLE IF EXISTS study;
CREATE TABLE IF NOT EXISTS study (
  name CHAR(12) NOT NULL,
  id VARCHAR(100) NOT NULL,
 
  PRIMARY KEY (name, id))
ENGINE = InnoDB;

DROP TABLE IF EXISTS ssafy_study.ssafy_member;
CREATE TABLE IF NOT EXISTS ssafy_study.ssafy_member (
  userid VARCHAR(16) NOT NULL,
  username VARCHAR(20) NOT NULL,
  userpwd VARCHAR(16) NOT NULL,
  email VARCHAR(50) NULL,
  address VARCHAR(100) NULL,
  joindate TIMESTAMP NULL DEFAULT current_timestamp,
  PRIMARY KEY (userid))
ENGINE = InnoDB;

INSERT INTO ssafy_member (userid, username, userpwd, email, address)
VALUES('admin', '관리자', 'admin', 'admin@ssafy.com','서울시 역삼동');

INSERT INTO ssafy_member (userid, username, userpwd, email, address)
VALUES('ssafy', '김싸피', 'ssafy', 'ssafy@ssafy.com','대전시 덕명동');

commit;
select *
from ssafy_member;

insert into study values ('java', 'admin'), ('java', 'ssafy'), ('spring', 'admin');
commit;
select *
from study;

select count(id) from study where name = 'java' group by name;