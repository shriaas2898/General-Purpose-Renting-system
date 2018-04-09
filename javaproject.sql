-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 20, 2018 at 01:27 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`name`) VALUES
('book'),
('books'),
('cycle'),
('dse'),
('movies'),
('vehicle');

-- --------------------------------------------------------

--
-- Table structure for table `criteria`
--

CREATE TABLE `criteria` (
  `id` int(11) NOT NULL,
  `category` varchar(30) NOT NULL,
  `time` varchar(6) NOT NULL,
  `no_of` int(11) NOT NULL,
  `duration` varchar(10) NOT NULL,
  `charge` decimal(10,0) NOT NULL,
  `occurence` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `criteria`
--

INSERT INTO `criteria` (`id`, `category`, `time`, `no_of`, `duration`, `charge`, `occurence`) VALUES
(1, 'books', 'Before', 5, 'Day', '10', 'Per day'),
(3, 'movies', 'Before', 10, 'Day', '0', 'Per day'),
(4, 'vehicle', 'Before', 10, 'Day', '1000', 'Per day'),
(5, 'cycle', 'Before', 2, 'Day', '1', 'Per day'),
(6, 'book', 'Before', 10, 'Day', '11', 'Per day'),
(7, 'dse', 'After', 1, 'Day', '1', 'Once');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(60) NOT NULL,
  `Phno` varchar(10) NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'genral'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `address`, `Phno`, `type`) VALUES
(1, 'aa', 'eltis	', '8978', 'premium'),
(2, 'bla', 'bla bla', '378794', 'premium'),
(3, 'bla', 'bla bla', '378794', 'premium');

-- --------------------------------------------------------

--
-- Table structure for table `customer_type`
--

CREATE TABLE `customer_type` (
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_type`
--

INSERT INTO `customer_type` (`type`) VALUES
('bla'),
('genral'),
('new'),
('premimum'),
('premium');

-- --------------------------------------------------------

--
-- Table structure for table `cus_item`
--

CREATE TABLE `cus_item` (
  `cid` int(11) NOT NULL,
  `iid` int(11) NOT NULL,
  `date_rented` date NOT NULL,
  `return_date` date NOT NULL,
  `date_return` date NOT NULL,
  `amnt_due` decimal(10,0) NOT NULL,
  `amnt_paid` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `entity_status`
--

CREATE TABLE `entity_status` (
  `entity_name` varchar(20) NOT NULL,
  `value` varchar(20) DEFAULT NULL,
  `status` varchar(7) NOT NULL DEFAULT 'not set'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entity_status`
--

INSERT INTO `entity_status` (`entity_name`, `value`, `status`) VALUES
('appname', '', 'set'),
('custype', '6', 'set');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `category` varchar(30) NOT NULL,
  `qty_total` int(11) NOT NULL,
  `qty_available` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `category`, `qty_total`, `qty_available`) VALUES
(8, 'book1', 'books', 5, 5),
(9, 'book2', 'books', 7, 4),
(10, 'bla', 'cycle', 10, 10),
(11, 'dsfdfgg', 'dse', 2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `criteria`
--
ALTER TABLE `criteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `criteria_ibfk_1` (`category`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_type`
--
ALTER TABLE `customer_type`
  ADD PRIMARY KEY (`type`);

--
-- Indexes for table `cus_item`
--
ALTER TABLE `cus_item`
  ADD KEY `cid` (`cid`),
  ADD KEY `iid` (`iid`);

--
-- Indexes for table `entity_status`
--
ALTER TABLE `entity_status`
  ADD PRIMARY KEY (`entity_name`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `criteria`
--
ALTER TABLE `criteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `criteria`
--
ALTER TABLE `criteria`
  ADD CONSTRAINT `criteria_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `cus_item`
--
ALTER TABLE `cus_item`
  ADD CONSTRAINT `cus_item_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `cus_item_ibfk_2` FOREIGN KEY (`iid`) REFERENCES `items` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
