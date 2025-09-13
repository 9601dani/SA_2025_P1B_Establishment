CREATE TABLE location (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    zip VARCHAR(20),
    phone VARCHAR(50),
    capacity INT,
    available BOOLEAN,
    description TEXT,
    type VARCHAR(100),
    created_at DATETIME,
    image_url VARCHAR(500)
);

CREATE TABLE opinions (
    id CHAR(36) NOT NULL PRIMARY KEY,
    location_id CHAR(36) NOT NULL,
    username VARCHAR(255),
    score INT,
    opinion_title VARCHAR(255),
    comment TEXT,
    created_at DATETIME,
    CONSTRAINT fk_opinions_location FOREIGN KEY (location_id) REFERENCES location(id)
);
