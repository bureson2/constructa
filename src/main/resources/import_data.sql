
-- Import locations
COPY location (location_id, city, country, descriptive_number, latitude, longitude, post_code, street)
    FROM 'example_data/locations.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', QUOTE '');


-- -- Import users
-- LOAD DATA LOCAL INFILE 'classpath:example_data/users.csv'
-- INTO TABLE user
-- FIELDS TERMINATED BY ','
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS
-- (id, first_name, last_name, ...);
--
-- -- Import companies
-- LOAD DATA LOCAL INFILE 'classpath::example_data/companies.csv'
-- INTO TABLE company
-- FIELDS TERMINATED BY ','
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS
-- (id, din, cin, name, ...);