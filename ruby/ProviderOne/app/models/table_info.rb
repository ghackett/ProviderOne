class TableInfo

  attr_accessor :name, :create_stmt, :columns, :camel_name, :update_algorithm, :insert_algorithm

  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ")
    @columns = [];
    @camel_name = @name.camelize
    @update_algorithm = "CONFLICT_NONE"
    @insert_algorithm = "CONFLICT_NONE"

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

  def to_hash
    tbl = {"name" => @name, "update_algorithm" => @update_algorithm, "insert_algorithm" => @insert_algorithm}
    columns = [];
    @columns.each do |col|
      columns << col.to_hash
    end
    tbl['column'] = columns
    return tbl
  end

  def from_hash(hash)
    if (hash['name'] == @name)

      @update_algorithm = hash['update_algorithm']
      @insert_algorithm = hash['insert_algorithm']

      columns = hash['column']
      columns.each do |col|
        mycol = get_column_by_name(col['name'])
        if (mycol != nil)
          mycol.from_hash(col)
        end
      end
    end
  end
end
