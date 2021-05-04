create table if not exists role
(
    id   serial
        constraint role_pk primary key,
    name varchar(20) not null
);

create table if not exists users
(
    id         serial
        constraint users_pk primary key,
    first_name varchar(20),
    last_name  varchar(20),
    email      varchar(20)  not null unique,
    password   varchar(100) not null
);
create unique index user_email_index
    on users (email);
create table if not exists users_roles
(
    id      serial primary key,
    role_id integer
        constraint users_role_id_fk
            references role,
    user_id integer
        constraint role_users_id_fk
            references users
);

create table if not exists quizzes
(
    id      serial
        constraint quizzes_pk primary key,
    title   varchar(50),
    subject varchar(50),
    user_id integer
        constraint quizzes_users_id_fk references users,
    result  varchar(50)

);
create table if not exists questions
(
    id          serial
        constraint questions_pk primary key,
    title       varchar(50),
    description varchar(500),
    image       varchar(255)

);
create table if not exists answers
(
    id          serial
        constraint answers_pk primary key,
    text        varchar(255),
    is_correct  boolean,
    question_id int,
    constraint questions_fk foreign key (question_id) references questions (id)
);

insert into role(name)
values ('ROLE_ADMIN');
insert into role(name)
values ('ROLE_TUTOR');
insert into role(name)
values ('ROLE_STUDENT');

