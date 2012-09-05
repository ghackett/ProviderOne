class IntColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Integer"
    end
    def get_hydrate_proc
      proc = File.read("public/templates/columns/int/HydrateProcedure.java")
      return process_file_content(proc)
    end
    
    def get_hydrate_json_proc
      proc = File.read("public/templates/columns/int/HydrateJsonProcedure.java")
      return process_file_content(proc)    
    end
end
