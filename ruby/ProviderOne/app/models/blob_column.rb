class BlobColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "ByteBuffer"
      @is_valid_lookup_key = false
    end

    def get_imports
       return ["java.nio.ByteBuffer"]
    end

    def get_hydrate_proc
      proc = File.read("public/templates/columns/blob/HydrateProcedure.java")
      return process_file_content(proc)
    end
    
    def get_hydrate_json_proc
      proc = File.read("public/templates/columns/blob/HydrateJsonProcedure.java")
      return process_file_content(proc)    
    end

    def get_add_to_content_values(table)
      defs = "\t\tif (m#{@cap_camel_name}Set)\n"
      defs += "\t\t\tvalues.put(#{table.cap_camel_name}Info.Columns.#{@cap_name}, m#{@cap_camel_name} == null ? null : m#{@cap_camel_name}.array());\n"
      return defs
    end

    def get_add_to_json_values(table)
      defs = "\t\t//Cannot add blobs to Json objects so #{@cap_camel_name} is skipped\n"
      return defs
    end

  def get_write_to_parcel()
    defs = "\t\tdest.writeValue(m#{@cap_camel_name} == null ? null : m#{@cap_camel_name}.array());\n"
    return defs + get_write_isset_to_parcel
  end

  def get_read_from_parcel()
    defs = File.read("public/templates/columns/blob/ReadFromParcel.java")
    defs = process_file_content(defs)
    return defs + get_read_isset_to_parcel
  end
end
