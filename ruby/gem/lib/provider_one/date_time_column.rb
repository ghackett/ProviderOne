class DateTimeColumn < ColumnInfo
    def initialize(name, type)
      super(name, type)
      @java_type = "Long"
      @is_valid_lookup_key = false
    end

    def get_imports
       return ["java.util.Date"]
    end

    def get_base_table_methods()
      return process_file_content(File.read("public/templates/columns/datetime/BaseTableMethods.java"))
    end
    
end
