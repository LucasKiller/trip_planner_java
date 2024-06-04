CREATE DATABASE IF NOT EXISTS project_trip_planner;
USE project_trip_planner;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  login VARCHAR(60) NOT NULL,
  password VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS hotel (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  checkin DATE,
  checkout DATE,
  image_path VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS carro (
  id INT AUTO_INCREMENT PRIMARY KEY, 
  nome VARCHAR(60) NOT NULL,
  marca VARCHAR(60) NOT NULL,
  placa VARCHAR(10) NOT NULL,
  temSeguro BOOLEAN,
  img_path VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS trips (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  descrp LONGTEXT,
  id_hotel INT NOT NULL,
  id_carro INT NOT NULL,
  init_date DATE,
  final_date DATE,
  img_path VARCHAR(100),
  id_user INT NOT NULL,
  FOREIGN KEY (id_user) REFERENCES users(id),
  FOREIGN KEY (id_hotel) REFERENCES hotel(id),
  FOREIGN KEY (id_carro) REFERENCES carro(id)
);

INSERT INTO `project_trip_planner`.`users` (`id`, `nome`, `login`, `password`) VALUES (null, 'Lucas Milani', 'lukita', 'admin');
