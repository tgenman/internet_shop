create table address (
    id bigint not null,
    building varchar(255),
    city varchar(255),
    country varchar(255),
    flat varchar(255),
    postal_code integer not null,
    street varchar(255),
    primary key (id)
) engine=InnoDB;

create table product (
     id bigint not null,
     active bit not null,
     category varchar(255),
     filename varchar(255),
     price integer not null,
     quantity integer not null,
     title varchar(255),
     volume integer not null,
     weight integer not null,
     primary key (id)
) engine=InnoDB;

create table cart (
    id bigint not null,
    session_id varchar(255),
    user_id bigint,
    primary key (id)
) engine=InnoDB;

create table cart_products (
    cart_id bigint not null,
    products integer,
    products_key bigint not null,
    primary key (cart_id, products_key)
) engine=InnoDB;

create table order_entity (
    id bigint not null,
    address_string varchar(255),
    date_of_order datetime,
    status_of_delivery varchar(255),
    status_of_payment varchar(255),
    type_of_delivery varchar(255),
    type_of_payment varchar(255),
    user_id bigint,
    primary key (id)
) engine=InnoDB;

create table order_entity_product (
    order_entity_id bigint not null,
    list_of_products integer,
    list_of_products_key bigint not null,
    primary key (order_entity_id, list_of_products_key)
) engine=InnoDB;

create table product_parameter (
    product_id bigint not null,
    parameters varchar(255),
    parameters_key varchar(255) not null,
    primary key (product_id, parameters_key)
) engine=InnoDB;

create table user (
    id bigint not null,
    date_of_birth datetime,
    email varchar(255),
    enabled bit,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(60),
primary key (id)) engine=InnoDB;

create table user_addresses (
    user_id bigint not null,
    address_id bigint not null,
    primary key (user_id, address_id)
) engine=InnoDB;

create table user_roles (
    user_id bigint not null,
    roles varchar(255)
) engine=InnoDB;

create table verification_token (
    id bigint not null,
    expiry_date datetime,
    token varchar(255),
    user_id bigint not null,
primary key (id)) engine=InnoDB;

create table password_reset_token (
    id bigint not null,
    expiry_date datetime,
    token varchar(255),
    user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;


insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

alter table cart
    add constraint FKl70asp4l4w0jmbm1tqyofho4o
        foreign key (user_id) references user (id);

alter table cart_products
    add constraint FK8x7wyc1ajldym6lj0uoi57ono
    foreign key (products_key) references product (id);

alter table cart_products
    add constraint FKnlhjc091rdu9k5c8u9xwp280w
    foreign key (cart_id) references cart (id);

alter table order_entity
    add constraint FKdikderaycp8pwoigw489wohfa
    foreign key (user_id) references user (id);

alter table order_entity_product
    add constraint FK59u8ap2q6itt6bq1kxjhkxrep
    foreign key (list_of_products_key) references product (id);

alter table order_entity_product
    add constraint FK2dg1g0qjsth4l3thsoaxpl6y4
    foreign key (order_entity_id) references order_entity (id);

alter table password_reset_token
    add constraint FK5lwtbncug84d4ero33v3cfxvl
    foreign key (user_id) references user (id);

alter table product_parameter
    add constraint FK78fyfsydxu5ncui96o4pgafwl
    foreign key (product_id) references product (id);

alter table user_addresses
    add constraint FKd2jqv60joaqmw8h94mdpx0f55
    foreign key (address_id) references address (id);

alter table user_addresses
    add constraint FKfm6x520mag23hvgr1oshaut8b
    foreign key (user_id) references user (id);

alter table user_roles
    add constraint FK55itppkw3i07do3h7qoclqd4k
    foreign key (user_id) references user (id);

alter table verification_token
    add constraint FKrdn0mss276m9jdobfhhn2qogw
    foreign key (user_id) references user (id);