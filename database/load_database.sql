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
  endereco VARCHAR(60) NOT NULL,
  checkin VARCHAR(60),
  checkout VARCHAR(60)
);

CREATE TABLE IF NOT EXISTS carro (
  id INT AUTO_INCREMENT PRIMARY KEY, 
  nome VARCHAR(60) NOT NULL,
  marca VARCHAR(60) NOT NULL,
  placa VARCHAR(10) NOT NULL,
  temSeguro BOOLEAN,
  valorSeguro INT,
  img_path VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS trips (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  descrp LONGTEXT,
  id_hotel INT NOT NULL,
  id_carro INT NOT NULL,
  init_date VARCHAR(60),
  final_date VARCHAR(60),
  img_path VARCHAR(100),
  id_user INT NOT NULL,
  FOREIGN KEY (id_user) REFERENCES users(id),
  FOREIGN KEY (id_hotel) REFERENCES hotel(id),
  FOREIGN KEY (id_carro) REFERENCES carro(id)
);

INSERT INTO `project_trip_planner`.`users` (`id`, `nome`, `login`, `password`) VALUES (null, 'Lucas Milani', 'lukita', 'admin');
INSERT INTO `project_trip_planner`.`carro` (`id`, `nome`, `marca`, `placa`, `temSeguro`, `valorSeguro`, `img_path`) VALUES (null, 'Carro top', 'Modelo Topissimo', '123-ABCD', '1', 1200, 'Teste');
INSERT INTO `project_trip_planner`.`hotel` (`id`, `nome`, `endereco`, `checkin`, `checkout`) VALUES (null, 'Hotel Chique', 'Rua Tal, 123', '05/06/2024', '09/06/2024');
INSERT INTO `project_trip_planner`.`trips` (`id`, `nome`, `descrp`, `id_hotel`, `id_carro`, `init_date`, `final_date`, `img_path`, `id_user`) VALUES (null, 'Viagem para a praia', 'Viagem em familia', '1', '1', '05/06/2024', '10/06/2024', 'teste','1');
