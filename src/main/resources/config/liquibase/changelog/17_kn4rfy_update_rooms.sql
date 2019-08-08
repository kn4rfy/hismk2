drop table bms.rooms;

create table bms.rooms
(
  id                 uuid not null primary key,

  room_no            varchar,
  bed_no             varchar,
  description        varchar,
  price              numeric,
  status             varchar,

  created_by         varchar(50),
  created_date       timestamp(6) default CURRENT_TIMESTAMP,
  last_modified_by   varchar(50),
  last_modified_date timestamp(6) default CURRENT_TIMESTAMP
);