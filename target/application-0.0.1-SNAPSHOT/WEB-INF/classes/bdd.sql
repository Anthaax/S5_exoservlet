CREATE TABLE IF NOT EXISTS users (
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  email VARCHAR(30),
  login VARCHAR(30) NOT NULL,
  password VARCHAR(30) NOT NULL,
  datenaissance DATE NOT NULL,
  id INTEGER NOT NULL PRIMARY KEY
);;