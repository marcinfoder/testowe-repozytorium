
    drop table APP_USER cascade constraints;

    drop table BOOK cascade constraints;

    drop sequence id_seq;

    create table APP_USER (
        ID number(19,0) not null,
        NAME varchar2(20 char),
        PASSWORD varchar2(50 char),
        primary key (ID)
    );

    create table BOOK (
        ID number(19,0) not null,
        TITLE varchar2(100 char),
        AUTHOR varchar2(50 char),
        AUTHOR_ADDRESS varchar2(100 char),
        primary key (ID)
    );

    create sequence id_seq;
