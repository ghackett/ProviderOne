class IdColumn < LongColumn

  def initialize(name, type)
    super(name, type)
    @camel_name = "id"
    @cap_camel_name = "Id"
  end

    def get_base_table_methods()
      return process_file_content(File.read("public/templates/columns/id_column/BaseTableMethods.java"))
    end
    
    def get_hydrate_json_proc
      proc = File.read("public/templates/columns/id_column/HydrateJsonProcedure.java")
      return process_file_content(proc)    
    end
end
