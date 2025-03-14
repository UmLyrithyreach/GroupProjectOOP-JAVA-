CREATE TABLE suppliers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255),
    contact VARCHAR(100)
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    salary DECIMAL(7,2) NOT NULL,
    startDate DATE NOT NULL,
    role VARCHAR(50) NOT NULL,
    password VARCHAR(100) DEFAULT NULL,
    isManager TINYINT(1) DEFAULT 0,
    username VARCHAR(100) DEFAULT NULL
);

CREATE TABLE clothes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    size VARCHAR(10) NOT NULL,
    price DECIMAL(7,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    style VARCHAR(50),
    supplierId INT,

    FOREIGN KEY (supplierId) REFERENCES suppliers (id);
);