CREATE TABLE `member` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '회원 번호',
	`email` VARCHAR(100) NOT NULL COMMENT '회원 이메일' COLLATE 'utf8mb4_unicode_ci',
	`password` VARCHAR(200) NOT NULL COMMENT '회원 비밀번호' COLLATE 'utf8mb4_unicode_ci',
	`name` VARCHAR(20) NOT NULL COMMENT '회원 이름' COLLATE 'utf8mb4_unicode_ci',
	`register_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '회원 가입날짜',
	PRIMARY KEY (`id`) USING BTREE
)
COMMENT='가입한 회원 정보들'
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;


