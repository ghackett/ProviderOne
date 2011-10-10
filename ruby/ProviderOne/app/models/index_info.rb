class IndexInfo < ActiveRecord::Base

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
