
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP SCHEMA IF EXISTS `proj_db` ;
CREATE SCHEMA IF NOT EXISTS `proj_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `proj_db` ;


DROP TABLE IF EXISTS `proj_db`.`user` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_num` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`sports_board` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`sports_board` (
  `sportsboard_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `view_cnt` INT NOT NULL DEFAULT '0',
  `user_id` INT NOT NULL,
  PRIMARY KEY (`sportsboard_id`),
  INDEX `fk_board_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `proj_db`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`comment` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `sportsboard_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_comment_sports_board1_idx` (`sportsboard_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_sports_board1`
    FOREIGN KEY (`sportsboard_id`)
    REFERENCES `proj_db`.`sports_board` (`sportsboard_id`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proj_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`follow` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`follow` (
  `user_from_id` INT NOT NULL,
  `user_to_id` INT NOT NULL,
  PRIMARY KEY (`user_from_id`, `user_to_id`),
  INDEX `fk_follow_user1_idx` (`user_from_id` ASC) VISIBLE,
  INDEX `fk_follow_user2_idx` (`user_to_id` ASC) VISIBLE,
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`user_from_id`)
    REFERENCES `proj_db`.`user` (`user_id`),
  CONSTRAINT `fk_follow_user2`
    FOREIGN KEY (`user_to_id`)
    REFERENCES `proj_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`message` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`message` (
  `title` VARCHAR(20) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_from_id` INT NOT NULL,
  `user_to_id` INT NOT NULL,
  INDEX `fk_message_user1_idx` (`user_from_id` ASC) VISIBLE,
  INDEX `fk_message_user2_idx` (`user_to_id` ASC) VISIBLE,
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`user_from_id`)
    REFERENCES `proj_db`.`user` (`user_id`),
  CONSTRAINT `fk_message_user2`
    FOREIGN KEY (`user_to_id`)
    REFERENCES `proj_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`sports_board_like` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`sports_board_like` (
  `board_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`board_id`, `user_id`),
  INDEX `fk_board_like_board1_idx` (`board_id` ASC) VISIBLE,
  INDEX `fk_board_like_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_like_board1`
    FOREIGN KEY (`board_id`)
    REFERENCES `proj_db`.`sports_board` (`sportsboard_id`),
  CONSTRAINT `fk_board_like_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proj_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`video` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`video` (
  `video_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(450) NOT NULL,
  `url` VARCHAR(450) NOT NULL,
  `channel_name` VARCHAR(45) NOT NULL,
  `view_cnt` INT NOT NULL DEFAULT '0',
  `part` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`video_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`video_like` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`video_like` (
  `user_id` INT NOT NULL,
  `video_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `video_id`),
  INDEX `fk_video_like_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_video_like_video1_idx` (`video_id` ASC) VISIBLE,
  CONSTRAINT `fk_video_like_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proj_db`.`user` (`user_id`),
  CONSTRAINT `fk_video_like_video1`
    FOREIGN KEY (`video_id`)
    REFERENCES `proj_db`.`video` (`video_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `proj_db`.`myPage` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`myPage` (
  `age` INT NULL,
  `gender` TINYINT NULL,
  `profile_url` VARCHAR(400) NULL,
  `self_intro` VARCHAR(1000) NULL,
  `dong_address` VARCHAR(45) NULL,
  `fav_sport` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  INDEX `fk_myPage_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_myPage_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proj_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `proj_db`.`review` ;
CREATE TABLE IF NOT EXISTS `proj_db`.`review` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(200) NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proj_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO video (title, url, channel_name, part)
VALUES ("[ENG] (근육통주의!!!!) 🔥올인원🔥 전신 근력운동 50분 홈트레이닝", "https://youtu.be/A5MzlPgNcJM?feature=shared", "힙으뜸", "전신"),
		("🔥건장한 상체🔥가 여리+슬림+탄탄해지는 '상체올킬루틴' (7일효과: 팔뚝살,등살,뱃살 완전빠짐&탄력up!!!)", "https://youtu.be/XjEfUcZLbG4?feature=shared", "이지은 다이어트 Jiny diet", "상체"),
        ("전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "https://youtu.be/gMaB-fG4u4g?feature=shared", "Thankyou BUBU", "전신");
		
select * from video;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;