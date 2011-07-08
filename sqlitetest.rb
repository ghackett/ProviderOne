#ruby sqlite test
require 'rubygems'
require 'sqlite3'
require 'DatabaseInfo'



filename = ARGV[0]

db = SQLite3::Database.new(filename)

dbinfo = DatabaseInfo.new(filename, db)

dbinfo.print_detail
