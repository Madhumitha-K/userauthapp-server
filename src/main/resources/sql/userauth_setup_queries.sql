create database userauth;

CREATE TABLE userauth.`um_usr_inf` (
`id` int NOT NULL AUTO_INCREMENT COMMENT 'Unique Identifier',
  `um_eml_id` varchar(50) NOT NULL COMMENT 'User Email Id',
  `um_usr_pwd` varchar(250) NOT NULL COMMENT 'User Password',
  `um_als_nme` varchar(50) NOT NULL COMMENT 'User Alias Name',
  `um_fst_nme` varchar(50) NOT NULL COMMENT 'User First Name',
  `um_lst_nme` varchar(50) NOT NULL COMMENT 'User Last Name',
  `um_usr_sts` tinyint(1) NOT NULL COMMENT 'User Activation Status',
  `identity` varchar(36) NOT NULL COMMENT 'Unique Identity',
  `is_dlt_sts` tinyint(1) NOT NULL COMMENT 'Delete Status',
  `crt_dte_tme` timestamp NOT NULL COMMENT 'Created Datetime',
  `crt_usr_id` int NOT NULL COMMENT 'Created User Id',
  `mdy_dte_tme` timestamp NULL DEFAULT NULL COMMENT 'Modified Datetime',
  `mdy_usr_id` int DEFAULT NULL COMMENT 'Modified User Id',
  PRIMARY KEY (`id`)
) COMMENT 'User Profile';

-- Password for all three - Demo@123
INSERT INTO userauth.um_usr_inf(um_eml_id,um_usr_pwd,um_als_nme,um_fst_nme,um_lst_nme,um_usr_sts,`identity`,is_dlt_sts,crt_dte_tme,crt_usr_id,mdy_dte_tme,mdy_usr_id) VALUES
     ('demotest1@test.com','$2a$10$eBIsgy6KqRf1jjnCMx28deOcFqVSk.6cKrYZoSTTS2aaLhkxunIMK','DemoTest1','Demo','Test1',1,uuid(),0,now(),1,NULL,NULL),
	 ('demotest2@test.com','$2a$10$SUH2gojKGCbXDajghvZ2Kuq/A4Uj0Wx2vkUUhoiOy51t3uoaTj6Um','DemoTest2','Demo','Test2',1,uuid(),0,now(),1,NULL,NULL),
	 ('testuser@test.com','$2a$10$xrZATlllHHa9FFE4stOIme5mSNciwQp747kH0EbbqCb20qu4F0/eG','TestUser','Test','User1',1,uuid(),0,now(),1,NULL,NULL);

CREATE TABLE userauth.`SPRING_SESSION` (
 `PRIMARY_ID` CHAR(36) NOT NULL,
 `SESSION_ID` CHAR(36) NOT NULL,
 `CREATION_TIME` BIGINT NOT NULL,
 `LAST_ACCESS_TIME` BIGINT NOT NULL,
 `MAX_INACTIVE_INTERVAL` INT NOT NULL,
 `EXPIRY_TIME` BIGINT NOT NULL,
 `PRINCIPAL_NAME` VARCHAR(100),
 CONSTRAINT `SPRING_SESSION_PK` PRIMARY KEY (`PRIMARY_ID`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX `SPRING_SESSION_IX1` ON userauth.`SPRING_SESSION` (`SESSION_ID`);
CREATE INDEX `SPRING_SESSION_IX2` ON userauth.`SPRING_SESSION` (`EXPIRY_TIME`);
CREATE INDEX `SPRING_SESSION_IX3` ON userauth.`SPRING_SESSION` (`PRINCIPAL_NAME`);

CREATE TABLE userauth.`SPRING_SESSION_ATTRIBUTES` (
 `SESSION_PRIMARY_ID` CHAR(36) NOT NULL,
 `ATTRIBUTE_NAME` VARCHAR(200) NOT NULL,
 `ATTRIBUTE_BYTES` BLOB NOT NULL,
 CONSTRAINT `SPRING_SESSION_ATTRIBUTES_PK` PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
 CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES userauth.`SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;