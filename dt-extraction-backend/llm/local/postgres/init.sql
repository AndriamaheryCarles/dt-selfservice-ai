--CREATE DATABASE datawarehouse;
--GRANT ALL PRIVILEGES ON lake.* TO 'ibanfirst'@'%';
--GRANT ALL PRIVILEGES ON metabase.* TO 'ibanfirst'@'%';
--FLUSH PRIVILEGES;

SELECT datname FROM pg_database;
