class BoolColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Boolean"
      @is_valid_lookup_key = false
    end

    def get_hydrate_proc
      proc = File.read("public/templates/columns/bool/HydrateProcedure.java")
      return process_file_content(proc)
    end
    
    def get_hydrate_json_proc
      proc = File.read("public/templates/columns/bool/HydrateJsonProcedure.java")
      return process_file_content(proc)    
    end
end
