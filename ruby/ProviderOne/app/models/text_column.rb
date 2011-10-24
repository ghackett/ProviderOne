class TextColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "String"
    end
    def get_hydrate_proc
      proc = File.read("public/templates/columns/string/HydrateProcedure.java")
      return process_file_content(proc)
    end
end
