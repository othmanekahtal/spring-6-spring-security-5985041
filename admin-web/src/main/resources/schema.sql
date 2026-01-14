CREATE TABLE customers(
    customer_id UUID primary key,
    name varchar(64) not null,
    contact_name varchar(128) not null,
    email varchar(128) not null,
    phone varchar(24) not null
);

CREATE TABLE orders(
    order_id UUID primary key,
    customer_id UUID not null,
    order_info varchar (2048) not null,
    foreign key (customer_id) references customers(customer_id)
);

CREATE TABLE users (
    username varchar_ignorecase(50) primary key,
    password varchar_ignorecase(500) not null,
    enabled boolean not null
);

CREATE TABLe authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    foreign key(username) references users(username)
);

CREATE UNIQUE INDEX idx_auth_username on authorities (username,authority);

