CREATE TABLE app_user (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          email VARCHAR(255),
                          password VARCHAR(255),
                          username VARCHAR(255) UNIQUE
);
