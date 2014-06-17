-- MySQL dump 10.10
--
-- Host: localhost    Database: library
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
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL default '0',
  `path` text,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `libraryName`
--

DROP TABLE IF EXISTS `libraryName`;
CREATE TABLE `libraryName` (
  `name` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `masterURL`
--

DROP TABLE IF EXISTS `masterURL`;
CREATE TABLE `masterURL` (
  `url` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`url`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `outstanding_calculations`
--

DROP TABLE IF EXISTS `outstanding_calculations`;
CREATE TABLE `outstanding_calculations` (
  `worker_id` int(11) NOT NULL default '0',
  `task_id` int(11) NOT NULL default '0',
  `file_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`worker_id`,`task_id`,`file_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `queue`
--

DROP TABLE IF EXISTS `queue`;
CREATE TABLE `queue` (
  `tid` int(11) NOT NULL default '0',
  `fid` int(11) NOT NULL default '0',
  `position` int(11) default NULL,
  PRIMARY KEY  (`tid`,`fid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `tid` int(11) NOT NULL default '0',
  `fid` int(11) NOT NULL default '0',
  `type` varchar(64) default NULL,
  `feature_key` longtext,
  `feature_values` longtext,
  PRIMARY KEY  (`tid`,`fid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL auto_increment,
  `user` varchar(64) NOT NULL default '',
  `settings` text NOT NULL,
  `number_total` int(11) NOT NULL default '0',
  `name` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
CREATE TABLE `time` (
  `type` int(11) NOT NULL default '0',
  `day` int(11) NOT NULL default '0',
  `hour` int(11) NOT NULL default '0',
  `minute` int(11) NOT NULL default '0',
  PRIMARY KEY  (`type`,`day`,`hour`,`minute`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(32) NOT NULL default '',
  `password` varchar(64) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO users VALUES ('admin','admin');

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL auto_increment,
  `location` varchar(128) NOT NULL default '',
  `name` varchar(64) default NULL,
  `priority` int(11) default '5',
  `maxCacheSize` int(11) default '10240',
  `onIdle` int(11) default '0',
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

