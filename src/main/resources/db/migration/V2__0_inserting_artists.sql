insert into artist (id, created_at) values ('abac6bde-2934-4c98-b8b5-68c545b68aea', now());
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aeb',
    'abac6bde-2934-4c98-b8b5-68c545b68aea',
    'John Doe',
    now()
);
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aec',
    'abac6bde-2934-4c98-b8b5-68c545b68aea',
    'Doe John',
    now()
);
update artist set current_alias_id = 'abac6bde-2934-4c98-b8b5-68c545b68aeb'
    where id = 'abac6bde-2934-4c98-b8b5-68c545b68aea';