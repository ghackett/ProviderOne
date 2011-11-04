class IdColumn < LongColumn

  def initialize(name, type)
    super(name, type)
    @camel_name = "id"
    @cap_camel_name = "Id"
  end

    def get_base_table_methods()
      return process_file_content(File.read("public/templates/columns/id_column/BaseTableMethods.java"))
    end
end
