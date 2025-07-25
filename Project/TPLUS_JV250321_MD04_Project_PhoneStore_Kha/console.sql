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


-- ------------------ Admin Permission ------------------

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


-- --------------------- Product ------------------------

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
create procedure check_product_name_is_exist(
    name_in int,
    out is_exist int
)
begin
    select count(*)
    into is_exist
    from Product
    where name = name_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure check_product_id_is_exist(
    id_in int,
    out is_exist int
)
begin
    select count(*)
    into is_exist
    from Product
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
    insert into Product (name, brand,
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
    from Product
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
    update Product
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
    delete
    from Product
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
    select *
    from Product
    where brand = brand_in;
end &&
DELIMITER &&

-- Search By Price Range

DELIMITER &&
create procedure find_by_product_price_range(
    price_in decimal(12, 2),
    price_out decimal(12, 2)
)
begin
    select *
    from Product
    where price between price_in and price_out;
end &&
DELIMITER &&

-- Search By Stock Available

DELIMITER &&
create procedure find_by_product_stock_available(
    stock_in int,
    stock_out int
)
begin
    select *
    from Product
    where stock between stock_in and stock_out;
end &&
DELIMITER &&

-- ------------------------------------------------------


-- --------------------- Customer ------------------------

-- Find All Customer

DELIMITER &&
create procedure find_all_customer()
begin
    select * from Customer;
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Create Customer

DELIMITER &&
create procedure check_customer_email_is_exist(
    in email_in int,
    out is_exist int
)
begin
    select count(*)
    into is_exist
    from Customer
    where email = email_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure check_customer_name_is_exist(
    in id_in int,
    out is_exist int
)
begin
    select count(*)
    into is_exist
    from Customer
    where id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure add_customer(
    name_in varchar(100),
    phone_in varchar(20),
    email_in varchar(100),
    address_in varchar(255)
)
begin
    insert into Customer(name, phone, email, address)
    values (name_in,
            phone_in,
            email_in,
            address_in);
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Update Customer

DELIMITER &&
create procedure find_customer_by_id(
    id_in int
)
begin
    select *
    from Customer
    where id = id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure update_customer(
    id_in int,
    name_in varchar(100),
    phone_in varchar(20),
    email_in varchar(100),
    address_in varchar(255)
)
begin
    update Customer
    set name    = name_in,
        phone   = phone_in,
        email   = email_in,
        address = address_in
    where id = id_in;
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Delete Customer

DELIMITER &&
create procedure delete_customer(
    id_in int
)
begin
    delete
    from Customer
    where id = id_in;
end &&
DELIMITER &&

-- ------------------------------------------------------


-- ---------------------- Invoice -----------------------

-- Find All Invoice

DELIMITER &&
create procedure find_all_invoice()
begin
    select c.name, i.*
    from Invoice i
             inner join Customer c on i.customer_id = c.id;
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Add Invoice

DELIMITER &&
create procedure add_invoice(
    customer_id_in int,
    created_at_in datetime
)
begin


    insert into Invoice(customer_id, created_at, total_amount)
    values (customer_id_in,
            created_at_in,
            0);
end &&
DELIMITER &&

-- ------------------------------------------------------

-- Search Invoice

DELIMITER &&
create procedure find_invoice_by_customer_name(
    customer_name_in varchar(100)
)
begin
    select c.name, i.*
    from Invoice as i
             inner join Customer as c on i.customer_id = c.id
    where c.name = customer_name_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure find_invoice_by_date(
    date_in date
)
begin
    select *
    from Invoice
    where day(created_at) = day(date_in)
      and month(created_at) = month(date_in)
      and year(created_at) = year(date_in);
end &&
DELIMITER &&

-- ------------------------------------------------------


-- ------------------ Invoice Details -------------------

-- Add Invoice Details

DELIMITER &&
create procedure check_quantity_vs_product_stock(
    quantity_in int,
    product_stock_in int
)
begin

end &&
DELIMITER &&

DELIMITER &&
create procedure add_invoice_details(
    invoice_id_in int,
    product_id_in int,
    quantity_in int,
    unit_price_in decimal(12, 2)
)
begin
    IF exists(select 1
              from Invoice_Details
              where product_id = product_id_in
                and invoice_id = invoice_id_in) THEN

        -- Nếu đã có thì cập nhật
        update Invoice_Details
        set quantity   = quantity + quantity_in,
            unit_price = unit_price_in
        where product_id = product_id_in
          and invoice_id = invoice_id_in;

    ELSE

        -- Nếu chưa có thì thêm mới
        insert into Invoice_Details(invoice_id, product_id, quantity, unit_price)
        values (invoice_id_in,
                product_id_in,
                quantity_in,
                unit_price_in);

        update Invoice
        set total_amount = quantity_in * unit_price_in
        where id = invoice_id_in;

    END IF;
end &&
DELIMITER &&

-- Total Amount By Day

DELIMITER &&
create procedure statitic_total_amount_by_day(
    day_in date
)
begin
    select date(created_at), sum(total_amount)
    from Invoice
    where date(created_at) = day_in
    group by date(created_at);
end &&
DELIMITER &&

call statitic_total_amount_by_day('2000/07/21');

-- Total Amount By Month

DELIMITER &&
create procedure statitic_total_amount_by_day(
    month_in int,
    year_in int
)
begin
    select date(created_at), sum(total_amount)
    from Invoice
    where month(created_at) = month_in
      and year(created_at) = year_in
    group by date(created_at);
end &&
DELIMITER &&

-- Total Amount By Year
