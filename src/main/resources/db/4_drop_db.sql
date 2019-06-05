UPDATE pg_database SET datallowconn = 'false' WHERE datname = 'university_db';

SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'university_db';

DROP DATABASE university_db;