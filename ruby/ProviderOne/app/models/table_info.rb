class TableInfo

  attr_accessor :name, :create_stmt, :columns

  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ")
    @columns = [];

    tableinfo.each do |info|
      @columns <<  ColumnInfo.get_column(info)
    end

  end

  def set_lookup_column(col_name)
    @columns.each do |col|
      col.is_lookup_key = (col.name == col_name)
    end
  end

  def get_lookup_column
    @columns.each do |col|
      if (col.is_lookup_key)
        return col
      end
    end
    rtr = get_column_by_name("_id")
    if (rtr == nil && @columns.size > 0)
      return @columns[0]
    end
    return rtr
  end

  def get_column_by_name(name)
    @columns.each do |col|
      if (col.name == name)
        return col
      end
    end
  end

  def to_s
    rtr = "\nTable named #{self.name}\n"
    @columns.each do |col|
      rtr += col.to_s + "\n"
    end
    return rtr + "\n"
  end
end
