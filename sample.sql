CREATE TABLE "my_table" (
"_id" INTEGER PRIMARY KEY AUTOINCREMENT,
"my_boolean" BOOL,
"my_double" DOUBLE,
"my_float" FLOAT,
"my_int" INTEGER,
"my_long" LONG,
"my_char" CHAR,
"my_string" TEXT,
"my_blob" BLOB,
"my_time" DATETIME
);

CREATE INDEX "sample_idx" ON "my_table" ("my_double");