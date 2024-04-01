-- TODO Task 5

drop database if exists pizza_factory;

create database pizza_factory;

use pizza_factory;

create table orders (
    order_id char(8) not null,
    name varchar(64) not null,
    email varchar(64) not null,
    pizza_id char(8) not null

    primary key(order_id)
);