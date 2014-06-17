-- MySQL dump 10.10
--
-- Host: localhost    Database: master_portal
-- ------------------------------------------------------
-- Server version	4.1.13a-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(32) NOT NULL default '',
  `password` varchar(32) NOT NULL default '',
  `email` text,
  PRIMARY KEY  (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO admin VALUES ('admin','admin','none@nowhere.edu');

--
-- Table structure for table `analysis_request`
--

DROP TABLE IF EXISTS `analysis_request`;
CREATE TABLE `analysis_request` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(64) NOT NULL default '',
  `name` varchar(32) NOT NULL default '',
  `rid` varchar(32) default NULL,
  `settings_id` int(11) NOT NULL default '0',
  `query_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL auto_increment,
  `album` text,
  `artist` text,
  `track` text,
  `library` varchar(64) NOT NULL default '',
  `type` varchar(64) NOT NULL default '',
  `genre` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
CREATE TABLE `library` (
  `id` varchar(64) NOT NULL default '',
  `contact_email` varchar(64) default NULL,
  `location` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `new_feature`
--

DROP TABLE IF EXISTS `new_feature`;
CREATE TABLE `new_feature` (
  `id` int(11) NOT NULL auto_increment,
  `feature` longtext NOT NULL,
  `name` text NOT NULL,
  `rid` varchar(32) default NULL,
  `class` blob,
  `type` varchar(64) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `query`
--

DROP TABLE IF EXISTS `query`;
CREATE TABLE `query` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) NOT NULL default '',
  `rid` varchar(32) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `query_file`
--

DROP TABLE IF EXISTS `query_file`;
CREATE TABLE `query_file` (
  `qid` int(11) NOT NULL default '0',
  `fid` int(11) NOT NULL default '0',
  PRIMARY KEY  (`qid`,`fid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `researcher`
--

DROP TABLE IF EXISTS `researcher`;
CREATE TABLE `researcher` (
  `id` varchar(32) NOT NULL default '',
  `password` varchar(64) NOT NULL default '',
  `email` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `file_id` int(11) NOT NULL default '0',
  `analysis_request_id` int(11) NOT NULL default '0',
  `feature_key` longtext,
  `feature_values` longtext NOT NULL,
  PRIMARY KEY  (`file_id`,`analysis_request_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
CREATE TABLE `settings` (
  `id` int(11) NOT NULL auto_increment,
  `content` text NOT NULL,
  `name` varchar(64) NOT NULL default '',
  `rid` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

