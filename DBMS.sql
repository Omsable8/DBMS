create database restaurant;
use restaurant;

create table items(item_id int primary key,name varchar(20),category varchar(10),price int);
INSERT INTO items (item_id, name, category, price) VALUES
(1, 'Biryani', 'MainCourse', 250),
(2, 'Butter Chicken', 'MainCourse', 300),
(3, 'Paneer Tikka', 'Appetizer', 200),
(4, 'roti', 'MainCourse', 20),
(5, 'Dal Makhani', 'MainCourse', 180),
(6, 'Chicken Tikka Masala', 'MainCourse', 320),
(7, 'Aloo Gobi', 'MainCourse', 150),
(8, 'Palak Paneer', 'MainCourse', 220),
(9, 'Chole Bhature', 'MainCourse', 180),
(10, 'Gulab Jamun', 'Dessert', 100);

create table customers(custid int primary key,billid int,name varchar(20),phno varchar(10),email varchar(20));
INSERT INTO customers (custid, billid, name, phno, email) VALUES
(1, 101, 'John Doe', 1234567890, 'john@example.com'),
(2, 102, 'Jane Smith', 9876543210, 'jane@example.com'),
(3, 103, 'Alice Johnson', 9988776655, 'alice@example.com'),
(4, 104, 'Bob Williams', 1122334455, 'bob@example.com'),
(5, 105, 'Ella Brown', 5544332211, 'ella@example.com'),
(6, 106, 'Mike Davis', 9988776655, 'mike@example.com'),
(7, 107, 'Sarah Wilson', 3344556677, 'sarah@example.com'),
(8, 108, 'David Taylor', 6677889900, 'david@example.com'),
(9, 109, 'Emily Martinez', 1122334455, 'emily@example.com'),
(10, 110, 'Ryan Garcia', 8899001122, 'ryan@example.com');

create table bill(billid int primary key,date date,custid int, orderid int,amount int);
INSERT INTO bill (billid, date, custid, orderid, amount) VALUES
(1, '2024-03-25', 101, 201, 500),
(2, '2024-03-26', 102, 202, 600),
(3, '2024-03-27', 103, 203, 700),
(4, '2024-03-28', 104, 204, 800),
(5, '2024-03-29', 105, 205, 900),
(6, '2024-03-30', 106, 206, 1000),
(7, '2024-03-31', 107, 207, 1100),
(8, '2024-04-01', 108, 208, 1200),
(9, '2024-04-02', 109, 209, 1300),
(10, '2024-04-03', 110, 210, 1400);
create table orders(orderid int primary key,custid int,item_id int, name varchar(20),price int,quantity int,total int);
-- Add 15 values to the orders table with multiple items for 7 customers
INSERT INTO orders (orderid, custid, item_id, name, price, quantity, total) VALUES
(1, 101, 1, 'Biryani', 250, 2, 500),
(2, 101, 2, 'Butter Chicken', 300, 1, 300),
(3, 101, 3, 'Paneer Tikka', 200, 3, 600),
(4, 102, 4, 'Samosa', 50, 4, 200),
(5, 102, 5, 'Dal Makhani', 180, 2, 360),
(6, 103, 6, 'Chicken Tikka Masala', 320, 1, 320),
(7, 103, 7, 'Aloo Gobi', 150, 3, 450),
(8, 104, 8, 'Palak Paneer', 220, 2, 440),
(9, 105, 9, 'Chole Bhature', 180, 1, 180),
(10, 106, 10, 'Gulab Jamun', 100, 5, 500),
(11, 107, 1, 'Biryani', 250, 2, 500),
(12, 108, 2, 'Butter Chicken', 300, 1, 300),
(13, 108, 3, 'Paneer Tikka', 200, 3, 600),
(14, 109, 4, 'Samosa', 50, 4, 200),
(15, 109, 5, 'Dal Makhani', 180, 2, 360);

create table staff(staffid int primary key,name varchar(15),gender varchar(2),DOJ date, designation varchar(10),salary int);
INSERT INTO staff (staffid, name, gender, DOJ, designation, salary) VALUES
(1, 'John Doe', 'M', '2022-01-01', 'Manager', 50000),
(2, 'Jane Smith', 'F', '2022-02-15', 'Assistant', 35000),
(3, 'Alice Johnson', 'F', '2022-03-10', 'Clerk', 25000),
(4, 'Bob Williams', 'M', '2022-04-20', 'Assistant', 35000),
(5, 'Ella Brown', 'F', '2022-05-05', 'Manager', 50000),
(6, 'Mike Davis', 'M', '2022-06-15', 'Clerk', 25000),
(7, 'Sarah Wilson', 'F', '2022-07-25', 'Manager', 50000),
(8, 'David Taylor', 'M', '2022-08-10', 'Assistant', 35000),
(9, 'Emily Martinez', 'F', '2022-09-20', 'Clerk', 25000),
(10, 'Ryan Garcia', 'M', '2022-10-05', 'Manager', 50000),
(11, 'Chef Gordon', 'M', '2022-01-15', 'Chef', 60000),
(12, 'Chef Ramsay', 'M', '2022-02-25', 'Chef', 60000),
(13, 'Chef Julia', 'F', '2022-03-20', 'Chef', 60000),
(14, 'Chef Marco', 'M', '2022-04-30', 'Chef', 60000),
(15, 'Chef Lisa', 'F', '2022-05-15', 'Chef', 60000);


create table chef(staffid int primary key,name varchar(10),cuisine varchar(10));
INSERT INTO chef (staffid, name, cuisine) VALUES
(11, 'Gordon', 'Italian'),
(12, 'Ramsay', 'French'),
(13, 'Julia', 'Mexican'),
(14, 'Marco', 'Chinese'),
(15, 'Lisa', 'Japanese');


select * from items;
select * from bill;
select * from orders;
select * from customers;
select * from staff;
select * from chef;

-- queries 
-- 1
select * from items where category = "MainCourse";

-- 2
select * from items where price > 200 and price < 300;
 
-- 3
select * from orders where total = (select max(total) from orders);

-- 4
select * from orders where total = (select min(total) from orders);

-- 5 - items ordered by each customer 
select custid,count(custid) as items,sum(total)  from orders group by custid; 

-- 6 count types of items
select category,count(category) from items group by category; 

-- 7 show bills whose amount is greater than avg
select avg(amount) from bill;
select * from bill where amount > (select avg(amount) from bill);

-- 8 Bill in descending order
select * from bill order by amount desc;

-- 9 manager's  salary greater than average
(select avg(salary) from staff);
select * from staff where salary  > (select avg(salary) from staff) and designation = "Manager";

-- 10 select customers whose name starts with J
select * from customers where name like "J%";

-- 11 get count of males and female staff
select gender, count(gender) from staff group by gender;

-- 12 get max price of each category 
select category,max(price) from items group by category;

-- 13 join bill and customer table
select * from customers natural join bill; -- on customers.custid = bill.custid;
-- discount, chef salary ,  delete chef, 

-- 14 join customer and order tables
select * from customers right outer join orders on customers.custid = orders.custid;

-- 15 phno and email of customer whose bill amount is greater than 700

select customers.name,customers.email,customers.phno,(bill.amount) from customers natural join bill where bill.amount > 700;

-- 16 give discount of 5% to customers whose amount is greater than 1000 and change their amount
update bill set amount = amount - (0.05 * amount) where amount > 1000;
select * from bill;

 -- 17 make a new column discount in bill table and show percentage.
 alter table bill add column (discount int);
 update bill set discount = case when amount >1000 then 5 
 else 0 end;

-- 18 increase salary of chinese chefs by 2%
update staff set salary = salary + (0.02 *salary) where staffid = (select staffid from chef where cuisine = "Chinese");
select * from staff;

-- 19 fire clerk emily martinez
delete from staff where name = "Emily Martinez" and designation = "Clerk";

-- 20 show salary and other details of all chefs
select staff.staffid,staff.name,staff.gender,staff.DOJ,staff.designation,staff.salary,chef.cuisine from staff inner join chef on staff.staffid = chef.staffid;