class CharColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Character"
    end

    def get_hydrate_proc
      proc = File.read("public/templates/columns/char/HydrateProcedure.java")
      return process_file_content(proc)
    end

    def get_add_to_content_values(table)
      defs = "\t\tif (m#{@cap_camel_name}Set)\n"
      defs += "\t\t\tvalues.put(#{table.cap_camel_name}Info.Columns.#{@cap_name}, m#{@cap_camel_name} == null ? null : String.valueOf(m#{@cap_camel_name}));\n"
      return defs
    end

    def get_add_to_json_values(table)
      defs = "\t\tif (m#{@cap_camel_name}Set && h.col_#{@lower_name} != -1)\n\t\t\trtr.put(#{table.cap_camel_name}Info.Columns.#{@cap_name}, String.valueOf(m#{@cap_camel_name}));\n"
      return defs
    end

  def get_write_to_parcel()
    defs = "\t\tdest.writeValue(m#{@cap_camel_name} == null ? null : String.valueOf(m#{@cap_camel_name}));\n"
    return defs + get_write_isset_to_parcel
  end

  def get_read_from_parcel()
    defs = File.read("public/templates/columns/char/ReadFromParcel.java")
    defs = process_file_content(defs)
    return defs + get_read_isset_to_parcel
  end
end