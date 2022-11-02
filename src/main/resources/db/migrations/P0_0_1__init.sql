create table if not exists onlineappdemo.category
(
    id      bigint auto_increment
    primary key,
    created datetime(6)  null,
    name    varchar(255) not null
    );


create table if not exists onlineappdemo.products
(
    id          bigint auto_increment
    primary key,
    count       bigint       null,
    created     datetime(6)  null,
    image       varchar(255) null,
    name        varchar(255) null,
    price       bigint       null,
    status      varchar(255) null,
    updated     datetime(6)  null,
    category_id bigint       not null,
    constraint FK1cf90etcu98x1e6n9aks3tel3
    foreign key (category_id) references onlineappdemo.category (id)
    );

create table if not exists onlineappdemo.users
(
    id               bigint auto_increment
    primary key,
    activation_date  datetime(6)  null,
    activation_token varchar(256) null,
    created          datetime(6)  null,
    email            varchar(255) null,
    first_name       varchar(255) null,
    is_active        bit          null,
    last_name        varchar(255) null,
    password         varchar(255) null,
    role             varchar(255) null,
    updated          datetime(6)  null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
    unique (email)
    );
