-- Create the tables
CREATE TABLE app_user (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          email VARCHAR(255),
                          password VARCHAR(255),
                          username VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- Insert test data
INSERT INTO app_user (email, password, username) VALUES
    ('test@example.com', '$2a$10$bU0cNHtlyQXX0w45C15ZwehJQ0.Yn8.4bJjz3JHZQqRrN9IY8KfiG', 'testuser');

INSERT INTO role (name) VALUES ('ROLE_USER');

-- Link the user with the role
INSERT INTO user_roles (user_id, role_id)
SELECT (SELECT id FROM app_user WHERE username = 'testuser'), (SELECT id FROM role WHERE name = 'ROLE_USER');
