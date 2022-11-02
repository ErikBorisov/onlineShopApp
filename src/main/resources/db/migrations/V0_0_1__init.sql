create table if not exists category
(
    id      bigint auto_increment
    primary key,
    name    varchar(255) null,
    created datetime(6)  null
    );

create table if not exists products
(
    id              bigint auto_increment
    primary key,
    activation_date datetime(6)  null,
    count           bigint       null,
    created         datetime(6)  null,
    name            varchar(255) null,
    price           bigint       null,
    status          varchar(255) null,
    updated         datetime(6)  null
    );

create table if not exists product_category
(
    product_id  bigint not null,
    category_id bigint not null,
    constraint FK5w81wp3eyugvi2lii94iao3fm
    foreign key (product_id) references products (id),
    constraint FKkud35ls1d40wpjb5htpp14q4e
    foreign key (category_id) references category (id)
    );

create table if not exists users
(
    id               bigint auto_increment
    primary key,
    activation_date  datetime(6)  null,
    activation_token varchar(256) null,
    created          datetime(6)  null,
    email            varchar(255) null,
    password         varchar(255) null,
    first_name       varchar(255) null,
    is_active        bit          null,
    last_name        varchar(255) null,
    role             varchar(255) null,
    updated          datetime(6)  null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
    unique (email)
    );
