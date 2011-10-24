class TableInfo

  attr_accessor :name, :create_stmt, :columns, :camel_name, :cap_camel_name, :update_algorithm, :insert_algorithm, :lower_name, :drop_stmt

  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @lower_name = @name.downcase
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ").gsub("\"", "\\\"")
    @drop_stmt = "DROP TABLE IF EXISTS \\\"" + @name + "\\\""
    @columns = [];
    @camel_name = @name.camelize
    @camel_name[0] = @camel_name.first.downcase
    @cap_camel_name = @name.camelize

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





  def process_file_content(file_content, dbinfo)
    file_content = file_content.gsub("{SqlTableName}", @name);
    file_content = file_content.gsub("{LowerTableName}", @lower_name);
    file_content = file_content.gsub("{CamelTableName}", @camel_name);
    file_content = file_content.gsub("{CapCamelTableName}", @cap_camel_name);
    file_content = file_content.gsub("{CreateStatement}", @create_stmt);
    file_content = file_content.gsub("{DropStatement}", @drop_stmt);
    file_content = file_content.gsub("{InsertAlgorithm}", @insert_algorithm);
    file_content = file_content.gsub("{UpdateAlgorithm}", @update_algorithm);
    return dbinfo.process_file_content(file_content)
  end

  def get_base_table_info_content(dbinfo)
    content = File.read("public/templates/tables/BaseTableInfo.java")
    lookup_col = get_lookup_column
    if (lookup_col.name == "_id")
      idxStart = content.index("{LookupStart}")-1
      idxEnd = content.index("{LookupEnd}") + 11
      content = content[0..idxStart] + content[idxEnd...content.length]
    else
      content = content.gsub("{LookupStart}", "")
      content = content.gsub("{LookupEnd}", "")

      content = content.gsub("{LookupCapCamelName}", lookup_col.cap_camel_name)
      content = content.gsub("{LookupCamelName}", lookup_col.camel_name)
    end

    col_defs = ""
    col_list = ""
    col_helper_defs = ""
    col_helper_init = ""
    @columns.each do |col|
      col_defs += "\t\tString #{col.cap_name} = \"#{col.name}\";\n"
      col_list += "\t\tColumns.#{col.cap_name},\n"
      col_helper_defs += "\t\tpublic int col_#{col.lower_name} = -1;\n"
      col_helper_init += "\t\t\tcol_#{col.lower_name} = getColumnIndex(Columns.#{col.cap_name});\n"
    end

    col_helper = "#{col_helper_defs}\n\n\t\tpublic ColumnHelper(String[] projection) {\n\t\t\tsuper(projection);\n#{col_helper_init}\t\t}"

    content = content.gsub("{ColumnDefs}", col_defs);
    content = content.gsub("{ColumnList}", col_list);
    content = content.gsub("{ColumnHelperContent}", col_helper);

    content = process_file_content(content, dbinfo)
    return content
  end















  def to_s
    rtr = "\nTable named #{self.name} - camel name #{self.camel_name} - cap camel #{self.cap_camel_name}\n"
    @columns.each do |col|
      rtr += col.to_s + "\n"
    end
    return rtr + "\n"
  end

  def to_hash
    tbl = {"name" => @name, "update_algorithm" => @update_algorithm, "insert_algorithm" => @insert_algorithm, "lookup_key" => get_lookup_column.name}
    #columns = [];
    #@columns.each do |col|
    #  columns << col.to_hash
    #end
    #tbl['column'] = columns
    return tbl
  end

  def from_hash(hash)
    if (hash['name'] == @name)

      @update_algorithm = hash['update_algorithm']
      @insert_algorithm = hash['insert_algorithm']
      set_lookup_column(hash['lookup_key'])

      #columns = hash['column']
      #columns.each do |col|
      #  mycol = get_column_by_name(col['name'])
      #  if (mycol != nil)
      #    mycol.from_hash(col)
      #  end
      #end
    end
  end
end
