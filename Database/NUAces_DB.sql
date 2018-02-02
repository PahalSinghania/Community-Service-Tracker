CREATE DATABASE IF NOT EXISTS `nuaces`;
USE `nuaces`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`user_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) NOT NULL,
`last_name` varchar(45),
`email` varchar(45) NOT NULL,
`username` varchar(45) NULL,
`access_type` ENUM ('AdminAccess','StudentAccess')
);

--
-- Table structure for table `partner`
--
/** 
Notes:
Table for community partner information. Not necessarily needed to have inheritance
with `user`.
*/
DROP TABLE IF EXISTS `partner`;
CREATE TABLE `partner` (
`partner_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`partner_name` varchar(45) NOT NULL
); 

--
-- Table structure for table `student`
--
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
`user_id` int(11) PRIMARY KEY REFERENCES `user`(`user_id`),
`partner_id` int(11) NOT NULL,
KEY `student_fkpartner` (`partner_id`),
CONSTRAINT `student_fkpartner`FOREIGN KEY (`partner_id`) REFERENCES `partner`(`partner_id`) 
);

--
-- Table structure for table `admin`
--
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
`user_id` int(11) PRIMARY KEY REFERENCES `user`(`user_id`)
);

--
-- Table structure for table `partner`
--
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
`location_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`location_name` varchar(45)
);

--
-- Table structure for table `activity`
--
/**
Notes:
How to connect the user table to the activity table? What columns need to be shared?
*/
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
`activity_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`activity_name` varchar(45) NULL,
`category` varchar(45) NULL, /** Use DEFAULT to make value set later;or ENUM? */
`date` DATE NOT NULL,
`time` TIME NOT NULL
);

--
-- Table structure for table `community_partner`
--
DROP TABLE IF EXISTS `community_partner`;
CREATE TABLE `community_partner` (
`activity_id` int(11) PRIMARY KEY REFERENCES `activity`(`activity_id`),
`partner_id` int(11) NOT NULL,
`location_id` int(11) NOT NULL,
KEY `community_partner_fkpartner` (`partner_id`),
KEY `community_partner_fklocation` (`location_id`),
CONSTRAINT `community_partner_fkpartner` FOREIGN KEY (`partner_id`) REFERENCES `partner`(`partner_id`),
CONSTRAINT `community_partner_fklocation` FOREIGN KEY (`location_id`) REFERENCES `location`(`location_id`), 
`hours` int(11),
`comments` varchar(160) NULL
);

--
-- Table structure for table `civic_action`
--
DROP TABLE IF EXISTS `civic_action`;
CREATE TABLE `civic_action` (
`activity_id` int(11) PRIMARY KEY REFERENCES `activity`(`activity_id`)
);

--
-- Table structure for table `alliance_building`
--
DROP TABLE IF EXISTS `alliance_building`;
CREATE TABLE `alliance_building` (
`activity_id` int(11) PRIMARY KEY REFERENCES `activity`(`activity_id`)
);