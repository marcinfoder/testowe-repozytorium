
    drop table if exists APP_USER;

    drop table if exists BOOK;

    create table APP_USER (
        ID bigint not null auto_increment,
        NAME varchar(20),
        PASSWORD varchar(50),
        primary key (ID)
    );

    create table BOOK (
        ID bigint not null auto_increment,
        TITLE varchar(100),
        AUTHOR varchar(50),
        AUTHOR_ADDRESS varchar(100),
        primary key (ID)
    );
