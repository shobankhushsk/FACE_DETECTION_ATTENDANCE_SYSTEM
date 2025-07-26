-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 11, 2024 at 09:04 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `facedetectiondatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` int(15) NOT NULL,
  `std_id` int(15) NOT NULL,
  `attendance_date` date NOT NULL,
  `remarks` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`attendance_id`, `std_id`, `attendance_date`, `remarks`) VALUES
(6, 10, '2024-05-25', 'good');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `dept_id` int(15) NOT NULL,
  `dept_name` varchar(50) NOT NULL,
  `remarks` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dept_id`, `dept_name`, `remarks`) VALUES
(1, 'SOFTWARE', 'GOOD'),
(2, 'Urdu', 'GOOD'),
(4, 'ARTS TAT', 'godoksksksksk');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `USER_ID` int(11) NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `USER_PASS` varchar(100) NOT NULL,
  `EMAIL` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`USER_ID`, `USER_NAME`, `USER_PASS`, `EMAIL`) VALUES
(1, 'shoban', '12345', 'shobankhushk123@gmail.com'),
(2, 'shoban', '12345', 'shobankhushk123@gmail.com'),
(3, 'jameel', 'hello1234', 'jameelbaloch123@gmail.com'),
(4, 'nabeel', '1234', 'nabeel124@gmail.com'),
(5, 'ali', 'abc123', 'alishoban@gmail.com'),
(6, 'junaid', 'hello4321', 'junaidkhushk@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `std_id` int(15) NOT NULL,
  `dept_id` int(15) NOT NULL,
  `std_name` varchar(50) NOT NULL,
  `remarks` varchar(50) NOT NULL,
  `father_name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `roll_no` varchar(100) NOT NULL,
  `shift` varchar(100) NOT NULL,
  `groupDes` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`std_id`, `dept_id`, `std_name`, `remarks`, `father_name`, `surname`, `roll_no`, `shift`, `groupDes`) VALUES
(5, 1, 'Ali', 'goodsss', 'Ahmed', 'Baloch', '2k29', 'MORNING', 'MEDICAL '),
(6, 1, 'Nisar', 'good', 'Deedar', 'khushk', '2k290', 'MORNING', 'MEDICAL '),
(7, 2, 'SHANU', 'good', 'ALI HYDER', 'KING', '2k-39-it0-3', 'MORNING', 'MEDICAL '),
(10, 1, 'shoban', 'good', 'ALI HYDER', 'KING', '2k-39-it0-3', 'MORNING', 'MEDICAL ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`dept_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`USER_ID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`std_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendance_id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `dept_id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `std_id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
