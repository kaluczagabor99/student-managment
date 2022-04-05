CREATE TABLE student (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR (7) NOT NULL,
    country_of_birth VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL
);

INSERT INTO student (first_name, last_name, gender, country_of_birth,date_of_birth) VALUES
                    ('Bende','Attila','Ferfi','Magyarorszag','1977-01-12',);
                    ('Bende','Attila','Ferfi','Magyarorszag','1977-01-12',);

INSERT INTO student (first_name, last_name, gender, country_of_birth,date_of_birth) VALUES
                    ('Omar','Galvada','Ferfi','Afrika','1990-10-25');

INSERT INTO student (first_name, last_name, gender, country_of_birth,date_of_birth) VALUES
                    ('Roberto','Carlos','No','Brazil','1973-04-10');

