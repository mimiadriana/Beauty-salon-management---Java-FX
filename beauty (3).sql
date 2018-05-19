-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 19, 2018 at 08:08 AM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `beauty`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `ID` int(100) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Phone` varchar(100) NOT NULL,
  `Comment` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf32;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`ID`, `Name`, `Phone`, `Comment`) VALUES
(2, 'Crina', '0000', ' '),
(3, 'Corina', '58585000', ' '),
(4, 'Silvia', '85785', ' '),
(5, 'Ana', '85785', ' '),
(6, 'Alina', '85785', ' '),
(7, 'Mihaela', '8585', ' '),
(8, 'Monica', '8585', ' ');

-- --------------------------------------------------------

--
-- Table structure for table `detail_services`
--

DROP TABLE IF EXISTS `detail_services`;
CREATE TABLE IF NOT EXISTS `detail_services` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(100) NOT NULL,
  `Price` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf32;

--
-- Dumping data for table `detail_services`
--

INSERT INTO `detail_services` (`ID`, `Description`, `Price`) VALUES
(1, 'Haircut long hair', '70'),
(2, 'Haircut short hair', '30'),
(3, 'Hair styling package', '150'),
(4, 'Hair dyeing', '60'),
(5, 'Cosmetic treatment', '150'),
(6, 'Microdermoabrazion', '150'),
(7, 'Tweezing', '20'),
(8, 'Massage 1 hour', '50'),
(9, 'Manicure French', '50'),
(10, 'Manicure Clasic', '50'),
(11, 'Manicure Clasic', '50'),
(12, 'Massage package 5 sessions', '200'),
(13, 'Massage 2.5 hrs', '80'),
(14, 'Make-up for brides', '200'),
(15, 'Make-up ', '150');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `admin` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `admin`, `password`) VALUES
(1, 'admin', '0000'),
(2, 'miha', 'miha');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `idcustomer` int(11) NOT NULL,
  `idservice` int(11) NOT NULL,
  `Date` varchar(100) NOT NULL,
  `Time` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `idcustomer` (`idcustomer`),
  KEY `idservice` (`idservice`),
  KEY `idcustomer_2` (`idcustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf32;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`ID`, `idcustomer`, `idservice`, `Date`, `Time`) VALUES
(1, 4, 8, '2018-05-14', '16:00:00'),
(2, 5, 1, '2018-05-16', '10:28:00'),
(3, 2, 10, '2018-06-06', '10:28:00'),
(4, 5, 9, '2018-04-13', '10:28:00'),
(5, 3, 2, '2018-05-29', '19:10:00'),
(7, 4, 7, '2018-05-18', '13:30:00'),
(8, 5, 3, '2018-05-09', '13'),
(9, 6, 14, '2018-05-18', '10:00'),
(10, 3, 2, '2018-05-18', '10:00:00'),
(11, 3, 2, '2018-05-31', '13:00:00'),
(12, 8, 9, '2018-05-17', '10:28:00'),
(13, 5, 5, '2018-05-18', '17:05'),
(14, 5, 5, '2018-05-18', '17:05');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idcustomer`) REFERENCES `customers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`idservice`) REFERENCES `detail_services` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
