-- create database phone_store;
use phone_store;

create table Admin
(
    id       int primary key auto_increment,
    username varchar(50)  not null unique,
    password varchar(255) not null
);

create table Product
(
    id    int primary key auto_increment,
    name  varchar(100)   not null,
    brand varchar(50)    not null,
    price decimal(12, 2) not null,
    stock int            not null
);

create table Customer
(
    id      int primary key auto_increment,
    name    varchar(100) not null,
    phone   varchar(20),
    email   varchar(100) unique,
    address varchar(255)
);

create table Invoice
(
    id           int primary key auto_increment,
    customer_id  int, -- FK

    created_at   datetime default current_timestamp,
    total_amount decimal(12, 2) not null,

    constraint fk_customer_id_at_invoice
        foreign key (customer_id)
            references Customer (id)
);

create table Invoice_Details
(
    id         int primary key auto_increment,
    invoice_id int, -- FK
    product_id int, -- FK
    quantity   int            not null,
    unit_price decimal(12, 2) not null,

    constraint fk_invoice_id
        foreign key (invoice_id)
            references Invoice (id),

    constraint fk_product_id
        foreign key (product_id)
            references Product (id)
);

-- ------------------------------------------------------

-- Admin Permission

DELIMITER &&
create procedure login_admin(
    in username_in varchar(50),
    in password_in varchar(255)
)
begin
select *
from Admin
where username = username_in
  and password = password_in;
end;
DELIMITER &&

-- ------------------------------------------------------

-- Display Product

DELIMITER &&
create procedure find_all_product()
begin
select * from Product;
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Add Product

DELIMITER &&
create procedure check_product_is_exist(
    id_in int,
    out is_exist int
)
begin
select count(*)
into is_exist
from product
where id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure add_product(
    name_in varchar(255),
    brand_in varchar(50),
    price_in decimal(12, 2),
    stock_in int
)
begin
insert into product (name, brand,
                     price, stock)
values (name_in,
        brand_in, price_in,
        stock_in);
end;
DELIMITER &&

-- ------------------------------------------------------

-- Find product by ID

DELIMITER &&
create procedure find_product_by_id(
    in id_in int
)
begin
select *
from product
where id = id_in;
end &&
DELIMITER &&

-- Update Product

DELIMITER &&
create procedure update_product(
    id_in int,
    name_in varchar(100),
    brand_in varchar(50),
    price_in decimal(12, 2),
    stock_in int
)
begin
update product
set name  = name_in,
    brand = brand_in,
    price = price_in,
    stock = stock_in
where id = id_in;
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Delete Product
DELIMITER &&
create procedure delete_product(
    id_in int
)
begin
delete from product
where id = id_in;
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Search By Brand
DELIMITER &&
create procedure find_by_product_brand(
    brand_in varchar(50)
)
begin
select * from Product
where brand = brand_in;
end &&
DELIMITER &&

-- ------------------------------------------------------