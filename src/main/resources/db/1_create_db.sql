CREATE DATABASE university_db ENCODING 'UTF-8';
CREATE USER dev with password 'admin';
GRANT ALL PRIVILEGES ON DATABASE university_db TO dev;
