--conn / as sysdba

create user REST_SAMPLE identified by REST_SAMPLE default tablespace USERS quota unlimited on USERS;

-- Create the role
create role REST_DEFAULT;
-- Grant/Revoke role privileges
grant select_catalog_role to REST_DEFAULT;
-- Grant/Revoke system privileges
grant alter session to REST_DEFAULT;
grant create any index to REST_DEFAULT;
grant create cluster to REST_DEFAULT;
grant create database link to REST_DEFAULT;
grant create dimension to REST_DEFAULT;
grant create indextype to REST_DEFAULT;
grant create materialized view to REST_DEFAULT;
grant create operator to REST_DEFAULT;
grant create procedure to REST_DEFAULT;
grant create role to REST_DEFAULT;
grant create sequence to REST_DEFAULT;
grant create session to REST_DEFAULT;
grant create synonym to REST_DEFAULT;
grant create table to REST_DEFAULT;
grant create trigger to REST_DEFAULT;
grant create type to REST_DEFAULT;
grant create view to REST_DEFAULT;
grant global query rewrite to REST_DEFAULT;
grant query rewrite to REST_DEFAULT;
grant REST_DEFAULT to REST_SAMPLE;



--------------

create table book(
       id number(13),
       author varchar2(50),
       title varchar2(100)
);

create sequence id_seq;

insert into book(id, author, title, author_address) values(id_seq.nextval, 'Kazimierz Myszkowski', 'moje zycie', 'Wrocław, Strzegomska 42B');
insert into book(id, author, title, author_address) values(id_seq.nextval, 'Anna Nowak', 'o kotach i psach', 'Wrocław, Kazimierza Wielkiego 11');
insert into book(id, author, title, author_address) values(id_seq.nextval, 'Zygmunt Kowalski', 'w lesie i w ogrodzie', 'Wrocław, Piękna 56');
insert into book(id, author, title, author_address) values(id_seq.nextval, 'Zygmunt Kowalski', 'moje kury i koguty', 'Oleśnica, Słowackiego 12');
insert into book(id, author, title, author_address) values(id_seq.nextval, 'Tomek Budzik', 'wesele Tomka', 'Warszawa, Ogrodowa 1');
insert into book(id, author, title, author_address) values(id_seq.nextval, 'Zenon Elf', 'chata skrzata', 'Wrocław, Rymarska 14');

create table app_user(
		id number(13),
    name varchar2(20),
    password varchar2(50)
);

insert into app_user values(id_seq.nextval, 'rod', 'a564de63c2d0da68cf47586ee05984d7');
insert into app_user values(id_seq.nextval, 'scott', 'a564de63c2d0da68cf47586ee05984d7');
insert into app_user values(id_seq.nextval, 'dr_scott', 'a564de63c2d0da68cf47586ee05984d7');

commit;

------------mysql

    
    create table USER_AUTHORITY (
		ID bigint not null auto_increment,
        NAME varchar(20),
        AUTHORITY varchar(20),
        primary key (ID)
        );
    
    insert into book(author, title, author_address) values('Kazimierz Myszkowski', 'moje zycie', 'Wrocław, Strzegomska 42B');
insert into book(author, title, author_address) values('Anna Nowak', 'o kotach i psach', 'Wrocław, Kazimierza Wielkiego 11');
insert into book(author, title, author_address) values('Zygmunt Kowalski', 'w lesie i w ogrodzie', 'Wrocław, Piękna 56');
insert into book(author, title, author_address) values('Zygmunt Kowalski', 'moje kury i koguty', 'Oleśnica, Słowackiego 12');
insert into book(author, title, author_address) values('Tomek Budzik', 'wesele Tomka', 'Warszawa, Ogrodowa 1');
insert into book(author, title, author_address) values('Zenon Elf', 'chata skrzata', 'Wrocław, Rymarska 14');

insert into app_user(name, password) values('rod', 'a564de63c2d0da68cf47586ee05984d7');
insert into app_user(name, password) values('scott', 'a564de63c2d0da68cf47586ee05984d7');
insert into app_user(name, password) values('dr_scott', 'a564de63c2d0da68cf47586ee05984d7');

insert into user_authority(name, authority) values('rod', 'ROLE_ADMIN');
insert into user_authority(name, authority) values('scott', 'ROLE_ADMIN');
insert into user_authority(name, authority) values('rod', 'guest');
