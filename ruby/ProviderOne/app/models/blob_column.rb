class BlobColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "ByteBuffer"
    end

    def get_imports
       return ["java.nio.ByteBuffer"]
    end

    def get_hydrate_proc
      proc = File.read("public/templates/columns/blob/HydrateProcedure.java")
      return process_file_content(proc)
    end
end
