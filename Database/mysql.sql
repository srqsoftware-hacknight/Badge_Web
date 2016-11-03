-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mousedoor
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mousedoor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mousedoor` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mousedoor` ;

-- -----------------------------------------------------
-- Table `mousedoor`.`BADGES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mousedoor`.`BADGES` (
  `RFID_ID` VARCHAR(16) NOT NULL,
  `FIRST_NAME` VARCHAR(64) NULL,
  `LAST_NAME` VARCHAR(64) NULL,
  `EMAIL` VARCHAR(64) NULL,
  `PHONE` VARCHAR(45) NULL,
  `ISSUED_DATE` TIMESTAMP(6) NULL,
  PRIMARY KEY (`RFID_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mousedoor`.`DEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mousedoor`.`DEVICES` (
  `DEVICE_ID` INT NOT NULL,
  `DESCRIPTION` TEXT(1024) NULL,
  PRIMARY KEY (`DEVICE_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mousedoor`.`BADGES_HISTORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mousedoor`.`BADGES_HISTORY` (
  `RETURN_DATE` TIMESTAMP(6) NOT NULL,
  `RFID_ID` VARCHAR(28) NOT NULL,
  `FIRST_NAME` VARCHAR(64) NULL,
  `LAST_NAME` VARCHAR(64) NULL,
  `EMAIL` VARCHAR(64) NULL,
  `PHONE` VARCHAR(45) NULL,
  `ISSUED_DATE` TIMESTAMP(6) NULL,
  PRIMARY KEY (`RETURN_DATE`, `RFID_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mousedoor`.`ACTIVITY_LOG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mousedoor`.`ACTIVITY_LOG` (
  `TIME` TIMESTAMP(6) NOT NULL,
  `BADGES_RFID_ID` VARCHAR(16) NOT NULL,
  `DEVICES_DEVICE_ID` INT NOT NULL,
  `STATUS` INT NULL,
  PRIMARY KEY (`TIME`, `BADGES_RFID_ID`, `DEVICES_DEVICE_ID`),
  INDEX `fk_ACTIVITY_LOG_BADGES_idx` (`BADGES_RFID_ID` ASC),
  INDEX `fk_ACTIVITY_LOG_DEVICES1_idx` (`DEVICES_DEVICE_ID` ASC),
  CONSTRAINT `fk_ACTIVITY_LOG_BADGES`
    FOREIGN KEY (`BADGES_RFID_ID`)
    REFERENCES `mousedoor`.`BADGES` (`RFID_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ACTIVITY_LOG_DEVICES1`
    FOREIGN KEY (`DEVICES_DEVICE_ID`)
    REFERENCES `mousedoor`.`DEVICES` (`DEVICE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
