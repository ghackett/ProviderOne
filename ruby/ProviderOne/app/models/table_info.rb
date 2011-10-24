class TableInfo

  attr_accessor :name, :create_stmt, :columns, :camel_name, :cap_camel_name, :update_algorithm, :insert_algorithm, :lower_name, :drop_stmt, :cap_name

  def initialize(tablename, sql, tableinfo)
    @name = tablename
    @lower_name = @name.downcase
    @create_stmt = sql.to_s.gsub("\r\n", " ").gsub("\n", " ").gsub("\"", "\\\"")
    @drop_stmt = "DROP TABLE IF EXISTS \\\"" + @name + "\\\""
    @columns = [];
    @camel_name = @name.camelize
    @camel_name[0] = @camel_name.first.downcase
    @cap_camel_name = @name.camelize
    @cap_name = @name.upcase

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



  def has_lookup_column
    lookup_col = get_lookup_column
    return lookup_col.name != "_id"
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

  def process_lookup_content(content)
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
    return content
  end

  def get_base_table_info_content(dbinfo)
    content = File.read("public/templates/tables/BaseTableInfo.java")
    content = process_lookup_content(content)

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

  def get_provider_match_defs(match_count)
    defs = ""
    defs += "\tpublic static final int #{@cap_name} = 0x#{match_count.to_s(16)};\n";
    match_count-=1;
    defs += "\tpublic static final int #{@cap_name}_COUNT = 0x#{match_count.to_s(16)};\n";
    match_count-=1;
    defs += "\tpublic static final int #{@cap_name}_ID = 0x#{match_count.to_s(16)};\n";
    match_count-=1;


    if (has_lookup_column)
      defs += "\tpublic static final int #{@cap_name}_LOOKUP = 0x#{match_count.to_s(16)};\n";
      match_count-=1;
    end

    return defs

  end

  def get_provider_uri_matcher_def
    defs = ""
    defs += "\t\tmatcher.addURI(authority, #{@cap_camel_name}Info.PATH, #{@cap_name});\n"
    defs += "\t\tmatcher.addURI(authority, #{@cap_camel_name}Info.PATH + PATH_COUNT, #{@cap_name}_COUNT);\n"
    defs += "\t\tmatcher.addURI(authority, #{@cap_camel_name}Info.PATH + PATH_ID, #{@cap_name}_ID);\n"
    if (has_lookup_column)
      defs += "\t\tmatcher.addURI(authority, #{@cap_camel_name}Info.PATH + PATH_LOOKUP, #{@cap_name}_LOOKUP);\n"
    end
    return defs
  end

  def get_provider_type_match_cases
    defs = ""
    defs += "\t\t\tcase #{@cap_name}:\n"
    defs += "\t\t\tcase #{@cap_name}_COUNT:\n"
    defs += "\t\t\t\treturn #{@cap_camel_name}Info.CONTENT_TYPE;\n"
    defs += "\t\t\tcase #{@cap_name}_ID:\n"
    if (has_lookup_column)
      defs += "\t\t\tcase #{@cap_name}_LOOKUP:\n"
    end
    defs += "\t\t\t\treturn #{@cap_camel_name}Info.CONTENT_ITEM_TYPE;\n"
    return defs
  end


  def get_provider_insert_match
    defs = ""
    defs += "\t\t\tcase #{@cap_name}: {\n"
    defs += "\t\t\t\tlong id = db.insertWithOnConflict(#{@cap_camel_name}Info.TABLE_NAME, null, values, #{@cap_camel_name}Info.INSERT_ALGORITHM);\n"
    defs += "\t\t\t\treturn #{@cap_camel_name}Info.buildIdLookupUri(id);\n"
    defs += "\t\t\t}\n"
    return defs
  end

  def get_provider_update_algorithm_match
    defs = ""
    defs += "\t\t\tcase #{@cap_name}:\n"
    defs += "\t\t\tcase #{@cap_name}_ID:\n"
    if (has_lookup_column)
      defs += "\t\t\tcase #{@cap_name}_LOOKUP:\n"
    end
    defs += "\t\t\t\talgorithm = #{@cap_camel_name}Info.UPDATE_ALGORITHM;\n"
    defs += "\t\t\t\tbreak;\n"
  end

  def get_provider_simple_selection_matches
    defs = ""
    defs += "\t\t\tcase #{@cap_name}:\n"
    defs += "\t\t\tcase #{@cap_name}_COUNT:\n"
    defs += "\t\t\t\treturn builder.table(#{@cap_camel_name}Info.TABLE_NAME);\n"
    defs += "\t\t\tcase #{@cap_name}_ID:\n"
    defs += "\t\t\t\tbuilder.where(#{@cap_camel_name}Info.Columns._ID + \"=?\", uri.getLastPathSegment());\n"
    defs += "\t\t\t\treturn builder.table(#{@cap_camel_name}Info.TABLE_NAME);\n"
    lookup_col = get_lookup_column
    if (lookup_col.name != "_id")
      defs += "\t\t\tcase #{@cap_name}_LOOKUP:\n"
      defs += "\t\t\t\tbuilder.where(#{@cap_camel_name}Info.Columns.#{lookup_col.cap_name} + \"=?\", uri.getLastPathSegment());\n"
      defs += "\t\t\t\treturn builder.table(#{@cap_camel_name}Info.TABLE_NAME);\n"
    end
    return defs
  end


  def get_base_table_object(dbinfo)
    file_content = File.read("public/templates/tables/BaseTable.java")

    file_content = process_lookup_content(file_content)

    imports = []
    col_defs = ""
    hydrate_proc = ""
    content_values = ""
    json_val = ""
    write_to_parcel = ""
    read_from_parcel = ""
    @columns.each do |col|
      imps = col.get_imports
      if (imps != nil)
        imps.each do |imp|
          imports << imp
        end
      end
      col_defs += col.get_java_def
      hydrate_proc += col.get_hydrate_proc
      content_values += col.get_add_to_content_values(self)
      json_val += col.get_add_to_json_values(self)
      write_to_parcel += col.get_write_to_parcel
      read_from_parcel += col.get_read_from_parcel
    end

    if (!imports.empty?)
      import_string = ""
      imports.uniq.each do |imp|
        import_string += "import #{imp};\n"
      end
      file_content = file_content.gsub("{Imports}", import_string);
    else
      file_content = file_content.gsub("{Imports}", "");
    end

    file_content = file_content.gsub("{JavaDefs}", col_defs);
    file_content = file_content.gsub("{HydrateProc}", hydrate_proc);
    file_content = file_content.gsub("{ToContentValues}", content_values);
    file_content = file_content.gsub("{ToJson}", json_val);
    file_content = file_content.gsub("{WriteToParcel}", write_to_parcel);
    file_content = file_content.gsub("{ReadFromParcel}", read_from_parcel);

    file_content = process_file_content(file_content, dbinfo)
    return file_content
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
