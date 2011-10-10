class TableInfo < ActiveRecord::Base

  attr_accessor :name, :create_stmt

  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ")
  end

  def to_s
    "Table named #{self.name}, create statment: #{self.create_stmt}"
  end
end
