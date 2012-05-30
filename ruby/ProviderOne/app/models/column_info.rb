class ColumnInfo

  attr_accessor :name, :type, :is_lookup_key, :camel_name, :cap_camel_name, :cap_name, :lower_name, :java_type, :is_valid_lookup_key

  def initialize(name, type)
    @name = name;
    @type = type;
    @camel_name = @name.camelize
    @camel_name[0] = @camel_name.first.downcase
    @cap_camel_name = @name.camelize
    @cap_name = @name.upcase
    @lower_name = @name.downcase
    @is_lookup_key = false
    @is_valid_lookup_key = true
  end

  def process_file_content(file_content)
    file_content = file_content.gsub("{JavaType}", @java_type);
    file_content = file_content.gsub("{SqlName}", @name);
    file_content = file_content.gsub("{LowerName}", @lower_name);
    file_content = file_content.gsub("{CamelName}", @camel_name);
    file_content = file_content.gsub("{CapCamelName}", @cap_camel_name);
    file_content = file_content.gsub("{CapName}", @cap_name);
    return file_content
  end

  def get_java_def
    defs = "\tprotected #{@java_type} m#{@cap_camel_name} = null;\n"
    defs += "\tprotected boolean m#{@cap_camel_name}Set = false;\n"
    return defs
  end

  def get_hydrate_proc
    proc = File.read("public/templates/columns/generic/HydrateProcedure.java")
    return process_file_content(proc)
  end
  
  def get_hydrate_json_proc
    proc = File.read("public/templates/columns/generic/HydrateJsonProcedure.java")
    return process_file_content(proc)    
  end

  def get_imports
    return nil
  end

  def get_add_to_content_values(table)
    defs = "\t\tif (m#{@cap_camel_name}Set)\n\t\t\tvalues.put(#{table.cap_camel_name}Info.Columns.#{@cap_name}, m#{@cap_camel_name});\n"
    return defs
  end

  def get_add_to_json_values(table)
    defs = "\t\tif (m#{@cap_camel_name}Set && h.col_#{@lower_name} != -1)\n\t\t\trtr.put(#{table.cap_camel_name}Info.Columns.#{@cap_name}, m#{@cap_camel_name});\n"
    return defs
  end

  def get_write_isset_to_parcel()
    return "\t\tdest.writeInt(m#{@cap_camel_name}Set ? 1 : 0);\n\n"
  end

  def get_read_isset_to_parcel()
    return "\t\tm#{@cap_camel_name}Set = in.readInt() == 1;\n\n"
  end

  def get_write_to_parcel()
    defs = "\t\tdest.writeValue(m#{@cap_camel_name});\n"
    return defs + get_write_isset_to_parcel
  end

  def get_read_from_parcel()
    defs = "\t\tm#{@cap_camel_name} = (#{@java_type}) in.readValue(#{@java_type}.class.getClassLoader());\n"
    return defs + get_read_isset_to_parcel
  end

  def get_base_table_methods()
    return process_file_content(File.read("public/templates/columns/generic/BaseTableMethods.java"))
  end

  def get_is_set_method()
    return process_file_content(File.read("public/templates/columns/generic/IsSetMethod.java"))
  end






  def to_s
    rtr = "COLUMN #{@name} OF TYPE #{type} - #{self.class.to_s} - #{@camel_name} - #{@cap_camel_name}"
    if @is_lookup_key
      rtr += " - LOOKUPKEY"
    end
    return rtr
  end

  #def to_hash
  #  return {"name" => @name, "is_lookup_key" => @is_lookup_key}
  #end
  #
  #def from_hash(hash)
  #  if (@name == hash['name'])
  #    @is_lookup_key = (hash['is_lookup_key'] == "true")
  #  end
  #end

  def self.get_column(col_info)
    name = col_info['name'].downcase
    type = col_info['type'].downcase

    if name == "_id"
      return IdColumn.new(name, type);
    end

    case type
      when "int", "integer"
        return IntColumn.new(name, type)
      when "string", "text", "varchar", "clob"
        return TextColumn.new(name, type)
      when "double", "real", "numeric"
        return DoubleColumn.new(name, type)
      when "bool", "boolean"
        return BoolColumn.new(name, type)
      when "float"
        return FloatColumn.new(name, type)
      when "long"
        return LongColumn.new(name, type)
      when "char", "character"
        return CharColumn.new(name, type)
      when "blob"
        return BlobColumn.new(name, type)
      when "datetime", "date", "timestamp"
        return DateTimeColumn.new(name, type)
    end
    return TextColumn.new(name, type)
  end

end
