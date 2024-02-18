drop table if exists artist cascade;
drop table if exists artist_alias cascade;
drop table if exists music_genre cascade;
drop table if exists music_track cascade;
drop table if exists music_track_metadata cascade;
create table artist (
    create_at timestamp(6) with time zone,
    timestamp timestamp(6) with time zone not null,
    current_alias_id uuid unique,
    id uuid not null,
    primary key (id)
);
create table artist_alias (
    artist_id uuid not null,
    id uuid not null,
    alias varchar(255) not null,
    primary key (id),
    unique (artist_id, alias)
);
create table music_genre (
    id uuid not null,
    name varchar(255) not null,
    primary key (id)
);
create table music_track (
    length integer not null,
    artist_id uuid not null,
    genre_id uuid not null,
    id uuid not null,
    title varchar(255) not null,
    primary key (id)
);
create table music_track_metadata (
    id uuid not null,
    music_track_id uuid not null,
    metadata_key varchar(255) not null,
    metadata_value varchar(255) not null,
    primary key (id),
    unique (metadata_key, metadata_value)
);
alter table if exists artist 
   add constraint FKr48canhwkydanus73f4ptswv4
   foreign key (current_alias_id)
   references artist_alias;
alter table if exists artist_alias 
   add constraint FK5x7ca9pl4tu7xlr9x02c2guxn
   foreign key (artist_id)
   references artist;
alter table if exists music_track 
   add constraint FKiw9kt4bki32dyi2f868vu82wb
   foreign key (artist_id)
   references artist;
alter table if exists music_track 
   add constraint FK83kvhoa738xa59qaw2fkhuy2s
   foreign key (genre_id)
   references music_genre;
alter table if exists music_track_metadata 
   add constraint FKmd1j45fes4fa6sh6hmaqwp6ov
   foreign key (music_track_id)
   references music_track;