insert into artist (id, created_at) values ('22222222-2222-2222-2222-222222222222', now());
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aeb',
    '22222222-2222-2222-2222-222222222222',
    'John Doe',
    now()
);
insert into artist_alias (id, artist_id, alias, created_at) values (
    'abac6bde-2934-4c98-b8b5-68c545b68aec',
    '22222222-2222-2222-2222-222222222222',
    'Dohn Joe',
    now()
);
update artist set current_alias_id = 'abac6bde-2934-4c98-b8b5-68c545b68aeb'
    where id = '22222222-2222-2222-2222-222222222222';
--------
insert into artist (id, created_at) values ('33333333-3333-3333-3333-333333333333', now());
insert into artist_alias (id, artist_id, alias, created_at) values (
    '123c6bde-2934-4c98-b8b5-68c545b68aeb',
    '33333333-3333-3333-3333-333333333333',
    'Bezerra da Silva',
    now()
);
update artist set current_alias_id = '123c6bde-2934-4c98-b8b5-68c545b68aeb'
    where id = '33333333-3333-3333-3333-333333333333';
--------
insert into artist (id, created_at) values ('44444444-4444-4444-4444-444444444444', now());
insert into artist_alias (id, artist_id, alias, created_at) values (
    '321c6bde-2934-4c98-b8b5-68c545b68aeb',
    '44444444-4444-4444-4444-444444444444',
    'Hannah Montana',
    now()
);
update artist set current_alias_id = '321c6bde-2934-4c98-b8b5-68c545b68aeb'
    where id = '44444444-4444-4444-4444-444444444444';