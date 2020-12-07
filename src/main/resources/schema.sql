

create table user (user_id bigint not null auto_increment, created datetime(6), email varchar(255), enabled bit not null, password varchar(255), username varchar(255), primary key (user_id), UNIQUE KEY email_UNIQUE (email)) engine=InnoDB;

create table authorities (
    username varchar ( 50 ) not null,
    authority varchar ( 50 ) not null,
    constraint fk_authorities_users foreign key (username) references
    users(username) ,
    UNIQUE KEY username_authority (username, authority)
);



create table comment (comment_id bigint not null auto_increment, content varchar(255), created datetime(6), post_id bigint, user_id bigint, primary key (comment_id)) engine=InnoDB;
create table post (post_id bigint not null auto_increment, created datetime(6), description varchar(255), title varchar(255), url varchar(255), city_id bigint, user_id bigint, primary key (post_id)) engine=InnoDB;
create table token (id bigint not null auto_increment, expiry_date datetime(6), token varchar(255), user_user_id bigint, primary key (id)) engine=InnoDB;
alter table comment add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 foreign key (post_id) references post (post_id);
alter table comment add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references users (user_id);
alter table post add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references users (user_id);
alter table token add constraint FK79keudebybjlldk2o4i0nwqev foreign key (user_user_id) references users (user_id);

