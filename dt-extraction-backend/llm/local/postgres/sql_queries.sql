CREATE TABLE IF NOT EXISTS sql_queries (
id SERIAL PRIMARY KEY,
question TEXT NOT NULL,
sql_queries TEXT NOT NULL,
timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);