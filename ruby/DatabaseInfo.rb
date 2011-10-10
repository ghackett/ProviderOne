require 'rubygems'
require 'sqlite3'

class String
  def starts_with?(prefix)
    prefix = prefix.to_s
    self[0, prefix.length] == prefix
  end
end

class DatabaseInfo
  attr_accessor :filename, :tables, :indecies
  
  def initialize(fname, db)
    @filename = fname
    @tables = Hash.new()
    @indecies = []
    
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
    rtr = "\n***************\nDatabase: #{@filename}\n"
    @tables.each_value do |info|
      rtr += info.to_s
    end

    @indecies.each do |index|
      rtr += index.to_s
    end
    rtr += "***************\n\n"
    return rtr
  end

end



class TableInfo
  attr_accessor :name, :create_stmt
  
  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ")
  end

  def to_s
    "Table named #{self.name}, create statment: #{self.create_stmt}"
  end
end

class IndexInfo
  attr_accessor :name, :table_name, :create_stmt

  def initialize(idx_name, tbl_name, crt_sql)
    @name = idx_name
    @table_name = tbl_name
    @create_stmt = crt_sql.to_s.gsub("\r\n", " ").gsub("\n", " ")
  end

  def to_s
    "Index named #{self.name} on table #{self.table_name}, create statment: #{self.create_stmt}"
  end
end