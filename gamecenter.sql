-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 26-Out-2019 às 18:37
-- Versão do servidor: 10.4.8-MariaDB
-- versão do PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- preciso criar o usuario do nosso gamecenter
create user 'usergc'@'localhost' identified by '12345678';

-- preciso criar a base de dados
create database gamecenter;

-- atribuir permissões para o usuário manipular a base
grant all privileges on gamecenter.* to 'usergc'@'localhost';

-- fazer acesso à base
use gamecenter;  


--
-- Banco de dados: `gamecenter`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tblgame`
--

CREATE TABLE `tblgame` (
  `id` int(11) UNSIGNED NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `plataforma` varchar(25) NOT NULL,
  `chave` varchar(64) NOT NULL,
  `idUser` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tblpontuacao`
--

CREATE TABLE `tblpontuacao` (
  `numSeq` int(10) UNSIGNED NOT NULL,
  `email` varchar(50) NOT NULL,
  `score` float UNSIGNED NOT NULL,
  `idGame` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbluser`
--

CREATE TABLE `tbluser` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tbluser`
--

INSERT INTO `tbluser` (`id`, `nome`, `email`, `senha`) VALUES
(1, 'Professor Isidro', 'isidro@professorisidro.com.br', '1234'),
(2, 'User de Teste', 'teste@teste.com', '1234'),
(3, 'Adamastor Gouveia dos Santos', 'ada@ada.com', '1234');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tblgame`
--
ALTER TABLE `tblgame`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `chave` (`chave`),
  ADD KEY `donodojogo` (`idUser`);

--
-- Índices para tabela `tblpontuacao`
--
ALTER TABLE `tblpontuacao`
  ADD PRIMARY KEY (`numSeq`),
  ADD KEY `pontosdojogo` (`idGame`);

--
-- Índices para tabela `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Index_Email` (`email`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tblgame`
--
ALTER TABLE `tblgame`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tblpontuacao`
--
ALTER TABLE `tblpontuacao`
  MODIFY `numSeq` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tblgame`
--
ALTER TABLE `tblgame`
  ADD CONSTRAINT `donodojogo` FOREIGN KEY (`idUser`) REFERENCES `tbluser` (`id`);

--
-- Limitadores para a tabela `tblpontuacao`
--
ALTER TABLE `tblpontuacao`
  ADD CONSTRAINT `pontosdojogo` FOREIGN KEY (`idGame`) REFERENCES `tblgame` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
