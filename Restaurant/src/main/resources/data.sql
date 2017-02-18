-- USERS
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('jelena@gmail.com', 'jelena', 'Jelena', 'Kalabic','guest', false, true, 'fsg656daf')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('tamara@gmail.com', 'tamara', 'Tamara', 'Mrksic','guest', false, true, 'hdg645gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('marina@gmail.com', 'marina', 'Marina', 'Zaric','guest', false, true, 'loi645gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('carna@gmail.com', 'carna', 'Carna', 'Stevic','guest', false, true, 'pdg545gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('tijana@gmail.com', 'tijana', 'Tijana', 'Djukic','guest', false, true, 'uyr645g8t')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('danilo@gmail.com', 'danilo', 'Danilo', 'Dimitrijevic','guest', false, true, 'mki566tgt')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('bakir@gmail.com', 'baki', 'Bakir', 'Niksic','guest', false, true, 'lwe267cfr')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('mirko@gmail.com', 'mirko', 'Mirko', 'Odalovic','guest', false, true, 'law369zde')


insert into bidders(email, password, first_name, last_name,first_login,user_role) values ('bid1@gmail.com', 'bid1', 'bid1', 'bid1',true,'bidder')

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('cook1@gmail.com','cook1','cook1','cook1','1994-07-27','no39','M',true,'cook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('waiter1@gmail.com','waiter1','waiter1','waiter1','1994-07-25','no39','M',true,'waiter')
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('bar1@gmail.com','bar1','bar1','bar1','1994-07-25','no42','M',true,'bartender')

insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role) values ('resman1@gmail.com','resman1','first 1','last 1',true,'resManager')
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role) values ('resman2@gmail.com','resman2','first 2','last 2',true,'resManager')

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