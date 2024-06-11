-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2024 at 05:25 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rekomendasi`
--

-- --------------------------------------------------------

--
-- Table structure for table `daftar_kriteria`
--

CREATE TABLE `daftar_kriteria` (
  `id_kriteria` int(11) NOT NULL,
  `nama_kriteria` varchar(100) NOT NULL,
  `bobot_kriteria` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `daftar_kriteria`
--

INSERT INTO `daftar_kriteria` (`id_kriteria`, `nama_kriteria`, `bobot_kriteria`) VALUES
(1, 'Lokasi', 0.2),
(2, 'Harga', 0.3),
(3, 'Variasi', 0.2),
(4, 'Kualitas', 0.2),
(5, 'Ulasan', 0.1);

-- --------------------------------------------------------

--
-- Table structure for table `daftar_oleh_oleh`
--

CREATE TABLE `daftar_oleh_oleh` (
  `id_oleholeh` int(50) NOT NULL,
  `nama_tempat` varchar(150) NOT NULL,
  `lokasi` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `daftar_oleh_oleh`
--

INSERT INTO `daftar_oleh_oleh` (`id_oleholeh`, `nama_tempat`, `lokasi`) VALUES
(1, 'Malang Strudel', 'Jalan Soekarno Hatta Kav. 6 No. 2, Mojolangu, Lowokwaru, Mojolangu, Kec. Lowokwaru, Kota Malang, Jaw'),
(2, 'Oleh-Oleh Khas Malang Batu', 'Jl. Diponegoro No.86, Sisir, Kec. Batu, Kota Batu, Jawa Timur 65314'),
(3, 'Bakpia Tugu Malang', 'Jl. Soekarno Hatta No.30 Kav 8, Jatimulyo, Kec. Lowokwaru, Kota Malang, Jawa Timur 65153'),
(4, 'Pia Cap Mangkok Malang', 'Jalan Semeru Nomor 25, Ruko Semeru, Klojen, Kauman, Kec. Klojen, Kota Malang, Jawa Timur 65111'),
(5, 'Toko Oen Malang', 'Jl. Jenderal Basuki Rahmat No.5, Kauman, Kec. Klojen, Kota Malang, Jawa Timur 65119'),
(11, 'strundel', 'jalan soekarno-hatta'),
(12, 'strundel', 'jalan soekarno-hatta'),
(13, 'gajahhah', 'ajajaj');

-- --------------------------------------------------------

--
-- Table structure for table `tb_matrik`
--

CREATE TABLE `tb_matrik` (
  `id_matrik` int(11) NOT NULL,
  `id_oleh_oleh` int(11) NOT NULL,
  `id_kriteria` int(11) NOT NULL,
  `nilai` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_matrik`
--

INSERT INTO `tb_matrik` (`id_matrik`, `id_oleh_oleh`, `id_kriteria`, `nilai`) VALUES
(1, 1, 1, 2),
(2, 1, 2, 1),
(3, 1, 4, 1),
(4, 1, 3, 2),
(5, 1, 5, 3),
(6, 3, 2, 2),
(7, 3, 4, 1),
(8, 3, 1, 1),
(9, 3, 5, 2),
(10, 3, 3, 3),
(11, 2, 2, 2),
(12, 2, 4, 2),
(13, 2, 1, 1),
(14, 2, 5, 2),
(15, 2, 3, 3),
(16, 4, 2, 1),
(17, 4, 4, 2),
(18, 4, 1, 1),
(19, 4, 5, 2),
(20, 4, 3, 2),
(21, 5, 2, 1),
(22, 5, 4, 2),
(23, 5, 1, 1),
(24, 5, 5, 2),
(25, 5, 3, 2),
(26, 11, 1, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daftar_kriteria`
--
ALTER TABLE `daftar_kriteria`
  ADD PRIMARY KEY (`id_kriteria`);

--
-- Indexes for table `daftar_oleh_oleh`
--
ALTER TABLE `daftar_oleh_oleh`
  ADD PRIMARY KEY (`id_oleholeh`);

--
-- Indexes for table `tb_matrik`
--
ALTER TABLE `tb_matrik`
  ADD PRIMARY KEY (`id_matrik`),
  ADD KEY `relasi_oleh` (`id_oleh_oleh`),
  ADD KEY `relasikriteria` (`id_kriteria`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daftar_kriteria`
--
ALTER TABLE `daftar_kriteria`
  MODIFY `id_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `daftar_oleh_oleh`
--
ALTER TABLE `daftar_oleh_oleh`
  MODIFY `id_oleholeh` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tb_matrik`
--
ALTER TABLE `tb_matrik`
  MODIFY `id_matrik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_matrik`
--
ALTER TABLE `tb_matrik`
  ADD CONSTRAINT `relasi_oleh` FOREIGN KEY (`id_oleh_oleh`) REFERENCES `daftar_oleh_oleh` (`id_oleholeh`),
  ADD CONSTRAINT `relasikriteria` FOREIGN KEY (`id_kriteria`) REFERENCES `daftar_kriteria` (`id_kriteria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
