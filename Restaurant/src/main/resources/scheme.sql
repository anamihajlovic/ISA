CREATE TABLE users(
  id         INTEGER      NOT NULL AUTO_INCREMENT,
  email      VARCHAR(30)  NOT NULL,
  password   VARCHAR(20)  NOT NULL,
  first_name VARCHAR(20)  NOT NULL,
  last_name  VARCHAR(30)  NOT NULL,
  address    VARCHAR(40)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE(email),
  UNIQUE(password),
  UNIQUE(first_name),
  UNIQUE(last_name),
  UNIQUE(address), 
) ENGINE = InnoDB;

CREATE TABLE system_managers (
  id         INTEGER      NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB;

