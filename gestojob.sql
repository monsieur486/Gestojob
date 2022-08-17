-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : db:3306
-- Généré le : mer. 17 août 2022 à 20:41
-- Version du serveur : 5.7.39
-- Version de PHP : 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestojob`
--

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL,
  `app_object` int(11) DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `appointment_time` time DEFAULT NULL,
  `comment` longtext,
  `company_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `company`
--

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL,
  `adress` varchar(150) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `comment` longtext,
  `complement` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `negative` bit(1) DEFAULT NULL,
  `postal_code` varchar(150) DEFAULT NULL,
  `telephone` varchar(150) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `company`
--

INSERT INTO `company` (`id`, `adress`, `city`, `comment`, `complement`, `email`, `name`, `negative`, `postal_code`, `telephone`) VALUES
(1, '62 rue  du Lazaret', 'Strasbourg', 'Ras', NULL, 'monemail@tartempion.fr', 'Entreprise Demo', b'0', '67000', '0388797950'),
(2, NULL, NULL, 'Ras', NULL, NULL, 'Entreprise 3', b'1', NULL, '0388797950'),
(3, NULL, NULL, 'Ras', NULL, NULL, 'Entreprise 2', b'1', NULL, '0388797950');

-- --------------------------------------------------------

--
-- Structure de la table `mail`
--

CREATE TABLE `mail` (
  `id` bigint(20) NOT NULL,
  `app_object` int(11) DEFAULT NULL,
  `comment` longtext,
  `mail_date` date DEFAULT NULL,
  `mail_time` time DEFAULT NULL,
  `company_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `mail`
--

INSERT INTO `mail` (`id`, `app_object`, `comment`, `mail_date`, `mail_time`, `company_id`) VALUES
(1, 0, 'Ras', '2022-01-22', '09:35:00', 1),
(2, 0, 'Ras', '2022-03-22', '09:35:00', 1),
(3, 0, 'Ras', '2022-02-22', '09:35:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `phone_call`
--

CREATE TABLE `phone_call` (
  `id` bigint(20) NOT NULL,
  `app_object` int(11) DEFAULT NULL,
  `comment` longtext,
  `phone_call_date` date DEFAULT NULL,
  `phone_call_time` time DEFAULT NULL,
  `company_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkitlquw475ct47brwaiiry5lt` (`company_id`);

--
-- Index pour la table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `mail`
--
ALTER TABLE `mail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl4srlvp2evpmp58ssvihvqypx` (`company_id`);

--
-- Index pour la table `phone_call`
--
ALTER TABLE `phone_call`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyyp5wrieqifcovvpmhr6tmep` (`company_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `company`
--
ALTER TABLE `company`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `mail`
--
ALTER TABLE `mail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `phone_call`
--
ALTER TABLE `phone_call`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
