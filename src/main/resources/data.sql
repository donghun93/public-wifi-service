create database wifi_db;
create user 'wifi'@'%' identified by 'wifi1234';
GRANT ALL PRIVILEGES ON *.* TO 'wifi'@'%';

flush PRIVILEGES;

use wifi_db;

DROP TABLE if exists wifi;
DROP TABLE if exists history;

create table wifi
(
    wifi_id       bigint primary key auto_increment,
    mgr_no        varchar(50),
    wrdofc        varchar(30),
    main_name     varchar(200),
    address1      varchar(200),
    address2      varchar(100),
    instl_floor   varchar(100),
    instl_ty      varchar(100),
    instl_mby     varchar(100),
    svc_se        varchar(100),
    cmcwr         varchar(100),
    cnstc_year    varchar(8),
    inout_door    varchar(10),
    remars3       varchar(100),
    lat           double,
    lnt           double,
    work_datetime timestamp
);

create table history
(
    history_id  bigint primary key auto_increment,
    x_location  double,
    y_location  double,
    search_date timestamp
);