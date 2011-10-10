class TableInfo < ActiveRecord::Base

  attr_accessor :name, :create_stmt, :table_info, :columns

  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ")
    @columns = [];

    tableinfo.each do |info|
      @columns <<  ColumnInfo.get_column(info)
    end

    @table_info = tableinfo
  end

  def to_s
    rtr = "Table named #{self.name}, create statment: #{self.create_stmt}\n\n#{self.table_info.to_s}\n\n"
    @columns.each do |col|
      rtr += col.to_s + "\n"
    end
    return rtr + "\n\n"
  end
end
