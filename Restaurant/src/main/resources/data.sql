-- USERS
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('jelena@gmail.com', 'jelena', 'Jelena', 'Kalabic','guest', false, true, 'fsg656daf')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('tamara@gmail.com', 'tamara', 'Tamara', 'Mrksic','guest', false, true, 'hdg645gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('ana@gmail.com', 'ana', 'Ana', 'Mihajlovic','guest', false, true, 'las849pqe')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('marina@gmail.com', 'marina', 'Marina', 'Zaric','guest', false, true, 'loi645gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('carna@gmail.com', 'carna', 'Carna', 'Stevic','guest', false, true, 'pdg545gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('tijana@gmail.com', 'tijana', 'Tijana', 'Djukic','guest', false, true, 'uyr645g8t')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('danilo@gmail.com', 'danilo', 'Danilo', 'Dimitrijevic','guest', false, true, 'mki566tgt')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('bakir@gmail.com', 'baki', 'Bakir', 'Niksic','guest',false, true, 'lwe267cfr')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('mirko@gmail.com', 'mirko', 'Mirko', 'Odalovic','guest', false, true, 'law369zde')



--FRIENDSHIPS
insert into friendships(sender_id, receiver_id, status) values (1, 3, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (1, 6, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (3, 5, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (8, 2, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (7, 1, 'sent')
insert into friendships(sender_id, receiver_id, status) values (6, 2, 'sent')



--EMPLOYED
insert into bidders(email, password, first_name, last_name,first_login,user_role) values ('bid1@gmail.com', 'bid1', 'bid1', 'bid2',false,'bidder')

insert into bidders(email, password, first_name, last_name,first_login,user_role) values ('bid2@gmail.com', 'bid2', 'bid2', 'bid2',true,'bidder')


insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('cook1@gmail.com','cook1','cook1','cook1','1994-07-27','no39','M',true,'cook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('waiter1@gmail.com','waiter1','waiter1','waiter1','1994-07-25','no39','M',true,'waiter')
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('bar1@gmail.com','bar1','bar1','bar1','1994-07-25','no42','M',true,'bartender')

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('cook2@gmail.com','cook2','cook1','cook2','1994-07-27','no39','M',true,'cook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('waiter2@gmail.com','waiter2','waiter2','waiter2','1994-07-25','no39','M',true,'waiter')
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role) values ('bar2@gmail.com','bar2','bar1','bar2','1994-07-25','no39','M',false,'bartender')

insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resman1@gmail.com','resman1','first 1','last 1',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('reeman2@gmail.com','resmadn2','fisrst 2','last 2',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmwan1@gmail.com','resmawn1','firwst 1','last 1',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmedan2@gmail.com','resqmean2','fierst 2','last 2',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmaeen1@gmail.com','resmsan1','firest 1','last 1',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resccman2@gmail.com','resmasn2','firest 2','last 2',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmqqan1@gmail.com','resmwan1','firset 1','last 1',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('rewesman2@gmail.com','resmwan2','fiwrst 2','last 2',false,'resManager',1)




insert into system_managers(email, password, first_name, last_name,preset_manager,first_login,user_role)values('sysman1@gmail.com','sysman1','sysman1','sysman1','yes',false,'sysManager')


-- RESTAURANTS
insert into restaurants(name, restaurant_type, street, city, country,ratings) values ('Lanterna', 'italijanski','Bulevar Oslobodjenja 55','Novi Sad','Srbija',0.0 )
insert into restaurants(name, restaurant_type, street, city, country,ratings) values ('Veliki', 'srpski','Pasiceva 15','Novi Sad','Srbija',0.0 )

--RESTAURANT MANAGERS IN RESTAURANTS
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,1)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,2)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,3)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,4)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,5)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,6)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,7)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,8)

--EMPLOYEED
insert into restaurant_waiters(restaurant_id,waiter_id)values(1,1)
insert into restaurant_waiters(restaurant_id,waiter_id)values(1,2)

insert into restaurant_cooks(restaurant_id,cook_id)values(1,1)
insert into restaurant_cooks(restaurant_id,cook_id)values(1,2)

insert into restaurant_bartenders(restaurant_id,bartender_id)values(1,1)
insert into restaurant_bartenders(restaurant_id,bartender_id)values(1,2)

--BIDDERS
insert into restaurant_bidders(restaurant_id,bidder_id)values(1,1)
insert into restaurant_bidders(restaurant_id,bidder_id)values(1,2)


--DISH
insert into dishes(name,text, price,dish_type)values('Pica','opis',250,'pizza')
insert into dishes(name,text, price,dish_type)values('Testo','opis',400,'pasta')
insert into dishes(name,text, price,dish_type)values('Palacinke','opis',200,'dessert')

--DRINK
insert into drinks(name,text, price,drink_type)values('Sok','opis',130,'soda')
insert into drinks(name,text, price,drink_type)values('Kafa','opis',120,'coffee')
insert into drinks(name,text, price,drink_type)values('Caj','opis',110,'coffee')


--WARENHOUSE
insert into foodstuffs(name,quantity)values('Kafa',120)
insert into foodstuffs(name,quantity)values('Caj',50)
insert into foodstuffs(name,quantity)values('Sok',120)
insert into foodstuffs(name,quantity)values('Jaje',50)
insert into foodstuffs(name,quantity)values('Paradajz',20)
insert into foodstuffs(name,quantity)values('Hleb',10)


--WARENHOUSE IN RESTAURANT
insert into foodstuffs_in_restuarants(restaurant_id,foodstuff_id)values(1,1)
insert into foodstuffs_in_restuarants(restaurant_id,foodstuff_id)values(1,2)
insert into foodstuffs_in_restuarants(restaurant_id,foodstuff_id)values(1,3)
insert into foodstuffs_in_restuarants(restaurant_id,foodstuff_id)values(2,4)
insert into foodstuffs_in_restuarants(restaurant_id,foodstuff_id)values(2,5)
insert into foodstuffs_in_restuarants(restaurant_id,foodstuff_id)values(2,6)


--MENU
insert into restuarant_menu(restaurant_id,dish_id)values(1,1)
insert into restuarant_menu(restaurant_id,dish_id)values(1,2)
insert into restuarant_menu(restaurant_id,dish_id)values(2,3)

--DRINK_CARD
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,1)
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,2)
insert into restaurant_drink_card(restaurant_id,drink_id)values(2,3)


--WORK SHIFTS
insert into work_shifts (start_time,end_time,waiter,cook,bartender)values('08:00:00','14:00:00',1,1,1)
insert into work_shifts (start_time,end_time,waiter,cook,bartender)values('14:00:01','20:00:00',2,2,2)

--WORK DAYS

insert into work_days (day)values('2016-02-26')
insert into work_days(day)values('2016-02-27')
insert into work_days(day)values('2016-03-28')

insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (1,1)
insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (1,2)

insert into restaurant_work_days(restaurant_id,work_day_id)values(1,1)
insert into restaurant_work_days(restaurant_id,work_day_id)values(1,2)
insert into restaurant_work_days(restaurant_id,work_day_id)values(1,3)
------------------------------------------------------------------------------------------------------

insert into res_orders(end_date,restaurant_name)values('29/02/2017','Lanterna')
insert into res_orders(end_date,restaurant_name)values('23/01/2017','Lanterna')
insert into res_orders(end_date,restaurant_name)values('13/01/2017','Veliki')

insert into restaurant_orders(restaurant_id,order_id)values (1,1)
insert into restaurant_orders(restaurant_id,order_id)values (1,2)
insert into restaurant_orders(restaurant_id,order_id)values (2,3)

insert into res_order_units(order_foodstuff,order_quantity,res_order)values ('kafa',50,1)
insert into res_order_units(order_foodstuff,order_quantity,res_order)values ('mleko',20,1)
insert into res_order_units(order_foodstuff,order_quantity,res_order)values ('caj',30,1)

insert into order_foodstuffs(order_id,unit_id)values(1,1)
insert into order_foodstuffs(order_id,unit_id)values(1,2)
insert into order_foodstuffs(order_id,unit_id)values(2,3)

insert into offer_units(unit_id,price)values(1,10)
insert into offer_units(unit_id,price)values(2,10)
insert into offer_units(unit_id,price)values(3,null)
