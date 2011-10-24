class CharColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Character"
    end

    def get_hydrate_proc
      proc = File.read("public/templates/columns/char/HydrateProcedure.java")
      return process_file_content(proc)
    end
end