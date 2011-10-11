require 'rubygems'
require 'sqlite3'

class DatabaseInfo
  attr_accessor :filepath, :filename, :tables, :indecies, :upload_id

  def initialize(fname, upload_id)
    @upload_id = upload_id
    @filepath = DatabaseInfo.get_sqlite_path(@upload_id)
    @filename = fname
    @tables = Hash.new()
    @indecies = []

    db = SQLite3::Database.new(@filepath)

    db.results_as_hash = true

    db.execute( "select * from sqlite_master" ) do |row|
      name = row[:name.to_s]
      type = row[:type.to_s]
      tbl_name = row[:tbl_name.to_s]
      sql = row[:sql.to_s]

      if (! name.to_s.starts_with? "sqlite")
        if (type.downcase == "table")
          @tables[name] = TableInfo.new(name, sql, db.table_info(name))
        elsif (type.downcase == "index")
          @indecies << IndexInfo.new(name, tbl_name, sql)
        end
      end

    end

  end

  def to_s
    rtr = "\n***************\nDatabase: #{@filename} at #{@filepath}\n"
    @tables.each_value do |info|
      rtr += info.to_s
    end

    @indecies.each do |index|
      rtr += index.to_s
    end
    rtr += "***************\n\n"
    return rtr
  end

  def self.get_sqlite_path(upload_id)
    path = "tmp/sqltmp/"

    if (!Dir.exists?(path))
      Dir.mkdir(path)
    end

    path += upload_id + ".sqlite"
    return path
  end

end
