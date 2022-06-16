create table reservation (
     id numeric(19,0) constraint pk_reservation primary key,
     start_date date not null,
     days numeric(19,0),
     owner_id numeric(19,0),
     dog_id numeric(19,0)
);

create sequence reservation_seq increment by 20 minvalue 10000 maxvalue 999999999999999999 cache 20;
