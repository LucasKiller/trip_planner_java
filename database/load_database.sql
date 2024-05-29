CREATE DATABASE project_trip_planner;
USE project_trip_planner;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(60) NOT NULL,
  password VARCHAR(60) NOT NULL
);

CREATE TABLE trips (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  descrp LONGTEXT,
  hotel VARCHAR(60),
  carro VARCHAR(60),
  init_date DATE,
  final_date DATE,
  img_path VARCHAR(100),
  id_user INT NOT NULL,
  FOREIGN KEY (id_user) REFERENCES users(id)
);

