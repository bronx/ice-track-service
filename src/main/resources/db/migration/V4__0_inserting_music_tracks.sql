insert into music_track (id, artist_id, genre_id, title, length, created_at)
values (
    '1bac6bde-2934-4c98-b8b5-68c545b68ae1',
    'abac6bde-2934-4c98-b8b5-68c545b68aea',
    'bbac6bde-2934-4c98-b8b5-68c545b68aef',
    'Power',
    172,
    now()
);
insert into music_track_metadata (id, music_track_id, metadata_key, metadata_value)
values ('ffac6bde-2934-4c98-b8b5-58c545b68ae1', '1bac6bde-2934-4c98-b8b5-68c545b68ae1', 'year', '2010');

insert into music_track (id, artist_id, genre_id, title, length, created_at)
values (
    '1bac6bde-2934-4c98-b8b5-68c545b68ae2',
    'abac6bde-2934-4c98-b8b5-68c545b68aea',
    'bbac6bde-2934-4c98-b8b5-68c545b68aef',
    'Gold Digger',
    208,
    now()
);
insert into music_track_metadata (id, music_track_id, metadata_key, metadata_value)
values ('ffac6bde-2934-4c98-b8b5-58c545b68ae2', '1bac6bde-2934-4c98-b8b5-68c545b68ae2', 'year', '2005');
insert into music_track_metadata (id, music_track_id, metadata_key, metadata_value)
values ('ffac6bde-2934-4c98-b8b5-58c545b68ae3', '1bac6bde-2934-4c98-b8b5-68c545b68ae2', 'producer', 'Kanye West');
insert into music_track_metadata (id, music_track_id, metadata_key, metadata_value)
values ('ffac6bde-2934-4c98-b8b5-58c545b68ae4', '1bac6bde-2934-4c98-b8b5-68c545b68ae2', 'producer', 'Jon Brion');
-------
insert into music_track (id, artist_id, genre_id, title, length, created_at)
values (
    '2bac6bde-2934-4c98-b8b5-68c545b68ae1',
    'abac6bde-2934-4c98-b8b5-68c545b68aeb',
    'bbac6bde-2934-4c98-b8b5-68c545b68ae1',
    'Man In The Mirror',
    172,
    now()
);
insert into music_track (id, artist_id, genre_id, title, length, created_at)
values (
    '2bac6bde-2934-4c98-b8b5-68c545b68ae2',
    'abac6bde-2934-4c98-b8b5-68c545b68aeb',
    'bbac6bde-2934-4c98-b8b5-68c545b68ae1',
    'Black or White',
    319,
    now()
);