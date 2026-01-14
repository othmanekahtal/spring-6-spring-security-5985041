CREATE TABLE customers
(
    customer_id  UUID primary key,
    name         varchar(64)  not null,
    contact_name varchar(128) not null,
    email        varchar(128) not null,
    phone        varchar(24)  not null
);

CREATE TABLE orders
(
    order_id    UUID primary key,
    customer_id UUID          not null,
    order_info  varchar(2048) not null,
    foreign key (customer_id) references customers (customer_id)
);
CREATE TABLE users
(
    username VARCHAR_IGNORECASE(50) PRIMARY KEY,
    password VARCHAR_IGNORECASE(500) NOT NULL,
    enabled  BOOLEAN NOT NULL
);

CREATE TABLE authorities
(
    username  VARCHAR_IGNORECASE(50) NOT NULL,
    authority VARCHAR_IGNORECASE(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX idx_auth_username ON authorities (username, authority);