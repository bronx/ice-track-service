insert into artist (id, created_at) values ('abac6bde-2934-4c98-b8b5-68c545b68aea', now());
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aeb',
    'abac6bde-2934-4c98-b8b5-68c545b68aea',
    'Ye',
    now()
);
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aec',
    'abac6bde-2934-4c98-b8b5-68c545b68aea',
    'Kanye West',
    now()
);
update artist set current_alias_id = 'abac6bde-2934-4c98-b8b5-68c545b68aeb'
    where id = 'abac6bde-2934-4c98-b8b5-68c545b68aea';
---------
insert into artist (id, created_at) values ('abac6bde-2934-4c98-b8b5-68c545b68aeb', now());
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aed',
    'abac6bde-2934-4c98-b8b5-68c545b68aeb',
    'Michael Jackson',
    now()
);
update artist set current_alias_id = 'abac6bde-2934-4c98-b8b5-68c545b68aed'
    where id = 'abac6bde-2934-4c98-b8b5-68c545b68aeb';