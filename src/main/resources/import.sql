
-- Inserindo as Categorias
insert into category ("description") values ('Animes');
insert into category ("description") values ('Movies');
insert into category ("description") values ('Games');
insert into category ("description") values ('Clothes');
insert into category ("description") values ('Utensils');

--Inserindo Users
insert into users("username", "password") values ('admin@gmail.com', 'Dqea/mtuitkfQ0CNCFp54QoW6mZLhWqZ1CbtUcqWegJ0YmJOEDWZjpiqBz31LlpSJ/Ro4Yz5wcVsG7UjDij74g==');
insert into users("username", "password") values ('user@gmail.com', 'rfn8b4ovxCwc0nvvqCrFPBFIApxDRppESGK0krCm0dzvrVBJ7xHLaLCQQmq9L5QSUemLAtO7dwrnhNgq8AVu3A==');
insert into users("username", "password") values ('jonas@gmail.com', 'jsr5hYfv/s8wSMhyWIv1V4yZLLbaOKzw+Vom8g9EVKayRnAX6gtfCyzbUVBjiu9otbYtOljpexynRgj8958Slg==');
insert into users("username", "password") values ('marcos@gmail.com', '7YIgZ3NxnMsIwUlPy5CmgvaqRVhuOG3laIBjWEBbNHOitrozVhdt+JUvyZD/2FL0acRf7wQi1ZT62MN4jFIQhg==');
insert into users("username", "password") values ('maria@gmail.com', 'ylA67BwElpsjIEqYptVMTppJzuEjDR+47NpefsoBWztLND/FVYjeI1FW8SGVIIuLKgLj+LIOdDEliF5xjEUElA==');
insert into users("username", "password") values ('jacinto@gmail.com', 'QwZRtnvGc8yhngjHnd4Z8jctA0X4V0HzvgKe9i9TgNllmhtsMY53KJPn7EN/ZMp/T1Xhft4z8cdx5XNbZAbRow==');

-- Inserindo os Customers
insert into customer ("first_name", "last_name", "age", "gender", "birthday", "user_id") values('Jonas', 'Brothers', 21, 'Masculino', '2002/08/15', 3);
insert into customer ("first_name", "last_name", "age", "gender", "birthday", "user_id") values('Thiago', 'Silva', 20, 'Masculino', '2003/10/07', 4);
insert into customer ("first_name", "last_name", "age", "gender", "birthday", "user_id") values('Maria', 'Derrota', 21, 'Feminino', '2002/09/23', 5);
insert into customer ("first_name", "last_name", "age", "gender", "birthday", "user_id") values('Jacinto', 'Carvalho', 21, 'Masculino', '2002/08/15', 6);

--Inserindo as Roles
insert into "role" ("user_id", "role") values (1, 'Admin');
insert into "role" ("user_id", "role") values (2, 'User');
insert into "role" ("user_id", "role") values (3, 'User');
insert into "role" ("user_id", "role") values (4, 'User');
insert into "role" ("user_id", "role") values (5, 'User');
insert into "role" ("user_id", "role") values (6, 'User');

-- Inserindo os Products
insert into product("description","price","stock","status","categories_id", "material", "dtype")
values ('Cool Keychain from Bleach', 9.99, 15, 'AVAILABLE', 1, 'Aluminum', 'Geek');
insert into product("description","price","stock","status","categories_id", "material", "dtype")
values ('Cool Keychain from Naruto', 9.99, 10, 'AVAILABLE', 1, 'Aluminum', 'Geek');
insert into product("description","price","stock","status","categories_id", "material", "dtype")
values ('Cool Keychain from One Piece', 10.99, 5, 'AVAILABLE', 1, 'Aluminum', 'Geek');
insert into product("description","price","stock","status","categories_id", "material", "dtype")
values ('Cool Keychain from DBZ', 9.99, 8, 'AVAILABLE', 1, 'Aluminum', 'Geek');

-- -- Inserindo os Telefones
insert into phone ("ddd", "number", "is_whatsapp") values ('034','992568471', false);
insert into phone ("ddd", "number", "is_whatsapp") values ('063','984631587', false);
insert into phone ("ddd", "number", "is_whatsapp") values ('015','981596378', false);

insert into phone ("ddd", "number", "is_whatsapp", "customer_id") values ('016','981741524',true, 1);
insert into phone ("ddd", "number", "is_whatsapp", "customer_id") values ('025','999475581',false, 1);
insert into phone ("ddd", "number", "is_whatsapp", "customer_id") values ('042','992568477',true, 2);

-- Inserindo os Estados 
insert into "state" ("name", "acronym") values ('Acre', 'AC');
insert into "state" ("name", "acronym") values ('Alagoas', 'AL');
insert into "state" ("name", "acronym") values ('Amazonas', 'AM');
insert into "state" ("name", "acronym") values ('Bahia', 'BA');
insert into "state" ("name", "acronym") values ('Ceará', 'CE');
insert into "state" ("name", "acronym") values ('Tocantins', 'TO');

-- Inserindo as Cidades dos Estados
-- ACRE
insert into city ("name","state_id") values ('Rio Branco', 1);
insert into city ("name","state_id") values ('Cruzeiro do Sul', 1);
insert into city ("name","state_id") values ('Sena Madureira', 1);
-- ALAGOAS
insert into city ("name","state_id") values ('Maceió', 2);
insert into city ("name","state_id") values ('Arapiraca', 2);
insert into city ("name","state_id") values ('Palmeira dos Índios', 2);
-- AMAZONAS
insert into city ("name","state_id") values ('Manaus', 3);
insert into city ("name","state_id") values ('Parintins', 3);
insert into city ("name","state_id") values ('Itacoatiara', 3);
-- BAHIA
insert into city ("name","state_id") values ('Salvador', 4);
insert into city ("name","state_id") values ('Porto Seguro', 4);
insert into city ("name","state_id") values ('Ilhéus', 4);
-- CEARÁ
insert into city ("name","state_id") values ('Fortaleza', 5);
insert into city ("name","state_id") values ('Juazeiro do Norte', 5);
insert into city ("name","state_id") values ('Sobral', 5);
-- TOCANTINS
insert into city ("name","state_id") values ('Palmas', 6);
insert into city ("name","state_id") values ('Araguaina', 6);
insert into city ("name","state_id") values ('Gurupi', 6);

-- Inserindo os Endereços
insert into "address" ("address","complement","city_id","customer_id") values ('Q. 1106 S Al. 2 Lt. 36','Plano Diretor Sul',10,1);
insert into "address" ("address","complement","city_id","customer_id") values ('Q. 112 N Al. 10 Lt. 45','Plano Diretor Norte',15,1);
insert into "address" ("address","complement","city_id","customer_id") values ('Q. 1207 N Al. 8 Lt. 25','Plano Diretor Norte',7,2);
insert into "address" ("address","complement","city_id","customer_id") values ('Q. 506 S Al. 15 Lt. 16','Plano Diretor Sul',9,3);
