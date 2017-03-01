-- USERS
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('ana@gmail.com', 'ana', 'Ana', 'Mihajlovic','guest', false, true, 'las849pqe')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('jelenakalabic@gmail.com', 'jelena', 'Jelena', 'Kalabic','guest', false, true, 'fsg656daf')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('tamara@gmail.com', 'tamara', 'Tamara', 'Mrksic','guest', false, true, 'hdg645gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('marina@gmail.com', 'marina', 'Marina', 'Zaric','guest', false, true, 'loi645gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('carna@gmail.com', 'carna', 'Carna', 'Stevic','guest', false, true, 'pdg545gst')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('tijana@gmail.com', 'tijana', 'Tijana', 'Djukic','guest', false, true, 'uyr645g8t')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('danilo@gmail.com', 'danilo', 'Danilo', 'Dimitrijevic','guest', false, true, 'mki566tgt')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('bakir@gmail.com', 'baki', 'Bakir', 'Niksic','guest',false, true, 'lwe267cfr')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('mirko@gmail.com', 'mirko', 'Mirko', 'Odalovic','guest', false, true, 'law369zde')
insert into guests(email, password, first_name, last_name,user_role, first_login, active, guest_a_code) values ('mika@gmail.com', 'mika', 'Mika', 'Mikic','guest', false, false, 'ljy769zde')



--FRIENDSHIPS
insert into friendships(sender_id, receiver_id, status) values (1, 2, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (1, 3, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (2, 6, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (3, 5, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (8, 2, 'accepted')
insert into friendships(sender_id, receiver_id, status) values (7, 1, 'sent')
insert into friendships(sender_id, receiver_id, status) values (6, 2, 'sent')
insert into friendships(sender_id, receiver_id, status) values (8, 3, 'sent')



-- RESTAURANTS
insert into restaurants(name, restaurant_type,number, street, city, country,ratings,latitude,longitude) values ('Lanterna', 'italijanski',55,'Bulevar Oslobodjenja','Novi Sad','Srbija',0.0,45.2539119,19.8359807)
insert into restaurants(name, restaurant_type,number, street, city, country,ratings,latitude,longitude) values ('Dva stapica', 'kineski',3,'Sremska','Novi Sad','Srbija',0.0 ,45.2496541,19.841903)
insert into restaurants(name, restaurant_type,number, street, city, country,ratings,latitude,longitude) values ('Plava frajla', 'srpski',15,'Strazilovska ','Novi Sad','Srbija',0.0,45.24885769999999,19.8487232 )
insert into restaurants(name, restaurant_type,number, street, city, country,ratings,latitude,longitude) values ('Veliki', 'srpski',15,'Pasiceva','Novi Sad','Srbija',0.0,45.2574419,19.8475388 )


insert into bidders(email, password, first_name, last_name,first_login,user_role) values ('bid1@gmail.com', 'bid1', 'bid1', 'bid2',false,'bidder')
insert into bidders(email, password, first_name, last_name,first_login,user_role) values ('bid2@gmail.com', 'bid2', 'bid2', 'bid2',true,'bidder')


insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id,cook_type) values ('cook1@gmail.com','cook1','cook1','cook1','1994-07-27','no39','M',false,'cook',1,'undefined')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('waiter1@gmail.com','waiter1','waiter1','waiter1','1994-07-25','no39','M',false,'waiter',1)
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role,restaurant_id) values ('bar1@gmail.com','bar1','bar1','bar1','1994-07-25','no42','M',false,'bartender',1)

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id, cook_type) values ('cook2@gmail.com','cook2','cook1','cook2','1994-07-27','no39','M',true,'cook',2,'pastaCook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('waiter2@gmail.com','waiter2','waiter2','waiter2','1994-07-25','no39','M',true,'waiter',2)
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('bar2@gmail.com','bar2','bar1','bar2','1994-07-25','no39','M',false,'bartender',2)

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id,cook_type) values ('cook3@gmail.com','cook3','cook3','cook3','1994-07-27','no39','M',false,'cook',3,'fishCook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('waiter3@gmail.com','waiter3','waiter3','waiter2','1994-07-25','no39','M',false,'waiter',3)
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('bar3@gmail.com','bar3','bar3','bar3','1994-07-25','no39','M',false,'bartender',3)

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id, cook_type) values ('cook4@gmail.com','cook4','cook4','cook4','1994-07-27','no39','M',true,'cook',4,'pastaCook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('waiter4@gmail.com','waiter4','waiter4','waiter4','1994-07-25','no39','M',true,'waiter',4)
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('bar4@gmail.com','bar4','bar4','bar4','1994-07-25','no39','M',false,'bartender',4)

insert into cooks(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id, cook_type) values ('cook5@gmail.com','cook5','cook5','cook5','1994-07-27','no39','M',true,'cook',1,'pastaCook')
insert into waiters(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('waiter5@gmail.com','waiter5','waiter5','waiter5','1994-07-25','no39','M',false,'waiter',1)
insert into bartenders(email, password, first_name, last_name,birthday,shoes_size,clothes_size,first_login,user_role, restaurant_id) values ('bar5@gmail.com','bar5','bar5','bar5','1994-07-25','no39','M',false,'bartender',1)

insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resman1@gmail.com','resman1','first 1','last 1',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resman2@gmail.com','resman2','first 2','last 2',false,'resManager',2)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('reeman2@gmail.com','resmadn2','fisrst 2','last 2',false,'resManager',2)


insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmwan1@gmail.com','resmawn1','firwst 1','last 1',false,'resManager',3)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmedan2@gmail.com','resqmean2','fierst 2','last 2',false,'resManager',4)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmaeen1@gmail.com','resmsan1','firest 1','last 1',false,'resManager',1)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resccman2@gmail.com','resmasn2','firest 2','last 2',false,'resManager',2)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('resmqqan1@gmail.com','resmwan1','firset 1','last 1',false,'resManager',3)
insert into restaurant_managers(email, password, first_name, last_name,first_login,user_role,restaurant_id) values ('rewesman2@gmail.com','resmwan2','fiwrst 2','last 2',false,'resManager',4)


insert into system_managers(email, password, first_name, last_name,preset_manager,first_login,user_role)values('sysman1@gmail.com','sysman1','sysman1','sysman1','yes',false,'sysManager')


--RESTAURANT MANAGERS IN RESTAURANTS
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,1)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(2,2)

insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(3,3)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(4,4)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(1,5)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(2,6)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(3,7)
insert into res_managers_in_restuarants(restaurant_id,res_manager_id)values(4,8)


--EMPLOYEED
insert into restaurant_waiters(restaurant_id,waiter_id)values(1,1)
insert into restaurant_waiters(restaurant_id,waiter_id)values(2,2)
insert into restaurant_waiters(restaurant_id,waiter_id)values(3,3)
insert into restaurant_waiters(restaurant_id,waiter_id)values(4,4)
insert into restaurant_waiters(restaurant_id,waiter_id)values(1,5)


insert into restaurant_cooks(restaurant_id,cook_id)values(1,1)
insert into restaurant_cooks(restaurant_id,cook_id)values(2,2)
insert into restaurant_cooks(restaurant_id,cook_id)values(3,3)
insert into restaurant_cooks(restaurant_id,cook_id)values(4,4)
insert into restaurant_cooks(restaurant_id,cook_id)values(1,5)


insert into restaurant_bartenders(restaurant_id,bartender_id)values(1,1)
insert into restaurant_bartenders(restaurant_id,bartender_id)values(2,2)
insert into restaurant_bartenders(restaurant_id,bartender_id)values(3,3)
insert into restaurant_bartenders(restaurant_id,bartender_id)values(4,4)
insert into restaurant_bartenders(restaurant_id,bartender_id)values(1,5)


--BIDDERS
insert into restaurant_bidders(restaurant_id,bidder_id)values(1,1)
insert into restaurant_bidders(restaurant_id,bidder_id)values(1,2)


--DISH
insert into dishes(name,text, price,dish_type)values('Pica','opis',250,'pasta')
insert into dishes(name,text, price,dish_type)values('Testo','opis',400,'pasta')
insert into dishes(name,text, price,dish_type)values('Palacinke','opis',200,'dessert')
insert into dishes(name,text, price,dish_type)values('Saran','opis',800,'fish')

--DRINK
insert into drinks(name,text, price,drink_type)values('Sok','opis',130,'soda')
insert into drinks(name,text, price,drink_type)values('Kafa','opis',120,'coffee')
insert into drinks(name,text, price,drink_type)values('Caj','opis',110,'coffee')
insert into drinks(name,text, price,drink_type)values('Limunada','opis',90,'soda')


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
insert into restuarant_menu(restaurant_id,dish_id)values(1,4)
insert into restuarant_menu(restaurant_id,dish_id)values(2,3)

--DRINK_CARD
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,1)
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,2)
insert into restaurant_drink_card(restaurant_id,drink_id)values(1,4)
insert into restaurant_drink_card(restaurant_id,drink_id)values(2,3)


--WORK SHIFTS
insert into work_shifts (start_time,end_time,shift_type,day)values('08:00:00','14:00:00','firstShift','2017-02-27')
insert into work_shifts (start_time,end_time,shift_type,day)values('14:00:01','23:55:00','secondShift','2017-02-27')
insert into work_shifts (start_time,end_time,shift_type,day)values('08:00:00','14:00:00','firstShift','2017-03-01')
insert into work_shifts (start_time,end_time,shift_type,day)values('14:00:01','20:00:00','secondShift','2017-03-01')
insert into work_shifts (start_time,end_time,shift_type,day)values('08:00:00','14:00:00','firstShift','2017-03-02')
insert into work_shifts (start_time,end_time,shift_type,day)values('14:00:01','20:00:00','secondShift','2017-03-02')
insert into work_shifts (start_time,end_time,shift_type,day)values('07:00:00','12:00:00','firstShift','2017-03-02')
insert into work_shifts (start_time,end_time,shift_type,day)values('14:00:00','18:00:00','secondShift','2017-03-02')



insert into work_shift_waiters values(1, 5)
insert into work_shift_waiters values(2, 1)
insert into work_shift_waiters values(3, 5)
insert into work_shift_waiters values(4, 5)
insert into work_shift_waiters values(5, 1)
insert into work_shift_waiters values(6, 5)

insert into work_shift_waiters values(7, 2)
insert into work_shift_waiters values(8, 2)

insert into work_shift_cooks values(1, 1)
insert into work_shift_cooks values(1, 1)
insert into work_shift_cooks values(2, 5)
insert into work_shift_cooks values(3, 5)
insert into work_shift_cooks values(5, 5)
insert into work_shift_cooks values(6, 1)

insert into work_shift_cooks values(7, 2)


insert into work_shift_bartenders values(1, 1)
insert into work_shift_bartenders values(2, 5)
insert into work_shift_bartenders values(2, 1)
insert into work_shift_bartenders values(3, 1)
insert into work_shift_bartenders values(5, 5)
insert into work_shift_bartenders values(6, 1)

insert into work_shift_bartenders values(8, 2)

--WORK DAYS
insert into work_days(day)values('2017-02-27')
insert into work_days(day)values('2017-03-01')
insert into work_days(day)values('2017-03-02')
insert into work_days(day)values('2017-03-02')


insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (1,1)
insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (1,2)

insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (2,3)
insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (2,4)

insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (3,5)
insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (3,6)


insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (4, 7)
insert into work_shifts_in_work_days (work_day_id,work_shift_id)values (4, 8)



insert into restaurant_work_days(restaurant_id,work_day_id)values(1,1)
insert into restaurant_work_days(restaurant_id,work_day_id)values(1,2)
insert into restaurant_work_days(restaurant_id,work_day_id)values(1,3)
insert into restaurant_work_days(restaurant_id,work_day_id)values(2,4)
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

insert into offers(id_bidder,id_res_order,accepted,delivery_time,garancy,total_price)values(1,1,'waiting','2017-03-03','garancy',20)
insert into offers(id_bidder,id_res_order,accepted,delivery_time,garancy,total_price)values(2,1,'waiting','2017-04-03','garancy',10)

insert into offers(id_bidder,id_res_order,accepted,delivery_time,garancy,total_price)values(1,2,'yes','2017-04-03','garancy',30)
insert into offers(id_bidder,id_res_order,accepted,delivery_time,garancy,total_price)values(2,2,'no','2017-04-03','garancy',10)

insert into bidder_offers(bidder_id,offer_id)values(1,1)
insert into bidder_offers(bidder_id,offer_id)values(1,2)
insert into bidder_offers(bidder_id,offer_id)values(2,3)
insert into bidder_offers(bidder_id,offer_id)values(2,4)

insert into units(offer_id,unit_id)values(1,1)
insert into units(offer_id,unit_id)values(1,2)

--ORDERS
insert into orders(restaurant_id,reservation_id,acceptance_time,order_date,order_status,drinks_status,dish_status, guest_id, accepted_waiter) values (1, 1,'19:00:00','2017-02-27','ordered', 'ordered', 'ordered', 2, 1)
insert into orders(restaurant_id,reservation_id,acceptance_time,order_date,order_status,drinks_status,dish_status, guest_id, accepted_waiter) values (1, 1,'19:30:00','2017-02-27', 'ordered', 'ordered', 'ordered', 3, 5)

insert into orders(restaurant_id,reservation_id,acceptance_time,order_date,order_status,drinks_status,dish_status) values (1, 1,'19:00:00','2017-03-01','ordered', 'ordered', 'ordered')
insert into orders(restaurant_id,reservation_id,acceptance_time,order_date,order_status,drinks_status,dish_status) values (1, 1,'19:30:00','2017-03-01', 'ordered', 'ordered', 'ordered')
insert into orders(restaurant_id,reservation_id,acceptance_time,order_date,order_status,drinks_status,dish_status) values (1, 1,'11:00:00','2017-03-02','ordered', 'ordered', 'ordered')
insert into orders(restaurant_id,reservation_id,acceptance_time,order_date,order_status,drinks_status,dish_status) values (1, 1,'12:00:00','2017-03-02', 'ordered', 'ordered', 'ordered')

insert into ordered_drinks(order_id,drink_id) values (1,1)
insert into ordered_drinks(order_id,drink_id) values (1,1)
insert into ordered_drinks(order_id,drink_id) values (1,2)

insert into ordered_drinks(order_id,drink_id) values (2,2)
insert into ordered_drinks(order_id,drink_id) values (2,2)
insert into ordered_drinks(order_id,drink_id) values (2,1)
--**********************01.03*****************************
insert into ordered_drinks(order_id,drink_id) values (3,1)
insert into ordered_drinks(order_id,drink_id) values (3,1)
insert into ordered_drinks(order_id,drink_id) values (3,2)

insert into ordered_drinks(order_id,drink_id) values (4,2)
insert into ordered_drinks(order_id,drink_id) values (4,2)
insert into ordered_drinks(order_id,drink_id) values (4,1)
---************************02.03**************************
insert into ordered_drinks(order_id,drink_id) values (5,1)
insert into ordered_drinks(order_id,drink_id) values (5,1)
insert into ordered_drinks(order_id,drink_id) values (5,2)

insert into ordered_drinks(order_id,drink_id) values (6,2)
insert into ordered_drinks(order_id,drink_id) values (6,2)
insert into ordered_drinks(order_id,drink_id) values (6,1)
--********************************************************

insert into all_ordered_dishes(order_id, dish_id, dish_status) values(2, 1, 'ordered')
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(2, 1, 'ordered')
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(2, 2, 'ordered')

insert into ordered_dish(order_id,dish_order_id) values (2,1)
insert into ordered_dish(order_id,dish_order_id) values (2,2)
insert into ordered_dish(order_id,dish_order_id) values (2,3)

--**********************01.03***************************************
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(4, 1, 'ordered')
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(4, 1, 'ordered')
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(4, 2, 'ordered')

insert into ordered_dish(order_id,dish_order_id) values (4,4)
insert into ordered_dish(order_id,dish_order_id) values (4,5)
insert into ordered_dish(order_id,dish_order_id) values (4,6)
--**********************02.03***************************************
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(6, 1, 'ordered')
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(6, 1, 'ordered')
insert into all_ordered_dishes(order_id, dish_id, dish_status) values(6, 2, 'ordered')

insert into ordered_dish(order_id,dish_order_id) values (6,7)
insert into ordered_dish(order_id,dish_order_id) values (6,8)
insert into ordered_dish(order_id,dish_order_id) values (6,9)


---CONFIGURATION 

insert into segments(segment_type,color) values ('Inside','#00bfff')
insert into segments(segment_type,color) values ('Smoke','#ffff00')


insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('four', 'exists', 'Smoke', 0, 0,'#00bfff','first')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('four', 'exists', 'Smoke', 0, 1,'#00bfff','first')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('four', 'exists', 'Inside', 0, 2,'#00bfff','first')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('four', 'not_exists','Inside', 0, 3,'#00bfff','first')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('four', 'exists','Inside', 0, 4,'#00bfff','first')

insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('two', 'exists','Inside', 1, 0,'#ffff00','second')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('two', 'exists','Inside', 1, 1,'#ffff00','second')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('two', 'not_exists','Inside', 1, 2,'#ffff00','second')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('two', 'exists','Inside', 1, 3,'#ffff00','second')
insert into tables(table_size, table_state, segment, x_pos, y_pos,segment_color,reon) values ('two', 'exists','Inside', 1, 4,'#ffff00','second')

insert into segment_tables(segment_id,table_id) values(1,1)
insert into segment_tables(segment_id,table_id) values(1,2)
insert into segment_tables(segment_id,table_id) values(1,3)
insert into segment_tables(segment_id,table_id) values(1,4)
insert into segment_tables(segment_id,table_id) values(1,5)
insert into segment_tables(segment_id,table_id) values(2,6)
insert into segment_tables(segment_id,table_id) values(2,7)
insert into segment_tables(segment_id,table_id) values(2,8)
insert into segment_tables(segment_id,table_id) values(2,9)
insert into segment_tables(segment_id,table_id) values(2,10)

insert into restaurant_segments(restaurant_id, segment_id) values (1,1)
insert into restaurant_segments(restaurant_id, segment_id) values (1,2)


---RESPONSABILITIES

insert into responsabilities(reon)values('first')
insert into responsabilities(reon)values('second')
insert into responsabilities(reon)values('first')
insert into responsabilities(reon)values('second')

insert into waiter_responsabilities values(1,1)
insert into waiter_responsabilities values(1,2)
insert into waiter_responsabilities values(3,5)
insert into waiter_responsabilities values(4,1)


insert into work_shift_responsabilites values(3,3)
insert into work_shift_responsabilites values(3,4)
insert into work_shift_responsabilites values(4,1)
insert into work_shift_responsabilites values(4,2)



--RESERVATIONS

insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-02-26', '17:30', '19:45', '1;2', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-02-26', '18:30', '20:00', '6', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-02-27', '17:30', '19:45', '7', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-02-28', '17:30', '19:45', '7', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-03-03', '17:30', '19:45', '10', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-03-03', '12:30', '13:45', '9', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-03-02', '17:30', '19:45', '2', false)
insert into reservations(res_id, res_name, guest_id, date, start_time, end_time, tables_id, deleted) values (1, 'Lanterna', 1, '2017-03-02', '12:30', '13:45', '1;3', false)


--RESERVATION_ORDERS
insert into reservation_orders(reservation_id, order_id) values (1, 1)
insert into reservation_orders(reservation_id, order_id) values (1, 2)


--POZIVNICE--
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(1,1,2,'Jelena Kalabic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(1,1,3,'Tamara Mrksic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(2,1,2,'Jelena Kalabic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(2,1,3,'Tamara Mrksic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(2,1,8,'Bakir Niksic','pending')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(3,1,2,'Jelena Kalabic','rejected')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(3,1,5,'Carna Stevic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(4,1,2,'Jelena Kalabic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(4,1,5,'Carna Stevic','accepted')
insert into invitations(reservation_id,sender_id,friend_id,friend_name,status)values(4,1,6,'Tijana Djukic','accepted')

--RESERVATION TABLES
insert into reservation_tables(table_id, reservation_id) values (1,1)
insert into reservation_tables(table_id, reservation_id) values (2,1)
insert into reservation_tables(table_id, reservation_id) values (6,2)
insert into reservation_tables(table_id, reservation_id) values (7,3)
insert into reservation_tables(table_id, reservation_id) values (7,4)
insert into reservation_tables(table_id, reservation_id) values (10,5)
insert into reservation_tables(table_id, reservation_id) values (9,6)
insert into reservation_tables(table_id, reservation_id) values (2,7)
insert into reservation_tables(table_id, reservation_id) values (1,8)
insert into reservation_tables(table_id, reservation_id) values (3,8)
--BILLS
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-19','09:05:00',1,500)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-19','09:15:00',1,4500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-20','09:05:00',1,1000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-20','19:15:00',1,3500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-21','09:05:00',1,1500)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-21','19:15:00',1,5500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-22','09:05:00',1,1200)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-22','19:15:00',1,7500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-23','09:05:00',1,1900)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-23','19:15:00',1,2500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-24','09:05:00',1,3100)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-24','09:15:00',1,2500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-25','09:05:00',1,5500)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-25','19:15:00',1,2500)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','09:05:00',1,1000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','09:15:00',1,2500)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','09:25:00',1,700)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','10:25:00',1,2900)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','10:35:00',1,3000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','10:55:00',1,1000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','11:05:00',1,5000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','12:25:00',1,1000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','14:05:00',2,900)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','15:25:00',2,1000)

insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:05:00',2,3000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:25:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:35:00',2,5000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:55:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','16:55:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:05:00',2,3000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:25:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:35:00',2,5000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','17:55:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','18:55:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','19:05:00',2,3000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','19:25:00',2,2000)
insert into bills(bill_date,bill_time,waiter_id,total_price)values('2017-02-26','19:35:00',2,5000)


insert into restaurant_bills values(1,1)
insert into restaurant_bills values(1,2)
insert into restaurant_bills values(1,3)
insert into restaurant_bills values(1,4)
insert into restaurant_bills values(1,5)
insert into restaurant_bills values(1,6)
insert into restaurant_bills values(1,7)
insert into restaurant_bills values(1,8)
insert into restaurant_bills values(1,9)
insert into restaurant_bills values(1,10)
insert into restaurant_bills values(1,11)
insert into restaurant_bills values(1,12)
insert into restaurant_bills values(1,13)
insert into restaurant_bills values(1,14)
insert into restaurant_bills values(1,15)
insert into restaurant_bills values(1,16)
insert into restaurant_bills values(1,17)
insert into restaurant_bills values(1,18)
insert into restaurant_bills values(1,19)
insert into restaurant_bills values(1,20)
insert into restaurant_bills values(1,21)
insert into restaurant_bills values(1,22)
insert into restaurant_bills values(1,23)
insert into restaurant_bills values(1,24)
insert into restaurant_bills values(1,25)
insert into restaurant_bills values(1,26)
insert into restaurant_bills values(1,27)
insert into restaurant_bills values(1,28)
insert into restaurant_bills values(1,29)
insert into restaurant_bills values(1,30)
insert into restaurant_bills values(1,31)
insert into restaurant_bills values(1,32)
insert into restaurant_bills values(1,33)
insert into restaurant_bills values(1,34)
insert into restaurant_bills values(1,35)
insert into restaurant_bills values(1,36)
insert into restaurant_bills values(1,37)

--GRADES
insert into grades(guest_id, restaurant_id, reservation_id, waiter_id, order_id, order_grade, waiter_grade, restaurant_grade, grade_date) values (3, 3, 2, 3, 4, 2.0, 3.0, 3.0, '2016-02-26')
insert into grades(guest_id, restaurant_id, reservation_id, waiter_id, order_id, order_grade, waiter_grade, restaurant_grade, grade_date) values (1, 3, 2, 3, 3, 5.0, 3.0, 4.0, '2016-02-26')
insert into grades(guest_id, restaurant_id, reservation_id, waiter_id, order_id, order_grade, waiter_grade, restaurant_grade, grade_date) values (3, 2, 3, 2, 6, 3.0, 3.0, 3.0, '2017-02-10')
insert into grades(guest_id, restaurant_id, reservation_id, waiter_id, order_id, order_grade, waiter_grade, restaurant_grade, grade_date) values (4, 2, 3, 2, 5, 1.0, 2.0, 2.0, '2017-02-10')


