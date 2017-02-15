-- USERS
insert into guests(email, password, first_name, last_name,first_login,user_role) values ('jelenakalabic@gmail.com', 'Jelena', 'Jelena', 'Kalabic',true,'guest')
insert into guests(email, password, first_name, last_name,first_login,user_role) values ('tamaramrksic@gmail.com', 'Tamara', 'Tamara', 'Mrksic',true,'guest')

insert into bidders(email, password, first_name, last_name,first_login,user_role) values ('anamihajlovic@gmail.com', 'pass', 'Ana', 'Mihajlovic',true,'bidder')

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('cook1@gmail.com','cook1','cook1','cook1','1994-07-27','no39','M',true,'cook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('waiter1@gmail.com','waiter1','waiter1','waiter1','1994-07-25','no39','M',true,'waiter')
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('bar1@gmail.com','bar1','bar1','bar1','1994-07-25','no39','M',true,'bartender')

insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role) values ('resman1@gmail.com','resman1','resman1','resman1',true,'resManager')
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role) values ('resman2@gmail.com','resman2','resman2','resman2',true,'resManager')

insert into system_managers(email, password, first_name, last_name,preset_manager,first_login,user_role)values('sysman1@gmail.com','sysman1','sysman1','sysman1','yes',true,'sysManager')


-- RESTAURANTS
insert into restaurants(name, restaurant_type, street, city, country,ratings) values ('Lanterna', 'italijanski','Bulevar Oslobodjenja 55','Novi Sad','Srbija',0.0 )
insert into restaurants(name, restaurant_type, street, city, country,ratings) values ('Veliki', 'srpski','Pasiceva 15','Novi Sad','Srbija',0.0 )

--RESTAURANT BIDDERS
insert into restaurant_bidders(restaurant_id, bidder_id) values(1,1)


--RESTAURANT MANAGERS IN RESTAURANTS
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,1)

--DISH
insert into dish(name,text, price)values('Pica','',250)
insert into dish(name,text, price)values('Testo','',400)
insert into dish(name,text, price)values('Palacinke','',200)

--DRINK
insert into drink(name,text, price)values('Sok','',130)
insert into drink(name,text, price)values('Kafa','',120)
insert into drink(name,text, price)values('Caj','',110)

--MENU
insert into restuarant_menu(restaurant_id,dish_id)values(1,1)
insert into restuarant_menu(restaurant_id,dish_id)values(1,2)
insert into restuarant_menu(restaurant_id,dish_id)values(2,3)

--DRINK_CARD
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,1)
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,2)
insert into restaurant_drink_card(restaurant_id,drink_id)values(2,3)